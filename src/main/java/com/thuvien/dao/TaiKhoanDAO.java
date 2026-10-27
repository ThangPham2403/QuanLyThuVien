// Khai báo package chứa lớp TaiKhoanDAO.
// DAO là viết tắt của Data Access Object, dùng để truy cập dữ liệu.
package com.thuvien.dao;

// Import lớp dùng để tạo kết nối đến cơ sở dữ liệu MySQL.
import com.thuvien.config.DBConnection;
import com.thuvien.model.DocGia;

// Import lớp Model đại diện cho thông tin tài khoản.
import com.thuvien.model.TaiKhoan;

// Import các lớp JDBC để làm việc với cơ sở dữ liệu.
import java.sql.Connection;        // Đại diện cho kết nối CSDL.
import java.sql.PreparedStatement;  // Thực thi câu lệnh SQL có tham số.
import java.sql.ResultSet;          // Lưu kết quả truy vấn SQL.
import java.sql.SQLException;       // Xử lý ngoại lệ liên quan đến SQL.
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    /**
     * Phương thức kiểm tra thông tin đăng nhập của tài khoản.
     *
     * @param user Tên đăng nhập do người dùng nhập.
     * @param pass Mật khẩu do người dùng nhập.
     * @param role Vai trò được chọn: ADMIN hoặc DOCGIA.
     *
     * @return Đối tượng TaiKhoan nếu thông tin đăng nhập hợp lệ;
     *         trả về null nếu không tìm thấy tài khoản phù hợp.
     *
     * @throws SQLException Nếu xảy ra lỗi khi truy cập cơ sở dữ liệu.
     */
    public TaiKhoan dangNhap(String user, String pass, String role)
            throws SQLException {

        // Câu lệnh SQL tìm tài khoản theo tên đăng nhập,
        // mật khẩu và vai trò.
        // Dấu ? là các tham số sẽ được truyền vào bằng setString().
        String sql = "SELECT taikhoan_id, taikhoan_name, email, password, roles FROM TAIKHOAN "
                   + "WHERE taikhoan_name = ? AND password = ? AND roles = ?";

        // Mở kết nối và chuẩn bị câu lệnh SQL.
        // try-with-resources tự động đóng tài nguyên khi kết thúc.
        try (Connection ket_noi = DBConnection.getConnection();
             PreparedStatement cau_lenh = ket_noi.prepareStatement(sql)) {

            // Truyền tên đăng nhập vào dấu ? thứ nhất.
            cau_lenh.setString(1, user);

            // Truyền mật khẩu vào dấu ? thứ hai.
            cau_lenh.setString(2, pass);

            // Truyền vai trò vào dấu ? thứ ba.
            cau_lenh.setString(3, role);

            // Thực thi câu lệnh SELECT và nhận kết quả truy vấn.
            try (ResultSet ket_qua= cau_lenh.executeQuery()) {

                // Kiểm tra xem có tài khoản nào khớp với
                // tên đăng nhập, mật khẩu và vai trò hay không.
                if (ket_qua.next()) {

                    // Nếu tìm thấy tài khoản, lấy dữ liệu từ
                    // ResultSet và tạo đối tượng TaiKhoan.
                    return new TaiKhoan(
                        ket_qua.getString("taikhoan_id"),   // Mã tài khoản.
                        ket_qua.getString("taikhoan_name"), // Tên đăng nhập.
                        ket_qua.getString("email"),         // Email tài khoản.
                        ket_qua.getString("password"),      // Mật khẩu lưu trong CSDL.
                        ket_qua.getString("roles")          // Vai trò tài khoản.
                    );
                }
            }
        }

        // Không tìm thấy tài khoản phù hợp thì trả về null.
        return null;
    }
    
    //--------------------------------------------------------------------
    //
    //------------------------------------------------------------------------
    
     /** Danh sách: {mã TK, tên đăng nhập, email, vai trò, họ tên độc giả}. */
    public List<Object[]> layDanhSach() throws SQLException {
        String sql = "SELECT t.taikhoan_id, t.taikhoan_name, t.email, t.roles, d.docgia_name "
                   + "FROM TAIKHOAN t LEFT JOIN DOCGIA d ON d.taikhoan_id = t.taikhoan_id "
                   + "ORDER BY t.taikhoan_id";
        List<Object[]> ds = new ArrayList<>();
        try (Connection ket_noi = DBConnection.getConnection();
             PreparedStatement cau_lenh = ket_noi.prepareStatement(sql);
             ResultSet ket_qua = cau_lenh.executeQuery()) {
            while (ket_qua.next()) {
                ds.add(new Object[]{ket_qua.getString(1), ket_qua.getString(2),
                        ket_qua.getString(3), ket_qua.getString(4), ket_qua.getString(5)});
            }
        }
        return ds;
    }

    public boolean tonTaiTenDangNhap(String ten) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TAIKHOAN WHERE taikhoan_name = ?";
        try (Connection ket_noi = DBConnection.getConnection();
             PreparedStatement cau_lenh = ket_noi.prepareStatement(sql)) {
            cau_lenh.setString(1, ten);
            try (ResultSet ket_qua = cau_lenh.executeQuery()) {
                return ket_qua.next() && ket_qua.getInt(1) > 0;
            }
        }
    }

    /** Số lượt mượn của độc giả gắn với tài khoản (0 nếu là Admin). */
    public int demLuotMuon(String taiKhoanId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM MUONTRA m JOIN DOCGIA d ON m.docgia_id = d.docgia_id "
                   + "WHERE d.taikhoan_id = ?";
        try (Connection ket_noi = DBConnection.getConnection();
             PreparedStatement cau_lenh = ket_noi.prepareStatement(sql)) {
            cau_lenh.setString(1, taiKhoanId);
            try (ResultSet ket_qua = cau_lenh.executeQuery()) {
                return ket_qua.next() ? ket_qua.getInt(1) : 0;
            }
        }
    }

    // Sinh mã mới dạng TK001, DG001... (bảng, cột, tiền tố là hằng số trong code)
    private String taoMa(Connection ket_noi, String bang, String cot, String tienTo) throws SQLException {
        String sql = "SELECT MAX(" + cot + ") FROM " + bang;
        try (PreparedStatement cau_lenh = ket_noi.prepareStatement(sql);
             ResultSet ket_qua = cau_lenh.executeQuery()) {
            if (ket_qua.next() && ket_qua.getString(1) != null) {
                int so = Integer.parseInt(ket_qua.getString(1).substring(tienTo.length())) + 1;
                return String.format("%s%03d", tienTo, so);
            }
        }
        return tienTo + "001";
    }

    /** Thêm tài khoản; dg khác null thì thêm luôn độc giả. Một bước lỗi thì hoàn tác cả hai. */
    public void them(TaiKhoan tk, DocGia dg) throws SQLException {
        try (Connection ket_noi = DBConnection.getConnection()) {
            ket_noi.setAutoCommit(false);
            try {
                String tkId = taoMa(ket_noi, "TAIKHOAN", "taikhoan_id", "TK");
                try (PreparedStatement cau_lenh = ket_noi.prepareStatement(
                        "INSERT INTO TAIKHOAN (taikhoan_id, taikhoan_name, email, password, roles) VALUES (?,?,?,?,?)")) {
                    cau_lenh.setString(1, tkId);
                    cau_lenh.setString(2, tk.getTaiKhoanName());
                    cau_lenh.setString(3, tk.getEmail());
                    cau_lenh.setString(4, tk.getPassword());
                    cau_lenh.setString(5, tk.getRoles());
                    cau_lenh.executeUpdate();
                }
                if (dg != null) {
                    String dgId = taoMa(ket_noi, "DOCGIA", "docgia_id", "DG");
                    try (PreparedStatement cau_lenh = ket_noi.prepareStatement(
                            "INSERT INTO DOCGIA (docgia_id, docgia_name, email, taikhoan_id) VALUES (?,?,?,?)")) {
                        cau_lenh.setString(1, dgId);
                        cau_lenh.setString(2, dg.getDocGiaName());
                        cau_lenh.setString(3, tk.getEmail());
                        cau_lenh.setString(4, tkId);
                        cau_lenh.executeUpdate();
                    }
                }
                ket_noi.commit();
            } catch (SQLException ex) {
                ket_noi.rollback();
                throw ex;
            }
        }
    }

       /** Sửa tên đăng nhập, email, mật khẩu (nếu nhập mới), họ tên độc giả (hoTen = null nếu là Admin). */
    public void capNhat(String id, String ten, String email, String matKhauMoi, String hoTen) throws SQLException {
        boolean doiMatKhau = matKhauMoi != null && !matKhauMoi.isEmpty();
        try (Connection ket_noi = DBConnection.getConnection()) {
            ket_noi.setAutoCommit(false);
            try {
                String sql = doiMatKhau
                    ? "UPDATE TAIKHOAN SET taikhoan_name = ?, email = ?, password = ? WHERE taikhoan_id = ?"
                    : "UPDATE TAIKHOAN SET taikhoan_name = ?, email = ? WHERE taikhoan_id = ?";
                try (PreparedStatement cau_lenh = ket_noi.prepareStatement(sql)) {
                    cau_lenh.setString(1, ten);
                    cau_lenh.setString(2, email);
                    if (doiMatKhau) { cau_lenh.setString(3, matKhauMoi); cau_lenh.setString(4, id); }
                    else            { cau_lenh.setString(3, id); }
                    cau_lenh.executeUpdate();
                }
                if (hoTen != null) {
                    try (PreparedStatement cau_lenh = ket_noi.prepareStatement(
                            "UPDATE DOCGIA SET docgia_name = ?, email = ? WHERE taikhoan_id = ?")) {
                        cau_lenh.setString(1, hoTen);
                        cau_lenh.setString(2, email);
                        cau_lenh.setString(3, id);
                        cau_lenh.executeUpdate();
                    }
                }
                ket_noi.commit();
            } catch (SQLException ex) {
                ket_noi.rollback();
                throw ex;
            }
        }
    }

    /** Tên đăng nhập đã bị tài khoản KHÁC dùng chưa (bỏ qua chính tài khoản đang sửa). */
    public boolean tonTaiTenDangNhapKhac(String ten, String id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TAIKHOAN WHERE taikhoan_name = ? AND taikhoan_id <> ?";
        try (Connection ket_noi = DBConnection.getConnection();
             PreparedStatement cau_lenh = ket_noi.prepareStatement(sql)) {
            cau_lenh.setString(1, ten);
            cau_lenh.setString(2, id);
            try (ResultSet ket_qua = cau_lenh.executeQuery()) {
                return ket_qua.next() && ket_qua.getInt(1) > 0;
            }
        }
    }


    /** Xóa độc giả (nếu có) rồi xóa tài khoản, trong một transaction. */
    public void xoa(String id) throws SQLException {
        try (Connection ket_noi = DBConnection.getConnection()) {
            ket_noi.setAutoCommit(false);
            try {
                try (PreparedStatement cau_lenh = ket_noi.prepareStatement(
                        "DELETE FROM DOCGIA WHERE taikhoan_id = ?")) {
                    cau_lenh.setString(1, id);
                    cau_lenh.executeUpdate();
                }
                try (PreparedStatement cau_lenh = ket_noi.prepareStatement(
                        "DELETE FROM TAIKHOAN WHERE taikhoan_id = ?")) {
                    cau_lenh.setString(1, id);
                    cau_lenh.executeUpdate();
                }
                ket_noi.commit();
            } catch (SQLException ex) {
                ket_noi.rollback();
                throw ex;
            }
        }
    }

    
    /** Mật khẩu hiện tại có đúng với tài khoản không. */
    public boolean kiemTraMatKhau(String taiKhoanId, String matKhau) throws SQLException {	
        String sql = "SELECT COUNT(*) FROM TAIKHOAN WHERE taikhoan_id = ? AND password = ?";
        try (Connection ket_noi = DBConnection.getConnection();
             PreparedStatement cau_lenh = ket_noi.prepareStatement(sql)) {
            cau_lenh.setString(1, taiKhoanId);
            cau_lenh.setString(2, matKhau);
            try (ResultSet ket_qua = cau_lenh.executeQuery()) {
                return ket_qua.next() && ket_qua.getInt(1) > 0;
            }
        }
    }

    public void doiMatKhau(String taiKhoanId, String matKhauMoi) throws SQLException {
        String sql = "UPDATE TAIKHOAN SET password = ? WHERE taikhoan_id = ?";
        try (Connection ket_noi = DBConnection.getConnection();
             PreparedStatement cau_lenh = ket_noi.prepareStatement(sql)) {
            cau_lenh.setString(1, matKhauMoi);
            cau_lenh.setString(2, taiKhoanId);
            cau_lenh.executeUpdate();
        }
    }

    
    
}
