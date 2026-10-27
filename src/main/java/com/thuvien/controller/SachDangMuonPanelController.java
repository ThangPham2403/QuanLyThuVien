package com.thuvien.controller;

import com.thuvien.dao.SachDangMuonDAO;
import com.thuvien.model.SachDangMuonCaNhanModel;
import java.sql.SQLException;
import java.util.List;

/** Điều phối việc lấy dữ liệu cho SachDangMuonPanel. */
public class SachDangMuonPanelController {
    private final SachDangMuonDAO sachDangMuonDAO;

    public SachDangMuonPanelController() {
        this.sachDangMuonDAO = new SachDangMuonDAO();
    }

    public List<SachDangMuonCaNhanModel> layDanhSach(String maDocGia) throws SQLException {
        if (maDocGia == null || maDocGia.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Không xác định được tài khoản độc giả đang đăng nhập. Vui lòng đăng nhập lại.");
        }
        return sachDangMuonDAO.layTheoDocGia(maDocGia.trim());
    }
}
