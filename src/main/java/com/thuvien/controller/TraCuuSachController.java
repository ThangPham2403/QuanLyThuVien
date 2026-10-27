package com.thuvien.controller;

import com.thuvien.dao.TraCuuSachDAO;
import com.thuvien.model.TraCuuSachModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Điều phối tìm kiếm và lọc trạng thái đầu sách. */
public class TraCuuSachController {
    private final TraCuuSachDAO dao;

    public TraCuuSachController() {
        this.dao = new TraCuuSachDAO();
    }

    public List<TraCuuSachModel> timKiem(String keyword, String theLoai, String trangThai)
            throws SQLException {
        String tuKhoa = keyword == null ? "" : keyword.trim();
        if (tuKhoa.contains("Nhập mã sách")) {
            tuKhoa = "";
        }
        List<TraCuuSachModel> ketQua = dao.timKiemSach(tuKhoa, theLoai);
        if (trangThai == null || trangThai.isEmpty()
                || "Tất cả trạng thái".equals(trangThai)) {
            return ketQua;
        }
        List<TraCuuSachModel> daLoc = new ArrayList<>();
        for (TraCuuSachModel sach : ketQua) {
            if (trangThai.equals(sach.getTrangThai())) {
                daLoc.add(sach);
            }
        }
        return daLoc;
    }
}
