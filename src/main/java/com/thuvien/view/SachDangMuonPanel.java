


package com.thuvien.view;

import com.thuvien.controller.SachDangMuonPanelController;
import com.thuvien.model.SachDangMuonCaNhanModel;
import com.thuvien.utils.PhienDangNhap;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SachDangMuonPanel extends javax.swing.JPanel {

    private JButton btnLamMoi;
    private final SachDangMuonPanelController controller = new SachDangMuonPanelController();

    public SachDangMuonPanel() {
        // PHẢI tạo nút trước initComponents()
        // vì initComponents() sử dụng nút trong GroupLayout.
        btnLamMoi = new JButton("Làm mới");

        initComponents();
        caiDatBang();
        caiDatNutLamMoi();
        loadDataToTable();
    }

    /**
     * Cài đặt giao diện bảng.
     */
    private void caiDatBang() {
        setBackground(new Color(245, 245, 245));

        jTextField1.setEditable(false);
        jTextField1.setFocusable(false);
        jTextField1.setBorder(null);
        jTextField1.setBackground(new Color(245, 245, 245));

        jScrollPane1.getViewport().setBackground(Color.WHITE);

        jTable1.setBackground(Color.WHITE);
        jTable1.setRowHeight(32);
        jTable1.setFillsViewportHeight(true);

        jTable1.getTableHeader().setOpaque(true);
        jTable1.getTableHeader().setBackground(
                new Color(240, 242, 245)
        );
        jTable1.getTableHeader().setForeground(Color.BLACK);
        jTable1.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        // Căn giữa các cột.
        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        // Tô màu cột trạng thái.
        jTable1.getColumnModel().getColumn(6).setCellRenderer(
                new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    javax.swing.JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column
                );

                setHorizontalAlignment(JLabel.CENTER);

                if (!isSelected) {
                    String trangThai = value == null
                            ? ""
                            : value.toString();

                    if ("Đang mượn".equalsIgnoreCase(trangThai)) {
                        c.setBackground(new Color(220, 255, 220));
                        c.setForeground(new Color(0, 100, 0));

                    } else if ("Quá hạn".equalsIgnoreCase(trangThai)) {
                        c.setBackground(new Color(255, 225, 225));
                        c.setForeground(new Color(180, 0, 0));

                    } else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.BLACK);
                    }
                }

                return c;
            }
        });

        // Độ rộng các cột.
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
    }

    /**
     * Cài đặt nút Làm mới.
     * Không tạo lại nút ở đây vì nút đã được tạo trước initComponents().
     */
    private void caiDatNutLamMoi() {
        btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setBackground(new Color(46, 125, 80));
        btnLamMoi.setFocusPainted(false);
        btnLamMoi.setOpaque(true);
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
        btnLamMoi.setBorder(
                BorderFactory.createEmptyBorder(8, 18, 8, 18)
        );

        btnLamMoi.addActionListener(e -> lamMoiDuLieu());
    }

    /**
     * Tải lại dữ liệu mượn trả từ database.
     * Có thể gọi từ màn hình khác nếu cần.
     */
    public void lamMoiDuLieu() {
        loadDataToTable();
    }

    /**
     * Chỉ tải thông tin mượn trả của độc giả đang đăng nhập.
     */
    private void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        String maDocGia = PhienDangNhap.maDocGia;
        if (maDocGia == null || maDocGia.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Không xác định được tài khoản độc giả đang đăng nhập. "
                    + "Vui lòng đăng nhập lại.",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            new com.thuvien.dao.TrangThaiMuonTraDAO().capNhatTrangThaiQuaHan();
            List<SachDangMuonCaNhanModel> danhSach = controller.layDanhSach(maDocGia);
            int stt = 1;
            for (SachDangMuonCaNhanModel phieu : danhSach) {
                model.addRow(new Object[]{
                    stt++,
                    phieu.getMaMuon(),
                    phieu.getTenSach(),
                    phieu.getSoLuong(),
                    phieu.getNgayMuon(),
                    phieu.getHanTra(),
                    phieu.getTrangThai()
                });
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this, e.getMessage(), "Thông báo", JOptionPane.WARNING_MESSAGE
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Lỗi tải dữ liệu mượn trả: " + e.getMessage(),
                    "Lỗi cơ sở dữ liệu",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Khởi tạo giao diện.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new Color(245, 245, 245));

        jTextField1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jTextField1.setText("SÁCH ĐANG MƯỢN");

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "STT",
                    "Mã mượn",
                    "Tên sách",
                    "Số lượng",
                    "Ngày mượn",
                    "Hạn trả",
                    "Trạng thái"
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(this);

        setLayout(layout);

        // Bố cục ngang.
        layout.setHorizontalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(
                                jTextField1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                300,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addContainerGap(
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE
                        )
                )
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(
                                btnLamMoi,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addContainerGap()
                )
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(
                                jScrollPane1,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                809,
                                Short.MAX_VALUE
                        )
                        .addContainerGap()
                )
        );

        // Bố cục dọc.
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(
                                jTextField1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                50,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addGap(5, 5, 5)
                        .addComponent(
                                btnLamMoi,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addGap(8, 8, 8)
                        .addComponent(
                                jScrollPane1,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                480,
                                Short.MAX_VALUE
                        )
                        .addContainerGap()
        );
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;

    /**
     * Chạy thử riêng panel.
     * Cần có PhienDangNhap.maDocGia hợp lệ.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sách đang mượn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new SachDangMuonPanel());
            frame.setSize(900, 650);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
