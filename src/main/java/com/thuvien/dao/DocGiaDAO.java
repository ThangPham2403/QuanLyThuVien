// Khai báo package chứa lớp DocGiaDAO.
// DAO (Data Access Object) dùng để truy cập dữ liệu độc giả.
package com.thuvien.dao;

// Import lớp quản lý kết nối đến cơ sở dữ liệu MySQL.
import com.thuvien.config.DBConnection;

// Import lớp Model đại diện cho thông tin độc giả.
import com.thuvien.model.DocGia;

// Import các lớp JDBC dùng để truy vấn cơ sở dữ liệu.
import java.sql.Connection;        // Đại diện cho kết nối CSDL.
import java.sql.PreparedStatement;  // Chuẩn bị và thực thi câu lệnh SQL có tham số.
import java.sql.ResultSet;          // Chứa kết quả truy vấn SQL.
import java.sql.SQLException;       // Xử lý lỗi liên quan đến SQL.

public class DocGiaDAO {

    // Doc gia dao cua chien
    public DocGia timTheoTaiKhoan(String taiKhoanId) throws SQLException {
        String sql = "SELECT docgia_id, docgia_name, sdt "
                   + "FROM DOCGIA WHERE taikhoan_id = ?";
        try (Connection ket_noi = DBConnection.getConnection();
             PreparedStatement cau_lenh = ket_noi.prepareStatement(sql)) {

            // Gán mã tài khoản vào tham số ? thứ nhất trong câu SQL.
            cau_lenh.setString(1, taiKhoanId);

            // Thực thi câu lệnh SELECT và nhận kết quả truy vấn.
            try (ResultSet ket_qua = cau_lenh.executeQuery()) {
                if (ket_qua.next()) {
                    DocGia dg = new DocGia();
                    dg.setDocGiaId(ket_qua.getString("docgia_id"));
                    dg.setDocGiaName(ket_qua.getString("docgia_name"));
                    dg.setSdt(ket_qua.getString("sdt"));
                    return dg;
                }
            }
        }
        return null;
    }


    // Doc Gia dao cua hien
    //  Cập nhật thông tin độc giả khi bấm nút "Lưu thay đổi"
    public boolean updateDocGia(DocGia dg) {

        String sql = "UPDATE DOCGIA SET diachi = ?, sdt = ?, email = ? WHERE docgia_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dg.getDiaChi());
            ps.setString(2, dg.getSdt());
            ps.setString(3, dg.getEmail());
            ps.setString(4, dg.getDocGiaId()); // Giá trị là DG001
            ps.setString(5, dg.getDocGiaName());
            ps.setString(6, dg.getNgaySinh());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

