
package com.thuvien.view;

import com.thuvien.controller.SuaSachController;
import com.thuvien.model.SuaSachItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SuaSachPanel extends JPanel {

    private final SuaSachController controller;

    private JTextField txtTimKiem;
    private JTable tblSach;
    private DefaultTableModel tableModel;

    private JTextField txtMaSach;
    private JTextField txtTenSach;
    private JTextField txtTacGia;
    private JTextField txtTheLoai;
    private JTextField txtNXB;
    private JTextField txtSoLuong;

    private JButton btnCapNhat;
    private JButton btnLamMoi;

    // Mã sách đang được chọn
    private String maSachDangChon = null;

    // Dữ liệu gốc để hoàn tác
    private SuaSachItem sachBanDau = null;

    private final Color GREEN = new Color(42, 157, 85);
    private final Color GREEN_HOVER = new Color(32, 130, 69);

    private final Color ORANGE = new Color(217, 119, 6);
    private final Color ORANGE_HOVER = new Color(184, 96, 3);

    private final Color BACKGROUND = new Color(244, 246, 249);
    private final Color TEXT = new Color(48, 56, 70);
    private final Color BORDER = new Color(221, 227, 235);

    public SuaSachPanel() {
        this(new SuaSachController());
    }

    public SuaSachPanel(SuaSachController controller) {
        this.controller = controller;
        initComponents();
        taiDanhSachSach("");
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 20));
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // ===== Tiêu đề =====
        JLabel lblTieuDe = new JLabel("Sửa Thông Tin Sách");
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblTieuDe.setForeground(TEXT);

        add(lblTieuDe, BorderLayout.NORTH);

        // ===== Chia giao diện trái / phải =====
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                taoPanelDanhSach(),
                taoPanelChinhSua()
        );

        splitPane.setResizeWeight(0.55);
        splitPane.setDividerSize(8);
        splitPane.setBorder(null);
        splitPane.setContinuousLayout(true);

        add(splitPane, BorderLayout.CENTER);
    }

    // ===== Panel danh sách bên trái =====
    private JPanel taoPanelDanhSach() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(BACKGROUND);
        panel.setBorder(new EmptyBorder(0, 0, 0, 10));

        txtTimKiem = taoTextField();

        JButton btnTimKiem = new JButton("Tìm sách");
        styleButton(btnTimKiem, GREEN, GREEN_HOVER, 120, 40);

        JPanel panelTimKiem = new JPanel(new BorderLayout(8, 0));
        panelTimKiem.setBackground(BACKGROUND);
        panelTimKiem.add(txtTimKiem, BorderLayout.CENTER);
        panelTimKiem.add(btnTimKiem, BorderLayout.EAST);

        // Không cho sửa trực tiếp các ô trên bảng
        tableModel = new DefaultTableModel(
                new Object[]{
                    "STT", "Mã sách", "Tên sách", "Tác giả"
                }, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblSach = new JTable(tableModel);
        tblSach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblSach.setRowHeight(30);
        tblSach.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );
        tblSach.setAutoCreateRowSorter(true);
        tblSach.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );
        tblSach.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tblSach);
        scroll.setBorder(BorderFactory.createLineBorder(BORDER));

        panel.add(panelTimKiem, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        // Tìm khi bấm nút
        btnTimKiem.addActionListener(e ->
                taiDanhSachSach(txtTimKiem.getText().trim())
        );

        // Tìm khi nhấn Enter
        txtTimKiem.addActionListener(e ->
                taiDanhSachSach(txtTimKiem.getText().trim())
        );

        // Nhấp đúp dòng để nạp thông tin
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int viewRow = tblSach.getSelectedRow();

                    if (viewRow >= 0) {
                        int modelRow = tblSach.convertRowIndexToModel(
                                viewRow
                        );

                        String maSach = tableModel.getValueAt(
                                modelRow, 1
                        ).toString();

                        napThongTinSach(maSach);
                    }
                }
            }
        });

        return panel;
    }

    // ===== Panel chỉnh sửa bên phải =====
    private JPanel taoPanelChinhSua() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);

        txtMaSach = taoTextField();
        txtMaSach.setEditable(false);
        txtMaSach.setBackground(new Color(235, 238, 242));

        txtTenSach = taoTextField();
        txtTacGia = taoTextField();
        txtTheLoai = taoTextField();
        txtNXB = taoTextField();
        txtSoLuong = taoTextField();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(9, 5, 9, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        themDongForm(form, gbc, 0, "Mã sách", txtMaSach);
        themDongForm(form, gbc, 1, "Tên sách", txtTenSach);
        themDongForm(form, gbc, 2, "Tác giả", txtTacGia);
        themDongForm(form, gbc, 3, "Thể loại", txtTheLoai);
        themDongForm(form, gbc, 4, "Nhà xuất bản", txtNXB);
        themDongForm(form, gbc, 5, "Số lượng", txtSoLuong);

        btnCapNhat = new JButton("Cập nhật hệ thống");
        btnLamMoi = new JButton("Làm mới / Hoàn tác");

        styleButton(
                btnCapNhat, GREEN, GREEN_HOVER, 190, 44
        );

        styleButton(
                btnLamMoi, ORANGE, ORANGE_HOVER, 190, 44
        );

        JPanel panelNut = new JPanel(
                new GridLayout(1, 2, 10, 0)
        );
        panelNut.setBackground(Color.WHITE);
        panelNut.add(btnCapNhat);
        panelNut.add(btnLamMoi);

        panel.add(form, BorderLayout.CENTER);
        panel.add(panelNut, BorderLayout.SOUTH);

        btnCapNhat.addActionListener(e -> capNhatSach());
        btnLamMoi.addActionListener(e -> lamMoiHoanTac());

        // Ban đầu chưa chọn sách
        khoaForm();

        return panel;
    }

    // ===== Thành phần giao diện dùng chung =====
    private JTextField taoTextField() {
        JTextField field = new JTextField();

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(TEXT);
        field.setBackground(Color.WHITE);
        field.setPreferredSize(new Dimension(180, 38));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(210, 218, 229)
                ),
                new EmptyBorder(5, 10, 5, 10)
        ));

        return field;
    }

    private void themDongForm(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String nhan,
            JComponent component) {

        JLabel label = new JLabel(nhan);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(TEXT);

        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        component.setPreferredSize(new Dimension(180, 38));
        panel.add(component, gbc);
    }

    private void styleButton(
            JButton button,
            Color normalColor,
            Color hoverColor,
            int width,
            int height) {

        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(normalColor);
        button.setPreferredSize(new Dimension(width, height));

        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(8, 12, 8, 12));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(hoverColor);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }

    // ===== Tải danh sách vào JTable =====
    private void taiDanhSachSach(String tuKhoa) {
        try {
            List<SuaSachItem> danhSach =
                    controller.getDanhSachSach(tuKhoa);

            tableModel.setRowCount(0);

            int stt = 1;

            for (SuaSachItem sach : danhSach) {
                tableModel.addRow(new Object[]{
                    stt++,
                    sach.getMaSach(),
                    sach.getTenSach(),
                    sach.getTenTacGia()
                });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Không thể tải danh sách sách.\n"
                    + ex.getMessage(),
                    "Lỗi database",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ===== Nạp thông tin khi nhấp đúp =====
    private void napThongTinSach(String maSach) {
        try {
            SuaSachItem sach = controller.getSachTheoMa(maSach);

            if (sach == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Không tìm thấy sách có mã " + maSach,
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE
                );

                taiDanhSachSach(txtTimKiem.getText().trim());
                return;
            }

            sachBanDau = sach;
            maSachDangChon = sach.getMaSach();

            hienThiThongTin(sach);
            moKhoaForm();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Không thể lấy thông tin sách.\n"
                    + ex.getMessage(),
                    "Lỗi database",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void hienThiThongTin(SuaSachItem sach) {
        txtMaSach.setText(sach.getMaSach());
        txtTenSach.setText(sach.getTenSach());
        txtTacGia.setText(sach.getTenTacGia());
        txtTheLoai.setText(sach.getTenTheLoai());
        txtNXB.setText(sach.getTenNXB());
        txtSoLuong.setText(String.valueOf(sach.getSoLuong()));
    }

    // ===== Cập nhật database =====
    private void capNhatSach() {
        if (maSachDangChon == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Bạn hãy nhấp đúp vào một cuốn sách trước.",
                    "Chưa chọn sách",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String tenSach = txtTenSach.getText().trim();
        String tacGia = txtTacGia.getText().trim();
        String theLoai = txtTheLoai.getText().trim();
        String nxb = txtNXB.getText().trim();
        String soLuongText = txtSoLuong.getText().trim();

        if (tenSach.isEmpty()
                || tacGia.isEmpty()
                || theLoai.isEmpty()
                || nxb.isEmpty()
                || soLuongText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng điền đầy đủ thông tin sách.",
                    "Thiếu thông tin",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (tenSach.length() > 255
                || tacGia.length() > 255
                || theLoai.length() > 255
                || nxb.length() > 255) {

            JOptionPane.showMessageDialog(
                    this,
                    "Tên sách, tác giả, thể loại và nhà xuất bản "
                    + "không được vượt quá 255 ký tự.",
                    "Dữ liệu không hợp lệ",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int soLuong;

        try {
            soLuong = Integer.parseInt(soLuongText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Số lượng phải là số nguyên.",
                    "Dữ liệu không hợp lệ",
                    JOptionPane.WARNING_MESSAGE
            );
            txtSoLuong.requestFocusInWindow();
            return;
        }

        if (soLuong < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Số lượng không được nhỏ hơn 0.",
                    "Dữ liệu không hợp lệ",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int luaChon = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn cập nhật sách "
                + maSachDangChon + " không?",
                "Xác nhận cập nhật",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (luaChon != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            controller.capNhatSach(
                    maSachDangChon,
                    tenSach,
                    tacGia,
                    theLoai,
                    nxb,
                    soLuong
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Cập nhật thông tin sách thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Tải lại bảng và dữ liệu đã lưu
            taiDanhSachSach(txtTimKiem.getText().trim());
            napThongTinSach(maSachDangChon);

        } catch (SQLException ex) {
            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Không thể cập nhật thông tin sách.\n"
                    + "Chi tiết: " + ex.getMessage(),
                    "Lỗi cập nhật",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Dữ liệu không hợp lệ",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ===== Làm mới / Hoàn tác =====
    private void lamMoiHoanTac() {
        if (maSachDangChon == null) {
            xoaTrangForm();
            return;
        }

        // Nạp lại dữ liệu hiện có trong database,
        // bỏ các chỉnh sửa chưa lưu.
        napThongTinSach(maSachDangChon);
    }

    private void khoaForm() {
        txtMaSach.setEditable(false);
        txtTenSach.setEditable(false);
        txtTacGia.setEditable(false);
        txtTheLoai.setEditable(false);
        txtNXB.setEditable(false);
        txtSoLuong.setEditable(false);

        btnCapNhat.setEnabled(false);
        btnLamMoi.setEnabled(false);
    }

    private void moKhoaForm() {
        txtMaSach.setEditable(false);
        txtTenSach.setEditable(true);
        txtTacGia.setEditable(true);
        txtTheLoai.setEditable(true);
        txtNXB.setEditable(true);
        txtSoLuong.setEditable(true);

        btnCapNhat.setEnabled(true);
        btnLamMoi.setEnabled(true);
    }

    private void xoaTrangForm() {
        maSachDangChon = null;
        sachBanDau = null;

        txtMaSach.setText("");
        txtTenSach.setText("");
        txtTacGia.setText("");
        txtTheLoai.setText("");
        txtNXB.setText("");
        txtSoLuong.setText("");

        khoaForm();
    }
}
