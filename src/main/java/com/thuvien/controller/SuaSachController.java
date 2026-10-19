package com.thuvien.controller;

import com.thuvien.dao.SuaSachDAO;
import com.thuvien.model.SuaSachItem;

import java.sql.SQLException;
import java.util.List;

public class SuaSachController {

    private final SuaSachDAO suaSachDAO;

    public SuaSachController() {
        suaSachDAO = new SuaSachDAO();
    }

    public List<SuaSachItem> getDanhSachSach(String tuKhoa)
            throws SQLException {
        return suaSachDAO.getDanhSachSach(tuKhoa);
    }

    public SuaSachItem getSachTheoMa(String maSach)
            throws SQLException {
        return suaSachDAO.getSachTheoMa(maSach);
    }

    public void capNhatSach(
            String maSach,
            String tenSach,
            String tenTacGia,
            String tenTheLoai,
            String tenNXB,
            int soLuong) throws SQLException {

        if (maSach == null || maSach.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Mã sách không được để trống."
            );
        }

        if (tenSach == null || tenSach.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Tên sách không được để trống."
            );
        }

        if (tenTacGia == null || tenTacGia.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Tên tác giả không được để trống."
            );
        }

        if (tenTheLoai == null || tenTheLoai.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Tên thể loại không được để trống."
            );
        }

        if (tenNXB == null || tenNXB.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Tên nhà xuất bản không được để trống."
            );
        }

        if (soLuong < 0) {
            throw new IllegalArgumentException(
                "Số lượng không được nhỏ hơn 0."
            );
        }

        suaSachDAO.capNhatSach(
            maSach.trim(),
            tenSach.trim(),
            tenTacGia.trim(),
            tenTheLoai.trim(),
            tenNXB.trim(),
            soLuong
        );
    }
}