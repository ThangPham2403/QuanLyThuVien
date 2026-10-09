package com.thuvien.model;

import java.util.Date;

public class DocGia {
    private String docGiaId;
    private String docGiaName;
    private Date ngaySinh;
    private String diaChi;
    private String sdt;
    private String email;
    private String taiKhoanId;

    public DocGia() {}

    public DocGia(String docGiaId, String docGiaName, Date ngaySinh, String diaChi, String sdt, String email, String taiKhoanId) {
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

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTaiKhoanId() { return taiKhoanId; }
    public void setTaiKhoanId(String taiKhoanId) { this.taiKhoanId = taiKhoanId; }
}