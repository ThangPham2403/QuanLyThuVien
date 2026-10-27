//
//package com.thuvien.view;
//
///**
// * Quản lý trả sách - Đã chuyển sang JPanel để nhúng vào MainAdminFrame2
// */
//public class QuanLyTraSachPanel extends javax.swing.JPanel {
//
//    /**
//     * Creates new form QuanLyTraSachPanel
//     */
//    public QuanLyTraSachPanel() {
//        initComponents();
//        setBackground(new java.awt.Color(240, 242, 245));
//        if (jTable1 != null) {
//            jTable1.setBackground(java.awt.Color.WHITE);
//        }
//        if (jScrollPane1 != null) {
//            jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);
//        }
//        // Tự động nạp dữ liệu sách đang mượn ngay khi mở giao diện
//        loadTatCaSachDangMuon();
//    }
//
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
//    private void initComponents() {
//
//        jLabel1 = new javax.swing.JLabel();
//        jLabel2 = new javax.swing.JLabel();
//        jTextField1 = new javax.swing.JTextField();
//        jLabel3 = new javax.swing.JLabel();
//        jTextField2 = new javax.swing.JTextField();
//        jLabel4 = new javax.swing.JLabel();
//        jTextField3 = new javax.swing.JTextField();
//        jButton1 = new javax.swing.JButton();
//        jLabel5 = new javax.swing.JLabel();
//        jScrollPane1 = new javax.swing.JScrollPane();
//        jTable1 = new javax.swing.JTable();
//        jButton2 = new javax.swing.JButton();
//
//        setBackground(new java.awt.Color(240, 242, 245));
//
//        jLabel1.setBackground(new java.awt.Color(51, 51, 255));
//        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
//        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
//        jLabel1.setText("     QUẢN LÝ MƯỢN TRẢ SÁCH - HỆ THỐNG THƯ VIỆN");
//        jLabel1.setOpaque(true);
//
//        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
//        jLabel2.setText(" HỌ VÀ TÊN");
//
//        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
//        jLabel3.setText(" SỐ ĐIỆN THOẠI");
//
//        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
//        jLabel4.setText(" ĐỊA CHỈ");
//
//        jButton1.setBackground(new java.awt.Color(0, 153, 51));
//        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
//        jButton1.setForeground(new java.awt.Color(255, 255, 255));
//        jButton1.setText("TÌM SÁCH ĐANG MƯỢN");
//        jButton1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton1ActionPerformed(evt);
//            }
//        });
//
//        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
//        jLabel5.setText("Danh Sách Mã Sách chưa trả: ");
//
//        jTable1.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {},
//            new String [] {
//                "Tên Sách", "Mã Sách", "Ngày Mượn", "Hạn Trả", "Mã CTMT"
//            }
//        ));
//        jScrollPane1.setViewportView(jTable1);
//
//        jButton2.setBackground(new java.awt.Color(204, 102, 0));
//        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
//        jButton2.setForeground(new java.awt.Color(255, 255, 255));
//        jButton2.setText("XÁC NHẬN TRẢ SÁCH");
//        jButton2.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton2ActionPerformed(evt);
//            }
//        });
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
//        this.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(57, 57, 57)
//                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(28, 28, 28)
//                        .addComponent(jLabel5))
//                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
//                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addContainerGap())))
//            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap())
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(28, 28, 28)
//                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(28, 28, 28)
//                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addContainerGap(111, Short.MAX_VALUE))
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(54, 54, 54)
//                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(36, 36, 36)
//                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(0, 0, Short.MAX_VALUE))))
//        );
//    }// </editor-fold>                        
//
//    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
//        int selectedRow = jTable1.getSelectedRow();
//        if (selectedRow == -1) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng sách cần trả trên bảng!");
//            return;
//        }
//        
//        String maSach = jTable1.getValueAt(selectedRow, 1).toString();
//        Object ctmtObj = jTable1.getValueAt(selectedRow, 4);
//        if (ctmtObj == null) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Không tìm thấy mã chi tiết phiếu mượn!");
//            return;
//        }
//        String maCTMT = ctmtObj.toString();
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=true;trustServerCertificate=true;";
//        
//        try (java.sql.Connection conn = com.thuvien.config.DBConnection.getConnection();) {
//            conn.setAutoCommit(false);
//            try {
//                int soLuong = 1;
//                String sqlSoLuong = "SELECT so_luong FROM CT_MUONTRA WHERE ctmt_id = ?";
//                try (java.sql.PreparedStatement pst = conn.prepareStatement(sqlSoLuong)) {
//                    pst.setString(1, maCTMT);
//                    java.sql.ResultSet rs = pst.executeQuery();
//                    if (rs.next()) {
//                        soLuong = rs.getInt("so_luong");
//                    }
//                }
//
//                String updateTrangThai = "UPDATE MUONTRA SET trang_thai = N'Đã trả', thoi_gian_tra = GETDATE() "
//                                       + "WHERE muontra_id IN (SELECT muontra_id FROM CT_MUONTRA WHERE ctmt_id = ?)";
//                try (java.sql.PreparedStatement pst = conn.prepareStatement(updateTrangThai)) {
//                    pst.setString(1, maCTMT);
//                    pst.executeUpdate();
//                }
//
//                String updateKho = "UPDATE SACH SET so_luong = so_luong + ? WHERE sach_id = ?";
//                try (java.sql.PreparedStatement pst = conn.prepareStatement(updateKho)) {
//                    pst.setInt(1, soLuong);
//                    pst.setString(2, maSach);
//                    pst.executeUpdate();
//                }
//
//                conn.commit();
//                javax.swing.JOptionPane.showMessageDialog(this, "Xác nhận trả sách thành công!\nĐã trả: " + soLuong + " cuốn.");
//                
//                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
//                model.removeRow(selectedRow);
//            } catch (Exception ex) {
//                conn.rollback();
//                throw ex;
//            }
//        } catch (Exception e) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi khi trả sách: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }                                        
//
//    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
//        String hoTen = jTextField1.getText().trim();
//        String sdt = jTextField2.getText().trim();
//        
//        if (hoTen.isEmpty() && sdt.isEmpty()) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập Họ tên hoặc Số điện thoại để tìm kiếm!");
//            return;
//        }
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=true;trustServerCertificate=true;";
//
//        StringBuilder sql = new StringBuilder(
//            "SELECT s.sach_name, s.sach_id, mt.thoi_gian_muon, mt.thoi_gian_tra, ct.ctmt_id, mt.trang_thai "
//            + "FROM MUONTRA mt "
//            + "JOIN DOCGIA dg ON mt.docgia_id = dg.docgia_id "
//            + "JOIN CT_MUONTRA ct ON mt.muontra_id = ct.muontra_id "
//            + "JOIN SACH s ON ct.sach_id = s.sach_id "
//            + "WHERE 1=1 "
//        );
//
//        boolean hasHoTen = !hoTen.isEmpty();
//        boolean hasSdt = !sdt.isEmpty();
//
//        if (hasHoTen && hasSdt) {
//            sql.append("AND (dg.docgia_name LIKE ? OR dg.sdt LIKE ?)");
//        } else if (hasHoTen) {
//            sql.append("AND dg.docgia_name LIKE ?");
//        } else if (hasSdt) {
//            sql.append("AND dg.sdt LIKE ?");
//        }
//        
//        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
//        model.setRowCount(0);
//        model.setColumnIdentifiers(new Object[]{"Tên Sách", "Mã Sách", "Ngày Mượn", "Hạn Trả", "Mã CTMT"});
//        
//        try (
//            java.sql.Connection conn = com.thuvien.config.DBConnection.getConnection();
//            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql.toString())
//        ) {
//            if (hasHoTen && hasSdt) {
//                pstmt.setString(1, "%" + hoTen + "%");
//                pstmt.setString(2, "%" + sdt + "%");
//            } else if (hasHoTen) {
//                pstmt.setString(1, "%" + hoTen + "%");
//            } else if (hasSdt) {
//                pstmt.setString(1, "%" + sdt + "%");
//            }
//
//            java.sql.ResultSet rs = pstmt.executeQuery();
//            boolean hasDangMuon = false;
//            boolean hasRecords = false;
//
//            while (rs.next()) {
//                hasRecords = true;
//                String trangThai = rs.getString("trang_thai");
//                
//                if ("Đang mượn".equals(trangThai)) {
//                    hasDangMuon = true;
//                    model.addRow(new Object[]{
//                        rs.getString("sach_name"),
//                        rs.getString("sach_id"),
//                        rs.getTimestamp("thoi_gian_muon"),
//                        rs.getTimestamp("thoi_gian_tra"),
//                        rs.getString("ctmt_id")
//                    });
//                }
//            }
//            
//            if (jTable1.getColumnCount() > 4) {
//                jTable1.getColumnModel().getColumn(4).setMinWidth(0);
//                jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
//                jTable1.getColumnModel().getColumn(4).setWidth(0);
//            }
//
//            if (hasDangMuon) {
//                javax.swing.JOptionPane.showMessageDialog(this, "Tìm thấy thông tin! Độc giả đang có sách mượn.");
//            } else if (hasRecords) {
//                javax.swing.JOptionPane.showMessageDialog(this, "Độc giả này hiện không có sách đang mượn (Sách đã được trả hết).");
//            } else {
//                javax.swing.JOptionPane.showMessageDialog(this, "Độc giả này không tồn tại hoặc không có thông tin mượn sách!");
//            }
//        } catch (Exception e) {
//            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }                                        
//
//        private void loadTatCaSachDangMuon() {
//         String sql = "SELECT s.sach_name, s.sach_id, mt.thoi_gian_muon, mt.thoi_gian_tra, ct.ctmt_id "
//                    + "FROM MUONTRA mt "
//                    + "JOIN DOCGIA dg ON mt.docgia_id = dg.docgia_id "
//                    + "JOIN CT_MUONTRA ct ON mt.muontra_id = ct.muontra_id "
//                    + "JOIN SACH s ON ct.sach_id = s.sach_id "
//                    + "WHERE mt.trang_thai = N'Đang mượn'";
//
//         javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
//         model.setRowCount(0);
//         model.setColumnIdentifiers(new Object[]{"Tên Sách", "Mã Sách", "Ngày Mượn", "Hạn Trả", "Mã CTMT"});
//
//         // Gọi DBConnection.getConnection() thay vì DriverManager
//         try (java.sql.Connection conn = com.thuvien.config.DBConnection.getConnection();
//              java.sql.Statement st = conn.createStatement();
//              java.sql.ResultSet rs = st.executeQuery(sql)) {
//
//             while (rs.next()) {
//                 model.addRow(new Object[]{
//                     rs.getString("sach_name"),
//                     rs.getString("sach_id"),
//                     rs.getTimestamp("thoi_gian_muon"),
//                     rs.getTimestamp("thoi_gian_tra"),
//                     rs.getString("ctmt_id")
//                 });
//             }
//             if (jTable1.getColumnCount() > 4) {
//                 jTable1.getColumnModel().getColumn(4).setMinWidth(0);
//                 jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
//                 jTable1.getColumnModel().getColumn(4).setWidth(0);
//             }
//         } catch (Exception e) {
//             javax.swing.JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }
//
//    // Variables declaration - do not modify                     
//    private javax.swing.JButton jButton1;
//    private javax.swing.JButton jButton2;
//    private javax.swing.JLabel jLabel1;
//    private javax.swing.JLabel jLabel2;
//    private javax.swing.JLabel jLabel3;
//    private javax.swing.JLabel jLabel4;
//    private javax.swing.JLabel jLabel5;
//    private javax.swing.JScrollPane jScrollPane1;
//    private javax.swing.JTable jTable1;
//    private javax.swing.JTextField jTextField1;
//    private javax.swing.JTextField jTextField2;
//    private javax.swing.JTextField jTextField3;
//    // End of variables declaration                   
//}
//
//



package com.thuvien.view;

/**
 * Quản lý trả sách - Đã chuyển sang JPanel để nhúng vào MainAdminFrame2
 */
public class QuanLyTraSachPanel extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyTraSachPanel
     */
    public QuanLyTraSachPanel() {
        initComponents();
        setBackground(new java.awt.Color(240, 242, 245));
        if (jTable1 != null) {
            jTable1.setBackground(java.awt.Color.WHITE);
        }
        if (jScrollPane1 != null) {
            jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);
        }
        // Tự động nạp dữ liệu sách đang mượn ngay khi mở giao diện
        loadTatCaSachDangMuon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 242, 245));

        jLabel1.setBackground(new java.awt.Color(51, 51, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("     QUẢN LÝ MƯỢN TRẢ SÁCH - HỆ THỐNG THƯ VIỆN");
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jLabel2.setText(" HỌ VÀ TÊN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jLabel3.setText(" SỐ ĐIỆN THOẠI");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jLabel4.setText(" ĐỊA CHỈ");

        jButton1.setBackground(new java.awt.Color(0, 153, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("TÌM SÁCH ĐANG MƯỢN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel5.setText("Danh Sách Mã Sách chưa trả: ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Tên Sách", "Mã Sách", "Ngày Mượn", "Hạn Trả", "Mã CTMT"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setBackground(new java.awt.Color(204, 102, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("XÁC NHẬN TRẢ SÁCH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(111, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng sách cần trả trên bảng!");
            return;
        }
        
        String maSach = jTable1.getValueAt(selectedRow, 1).toString();
        Object ctmtObj = jTable1.getValueAt(selectedRow, 4);
        if (ctmtObj == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Không tìm thấy mã chi tiết phiếu mượn!");
            return;
        }
        String maCTMT = ctmtObj.toString();
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;encrypt=true;trustServerCertificate=true;";
        
        try (java.sql.Connection conn = com.thuvien.config.DBConnection.getConnection();) {
            conn.setAutoCommit(false);
            try {
                int soLuong = 1;
                String sqlSoLuong = "SELECT so_luong FROM CT_MUONTRA WHERE ctmt_id = ?";
                try (java.sql.PreparedStatement pst = conn.prepareStatement(sqlSoLuong)) {
                    pst.setString(1, maCTMT);
                    java.sql.ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        soLuong = rs.getInt("so_luong");
                    }
                }

                String updateTrangThai = "UPDATE MUONTRA SET trang_thai = N'Đã trả', thoi_gian_tra = GETDATE() "
                                       + "WHERE muontra_id IN (SELECT muontra_id FROM CT_MUONTRA WHERE ctmt_id = ?)";
                try (java.sql.PreparedStatement pst = conn.prepareStatement(updateTrangThai)) {
                    pst.setString(1, maCTMT);
                    pst.executeUpdate();
                }

                String updateKho = "UPDATE SACH SET so_luong = so_luong + ? WHERE sach_id = ?";
                try (java.sql.PreparedStatement pst = conn.prepareStatement(updateKho)) {
                    pst.setInt(1, soLuong);
                    pst.setString(2, maSach);
                    pst.executeUpdate();
                }

                conn.commit();
                javax.swing.JOptionPane.showMessageDialog(this, "Xác nhận trả sách thành công!\nĐã trả: " + soLuong + " cuốn.");
                
                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
                model.removeRow(selectedRow);
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi khi trả sách: " + e.getMessage());
            e.printStackTrace();
        }
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String hoTenNhap = jTextField1.getText().trim();
        String sdtNhap = jTextField2.getText().trim();

        if (hoTenNhap.isEmpty() && sdtNhap.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập Họ tên hoặc Số điện thoại để tìm kiếm!");
            return;
        }

        // Bước 1: tìm độc giả theo tên HOẶC số điện thoại, rồi điền đủ 3 trường.
        StringBuilder sqlDocGia = new StringBuilder(
                "SELECT docgia_id, docgia_name, sdt, diachi "
                + "FROM DOCGIA WHERE 1=1 ");

        if (!hoTenNhap.isEmpty() && !sdtNhap.isEmpty()) {
            sqlDocGia.append("AND (docgia_name LIKE ? OR sdt LIKE ?) ");
        } else if (!hoTenNhap.isEmpty()) {
            sqlDocGia.append("AND docgia_name LIKE ? ");
        } else {
            sqlDocGia.append("AND sdt LIKE ? ");
        }

        String maDocGia = null;
        int soDocGiaTimDuoc = 0;

        try (java.sql.Connection conn = com.thuvien.config.DBConnection.getConnection();
             java.sql.PreparedStatement psDocGia =
                     conn.prepareStatement(sqlDocGia.toString())) {

            if (!hoTenNhap.isEmpty() && !sdtNhap.isEmpty()) {
                psDocGia.setString(1, "%" + hoTenNhap + "%");
                psDocGia.setString(2, "%" + sdtNhap + "%");
            } else if (!hoTenNhap.isEmpty()) {
                psDocGia.setString(1, "%" + hoTenNhap + "%");
            } else {
                psDocGia.setString(1, "%" + sdtNhap + "%");
            }

            try (java.sql.ResultSet rsDocGia = psDocGia.executeQuery()) {
                while (rsDocGia.next()) {
                    soDocGiaTimDuoc++;

                    // Tên/số điện thoại có thể khớp nhiều độc giả.
                    // Không tự ý lấy sách của người đầu tiên nếu kết quả không duy nhất.
                    if (soDocGiaTimDuoc == 1) {
                        maDocGia = rsDocGia.getString("docgia_id");
                        jTextField1.setText(
                                rsDocGia.getString("docgia_name") == null
                                ? "" : rsDocGia.getString("docgia_name"));
                        jTextField2.setText(
                                rsDocGia.getString("sdt") == null
                                ? "" : rsDocGia.getString("sdt"));
                        jTextField3.setText(
                                rsDocGia.getString("diachi") == null
                                ? "" : rsDocGia.getString("diachi"));
                    }
                }
            }

            if (soDocGiaTimDuoc == 0) {
                javax.swing.table.DefaultTableModel model =
                        (javax.swing.table.DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Không tìm thấy độc giả phù hợp với thông tin đã nhập.");
                return;
            }

            if (soDocGiaTimDuoc > 1) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Có nhiều độc giả phù hợp. Vui lòng nhập tên đầy đủ hơn "
                        + "hoặc số điện thoại chính xác để tránh nhầm người.");
                return;
            }

            // Bước 2: lấy toàn bộ sách chưa trả của đúng độc giả vừa tìm được.
            // Bao gồm cả trạng thái Đang mượn và Quá hạn; loại trừ Đã trả.
            String sqlSach = "SELECT s.sach_name, s.sach_id, "
                    + "mt.thoi_gian_muon, mt.thoi_gian_tra, ct.ctmt_id "
                    + "FROM MUONTRA mt "
                    + "JOIN CT_MUONTRA ct ON mt.muontra_id = ct.muontra_id "
                    + "JOIN SACH s ON ct.sach_id = s.sach_id "
                    + "WHERE mt.docgia_id = ? "
                    + "AND ISNULL(LTRIM(RTRIM(mt.trang_thai)), N'Đang mượn') "
                    + "<> N'Đã trả' "
                    + "ORDER BY mt.thoi_gian_tra, mt.thoi_gian_muon";

            javax.swing.table.DefaultTableModel model =
                    (javax.swing.table.DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            model.setColumnIdentifiers(new Object[]{
                "Tên Sách", "Mã Sách", "Ngày Mượn", "Hạn Trả", "Mã CTMT"
            });

            try (java.sql.PreparedStatement psSach = conn.prepareStatement(sqlSach)) {
                psSach.setString(1, maDocGia);

                try (java.sql.ResultSet rsSach = psSach.executeQuery()) {
                    while (rsSach.next()) {
                        model.addRow(new Object[]{
                            rsSach.getString("sach_name"),
                            rsSach.getString("sach_id"),
                            rsSach.getTimestamp("thoi_gian_muon"),
                            rsSach.getTimestamp("thoi_gian_tra"),
                            rsSach.getString("ctmt_id")
                        });
                    }
                }
            }

            anCotMaCTMT();

            if (model.getRowCount() > 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Đã tìm thấy thông tin độc giả và các sách chưa trả "
                        + "(bao gồm cả sách quá hạn).");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Đã tìm thấy độc giả, nhưng hiện không có sách chưa trả.");
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Lỗi tìm kiếm: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadTatCaSachDangMuon() {
        // Chỉ loại trừ phiếu đã trả; không loại các phiếu quá hạn.
        String sql = "SELECT s.sach_name, s.sach_id, "
                + "mt.thoi_gian_muon, mt.thoi_gian_tra, ct.ctmt_id "
                + "FROM MUONTRA mt "
                + "JOIN CT_MUONTRA ct ON mt.muontra_id = ct.muontra_id "
                + "JOIN SACH s ON ct.sach_id = s.sach_id "
                + "WHERE ISNULL(LTRIM(RTRIM(mt.trang_thai)), N'Đang mượn') "
                + "<> N'Đã trả' "
                + "ORDER BY mt.thoi_gian_tra, mt.thoi_gian_muon";

        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{
            "Tên Sách", "Mã Sách", "Ngày Mượn", "Hạn Trả", "Mã CTMT"
        });

        try (java.sql.Connection conn =
                     com.thuvien.config.DBConnection.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("sach_name"),
                    rs.getString("sach_id"),
                    rs.getTimestamp("thoi_gian_muon"),
                    rs.getTimestamp("thoi_gian_tra"),
                    rs.getString("ctmt_id")
                });
            }
            anCotMaCTMT();

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Lỗi load dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void anCotMaCTMT() {
        if (jTable1.getColumnCount() > 4) {
            jTable1.getColumnModel().getColumn(4).setMinWidth(0);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(4).setWidth(0);
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration                   
}


