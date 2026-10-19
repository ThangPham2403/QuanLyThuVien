// Khai báo package chứa lớp điều khiển đăng nhập.
package com.thuvien.controller;

// Import DAO để truy cập dữ liệu tài khoản và độc giả.
import com.thuvien.dao.TaiKhoanDAO;
import com.thuvien.dao.DocGiaDAO;

// Import Model chứa thông tin tài khoản và độc giả.
import com.thuvien.model.TaiKhoan;
import com.thuvien.model.DocGia;

// Import lớp lưu thông tin phiên đăng nhập.
//import com.thuvien.utils;
import com.thuvien.utils.PhienDangNhap;

// Import để xử lý lỗi truy vấn cơ sở dữ liệu.
import java.sql.SQLException;

public class DangNhapController {

    // Khởi tạo DAO để kiểm tra tài khoản.
    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    // Khởi tạo DAO để tìm thông tin độc giả.
    private final DocGiaDAO docGiaDAO = new DocGiaDAO();

    /**
     * Điều phối quá trình đăng nhập.
     *
     * @param user Tên đăng nhập.
     * @param pass Mật khẩu.
     * @param role Vai trò ADMIN hoặc DOCGIA.
     *
     * @return null nếu đăng nhập thành công;
     *         thông báo lỗi nếu đăng nhập thất bại.
     */
    public String login(String user, String pass, String role) {

        // Kiểm tra người dùng đã chọn loại tài khoản chưa.
        if (role == null) {
            return "Vui lòng chọn loại tài khoản (Độc giả hoặc Admin)!";
        }

        // Kiểm tra tên đăng nhập và mật khẩu.
        if (user == null || user.trim().isEmpty()
                || pass == null || pass.isEmpty()) {
            return "Vui lòng nhập tên đăng nhập và mật khẩu!";
        }

        try {
            // Bước 1: Kiểm tra tài khoản, mật khẩu và vai trò.
            TaiKhoan tk = taiKhoanDAO.dangNhap(
                user.trim(), pass, role
            );

            // Nếu không tìm thấy tài khoản phù hợp.
            if (tk == null) {
                return "Sai tài khoản, mật khẩu hoặc vai trò không hợp lệ!";
            }

            // Biến lưu thông tin độc giả nếu tài khoản là DOCGIA.
            DocGia dg = null;

            // Bước 2: Nếu là độc giả, tìm hồ sơ được liên kết.
            if ("DOCGIA".equalsIgnoreCase(tk.getRoles())) {

                dg = docGiaDAO.timTheoTaiKhoan(
                    tk.getTaiKhoanId()
                );

                // Nếu tài khoản chưa liên kết với hồ sơ độc giả.
                if (dg == null) {
                    return "Tài khoản chưa được liên kết với độc giả nào. "
                         + "Vui lòng liên hệ quản trị viên!";
                }
            }

            // Bước 3: Xóa thông tin phiên cũ trước khi lưu phiên mới.
            PhienDangNhap.clear();

            // Lưu thông tin chung của tài khoản đăng nhập.
            PhienDangNhap.taiKhoanId = tk.getTaiKhoanId();
            PhienDangNhap.taiKhoanName = tk.getTaiKhoanName();
            PhienDangNhap.role = tk.getRoles();

            // Nếu là độc giả, lưu thêm thông tin cá nhân.
            if (dg != null) {
                PhienDangNhap.maDocGia = dg.getDocGiaId();
                PhienDangNhap.tenDocGia = dg.getDocGiaName();
                PhienDangNhap.sdtDocGia = dg.getSdt();
            }

            // Đăng nhập thành công.
            return null;

        } catch (SQLException ex) {

            // Trả thông báo lỗi để giao diện hiển thị.
            return "Lỗi kết nối cơ sở dữ liệu: "
                 + ex.getMessage();
        }
    }
    
    
}
