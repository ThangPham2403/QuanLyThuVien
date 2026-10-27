

package com.thuvien.view;

import com.thuvien.dao.PhieuMuonDAO;
import com.thuvien.model.PhieuMuon;
import com.thuvien.model.CTMuonTra;

public class QuanLyMuonTraPanel extends javax.swing.JPanel {

    private javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> rowSorter;

    public QuanLyMuonTraPanel() {
        initComponents();

        loadDataToTable();

        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) tblMuonTra.getModel();

        rowSorter = new javax.swing.table.TableRowSorter<>(model);
        tblMuonTra.setRowSorter(rowSorter);

        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                locDanhSachSach();
            }
        });

        // Tự động dàn đều các cột, tránh khoảng trống xanh bên phải bảng.
        tblMuonTra.setRowHeight(35);
        tblMuonTra.setAutoResizeMode(
                javax.swing.JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tblMuonTra.setFillsViewportHeight(true);
        tblMuonTra.setBackground(java.awt.Color.WHITE);
        jScrollPane2.getViewport().setBackground(java.awt.Color.WHITE);
        tblMuonTra.getTableHeader().setPreferredSize(
                new java.awt.Dimension(0, 35));
        tblMuonTra.getTableHeader().setOpaque(true);
        tblMuonTra.getTableHeader().setBackground(
                new java.awt.Color(30, 90, 200));
        tblMuonTra.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblMuonTra.getTableHeader().setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        tblMuonTra.setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

        // Tên sách tự động hiển thị theo dòng đang chọn.
        jTextField6.setEditable(false);

        tblMuonTra.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblMuonTra.getSelectedRow();

                if (selectedRow >= 0) {
                    int modelRow =
                            tblMuonTra.convertRowIndexToModel(selectedRow);

                    jTextField6.setText(
                            String.valueOf(model.getValueAt(modelRow, 1)));
                }
            }
        });

        // Mặc định ngày mượn là hôm nay, hạn trả sau 14 ngày.
        java.time.LocalDate homNay = java.time.LocalDate.now();

        jTextField11.setText(homNay.toString());
        jTextField1.setText(homNay.plusDays(14).toString());
    }

    private void locDanhSachSach() {
        if (rowSorter == null) {
            return;
        }

        String text = jTextField12.getText().trim();

        if (text.isEmpty()
                || text.equals("Tìm theo Tên, Tác giả, Mã sách.....")) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(
                    javax.swing.RowFilter.regexFilter(
                            "(?i)" + java.util.regex.Pattern.quote(text)));
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMuonTra = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        button1 = new java.awt.Button();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(240, 242, 245));
        setBorder(new javax.swing.border.LineBorder(
                new java.awt.Color(153, 204, 255), 5, true));

        jLabel1.setBackground(new java.awt.Color(51, 153, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER);
        jLabel1.setText(
                "QUẢN LÝ MƯỢN TRẢ SÁCH - HỆ THỐNG THƯ VIỆN");
        jLabel1.setOpaque(true);

        jLabel2.setText("TÊN SÁCH");
        jLabel3.setText("HỌ TÊN ĐỘC GIẢ");
        jLabel4.setText("SỐ ĐIỆN THOẠI");
        jLabel5.setText("ĐỊA CHỈ");
        jLabel6.setText("NGÀY MƯỢN");
        jLabel7.setText("HẠN TRẢ");

        jButton1.setBackground(new java.awt.Color(0, 51, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jButton1.setForeground(java.awt.Color.WHITE);
        jButton1.setText("Xác nhận mượn");
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        tblMuonTra.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Mã Sách", "Tên Sách",
                    "Tác Giả", "Số lượng trong kho"
                }
        ));
        jScrollPane2.setViewportView(tblMuonTra);

        jTextField12.setText("Tìm theo Tên, Tác giả, Mã sách.....");

        button1.setBackground(new java.awt.Color(51, 153, 0));
        button1.setForeground(java.awt.Color.WHITE);
        button1.setLabel("Tìm Kiếm");
        button1.addActionListener(evt -> locDanhSachSach());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField12)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, 62, 62, 62))
                    .addComponent(jScrollPane2, 0, 578, Short.MAX_VALUE))
                .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, 102, 102, 102)
                    .addComponent(jTextField6)
                    .addComponent(jLabel3, 112, 112, 112)
                    .addComponent(jTextField8)
                    .addComponent(jLabel4, 106, 106, 106)
                    .addComponent(jTextField9)
                    .addComponent(jLabel5, 102, 102, 102)
                    .addComponent(jTextField10)
                    .addComponent(jLabel6, 100, 100, 100)
                    .addComponent(jTextField11)
                    .addComponent(jLabel7, 89, 89, 89)
                    .addComponent(jTextField1)
                    .addComponent(jButton1, 212, 212, 212))
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jLabel1, 318, 318, 318)
                .addContainerGap(
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, 32, 32, 32)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, 34, 34, 34))
                    .addComponent(button1, 35, 35, 35)
                    .addComponent(jTextField12, 35, 35, 35))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, 26, 26, 26)
                        .addGap(2, 2, 2)
                        .addComponent(jTextField8, 34, 34, 34)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, 34, 34, 34)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, 37, 37, 37)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, 34, 34, 34)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, 26, 26, 26)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, 37, 37, 37)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, 38, 38, 38))
                    .addComponent(jScrollPane2, 444, 444, 444))
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        int selectedRow = tblMuonTra.getSelectedRow();

        if (selectedRow < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn một cuốn sách trong bảng trước khi xác nhận mượn.");
            return;
        }

        int modelRow = tblMuonTra.convertRowIndexToModel(selectedRow);

        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) tblMuonTra.getModel();

        String maSach = String.valueOf(model.getValueAt(modelRow, 0));
        String tenSach = String.valueOf(model.getValueAt(modelRow, 1));

        int soLuongKho;
        try {
            soLuongKho = Integer.parseInt(
                    String.valueOf(model.getValueAt(modelRow, 3)));
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Không đọc được số lượng tồn kho của sách đã chọn.");
            return;
        }

        if (soLuongKho <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Sách này hiện không còn trong kho.");
            return;
        }

        String hoTen = jTextField8.getText().trim();
        String sdt = jTextField9.getText().trim();
        String diaChi = jTextField10.getText().trim();
        String ngayMuonStr = jTextField11.getText().trim();
        String hanTraStr = jTextField1.getText().trim();

        if (hoTen.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()
                || ngayMuonStr.isEmpty() || hanTraStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập đầy đủ họ tên, số điện thoại, địa chỉ, ngày mượn và hạn trả.");
            return;
        }

        java.util.Date ngayMuon;
        java.util.Date hanTra;

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            ngayMuon = sdf.parse(ngayMuonStr);
            hanTra = sdf.parse(hanTraStr);
        } catch (java.text.ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Ngày không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
            return;
        }

        if (hanTra.before(ngayMuon)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Hạn trả không được trước ngày mượn.");
            return;
        }

        try {
            PhieuMuonDAO dao = new PhieuMuonDAO();

            String maDocGia = dao.timDocGiaId(hoTen, sdt, diaChi);

            if (maDocGia == null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Không tìm thấy độc giả khớp cả họ tên, số điện thoại và địa chỉ trong bảng DOCGIA. "
                        + "Hãy kiểm tra thông tin hoặc tạo hồ sơ độc giả trước.");
                return;
            }

            PhieuMuon phieu = new PhieuMuon();
            phieu.setMuontraId(
                    dao.taoMaMoi("MUONTRA", "muontra_id", "MT"));
            phieu.setDocgiaId(maDocGia);
            phieu.setThoiGianMuon(ngayMuon);

            // Theo thống nhất của nhóm: thoi_gian_tra lưu hạn trả khi lập phiếu.
            phieu.setThoiGianTra(hanTra);
            phieu.setTrangThai("Đang mượn");

            CTMuonTra chiTiet = new CTMuonTra();
            chiTiet.setCtmtId(
                    dao.taoMaMoi("CT_MUONTRA", "ctmt_id", "CTMT"));
            chiTiet.setMuonTraId(phieu.getMuontraId());
            chiTiet.setSachId(maSach);
            chiTiet.setSoLuong(1);

            dao.muonSach(phieu, chiTiet);

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Lập phiếu mượn thành công!"
                    + "\nMã phiếu: " + phieu.getMuontraId()
                    + "\nTên sách: " + tenSach
                    + "\nMã sách: " + maSach
                    + "\nHạn trả: " + hanTraStr
                    + "\nSố lượng mượn: 1");

            loadDataToTable();
            jTextField6.setText("");

        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Không thể lập phiếu mượn: " + ex.getMessage(),
                    "Lỗi mượn sách",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    
    
    

    private void loadDataToTable() {

        String sql = "SELECT s.sach_id, s.sach_name, "
                + "ISNULL(tg.tacgia_name, N'Chưa có') AS tacgia, s.so_luong "
                + "FROM SACH s "
                + "LEFT JOIN TACGIA tg ON s.tacgia_id = tg.tacgia_id "
                + "ORDER BY s.sach_id";

        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) tblMuonTra.getModel();

        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{
            "Mã Sách", "Tên Sách", "Tác Giả", "Số lượng trong kho"
        });

        try (java.sql.Connection conn =
                     com.thuvien.config.DBConnection.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("sach_id"),
                    rs.getString("sach_name"),
                    rs.getString("tacgia"),
                    rs.getInt("so_luong")
                });
            }

            tblMuonTra.setAutoResizeMode(
                    javax.swing.JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            tblMuonTra.setFillsViewportHeight(true);

            if (rowSorter != null) {
                locDanhSachSach();
            }

        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Không thể tải danh sách sách từ database: " + e.getMessage(),
                    "Lỗi kết nối database",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Variables của giao diện
    private java.awt.Button button1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTable tblMuonTra;

    public static void main(String[] args) {
        javax.swing.JFrame frame =
                new javax.swing.JFrame("Quản Lý Mượn Trả Sách");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.add(new QuanLyMuonTraPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
