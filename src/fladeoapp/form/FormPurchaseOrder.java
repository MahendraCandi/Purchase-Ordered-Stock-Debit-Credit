package fladeoapp.form;

import fladeoapp.controller.PurchaseOrderController;
import fladeoapp.data.PurchaseOrder;
import fladeoapp.FladeoApp;
import fladeoapp.data.User;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
public class FormPurchaseOrder extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    PurchaseOrder po = new PurchaseOrder();
    PurchaseOrderController poCont = new PurchaseOrderController(FladeoApp.emf);
    User userLogin = new User();
    DefaultTableModel model;
    
    
    /**
     * Creates new form FormPurchaseOrder
     */
    public FormPurchaseOrder(User user) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel();
        model.addColumn("Nomor PO");
        model.addColumn("Tgl. Buat");
        model.addColumn("Tgl. Kirim");
        model.addColumn("Supplier");
        model.addColumn("Total Qty");
        model.addColumn("Username");
        tablePO.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tidakAktif();
        userLogin = user;
    }
    
    private void tidakAktif(){
        txtNoPO.setEnabled(false);
        txtTglBuat.setEnabled(false);
        txtTglKirim.setEnabled(false);
        txtSupplier.setEnabled(false);
        txtTotalQty.setEnabled(false);
        txtUsername.setEnabled(false);
        txtCari.setEnabled(false);
        btnBuatPO.setEnabled(false);
        btnDetailPO.setEnabled(false);
    }
    
    private void showTable(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        List<Object[]> listPO = poCont.findAllPurchaseOrder();
        for(Object[] po : listPO){
            model.addRow(po);
        }
        tablePO.setModel(model);
    }
    
    private void cariTable(String cari){
        List<Object[]> listPO = poCont.searchPurchaseOrder(cari);
        if(listPO.isEmpty()){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            if(cari.isEmpty()){
                showTable();
            }else{
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                for(Object[] po : listPO){
                    model.addRow(po);
                }
                tablePO.setModel(model);
            }
        }
    }
    
    private void seleksiBaris(){
        tablePO.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tablePO.getSelectedRow(); 
                if(baris != -1){                        
                    txtNoPO.setText(tablePO.getValueAt(baris, 0).toString());
                    txtTglBuat.setText(tablePO.getValueAt(baris, 1).toString());
                    txtTglKirim.setText(tablePO.getValueAt(baris, 2).toString());
                    txtSupplier.setText(tablePO.getValueAt(baris, 3).toString());
                    txtTotalQty.setText(tablePO.getValueAt(baris, 4).toString());
                    txtUsername.setText(tablePO.getValueAt(baris, 5).toString());
                }
            }
        });
    }
    
    private PurchaseOrder getPOSession(){
        PurchaseOrder purchaseOrder = poCont.findPurchaseOrder(txtNoPO.getText());
        return purchaseOrder;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        txtNamaAkun2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTglBuat = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtNoPO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTglKirim = new javax.swing.JTextField();
        txtSupplier = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTotalQty = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnBuatPO = new javax.swing.JButton();
        btnDetailPO = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePO = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tgl. Kirim");

        txtNamaAkun2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNamaAkun2.setForeground(new java.awt.Color(51, 51, 51));

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 118, 117));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tgl. buat");

        txtTglBuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTglBuat.setForeground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buat Purchase Order");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nomor PO");

        txtNoPO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoPO.setForeground(new java.awt.Color(51, 51, 51));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tgl. Kirim");

        txtTglKirim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTglKirim.setForeground(new java.awt.Color(51, 51, 51));

        txtSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSupplier.setForeground(new java.awt.Color(51, 51, 51));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Supplier");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Qty");

        txtTotalQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTotalQty.setForeground(new java.awt.Color(51, 51, 51));

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(51, 51, 51));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Username");

        btnBuatPO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuatPO.setText("Buat PO Baru");
        btnBuatPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuatPOActionPerformed(evt);
            }
        });

        btnDetailPO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDetailPO.setText("Lihat Detail PO");
        btnDetailPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailPOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTglBuat, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTglKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuatPO, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDetailPO, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTglBuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTglKirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuatPO)
                    .addComponent(btnDetailPO))
                .addContainerGap())
        );

        tablePO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablePO.setForeground(new java.awt.Color(51, 51, 51));
        tablePO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor PO", "Tgl. Buat", "Tgl. Kirim", "Supplier", "Total Qty", "Username"
            }
        ));
        jScrollPane1.setViewportView(tablePO);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Cari Data");

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(51, 51, 51));
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            cariTable(txtCari.getText());
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void btnBuatPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuatPOActionPerformed
        po = null;
        FormDetailPurchaseOrder formDetail = new FormDetailPurchaseOrder(userLogin, po);
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(formDetail);
        formDetail.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBuatPOActionPerformed

    private void btnDetailPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailPOActionPerformed
        po = poCont.findPurchaseOrder(txtNoPO.getText());
        FormDetailPurchaseOrder formDetail = new FormDetailPurchaseOrder(userLogin, po);
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(formDetail);
        formDetail.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDetailPOActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuatPO;
    private javax.swing.JButton btnDetailPO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablePO;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtNamaAkun2;
    private javax.swing.JTextField txtNoPO;
    private javax.swing.JTextField txtSupplier;
    private javax.swing.JTextField txtTglBuat;
    private javax.swing.JTextField txtTglKirim;
    private javax.swing.JTextField txtTotalQty;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        txtCari.setEnabled(true);
        btnBuatPO.setEnabled(true);
        btnDetailPO.setEnabled(true);
        bersih();
        seleksiBaris();
    }

    @Override
    public void bersih() {
        txtNoPO.setText("");
        txtTglBuat.setText("");
        txtTglKirim.setText("");
        txtSupplier.setText("");
        txtTotalQty.setText("");
        txtUsername.setText("");
        txtCari.setText("");
        showTable();
    }

    @Override
    public void simpan() {
        JOptionPane.showMessageDialog(null, "Buat PO baru untuk menyimpan data!");
    }

    @Override
    public void hapus() {
        int baris = tablePO.getSelectedRow();
        if(baris == -1){
            JOptionPane.showMessageDialog(null, "Pilih data yang mau dihapus!");
        }else{
            PurchaseOrder pOrder = new PurchaseOrder();
            try {
                pOrder = poCont.findPurchaseOrder(txtNoPO.getText());
                poCont.delete(pOrder.getNoPO());
            } catch (Exception e) {
                e.printStackTrace();
            }
            bersih();
        }
    }

    @Override
    public void cari() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tampil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}