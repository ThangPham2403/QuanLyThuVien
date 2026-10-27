package com.thuvien.controller;

import com.thuvien.dao.ThongTinTheDAO;
import com.thuvien.model.ThongTinTheModel;
import com.thuvien.utils.PhienDangNhap;
import java.sql.SQLException;

/** Controller kết nối View với DAO và cập nhật thông tin phiên đăng nhập. */
public class ThongTinTheController {
    private final ThongTinTheDAO dao;

    public ThongTinTheController() {
        this.dao = new ThongTinTheDAO();
    }

    public ThongTinTheController(ThongTinTheDAO dao) {
        this.dao = dao;
    }

    public ThongTinTheModel layThongTinThe(String taiKhoanId) throws SQLException {
        if (taiKhoanId == null || taiKhoanId.trim().isEmpty()) {
            return null;
        }
        return dao.layTheoTaiKhoan(taiKhoanId.trim());
    }

    public boolean capNhatThongTinThe(String docGiaId, String taiKhoanId,
            String hoTen, String sdt) throws SQLException {
        if (docGiaId == null || docGiaId.trim().isEmpty()
                || taiKhoanId == null || taiKhoanId.trim().isEmpty()) {
            return false;
        }
        String tenDaChuanHoa = hoTen == null ? "" : hoTen.trim();
        String sdtDaChuanHoa = sdt == null ? "" : sdt.trim();
        if (tenDaChuanHoa.isEmpty()) {
            return false;
        }

        boolean thanhCong = dao.capNhat(docGiaId, taiKhoanId,
                tenDaChuanHoa, sdtDaChuanHoa);
        if (thanhCong) {
            // Giữ đồng bộ session như hành vi của phiên bản trước.
            PhienDangNhap.tenDocGia = tenDaChuanHoa;
            PhienDangNhap.sdtDocGia = sdtDaChuanHoa.isEmpty() ? null : sdtDaChuanHoa;
            PhienDangNhap.maDocGia = docGiaId;
        }
        return thanhCong;
    }
}
