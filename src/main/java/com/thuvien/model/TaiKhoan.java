package com.thuvien.model;

public class TaiKhoan {
    private String taiKhoanId;
    private String taiKhoanName;
    private String email;
    private String password;
    private String roles;

    public TaiKhoan() {}

    public TaiKhoan(String taiKhoanId, String taiKhoanName, String email, String password, String roles) {
        this.taiKhoanId = taiKhoanId;
        this.taiKhoanName = taiKhoanName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getTaiKhoanId() { return taiKhoanId; }
    public void setTaiKhoanId(String taiKhoanId) { this.taiKhoanId = taiKhoanId; }

    public String getTaiKhoanName() { return taiKhoanName; }
    public void setTaiKhoanName(String taiKhoanName) { this.taiKhoanName = taiKhoanName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }
}