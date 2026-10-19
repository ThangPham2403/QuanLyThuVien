package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.CTMuonTra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CTMuonTraDAO {

    // 1. Lấy tất cả danh sách chi tiết mượn trả (Có JOIN với bảng Sach để lấy tên sách và tác giả)
    public List<CTMuonTra> selectAll() {
        List<CTMuonTra> list = new ArrayList<>();
        // Câu lệnh SQL JOIN giữa ChiTietMuonTra và Sach
        String sql = "SELECT ct.ctmtId, ct.muonTraId, ct.sachId, ct.soLuong, s.tenSach, s.tacGia " +
                     "FROM ChiTietMuonTra ct JOIN Sach s ON ct.sachId = s.sachId";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                CTMuonTra ct = new CTMuonTra();
                ct.setCtmtId(rs.getString("ctmtId"));
                ct.setMuonTraId(rs.getString("muonTraId"));
                ct.setSachId(rs.getString("sachId"));
                ct.setSoLuong(rs.getInt("soLuong"));
                // Lấy thêm tên sách và tác giả từ kết quả JOIN
                ct.setTenSach(rs.getString("tenSach"));
                ct.setTacGia(rs.getString("tacGia"));
                
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}