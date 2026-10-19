package com.thuvien.model;
import java.util.Date;
public class PhieuMuon {
    private String muontraId;
    private String docgiaId;
    private Date thoiGianMuon;
    private Date thoiGianTra;
    private String trangThai;

    // Constructor không tham số
    public PhieuMuon() {
    }

    // Constructor có tham số đầy đủ
    public PhieuMuon(String muontraId, String docgiaId, Date thoiGianMuon, Date thoiGianTra, String trangThai) {
        this.muontraId = muontraId;
        this.docgiaId = docgiaId;
        this.thoiGianMuon = thoiGianMuon;
        this.thoiGianTra = thoiGianTra;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public String getMuontraId() {
        return muontraId;
    }

    public void setMuontraId(String muontraId) {
        this.muontraId = muontraId;
    }

    public String getDocgiaId() {
        return docgiaId;
    }

    public void setDocgiaId(String docgiaId) {
        this.docgiaId = docgiaId;
    }

    public Date getThoiGianMuon() {
        return thoiGianMuon;
    }

    public void setThoiGianMuon(Date thoiGianMuon) {
        this.thoiGianMuon = thoiGianMuon;
    }

    public Date getThoiGianTra() {
        return thoiGianTra;
    }

    public void setThoiGianTra(Date thoiGianTra) {
        this.thoiGianTra = thoiGianTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
