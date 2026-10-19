package com.thuvien.controller;

import com.thuvien.dao.XoaSachDAO;
import com.thuvien.model.XoaSachItem;

import java.sql.SQLException;
import java.util.List;

public class XoaSachController {

    private final XoaSachDAO xoaSachDAO;

    public XoaSachController() {
        xoaSachDAO = new XoaSachDAO();
    }

    public List<XoaSachItem> timSachCanXoa(String tuKhoa)
            throws SQLException {
        return xoaSachDAO.timSachCanXoa(tuKhoa);
    }

    public void xoaSach(List<String> danhSachMaSach)
            throws SQLException {
        xoaSachDAO.xoaSach(danhSachMaSach);
    }
}