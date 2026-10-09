package com.thuvien.model;

public class CTMuonTra {
    private String ctmtId;
    private String muonTraId;
    private String sachId;
    private int soLuong;

    public CTMuonTra() {}

    public CTMuonTra(String ctmtId, String muonTraId, String sachId, int soLuong) {
        this.ctmtId = ctmtId;
        this.muonTraId = muonTraId;
        this.sachId = sachId;
        this.soLuong = soLuong;
    }

    public String getCtmtId() { return ctmtId; }
    public void setCtmtId(String ctmtId) { this.ctmtId = ctmtId; }

    public String getMuonTraId() { return muonTraId; }
    public void setMuonTraId(String muonTraId) { this.muonTraId = muonTraId; }

    public String getSachId() { return sachId; }
    public void setSachId(String sachId) { this.sachId = sachId; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}