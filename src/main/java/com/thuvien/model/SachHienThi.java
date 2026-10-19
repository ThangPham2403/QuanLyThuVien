package com.thuvien.model;

public class SachHienThi {

    private String sachId;
    private String sachName;
    private String tacGiaName;
    private String theLoaiName;
    private String nxbName;
    private int soLuong;

    public SachHienThi(
            String sachId,
            String sachName,
            String tacGiaName,
            String theLoaiName,
            String nxbName,
            int soLuong) {

        this.sachId = sachId;
        this.sachName = sachName;
        this.tacGiaName = tacGiaName;
        this.theLoaiName = theLoaiName;
        this.nxbName = nxbName;
        this.soLuong = soLuong;
    }

    public String getSachId() {
        return sachId;
    }

    public String getSachName() {
        return sachName;
    }

    public String getTacGiaName() {
        return tacGiaName;
    }

    public String getTheLoaiName() {
        return theLoaiName;
    }

    public String getNxbName() {
        return nxbName;
    }

    public int getSoLuong() {
        return soLuong;
    }
}