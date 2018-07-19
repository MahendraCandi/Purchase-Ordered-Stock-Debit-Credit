package fladeoapp.form;

import fladeoapp.controller.PurchaseOrderController;
import fladeoapp.data.PurchaseOrder;
import fladeoapp.data.User;
import fladeoapp.FladeoApp;
import fladeoapp.controller.BarangController;
import fladeoapp.controller.DetailPurchaseOrderController;
import fladeoapp.controller.SupplierController;
import fladeoapp.data.Barang;
import fladeoapp.data.Supplier;
import fladeoapp.data.DetailPurchaseOrder;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FormDetailPurchaseOrder extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    PurchaseOrderController poCont = new PurchaseOrderController(FladeoApp.emf);
    DetailPurchaseOrderController detailCont = new DetailPurchaseOrderController(FladeoApp.emf);
    BarangController bCont = new BarangController(FladeoApp.emf);
    SupplierController sCont = new SupplierController(FladeoApp.emf);
    
    User userLogin = new User();
    PurchaseOrder poSession = new PurchaseOrder();
    DetailPurchaseOrder detailOrder = new DetailPurchaseOrder();
    Barang barang = new Barang();
    Supplier supplier = new Supplier();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
    DefaultTableModel model; // tableBarang
    DefaultTableModel modelDetail; // tableDetail
    
    int qty=0, totalQty=0;
    
    boolean kembaliPenerimaBarang = false;
    
    /**
     * Creates new form FormDetailPurchaseOrder
     */
    public FormDetailPurchaseOrder(User user, PurchaseOrder purchaseOrder) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        userLogin = user;
        poSession = purchaseOrder;
        jdcTglKirim.setLocale(Locale.forLanguageTag("in-ID"));
        jdcTglKirim.setDateFormatString("dd MMMM yyyy");
        dataComboBoxSupplier();
        tableDetailBarang();
        validasiPO();
        //barang dialog
        columnBarang();
        barangDialog.setLocationRelativeTo(null);
        seleksiTableBarang();
        tidakAktif();
    }
    
    private void tidakAktif(){
        txtNoPO.setEnabled(false);
            txtTglBuat.setEnabled(false);
            txtDNamaSupplier.setEnabled(false);
            txtDKdSupplier.setEnabled(false);
            txtDHrgbeli.setEnabled(false);
            txtDHrgJual.setEnabled(false);
    }
    
    private void validasiPO(){
        if(poSession == null){
            txtTitleDetailPO.setText("Buat PO Baru");
            txtNoPO.setText(poCont.nomorOtomatis());
            txtTglBuat.setText(sdf.format(new Date()));
            getTglKirim();
        }else{
            txtTitleDetailPO.setText("Detail PO");
            txtNoPO.setText(poSession.getNoPO());
            dataNamaSupplier();
            txtTglBuat.setText(sdf.format(poSession.getTglPO()));
            jdcTglKirim.setDate(poSession.getTglKirim());
            dataDetailByPO();
            totalQty();
            cmbSupplier.setEnabled(false);
            jdcTglKirim.setEnabled(false);
        }
    }
    
    private void dataDetailByPO(){
        List<DetailPurchaseOrder> listDetail = detailCont.findAllDetailByPONumber(poSession.getNoPO());
        for(DetailPurchaseOrder dpo : listDetail){
            Object[] obj=new Object[5];  
            obj[1]=dpo.getKdBarang();
            obj[2]=dpo.getQtyOrder(); 
            obj[3]=dpo.getHargaBeli(); 
            obj[4]=dpo.getHargaJual(); 
            modelDetail.addRow(obj); 
            noTable();
        }
    }
    
    private void getTglKirim(){
        jdcTglKirim.setMinSelectableDate(new Date());
    }
    
    private void dataComboBoxSupplier(){
        List<Supplier> listSupplier = sCont.findSupplierToList();
        List<String> listNamaSupplier = new ArrayList<>();
        for(Supplier s : listSupplier){
            listNamaSupplier.add(s.getNmSupplier());
        }
        cmbSupplier.setModel(new DefaultComboBoxModel(listNamaSupplier.toArray()));
    }
    
    private void dataNamaSupplier(){
        supplier = sCont.findSupplier(poSession.getKdSupplier());
        cmbSupplier.setSelectedItem(supplier.getNmSupplier());
    }
    
    //barang dialog
    private void columnBarang(){
        model = new DefaultTableModel();
        model.addColumn("Kode Barang");
        model.addColumn("Jenis Barang");
        model.addColumn("Size");
        model.addColumn("Warna");
        model.addColumn("Harga Beli");
        model.addColumn("Harga Jual");
        model.addColumn("Kode Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Kota");
        tableBarangDialog.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
    }
    
    //barang dialog
    private void dataBarangDialog(String kodeSupplier){
        List<Barang> listBarang = bCont.findBarangBySupplierToList(kodeSupplier);
        List<String> listKdBarang = new ArrayList<>();
        for(Barang b : listBarang){
            listKdBarang.add(b.getKdBarang());
        }
        cmbDKdBarang.setModel(new DefaultComboBoxModel(listKdBarang.toArray()));
    }
    
    private void seleksiTableBarang(){
        tableBarangDialog.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableBarangDialog.getSelectedRow();       
                if(baris != -1){                        
                    cmbDKdBarang.setSelectedItem(tableBarangDialog.getValueAt(baris, 0));
                    txtDHrgbeli.setText(tableBarangDialog.getValueAt(baris, 3).toString());
                    txtDHrgJual.setText(tableBarangDialog.getValueAt(baris, 4).toString());
                }
            }
        });
    }
    
    private void tableDetailBarang(){
        modelDetail=(DefaultTableModel) tableDetailBarang.getModel();
    }
    
    private void tampilDetail(){
        Object[] obj=new Object[5];  
        obj[1]=cmbDKdBarang.getSelectedItem();
        obj[2]=txtDQty.getText(); 
        obj[3]=txtDHrgbeli.getText(); 
        obj[4]=txtDHrgJual.getText(); 
        modelDetail.addRow(obj); 
        noTable();
    }
    private void noTable(){
        int row=modelDetail.getRowCount(); 
        for(int a=0; a<row ;a++){
            String no=String.valueOf(a+1);
            tableDetailBarang.setValueAt(no, a, 0); 
        }
    }
    private void hapusList(){
        int pilih=tableDetailBarang.getSelectedRow();
        if(pilih==-1){
            JOptionPane.showMessageDialog(null, "Pilih barang yang mau dihapus!");
        }else{
            modelDetail.removeRow(pilih);
        }
        noTable(); 
    }
    private void clearList(){
        int row=tableDetailBarang.getRowCount();
        while(row>0){
            row--;
            modelDetail.removeRow(row);
        }
    }
    
    private void totalQty(){
        totalQty=0;
        int baris=tableDetailBarang.getRowCount();
        if(baris==-1){
            txtTotalQty.setText(String.valueOf(totalQty));
        }else{
            for(int i=0; i<baris;i++){
                qty=Integer.parseInt(tableDetailBarang.getValueAt(i, 2).toString());
                totalQty+=qty;
            }
            txtTotalQty.setText(String.valueOf(totalQty));
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

        barangDialog = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtDQty = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDNamaSupplier = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtDKdSupplier = new javax.swing.JTextField();
        cmbDKdBarang = new javax.swing.JComboBox<>();
        txtDHrgbeli = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDHrgJual = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableBarangDialog = new javax.swing.JTable();
        PilihBarang = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTglBuat = new javax.swing.JTextField();
        txtTitleDetailPO = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtNoPO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jdcTglKirim = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        cmbSupplier = new javax.swing.JComboBox<>();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetailBarang = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnHapusDetail = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTotalQty = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        barangDialog.setTitle("Cari Barang");
        barangDialog.setAlwaysOnTop(true);
        barangDialog.setBackground(new java.awt.Color(255, 255, 255));
        barangDialog.setMinimumSize(new java.awt.Dimension(879, 380));
        barangDialog.setModal(true);

        jPanel2.setBackground(new java.awt.Color(255, 118, 117));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pilih Barang");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari Barang");

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Order Qty");

        txtDQty.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDQty.setForeground(new java.awt.Color(51, 51, 51));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nama Supplier");

        txtDNamaSupplier.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDNamaSupplier.setForeground(new java.awt.Color(51, 51, 51));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Kode Supplier");

        txtDKdSupplier.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDKdSupplier.setForeground(new java.awt.Color(51, 51, 51));

        cmbDKdBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmbDKdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDKdBarangActionPerformed(evt);
            }
        });

        txtDHrgbeli.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDHrgbeli.setForeground(new java.awt.Color(51, 51, 51));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Harga Beli");

        txtDHrgJual.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDHrgJual.setForeground(new java.awt.Color(51, 51, 51));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Harga Jual");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDKdSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbDKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDHrgbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDQty, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDHrgJual, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(163, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtDKdSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbDKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtDHrgbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtDQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtDHrgJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableBarangDialog.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableBarangDialog.setForeground(new java.awt.Color(51, 51, 51));
        tableBarangDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Jenis Barang", "Harga Beli", "Harga Jual", "Kode Supplier", "Nama Supplier", "Kota"
            }
        ));
        jScrollPane3.setViewportView(tableBarangDialog);

        PilihBarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        PilihBarang.setText("Pilih Barang");
        PilihBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PilihBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barangDialogLayout = new javax.swing.GroupLayout(barangDialog.getContentPane());
        barangDialog.getContentPane().setLayout(barangDialogLayout);
        barangDialogLayout.setHorizontalGroup(
            barangDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
            .addGroup(barangDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PilihBarang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        barangDialogLayout.setVerticalGroup(
            barangDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barangDialogLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PilihBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 118, 117));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tgl. Buat");

        txtTglBuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTglBuat.setForeground(new java.awt.Color(51, 51, 51));

        txtTitleDetailPO.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtTitleDetailPO.setForeground(new java.awt.Color(255, 255, 255));
        txtTitleDetailPO.setText("Buat PO Baru");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No. PO");

        txtNoPO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoPO.setForeground(new java.awt.Color(51, 51, 51));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tgl. Kirim");

        jdcTglKirim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Supplier");

        cmbSupplier.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSupplierActionPerformed(evt);
            }
        });

        btnKembali.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnKembali.setText("Kembali");
        btnKembali.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTitleDetailPO)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTglBuat, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcTglKirim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembali))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitleDetailPO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKembali)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNoPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTglBuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdcTglKirim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        tableDetailBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableDetailBarang.setForeground(new java.awt.Color(51, 51, 51));
        tableDetailBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor", "Kode Barang", "Qty Order", "Harga Beli", "Harga Jual"
            }
        ));
        jScrollPane1.setViewportView(tableDetailBarang);
        if (tableDetailBarang.getColumnModel().getColumnCount() > 0) {
            tableDetailBarang.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        btnTambah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTambah.setText("Tambah Barang");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapusDetail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHapusDetail.setText("Hapus Barang");
        btnHapusDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusDetailActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Total Qty :");

        txtTotalQty.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTotalQty.setForeground(new java.awt.Color(51, 51, 51));
        txtTotalQty.setText("0");

        jSeparator3.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator3.setForeground(new java.awt.Color(51, 51, 51));

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
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapusDetail)
                        .addGap(0, 731, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnHapusDetail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTotalQty))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        if(kembaliPenerimaBarang == true){
            FormPenerimaanBarang fpb = new FormPenerimaanBarang(userLogin);
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(fpb);
            fpb.setVisible(true);
            this.dispose();
        }
        FormPurchaseOrder formPO = new FormPurchaseOrder(userLogin);
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(formPO);
        formPO.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        supplier = sCont.findSupplierByName(cmbSupplier.getSelectedItem().toString());
        txtDKdSupplier.setText(supplier.getKdSupplier());
        txtDNamaSupplier.setText(supplier.getNmSupplier());
        dataBarangDialog(supplier.getKdSupplier());
        tableBarangDialog.setModel(bCont.searchBarang(model, supplier.getKdSupplier()));
        barangDialog.setVisible(true);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void cmbDKdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDKdBarangActionPerformed
        barang = bCont.findBarang(cmbDKdBarang.getSelectedItem().toString());
        txtDHrgbeli.setText(String.valueOf(barang.getHrgBeli()));
        txtDHrgJual.setText(String.valueOf(barang.getHrgJual()));
        txtDQty.requestFocus();
    }//GEN-LAST:event_cmbDKdBarangActionPerformed

    private void cmbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSupplierActionPerformed
        int baris=tableDetailBarang.getRowCount();
        if(baris > 0){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Data supplier berubah!");
            clearList();
        }
    }//GEN-LAST:event_cmbSupplierActionPerformed

    private void PilihBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PilihBarangActionPerformed
        int baris=tableDetailBarang.getRowCount();
        String data="";
        boolean eksis=false;
        int update=-1;
        if(txtDHrgbeli.getText().isEmpty() || txtDHrgJual.getText().isEmpty() || txtDQty.getText().isEmpty()){
            barangDialog.dispose();
        }else{
            for(int i = 0; i < baris; i++){
                data=tableDetailBarang.getValueAt(i, 1).toString();
                if(data.equalsIgnoreCase(cmbDKdBarang.getSelectedItem().toString())){
                    eksis=true;
                    update=i;
                    break;
                }
            }
            
            if(!eksis){
                tampilDetail();
                totalQty();
                barangDialog.dispose();
            }else{
                tableDetailBarang.setValueAt(txtDQty.getText(), update, 2);
                totalQty();
                barangDialog.dispose();
            }
            
        }
    }//GEN-LAST:event_PilihBarangActionPerformed

    private void btnHapusDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusDetailActionPerformed
        hapusList();
        totalQty();
    }//GEN-LAST:event_btnHapusDetailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PilihBarang;
    private javax.swing.JDialog barangDialog;
    private javax.swing.JButton btnHapusDetail;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbDKdBarang;
    private javax.swing.JComboBox<String> cmbSupplier;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private com.toedter.calendar.JDateChooser jdcTglKirim;
    private javax.swing.JTable tableBarangDialog;
    private javax.swing.JTable tableDetailBarang;
    private javax.swing.JTextField txtDHrgJual;
    private javax.swing.JTextField txtDHrgbeli;
    private javax.swing.JTextField txtDKdSupplier;
    private javax.swing.JTextField txtDNamaSupplier;
    private javax.swing.JTextField txtDQty;
    private javax.swing.JTextField txtNoPO;
    private javax.swing.JTextField txtTglBuat;
    private javax.swing.JLabel txtTitleDetailPO;
    private javax.swing.JLabel txtTotalQty;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {

    }

    @Override
    public void bersih() {

    }

    @Override
    public void simpan() {
        int baris=tableDetailBarang.getRowCount();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(baris == 0){
            JOptionPane.showMessageDialog(null, "Daftar barang masih kosong!");
        }else if(jdcTglKirim.getDate() == null){
            JOptionPane.showMessageDialog(null, "Masukan Tanggal Kirim!");
        }else{
            if(jdcTglKirim.equals("")){
                JOptionPane.showMessageDialog(null, "Masukan tanggal kirim!");
            }else{
                try {
                    detailOrder.setNoPO(txtNoPO.getText());
                    for(int i=0;i<baris;i++){
                        detailOrder.setKdBarang(tableDetailBarang.getValueAt(i, 1).toString());
                        detailOrder.setQtyOrder(Integer.parseInt(tableDetailBarang.getValueAt(i, 2).toString()));
                        detailOrder.setHargaBeli(Double.parseDouble(tableDetailBarang.getValueAt(i, 3).toString()));
                        detailOrder.setHargaJual(Double.parseDouble(tableDetailBarang.getValueAt(i, 4).toString()));
                        detailCont.save(detailOrder);
                    }
                    
                    poSession = new PurchaseOrder();
                    poSession.setNoPO(txtNoPO.getText());
                    poSession.setTglPO(sdf.parse(txtTglBuat.getText()));
                    poSession.setTglKirim(jdcTglKirim.getDate());
                    poSession.setKdSupplier(supplier.getKdSupplier());
                    poSession.setTotalQty(Integer.parseInt(txtTotalQty.getText()));
                    poSession.setUsername(userLogin.getUsername());
                    poCont.save(poSession);
                    
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                    FormPurchaseOrder formPO = new FormPurchaseOrder(userLogin);
                    JDesktopPane desktopPane = getDesktopPane();
                    desktopPane.add(formPO);
                    formPO.setVisible(true);
                    this.dispose();
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
