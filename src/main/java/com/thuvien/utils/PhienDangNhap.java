package com.thuvien.utils;

// Lớp dùng để lưu trữ thông tin tài khoản đang đăng nhập
// trong phiên làm việc hiện tại của ứng dụng.
public class PhienDangNhap {

    // Thông tin chung của tài khoản đang đăng nhập
    public static String taiKhoanId;    // Mã tài khoản, ví dụ: TK001
    public static String taiKhoanName;  // Tên đăng nhập
    public static String role;          // Vai trò: ADMIN hoặc DOCGIA

    // Thông tin riêng của độc giả
    // Nếu tài khoản là ADMIN thì các thuộc tính này có thể bằng null.
    public static String maDocGia;      // Mã độc giả, ví dụ: DG001
    public static String tenDocGia;     // Họ và tên độc giả
    public static String sdtDocGia;     // Số điện thoại của độc giả

    // Kiểm tra tài khoản hiện tại có phải là quản trị viên hay không.
    // Trả về true nếu role bằng "ADMIN", không phân biệt chữ hoa, chữ thường.
    // Trả về false nếu là DOCGIA hoặc role chưa được thiết lập.
    public static boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }

    // Xóa toàn bộ thông tin phiên đăng nhập hiện tại.
    // Thường được gọi khi người dùng đăng xuất.
    public static void clear() {
        taiKhoanId = taiKhoanName = role = null;
        maDocGia = tenDocGia = sdtDocGia = null;
    }

    public String login(String user, String pass, String role) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

