package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.SachHienThi;
import com.thuvien.model.DanhMucItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {

    //------------------------------------------------------------------
    //----- phần SachDao của TimSachPanel -----
    //--------------------------------------------------------------------
    
    // Lấy toàn bộ danh sách sách
    public List<SachHienThi> getAllSach() throws SQLException {

        return timKiemSach("");
    }

    // Tìm theo mã sách, tên sách hoặc tên tác giả
    public List<SachHienThi> timKiemSach(
            String keyword) throws SQLException {

        List<SachHienThi> danhSach = new ArrayList<>();

        String sql =
            "SELECT s.sach_id, s.sach_name, " +
            "       tg.tacgia_name, " +
            "       tl.theloai_name, " +
            "       nxb.nxb_name, " +
            "       s.so_luong " +
            "FROM SACH s " +
            "LEFT JOIN TACGIA tg " +
            "       ON s.tacgia_id = tg.tacgia_id " +
            "LEFT JOIN THELOAI tl " +
            "       ON s.theloai_id = tl.theloai_id " +
            "LEFT JOIN NXB nxb " +
            "       ON s.nxb_id = nxb.nxb_id " +
            "WHERE s.sach_id LIKE ? " +
            "   OR s.sach_name LIKE ? " +
            "   OR tg.tacgia_name LIKE ? " +
            "ORDER BY s.sach_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String tuKhoa = "%" + keyword.trim() + "%";

            ps.setString(1, tuKhoa);
            ps.setString(2, tuKhoa);
            ps.setString(3, tuKhoa);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    String maSach = rs.getString("sach_id");
                    String tenSach = rs.getString("sach_name");
                    String tenTacGia = rs.getString("tacgia_name");
                    String tenTheLoai = rs.getString("theloai_name");
                    String tenNXB = rs.getString("nxb_name");
                    int soLuong = rs.getInt("so_luong");

                    SachHienThi sach = new SachHienThi(
                        maSach,
                        tenSach,
                        tenTacGia,
                        tenTheLoai,
                        tenNXB,
                        soLuong
                    );

                    danhSach.add(sach);
                }
            }
        }

        return danhSach;
    }
    
    
    
    
    //--------------------------------------------------------------
    //----- Phần SachDao của ThemSachPanel -----
    //-----------------------------------------------------------------------
    
    // Tìm tác giả theo tên; nếu chưa có thì tự tạo mới.
    private String timHoacTaoTacGia(
            Connection conn, String ten) throws SQLException {

        String sql = "SELECT tacgia_id FROM TACGIA "
                + "WHERE tacgia_name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ten);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tacgia_id");
                }
            }
        }

        String id;
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT NEXT VALUE FOR dbo.SeqTacGia")) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                id = "TG" + String.format("%03d", rs.getInt(1));
            }
        }

        String insert = "INSERT INTO TACGIA "
                + "(tacgia_id, tacgia_name, info) VALUES (?, ?, NULL)";

        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, id);
            ps.setString(2, ten);
            ps.executeUpdate();
        }

        return id;
    }

    // Tìm thể loại theo tên; nếu chưa có thì tự tạo mới.
    private String timHoacTaoTheLoai(
            Connection conn, String ten) throws SQLException {

        String sql = "SELECT theloai_id FROM THELOAI "
                + "WHERE theloai_name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ten);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("theloai_id");
                }
            }
        }

        String id;
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT NEXT VALUE FOR dbo.SeqTheLoai")) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                id = "TL" + String.format("%03d", rs.getInt(1));
            }
        }

        String insert = "INSERT INTO THELOAI "
                + "(theloai_id, theloai_name, info) VALUES (?, ?, NULL)";

        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, id);
            ps.setString(2, ten);
            ps.executeUpdate();
        }

        return id;
    }

    // Tìm nhà xuất bản theo tên; nếu chưa có thì tự tạo mới.
    private String timHoacTaoNXB(
            Connection conn, String ten) throws SQLException {

        String sql = "SELECT nxb_id FROM NXB WHERE nxb_name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ten);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nxb_id");
                }
            }
        }

        String id;
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT NEXT VALUE FOR dbo.SeqNXB")) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                id = "NXB" + String.format("%03d", rs.getInt(1));
            }
        }

        String insert = "INSERT INTO NXB "
                + "(nxb_id, nxb_name, info) VALUES (?, ?, NULL)";

        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, id);
            ps.setString(2, ten);
            ps.executeUpdate();
        }

        return id;
    }

    // Phương thức Thêm sách mới.
    public void themSach(
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

                    String sql = "INSERT INTO SACH "
                            + "(sach_id, sach_name, info_sach, img_sach, "
                            + "tacgia_id, theloai_id, nxb_id, so_luong) "
                            + "VALUES (?, ?, NULL, NULL, ?, ?, ?, ?)";

                    try (PreparedStatement ps =
                                 conn.prepareStatement(sql)) {

                        ps.setString(1, maSach);
                        ps.setString(2, tenSach);
                        ps.setString(3, tacGiaId);
                        ps.setString(4, theLoaiId);
                        ps.setString(5, nxbId);
                        ps.setInt(6, soLuong);

                        ps.executeUpdate();
                    }

                    conn.commit();

                } catch (SQLException ex) {
                    conn.rollback();
                    throw ex;
                }

            } finally {
                conn.setAutoCommit(autoCommitCu);
            }
        }
    }
    
    
}