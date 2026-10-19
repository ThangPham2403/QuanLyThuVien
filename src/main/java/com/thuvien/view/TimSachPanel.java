package com.thuvien.view;

import com.thuvien.controller.SachController;
import com.thuvien.model.SachHienThi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TimSachPanel extends JPanel {

    private final SachController controller;

    private JTextField txtKeyword;
    private JTable tblSach;
    private DefaultTableModel tableModel;

    // Màu giao diện
    private final Color BLUE =
            new Color(36, 87, 167);

    private final Color BLUE_DARK =
            new Color(37, 54, 74);

    private final Color BLUE_HOVER =
            new Color(28, 70, 140);

    private final Color ORANGE =
            new Color(217, 119, 6);

    private final Color ORANGE_HOVER =
            new Color(184, 96, 3);

    private final Color BACKGROUND =
            new Color(244, 246, 249);

    private final Color TEXT =
            new Color(48, 56, 70);

    private final Color BORDER =
            new Color(221, 227, 235);

    private final Color ROW_LINE =
            new Color(231, 235, 241);

    public TimSachPanel() {
        this(new SachController());
    }

    public TimSachPanel(SachController controller) {

        this.controller = controller;

        initComponents();

        // Tự động tải sách khi mở chức năng
        taiTatCaSach();
    }

    // =================================================
    // 1. KHỞI TẠO GIAO DIỆN
    // =================================================

    private void initComponents() {

        setLayout(new BorderLayout(0, 20));
        setBackground(BACKGROUND);

        // Tạo khoảng cách với mép vùng nội dung
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // ---------------------------------------------
        // KHU VỰC TRÊN: TIÊU ĐỀ VÀ TÌM KIẾM
        // ---------------------------------------------

        JPanel topPanel = new JPanel();
        topPanel.setLayout(
                new BoxLayout(topPanel, BoxLayout.Y_AXIS)
        );

        topPanel.setBackground(BACKGROUND);

        // Tiêu đề
        JLabel lblTitle = new JLabel(
                "Tra Cứu & Danh Sách Thư Viện"
        );

        lblTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 25)
        );

        lblTitle.setForeground(TEXT);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        topPanel.add(lblTitle);

        // Khoảng cách giữa tiêu đề và khu vực tìm kiếm
        topPanel.add(Box.createVerticalStrut(20));

        // ---------------------------------------------
        // THANH TÌM KIẾM
        // ---------------------------------------------

        JPanel searchPanel = new JPanel(
                new BorderLayout(12, 0)
        );

        searchPanel.setBackground(Color.WHITE);

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        new EmptyBorder(10, 12, 10, 12)
                )
        );

        // Ô nhập từ khóa
        txtKeyword = new JTextField();

        txtKeyword.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        txtKeyword.setForeground(TEXT);
        txtKeyword.setBackground(Color.WHITE);

        txtKeyword.setPreferredSize(
                new Dimension(250, 40)
        );

        txtKeyword.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 218, 229)
                        ),
                        new EmptyBorder(5, 10, 5, 10)
                )
        );

        txtKeyword.setToolTipText(
                "Nhập mã sách, tên sách hoặc tên tác giả"
        );

        searchPanel.add(
                txtKeyword,
                BorderLayout.CENTER
        );

        // Nhóm nút
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 10, 0)
        );

        buttonPanel.setBackground(Color.WHITE);

        // Nút tìm kiếm
        JButton btnTimKiem = new JButton("Tìm kiếm");

        styleButton(
                btnTimKiem,
                BLUE,
                BLUE_HOVER,
                125,
                40
        );

        // Nút làm mới
        JButton btnLamMoi = new JButton("Làm mới / Tất cả");

        styleButton(
                btnLamMoi,
                ORANGE,
                ORANGE_HOVER,
                165,
                40
        );

        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnLamMoi);

        searchPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );

        searchPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // Đảm bảo thanh tìm kiếm chiếm hết chiều ngang
        searchPanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 64)
        );

        topPanel.add(searchPanel);

        add(topPanel, BorderLayout.NORTH);

        // ---------------------------------------------
        // KHU VỰC BẢNG SÁCH
        // ---------------------------------------------

        String[] columns = {
            "STT",
            "Mã sách",
            "Tên sách",
            "Tác giả",
            "Thể loại",
            "Nhà xuất bản",
            "Số lượng"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row, int column) {

                return false;
            }
        };

        tblSach = new JTable(tableModel);

        tblSach.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        tblSach.setRowHeight(38);

        tblSach.setBackground(Color.WHITE);
        tblSach.setForeground(TEXT);

        tblSach.setSelectionBackground(
                new Color(220, 234, 252)
        );

        tblSach.setSelectionForeground(TEXT);

        tblSach.setGridColor(ROW_LINE);

        tblSach.setShowVerticalLines(true);
        tblSach.setShowHorizontalLines(true);

        tblSach.setFillsViewportHeight(true);

        tblSach.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS
        );

        // ---------------------------------------------
        // CHỈNH TIÊU ĐỀ BẢNG
        // ---------------------------------------------

        JTableHeader header = tblSach.getTableHeader();

        header.setPreferredSize(
                new Dimension(0, 44)
        );

        header.setReorderingAllowed(false);

        // Renderer riêng: ép nền xanh và chữ trắng
        DefaultTableCellRenderer headerRenderer =
                new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                JLabel label = (JLabel) super
                        .getTableCellRendererComponent(
                                table,
                                value,
                                false,
                                false,
                                row,
                                column
                        );

                label.setOpaque(true);

                // Căn giữa tiêu đề tất cả các cột
                label.setHorizontalAlignment(
                        SwingConstants.CENTER
                );

                // Ép màu nền và màu chữ
                label.setBackground(BLUE_DARK);
                label.setForeground(Color.WHITE);

                label.setFont(
                        new Font("Segoe UI", Font.BOLD, 13)
                );

                // Viền nhẹ giữa các tiêu đề
                label.setBorder(
                        BorderFactory.createMatteBorder(
                                0, 0, 1, 1,
                                new Color(72, 88, 108)
                        )
                );

                return label;
            }
        };

        // Dòng này rất quan trọng:
        // sử dụng renderer vừa tạo cho toàn bộ header
        header.setDefaultRenderer(headerRenderer);

        header.setBackground(BLUE_DARK);
        header.setForeground(Color.WHITE);

        // Căn giữa STT, mã sách và số lượng trong phần dữ liệu
        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        tblSach.getColumnModel()
                .getColumn(0)
                .setCellRenderer(centerRenderer);

        tblSach.getColumnModel()
                .getColumn(1)
                .setCellRenderer(centerRenderer);

        tblSach.getColumnModel()
                .getColumn(6)
                .setCellRenderer(centerRenderer);

        // Độ rộng ưu tiên cho từng cột
        tblSach.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(48);

        tblSach.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(85);

        tblSach.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(210);

        tblSach.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(150);

        tblSach.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(155);

        tblSach.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(190);

        tblSach.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(85);

        // ---------------------------------------------
        // SCROLL PANE VÀ KHOẢNG CÁCH VỚI THANH TÌM KIẾM
        // ---------------------------------------------

        JScrollPane scrollPane = new JScrollPane(tblSach);

        scrollPane.setBackground(Color.WHITE);

        scrollPane.getViewport().setBackground(Color.WHITE);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(BORDER)
        );

        // Đặt bảng vào một panel riêng
        JPanel tablePanel = new JPanel(
                new BorderLayout()
        );

        tablePanel.setBackground(BACKGROUND);

        // Tạo khoảng cách phía trên bảng
        tablePanel.setBorder(
                new EmptyBorder(4, 0, 0, 0)
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(tablePanel, BorderLayout.CENTER);

        // =================================================
        // 2. XỬ LÝ SỰ KIỆN
        // =================================================

        btnTimKiem.addActionListener(e -> timKiemSach());

        btnLamMoi.addActionListener(e -> {
            txtKeyword.setText("");
            taiTatCaSach();
        });

        // Nhấn Enter cũng tìm kiếm
        txtKeyword.addActionListener(e -> timKiemSach());
    }

    // =================================================
    // 3. ĐỊNH DẠNG NÚT BẤM
    // =================================================

    private void styleButton(
            JButton button,
            Color normalColor,
            Color hoverColor,
            int width,
            int height) {

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        button.setForeground(Color.WHITE);
        button.setBackground(normalColor);

        button.setPreferredSize(
                new Dimension(width, height)
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setBorder(
                new EmptyBorder(8, 12, 8, 12)
        );

        // Hiệu ứng khi di chuột
        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e) {

                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e) {

                button.setBackground(normalColor);
            }
        });
    }

    // =================================================
    // 4. TẢI TẤT CẢ SÁCH
    // =================================================

    private void taiTatCaSach() {

        try {

            List<SachHienThi> danhSach =
                    controller.getAllSach();

            hienThiSach(danhSach);

        } catch (SQLException ex) {

            hienThiLoi(
                    "Không thể tải danh sách sách.",
                    ex
            );
        }
    }

    // =================================================
    // 5. TÌM KIẾM SÁCH
    // =================================================

    private void timKiemSach() {

        String keyword = txtKeyword.getText().trim();

        try {

            List<SachHienThi> danhSach =
                    controller.timKiemSach(keyword);

            hienThiSach(danhSach);

            if (danhSach.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Không tìm thấy sách phù hợp.",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (SQLException ex) {

            hienThiLoi(
                    "Tìm kiếm sách thất bại.",
                    ex
            );
        }
    }

    // =================================================
    // 6. ĐỔ DỮ LIỆU VÀO JTABLE
    // =================================================

    private void hienThiSach(
            List<SachHienThi> danhSach) {

        tableModel.setRowCount(0);

        int stt = 1;

        for (SachHienThi sach : danhSach) {

            Object[] row = {
                stt++,
                sach.getSachId(),
                sach.getSachName(),
                sach.getTacGiaName() == null
                        ? ""
                        : sach.getTacGiaName(),
                sach.getTheLoaiName() == null
                        ? ""
                        : sach.getTheLoaiName(),
                sach.getNxbName() == null
                        ? ""
                        : sach.getNxbName(),
                sach.getSoLuong()
            };

            tableModel.addRow(row);
        }
    }

    // =================================================
    // 7. HIỂN THỊ LỖI
    // =================================================

    private void hienThiLoi(
            String message,
            SQLException ex) {

        ex.printStackTrace();

        JOptionPane.showMessageDialog(
                this,
                message
                        + "\nChi tiết: "
                        + ex.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
        );
    }
}