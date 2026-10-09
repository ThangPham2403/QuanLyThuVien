package com.thuvien.model;

import java.util.Date;

public class MuonTra {
    private String muonTraId;
    private String docGiaId;
    private Date thoiGianMuon;
    private Date thoiGianTra;
    private String trangThai;

    public MuonTra() {}

    public MuonTra(String muonTraId, String docGiaId, Date thoiGianMuon, Date thoiGianTra, String trangThai) {
        this.muonTraId = muonTraId;
        this.docGiaId = docGiaId;
        this.thoiGianMuon = thoiGianMuon;
        this.thoiGianTra = thoiGianTra;
        this.trangThai = trangThai;
    }

    public String getMuonTraId() { return muonTraId; }
    public void setMuonTraId(String muonTraId) { this.muonTraId = muonTraId; }

    public String getDocGiaId() { return docGiaId; }
    public void setDocGiaId(String docGiaId) { this.docGiaId = docGiaId; }

    public Date getThoiGianMuon() { return thoiGianMuon; }
    public void setThoiGianMuon(Date thoiGianMuon) { this.thoiGianMuon = thoiGianMuon; }

    public Date getThoiGianTra() { return thoiGianTra; }
    public void setThoiGianTra(Date thoiGianTra) { this.thoiGianTra = thoiGianTra; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}