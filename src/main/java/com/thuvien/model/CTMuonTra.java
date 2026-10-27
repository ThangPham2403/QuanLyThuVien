//package com.thuvien.model;
//
//public class CTMuonTra {
//    private String ctmtId;
//    private String muonTraId;
//    private String sachId;
//    private int soLuong;
//
//    public CTMuonTra() {}
//
//    public CTMuonTra(String ctmtId, String muonTraId, String sachId, int soLuong) {
//        this.ctmtId = ctmtId;
//        this.muonTraId = muonTraId;
//        this.sachId = sachId;
//        this.soLuong = soLuong;
//    }
//
//    public String getCtmtId() { return ctmtId; }
//    public void setCtmtId(String ctmtId) { this.ctmtId = ctmtId; }
//
//    public String getMuonTraId() { return muonTraId; }
//    public void setMuonTraId(String muonTraId) { this.muonTraId = muonTraId; }
//
//    public String getSachId() { return sachId; }
//    public void setSachId(String sachId) { this.sachId = sachId; }
//
//    public int getSoLuong() { return soLuong; }
//    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
//    
//    
//    
//    //-------------
//    // --- 2 cái public này chính là 2 cái cần thêm ở đoạn code của Nhung phần CTMuonTraDAO----
//    //------------------------------
//    public void setTenSach(String string) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public void setTacGia(String string) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}


package com.thuvien.model;

public class CTMuonTra {
    private String ctmtId;
    private String muonTraId;
    private String sachId;
    private int soLuong;    
    private String tenSach;    
    private String tacGia;

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
    
    
    
    //-------------
    // --- 2 cái public này chính là 2 cái cần thêm ở đoạn code của Nhung phần CTMuonTraDAO----
    //------------------------------
    public void setTenSach(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setTacGia(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}