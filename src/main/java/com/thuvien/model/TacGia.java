package com.thuvien.model;

public class TacGia {
    private String tacGiaId;
    private String tacGiaName;
    private String info;

    public TacGia() {}

    public TacGia(String tacGiaId, String tacGiaName, String info) {
        this.tacGiaId = tacGiaId;
        this.tacGiaName = tacGiaName;
        this.info = info;
    }

    public String getTacGiaId() { return tacGiaId; }
    public void setTacGiaId(String tacGiaId) { this.tacGiaId = tacGiaId; }

    public String getTacGiaName() { return tacGiaName; }
    public void setTacGiaName(String tacGiaName) { this.tacGiaName = tacGiaName; }

    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

    @Override
    public String toString() {
        return tacGiaName; // Hiển thị tên khi đưa vào JComboBox
    }
}