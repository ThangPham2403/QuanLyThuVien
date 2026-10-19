package com.thuvien.dao;
import com.thuvien.config.DBConnection;
import com.thuvien.model.MuonTra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PhieuMuonDAO {

    // 1. Lấy toàn bộ danh sách phiếu mượn trả từ CSDL
    public List<MuonTra> selectAll() {
        List<MuonTra> list = new ArrayList<>();
        String sql = "SELECT * FROM MuonTra";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                MuonTra mt = new MuonTra();
                mt.setMuonTraId(rs.getString("muonTraId"));
                mt.setDocGiaId(rs.getString("docGiaId"));
                mt.setThoiGianMuon(rs.getTimestamp("thoiGianMuon"));
                mt.setThoiGianTra(rs.getTimestamp("thoiGianTra"));
                mt.setTrangThai(rs.getString("trangThai"));
                list.add(mt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm mới một phiếu mượn sách
    public boolean insert(MuonTra mt) {
        String sql = "INSERT INTO MuonTra (muonTraId, docGiaId, thoiGianMuon, thoiGianTra, trangThai) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, mt.getMuonTraId());
            ps.setString(2, mt.getDocGiaId());
            
            if (mt.getThoiGianMuon() != null) {
                ps.setTimestamp(3, new java.sql.Timestamp(mt.getThoiGianMuon().getTime()));
            } else {
                ps.setNull(3, Types.TIMESTAMP);
            }
            
            if (mt.getThoiGianTra() != null) {
                ps.setTimestamp(4, new java.sql.Timestamp(mt.getThoiGianTra().getTime()));
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }
            
            ps.setString(5, mt.getTrangThai());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Cập nhật ngày trả sách (Thực hiện trả sách)
    public boolean traSach(String muonTraId, java.util.Date ngayTra) {
        String sql = "UPDATE MuonTra SET thoiGianTra = ?, trangThai = ? WHERE muonTraId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setTimestamp(1, new java.sql.Timestamp(ngayTra.getTime()));
            ps.setString(2, "Đã trả");
            ps.setString(3, muonTraId);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Xóa phiếu mượn theo mã
    public boolean delete(String muonTraId) {
        String sql = "DELETE FROM MuonTra WHERE muonTraId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, muonTraId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}