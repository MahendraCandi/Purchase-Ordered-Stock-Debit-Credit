package fladeoapp.form;

import fladeoapp.controller.SupplierController;
import fladeoapp.data.Supplier;
import fladeoapp.FladeoApp;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FormSupplier extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    Supplier supplier = new Supplier();
    SupplierController supCont = new SupplierController(FladeoApp.emf);
    DefaultTableModel model;
    /**
     * Creates new form FormSupplier
     */
    public FormSupplier() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel();
        model.addColumn("Kode Supplier");
        model.addColumn("Nama");
        model.addColumn("Telepon");
        model.addColumn("Kota");
        model.addColumn("Alamat");
        tableSupplier.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tidakAktif();
    }

    private void tidakAktif(){
        txtKdSupplie.setEnabled(false);
        txtNmSupplier.setEnabled(false);
        txtTelp.setEnabled(false);
        txtKota.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtCari.setEnabled(false);
    }
    
    private void showTable(){
        tableSupplier.setModel(supCont.findAllSupplier(model));
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=supCont.searchSupplier(model,cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            if(cari.isEmpty()){
                showTable();
            }else{
                tableSupplier.setModel(supCont.searchSupplier(model,cari));
            }
        }
    }
    
    private void seleksiBaris(){
        tableSupplier.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableSupplier.getSelectedRow(); 
                if(baris != -1){                        
                    txtKdSupplie.setText(tableSupplier.getValueAt(baris, 0).toString());
                    txtNmSupplier.setText(tableSupplier.getValueAt(baris, 1).toString());
                    txtTelp.setText(tableSupplier.getValueAt(baris, 2).toString());
                    txtKota.setText(tableSupplier.getValueAt(baris, 3).toString());
                    txtAlamat.setText(tableSupplier.getValueAt(baris, 4).toString());
                }
            }
        });
    }
    
    private void validasiKodeSupplier(){
        supplier = supCont.findSupplier(txtKdSupplie.getText());
        if(supplier.getKdSupplier() != null){
            if(JOptionPane.showConfirmDialog(null, "Kode Supplier eksis! Mau edit data?", "Warning", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                txtNmSupplier.setText(supplier.getNmSupplier());
                txtTelp.setText(supplier.getTelepon());
                txtKota.setText(supplier.getKota());
                txtAlamat.setText(supplier.getAlamat());
                txtNmSupplier.requestFocus();
            }else{
                txtKdSupplie.setText("");
                txtNmSupplier.setText("");
                txtTelp.setText("");
                txtKota.setText("");
                txtAlamat.setText("");
                txtKdSupplie.requestFocus();
            }
        }
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
        txtNmSupplier = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtKota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtKdSupplie = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSupplier = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 118, 117));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Supplier");

        txtNmSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNmSupplier.setForeground(new java.awt.Color(51, 51, 51));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("No. Telepon");

        txtTelp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTelp.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kota");

        txtKota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKota.setForeground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buat Supplier Baru");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Supplier");

        txtKdSupplie.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKdSupplie.setForeground(new java.awt.Color(51, 51, 51));
        txtKdSupplie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKdSupplieFocusLost(evt);
            }
        });
        txtKdSupplie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKdSupplieKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Alamat");

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAlamat.setForeground(new java.awt.Color(51, 51, 51));
        txtAlamat.setLineWrap(true);
        txtAlamat.setRows(5);
        txtAlamat.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtAlamat);

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKdSupplie, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNmSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(303, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtKdSupplie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNmSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tableSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableSupplier.setForeground(new java.awt.Color(51, 51, 51));
        tableSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Supplier", "Nama", "Telepon", "Kota", "Alamat"
            }
        ));
        jScrollPane1.setViewportView(tableSupplier);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Cari User");

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(51, 51, 51));
        txtCari.setText("Cari User");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            cariTable(txtCari.getText());
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void txtKdSupplieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKdSupplieKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            validasiKodeSupplier();
        }
    }//GEN-LAST:event_txtKdSupplieKeyPressed

    private void txtKdSupplieFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKdSupplieFocusLost
        validasiKodeSupplier();
    }//GEN-LAST:event_txtKdSupplieFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableSupplier;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKdSupplie;
    private javax.swing.JTextField txtKota;
    private javax.swing.JTextField txtNmSupplier;
    private javax.swing.JTextField txtTelp;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        txtKdSupplie.setEnabled(true);
        txtNmSupplier.setEnabled(true);
        txtTelp.setEnabled(true);
        txtKota.setEnabled(true);
        txtAlamat.setEnabled(true);
        txtCari.setEnabled(true);
        bersih();
        txtNmSupplier.requestFocus();
        seleksiBaris();
    }

    @Override
    public void bersih() {
        txtKdSupplie.setText("");
        txtNmSupplier.setText("");
        txtTelp.setText("");
        txtKota.setText("");
        txtAlamat.setText("");
        txtCari.setText("");
        txtKdSupplie.requestFocus();
        showTable();    
    }

    @Override
    public void simpan() {
        if(txtNmSupplier.getText().isEmpty() || txtTelp.getText().isEmpty() ||
                txtKota.getText().isEmpty() || txtAlamat.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else{
            supplier=supCont.findSupplier(txtKdSupplie.getText());
            Supplier Sup=new Supplier();
            if(supplier==null){
                Sup.setKdSupplier(txtKdSupplie.getText());
                Sup.setNmSupplier(txtNmSupplier.getText());
                Sup.setTelepon(txtTelp.getText());
                Sup.setKota(txtKota.getText());
                Sup.setAlamat(txtAlamat.getText());
                try{
                    supCont.save(Sup);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            }else{
                Sup.setKdSupplier(txtKdSupplie.getText());
                Sup.setNmSupplier(txtNmSupplier.getText());
                Sup.setTelepon(txtTelp.getText());
                Sup.setKota(txtKota.getText());
                Sup.setAlamat(txtAlamat.getText());
                try{
                    supCont.update(Sup);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
            }
            bersih();
        }
    }

    @Override
    public void hapus() {
        int baris = tableSupplier.getSelectedRow();
        if(baris==-1){
            JOptionPane.showMessageDialog(null, "Pilih data yang mau dihapus!");
        }else{
            try{
                supCont.delete(txtKdSupplie.getText());
                JOptionPane.showMessageDialog(null, "Data telah dihapus!");
            }catch(Exception ex){
                ex.printStackTrace();
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
