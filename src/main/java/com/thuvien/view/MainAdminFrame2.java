package com.thuvien.view;


import com.thuvien.view.ThemSachPanel;
import com.thuvien.view.TimSachPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MainAdminFrame2 extends JFrame {

    // =========================
    // 1. MÀU SẮC GIAO DIỆN
    // =========================

    private final Color BLUE = new Color(36, 87, 167);
    private final Color BLUE_DARK = new Color(23, 70, 138);

    private final Color GREEN = new Color(62, 128, 84);
    private final Color GREEN_LIGHT = new Color(232, 243, 235);

    private final Color BACKGROUND = new Color(244, 246, 249);
    private final Color WHITE = Color.WHITE;

    private final Color TEXT = new Color(48, 56, 70);
    private final Color TEXT_LIGHT = new Color(105, 115, 130);

    private final Color BORDER = new Color(221, 227, 235);

    // =========================
    // 2. CÁC PANEL CHÍNH
    // =========================

    private JPanel headerPanel;
    private JPanel sidebarPanel;
    private JPanel menuContainer;
    private JPanel contentPanel;

    private JTextField txtSearch;

    private JLabel lblUsername;

    private JButton selectedButton;

    // =========================
    // 3. CONSTRUCTOR
    // =========================

    public MainAdminFrame2() {

        initFrame();

        initComponents();

    }

    // =========================
    // 4. KHỞI TẠO CỬA SỔ
    // =========================

    private void initFrame() {

        setTitle("Hệ Thống Quản Lý Thư Viện");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1366, 768);

        setMinimumSize(new Dimension(1100, 650));

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        getContentPane().setBackground(BACKGROUND);

    }

    // =========================
    // 5. KHỞI TẠO GIAO DIỆN
    // =========================

    private void initComponents() {

        // Header nằm phía trên

        headerPanel = createHeader();

        add(headerPanel, BorderLayout.NORTH);

        // Panel chứa sidebar và vùng nội dung

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        // Sidebar bên trái

        sidebarPanel = createSidebar();

        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        // Vùng nội dung bên phải

        contentPanel = new JPanel(new BorderLayout());

        contentPanel.setBackground(BACKGROUND);

        contentPanel.setBorder(
                new EmptyBorder(0, 0, 0, 0)
        );

        // Ban đầu không thêm nội dung nào

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

    }

    // =========================
    // 6. TẠO HEADER
    // =========================

    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(WHITE);

        header.setPreferredSize(new Dimension(0, 70));

        header.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 1, 0, BORDER
                )
        );

        // ----- BÊN TRÁI HEADER -----

        JPanel leftPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        20,
                        0
                )
        );

        leftPanel.setBackground(WHITE);

        leftPanel.setBorder(
                new EmptyBorder(20, 0, 0, 0)
        );

        JLabel lblSystem = new JLabel(
                "▣  Hệ Thống Quản Lý Thư Viện"
        );

        lblSystem.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        lblSystem.setForeground(TEXT);

        leftPanel.add(lblSystem);

        header.add(leftPanel, BorderLayout.WEST);

        // ----- GIỮA HEADER -----

        JPanel centerPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        0,
                        0
                )
        );

        centerPanel.setBackground(WHITE);

        centerPanel.setBorder(
                new EmptyBorder(15, 0, 0, 0)
        );

        txtSearch = new JTextField();

        txtSearch.setPreferredSize(
                new Dimension(400, 40)
        );

        txtSearch.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        txtSearch.setText("Tìm kiếm chức năng...");

        txtSearch.setForeground(TEXT_LIGHT);

        txtSearch.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1,
                                true
                        ),
                        new EmptyBorder(5, 15, 5, 15)
                )
        );

        centerPanel.add(txtSearch);

        header.add(centerPanel, BorderLayout.CENTER);

        // ----- BÊN PHẢI HEADER -----

        JPanel rightPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        15,
                        0
                )
        );

        rightPanel.setBackground(WHITE);

        rightPanel.setBorder(
                new EmptyBorder(18, 0, 0, 15)
        );

        JLabel lblUserIcon = new JLabel("●");

        lblUserIcon.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        lblUserIcon.setForeground(TEXT_LIGHT);

        rightPanel.add(lblUserIcon);

        lblUsername = new JLabel("admin");

        lblUsername.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        lblUsername.setForeground(TEXT);

        rightPanel.add(lblUsername);

        JButton btnLogout = new JButton("Logout");

        btnLogout.setPreferredSize(
                new Dimension(85, 34)
        );

        btnLogout.setBackground(GREEN);

        btnLogout.setForeground(WHITE);

        btnLogout.setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );

        btnLogout.setFocusPainted(false);

        btnLogout.setBorderPainted(false);

        btnLogout.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        btnLogout.addActionListener(e -> {

            int choice = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn đăng xuất?",
                    "Xác nhận đăng xuất",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            if (choice == javax.swing.JOptionPane.YES_OPTION) {

                dispose();

                // Khi tích hợp LoginView, mở lại màn hình đăng nhập
                // tại đây.

            }

        });

        rightPanel.add(btnLogout);

        header.add(rightPanel, BorderLayout.EAST);

        return header;

    }

    // =========================
    // 7. TẠO SIDEBAR
    // =========================

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel(new BorderLayout());

        sidebar.setBackground(WHITE);

        sidebar.setPreferredSize(
                new Dimension(260, 0)
        );

        sidebar.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 0, 1, BORDER
                )
        );

        // Chứa các nhóm menu theo chiều dọc

        menuContainer = new JPanel();

        menuContainer.setLayout(
                new BoxLayout(
                        menuContainer,
                        BoxLayout.Y_AXIS
                )
        );

        menuContainer.setBackground(WHITE);

        menuContainer.setBorder(
                new EmptyBorder(15, 8, 15, 8)
        );

        // Nhóm 1

        addMenuGroup(
                "📚  Dịch vụ sách",
                new String[]{
                    "  Tìm sách",
                    "  Thêm sách",
                    "  Xóa sách",
                    "  Sửa thông tin sách"
                }
        );

        // Nhóm 2

        addMenuGroup(
                "📋  Mượn / Trả sách",
                new String[]{
                    "  Mượn sách",
                    "  Trả sách"
                }
        );

        // Nhóm 3

        addMenuGroup(
                "📊  Thống kê và báo cáo",
                new String[]{
                    "  Tổng quan thống kê",
                    "  Sách đang được mượn",
                    "  Sách quá hạn",
                    "  Xuất báo cáo"
                }
        );

        // Nhóm 4

        addMenuGroup(
                "⚙️  Quản lý hệ thống",
                new String[]{
                    "  Thêm tài khoản",
                    "  Quản lý tài khoản"
                }
        );

        JScrollPane scrollPane = new JScrollPane(
                menuContainer
        );

        scrollPane.setBorder(null);

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        scrollPane.setBackground(WHITE);

        sidebar.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return sidebar;

    }

    // =========================
    // 8. TẠO NHÓM MENU CHA
    // =========================

    private void addMenuGroup(
            String groupName,
            String[] children
    ) {

        // Panel của một nhóm menu

        JPanel groupPanel = new JPanel();

        groupPanel.setLayout(
                new BoxLayout(
                        groupPanel,
                        BoxLayout.Y_AXIS
                )
        );

        groupPanel.setBackground(WHITE);

        groupPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // Nút menu cha

        JButton parentButton = createMenuButton(
                "▾  " + groupName,
                false
        );

        parentButton.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        // Panel chứa các nút menu con

        JPanel childPanel = new JPanel();

        childPanel.setLayout(
                new BoxLayout(
                        childPanel,
                        BoxLayout.Y_AXIS
                )
        );

        childPanel.setBackground(WHITE);

        childPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        for (String childName : children) {

            JButton childButton = createMenuButton(
                    childName,
                    true
            );

//            childButton.addActionListener(e -> {
//
//                selectMenuButton(childButton);
//
//                String functionName = removeIcon(childName);
//
//                // Tạm thời chỉ hiển thị thông báo.
//                // Khi tích hợp chức năng thật, thay đoạn này
//                // bằng lời gọi showContent(panel).
//
//                System.out.println(
//                        "Đã chọn chức năng: " + functionName
//                );
//
//            });

            childButton.addActionListener(e -> {

            selectMenuButton(childButton);

            String functionName = removeIcon(childName).trim();

            switch (functionName) {

                case "Tìm sách":
                    showContent(new TimSachPanel());
                    break;

                case "Thêm sách":
                    showContent(new ThemSachPanel());
                    break;

                case "Xóa sách":
                    showContent(new XoaSachPanel());
                    break;
                
                case "Sửa thông tin sách":
                    showContent(new SuaSachPanel());
                    break;
                    
                case "Mượn sách":
                    showContent(new QuanLyMuonTraPanel());
                    break;
                    
                case "Trả sách":
                    showContent(new QuanLyTraSachPanel());
                    break;
                    
                
                    
                    
                default:
                    // Các chức năng khác tích hợp sau
                    break;
            }
        });

            childPanel.add(childButton);

        }

        // Mặc định mở các nhóm menu

        childPanel.setVisible(true);

        // Nhấn menu cha để đóng/mở menu con

        parentButton.addActionListener(e -> {

            boolean visible = childPanel.isVisible();

            childPanel.setVisible(!visible);

            parentButton.setText(
                    (visible ? "▸  " : "▾  ")
                    + groupName.substring(3)
            );

            menuContainer.revalidate();

            menuContainer.repaint();

        });

        groupPanel.add(parentButton);

        groupPanel.add(childPanel);

        groupPanel.add(
                Box.createVerticalStrut(8)
        );

        menuContainer.add(groupPanel);

    }

    // =========================
    // 9. TẠO NÚT MENU
    // =========================

    private JButton createMenuButton(
            String text,
            boolean isChild
    ) {

        JButton button = new JButton(text);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        isChild ? 40 : 44
                )
        );

        button.setPreferredSize(
                new Dimension(
                        240,
                        isChild ? 40 : 44
                )
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        isChild ? Font.PLAIN : Font.BOLD,
                        isChild ? 13 : 13
                )
        );

        button.setForeground(TEXT);

        button.setBackground(WHITE);

        button.setBorder(
                new EmptyBorder(
                        5,
                        isChild ? 28 : 10,
                        5,
                        5
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setContentAreaFilled(true);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return button;

    }

    // =========================
    // 10. ĐÁNH DẤU MENU ĐANG CHỌN
    // =========================

    private void selectMenuButton(
            JButton button
    ) {

        if (selectedButton != null) {

            selectedButton.setBackground(WHITE);

            selectedButton.setForeground(TEXT);

        }

        selectedButton = button;

        selectedButton.setBackground(GREEN_LIGHT);

        selectedButton.setForeground(GREEN);

    }

    // =========================
    // 11. LẤY TÊN CHỨC NĂNG
    // =========================

    private String removeIcon(String text) {

        int index = text.indexOf("  ");

        if (index >= 0) {

            return text.substring(index + 2).trim();

        }

        return text.trim();

    }

    // =========================
    // 12. THAY ĐỔI VÙNG NỘI DUNG
    // =========================

    public void showContent(JPanel panel) {

        if (panel == null) {

            return;

        }

        contentPanel.removeAll();

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();

    }

    // =========================
    // 13. CẬP NHẬT TÊN TÀI KHOẢN
    // =========================

    public void setUsername(String username) {

        if (username != null && !username.trim().isEmpty()) {

            lblUsername.setText(username);

        }

    }

    // =========================
    // 14. HÀM MAIN ĐỂ CHẠY THỬ
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );

            } catch (Exception e) {

                e.printStackTrace();

            }

            MainAdminFrame2 frame = new MainAdminFrame2();

            frame.setVisible(true);

        });

    }

}