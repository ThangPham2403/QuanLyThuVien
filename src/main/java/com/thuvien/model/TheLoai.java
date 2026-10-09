package com.thuvien.model;

public class TheLoai {
    private String theLoaiId;
    private String theLoaiName;
    private String info;

    public TheLoai() {}

    public TheLoai(String theLoaiId, String theLoaiName, String info) {
        this.theLoaiId = theLoaiId;
        this.theLoaiName = theLoaiName;
        this.info = info;
    }

    public String getTheLoaiId() { return theLoaiId; }
    public void setTheLoaiId(String theLoaiId) { this.theLoaiId = theLoaiId; }

    public String getTheLoaiName() { return theLoaiName; }
    public void setTheLoaiName(String theLoaiName) { this.theLoaiName = theLoaiName; }

    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

    @Override
    public String toString() {
        return theLoaiName; // Hiển thị tên khi đưa vào JComboBox
    }
}