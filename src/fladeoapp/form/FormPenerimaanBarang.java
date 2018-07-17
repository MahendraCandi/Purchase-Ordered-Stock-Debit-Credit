package fladeoapp.form;

import fladeoapp.FladeoApp;
import fladeoapp.controller.PenerimaanBarangController;
import fladeoapp.controller.PurchaseOrderController;
import fladeoapp.data.PenerimaanBarang;
import fladeoapp.data.PurchaseOrder;
import fladeoapp.data.User;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormPenerimaanBarang extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    // BUAT BUTTON KEMBALI DI DETAIL PO, KEMBLI KE SINI
    
    PenerimaanBarang terimaBarang = new PenerimaanBarang();
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    User userLogin = new User();
    PenerimaanBarangController pbCont = new PenerimaanBarangController(FladeoApp.emf);
    PurchaseOrderController poCont = new PurchaseOrderController(FladeoApp.emf);
    
    DefaultTableModel model;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
    /**
     * Creates new form FormPenerimaanBarang
     */
    public FormPenerimaanBarang(User user) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel();
        model.addColumn("No. Tanda Terima");
        model.addColumn("Tgl. Terima Barang");
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
        txtCari.setEnabled(false);
        txtNoTandaTerima.setEnabled(false);
        txtTglTerima.setEnabled(false);
        cmbNoPo.setEnabled(false);
        btnDetailPO.setEnabled(false);
    }
    
    private void showTable(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        List<Object[]> listPO = pbCont.findAllPenerimaanBarang();
        for(Object[] po : listPO){
            model.addRow(po);
        }
        tablePO.setModel(model);
    }
    
    private void cariTable(String cari){
        List<Object[]> listPb = pbCont.searchPenerimaanBarang(cari);
        if(listPb.isEmpty()){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            if(cari.isEmpty()){
                showTable();
            }else{
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                for(Object[] pb : listPb){
                    model.addRow(pb);
                }
                tablePO.setModel(model);
            }
        }
    }
    
    private void comboBoxPO(){
        List<PurchaseOrder> listPO = poCont.findAllPONotExistInTerimaBarang();
        List<String> list = new ArrayList<>();
        for (PurchaseOrder po : listPO) {
            list.add(po.getNoPO());
        }
        cmbNoPo.setModel(new DefaultComboBoxModel(list.toArray()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtNoTandaTerima = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTglTerima = new javax.swing.JTextField();
        btnDetailPO = new javax.swing.JButton();
        cmbNoPo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePO = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 118, 117));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("No. PO");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Penerimaan Barang");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No. Tanda Terima");

        txtNoTandaTerima.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoTandaTerima.setForeground(new java.awt.Color(51, 51, 51));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tgl. Terima Barang");

        txtTglTerima.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTglTerima.setForeground(new java.awt.Color(51, 51, 51));

        btnDetailPO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDetailPO.setText("Lihat Detail PO");
        btnDetailPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailPOActionPerformed(evt);
            }
        });

        cmbNoPo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTglTerima, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtNoTandaTerima, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(cmbNoPo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnDetailPO, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(txtNoTandaTerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbNoPo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTglTerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnDetailPO)
                .addContainerGap())
        );

        tablePO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablePO.setForeground(new java.awt.Color(51, 51, 51));
        tablePO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Tanda Terima", "Tgl. Terima Barang", "Nomor PO", "Tgl. Buat", "Tgl. Kirim", "Supplier", "Total Qty", "Username"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetailPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailPOActionPerformed
        
        int baris = tablePO.getSelectedRow();
        if(baris == -1){
            JOptionPane.showMessageDialog(null, "Tidak ada data yang dipilih!");
        }else{
            purchaseOrder = poCont.findPurchaseOrder(tablePO.getValueAt(baris, 3).toString());
            FormDetailPurchaseOrder formDetail = new FormDetailPurchaseOrder(userLogin, purchaseOrder);
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(formDetail);
            formDetail.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_btnDetailPOActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            cariTable(txtCari.getText());
        }
    }//GEN-LAST:event_txtCariKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetailPO;
    private javax.swing.JComboBox<String> cmbNoPo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablePO;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtNoTandaTerima;
    private javax.swing.JTextField txtTglTerima;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        txtCari.setEnabled(true);
        cmbNoPo.setEnabled(true);
        btnDetailPO.setEnabled(true);
        bersih();
        
    }

    @Override
    public void bersih() {
        txtNoTandaTerima.setText(pbCont.nomorOtomatis());
        txtCari.setText("");
        txtTglTerima.setText(sdf.format(new Date()));
        comboBoxPO();
        showTable();
        
    }
    
    @Override
    public void simpan() {
        if(cmbNoPo.getItemCount() == 0){
            JOptionPane.showMessageDialog(null, "Data booking tidak ada!");
        }else{
            try {
                terimaBarang = new PenerimaanBarang();
                terimaBarang.setNoPO(cmbNoPo.getSelectedItem().toString());
                terimaBarang.setNoTandaTerima(txtNoTandaTerima.getText());
                terimaBarang.setTglTerimaBarang(sdf.parse(txtTglTerima.getText()));
                pbCont.save(terimaBarang);
                JOptionPane.showMessageDialog(null, "Dasta berhasil disimpan!");
                bersih();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        
    }

    @Override
    public void hapus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
