package com.thuvien.model;

import java.util.Date;

public class DocGia {
    private String docGiaId;
    private String docGiaName;
    private String ngaySinh;
    private String diaChi;
    private String sdt;
    private String email;
    private String taiKhoanId;

    public DocGia() {}

    public DocGia(String docGiaId, String docGiaName, String ngaySinh, String diaChi, String sdt, String email, String taiKhoanId) {
        this.docGiaId = docGiaId;
        this.docGiaName = docGiaName;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.taiKhoanId = taiKhoanId;
    }

    public String getDocGiaId() { return docGiaId; }
    public void setDocGiaId(String docGiaId) { this.docGiaId = docGiaId; }

    public String getDocGiaName() { return docGiaName; }
    public void setDocGiaName(String docGiaName) { this.docGiaName = docGiaName; }

    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTaiKhoanId() { return taiKhoanId; }
    public void setTaiKhoanId(String taiKhoanId) { this.taiKhoanId = taiKhoanId; }

    public Object getSoSachDangMuon() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getLuotMuon() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



//






