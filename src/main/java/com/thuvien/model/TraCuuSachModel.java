package com.thuvien.model;

/** Dữ liệu một đầu sách hiển thị trong màn hình Tra cứu sách. */
public class TraCuuSachModel {
    private final String maSach;
    private final String tenSach;
    private final String tacGia;
    private final String theLoai;
    private final String nhaXuatBan;
    private final int tongSoLuong;
    private final int dangMuon;
    private final int soLuongCon;
    private final String trangThai;

    public TraCuuSachModel(String maSach, String tenSach, String tacGia,
            String theLoai, String nhaXuatBan, int tongSoLuong, int dangMuon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.nhaXuatBan = nhaXuatBan;
        this.tongSoLuong = tongSoLuong;
        this.dangMuon = dangMuon;
        this.soLuongCon = Math.max(0, tongSoLuong - dangMuon);
        this.trangThai = this.soLuongCon > 0 ? "Còn sách" : "Hết sách";
    }

    public String getMaSach() { return maSach; }
    public String getTenSach() { return tenSach; }
    public String getTacGia() { return tacGia; }
    public String getTheLoai() { return theLoai; }
    public String getNhaXuatBan() { return nhaXuatBan; }
    public int getTongSoLuong() { return tongSoLuong; }
    public int getDangMuon() { return dangMuon; }
    public int getSoLuongCon() { return soLuongCon; }
    public String getTrangThai() { return trangThai; }
    public String getSoLuongHienThi() { return soLuongCon + "/" + tongSoLuong; }
}
