package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.ThongTinTheModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/** DAO chịu trách nhiệm truy vấn/cập nhật dữ liệu thẻ độc giả trong SQL Server. */
public class ThongTinTheDAO {

    /** Trả về thông tin độc giả theo tài khoản đăng nhập, kèm thống kê mượn. */
    public ThongTinTheModel layTheoTaiKhoan(String taiKhoanId) throws SQLException {
        String sqlDocGia = "SELECT docgia_id, docgia_name, sdt "
                + "FROM DOCGIA WHERE taikhoan_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlDocGia)) {
            ps.setString(1, taiKhoanId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                String docGiaId = rs.getString("docgia_id");
                String hoTen = rs.getString("docgia_name");
                String sdt = rs.getString("sdt");
                int soSachDangMuon = laySoSachDangMuon(con, docGiaId);
                int luotMuon = layLuotMuon(con, docGiaId);
                return new ThongTinTheModel(docGiaId, hoTen, sdt,
                        soSachDangMuon, luotMuon);
            }
        }
    }

    private int laySoSachDangMuon(Connection con, String docGiaId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(ct.so_luong), 0) AS so_sach "
                + "FROM MUONTRA mt "
                + "LEFT JOIN CT_MUONTRA ct ON ct.muontra_id = mt.muontra_id "
                + "WHERE mt.docgia_id = ? "
                + "AND mt.trang_thai IN (N'Đang mượn', N'Quá hạn')";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, docGiaId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("so_sach") : 0;
            }
        }
    }

    private int layLuotMuon(Connection con, String docGiaId) throws SQLException {
        String sql = "SELECT COUNT(*) AS luot_muon FROM MUONTRA WHERE docgia_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, docGiaId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("luot_muon") : 0;
            }
        }
    }

    /** Chỉ cập nhật hồ sơ khi cả docgia_id và taikhoan_id cùng khớp. */
    public boolean capNhat(String docGiaId, String taiKhoanId,
            String hoTen, String sdt) throws SQLException {
        String sql = "UPDATE DOCGIA SET docgia_name = ?, sdt = ? "
                + "WHERE docgia_id = ? AND taikhoan_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hoTen);
            if (sdt == null || sdt.isEmpty()) {
                ps.setNull(2, Types.VARCHAR);
            } else {
                ps.setString(2, sdt);
            }
            ps.setString(3, docGiaId);
            ps.setString(4, taiKhoanId);
            return ps.executeUpdate() > 0;
        }
    }
}
