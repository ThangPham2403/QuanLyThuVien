
package com.thuvien.view;

import com.thuvien.controller.TraCuuSachController;
import com.thuvien.model.TraCuuSachModel;
import java.sql.SQLException;
import java.util.List;

public class TraCuuSachPanel extends javax.swing.JPanel {

    private final TraCuuSachController controller = new TraCuuSachController();

    public TraCuuSachPanel() {
        initComponents();

        jTextField1.setEditable(false);
        jTextField1.setFocusable(false);

        // 1. Đổi màu nền JPanel thành xám nhạt
        this.setBackground(new java.awt.Color(240, 242, 245));

        // 2. Chỉnh bảng và khung cuộn thành màu trắng
        jTable1.setBackground(java.awt.Color.WHITE);
        jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);

        // 3. Định dạng tiêu đề bảng
        jTable1.getTableHeader().setOpaque(true);
        jTable1.getTableHeader().setBackground(
                new java.awt.Color(240, 242, 245));
        jTable1.getTableHeader().setForeground(java.awt.Color.BLACK);
        jTable1.getTableHeader().setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        jTable1.setRowHeight(32);

        // 4. Căn giữa một số cột
        javax.swing.table.DefaultTableCellRenderer centerRenderer
                = new javax.swing.table.DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        jTable1.getColumnModel().getColumn(0)
                .setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(1)
                .setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(6)
                .setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(7)
                .setCellRenderer(centerRenderer);

        // 5. Chỉnh độ rộng các cột
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(170);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(90);

        // 6. Khởi tạo ComboBox thể loại
        cboTheLoai.removeAllItems();
        cboTheLoai.addItem("Tất cả thể loại");
        cboTheLoai.addItem("Kỹ năng sống");
        cboTheLoai.addItem("Công nghệ thông tin");
        cboTheLoai.addItem("Văn học thiếu nhi");

        // 7. Khởi tạo ComboBox trạng thái
        cboTrangThai.removeAllItems();
        cboTrangThai.addItem("Tất cả trạng thái");
        cboTrangThai.addItem("Còn sách");
        cboTrangThai.addItem("Hết sách");

        // 8. Tải dữ liệu sách khi mở giao diện
        loadDataToTable();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        button1 = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        cboTheLoai = new javax.swing.JComboBox<>();
        cboTrangThai = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(240, 242, 245));

        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setText("Từ Khóa Tìm Kiếm:");

        button1.setBackground(new java.awt.Color(51, 153, 0));
        button1.setFont(new java.awt.Font("Dialog", 1, 12));
        button1.setForeground(java.awt.Color.WHITE);
        button1.setLabel("Tìm");
        button1.addActionListener(evt -> button1ActionPerformed(evt));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null}
                },
                new String[]{
                    "STT", "Mã", "Tên Sách", "Tác Giả",
                    "Thể Loại", "Nhà Xuất Bản", "Còn/Tổng", "Trạng Thái"
                }
        ));

        jScrollPane1.setViewportView(jTable1);

        txtTimKiem.setText("Nhập mã sách, tên sách, tác giả...");
        txtTimKiem.addActionListener(evt -> txtTimKiemActionPerformed(evt));

        cboTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Tất cả thể loại"}));
        cboTheLoai.addActionListener(evt -> cboTheLoaiActionPerformed(evt));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Tất cả trạng thái"}));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 16));
        jTextField1.setText("    XEM ĐẦU SÁCH");

        // Bố cục giao diện: đã bỏ jLabel6 đăng ký mượn
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29)
                                .addGroup(layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1, 182, 182, 182)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(
                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2, 159, 159, 159)
                                                        .addComponent(txtTimKiem, 196, 196, 196))
                                                .addGap(20)
                                                .addComponent(button1, 93, 93, 93)
                                                .addGap(30)
                                                .addComponent(cboTheLoai, 150, 150, 150)
                                                .addGap(20)
                                                .addComponent(cboTrangThai, 160, 160, 160)))
                                .addContainerGap(30, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, 57, 57, 57)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.CENTER)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, 35, 35, 35)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTimKiem, 35, 35, 35))
                                .addComponent(button1, 45, 45, 45)
                                .addComponent(cboTheLoai, 39, 39, 39)
                                .addComponent(cboTrangThai, 39, 39, 39))
                        .addGap(26)
                        .addComponent(jScrollPane1, 410, 410, 410)
                        .addContainerGap(20, Short.MAX_VALUE)
        );
    }

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
        loadDataToTable();
    }

    private void cboTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể bổ sung lọc tự động khi thay đổi thể loại nếu cần.
    }

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {
        loadDataToTable();
    }

    // Các thành phần giao diện
    private java.awt.Button button1;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtTimKiem;

    private void loadDataToTable() {
        String keyword = txtTimKiem.getText().trim();
        String theLoaiChon = cboTheLoai.getSelectedItem() != null
                ? cboTheLoai.getSelectedItem().toString() : "";
        String trangThaiChon = cboTrangThai.getSelectedItem() != null
                ? cboTrangThai.getSelectedItem().toString() : "";

        javax.swing.table.DefaultTableModel tableModel
                = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);

        try {
            List<TraCuuSachModel> danhSach = controller.timKiem(
                    keyword, theLoaiChon, trangThaiChon);
            int stt = 1;
            for (TraCuuSachModel sach : danhSach) {
                tableModel.addRow(new Object[]{
                    stt++,
                    sach.getMaSach(),
                    sach.getTenSach(),
                    sach.getTacGia(),
                    sach.getTheLoai(),
                    sach.getNhaXuatBan(),
                    sach.getSoLuongHienThi(),
                    sach.getTrangThai()
                });
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Lỗi tải dữ liệu: " + e.getMessage(),
                    "Lỗi kết nối cơ sở dữ liệu",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame
                    = new javax.swing.JFrame("Chạy thử Tra Cứu Sách Panel");

            frame.setDefaultCloseOperation(
                    javax.swing.JFrame.EXIT_ON_CLOSE);

            frame.add(new TraCuuSachPanel());
            frame.setSize(1100, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}