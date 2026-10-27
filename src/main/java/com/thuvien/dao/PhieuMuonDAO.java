package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.CTMuonTra;
import com.thuvien.model.MuonTra;
import com.thuvien.model.PhieuMuon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {

    // Giữ lại phương thức cũ để không làm ảnh hưởng phần code đang dùng MuonTra.
    public List<MuonTra> selectAll() {
        List<MuonTra> list = new ArrayList<>();
        String sql = "SELECT muontra_id, docgia_id, thoi_gian_muon, "
                + "thoi_gian_tra, trang_thai FROM MUONTRA";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MuonTra mt = new MuonTra();
                mt.setMuonTraId(rs.getString("muontra_id"));
                mt.setDocGiaId(rs.getString("docgia_id"));
                mt.setThoiGianMuon(rs.getTimestamp("thoi_gian_muon"));
                mt.setThoiGianTra(rs.getTimestamp("thoi_gian_tra"));
                mt.setTrangThai(rs.getString("trang_thai"));
                list.add(mt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Phương thức insert cũ được giữ lại để tương thích với code dùng model MuonTra.
    public boolean insert(MuonTra mt) {
        String sql = "INSERT INTO MUONTRA "
                + "(muontra_id, docgia_id, thoi_gian_muon, thoi_gian_tra, trang_thai) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mt.getMuonTraId());
            ps.setString(2, mt.getDocGiaId());
            if (mt.getThoiGianMuon() == null) {
                ps.setNull(3, Types.TIMESTAMP);
            } else {
                ps.setTimestamp(3, new Timestamp(mt.getThoiGianMuon().getTime()));
            }
            if (mt.getThoiGianTra() == null) {
                ps.setNull(4, Types.TIMESTAMP);
            } else {
                ps.setTimestamp(4, new Timestamp(mt.getThoiGianTra().getTime()));
            }
            ps.setString(5, mt.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm độc giả theo đúng cả họ tên, số điện thoại và địa chỉ.
    public String timDocGiaId(String hoTen, String sdt, String diaChi)
            throws SQLException {
        String sql = "SELECT TOP 1 docgia_id FROM DOCGIA "
                + "WHERE LTRIM(RTRIM(docgia_name)) = ? "
                + "AND ISNULL(LTRIM(RTRIM(sdt)), '') = ? "
                + "AND ISNULL(LTRIM(RTRIM(diachi)), '') = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoTen.trim());
            ps.setString(2, sdt.trim());
            ps.setString(3, diaChi.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("docgia_id");
                }
            }
        }
        return null;
    }

    // Sinh mã mới dựa trên mã lớn nhất đang có trong database.
    // Cách này tránh lỗi khi SQL SEQUENCE bị lệch so với dữ liệu đã tồn tại
    // (ví dụ sequence trả về MT021 trong khi MT021 đã có trong MUONTRA).
    public String taoMaMoi(String table, String column, String prefix)
            throws SQLException {
        String tableName;
        String columnName;

        if ("MUONTRA".equalsIgnoreCase(table)
                && "muontra_id".equalsIgnoreCase(column)
                && "MT".equalsIgnoreCase(prefix)) {
            tableName = "MUONTRA";
            columnName = "muontra_id";
        } else if ("CT_MUONTRA".equalsIgnoreCase(table)
                && "ctmt_id".equalsIgnoreCase(column)
                && "CTMT".equalsIgnoreCase(prefix)) {
            tableName = "CT_MUONTRA";
            columnName = "ctmt_id";
        } else {
            throw new SQLException("Không hỗ trợ sinh mã cho bảng/cột: "
                    + table + "." + column);
        }

        String sql = "SELECT ISNULL(MAX(TRY_CONVERT(INT, "
                + "SUBSTRING(" + columnName + ", ?, 20))), 0) + 1 AS ma_moi "
                + "FROM " + tableName + " "
                + "WHERE " + columnName + " LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prefix.length() + 1);
            ps.setString(2, prefix + "%");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int soThuTu = rs.getInt("ma_moi");
                    return prefix + String.format("%03d", soThuTu);
                }
            }
        }

        throw new SQLException("Không sinh được mã mới cho "
                + tableName + "." + columnName);
    }

    // Lập phiếu, lưu chi tiết mượn và giảm tồn kho trong cùng một transaction.
    public void muonSach(PhieuMuon phieu, CTMuonTra chiTiet)
            throws SQLException {
        String sqlTruKho = "UPDATE SACH SET so_luong = so_luong - ? "
                + "WHERE sach_id = ? AND so_luong >= ?";
        String sqlPhieu = "INSERT INTO MUONTRA "
                + "(muontra_id, docgia_id, thoi_gian_muon, thoi_gian_tra, trang_thai) "
                + "VALUES (?, ?, ?, ?, ?)";
        String sqlChiTiet = "INSERT INTO CT_MUONTRA "
                + "(ctmt_id, muontra_id, sach_id, so_luong) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = conn.prepareStatement(sqlTruKho)) {
                    ps.setInt(1, chiTiet.getSoLuong());
                    ps.setString(2, chiTiet.getSachId());
                    ps.setInt(3, chiTiet.getSoLuong());
                    int updated = ps.executeUpdate();
                    if (updated != 1) {
                        throw new SQLException(
                                "Sách không tồn tại hoặc số lượng trong kho không đủ.");
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement(sqlPhieu)) {
                    ps.setString(1, phieu.getMuontraId());
                    ps.setString(2, phieu.getDocgiaId());
                    ps.setTimestamp(3,
                            new Timestamp(phieu.getThoiGianMuon().getTime()));
                    if (phieu.getThoiGianTra() == null) {
                        ps.setNull(4, Types.TIMESTAMP);
                    } else {
                        // Theo thống nhất hiện tại: thoi_gian_tra lưu hạn trả.
                        ps.setTimestamp(4,
                                new Timestamp(phieu.getThoiGianTra().getTime()));
                    }
                    ps.setString(5, phieu.getTrangThai());
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = conn.prepareStatement(sqlChiTiet)) {
                    ps.setString(1, chiTiet.getCtmtId());
                    ps.setString(2, chiTiet.getMuonTraId());
                    ps.setString(3, chiTiet.getSachId());
                    ps.setInt(4, chiTiet.getSoLuong());
                    ps.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
                throw e;
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ignored) {
                    // Connection sẽ được đóng khi ra khỏi try-with-resources.
                }
            }
        }
    }

    // Dùng khi trả sách thực tế; lúc này thoi_gian_tra được cập nhật thành ngày trả.
    public boolean traSach(String muonTraId, java.util.Date ngayTra) {
        String sql = "UPDATE MUONTRA SET thoi_gian_tra = ?, trang_thai = ? "
                + "WHERE muontra_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (ngayTra == null) {
                ps.setNull(1, Types.TIMESTAMP);
            } else {
                ps.setTimestamp(1, new Timestamp(ngayTra.getTime()));
            }
            ps.setString(2, "Đã trả");
            ps.setString(3, muonTraId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String muonTraId) {
        String sql = "DELETE FROM MUONTRA WHERE muontra_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, muonTraId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
