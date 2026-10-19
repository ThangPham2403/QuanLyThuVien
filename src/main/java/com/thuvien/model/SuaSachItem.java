package com.thuvien.model;

public class SuaSachItem {

    private String maSach;
    private String tenSach;
    private String tenTacGia;
    private String tenTheLoai;
    private String tenNXB;
    private int soLuong;

    public SuaSachItem(String maSach, String tenSach,
            String tenTacGia, String tenTheLoai,
            String tenNXB, int soLuong) {

        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.tenTheLoai = tenTheLoai;
        this.tenNXB = tenNXB;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public int getSoLuong() {
        return soLuong;
    }
}