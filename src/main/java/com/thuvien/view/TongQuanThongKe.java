
package com.thuvien.view;

import com.thuvien.controller.ThongKeController;
import com.thuvien.model.ThongKeModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TongQuanThongKe extends JPanel {

    private JLabel lblTongSach;
    private JLabel lblDangMuon;
    private JLabel lblQuaHan;
    private JLabel lblTongDocGia;

    private JButton btnLamMoi;
    private JButton btnXuatBaoCao;

    private final ThongKeController controller;

    public TongQuanThongKe() {
        controller = new ThongKeController();

        initComponentsCustom();
        capNhatDuLieuTuDatabase();
    }

    private void initComponentsCustom() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 250));
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // ================= HEADER =================
        JPanel pnlHeader = new JPanel(new BorderLayout(15, 10));
        pnlHeader.setOpaque(false);

        // Tiêu đề và mô tả
        JLabel lblTitle = new JLabel("TỔNG QUAN THỐNG KÊ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(44, 62, 80));

        JLabel lblMoTa = new JLabel(
                "Theo dõi tình hình sách và độc giả trong thư viện");
        lblMoTa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMoTa.setForeground(new Color(110, 120, 130));

        JPanel pnlTieuDe = new JPanel(new BorderLayout(0, 6));
        pnlTieuDe.setOpaque(false);
        pnlTieuDe.add(lblTitle, BorderLayout.NORTH);
        pnlTieuDe.add(lblMoTa, BorderLayout.CENTER);

        // ================= NÚT LÀM MỚI =================
        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setBackground(new Color(0, 125, 190));
        btnLamMoi.setFocusPainted(false);
        btnLamMoi.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        btnLamMoi.setPreferredSize(new Dimension(120, 40));

        btnLamMoi.addActionListener(e ->
                capNhatDuLieuTuDatabase());

        // ================= NÚT XUẤT BÁO CÁO =================
        btnXuatBaoCao = new JButton("Xuất báo cáo");
        btnXuatBaoCao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnXuatBaoCao.setForeground(Color.WHITE);
        btnXuatBaoCao.setBackground(new Color(46, 125, 50));
        btnXuatBaoCao.setFocusPainted(false);
        btnXuatBaoCao.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        btnXuatBaoCao.setPreferredSize(new Dimension(150, 40));

        btnXuatBaoCao.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Xuất báo cáo thành công!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE
                ));

        // Gom hai nút về bên phải
        JPanel pnlNutChucNang = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlNutChucNang.setOpaque(false);
        pnlNutChucNang.add(btnLamMoi);
        pnlNutChucNang.add(btnXuatBaoCao);

        pnlHeader.add(pnlTieuDe, BorderLayout.CENTER);
        pnlHeader.add(pnlNutChucNang, BorderLayout.EAST);

        add(pnlHeader, BorderLayout.NORTH);

        // ================= BỐN THẺ THỐNG KÊ =================
        JPanel pnlCards = new JPanel(
                new GridLayout(2, 2, 20, 20));
        pnlCards.setOpaque(false);

        lblTongSach = taoLabelSoLieu();
        lblDangMuon = taoLabelSoLieu();
        lblQuaHan = taoLabelSoLieu();
        lblTongDocGia = taoLabelSoLieu();

        pnlCards.add(createCard(
                "Tổng đầu sách",
                lblTongSach,
                new Color(228, 241, 255),
                new Color(0, 39, 108)
        ));

        pnlCards.add(createCard(
                "Sách đang mượn",
                lblDangMuon,
                new Color(228, 255, 228),
                new Color(0, 102, 0)
        ));

        pnlCards.add(createCard(
                "Sách quá hạn",
                lblQuaHan,
                new Color(255, 228, 228),
                new Color(153, 0, 0)
        ));

        pnlCards.add(createCard(
                "Tổng độc giả",
                lblTongDocGia,
                new Color(252, 252, 226),
                new Color(176, 141, 0)
        ));

        add(pnlCards, BorderLayout.CENTER);
    }

    // Tạo nhãn hiển thị số liệu
    private JLabel taoLabelSoLieu() {
        JLabel label = new JLabel("0", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 38));
        return label;
    }

    // Tạo thẻ thống kê
    private JPanel createCard(
            String title,
            JLabel lblValue,
            Color bgColor,
            Color accentColor) {

        JPanel card = new JPanel(new BorderLayout(10, 15));
        card.setBackground(bgColor);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        accentColor, 1, true),
                new EmptyBorder(22, 22, 22, 22)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitle.setForeground(accentColor);

        lblValue.setForeground(accentColor);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);

        return card;
    }

    // ================= TẢI DỮ LIỆU THỐNG KÊ =================
    public void capNhatDuLieuTuDatabase() {
        try {
            ThongKeModel tk = controller.layThongKe();

            if (tk == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Không lấy được dữ liệu thống kê.",
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            lblTongSach.setText(
                    String.valueOf(tk.getTongDauSach()));

            lblDangMuon.setText(
                    String.valueOf(tk.getDangMuon()));

            lblQuaHan.setText(
                    String.valueOf(tk.getQuaHan()));

            lblTongDocGia.setText(
                    String.valueOf(tk.getTongDocGia()));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Lỗi khi tải thống kê:\n" + ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
