
package com.thuvien.view;

import com.thuvien.controller.XoaSachController;
import com.thuvien.model.XoaSachItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XoaSachPanel extends JPanel {

    private final XoaSachController controller;

    private JTextField txtTimKiem;
    private JButton btnTimKiem;
    private JButton btnXoa;

    private JPanel pnlDanhSach;
    private JScrollPane scrollDanhSach;

    private final List<JCheckBox> danhSachCheckbox = new ArrayList<>();
    private final List<String> danhSachMaSach = new ArrayList<>();

    // Màu sắc đồng bộ với ThemSachPanel
    private final Color GREEN = new Color(42, 157, 85);
    private final Color GREEN_HOVER = new Color(32, 130, 69);

    private final Color RED = new Color(220, 65, 45);
    private final Color RED_HOVER = new Color(190, 48, 32);

    private final Color BACKGROUND = new Color(244, 246, 249);
    private final Color TEXT = new Color(48, 56, 70);
    private final Color BORDER = new Color(221, 227, 235);

    public XoaSachPanel() {
        this(new XoaSachController());
    }

    public XoaSachPanel(XoaSachController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 20));
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // ===== 1. Tiêu đề căn trái =====
        JLabel lblTieuDe = new JLabel("Xóa Sách Khỏi Hệ Thống");
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblTieuDe.setForeground(TEXT);
        lblTieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== 2. Ô tìm kiếm =====
        txtTimKiem = new JTextField();
        txtTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTimKiem.setForeground(TEXT);
        txtTimKiem.setBackground(Color.WHITE);
        txtTimKiem.setPreferredSize(new Dimension(250, 40));
        txtTimKiem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(210, 218, 229)),
                new EmptyBorder(5, 10, 5, 10)
        ));

        // ===== 3. Nút tìm kiếm =====
        btnTimKiem = new JButton("Tìm cuốn sách này");
        styleButton(
                btnTimKiem,
                GREEN,
                GREEN_HOVER,
                210,
                44
        );

        JPanel pnlTimKiem = new JPanel(new BorderLayout(10, 0));
        pnlTimKiem.setBackground(BACKGROUND);
        pnlTimKiem.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlTimKiem.add(txtTimKiem, BorderLayout.CENTER);
        pnlTimKiem.add(btnTimKiem, BorderLayout.EAST);

        // ===== 4. Khu vực danh sách kết quả =====
        pnlDanhSach = new JPanel();
        pnlDanhSach.setLayout(
                new BoxLayout(pnlDanhSach, BoxLayout.Y_AXIS)
        );
        pnlDanhSach.setBackground(BACKGROUND);

        scrollDanhSach = new JScrollPane(pnlDanhSach);
        scrollDanhSach.setBorder(
                BorderFactory.createLineBorder(BORDER)
        );
        scrollDanhSach.getVerticalScrollBar().setUnitIncrement(16);
        scrollDanhSach.setVisible(false);

        // ===== 5. Nút xác nhận xóa =====
        btnXoa = new JButton("XÁC NHẬN XÓA VĨNH VIỄN");
        styleButton(
                btnXoa,
                RED,
                RED_HOVER,
                280,
                44
        );
        btnXoa.setVisible(false);

        // ===== 6. Khu vực phía trên =====
        JPanel pnlPhiaTren = new JPanel();
        pnlPhiaTren.setLayout(
                new BoxLayout(pnlPhiaTren, BoxLayout.Y_AXIS)
        );
        pnlPhiaTren.setBackground(BACKGROUND);

        pnlPhiaTren.add(lblTieuDe);
        pnlPhiaTren.add(Box.createVerticalStrut(18));
        pnlPhiaTren.add(pnlTimKiem);
        pnlPhiaTren.add(Box.createVerticalStrut(12));

        // ===== 7. Ghép các thành phần =====
        JPanel pnlNutXoa = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 0, 0)
        );
        pnlNutXoa.setBackground(BACKGROUND);
        pnlNutXoa.add(btnXoa);

        add(pnlPhiaTren, BorderLayout.NORTH);
        add(scrollDanhSach, BorderLayout.CENTER);
        add(pnlNutXoa, BorderLayout.SOUTH);

        // ===== 8. Sự kiện =====
        btnTimKiem.addActionListener(e -> timKiemSach());

        txtTimKiem.addActionListener(e -> timKiemSach());

        btnXoa.addActionListener(e -> xacNhanXoa());
    }

    // Tạo nút theo phong cách ThemSachPanel
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
        button.setEnabled(true);

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

    // ===== Tìm kiếm sách =====
    private void timKiemSach() {
        String tuKhoa = txtTimKiem.getText().trim();

        try {
            List<XoaSachItem> ketQua =
                    controller.timSachCanXoa(tuKhoa);

            hienThiDanhSach(ketQua);

            if (ketQua.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Không tìm thấy cuốn sách phù hợp.",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Không thể tìm sách trong database.\n"
                    + "Chi tiết: " + ex.getMessage(),
                    "Lỗi tìm kiếm",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ===== Hiển thị danh sách sách tìm được =====
    private void hienThiDanhSach(List<XoaSachItem> danhSach) {
        pnlDanhSach.removeAll();
        danhSachCheckbox.clear();
        danhSachMaSach.clear();

        for (XoaSachItem sach : danhSach) {
            JCheckBox chk = new JCheckBox();
            chk.setBackground(Color.WHITE);
            chk.setFocusPainted(false);
            chk.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JPanel pnlThongTin = new JPanel();
            pnlThongTin.setLayout(
                    new BoxLayout(pnlThongTin, BoxLayout.Y_AXIS)
            );
            pnlThongTin.setBackground(Color.WHITE);

            pnlThongTin.add(
                    taoDong("Mã sách: ", sach.getMaSach())
            );
            pnlThongTin.add(
                    taoDong("Tên sách: ", sach.getTenSach())
            );
            pnlThongTin.add(
                    taoDong("Tác giả: ", sach.getTenTacGia())
            );
            pnlThongTin.add(
                    taoDong("Thể loại: ", sach.getTenTheLoai())
            );
            pnlThongTin.add(
                    taoDong("Nhà xuất bản: ", sach.getTenNXB())
            );
            pnlThongTin.add(
                    taoDong(
                            "Số lượng trong kho: ",
                            String.valueOf(sach.getSoLuong())
                    )
            );

            JPanel pnlMotSach = new JPanel(
                    new BorderLayout(12, 0)
            );
            pnlMotSach.setBackground(Color.WHITE);
            pnlMotSach.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(BORDER),
                            new EmptyBorder(12, 12, 12, 12)
                    )
            );

            pnlMotSach.add(chk, BorderLayout.WEST);
            pnlMotSach.add(pnlThongTin, BorderLayout.CENTER);
            pnlMotSach.setAlignmentX(Component.LEFT_ALIGNMENT);
            pnlMotSach.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 190)
            );

            pnlDanhSach.add(pnlMotSach);
            pnlDanhSach.add(Box.createVerticalStrut(8));

            danhSachCheckbox.add(chk);
            danhSachMaSach.add(sach.getMaSach());
        }

        boolean coKetQua = !danhSach.isEmpty();

        scrollDanhSach.setVisible(coKetQua);
        btnXoa.setVisible(coKetQua);

        pnlDanhSach.revalidate();
        pnlDanhSach.repaint();

        revalidate();
        repaint();
    }

    // Tạo một dòng thông tin sách
    private JLabel taoDong(String nhan, String giaTri) {
        JLabel lbl = new JLabel(
                nhan + (giaTri == null ? "" : giaTri)
        );

        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(TEXT);
        lbl.setBorder(new EmptyBorder(2, 0, 2, 0));

        return lbl;
    }

    // ===== Xác nhận và xử lý xóa =====
    private void xacNhanXoa() {
        List<String> maSachCanXoa = new ArrayList<>();

        for (int i = 0; i < danhSachCheckbox.size(); i++) {
            if (danhSachCheckbox.get(i).isSelected()) {
                maSachCanXoa.add(danhSachMaSach.get(i));
            }
        }

        if (maSachCanXoa.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn ít nhất một cuốn sách cần xóa.",
                    "Chưa chọn sách",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int luaChon = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn xóa "
                + maSachCanXoa.size()
                + " cuốn sách đã chọn không?\n"
                + "Thao tác này sẽ xóa dữ liệu sách khỏi hệ thống.",
                "Xác nhận xóa sách",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (luaChon != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            controller.xoaSach(maSachCanXoa);

            JOptionPane.showMessageDialog(
                    this,
                    "Đã xóa sách thành công.",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Tìm lại theo từ khóa đang nhập
            timKiemSach();

        } catch (SQLException ex) {
            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Không thể xóa sách.\n"
                    + "Có thể sách đang được tham chiếu bởi dữ liệu "
                    + "mượn/trả hoặc chi tiết mượn/trả.\n\n"
                    + "Chi tiết: " + ex.getMessage(),
                    "Lỗi xóa sách",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
