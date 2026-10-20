
package com.thuvien.view;

import com.thuvien.controller.SachDangMuonController;
import com.thuvien.model.SachDangMuonModel;

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

public class DangMuon extends JPanel {

    private final SachDangMuonController sachController;
    private DefaultTableModel tableModel;
    private JTable tblDangMuon;

    private final Color mauNen = new Color(245, 247, 250);
    private final Color mauChu = new Color(44, 62, 80);
    private final Color mauXanh = new Color(39, 150, 87);

    public DangMuon() {
        sachController = new SachDangMuonController();

        initComponentsCustom();
        loadSachDangMuon();
    }

    private void initComponentsCustom() {
        setLayout(new BorderLayout(15, 20));
        setBackground(mauNen);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Phần tiêu đề và nút
        JPanel pnlTop = new JPanel(new BorderLayout(10, 10));
        pnlTop.setOpaque(false);

        JLabel lblTitle = new JLabel("Sách đang được mượn");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(mauChu);

        JButton btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLamMoi.setBackground(mauXanh);
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setFocusPainted(false);
        btnLamMoi.setOpaque(true);
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLamMoi.setPreferredSize(new Dimension(120, 38));
        btnLamMoi.addActionListener(e -> loadSachDangMuon());

        pnlTop.add(lblTitle, BorderLayout.CENTER);
        pnlTop.add(btnLamMoi, BorderLayout.EAST);

        add(pnlTop, BorderLayout.NORTH);

        // Bảng dữ liệu
        String[] headers = {
            "Mã Mượn Trả",
            "Số điện thoại",
            "Thời gian mượn",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblDangMuon = new JTable(tableModel);
        tblDangMuon.setRowHeight(34);
        tblDangMuon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblDangMuon.setForeground(mauChu);
        tblDangMuon.setSelectionBackground(new Color(220, 237, 227));
        tblDangMuon.setSelectionForeground(mauChu);
        tblDangMuon.setShowGrid(true);
        tblDangMuon.setGridColor(new Color(225, 229, 235));
        tblDangMuon.setFillsViewportHeight(true);

        tblDangMuon.getTableHeader().setFont(
            new Font("Segoe UI", Font.BOLD, 14)
        );
        tblDangMuon.getTableHeader().setBackground(mauChu);
        tblDangMuon.getTableHeader().setForeground(Color.WHITE);
        tblDangMuon.getTableHeader().setOpaque(true);
        tblDangMuon.getTableHeader().setPreferredSize(
            new Dimension(0, 40)
        );

        DefaultTableCellRenderer centerRenderer =
            new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblDangMuon.getColumnCount(); i++) {
            tblDangMuon.getColumnModel()
                .getColumn(i)
                .setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblDangMuon);
        scrollPane.setBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230))
        );

        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadSachDangMuon() {
        tableModel.setRowCount(0);

        try {
            List<SachDangMuonModel> danhSach =
                sachController.laySachDangMuon();

            for (SachDangMuonModel sach : danhSach) {
                tableModel.addRow(new Object[]{
                    sach.getIdSach(),
                    sach.getSoDienThoai(),
                    sach.getHanTra(),
                    sach.getTrangThai()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Lỗi tải danh sách sách đang mượn:\n" + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }
}
