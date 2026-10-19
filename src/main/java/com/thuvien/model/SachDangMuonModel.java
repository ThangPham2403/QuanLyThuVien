
package com.thuvien.model;

public class SachDangMuonModel {

    private String idSach;
    private String soDienThoai;
    private String hanTra;
    private String trangThai;

    public SachDangMuonModel(String idSach,
            String soDienThoai, String hanTra,
            String trangThai) {

        this.idSach = idSach;
        this.soDienThoai = soDienThoai;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
    }

    public String getIdSach() {
        return idSach;
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
}
