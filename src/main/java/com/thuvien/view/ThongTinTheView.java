package com.thuvien.view;

import com.thuvien.utils.PhienDangNhap;
import com.thuvien.controller.ThongTinTheController;
import com.thuvien.model.ThongTinTheModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Form thông tin thẻ độc giả.
 *
 * Lớp này chỉ là JPanel để đặt vào vùng nội dung của giao diện DocGia.
 * Dữ liệu được lấy theo PhienDangNhap.taiKhoanId, không lấy cố định theo DG001.
 * Form cho phép sửa hai trường: họ tên và số điện thoại.
 */
public class ThongTinTheView extends JPanel {

    private static final Color XANH_CHU_DAO = new Color(38, 128, 74);
    private static final Color XANH_DAM = new Color(31, 111, 64);
    private static final Color NEN_FORM = new Color(244, 246, 248);
    private static final Color MAU_CHU = new Color(38, 45, 52);
    private static final Color MAU_VIEN = new Color(220, 225, 229);

    // Nội dung thẻ xanh và hai ô thống kê ở bên trái.
    private final JLabel lblTen = new JLabel("Đang tải thông tin...");
    private final JLabel lblMaThe = new JLabel("Mã thẻ:");
    private final JLabel lblSachDangMuon = new JLabel("0");
    private final JLabel lblLuotMuon = new JLabel("0");

    // Chỉ giữ lại hai trường cho phép độc giả chỉnh sửa.
    private final JTextField txtHoTen = new JTextField();
    private final JTextField txtSoDienThoai = new JTextField();
    private final JButton btnLuuThayDoi = new JButton("Lưu thay đổi");
    private final JLabel lblTrangThai = new JLabel(" ");

    // Controller xử lý nghiệp vụ; View chỉ nhận dữ liệu và cập nhật giao diện.
    private final ThongTinTheController controller = new ThongTinTheController();
    private String docGiaId;

    public ThongTinTheView() {
        setLayout(new BorderLayout(0, 18));
        setBackground(NEN_FORM);
        setBorder(new EmptyBorder(24, 26, 26, 26));

        add(taoTieuDe(), BorderLayout.NORTH);
        add(taoNoiDung(), BorderLayout.CENTER);

        btnLuuThayDoi.addActionListener(e -> luuThayDoi());

        // Tải hồ sơ của người đang đăng nhập ngay khi mở form.
        taiThongTinThe();
    }

    private JLabel taoTieuDe() {
        JLabel title = new JLabel("Thông tin thẻ");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(MAU_CHU);
        return title;
    }

    private JPanel taoNoiDung() {
        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);

        JPanel cotTrai = taoCotTrai();
        JPanel form = taoFormBenPhai();

        GridBagConstraints gbcTrai = new GridBagConstraints();
        gbcTrai.gridx = 0;
        gbcTrai.gridy = 0;
        gbcTrai.weightx = 0.44;
        gbcTrai.weighty = 1.0;
        gbcTrai.fill = GridBagConstraints.HORIZONTAL;
        gbcTrai.anchor = GridBagConstraints.NORTHWEST;
        gbcTrai.insets = new Insets(0, 0, 0, 18);
        content.add(cotTrai, gbcTrai);

        GridBagConstraints gbcPhai = new GridBagConstraints();
        gbcPhai.gridx = 1;
        gbcPhai.gridy = 0;
        gbcPhai.weightx = 0.56;
        gbcPhai.weighty = 1.0;
        gbcPhai.fill = GridBagConstraints.BOTH;
        gbcPhai.anchor = GridBagConstraints.NORTHWEST;
        gbcPhai.insets = new Insets(0, 0, 0, 0);
        content.add(form, gbcPhai);

        return content;
    }

    private JPanel taoCotTrai() {
        JPanel cotTrai = new JPanel();
        cotTrai.setOpaque(false);
        cotTrai.setLayout(new BoxLayout(cotTrai, BoxLayout.Y_AXIS));

        RoundedPanel theXanh = new RoundedPanel(XANH_CHU_DAO, 16);
        theXanh.setLayout(new BorderLayout(0, 12));
        theXanh.setBorder(new EmptyBorder(22, 20, 20, 20));
        theXanh.setPreferredSize(new Dimension(300, 162));
        theXanh.setMinimumSize(new Dimension(210, 162));
        theXanh.setMaximumSize(new Dimension(Integer.MAX_VALUE, 162));
        theXanh.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tieuDeThe = new JLabel("THẺ ĐỘC GIẢ  •  LIBRARY MANAGER");
        tieuDeThe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tieuDeThe.setForeground(Color.WHITE);

        JPanel thongTinThe = new JPanel();
        thongTinThe.setOpaque(false);
        thongTinThe.setLayout(new BoxLayout(thongTinThe, BoxLayout.Y_AXIS));

        lblTen.setFont(new Font("Segoe UI", Font.BOLD, 21));
        lblTen.setForeground(Color.WHITE);
        lblTen.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblMaThe.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblMaThe.setForeground(Color.WHITE);
        lblMaThe.setAlignmentX(Component.LEFT_ALIGNMENT);

        thongTinThe.add(lblTen);
        thongTinThe.add(Box.createVerticalStrut(5));
        thongTinThe.add(lblMaThe);

        theXanh.add(tieuDeThe, BorderLayout.NORTH);
        theXanh.add(thongTinThe, BorderLayout.SOUTH);

        JPanel thongKe = new JPanel(new GridLayout(1, 2, 12, 0));
        thongKe.setOpaque(false);
        thongKe.setPreferredSize(new Dimension(300, 92));
        thongKe.setMaximumSize(new Dimension(Integer.MAX_VALUE, 92));
        thongKe.setAlignmentX(Component.LEFT_ALIGNMENT);
        thongKe.setBorder(new EmptyBorder(14, 0, 0, 0));
        thongKe.add(taoTheThongKe(lblSachDangMuon, "Sách đang mượn"));
        thongKe.add(taoTheThongKe(lblLuotMuon, "Lượt mượn"));

        cotTrai.add(theXanh);
        cotTrai.add(thongKe);
        return cotTrai;
    }

    private JPanel taoTheThongKe(JLabel soLieu, String moTa) {
        JPanel the = new JPanel();
        the.setBackground(Color.WHITE);
        the.setLayout(new BoxLayout(the, BoxLayout.Y_AXIS));
        the.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MAU_VIEN),
                new EmptyBorder(10, 13, 10, 8)));

        soLieu.setFont(new Font("Segoe UI", Font.BOLD, 29));
        soLieu.setForeground(XANH_DAM);
        soLieu.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblMoTa = new JLabel(moTa);
        lblMoTa.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMoTa.setForeground(new Color(95, 101, 107));
        lblMoTa.setAlignmentX(Component.LEFT_ALIGNMENT);

        the.add(soLieu);
        the.add(Box.createVerticalStrut(2));
        the.add(lblMoTa);
        return the;
    }

    private JPanel taoFormBenPhai() {
        RoundedPanel form = new RoundedPanel(Color.WHITE, 14);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(24, 22, 22, 22));
        form.setMinimumSize(new Dimension(250, 250));

        JLabel title = new JLabel("Chỉnh sửa thông tin");
        title.setFont(new Font("Segoe UI", Font.BOLD, 17));
        title.setForeground(MAU_CHU);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        form.add(title);
        form.add(Box.createVerticalStrut(22));
        form.add(taoTruongNhap("Họ và tên", txtHoTen));
        form.add(Box.createVerticalStrut(16));
        form.add(taoTruongNhap("Số điện thoại", txtSoDienThoai));
        form.add(Box.createVerticalStrut(20));

        btnLuuThayDoi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLuuThayDoi.setForeground(Color.WHITE);
        btnLuuThayDoi.setBackground(XANH_CHU_DAO);
        btnLuuThayDoi.setFocusPainted(false);
        btnLuuThayDoi.setBorderPainted(false);
        btnLuuThayDoi.setOpaque(true);
        btnLuuThayDoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuuThayDoi.setPreferredSize(new Dimension(145, 38));
        btnLuuThayDoi.setMaximumSize(new Dimension(160, 40));
        btnLuuThayDoi.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTrangThai.setForeground(new Color(75, 85, 95));
        lblTrangThai.setAlignmentX(Component.LEFT_ALIGNMENT);

        form.add(btnLuuThayDoi);
        form.add(Box.createVerticalStrut(10));
        form.add(lblTrangThai);
        return form;
    }

    private JPanel taoTruongNhap(String tenTruong, JTextField truongNhap) {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JLabel label = new JLabel(tenTruong);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(MAU_CHU);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        truongNhap.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        truongNhap.setForeground(MAU_CHU);
        truongNhap.setCaretColor(XANH_DAM);
        truongNhap.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MAU_VIEN),
                new EmptyBorder(8, 10, 8, 10)));
        truongNhap.setPreferredSize(new Dimension(260, 39));
        truongNhap.setMinimumSize(new Dimension(100, 39));
        truongNhap.setMaximumSize(new Dimension(Integer.MAX_VALUE, 39));
        truongNhap.setAlignmentX(Component.LEFT_ALIGNMENT);

        wrapper.add(label);
        wrapper.add(Box.createVerticalStrut(7));
        wrapper.add(truongNhap);
        return wrapper;
    }

    /** Tải thẻ, họ tên, số điện thoại và thống kê theo tài khoản hiện tại. */
    private void taiThongTinThe() {
        String taiKhoanId = PhienDangNhap.taiKhoanId;
        if (taiKhoanId == null || taiKhoanId.trim().isEmpty()) {
            hienThiKhongCoHoSo("Không tìm thấy phiên đăng nhập. Vui lòng đăng nhập lại.");
            return;
        }

        try {
            ThongTinTheModel thongTin = controller.layThongTinThe(taiKhoanId);
            if (thongTin == null) {
                hienThiKhongCoHoSo("Tài khoản chưa có hồ sơ độc giả được liên kết.");
                return;
            }
            docGiaId = thongTin.getDocGiaId();
            String hoTen = thongTin.getHoTen();
            String sdt = thongTin.getSoDienThoai();
            lblTen.setText(hoTen == null || hoTen.trim().isEmpty()
                    ? "Chưa cập nhật họ tên" : hoTen);
            lblMaThe.setText("Mã thẻ: " + docGiaId);
            txtHoTen.setText(hoTen == null ? "" : hoTen);
            txtSoDienThoai.setText(sdt == null ? "" : sdt);
            lblSachDangMuon.setText(String.valueOf(thongTin.getSoSachDangMuon()));
            lblLuotMuon.setText(String.valueOf(thongTin.getLuotMuon()));
            setTrangThai(" ", false);
            setChoPhepSua(true);
        } catch (SQLException ex) {
            hienThiKhongCoHoSo("Không tải được dữ liệu: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /** Làm mới thông tin thẻ sau khi độc giả đăng ký mượn sách. */
    public void lamMoiThongTinThe() {
        taiThongTinThe();
    }

    /** Lưu đúng hồ sơ độc giả gắn với tài khoản hiện tại. */
    private void luuThayDoi() {
        String taiKhoanId = PhienDangNhap.taiKhoanId;
        String hoTen = txtHoTen.getText().trim();
        String sdt = txtSoDienThoai.getText().trim();

        if (docGiaId == null || taiKhoanId == null || taiKhoanId.trim().isEmpty()) {
            setTrangThai("Không xác định được hồ sơ để cập nhật.", true);
            return;
        }
        if (hoTen.isEmpty()) {
            setTrangThai("Họ và tên không được để trống.", true);
            txtHoTen.requestFocusInWindow();
            return;
        }

        // Giữ nguyên quy tắc kiểm tra số điện thoại của giao diện cũ.
        if (!sdt.isEmpty()) {
            if (!sdt.matches("[0-9+(). -]+")) {
                setTrangThai("Số điện thoại chỉ được chứa chữ số và ký tự + ( ) . -.", true);
                txtSoDienThoai.requestFocusInWindow();
                return;
            }
            int soChuSo = sdt.replaceAll("\\D", "").length();
            if (soChuSo < 9 || soChuSo > 15) {
                setTrangThai("Số điện thoại phải có từ 9 đến 15 chữ số.", true);
                txtSoDienThoai.requestFocusInWindow();
                return;
            }
        }

        try {
            boolean daCapNhat = controller.capNhatThongTinThe(
                    docGiaId, taiKhoanId, hoTen, sdt);
            if (daCapNhat) {
                setTrangThai("Đã lưu thông tin thành công.", false);
                JOptionPane.showMessageDialog(this,
                        "Thông tin độc giả đã được cập nhật vào cơ sở dữ liệu.",
                        "Lưu thành công", JOptionPane.INFORMATION_MESSAGE);
                taiThongTinThe();
            } else {
                setTrangThai("Không có dữ liệu được cập nhật. Hãy tải lại form hoặc đăng nhập lại.", true);
            }
        } catch (SQLException ex) {
            setTrangThai("Lỗi khi lưu dữ liệu: " + ex.getMessage(), true);
            ex.printStackTrace();
        }
    }

    private void hienThiKhongCoHoSo(String thongBao) {
        docGiaId = null;
        lblTen.setText("Chưa có thông tin");
        lblMaThe.setText("Mã thẻ chưa khả dụng");
        lblSachDangMuon.setText("0");
        lblLuotMuon.setText("0");
        txtHoTen.setText("");
        txtSoDienThoai.setText("");
        setChoPhepSua(false);
        setTrangThai(thongBao, true);
    }

    private void setChoPhepSua(boolean choPhep) {
        txtHoTen.setEnabled(choPhep);
        txtSoDienThoai.setEnabled(choPhep);
        btnLuuThayDoi.setEnabled(choPhep);
    }

    private void setTrangThai(String noiDung, boolean laLoi) {
        lblTrangThai.setText(noiDung == null || noiDung.isEmpty() ? " " : noiDung);
        lblTrangThai.setForeground(laLoi
                ? new Color(180, 45, 45)
                : new Color(44, 125, 72));
    }

    /** Panel tự vẽ góc bo tròn, không cần thêm thư viện ngoài. */
    private static class RoundedPanel extends JPanel {
        private final int radius;

        RoundedPanel(Color background, int radius) {
            this.radius = radius;
            setBackground(background);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }
    }

}
