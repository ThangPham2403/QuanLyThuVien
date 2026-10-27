


package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.CTMuonTra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CTMuonTraDAO {
    public List<CTMuonTra> selectAll() {
        List<CTMuonTra> list = new ArrayList<>();
        String sql = "SELECT ct.ctmt_id, ct.muontra_id, ct.sach_id, ct.so_luong, s.sach_name, tg.tacgia_name "
                   + "FROM CT_MUONTRA ct JOIN SACH s ON ct.sach_id = s.sach_id "
                   + "LEFT JOIN TACGIA tg ON s.tacgia_id = tg.tacgia_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CTMuonTra ct = new CTMuonTra();
                ct.setCtmtId(rs.getString("ctmt_id"));
                ct.setMuonTraId(rs.getString("muontra_id"));
                ct.setSachId(rs.getString("sach_id"));
                ct.setSoLuong(rs.getInt("so_luong"));
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(CTMuonTra ct) {
        String sql = "INSERT INTO CT_MUONTRA (ctmt_id, muontra_id, sach_id, so_luong) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ct.getCtmtId());
            ps.setString(2, ct.getMuonTraId());
            ps.setString(3, ct.getSachId());
            ps.setInt(4, ct.getSoLuong());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
