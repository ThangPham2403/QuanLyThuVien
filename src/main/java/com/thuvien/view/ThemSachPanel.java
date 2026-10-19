
package com.thuvien.view;

import com.thuvien.controller.SachController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class ThemSachPanel extends JPanel {

    private final SachController controller;

    private JTextField txtMaSach;
    private JTextField txtTenSach;
    private JTextField txtTacGia;
    private JTextField txtTheLoai;
    private JTextField txtNXB;
    private JTextField txtSoLuong;

    private final Color GREEN = new Color(42, 157, 85);
    private final Color GREEN_HOVER = new Color(32, 130, 69);
    private final Color ORANGE = new Color(217, 119, 6);
    private final Color ORANGE_HOVER = new Color(184, 96, 3);
    private final Color BACKGROUND = new Color(244, 246, 249);
    private final Color TEXT = new Color(48, 56, 70);
    private final Color BORDER = new Color(221, 227, 235);

    public ThemSachPanel() {
        this(new SachController());
    }

    public ThemSachPanel(SachController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 20));
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Tiêu đề
        JLabel lblTitle = new JLabel("Thêm Sách Mới Vào Thư Viện");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblTitle.setForeground(TEXT);
        add(lblTitle, BorderLayout.NORTH);

        // Form nhập thông tin
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(24, 30, 24, 30)
        ));

        txtMaSach = createTextField();
        txtTenSach = createTextField();
        txtTacGia = createTextField();
        txtTheLoai = createTextField();
        txtNXB = createTextField();
        txtSoLuong = createTextField();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        addFormRow(formPanel, gbc, 0, "Mã sách", txtMaSach);
        addFormRow(formPanel, gbc, 1, "Tên sách", txtTenSach);
        addFormRow(formPanel, gbc, 2, "Tác giả", txtTacGia);
        addFormRow(formPanel, gbc, 3, "Thể loại", txtTheLoai);
        addFormRow(formPanel, gbc, 4, "Nhà xuất bản", txtNXB);
        addFormRow(formPanel, gbc, 5, "Số lượng kho", txtSoLuong);

        // Gợi ý cho người dùng
        JLabel lblHint = new JLabel(
                "<html><i>Nhập tên tác giả, thể loại và nhà xuất bản. "
                + "Nếu chưa tồn tại, hệ thống sẽ tạo mới.</i></html>"
        );
        lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblHint.setForeground(new Color(100, 110, 125));

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1;
        gbc.insets = new Insets(4, 10, 10, 10);
        formPanel.add(lblHint, gbc);

        // Các nút
        JButton btnThemSach = new JButton("Thêm sách vào hệ thống");
        JButton btnNhapLai = new JButton("Nhập lại");

        styleButton(btnThemSach, GREEN, GREEN_HOVER, 230, 44);
        styleButton(btnNhapLai, ORANGE, ORANGE_HOVER, 130, 44);

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 12, 0)
        );
        buttonPanel.setBackground(BACKGROUND);
        buttonPanel.add(btnThemSach);
        buttonPanel.add(btnNhapLai);

        JPanel centerPanel = new JPanel(new BorderLayout(0, 20));
        centerPanel.setBackground(BACKGROUND);
        centerPanel.add(formPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Sự kiện
        btnThemSach.addActionListener(e -> xuLyThemSach());
        btnNhapLai.addActionListener(e -> nhapLai());
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(TEXT);
        field.setBackground(Color.WHITE);
        field.setPreferredSize(new Dimension(250, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(210, 218, 229)
                ),
                new EmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JComponent component) {

        gbc.gridy = row;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        component.setPreferredSize(new Dimension(250, 40));
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
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(8, 12, 8, 12));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }

    private void xuLyThemSach() {
        String maSach = txtMaSach.getText().trim();
        String tenSach = txtTenSach.getText().trim();
        String tenTacGia = txtTacGia.getText().trim();
        String tenTheLoai = txtTheLoai.getText().trim();
        String tenNXB = txtNXB.getText().trim();
        String soLuongText = txtSoLuong.getText().trim();

        if (maSach.isEmpty()) {
            baoLoi("Bạn chưa nhập mã sách.", txtMaSach);
            return;
        }

        if (maSach.length() > 20) {
            baoLoi("Mã sách không được vượt quá 20 ký tự.", txtMaSach);
            return;
        }

        if (tenSach.isEmpty()) {
            baoLoi("Bạn chưa nhập tên sách.", txtTenSach);
            return;
        }

        if (tenSach.length() > 255) {
            baoLoi("Tên sách không được vượt quá 255 ký tự.", txtTenSach);
            return;
        }

        if (tenTacGia.isEmpty()) {
            baoLoi("Bạn chưa nhập tên tác giả.", txtTacGia);
            return;
        }

        if (tenTacGia.length() > 255) {
            baoLoi("Tên tác giả không được vượt quá 255 ký tự.", txtTacGia);
            return;
        }

        if (tenTheLoai.isEmpty()) {
            baoLoi("Bạn chưa nhập tên thể loại.", txtTheLoai);
            return;
        }

        if (tenTheLoai.length() > 255) {
            baoLoi("Tên thể loại không được vượt quá 255 ký tự.", txtTheLoai);
            return;
        }

        if (tenNXB.isEmpty()) {
            baoLoi("Bạn chưa nhập tên nhà xuất bản.", txtNXB);
            return;
        }

        if (tenNXB.length() > 255) {
            baoLoi("Tên nhà xuất bản không được vượt quá 255 ký tự.", txtNXB);
            return;
        }

        if (soLuongText.isEmpty()) {
            baoLoi("Bạn chưa nhập số lượng kho.", txtSoLuong);
            return;
        }

        int soLuong;
        try {
            soLuong = Integer.parseInt(soLuongText);
        } catch (NumberFormatException ex) {
            baoLoi("Số lượng phải là số nguyên.", txtSoLuong);
            return;
        }

        if (soLuong < 0) {
            baoLoi("Số lượng không được nhỏ hơn 0.", txtSoLuong);
            return;
        }

        try {
            controller.themSach(
                    maSach,
                    tenSach,
                    tenTacGia,
                    tenTheLoai,
                    tenNXB,
                    soLuong
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Thêm sách thành công!\n"
                    + "Mã sách: " + maSach
                    + "\nTên sách: " + tenSach,
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
            );

            nhapLai();

        } catch (SQLException ex) {
            ex.printStackTrace();

            String message = "Không thể thêm sách vào database.";

            if (ex.getErrorCode() == 2627
                    || ex.getErrorCode() == 2601) {
                message = "Mã sách đã tồn tại. Vui lòng nhập mã khác.";
            } else {
                message += "\nChi tiết: " + ex.getMessage();
            }

            JOptionPane.showMessageDialog(
                    this,
                    message,
                    "Lỗi thêm sách",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void baoLoi(String message, JComponent component) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Thông tin chưa hợp lệ",
                JOptionPane.WARNING_MESSAGE
        );
        component.requestFocusInWindow();
    }

    private void nhapLai() {
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtTacGia.setText("");
        txtTheLoai.setText("");
        txtNXB.setText("");
        txtSoLuong.setText("");
        txtMaSach.requestFocusInWindow();
    }
}
