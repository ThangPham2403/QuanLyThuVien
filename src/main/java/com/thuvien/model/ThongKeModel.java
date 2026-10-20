package com.thuvien.model;

public class ThongKeModel {

    private int tongDauSach;
    private int dangMuon;
    private int quaHan;
    private int tongDocGia;

    public ThongKeModel(int tongDauSach, int dangMuon, int quaHan, int tongDocGia) {
        this.tongDauSach = tongDauSach;
        this.dangMuon = dangMuon;
        this.quaHan = quaHan;
        this.tongDocGia = tongDocGia;
    }

    public int getTongDauSach() {
        return tongDauSach;
    }

    public int getDangMuon() {
        return dangMuon;
    }

    public int getQuaHan() {
        return quaHan;
    }

    public int getTongDocGia() {
        return tongDocGia;
    }

    public Object getTongSoSach() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getTongSach() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}