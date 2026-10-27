package com.thuvien.model;

import java.sql.Timestamp;

/** Một phiếu mượn của độc giả đang đăng nhập, dùng cho màn hình Sách đang mượn. */
public class SachDangMuonCaNhanModel {
    private final String maMuon;
    private final String tenSach;
    private final int soLuong;
    private final Timestamp ngayMuon;
    private final Timestamp hanTra;
    private final String trangThai;

    public SachDangMuonCaNhanModel(String maMuon, String tenSach, int soLuong,
            Timestamp ngayMuon, Timestamp hanTra, String trangThai) {
        this.maMuon = maMuon;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
    }

    public String getMaMuon() { return maMuon; }
    public String getTenSach() { return tenSach; }
    public int getSoLuong() { return soLuong; }
    public Timestamp getNgayMuon() { return ngayMuon; }
    public Timestamp getHanTra() { return hanTra; }
    public String getTrangThai() { return trangThai; }
}
