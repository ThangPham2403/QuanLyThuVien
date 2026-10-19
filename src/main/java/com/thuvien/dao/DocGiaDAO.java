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

    /**
     * Tìm thông tin độc giả dựa trên mã tài khoản đã liên kết.
     *
     * @param taiKhoanId Mã tài khoản cần tìm độc giả tương ứng.
     *
     * @return Đối tượng DocGia nếu tìm thấy độc giả có mã tài khoản
     *         tương ứng; trả về null nếu không tìm thấy hoặc chưa liên kết.
     *
     * @throws SQLException Nếu xảy ra lỗi khi truy cập cơ sở dữ liệu.
     */
    public DocGia timTheoTaiKhoan(String taiKhoanId) throws SQLException {

        // Câu lệnh SQL lấy mã độc giả, tên độc giả và số điện thoại
        // từ bảng DOCGIA dựa trên mã tài khoản.
        // Dấu ? là vị trí sẽ nhận giá trị taiKhoanId.
        String sql = "SELECT docgia_id, docgia_name, sdt "
                   + "FROM DOCGIA WHERE taikhoan_id = ?";

        // Mở kết nối đến CSDL và chuẩn bị câu lệnh SQL.
        // try-with-resources tự động đóng Connection và PreparedStatement
        // sau khi hoàn thành, kể cả khi xảy ra lỗi.
        try (Connection ket_noi = DBConnection.getConnection();
             PreparedStatement cau_lenh = ket_noi.prepareStatement(sql)) {

            // Gán mã tài khoản vào tham số ? thứ nhất trong câu SQL.
            cau_lenh.setString(1, taiKhoanId);

            // Thực thi câu lệnh SELECT và nhận kết quả truy vấn.
            try (ResultSet ket_qua = cau_lenh.executeQuery()) {

                // Kiểm tra xem kết quả có bản ghi nào hay không.
                // ket_qua.next() di chuyển đến bản ghi đầu tiên nếu có.
                if (ket_qua.next()) {

                    // Tạo một đối tượng DocGia để lưu thông tin
                    // lấy được từ cơ sở dữ liệu.
                    DocGia dg = new DocGia();

                    // Lấy mã độc giả từ cột docgia_id
                    // và gán vào đối tượng DocGia.
                    dg.setDocGiaId(ket_qua.getString("docgia_id"));

                    // Lấy tên độc giả từ cột docgia_name
                    // và gán vào đối tượng DocGia.
                    dg.setDocGiaName(ket_qua.getString("docgia_name"));

                    // Lấy số điện thoại từ cột sdt
                    // và gán vào đối tượng DocGia.
                    dg.setSdt(ket_qua.getString("sdt"));

                    // Trả về đối tượng DocGia chứa thông tin đã tìm được.
                    return dg;
                }
            }
        }

        // Nếu không tìm thấy độc giả có mã tài khoản tương ứng,
        // trả về null để tầng gọi biết rằng không có kết quả.
        return null;
    }
}

