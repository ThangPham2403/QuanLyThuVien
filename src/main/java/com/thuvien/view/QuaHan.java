
package com.thuvien.view;

import com.thuvien.controller.SachQuaHanController;
import com.thuvien.model.SachQuaHanModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class QuaHan extends JPanel {

    private final SachQuaHanController sachQuaHanController;
    private DefaultTableModel tableModel;
    private JTable tblSachQuaHan;

    private final Color mauNen = new Color(245, 247, 250);
    private final Color mauDo = new Color(183, 28, 28);
    private final Color mauChu = new Color(44, 62, 80);

    public QuaHan() {
        sachQuaHanController = new SachQuaHanController();

        initComponentsCustom();
        loadDanhSachSachQuaHan();
    }

    private void initComponentsCustom() {
        setLayout(new BorderLayout(15, 20));
        setBackground(mauNen);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Phần tiêu đề và nút
        JPanel pnlTop = new JPanel(new BorderLayout(10, 10));
        pnlTop.setOpaque(false);

        JLabel lblTitle =
            new JLabel("Danh sách sách quá hạn chưa trả");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(mauDo);

        JButton btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLamMoi.setBackground(mauDo);
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setFocusPainted(false);
        btnLamMoi.setOpaque(true);
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLamMoi.setPreferredSize(new Dimension(120, 38));
        btnLamMoi.addActionListener(e -> loadDanhSachSachQuaHan());

        pnlTop.add(lblTitle, BorderLayout.CENTER);
        pnlTop.add(btnLamMoi, BorderLayout.EAST);

        add(pnlTop, BorderLayout.NORTH);

        // Bảng dữ liệu
        String[] headers = {
            "Mã Mượn Trả",
            "Số điện thoại",
            "Hạn trả",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblSachQuaHan = new JTable(tableModel);
        tblSachQuaHan.setRowHeight(34);
        tblSachQuaHan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblSachQuaHan.setForeground(mauChu);
        tblSachQuaHan.setSelectionBackground(new Color(255, 224, 224));
        tblSachQuaHan.setSelectionForeground(mauChu);
        tblSachQuaHan.setShowGrid(true);
        tblSachQuaHan.setGridColor(new Color(225, 229, 235));
        tblSachQuaHan.setFillsViewportHeight(true);

        tblSachQuaHan.getTableHeader().setFont(
            new Font("Segoe UI", Font.BOLD, 14)
        );
        tblSachQuaHan.getTableHeader().setBackground(mauDo);
        tblSachQuaHan.getTableHeader().setForeground(Color.WHITE);
        tblSachQuaHan.getTableHeader().setOpaque(true);
        tblSachQuaHan.getTableHeader().setPreferredSize(
            new Dimension(0, 40)
        );

        DefaultTableCellRenderer centerRenderer =
            new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblSachQuaHan.getColumnCount(); i++) {
            tblSachQuaHan.getColumnModel()
                .getColumn(i)
                .setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblSachQuaHan);
        scrollPane.setBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230))
        );

        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadDanhSachSachQuaHan() {
        tableModel.setRowCount(0);

        try {
            List<SachQuaHanModel> danhSach =
                sachQuaHanController.layDanhSachSachQuaHan();

            for (SachQuaHanModel sach : danhSach) {
                tableModel.addRow(new Object[]{
                    sach.getMaMuonTra(),
                    sach.getSoDienThoai(),
                    sach.getHanTra(),
                    sach.getTrangThai()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Lỗi tải danh sách sách quá hạn:\n" + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }
}
