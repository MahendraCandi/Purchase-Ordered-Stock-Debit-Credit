package fladeoapp.form;

import fladeoapp.FladeoApp;
import fladeoapp.controller.PenerimaanBarangController;
import fladeoapp.controller.TransaksiPembelianController;
import fladeoapp.data.PenerimaanBarang;
import fladeoapp.data.TransaksiPembelian;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class FormTransaksiPembelian extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    TransaksiPembelian transaksi = new TransaksiPembelian();
    PenerimaanBarang penerimaanBarang = new PenerimaanBarang();
    TransaksiPembelianController transCont = new TransaksiPembelianController(FladeoApp.emf);
    PenerimaanBarangController pbCont = new PenerimaanBarangController(FladeoApp.emf);
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
    DefaultTableModel model;
    DecimalFormat myFormatter = new DecimalFormat("###,###.##");
    FormUtama formUtama = FormUtama.staticUtama;
    
    /**
     * Creates new form FormTransaksiPembelian
     */
    public FormTransaksiPembelian() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model = new DefaultTableModel();
        model.addColumn("No. Transaksi");
        model.addColumn("No. Tanda Terima");
        model.addColumn("No. Invoice");
        model.addColumn("Total Transaksi");
        tableTransaksi.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tidakAktif();
        panjangKarakter();
        formUtama.buttonOff();
        formUtama.getTambahBtn().setEnabled(true);
    }
    
    private void tidakAktif(){
        txtNoTrans.setEnabled(false);
        txtNoInvoice.setEnabled(false);
        cmbTandaTerima.setEnabled(false);
        txtTglTandaTerima.setEnabled(false);
        txtNoPO.setEnabled(false);
        txtSupplier.setEnabled(false);
        txtTglPO.setEnabled(false);
        txtTglKirim.setEnabled(false);
        txtTotalQty.setEnabled(false);
        txtTotalBeli.setEnabled(false);
        txtTotalTrans.setEnabled(false);
        txtCari.setEnabled(false);
    }
    
    private void panjangKarakter(){
        txtNoInvoice.setDocument(new JTextFieldLimit((10)));
    }
    
    private void comboBoxTandaTerima(){
        List<Object[]> listPO = pbCont.findAllNomorNotExistInTransaksiPembelian();
        List<Object> list = new ArrayList<>();
        for (Object[] po : listPO) {
            list.add(po[0]);
        }
        cmbTandaTerima.setModel(new DefaultComboBoxModel(list.toArray()));
    }
    
    private void showDataPenerimaanBarang(){
        List<Object[]> listObj = pbCont.findTotalPenerimaanBarang(cmbTandaTerima.getSelectedItem().toString());
        for(Object[] obj : listObj){
            //pb.Tgl_Terima_Barang, pb.No_PO, sp.Nm_Supplier, po.Tgl_PO, po.Tgl_Kirim, po.Total_Qty
            txtTglTandaTerima.setText(sdf.format((Date) obj[1]));
            txtNoPO.setText((String) obj[2]);
            txtSupplier.setText((String) obj[3]);
            txtTglPO.setText(sdf.format((Date) obj[4]));
            txtTglKirim.setText(sdf.format((Date) obj[5]));
            txtTotalQty.setText(myFormatter.format(obj[6]));
            txtTotalBeli.setText(myFormatter.format(obj[7]));
            txtTotalTrans.setText(myFormatter.format(obj[8]));
            
        }
    }
    
    private void showTable(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        List<TransaksiPembelian> listPO = transCont.findAllTransaksiPembelian();
        for(TransaksiPembelian tp : listPO){
            Object[] obj = new Object[4];
            obj[0] = tp.getNoTransaksi();
            obj[1] = tp.getNoTandaTerima();
            obj[2] = tp.getNoInvoice();
            obj[3] = tp.getTotalTransaksi();
            model.addRow(obj);
        }
        tableTransaksi.setModel(model);
    }
    
    private void seleksiBaris(){
        tableTransaksi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableTransaksi.getSelectedRow(); 
                if(baris != -1){                        
                    txtNoTrans.setText(tableTransaksi.getValueAt(baris, 0).toString());
                    Object[] obj = {tableTransaksi.getValueAt(baris, 1)};
                    cmbTandaTerima.setModel(new DefaultComboBoxModel(obj));
                    txtNoInvoice.setText(tableTransaksi.getValueAt(baris, 2).toString());
                    showDataPenerimaanBarang();
                }
            }
        });
    }
    
    private void cariTable(String cari){
        List<Object[]> listPb = transCont.searchTransaksiPembelian(cari);
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
                tableTransaksi.setModel(model);
            }
        }
    }
    
    private void renderTableTotal(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            //SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
            
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Number){
                    value = myFormatter.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tableTransaksi.getColumnModel().getColumn(3).setCellRenderer(tbr);
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
        txtNoInvoice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtNoTrans = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNoPO = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSupplier = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTglPO = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTglTandaTerima = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTglKirim = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTotalQty = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTotalBeli = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTotalTrans = new javax.swing.JTextField();
        cmbTandaTerima = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 118, 117));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("No. Invoice");

        txtNoInvoice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoInvoice.setForeground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Transaksi");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No. Transaksi");

        txtNoTrans.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoTrans.setForeground(new java.awt.Color(51, 51, 51));
        txtNoTrans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoTransKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("No. Tanda Terima");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("No. PO");

        txtNoPO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoPO.setForeground(new java.awt.Color(51, 51, 51));
        txtNoPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoPOKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Supplier");

        txtSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSupplier.setForeground(new java.awt.Color(51, 51, 51));
        txtSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSupplierKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tgl. PO");

        txtTglPO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTglPO.setForeground(new java.awt.Color(51, 51, 51));
        txtTglPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTglPOKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tgl. Tanda Terima");

        txtTglTandaTerima.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTglTandaTerima.setForeground(new java.awt.Color(51, 51, 51));
        txtTglTandaTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglTandaTerimaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tgl. Kirim");

        txtTglKirim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTglKirim.setForeground(new java.awt.Color(51, 51, 51));
        txtTglKirim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTglKirimKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total Qty");

        txtTotalQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTotalQty.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalQtyKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Total Beli");

        txtTotalBeli.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTotalBeli.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalBeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalBeliKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Transaksi");

        txtTotalTrans.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTotalTrans.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalTrans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalTransKeyTyped(evt);
            }
        });

        cmbTandaTerima.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbTandaTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTandaTerimaActionPerformed(evt);
            }
        });

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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTglTandaTerima))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNoInvoice, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNoTrans, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbTandaTerima, javax.swing.GroupLayout.Alignment.LEADING, 0, 160, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(16, 16, 16)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSupplier, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoPO, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTglPO)
                            .addComponent(txtTglKirim, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTotalBeli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txtTotalQty, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalTrans))))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtTotalBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTotalTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNoTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNoInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(txtTglPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTandaTerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTglTandaTerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtTglKirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tableTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableTransaksi.setForeground(new java.awt.Color(51, 51, 51));
        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Transaksi", "No. Tanda Terima", "No. Invoice", "Total Transaksi"
            }
        ));
        jScrollPane1.setViewportView(tableTransaksi);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Cari");

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoTransKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoTransKeyTyped

    }//GEN-LAST:event_txtNoTransKeyTyped

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            cariTable(txtCari.getText());
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void txtNoPOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoPOKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoPOKeyTyped

    private void txtSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSupplierKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupplierKeyTyped

    private void txtTglPOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTglPOKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglPOKeyTyped

    private void txtTglTandaTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglTandaTerimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglTandaTerimaActionPerformed

    private void txtTglKirimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTglKirimKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglKirimKeyTyped

    private void txtTotalQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalQtyKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalQtyKeyTyped

    private void txtTotalBeliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalBeliKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalBeliKeyTyped

    private void txtTotalTransKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalTransKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalTransKeyTyped

    private void cmbTandaTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTandaTerimaActionPerformed
        showDataPenerimaanBarang();
    }//GEN-LAST:event_cmbTandaTerimaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbTandaTerima;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTable tableTransaksi;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtNoInvoice;
    private javax.swing.JTextField txtNoPO;
    private javax.swing.JTextField txtNoTrans;
    private javax.swing.JTextField txtSupplier;
    private javax.swing.JTextField txtTglKirim;
    private javax.swing.JTextField txtTglPO;
    private javax.swing.JTextField txtTglTandaTerima;
    private javax.swing.JTextField txtTotalBeli;
    private javax.swing.JTextField txtTotalQty;
    private javax.swing.JTextField txtTotalTrans;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        cmbTandaTerima.setEnabled(true);
        txtCari.setEnabled(true);
        txtNoInvoice.setEnabled(true);
        bersih();
        seleksiBaris();
        renderTableTotal();
        formUtama.getSimpanBtn().setEnabled(true);
    }

    @Override
    public void bersih() {
        txtNoTrans.setText(transCont.nomorOtomatis());
        txtNoInvoice.setText("");
        txtTglTandaTerima.setText("");
        txtNoPO.setText("");
        txtSupplier.setText("");
        txtTglPO.setText("");
        txtTglKirim.setText("");
        txtTotalQty.setText("");
        txtTotalBeli.setText("");
        txtTotalTrans.setText("");
        txtCari.setText("");
        comboBoxTandaTerima();
        cmbTandaTerima.setSelectedIndex(0);
        showTable();
    }

    @Override
    public void simpan(){
        if(txtNoInvoice.getText().isEmpty() || txtNoPO.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
            return;
        }
        if(cmbTandaTerima.getItemCount() == 0){
            JOptionPane.showMessageDialog(null, "Data tanda terima kosong!");
            return;
        }else{
            List<Object[]> list = transCont.searchTransaksiPembelian(cmbTandaTerima.getSelectedItem().toString());
            if(!list.isEmpty()){
                JOptionPane.showMessageDialog(null, "No tanda terima eksis!");
            }else{
                try {
                    transaksi = new TransaksiPembelian();
                    transaksi.setNoTransaksi(txtNoTrans.getText());
                    transaksi.setNoTandaTerima(cmbTandaTerima.getSelectedItem().toString());
                    transaksi.setNoInvoice(txtNoInvoice.getText());
                    Number number = myFormatter.parse(txtTotalTrans.getText());
                    transaksi.setTotalTransaksi(number.doubleValue());
                    transCont.save(transaksi);
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                    bersih();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
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
