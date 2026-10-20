
package com.thuvien.model;

public class SachQuaHanModel {

    private String maMuonTra;
    private String soDienThoai;
    private String hanTra;
    private String trangThai;

    public SachQuaHanModel(String maMuonTra,
                           String soDienThoai,
                           String hanTra,
                           String trangThai) {
        this.maMuonTra = maMuonTra;
        this.soDienThoai = soDienThoai;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
    }

    public String getMaMuonTra() {
        return maMuonTra;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getHanTra() {
        return hanTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public Object getSdt() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
