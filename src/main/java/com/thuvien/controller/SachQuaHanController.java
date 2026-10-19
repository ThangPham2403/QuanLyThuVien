
package com.thuvien.controller;

import com.thuvien.config.DBConnection;
import com.thuvien.model.SachQuaHanModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SachQuaHanController {

    public List<SachQuaHanModel> layDanhSachSachQuaHan()
            throws Exception {

        List<SachQuaHanModel> danhSach = new ArrayList<>();

        String sql =
            "SELECT mt.muontra_id, dg.sdt, " +
            "mt.thoi_gian_tra, mt.trang_thai " +
            "FROM MUONTRA mt " +
            "JOIN DOCGIA dg ON mt.docgia_id = dg.docgia_id " +
            "WHERE mt.trang_thai = N'Quá hạn'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                SachQuaHanModel sach = new SachQuaHanModel(
                    rs.getString("muontra_id"),
                    rs.getString("sdt"),
                    rs.getString("thoi_gian_tra"),
                    rs.getString("trang_thai")
                );

                danhSach.add(sach);
            }
        }

        return danhSach;
    }
}
