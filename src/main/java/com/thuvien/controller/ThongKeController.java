
package com.thuvien.controller;

import com.thuvien.config.DBConnection;
import com.thuvien.model.ThongKeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKeController {

    // Đếm số bản ghi theo câu lệnh SQL
    private int demSoLuong(Connection conn, String sql)
            throws SQLException {

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }

    // Lấy toàn bộ dữ liệu thống kê
    public ThongKeModel layThongKe() throws SQLException {
        
        new com.thuvien.dao.TrangThaiMuonTraDAO().capNhatTrangThaiQuaHan();
        
        // Đếm tổng số đầu sách
        String sqlSach = "SELECT COUNT(*) FROM SACH";

        // Đếm số lượt mượn đang mượn
        String sqlDangMuon = "SELECT COUNT(*) FROM MUONTRA "
                + "WHERE trang_thai = N'Đang mượn'";

        // Đếm số lượt mượn quá hạn
        String sqlQuaHan = "SELECT COUNT(*) FROM MUONTRA "
                + "WHERE trang_thai = N'Quá hạn'";

        // Đếm tổng số độc giả
        String sqlDocGia = "SELECT COUNT(*) FROM DOCGIA";

        // Kết nối cơ sở dữ liệu và lấy kết quả
        try (Connection conn = DBConnection.getConnection()) {

            int tongDauSach = demSoLuong(conn, sqlSach);
            int dangMuon = demSoLuong(conn, sqlDangMuon);
            int quaHan = demSoLuong(conn, sqlQuaHan);
            int tongDocGia = demSoLuong(conn, sqlDocGia);

            // Đóng gói dữ liệu vào Model và trả về
            return new ThongKeModel(
                    tongDauSach,
                    dangMuon,
                    quaHan,
                    tongDocGia
            );
        }
    }

    public ThongKeModel getThongKe() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

