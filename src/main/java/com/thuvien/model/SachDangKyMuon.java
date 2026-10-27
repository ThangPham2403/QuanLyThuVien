package com.thuvien.model;

public class SachDangKyMuon {
    private final String maSach, tenSach, tacGia;
    private final int tongSoLuong, conLai;

    public SachDangKyMuon(String maSach, String tenSach, String tacGia, int tongSoLuong, int conLai) {
        this.maSach = maSach; this.tenSach = tenSach; this.tacGia = tacGia;
        this.tongSoLuong = tongSoLuong; this.conLai = conLai;
    }
    public String getMaSach() { return maSach; }
    public String getTenSach() { return tenSach; }
    public String getTacGia() { return tacGia; }
    public int getTongSoLuong() { return tongSoLuong; }
    public int getConLai() { return conLai; }
}
