package com.thuvien.controller;

import com.thuvien.dao.TaiKhoanDAO;
import com.thuvien.model.DocGia;
import com.thuvien.model.TaiKhoan;
import com.thuvien.utils.PhienDangNhap;
import java.sql.SQLException;
import java.util.List;

public class TaiKhoanController {

    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private static final String MAU_EMAIL = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$";

    public List<Object[]> layDanhSach() throws SQLException {
        return taiKhoanDAO.layDanhSach();
    }

    /** Trả về null nếu thành công, ngược lại là nội dung lỗi. */
    public String them(String ten, String matKhau, String email, String vaiTro, String hoTen) {
        vaiTro = vaiTro == null ? "" : vaiTro.toUpperCase();
        if (ten.isEmpty())
            return "Vui lòng nhập tên đăng nhập!";
        if (matKhau.length() < 6)
            return "Mật khẩu phải có ít nhất 6 ký tự!";
        if (!email.matches(MAU_EMAIL))
            return "Email không đúng định dạng!";
        if (!vaiTro.equals("ADMIN") && !vaiTro.equals("DOCGIA"))
            return "Vai trò không hợp lệ!";
        if (vaiTro.equals("DOCGIA") && hoTen.isEmpty())
            return "Vui lòng nhập họ tên độc giả!";

        try {
            if (taiKhoanDAO.tonTaiTenDangNhap(ten))
                return "Tên đăng nhập đã tồn tại!";

            TaiKhoan tk = new TaiKhoan(null, ten, email, matKhau, vaiTro);
            DocGia dg = null;
            if (vaiTro.equals("DOCGIA")) {
                dg = new DocGia();
                dg.setDocGiaName(hoTen);
            }
            taiKhoanDAO.them(tk, dg);
            return null;
        } catch (SQLException ex) {
            return "Lỗi cơ sở dữ liệu: " + ex.getMessage();
        }
    }

        public String capNhat(String id, String ten, String email, String matKhauMoi, String vaiTro, String hoTen) {
        if (id == null)
            return "Vui lòng chọn một tài khoản trong bảng!";
        if (ten.isEmpty())
            return "Vui lòng nhập tên đăng nhập!";
        if (!email.matches(MAU_EMAIL))
            return "Email không đúng định dạng!";
        if (!matKhauMoi.isEmpty() && matKhauMoi.length() < 6)
            return "Mật khẩu mới phải có ít nhất 6 ký tự!";
        boolean laDocGia = "DOCGIA".equalsIgnoreCase(vaiTro);
        if (laDocGia && hoTen.isEmpty())
            return "Vui lòng nhập họ tên độc giả!";

        try {
            if (taiKhoanDAO.tonTaiTenDangNhapKhac(ten, id))
                return "Tên đăng nhập đã được tài khoản khác sử dụng!";
            taiKhoanDAO.capNhat(id, ten, email, matKhauMoi, laDocGia ? hoTen : null);
            return null;
        } catch (SQLException ex) {
            return "Lỗi cơ sở dữ liệu: " + ex.getMessage();
        }
    }


    public String xoa(String id) {
        if (id == null)
            return "Vui lòng chọn một tài khoản trong bảng!";
        if (id.equals(PhienDangNhap.taiKhoanId))
            return "Không thể xóa tài khoản đang đăng nhập!";
        try {
            if (taiKhoanDAO.demLuotMuon(id) > 0)
                return "Độc giả này đã có lịch sử mượn trả, không thể xóa!";
            taiKhoanDAO.xoa(id);
            return null;
        } catch (SQLException ex) {
            return "Lỗi cơ sở dữ liệu: " + ex.getMessage();
        }
    }
}
