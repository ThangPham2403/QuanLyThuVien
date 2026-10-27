package com.thuvien.model;

/** Model dữ liệu hiển thị trên thẻ độc giả và thống kê mượn sách. */
public class ThongTinTheModel {
    private final String docGiaId;
    private final String hoTen;
    private final String soDienThoai;
    private final int soSachDangMuon;
    private final int luotMuon;

    public ThongTinTheModel(String docGiaId, String hoTen, String soDienThoai,
            int soSachDangMuon, int luotMuon) {
        this.docGiaId = docGiaId;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.soSachDangMuon = soSachDangMuon;
        this.luotMuon = luotMuon;
    }

    public String getDocGiaId() { return docGiaId; }
    public String getHoTen() { return hoTen; }
    public String getSoDienThoai() { return soDienThoai; }
    public int getSoSachDangMuon() { return soSachDangMuon; }
    public int getLuotMuon() { return luotMuon; }
}
