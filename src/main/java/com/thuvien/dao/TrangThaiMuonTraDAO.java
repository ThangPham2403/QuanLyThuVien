package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Cập nhật trạng thái những phiếu mượn đã quá hạn.
 */
public class TrangThaiMuonTraDAO {

    public void capNhatTrangThaiQuaHan() throws SQLException {

        String sql =
            "UPDATE MUONTRA "
          + "SET trang_thai = N'Quá hạn' "
          + "WHERE trang_thai = N'Đang mượn' "
          + "AND thoi_gian_tra IS NOT NULL "
          + "AND CONVERT(date, thoi_gian_tra) < CONVERT(date, GETDATE())";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();
        }
    }
}