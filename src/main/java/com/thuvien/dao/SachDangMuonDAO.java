package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.SachDangMuonCaNhanModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Truy vấn các phiếu mượn của một độc giả. */
public class SachDangMuonDAO {

    private static final String SQL_DANH_SACH =
            "SELECT mt.muontra_id, "
            + "mt.thoi_gian_muon, "
            + "mt.thoi_gian_tra, "
            + "mt.trang_thai, "
            + "ISNULL((SELECT SUM(ct.so_luong) "
            + " FROM CT_MUONTRA ct WHERE ct.muontra_id = mt.muontra_id), 0) AS tong_sl, "
            + "(SELECT STRING_AGG(x.ten_sach, N', ') "
            + " FROM (SELECT DISTINCT s.sach_name AS ten_sach "
            + "       FROM CT_MUONTRA ct2 "
            + "       JOIN SACH s ON s.sach_id = ct2.sach_id "
            + "       WHERE ct2.muontra_id = mt.muontra_id) x) AS ten_sach "
            + "FROM MUONTRA mt "
            + "WHERE mt.docgia_id = ? "
            + "ORDER BY mt.thoi_gian_muon DESC";

    public List<SachDangMuonCaNhanModel> layTheoDocGia(String maDocGia) throws SQLException {
        List<SachDangMuonCaNhanModel> danhSach = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DANH_SACH)) {
            ps.setString(1, maDocGia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String tenSach = rs.getString("ten_sach");
                    if (tenSach == null || tenSach.trim().isEmpty()) {
                        tenSach = "Chưa có sách";
                    }
                    danhSach.add(new SachDangMuonCaNhanModel(
                            rs.getString("muontra_id"), tenSach,
                            rs.getInt("tong_sl"), rs.getTimestamp("thoi_gian_muon"),
                            rs.getTimestamp("thoi_gian_tra"), rs.getString("trang_thai")));
                }
            }
        }
        return danhSach;
    }
}
