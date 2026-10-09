package com.thuvien.model;

public class Sach {
    private String sachId;
    private String sachName;
    private String infoSach;
    private String imgSach;
    private String tacGiaId;
    private String theLoaiId;
    private String nxbId;
    private int soLuong;

    public Sach() {}

    public Sach(String sachId, String sachName, String infoSach, String imgSach, 
                String tacGiaId, String theLoaiId, String nxbId, int soLuong) {
        this.sachId = sachId;
        this.sachName = sachName;
        this.infoSach = infoSach;
        this.imgSach = imgSach;
        this.tacGiaId = tacGiaId;
        this.theLoaiId = theLoaiId;
        this.nxbId = nxbId;
        this.soLuong = soLuong;
    }

    public String getSachId() { return sachId; }
    public void setSachId(String sachId) { this.sachId = sachId; }

    public String getSachName() { return sachName; }
    public void setSachName(String sachName) { this.sachName = sachName; }

    public String getInfoSach() { return infoSach; }
    public void setInfoSach(String infoSach) { this.infoSach = infoSach; }

    public String getImgSach() { return imgSach; }
    public void setImgSach(String imgSach) { this.imgSach = imgSach; }

    public String getTacGiaId() { return tacGiaId; }
    public void setTacGiaId(String tacGiaId) { this.tacGiaId = tacGiaId; }

    public String getTheLoaiId() { return theLoaiId; }
    public void setTheLoaiId(String theLoaiId) { this.theLoaiId = theLoaiId; }

    public String getNxbId() { return nxbId; }
    public void setNxbId(String nxbId) { this.nxbId = nxbId; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}