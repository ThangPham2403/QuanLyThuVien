package com.thuvien.model;

public class NXB {
    private String nxbId;
    private String nxbName;
    private String info;

    public NXB() {}

    public NXB(String nxbId, String nxbName, String info) {
        this.nxbId = nxbId;
        this.nxbName = nxbName;
        this.info = info;
    }

    public String getNxbId() { return nxbId; }
    public void setNxbId(String nxbId) { this.nxbId = nxbId; }

    public String getNxbName() { return nxbName; }
    public void setNxbName(String nxbName) { this.nxbName = nxbName; }

    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

    @Override
    public String toString() {
        return nxbName; // Hiển thị tên khi đưa vào JComboBox
    }
}