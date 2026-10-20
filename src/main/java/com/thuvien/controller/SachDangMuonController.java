package com.thuvien.controller;

import com.thuvien.config.DBConnection;
import com.thuvien.model.SachDangMuonModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

public class SachDangMuonController {

    public List<SachDangMuonModel> laySachDangMuon()
            throws Exception {

        List<SachDangMuonModel> danhSach = new ArrayList<>();

  String sql =
    "SELECT s.sach_id, dg.sdt, mt.thoi_gian_tra, mt.trang_thai " +
    "FROM MUONTRA mt " +
    "JOIN DOCGIA dg ON mt.docgia_id = dg.docgia_id " +
    "JOIN CT_MUONTRA ct ON mt.muontra_id = ct.muontra_id " +
    "JOIN SACH s ON ct.sach_id = s.sach_id " +
    "WHERE mt.trang_thai = N'Đang mượn'";
  
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SachDangMuonModel sach = new SachDangMuonModel(
                    // Chưa lấy được ID sách
                    rs.getString("sach_id"),
                    rs.getString("sdt"),
                    rs.getString("thoi_gian_tra"),
                    rs.getString("trang_thai")
                );

                danhSach.add(sach);
            }
        }

        return danhSach;
    }

    public void laySachDangMuon(JTable tblDangMuon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}