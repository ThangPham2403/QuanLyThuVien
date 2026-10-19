package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.SuaSachItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuaSachDAO {

    // Lấy danh sách sách, có thể lọc theo mã, tên hoặc tác giả
    public List<SuaSachItem> getDanhSachSach(String tuKhoa)
            throws SQLException {

        List<SuaSachItem> danhSach = new ArrayList<>();

        String sql =
            "SELECT s.sach_id, s.sach_name, " +
            "tg.tacgia_name, tl.theloai_name, " +
            "nxb.nxb_name, s.so_luong " +
            "FROM SACH s " +
            "LEFT JOIN TACGIA tg ON s.tacgia_id = tg.tacgia_id " +
            "LEFT JOIN THELOAI tl ON s.theloai_id = tl.theloai_id " +
            "LEFT JOIN NXB nxb ON s.nxb_id = nxb.nxb_id " +
            "WHERE s.sach_id LIKE ? " +
            "OR s.sach_name LIKE ? " +
            "OR tg.tacgia_name LIKE ? " +
            "ORDER BY s.sach_id";

        String keyword = "%" +
                (tuKhoa == null ? "" : tuKhoa.trim()) +
                "%";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 1; i <= 3; i++) {
                ps.setString(i, keyword);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(new SuaSachItem(
                        rs.getString("sach_id"),
                        rs.getString("sach_name"),
                        rs.getString("tacgia_name"),
                        rs.getString("theloai_name"),
                        rs.getString("nxb_name"),
                        rs.getInt("so_luong")
                    ));
                }
            }
        }

        return danhSach;
    }

    // Tìm một cuốn sách theo mã chính xác
    public SuaSachItem getSachTheoMa(String maSach)
            throws SQLException {

        String sql =
            "SELECT s.sach_id, s.sach_name, " +
            "tg.tacgia_name, tl.theloai_name, " +
            "nxb.nxb_name, s.so_luong " +
            "FROM SACH s " +
            "LEFT JOIN TACGIA tg ON s.tacgia_id = tg.tacgia_id " +
            "LEFT JOIN THELOAI tl ON s.theloai_id = tl.theloai_id " +
            "LEFT JOIN NXB nxb ON s.nxb_id = nxb.nxb_id " +
            "WHERE s.sach_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSach);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new SuaSachItem(
                        rs.getString("sach_id"),
                        rs.getString("sach_name"),
                        rs.getString("tacgia_name"),
                        rs.getString("theloai_name"),
                        rs.getString("nxb_name"),
                        rs.getInt("so_luong")
                    );
                }
            }
        }

        return null;
    }

    // Cập nhật thông tin sách
    public void capNhatSach(
            String maSach,
            String tenSach,
            String tenTacGia,
            String tenTheLoai,
            String tenNXB,
            int soLuong) throws SQLException {

        try (Connection conn = DBConnection.getConnection()) {
            boolean autoCommitCu = conn.getAutoCommit();

            try {
                conn.setAutoCommit(false);

                try {
                    String tacGiaId =
                        timHoacTaoTacGia(conn, tenTacGia);

                    String theLoaiId =
                        timHoacTaoTheLoai(conn, tenTheLoai);

                    String nxbId =
                        timHoacTaoNXB(conn, tenNXB);

                    String sql =
                        "UPDATE SACH SET " +
                        "sach_name = ?, " +
                        "tacgia_id = ?, " +
                        "theloai_id = ?, " +
                        "nxb_id = ?, " +
                        "so_luong = ? " +
                        "WHERE sach_id = ?";

                    try (PreparedStatement ps =
                            conn.prepareStatement(sql)) {

                        ps.setString(1, tenSach);
                        ps.setString(2, tacGiaId);
                        ps.setString(3, theLoaiId);
                        ps.setString(4, nxbId);
                        ps.setInt(5, soLuong);
                        ps.setString(6, maSach);

                        int soDong = ps.executeUpdate();

                        if (soDong == 0) {
                            throw new SQLException(
                                "Không tìm thấy sách có mã: " + maSach
                            );
                        }
                    }

                    conn.commit();

                } catch (SQLException | RuntimeException ex) {
                    conn.rollback();
                    throw ex;
                }

            } finally {
                conn.setAutoCommit(autoCommitCu);
            }
        }
    }

    // ===== Tìm hoặc tạo tác giả =====
    private String timHoacTaoTacGia(
            Connection conn, String ten) throws SQLException {

        String sqlTim =
            "SELECT tacgia_id FROM TACGIA WHERE tacgia_name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlTim)) {
            ps.setString(1, ten);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tacgia_id");
                }
            }
        }

        int so = laySoTiepTheo(conn, "SeqTacGia");
        String id = String.format("TG%03d", so);

        String sqlThem =
            "INSERT INTO TACGIA (tacgia_id, tacgia_name, info) " +
            "VALUES (?, ?, NULL)";

        try (PreparedStatement ps = conn.prepareStatement(sqlThem)) {
            ps.setString(1, id);
            ps.setString(2, ten);
            ps.executeUpdate();
        }

        return id;
    }

    // ===== Tìm hoặc tạo thể loại =====
    private String timHoacTaoTheLoai(
            Connection conn, String ten) throws SQLException {

        String sqlTim =
            "SELECT theloai_id FROM THELOAI WHERE theloai_name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlTim)) {
            ps.setString(1, ten);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("theloai_id");
                }
            }
        }

        int so = laySoTiepTheo(conn, "SeqTheLoai");
        String id = String.format("TL%03d", so);

        String sqlThem =
            "INSERT INTO THELOAI (theloai_id, theloai_name, info) " +
            "VALUES (?, ?, NULL)";

        try (PreparedStatement ps = conn.prepareStatement(sqlThem)) {
            ps.setString(1, id);
            ps.setString(2, ten);
            ps.executeUpdate();
        }

        return id;
    }

    // ===== Tìm hoặc tạo nhà xuất bản =====
    private String timHoacTaoNXB(
            Connection conn, String ten) throws SQLException {

        String sqlTim =
            "SELECT nxb_id FROM NXB WHERE nxb_name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlTim)) {
            ps.setString(1, ten);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nxb_id");
                }
            }
        }

        int so = laySoTiepTheo(conn, "SeqNXB");
        String id = String.format("NXB%03d", so);

        String sqlThem =
            "INSERT INTO NXB (nxb_id, nxb_name, info) " +
            "VALUES (?, ?, NULL)";

        try (PreparedStatement ps = conn.prepareStatement(sqlThem)) {
            ps.setString(1, id);
            ps.setString(2, ten);
            ps.executeUpdate();
        }

        return id;
    }

    // Đọc giá trị kế tiếp từ sequence đã tạo trong SQL Server
    private int laySoTiepTheo(Connection conn, String tenSequence)
            throws SQLException {

        // Chỉ chấp nhận các sequence cố định được định nghĩa ở đây.
        String sequence;

        switch (tenSequence) {
            case "SeqTacGia":
                sequence = "dbo.SeqTacGia";
                break;
            case "SeqTheLoai":
                sequence = "dbo.SeqTheLoai";
                break;
            case "SeqNXB":
                sequence = "dbo.SeqNXB";
                break;
            default:
                throw new SQLException("Sequence không hợp lệ.");
        }

        String sql = "SELECT NEXT VALUE FOR " + sequence;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        throw new SQLException("Không lấy được số tiếp theo từ sequence.");
    }
}