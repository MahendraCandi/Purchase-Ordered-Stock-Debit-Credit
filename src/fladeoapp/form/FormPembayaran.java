package fladeoapp.form;

import fladeoapp.FladeoApp;
import fladeoapp.controller.DetailPelunasanPembayaranController;
import fladeoapp.controller.PelunasanPembayaranController;
import fladeoapp.controller.SupplierController;
import fladeoapp.controller.TransaksiPembelianController;
import fladeoapp.data.DetailPelunasanPembayaran;
import fladeoapp.data.PelunasanPembayaran;
import fladeoapp.data.Supplier;
import fladeoapp.data.TransaksiPembelian;
import java.awt.Component;
import java.awt.Font;
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

public class FormPembayaran extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    Supplier supplier = new Supplier();
    PelunasanPembayaran pembayaran = new PelunasanPembayaran();
    DetailPelunasanPembayaran detailPembayaran= new DetailPelunasanPembayaran();
    PelunasanPembayaranController bayarCont = new PelunasanPembayaranController(FladeoApp.emf);
    DetailPelunasanPembayaranController detailCont = new DetailPelunasanPembayaranController(FladeoApp.emf);
    SupplierController sCont = new SupplierController(FladeoApp.emf);
    TransaksiPembelianController trCont = new TransaksiPembelianController(FladeoApp.emf);
    DefaultTableModel model;
    DefaultTableModel modelDialog;
    FormUtama formUtama = FormUtama.staticUtama;
    double totalBayar, bayar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.forLanguageTag("id-ID"));
    DecimalFormat myFormatter = new DecimalFormat("###,###.##");
    Number number;
    /**
     * Creates new form FormPembayaran
     */
    public FormPembayaran() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model = (DefaultTableModel) tableTransaksi.getModel();
        modelDialog = new DefaultTableModel();
        modelDialog.addColumn("No. Transaksi");
        modelDialog.addColumn("No. Tanda Terima");
        modelDialog.addColumn("No. Invoice");
        modelDialog.addColumn("Total Transaksi");
        tableTransaksi.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableTransaksiDialog.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tidakAktif();
        formUtama.buttonOff();
        formUtama.getTambahBtn().setEnabled(true);
        dialogTransaksi.setLocationRelativeTo(this);
    }
    
    private void tidakAktif(){
        txtNoPembayaran.setEnabled(false);
        cmbSupplier.setEnabled(false);
        jdcTgl.setEnabled(false);
        btnTambah.setEnabled(false);
        btnHapus.setEnabled(false);
        
        //dialog
        txtNoInvoice.setEnabled(false);
        txtTandaTerima.setEnabled(false);
        txtTglTandaTerima.setEnabled(false);
        txtNoPO.setEnabled(false);
        txtSupplier.setEnabled(false);
        txtTglPO.setEnabled(false);
        txtTglKirim.setEnabled(false);
        txtTotalQty.setEnabled(false);
        txtTotalBeli.setEnabled(false);
        txtTotalTrans.setEnabled(false);
    }
    
    private void dataComboBoxSupplier(){
        List<Supplier> listSupplier = sCont.findSupplierToList();
        List<String> listNamaSupplier = new ArrayList<>();
        listNamaSupplier.add("Pilih");
        for(Supplier s : listSupplier){
            listNamaSupplier.add(s.getNmSupplier());
        }
        cmbSupplier.setModel(new DefaultComboBoxModel(listNamaSupplier.toArray()));
    }
    
    private void dataComboBoxAndTableTransaksiDialog(){
        List<Object[]> listTransaksi = trCont.findAllTransaksiBySupplier(cmbSupplier.getSelectedItem().toString());
        if(listTransaksi.isEmpty()){
            JOptionPane.showMessageDialog(null, "Supplier ini tidak mempunyai data transaksi");
            clearDataTransakisDialog();
        }else{
            clearDataTransakisDialog();
            //combobox transaksi
            List<String> listNoTransaksi = new ArrayList<>();
            listNoTransaksi.add("Pilih");
            for(Object[] s : listTransaksi){
                listNoTransaksi.add((String) s[0]);
            }
            cmbNoTransaksi.setModel(new DefaultComboBoxModel(listNoTransaksi.toArray()));

            //table dialog
            for(Object[] o : listTransaksi){
                Object[] obj = new Object[4];
                obj[0] = o[0];
                obj[1] = o[1];
                obj[2] = o[2];
                obj[3] = o[6];
                modelDialog.addRow(obj);
            }
            tableTransaksiDialog.setModel(modelDialog);
        }
    }
    
    private void renderTableTotal(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Number){
                    value = myFormatter.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tableTransaksiDialog.getColumnModel().getColumn(3).setCellRenderer(tbr);
    }
    
    private void seleksiBarisDialog(){
        tableTransaksiDialog.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableTransaksiDialog.getSelectedRow(); 
                if(baris != -1){
                    cmbNoTransaksi.setSelectedItem(tableTransaksiDialog.getValueAt(baris, 0).toString());
                    dataTransaksiDialog();
                }
            }
        });
    }
    
    private void dataTransaksiDialog(){
        List<Object[]> listObj = trCont.findDetailTransaksiByNoTrans(cmbNoTransaksi.getSelectedItem().toString());
        for(Object[] obj : listObj){
            txtNoInvoice.setText((String) obj[1]);
            txtTandaTerima.setText((String) obj[2]);
            txtTglTandaTerima.setText(sdf.format((Date) obj[3]));
            txtNoPO.setText((String) obj[4]);
            txtSupplier.setText((String) obj[5]);
            txtTglPO.setText(sdf.format((Date) obj[6]));
            txtTglKirim.setText(sdf.format((Date) obj[7]));
            txtTotalQty.setText(myFormatter.format(obj[8]));
            txtTotalBeli.setText(myFormatter.format(obj[9]));
            txtTotalTrans.setText(myFormatter.format(obj[10]));
        }
    }
    
    private void clearDataTransakisDialog(){
        cmbNoTransaksi.removeAllItems();
        modelDialog.getDataVector().removeAllElements();
        modelDialog.fireTableDataChanged();
        txtNoInvoice.setText("");
        txtTandaTerima.setText("");
        txtTglTandaTerima.setText("");
        txtNoPO.setText("");
        txtSupplier.setText("");
        txtTglPO.setText("");
        txtTglKirim.setText("");
        txtTotalQty.setText("");
        txtTotalBeli.setText("");
        txtTotalTrans.setText("");
    }
    
    private void addDataToTableTransaksi(){
        Object[] obj = new Object[7];
        obj[0] = cmbNoTransaksi.getSelectedItem().toString();
        obj[1] = txtTandaTerima.getText();
        obj[2] = txtNoPO.getText();
        obj[3] = txtSupplier.getText();
        obj[4] = txtTotalQty.getText();
        obj[5] = txtTotalBeli.getText();
        obj[6] = txtTotalTrans.getText();
        model.addRow(obj);
    }
    
    private void hapusList(){
        int pilih=tableTransaksi.getSelectedRow();
        if(pilih==-1){
            JOptionPane.showMessageDialog(null, "Pilih transaksi yang mau dihapus!");
        }else{
            model.removeRow(pilih);
        }
    }
    
    private void clearList(){
        int row=tableTransaksi.getRowCount();
        while(row>0){
            row--;
            model.removeRow(row);
        }
        totalPembayaran();
    }
    
    private void totalPembayaran() {
        totalBayar=0;
        int baris=tableTransaksi.getRowCount();
        if(baris==-1){
            txtTotalPembayaran.setText(String.valueOf(totalBayar));
        }else{
            try {
                for(int i=0; i<baris;i++){
                    number = myFormatter.parse(tableTransaksi.getValueAt(i, 6).toString());
                    bayar = number.doubleValue();
                    totalBayar+=bayar;
                }
                txtTotalPembayaran.setText(myFormatter.format(totalBayar));    
            } catch (Exception e) {
                e.printStackTrace();
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

        dialogTransaksi = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txtTandaTerima = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTglTandaTerima = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTglKirim = new javax.swing.JTextField();
        txtTglPO = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNoPO = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTotalTrans = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTotalQty = new javax.swing.JTextField();
        txtNoInvoice = new javax.swing.JTextField();
        txtSupplier = new javax.swing.JTextField();
        txtTotalBeli = new javax.swing.JTextField();
        cmbNoTransaksi = new javax.swing.JComboBox<>();
        btnPilihTransaksi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTransaksiDialog = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cmbSupplier = new javax.swing.JComboBox<>();
        jdcTgl = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txtNoPembayaran = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
        btnHapus = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTotalPembayaran = new javax.swing.JLabel();

        dialogTransaksi.setTitle("Cari Transaksi");
        dialogTransaksi.setAlwaysOnTop(true);
        dialogTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        dialogTransaksi.setMinimumSize(new java.awt.Dimension(879, 380));
        dialogTransaksi.setModal(true);

        jPanel2.setBackground(new java.awt.Color(255, 118, 117));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("No. Invoice");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari Transaksi");

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("No. Tanda Terima");

        txtTandaTerima.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTandaTerima.setForeground(new java.awt.Color(51, 51, 51));
        txtTandaTerima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTandaTerimaKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("No. Transaksi");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tgl. Tanda Terima");

        txtTglTandaTerima.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTglTandaTerima.setForeground(new java.awt.Color(51, 51, 51));
        txtTglTandaTerima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTglTandaTerimaKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Supplier");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tgl. PO");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tgl. Kirim");

        txtTglKirim.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTglKirim.setForeground(new java.awt.Color(51, 51, 51));
        txtTglKirim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTglKirimKeyTyped(evt);
            }
        });

        txtTglPO.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTglPO.setForeground(new java.awt.Color(51, 51, 51));
        txtTglPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTglPOKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("No. PO");

        txtNoPO.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNoPO.setForeground(new java.awt.Color(51, 51, 51));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total Beli");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total Transaksi");

        txtTotalTrans.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTotalTrans.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalTrans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalTransKeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Total Qty");

        txtTotalQty.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTotalQty.setForeground(new java.awt.Color(51, 51, 51));

        txtNoInvoice.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNoInvoice.setForeground(new java.awt.Color(51, 51, 51));

        txtSupplier.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtSupplier.setForeground(new java.awt.Color(51, 51, 51));

        txtTotalBeli.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTotalBeli.setForeground(new java.awt.Color(51, 51, 51));

        cmbNoTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cmbNoTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNoTransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTandaTerima, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTglTandaTerima, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNoInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(cmbNoTransaksi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTglPO, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTglKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotalBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(cmbNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtNoInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtTandaTerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(txtNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtTglPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtTotalBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtTotalTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtTglTandaTerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txtTglKirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPilihTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnPilihTransaksi.setText("Pilih Transaksi");
        btnPilihTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihTransaksiActionPerformed(evt);
            }
        });

        tableTransaksiDialog.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableTransaksiDialog.setForeground(new java.awt.Color(51, 51, 51));
        tableTransaksiDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Transaksi", "No. Tanda Terima", "No. Invoice", "Total Transaksi"
            }
        ));
        jScrollPane2.setViewportView(tableTransaksiDialog);

        javax.swing.GroupLayout dialogTransaksiLayout = new javax.swing.GroupLayout(dialogTransaksi.getContentPane());
        dialogTransaksi.getContentPane().setLayout(dialogTransaksiLayout);
        dialogTransaksiLayout.setHorizontalGroup(
            dialogTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dialogTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                    .addGroup(dialogTransaksiLayout.createSequentialGroup()
                        .addComponent(btnPilihTransaksi)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dialogTransaksiLayout.setVerticalGroup(
            dialogTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogTransaksiLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPilihTransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 118, 117));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Pelunasan Pembayaran");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pilih Supplier");

        cmbSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSupplierActionPerformed(evt);
            }
        });

        jdcTgl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("No. Pembayaran");

        txtNoPembayaran.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoPembayaran.setForeground(new java.awt.Color(51, 51, 51));
        txtNoPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoPembayaranActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jatuh Tempo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNoPembayaran))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbSupplier, javax.swing.GroupLayout.Alignment.LEADING, 0, 160, Short.MAX_VALUE)
                                .addComponent(jdcTgl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(687, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdcTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        tableTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableTransaksi.setForeground(new java.awt.Color(51, 51, 51));
        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Transaksi", "No. Tanda Terima", "No. PO", "Supplier", "Total Qty", "Total Beli", "Total Transaksi"
            }
        ));
        jScrollPane1.setViewportView(tableTransaksi);

        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHapus.setText("Hapus Transaksi");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnTambah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTambah.setText("Tambah Transaksi");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Total Pembayaran : ");

        txtTotalPembayaran.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTotalPembayaran.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalPembayaran.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus)
                    .addComponent(btnTambah)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSupplierActionPerformed
        int baris = tableTransaksi.getRowCount();
        if(baris > 0){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Data supplier berubah!");
            clearList();
            dataComboBoxAndTableTransaksiDialog();
        }else{
            dataComboBoxAndTableTransaksiDialog();
            renderTableTotal();
        }
    }//GEN-LAST:event_cmbSupplierActionPerformed

    private void txtNoPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoPembayaranActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapusList();
        totalPembayaran();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtTandaTerimaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTandaTerimaKeyTyped

    }//GEN-LAST:event_txtTandaTerimaKeyTyped

    private void btnPilihTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihTransaksiActionPerformed
        int baris=tableTransaksi.getRowCount();
        String data="";
        boolean eksis=false;
        if(txtNoInvoice.getText().isEmpty() || cmbNoTransaksi.getItemCount() == 0 || cmbNoTransaksi.getSelectedItem().equals("Pilih")){
            dialogTransaksi.dispose();
        }else{
            for(int i = 0; i < baris; i++){
                data=tableTransaksi.getValueAt(i, 0).toString();
                if(data.equalsIgnoreCase(cmbNoTransaksi.getSelectedItem().toString())){
                    eksis=true;
                    break;
                }
            }

            if(!eksis){
                addDataToTableTransaksi();
                totalPembayaran();
                dialogTransaksi.dispose();
            }else{
                dialogTransaksi.dispose();
            }

        }
    }//GEN-LAST:event_btnPilihTransaksiActionPerformed

    private void txtTglTandaTerimaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTglTandaTerimaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglTandaTerimaKeyTyped

    private void txtTglKirimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTglKirimKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglKirimKeyTyped

    private void txtTglPOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTglPOKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglPOKeyTyped

    private void txtTotalTransKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalTransKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalTransKeyTyped

    private void cmbNoTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNoTransaksiActionPerformed
        if(cmbNoTransaksi.getItemCount() == 0){
            return;
        }else if(cmbNoTransaksi.getSelectedItem().equals("Pilih")){
            txtNoInvoice.setText("");
            txtTandaTerima.setText("");
            txtTglTandaTerima.setText("");
            txtNoPO.setText("");
            txtSupplier.setText("");
            txtTglPO.setText("");
            txtTglKirim.setText("");
            txtTotalQty.setText("");
            txtTotalBeli.setText("");
            txtTotalTrans.setText("");
            tableTransaksiDialog.clearSelection();
        }else{
            dataTransaksiDialog();
        }
    }//GEN-LAST:event_cmbNoTransaksiActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        dialogTransaksi.setVisible(true);
    }//GEN-LAST:event_btnTambahActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnPilihTransaksi;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbNoTransaksi;
    private javax.swing.JComboBox<String> cmbSupplier;
    private javax.swing.JDialog dialogTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.toedter.calendar.JDateChooser jdcTgl;
    private javax.swing.JTable tableTransaksi;
    private javax.swing.JTable tableTransaksiDialog;
    private javax.swing.JTextField txtNoInvoice;
    private javax.swing.JTextField txtNoPO;
    private javax.swing.JTextField txtNoPembayaran;
    private javax.swing.JTextField txtSupplier;
    private javax.swing.JTextField txtTandaTerima;
    private javax.swing.JTextField txtTglKirim;
    private javax.swing.JTextField txtTglPO;
    private javax.swing.JTextField txtTglTandaTerima;
    private javax.swing.JTextField txtTotalBeli;
    private javax.swing.JLabel txtTotalPembayaran;
    private javax.swing.JTextField txtTotalQty;
    private javax.swing.JTextField txtTotalTrans;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        cmbSupplier.setEnabled(true);
        jdcTgl.setEnabled(true);
        btnTambah.setEnabled(true);
        btnHapus.setEnabled(true);
        jdcTgl.setMinSelectableDate(new Date());
        dataComboBoxSupplier();
        seleksiBarisDialog();
        formUtama.getSimpanBtn().setEnabled(true);
    }

    @Override
    public void bersih() {
        txtNoPembayaran.setText(bayarCont.nomorOtomatis());
        jdcTgl.setDate(null);
        clearDataTransakisDialog();
        clearList();
    }

    @Override
    public void simpan() {
        if(cmbSupplier.getSelectedItem().equals("Pilih")){
            JOptionPane.showMessageDialog(null, "Pilih Supplier!");
        }else if(jdcTgl.getDate() == null){
            JOptionPane.showMessageDialog(null, "Pilih tempo pembayaran!");
        }else{
            int baris = tableTransaksi.getRowCount();
            if(baris == 0){
                JOptionPane.showMessageDialog(null, "Data Transaki kosong!");
            }else{
                try {
                    pembayaran = new PelunasanPembayaran();
                    pembayaran.setNoPembayaran(txtNoPembayaran.getText());
                    pembayaran.setJthTempo(jdcTgl.getDate());
                    number = myFormatter.parse(txtTotalPembayaran.getText());
                    pembayaran.setTotalBayar(number.doubleValue());
                    
                    
                    for (int i = 0; i < baris; i++) {
                        detailPembayaran.setNoPembayaran(txtNoPembayaran.getText());
                        detailPembayaran.setNoTransaksi(tableTransaksi.getValueAt(i, 0).toString());
                        detailCont.save(detailPembayaran);
                    }
                    
                    bayarCont.save(pembayaran);
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
