package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.XoaSachItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XoaSachDAO {

    // Tìm sách theo mã, tên, tác giả, thể loại hoặc nhà xuất bản
    public List<XoaSachItem> timSachCanXoa(String tuKhoa)
            throws SQLException {

        List<XoaSachItem> danhSach = new ArrayList<>();

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
            "OR tl.theloai_name LIKE ? " +
            "OR nxb.nxb_name LIKE ? " +
            "ORDER BY s.sach_id";

        String keyword = "%" +
                (tuKhoa == null ? "" : tuKhoa.trim()) +
                "%";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 1; i <= 5; i++) {
                ps.setString(i, keyword);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    XoaSachItem sach = new XoaSachItem(
                        rs.getString("sach_id"),
                        rs.getString("sach_name"),
                        rs.getString("tacgia_name"),
                        rs.getString("theloai_name"),
                        rs.getString("nxb_name"),
                        rs.getInt("so_luong")
                    );

                    danhSach.add(sach);
                }
            }
        }

        return danhSach;
    }

    // Xóa nhiều sách theo mã sách trong cùng một giao dịch
    public void xoaSach(List<String> danhSachMaSach)
            throws SQLException {

        if (danhSachMaSach == null || danhSachMaSach.isEmpty()) {
            throw new IllegalArgumentException(
                "Bạn chưa chọn cuốn sách nào để xóa."
            );
        }

        String sql = "DELETE FROM SACH WHERE sach_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            boolean autoCommitCu = conn.getAutoCommit();

            try {
                conn.setAutoCommit(false);

                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    for (String maSach : danhSachMaSach) {
                        ps.setString(1, maSach);
                        ps.addBatch();
                    }

                    int[] ketQua = ps.executeBatch();

                    // Bảo đảm mọi mã được chọn đều tồn tại
                    for (int soDong : ketQua) {
                        if (soDong == 0) {
                            throw new SQLException(
                                "Có sách không còn tồn tại trong database."
                            );
                        }
                    }
                }

                conn.commit();

            } catch (SQLException | RuntimeException ex) {
                conn.rollback();
                throw ex;

            } finally {
                conn.setAutoCommit(autoCommitCu);
            }
        }
    }
}