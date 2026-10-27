package com.thuvien.dao;

import com.thuvien.config.DBConnection;
import com.thuvien.model.SachDangKyMuon;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DangKyMuonDAO {
    public List<SachDangKyMuon> laySachCoTheMuon(String tuKhoa) throws SQLException {
        List<SachDangKyMuon> list = new ArrayList<>();
        String sql = "SELECT s.sach_id, s.sach_name, ISNULL(tg.tacgia_name,N'') tacgia_name, s.so_luong, "
            + "(s.so_luong - ISNULL((SELECT SUM(ct.so_luong) FROM CT_MUONTRA ct "
            + "JOIN MUONTRA mt ON mt.muontra_id=ct.muontra_id "
            + "WHERE ct.sach_id=s.sach_id AND mt.trang_thai IN (N'Đang mượn',N'Quá hạn')),0)) con_lai "
            + "FROM SACH s LEFT JOIN TACGIA tg ON tg.tacgia_id=s.tacgia_id "
            + "WHERE (?='' OR s.sach_id LIKE ? OR s.sach_name LIKE ? OR tg.tacgia_name LIKE ?) "
            + "ORDER BY s.sach_name";
        try (Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)) {
            String k=tuKhoa==null ? "" : tuKhoa.trim();
            ps.setString(1,k); ps.setString(2,"%"+k+"%"); ps.setString(3,"%"+k+"%"); ps.setString(4,"%"+k+"%");
            try(ResultSet rs=ps.executeQuery()) {
                while(rs.next()) list.add(new SachDangKyMuon(rs.getString("sach_id"),rs.getString("sach_name"),
                    rs.getString("tacgia_name"),rs.getInt("so_luong"),Math.max(0,rs.getInt("con_lai"))));
            }
        }
        return list;
    }

    public void dangKyMuon(String docGiaId, Map<String,Integer> gioHang, LocalDate ngayMuon, LocalDate hanTra) throws SQLException {
        if (docGiaId==null || docGiaId.trim().isEmpty()) throw new SQLException("Không xác định được mã độc giả. Vui lòng đăng xuất và đăng nhập lại.");
        if (gioHang==null || gioHang.isEmpty()) throw new SQLException("Vui lòng thêm ít nhất một đầu sách vào phiếu mượn.");
        if (ngayMuon==null || hanTra==null || hanTra.isBefore(ngayMuon)) throw new SQLException("Ngày trả phải bằng hoặc sau ngày mượn.");
        try(Connection c=DBConnection.getConnection()) {
            c.setAutoCommit(false);
            try {
                // Kiểm tra tồn tại độc giả.
                try(PreparedStatement p=c.prepareStatement("SELECT 1 FROM DOCGIA WHERE docgia_id=?")) {
                    p.setString(1,docGiaId); try(ResultSet r=p.executeQuery()) { if(!r.next()) throw new SQLException("Mã độc giả không tồn tại."); }
                }
                // Khóa các dòng sách để tránh hai độc giả cùng đăng ký vượt tồn.
                for(Map.Entry<String,Integer> e:gioHang.entrySet()) {
                    if(e.getValue()==null || e.getValue()<1) throw new SQLException("Số lượng mượn không hợp lệ.");
                    String q="SELECT s.so_luong - ISNULL((SELECT SUM(ct.so_luong) FROM CT_MUONTRA ct "
                        +"JOIN MUONTRA mt ON mt.muontra_id=ct.muontra_id WHERE ct.sach_id=s.sach_id "
                        +"AND mt.trang_thai IN (N'Đang mượn',N'Quá hạn')),0) con_lai "
                        +"FROM SACH s WITH (UPDLOCK,HOLDLOCK) WHERE s.sach_id=?";
                    try(PreparedStatement p=c.prepareStatement(q)) {
                        p.setString(1,e.getKey()); try(ResultSet r=p.executeQuery()) {
                            if(!r.next()) throw new SQLException("Không tìm thấy sách mã "+e.getKey());
                            if(r.getInt(1)<e.getValue()) throw new SQLException("Sách "+e.getKey()+" không đủ số lượng còn lại.");
                        }
                    }
                }
//                String mtId=taoMa(c,"MUONTRA","muontra_id","MT");
//                String insertMt="INSERT INTO MUONTRA(muontra_id,docgia_id,thoi_gian_muon,thoi_gian_tra,trang_thai) VALUES(?,?,?,?,N'Đang mượn')";
//                try(PreparedStatement p=c.prepareStatement(insertMt)) {
//                    p.setString(1,mtId); p.setString(2,docGiaId);
//                    p.setTimestamp(3,Timestamp.valueOf(ngayMuon.atStartOfDay()));
//                    // Trong schema hiện tại thoi_gian_tra được dùng làm hạn trả khi phiếu chưa trả.
//                    p.setTimestamp(4,Timestamp.valueOf(hanTra.atTime(23,59,59))); p.executeUpdate();
//                }
                    String mtId=taoMa(c,"MUONTRA","muontra_id","MT");
                    String trangThai = hanTra.isBefore(LocalDate.now())
                ? "Quá hạn"
                : "Đang mượn";

                String insertMt =
                    "INSERT INTO MUONTRA "
                  + "(muontra_id, docgia_id, thoi_gian_muon, thoi_gian_tra, trang_thai) "
                  + "VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement p = c.prepareStatement(insertMt)) {
                    p.setString(1, mtId);
                    p.setString(2, docGiaId);
                    p.setTimestamp(3,
                        Timestamp.valueOf(ngayMuon.atStartOfDay()));
                    p.setTimestamp(4,
                        Timestamp.valueOf(hanTra.atTime(23, 59, 59)));
                    p.setString(5, trangThai);

                    p.executeUpdate();
                }

                
                
                int i=1;
                for(Map.Entry<String,Integer> e:gioHang.entrySet()) {
                    String ctId=taoMa(c,"CT_MUONTRA","ctmt_id","CTMT");
                    try(PreparedStatement p=c.prepareStatement("INSERT INTO CT_MUONTRA(ctmt_id,muontra_id,sach_id,so_luong) VALUES(?,?,?,?)")) {
                        p.setString(1,ctId); p.setString(2,mtId); p.setString(3,e.getKey()); p.setInt(4,e.getValue()); p.executeUpdate();
                    }
                }
                c.commit();
            } catch(Exception ex) {
                c.rollback();
                if(ex instanceof SQLException) throw (SQLException)ex;
                throw new SQLException("Không thể đăng ký mượn.",ex);
            } finally { c.setAutoCommit(true); }
        }
    }

    private String taoMa(Connection c, String table, String column, String prefix) throws SQLException {
        // Các bảng hiện dùng mã dạng MT001/CTMT001, không có IDENTITY.
        String sql="SELECT MAX(TRY_CONVERT(INT,SUBSTRING("+column+", "+(prefix.length()+1)+", 20))) FROM "+table;
        int max=0;
        try(Statement st=c.createStatement(); ResultSet rs=st.executeQuery(sql)) { if(rs.next()) max=rs.getInt(1); }
        return prefix+String.format("%03d",max+1);
    }
}
