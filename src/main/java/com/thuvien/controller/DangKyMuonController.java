package com.thuvien.controller;

import com.thuvien.dao.DangKyMuonDAO;
import com.thuvien.model.SachDangKyMuon;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DangKyMuonController {
    private final DangKyMuonDAO dao = new DangKyMuonDAO();
    public List<SachDangKyMuon> laySachCoTheMuon(String tuKhoa) throws SQLException {
        return dao.laySachCoTheMuon(tuKhoa);
    }
    public void dangKyMuon(String docGiaId, Map<String,Integer> gioHang, LocalDate ngayMuon, LocalDate hanTra) throws SQLException {
        dao.dangKyMuon(docGiaId, gioHang, ngayMuon, hanTra);
    }
}
