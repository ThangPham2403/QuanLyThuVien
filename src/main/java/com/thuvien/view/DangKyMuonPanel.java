package com.thuvien.view;

import com.thuvien.controller.DangKyMuonController;
import com.thuvien.model.SachDangKyMuon;
import com.thuvien.utils.PhienDangNhap;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DangKyMuonPanel extends JPanel {
    private final DangKyMuonController controller = new DangKyMuonController();
    private final Map<String,Integer> gioHang = new LinkedHashMap<>();
    private final DefaultTableModel sachModel = new DefaultTableModel(
        new Object[]{"Mã sách","Tên sách","Tác giả","Còn lại","Số lượng"},0) {
        public boolean isCellEditable(int r,int c) { return c==4; }
    };
    private final DefaultTableModel gioModel = new DefaultTableModel(
        new Object[]{"Mã sách","Tên sách","Số lượng",""},0) {
        public boolean isCellEditable(int r,int c) { return c==2; }
    };
    private final JTable tblSach = new JTable(sachModel), tblGio = new JTable(gioModel);
    private final JTextField txtTim = new JTextField(18);
    private final JTextField txtNgayMuon = new JTextField(LocalDate.now().toString(),10);
    private final JTextField txtHanTra = new JTextField(LocalDate.now().plusDays(14).toString(),10);

    public DangKyMuonPanel() {
        setLayout(new BorderLayout(14,14));
        setBackground(new Color(245,246,250));
        setBorder(BorderFactory.createEmptyBorder(20,22,20,22));
        JLabel title=new JLabel("Đăng ký mượn");
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        add(title,BorderLayout.NORTH);

        JPanel content=new JPanel(new GridLayout(1,2,16,0)); 
        content.setOpaque(false);
        
        JPanel left=new JPanel(new BorderLayout(8,8)); 
        left.setBackground(Color.WHITE);
        
        JPanel search=new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        search.setBackground(Color.WHITE);
        search.add(new JLabel("Sách có thể mượn:")); 
        search.add(txtTim);
        
        JButton btnTim=new JButton("Tìm"); 
        btnTim.addActionListener(e->taiSach()); 
        search.add(btnTim);
        left.add(search,BorderLayout.NORTH);
        
        tblSach.setRowHeight(30); 
        tblSach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        left.add(new JScrollPane(tblSach),BorderLayout.CENTER);
        
        JButton btnThem=new JButton("Thêm sách đã chọn"); 
        btnThem.addActionListener(e->themSach());
        left.add(btnThem,BorderLayout.SOUTH);

        JPanel right=new JPanel(new BorderLayout(8,10)); 
        right.setBackground(Color.WHITE);
        JLabel cartTitle=new JLabel("Phiếu đăng ký mượn"); 
        cartTitle.setFont(new Font("Segoe UI",Font.BOLD,17));
        right.add(cartTitle,BorderLayout.NORTH);
        right.add(new JScrollPane(tblGio),BorderLayout.CENTER);
        
        JPanel bottom=new JPanel(); 
        bottom.setBackground(Color.WHITE); 
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));
        
        JPanel dates=new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        dates.setBackground(Color.WHITE);
        dates.add(new JLabel("Ngày mượn (yyyy-MM-dd):")); 
        dates.add(txtNgayMuon);
        dates.add(new JLabel("Hạn trả:")); 
        dates.add(txtHanTra); bottom.add(dates);
        
        JPanel actions=new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
        actions.setBackground(Color.WHITE);
        
        JButton btnXoa=new JButton("Xóa khỏi phiếu"); 
        btnXoa.addActionListener(e->xoaSach());
        
        JButton btnGui=new JButton("Gửi đăng ký mượn"); 
        btnGui.setBackground(new Color(46,125,70)); 
        btnGui.setForeground(Color.WHITE);
        btnGui.addActionListener(e->guiDangKy());
        actions.add(btnXoa); actions.add(btnGui); 
        bottom.add(actions); right.add(bottom,BorderLayout.SOUTH);
        content.add(left); 
        content.add(right); 
        add(content,BorderLayout.CENTER);
        taiSach();
    }

    private void taiSach() {
        try {
            sachModel.setRowCount(0);
            List<SachDangKyMuon> list=controller.laySachCoTheMuon(txtTim.getText());
            for(SachDangKyMuon s:list) sachModel.addRow(new Object[]{s.getMaSach(),s.getTenSach(),s.getTacGia(),s.getConLai()+"/"+s.getTongSoLuong(),1});
        } catch(Exception ex) { thongBao("Không tải được danh sách sách: "+ex.getMessage(),JOptionPane.ERROR_MESSAGE); }
    }
    private void themSach() {
        int row=tblSach.getSelectedRow();
        if(row<0) { thongBao("Vui lòng chọn một đầu sách.",JOptionPane.WARNING_MESSAGE); return; }
        
        String ma=String.valueOf(sachModel.getValueAt(row,0));
        int con;
        try { con=Integer.parseInt(String.valueOf(sachModel.getValueAt(row,3)).split("/")[0]); }
        catch(Exception ex) { con=0; }
        
        if(con<=0) { thongBao("Đầu sách này đã hết.",JOptionPane.WARNING_MESSAGE); return; }
        String input=JOptionPane.showInputDialog(this,"Nhập số lượng muốn mượn (còn "+con+"):","1");
        
        if(input==null) return;
        try {
            int sl=Integer.parseInt(input.trim());
            if(sl<1 || sl>con) throw new NumberFormatException();
            int old=gioHang.getOrDefault(ma,0);
            if(old+sl>con) { thongBao("Số lượng trong phiếu vượt quá số sách còn.",JOptionPane.WARNING_MESSAGE); return; }
            gioHang.put(ma,old+sl); capNhatGio();
        } catch(NumberFormatException ex) { thongBao("Số lượng phải là số nguyên từ 1 đến số lượng còn.",JOptionPane.WARNING_MESSAGE); }
    }
    private void capNhatGio() {
        gioModel.setRowCount(0);
        for(Map.Entry<String,Integer> e:gioHang.entrySet()) {
            String ten="";
            for(int i=0;i<sachModel.getRowCount();i++) 
                if(e.getKey().equals(sachModel.getValueAt(i,0))) ten=String.valueOf(sachModel.getValueAt(i,1));
            gioModel.addRow(new Object[]{e.getKey(),ten,e.getValue(),""});
        }
    }
    private void xoaSach() {
        int row=tblGio.getSelectedRow();
        if(row<0) { thongBao("Chọn sách trong phiếu cần xóa.",JOptionPane.WARNING_MESSAGE); return; }
        gioHang.remove(String.valueOf(gioModel.getValueAt(row,0))); capNhatGio();
    }
    private void guiDangKy() {
        try {
            LocalDate ngay=LocalDate.parse(txtNgayMuon.getText().trim());
            LocalDate han=LocalDate.parse(txtHanTra.getText().trim());
            controller.dangKyMuon(PhienDangNhap.maDocGia,gioHang,ngay,han);
            gioHang.clear(); capNhatGio(); taiSach();
            thongBao("Đăng ký mượn thành công!",JOptionPane.INFORMATION_MESSAGE);
        } catch(DateTimeParseException ex) {
            thongBao("Ngày phải nhập đúng định dạng yyyy-MM-dd, ví dụ 2026-10-24.",JOptionPane.WARNING_MESSAGE);
        } catch(Exception ex) { thongBao("Đăng ký mượn thất bại: "+ex.getMessage(),JOptionPane.ERROR_MESSAGE); }
    }
    private void thongBao(String msg,int type) { JOptionPane.showMessageDialog(this,msg,"Đăng ký mượn",type); }
}
