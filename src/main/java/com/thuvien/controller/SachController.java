package com.thuvien.controller;

import com.thuvien.dao.SachDAO;
import com.thuvien.model.SachHienThi;
import com.thuvien.model.DanhMucItem;

import java.sql.SQLException;
import java.util.List;

public class SachController {
    
    
    //-----------------------------------------------------------
    // ------ Phần SachController của TimSachPanel ------
    //--------------------------------------------------------------
    private final SachDAO sachDAO;

    public SachController() {
        this.sachDAO = new SachDAO();
    }

    // Tải tất cả sách
    public List<SachHienThi> getAllSach()
            throws SQLException {

        return sachDAO.getAllSach();
    }

    // Tìm sách theo từ khóa
    public List<SachHienThi> timKiemSach(String keyword)
            throws SQLException {

        if (keyword == null) {
            keyword = "";
        }

        return sachDAO.timKiemSach(keyword.trim());
    }
    
    
    
    //---------------------------------------------------------------------
    // ------ Phần SachController của ThemSAchPanel ------
    //------------------------------------------------------------------------

            public void themSach(
            String maSach,
            String tenSach,
            String tenTacGia,
            String tenTheLoai,
            String tenNXB,
            int soLuong) throws SQLException {

        sachDAO.themSach(
                maSach,
                tenSach,
                tenTacGia,
                tenTheLoai,
                tenNXB,
                soLuong
        );
    }
    
    
}