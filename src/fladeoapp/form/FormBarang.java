package fladeoapp.form;

import fladeoapp.controller.BarangController;
import fladeoapp.controller.SupplierController;
import fladeoapp.data.Barang;
import fladeoapp.data.Supplier;
import fladeoapp.FladeoApp;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FormBarang extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    Barang barang = new Barang();
    Supplier supplier = new Supplier();
    BarangController brgCont= new BarangController(FladeoApp.emf);
    SupplierController supCont = new SupplierController(FladeoApp.emf);
    DefaultTableModel model;
    Image imageBarang;
    /**
     * Creates new form FormBarang
     */
    public FormBarang() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel();
        model.addColumn("Kode Barang");
        model.addColumn("Jenis Barang");
        model.addColumn("size");
        model.addColumn("Harga Beli");
        model.addColumn("Harga Jual");
        model.addColumn("Kode Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Kota");
        tableBarang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tidakAktif();
    }
    
    private void tidakAktif(){
        cmbJenis.setEnabled(false);
        txtKode.setEnabled(false);
        txtBeli.setEnabled(false);
        txtJual.setEnabled(false);
        cmbSupplier.setEnabled(false);
        cmbSize.setEnabled(false);
        txtKdSupplier.setEnabled(false);
        txtKotaSupplier.setEnabled(false);
        txtCari.setEnabled(false);
        btnFoto.setEnabled(false);
    }
    
    private void dataComboBoxJenisBarang(){
        String[] jenisBarang = {
            "Pilih Jenis Barang","Ladies Tali","Ladies Hak","Ladies Sandal","Ladies Casual","Ladies Formal",
            "Mens Tali","Mens Casual","Mens Formal","Mens Sandal",
            "Sepatu Kids Casual","Sepatu Kids Sekolah","Kids Sandal","Kids Tali","Bag"
        };
        cmbJenis.setModel(new DefaultComboBoxModel(jenisBarang));
    }
    
    private void dataComboBoxSizeBarang(){
        String[] sizeBarang = {
            "Pilih Size Barang", "31", "32", "33", "34", "35", "36", "37"
        };
        cmbSize.setModel(new DefaultComboBoxModel<>(sizeBarang));
    }
    
    private void dataComboBoxSupplier(){
        List<Supplier> listSupplier = supCont.findSupplierToList();
        List<String> listNamaSupplier = new ArrayList<>();
        for(Supplier s : listSupplier){
            listNamaSupplier.add(s.getNmSupplier());
        }
        cmbSupplier.setModel(new DefaultComboBoxModel(listNamaSupplier.toArray()));
    }
    
    private void dataSupplierByName(){
        Supplier supplier = supCont.findSupplierByName(cmbSupplier.getSelectedItem().toString());
        txtKdSupplier.setText(supplier.getKdSupplier());
        txtKotaSupplier.setText(supplier.getKota());
    }
    
    private void showTable(){
        tableBarang.setModel(brgCont.findAllBarang(model));
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=brgCont.searchBarang(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            if(cari.isEmpty()){
                showTable();
            }else{
                tableBarang.setModel(brgCont.searchBarang(model, cari));
            }
        }
    }
    
    private void seleksiBaris(){
        tableBarang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableBarang.getSelectedRow(); 
                if(baris != -1){                       
                    cmbJenis.setSelectedItem(tableBarang.getValueAt(baris, 1).toString());
                    txtKode.setText(tableBarang.getValueAt(baris, 0).toString()); 
                    cmbSize.setSelectedItem(tableBarang.getValueAt(baris, 2).toString()); 
                    txtBeli.setText(tableBarang.getValueAt(baris, 3).toString());
                    txtJual.setText(tableBarang.getValueAt(baris, 4).toString());
                    cmbSupplier.setSelectedItem(tableBarang.getValueAt(baris, 6).toString());
                    tampilFoto();
                }
            }
        });
    }
    
    private void tampilFoto(){
        barang=brgCont.findByKodeBarang(txtKode.getText());
        if(barang.getFoto()!=null){
            try{
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(barang.getFoto()));
                ImageIcon icon=(ImageIcon) inputStream.readObject();
                imageBarang=icon.getImage();
                panelGambar1.setImage(imageBarang);
                inputStream.close();
                txtFoto.setText("");
            }catch(ClassNotFoundException ex){
            }catch(IOException ex){}
        }else{
            panelGambar1.setImage(null);
            txtFoto.setText("Foto tidak ada");
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
        txtKode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cmbJenis = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtBeli = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtJual = new javax.swing.JTextField();
        cmbSupplier = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtKdSupplier = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtKotaSupplier = new javax.swing.JTextField();
        cmbSize = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        panelGambar1 = new fladeoapp.panelFoto.panelGambar();
        txtFoto = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 118, 117));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kode Barang");

        txtKode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKode.setForeground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buat Kode Barang");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Jenis Barang");

        cmbJenis.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Harga Beli");

        txtBeli.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBeli.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga Jual");

        txtJual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtJual.setForeground(new java.awt.Color(51, 51, 51));

        cmbSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSupplierActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Supplier");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kode Supplier");

        txtKdSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKdSupplier.setForeground(new java.awt.Color(51, 51, 51));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kota");

        txtKotaSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKotaSupplier.setForeground(new java.awt.Color(51, 51, 51));

        cmbSize.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSizeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Size");

        panelGambar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        panelGambar1.setOpaque(false);
        panelGambar1.setPreferredSize(new java.awt.Dimension(260, 260));

        txtFoto.setBackground(new java.awt.Color(255, 255, 255));
        txtFoto.setForeground(new java.awt.Color(255, 255, 255));
        txtFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFoto.setText("Tidak ada foto");

        javax.swing.GroupLayout panelGambar1Layout = new javax.swing.GroupLayout(panelGambar1);
        panelGambar1.setLayout(panelGambar1Layout);
        panelGambar1Layout.setHorizontalGroup(
            panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGambar1Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(txtFoto)
                .addGap(104, 104, 104))
        );
        panelGambar1Layout.setVerticalGroup(
            panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGambar1Layout.createSequentialGroup()
                .addContainerGap(258, Short.MAX_VALUE)
                .addComponent(txtFoto)
                .addContainerGap())
        );

        btnFoto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFoto.setText("Cari Foto");
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Foto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbJenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJual, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKdSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKotaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFoto))))
                .addGap(18, 18, 18)
                .addComponent(panelGambar1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtKdSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtKotaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFoto)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(128, 128, 128))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(panelGambar1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableBarang.setForeground(new java.awt.Color(51, 51, 51));
        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Jenis Barang", "Size", "Harga Beli", "Harga Jual", "Kode Supplier", "Nama Supplier", "Kota"
            }
        ));
        jScrollPane1.setViewportView(tableBarang);

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(51, 51, 51));
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Cari ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            cariTable(txtCari.getText());
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void cmbJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisActionPerformed
        if("Pilih Jenis Barang".equalsIgnoreCase(cmbJenis.getSelectedItem().toString())){
            txtKode.setText("");
            txtBeli.setText("");
            txtJual.setText("");
        }else{
            txtKode.setText(brgCont.kodeOtomatis(cmbJenis.getSelectedItem().toString()));
            txtBeli.setText("");
            txtJual.setText("");
        }
    }//GEN-LAST:event_cmbJenisActionPerformed

    private void cmbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSupplierActionPerformed
        dataSupplierByName();
    }//GEN-LAST:event_cmbSupplierActionPerformed

    private void cmbSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSizeActionPerformed

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
        JFileChooser chooser = new JFileChooser("..\\Purchase-Ordered-Stock-Debit-Credit\\foto\\fotoBarang");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("jpg|jpeg|png|bmp", "jpg","jpeg","png","bmp"));
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            File file=chooser.getSelectedFile();
            try{
                imageBarang=ImageIO.read(file);
                panelGambar1.setImage(imageBarang);
                txtFoto.setText("");
            }catch(IOException ex){}
        }
    }//GEN-LAST:event_btnFotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFoto;
    private javax.swing.JComboBox<String> cmbJenis;
    private javax.swing.JComboBox<String> cmbSize;
    private javax.swing.JComboBox<String> cmbSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private fladeoapp.panelFoto.panelGambar panelGambar1;
    private javax.swing.JTable tableBarang;
    private javax.swing.JTextField txtBeli;
    private javax.swing.JTextField txtCari;
    private javax.swing.JLabel txtFoto;
    private javax.swing.JTextField txtJual;
    private javax.swing.JTextField txtKdSupplier;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtKotaSupplier;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        cmbJenis.setEnabled(true);
        txtBeli.setEnabled(true);
        txtJual.setEnabled(true);
        cmbSupplier.setEnabled(true);
        cmbSize.setEnabled(true);
        txtCari.setEnabled(true);
        btnFoto.setEnabled(true);
        bersih();
        seleksiBaris();
    }

    @Override
    public void bersih() {
        dataComboBoxJenisBarang();
        dataComboBoxSizeBarang();
        dataComboBoxSupplier();
        cmbJenis.setSelectedIndex(0);
        txtKode.setText("");
        txtBeli.setText("");
        txtJual.setText("");
        cmbSupplier.setSelectedIndex(0);
        cmbSize.setSelectedIndex(0);
        txtKdSupplier.setText("");
        txtKotaSupplier.setText("");
        txtCari.setText("");
        txtFoto.setText("Tidak ada foto");
        panelGambar1.setImage(null);
        showTable();
    }

    @Override
    public void simpan() {
        if("Pilih Jenis Barang".equalsIgnoreCase(cmbJenis.getSelectedItem().toString()) ||
                "Pilih Size Barang".equalsIgnoreCase(cmbSize.getSelectedItem().toString()) ||
                txtBeli.getText().isEmpty() || txtJual.getText().isEmpty() || txtKdSupplier.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else{
            barang=brgCont.findByKodeBarang(txtKode.getText());
            Barang brg=new Barang();
            if(barang==null){
                try{
                    brg.setKdBarang(txtKode.getText());
                    brg.setJenisBarang(cmbJenis.getSelectedItem().toString());
                    brg.setSize(cmbSize.getSelectedItem().toString());
                    brg.setHrgBeli(Double.parseDouble(txtBeli.getText()));
                    brg.setHrgJual(Double.parseDouble(txtJual.getText()));
                    brg.setKdSupplier(txtKdSupplier.getText());
                
                    ObjectOutputStream objectoutputstream=null;
                    ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                    objectoutputstream=new ObjectOutputStream(outputstream);
                    ImageIcon icon=new ImageIcon(imageBarang);
                    objectoutputstream.writeObject(icon);
                    objectoutputstream.flush();
                    objectoutputstream.close();
                    brg.setFoto(outputstream.toByteArray());
                    
                    brgCont.save(brg);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            }else{
                try{
                    barang.setKdBarang(txtKode.getText());
                    barang.setJenisBarang(cmbJenis.getSelectedItem().toString());
                    barang.setSize(cmbSize.getSelectedItem().toString());;
                    barang.setHrgBeli(Double.parseDouble(txtBeli.getText()));
                    barang.setHrgJual(Double.parseDouble(txtJual.getText()));
                    barang.setKdSupplier(txtKdSupplier.getText());
                    
                    ObjectOutputStream objectoutputstream=null;
                    ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                    objectoutputstream=new ObjectOutputStream(outputstream);
                    ImageIcon icon=new ImageIcon(imageBarang);
                    objectoutputstream.writeObject(icon);
                    objectoutputstream.flush();
                    objectoutputstream.close();
                    barang.setFoto(outputstream.toByteArray());
                
                    brgCont.update(barang);
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
        int baris = tableBarang.getSelectedRow();
        if(baris == -1){
            JOptionPane.showMessageDialog(null, "Pilih data yang mau dihapus!");
        }else{
            Barang brg = new Barang();
            try {
                brg = brgCont.findByKodeBarang(txtKode.getText());
                brgCont.delete(brg.getIdBarang());
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
