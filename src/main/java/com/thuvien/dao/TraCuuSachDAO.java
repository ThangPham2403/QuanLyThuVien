package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.TraCuuSachModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** DAO truy vấn dữ liệu đầu sách từ SQL Server. */
public class TraCuuSachDAO {

    public List<TraCuuSachModel> timKiemSach(String keyword, String theLoai)
            throws SQLException {
        List<TraCuuSachModel> danhSach = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT s.sach_id, s.sach_name, "
                + "ISNULL(tg.tacgia_name, N'Chưa có') AS tacgia, "
                + "ISNULL(tl.theloai_name, N'Chưa có') AS theloai, "
                + "ISNULL(n.nxb_name, N'Chưa có') AS nxb, "
                + "s.so_luong AS tong_sl, "
                + "ISNULL((SELECT SUM(ct.so_luong) "
                + "FROM CT_MUONTRA ct "
                + "JOIN MUONTRA mt ON ct.muontra_id = mt.muontra_id "
                + "WHERE ct.sach_id = s.sach_id "
                + "AND mt.trang_thai IN (N'Đang mượn', N'Quá hạn')), 0) AS dang_muon "
                + "FROM SACH s "
                + "LEFT JOIN TACGIA tg ON s.tacgia_id = tg.tacgia_id "
                + "LEFT JOIN THELOAI tl ON s.theloai_id = tl.theloai_id "
                + "LEFT JOIN NXB n ON s.nxb_id = n.nxb_id "
                + "WHERE 1=1 ");

        boolean coTuKhoa = keyword != null && !keyword.trim().isEmpty();
        boolean locTheLoai = theLoai != null && !theLoai.trim().isEmpty()
                && !"Tất cả thể loại".equals(theLoai);

        if (coTuKhoa) {
            sql.append("AND (s.sach_id LIKE ? OR s.sach_name LIKE ? OR tg.tacgia_name LIKE ?) ");
        }
        if (locTheLoai) {
            sql.append("AND tl.theloai_name = ? ");
        }
        sql.append("ORDER BY s.sach_id");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int index = 1;
            if (coTuKhoa) {
                String pattern = "%" + keyword.trim() + "%";
                ps.setString(index++, pattern);
                ps.setString(index++, pattern);
                ps.setString(index++, pattern);
            }
            if (locTheLoai) {
                ps.setString(index, theLoai);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(new TraCuuSachModel(
                            rs.getString("sach_id"),
                            rs.getString("sach_name"),
                            rs.getString("tacgia"),
                            rs.getString("theloai"),
                            rs.getString("nxb"),
                            rs.getInt("tong_sl"),
                            rs.getInt("dang_muon")));
                }
            }
        }
        return danhSach;
    }
}
