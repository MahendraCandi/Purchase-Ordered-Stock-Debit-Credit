package fladeoapp.form;

import fladeoapp.data.User;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicMenuBarUI;

public class FormUtama extends javax.swing.JFrame {
    private String nama;
    private String hakAkses;
    private String username;
    private User userLogin = new User();
    public String tampilLaporan = "Other";
    
    int xx;
    int xy;
    public static FormUtama staticUtama;
    /**
     * Creates new form FormUtama
     */
    
    public FormUtama(User user) {
        initComponents();
        setLocationRelativeTo(null);
        jMenuBar1.setUI(new BasicMenuBarUI(){
            public void paint(Graphics g, JComponent c){
                g.setColor(new Color(255, 255, 255));
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });
        txtUsername.setText(user.getNama());
        txtHakAkses.setText(user.getHakAkses());
        userLogin = user;
        buttonOff();
        staticUtama = this;
    }
    
    
    public void buttonOn(){
        getTambahBtn().setEnabled(true);
        getDeleteBtn().setEnabled(true);
        getSimpanBtn().setEnabled(true);
    }
    
    public void buttonOff(){
        getTambahBtn().setEnabled(false);
        getDeleteBtn().setEnabled(false);
        getSimpanBtn().setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        tambahBtn = new javax.swing.JButton();
        simpanBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JLabel();
        txtHakAkses = new javax.swing.JLabel();
        desktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuMaster = new javax.swing.JMenu();
        masterItemSupplier = new javax.swing.JMenuItem();
        masterItemBarang = new javax.swing.JMenuItem();
        masterItemAkun = new javax.swing.JMenuItem();
        masterItemUser = new javax.swing.JMenuItem();
        MenuTransaksi = new javax.swing.JMenu();
        transItemPO = new javax.swing.JMenuItem();
        transItemPB = new javax.swing.JMenuItem();
        transItemPembelian = new javax.swing.JMenuItem();
        transItemPembayaran = new javax.swing.JMenuItem();
        transItemJurnal = new javax.swing.JMenuItem();
        MenuLaporan = new javax.swing.JMenu();
        lapItemPembelian = new javax.swing.JMenuItem();
        lapItemKas = new javax.swing.JMenuItem();
        lapItemTerimaBarang = new javax.swing.JMenuItem();
        MenuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Utama");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1200, 600));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 118, 117));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        panelHeader.setBackground(new java.awt.Color(214, 48, 49));
        panelHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelHeaderMouseDragged(evt);
            }
        });
        panelHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelHeaderMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelHeaderMousePressed(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);
        jToolBar1.setOpaque(false);

        tambahBtn.setForeground(new java.awt.Color(255, 255, 255));
        tambahBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Add.png"))); // NOI18N
        tambahBtn.setText("Tambah");
        tambahBtn.setFocusable(false);
        tambahBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tambahBtn.setOpaque(false);
        tambahBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tambahBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(tambahBtn);

        simpanBtn.setForeground(new java.awt.Color(255, 255, 255));
        simpanBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save.png"))); // NOI18N
        simpanBtn.setText("Simpan");
        simpanBtn.setFocusable(false);
        simpanBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        simpanBtn.setOpaque(false);
        simpanBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        simpanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(simpanBtn);

        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.setFocusable(false);
        deleteBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteBtn.setOpaque(false);
        deleteBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(deleteBtn);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gender Neutral User_48px.png"))); // NOI18N

        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setText("Username");

        txtHakAkses.setForeground(new java.awt.Color(255, 255, 255));
        txtHakAkses.setText("Hak Akses");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 872, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername)
                    .addComponent(txtHakAkses))
                .addGap(83, 83, 83))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHakAkses)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        desktopPane.setBackground(new java.awt.Color(255, 118, 117));
        desktopPane.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktopPane)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, "card2");

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorderPainted(false);

        MenuMaster.setText("Master");

        masterItemSupplier.setBackground(new java.awt.Color(255, 255, 255));
        masterItemSupplier.setText("Data Supplier");
        masterItemSupplier.setContentAreaFilled(false);
        masterItemSupplier.setOpaque(true);
        masterItemSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterItemSupplierActionPerformed(evt);
            }
        });
        MenuMaster.add(masterItemSupplier);

        masterItemBarang.setBackground(new java.awt.Color(255, 255, 255));
        masterItemBarang.setText("Data Barang");
        masterItemBarang.setContentAreaFilled(false);
        masterItemBarang.setOpaque(true);
        masterItemBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterItemBarangActionPerformed(evt);
            }
        });
        MenuMaster.add(masterItemBarang);

        masterItemAkun.setBackground(new java.awt.Color(255, 255, 255));
        masterItemAkun.setText("Data Akun");
        masterItemAkun.setContentAreaFilled(false);
        masterItemAkun.setOpaque(true);
        masterItemAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterItemAkunActionPerformed(evt);
            }
        });
        MenuMaster.add(masterItemAkun);

        masterItemUser.setBackground(new java.awt.Color(255, 255, 255));
        masterItemUser.setText("Data User");
        masterItemUser.setContentAreaFilled(false);
        masterItemUser.setOpaque(true);
        masterItemUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterItemUserActionPerformed(evt);
            }
        });
        MenuMaster.add(masterItemUser);

        jMenuBar1.add(MenuMaster);

        MenuTransaksi.setText("Transaksi");

        transItemPO.setBackground(new java.awt.Color(255, 255, 255));
        transItemPO.setText("Purchase Order");
        transItemPO.setOpaque(true);
        transItemPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transItemPOActionPerformed(evt);
            }
        });
        MenuTransaksi.add(transItemPO);

        transItemPB.setBackground(new java.awt.Color(255, 255, 255));
        transItemPB.setText("Penerimaan Barang");
        transItemPB.setOpaque(true);
        transItemPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transItemPBActionPerformed(evt);
            }
        });
        MenuTransaksi.add(transItemPB);

        transItemPembelian.setBackground(new java.awt.Color(255, 255, 255));
        transItemPembelian.setText("Transaksi Pembelian");
        transItemPembelian.setOpaque(true);
        transItemPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transItemPembelianActionPerformed(evt);
            }
        });
        MenuTransaksi.add(transItemPembelian);

        transItemPembayaran.setBackground(new java.awt.Color(255, 255, 255));
        transItemPembayaran.setText("Pelunasan Pembayaran");
        transItemPembayaran.setOpaque(true);
        transItemPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transItemPembayaranActionPerformed(evt);
            }
        });
        MenuTransaksi.add(transItemPembayaran);

        transItemJurnal.setBackground(new java.awt.Color(255, 255, 255));
        transItemJurnal.setText("Jurnal");
        transItemJurnal.setOpaque(true);
        MenuTransaksi.add(transItemJurnal);

        jMenuBar1.add(MenuTransaksi);

        MenuLaporan.setText("Laporan");

        lapItemPembelian.setBackground(new java.awt.Color(255, 255, 255));
        lapItemPembelian.setText("Laporan Pembelian");
        lapItemPembelian.setOpaque(true);
        MenuLaporan.add(lapItemPembelian);

        lapItemKas.setBackground(new java.awt.Color(255, 255, 255));
        lapItemKas.setText("Laporan Pengeluaran Kas");
        lapItemKas.setOpaque(true);
        MenuLaporan.add(lapItemKas);

        lapItemTerimaBarang.setBackground(new java.awt.Color(255, 255, 255));
        lapItemTerimaBarang.setText("Laporan Penerimaan Barang");
        lapItemTerimaBarang.setOpaque(true);
        lapItemTerimaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapItemTerimaBarangActionPerformed(evt);
            }
        });
        MenuLaporan.add(lapItemTerimaBarang);

        jMenuBar1.add(MenuLaporan);

        MenuLogout.setText("Logout");
        MenuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuLogoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(MenuLogout);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void masterItemSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterItemSupplierActionPerformed
        FormSupplier FS = new FormSupplier();
        showForm(FS);
    }//GEN-LAST:event_masterItemSupplierActionPerformed

    private void panelHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHeaderMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_panelHeaderMousePressed

    private void panelHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHeaderMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x -xx, y -xy);
    }//GEN-LAST:event_panelHeaderMouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x -xx, y -xy);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void panelHeaderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHeaderMouseClicked
        int frame = getExtendedState();
        if (evt.getClickCount() == 2) {
            if(frame != JFrame.MAXIMIZED_BOTH){
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }else{
                setExtendedState(JFrame.NORMAL);
            }
        }
    }//GEN-LAST:event_panelHeaderMouseClicked

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if(JOptionPane.showConfirmDialog(null, "Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            nav = (NavigatorFormInterface) desktopPane.getSelectedFrame();
            nav.hapus();
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void simpanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBtnActionPerformed
        nav = (NavigatorFormInterface) desktopPane.getSelectedFrame();
        nav.simpan();
    }//GEN-LAST:event_simpanBtnActionPerformed

    private void tambahBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBtnActionPerformed
        nav = (NavigatorFormInterface) desktopPane.getSelectedFrame();
        nav.bersih();
        nav.aktif();
    }//GEN-LAST:event_tambahBtnActionPerformed

    private void masterItemUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterItemUserActionPerformed
        FormUser fu = new FormUser(userLogin);
        showForm(fu);
    }//GEN-LAST:event_masterItemUserActionPerformed

    private void MenuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuLogoutMouseClicked
        FormLogin FL=new FormLogin();
        this.dispose();
        FL.setVisible(true);
        FL.setAutoRequestFocus(true);
    }//GEN-LAST:event_MenuLogoutMouseClicked

    private void masterItemAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterItemAkunActionPerformed
        FormPerkiraan fa = new FormPerkiraan();
        showForm(fa);
    }//GEN-LAST:event_masterItemAkunActionPerformed

    private void masterItemBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterItemBarangActionPerformed
        FormBarang FB = new FormBarang();
        showForm(FB);
    }//GEN-LAST:event_masterItemBarangActionPerformed

    private void transItemPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transItemPOActionPerformed
        FormPurchaseOrder po = new FormPurchaseOrder(userLogin);
        showForm(po);
    }//GEN-LAST:event_transItemPOActionPerformed

    private void transItemPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transItemPBActionPerformed
       FormPenerimaanBarang fpb = new FormPenerimaanBarang(userLogin);
       showForm(fpb);
    }//GEN-LAST:event_transItemPBActionPerformed

    private void lapItemTerimaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapItemTerimaBarangActionPerformed
        String tampil = "PenerimaanBarang";
        FormLaporan fl = new FormLaporan(tampil);
        fl.getPanelOther().setVisible(false);
        fl.getPanelPenerimaanBarang().setVisible(true);
        showForm(fl);
        
    }//GEN-LAST:event_lapItemTerimaBarangActionPerformed

    private void transItemPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transItemPembelianActionPerformed
        FormTransaksiPembelian ftb = new FormTransaksiPembelian();
        showForm(ftb);
    }//GEN-LAST:event_transItemPembelianActionPerformed

    private void transItemPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transItemPembayaranActionPerformed
        FormPembayaran fp = new FormPembayaran();
        showForm(fp);
    }//GEN-LAST:event_transItemPembayaranActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new FormUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuLaporan;
    private javax.swing.JMenu MenuLogout;
    private javax.swing.JMenu MenuMaster;
    private javax.swing.JMenu MenuTransaksi;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem lapItemKas;
    private javax.swing.JMenuItem lapItemPembelian;
    private javax.swing.JMenuItem lapItemTerimaBarang;
    private javax.swing.JMenuItem masterItemAkun;
    private javax.swing.JMenuItem masterItemBarang;
    private javax.swing.JMenuItem masterItemSupplier;
    private javax.swing.JMenuItem masterItemUser;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JButton simpanBtn;
    private javax.swing.JButton tambahBtn;
    private javax.swing.JMenuItem transItemJurnal;
    private javax.swing.JMenuItem transItemPB;
    private javax.swing.JMenuItem transItemPO;
    private javax.swing.JMenuItem transItemPembayaran;
    private javax.swing.JMenuItem transItemPembelian;
    private javax.swing.JLabel txtHakAkses;
    private javax.swing.JLabel txtUsername;
    // End of variables declaration//GEN-END:variables
    NavigatorFormInterface nav;
    private void showForm(Object obj){
        JInternalFrame jf=null;
        jf=(JInternalFrame) obj;
        desktopPane.add(jf);
        jf.setVisible(true);
        try {
            jf.setMaximizable(true);
            jf.setSelected(true);
        } catch (Exception e) {
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public javax.swing.JMenuItem getLapItemKas() {
        return lapItemKas;
    }

    public void setLapItemKas(javax.swing.JMenuItem lapItemKas) {
        this.lapItemKas = lapItemKas;
    }

    public javax.swing.JMenuItem getLapItemPembelian() {
        return lapItemPembelian;
    }

    public void setLapItemPembelian(javax.swing.JMenuItem lapItemPembelian) {
        this.lapItemPembelian = lapItemPembelian;
    }

    public javax.swing.JMenuItem getLapItemTerimaBarang() {
        return lapItemTerimaBarang;
    }

    public void setLapItemTerimaBarang(javax.swing.JMenuItem lapItemTerimaBarang) {
        this.lapItemTerimaBarang = lapItemTerimaBarang;
    }

    public javax.swing.JMenuItem getMasterItemAkun() {
        return masterItemAkun;
    }

    public void setMasterItemAkun(javax.swing.JMenuItem masterItemAkun) {
        this.masterItemAkun = masterItemAkun;
    }

    public javax.swing.JMenuItem getMasterItemBarang() {
        return masterItemBarang;
    }

    public void setMasterItemBarang(javax.swing.JMenuItem masterItemBarang) {
        this.masterItemBarang = masterItemBarang;
    }

    public javax.swing.JMenuItem getMasterItemSupplier() {
        return masterItemSupplier;
    }

    public void setMasterItemSupplier(javax.swing.JMenuItem masterItemSupplier) {
        this.masterItemSupplier = masterItemSupplier;
    }

    public javax.swing.JMenuItem getMasterItemUser() {
        return masterItemUser;
    }

    public void setMasterItemUser(javax.swing.JMenuItem masterItemUser) {
        this.masterItemUser = masterItemUser;
    }

    public javax.swing.JMenuItem getTransItemJurnal() {
        return transItemJurnal;
    }

    public void setTransItemJurnal(javax.swing.JMenuItem transItemJurnal) {
        this.transItemJurnal = transItemJurnal;
    }

    public javax.swing.JMenuItem getTransItemPB() {
        return transItemPB;
    }

    public void setTransItemPB(javax.swing.JMenuItem transItemPB) {
        this.transItemPB = transItemPB;
    }

    public javax.swing.JMenuItem getTransItemPO() {
        return transItemPO;
    }

    public void setTransItemPO(javax.swing.JMenuItem transItemPO) {
        this.transItemPO = transItemPO;
    }

    public javax.swing.JMenuItem getTransItemPembayaran() {
        return transItemPembayaran;
    }

    public void setTransItemPembayaran(javax.swing.JMenuItem transItemPembayaran) {
        this.transItemPembayaran = transItemPembayaran;
    }

    public javax.swing.JMenuItem getTransItemPembelian() {
        return transItemPembelian;
    }

    public void setTransItemPembelian(javax.swing.JMenuItem transItemPembelian) {
        this.transItemPembelian = transItemPembelian;
    }

    public javax.swing.JButton getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(javax.swing.JButton deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public javax.swing.JButton getSimpanBtn() {
        return simpanBtn;
    }

    public void setSimpanBtn(javax.swing.JButton simpanBtn) {
        this.simpanBtn = simpanBtn;
    }

    public javax.swing.JButton getTambahBtn() {
        return tambahBtn;
    }

    public void setTambahBtn(javax.swing.JButton tambahBtn) {
        this.tambahBtn = tambahBtn;
    }

    public String getTampilLaporan() {
        return tampilLaporan;
    }

    public void setTampilLaporan(String tampilLaporan) {
        this.tampilLaporan = tampilLaporan;
    }
    
    
}
