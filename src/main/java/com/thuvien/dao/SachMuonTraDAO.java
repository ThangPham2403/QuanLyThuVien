package com.thuvien.dao;
import com.thuvien.config.DBConnection;
import com.thuvien.model.Sach;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SachMuonTraDAO {
    // 1. Lấy tất cả danh sách sách
    public List<Sach> selectAll() {
        List<Sach> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Sach s = new Sach(
                    rs.getString("sachId"),
                    rs.getString("sachName"),
                    rs.getString("infoSach"),
                    rs.getString("imgSach"),
                    rs.getString("tacGiaId"),
                    rs.getString("theLoaiId"),
                    rs.getString("nxbId"),
                    rs.getInt("soLuong")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // 2. Thêm sách mới
    public boolean insert(Sach s) {
        String sql = "INSERT INTO Sach (sachId, sachName, infoSach, imgSach, tacGiaId, theLoaiId, nxbId, soLuong) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, s.getSachId());
            ps.setString(2, s.getSachName());
            ps.setString(3, s.getInfoSach());
            ps.setString(4, s.getImgSach());
            ps.setString(5, s.getTacGiaId());
            ps.setString(6, s.getTheLoaiId());
            ps.setString(7, s.getNxbId());
            ps.setInt(8, s.getSoLuong());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 3. Cập nhật thông tin sách
    public boolean update(Sach s) {
        String sql = "UPDATE Sach SET sachName = ?, infoSach = ?, imgSach = ?, tacGiaId = ?, theLoaiId = ?, nxbId = ?, soLuong = ? WHERE sachId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, s.getSachName());
            ps.setString(2, s.getInfoSach());
            ps.setString(3, s.getImgSach());
            ps.setString(4, s.getTacGiaId());
            ps.setString(5, s.getTheLoaiId());
            ps.setString(6, s.getNxbId());
            ps.setInt(7, s.getSoLuong());
            ps.setString(8, s.getSachId());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 4. Xóa sách theo mã (sachId)
    public boolean delete(String sachId) {
        String sql = "DELETE FROM Sach WHERE sachId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, sachId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
