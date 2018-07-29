package fladeoapp.form;

import fladeoapp.FladeoApp;
import fladeoapp.controller.CetakLaporanController;
import fladeoapp.controller.JurnalController;
import fladeoapp.controller.PenerimaanBarangController;
import fladeoapp.controller.TransaksiPembelianController;
import fladeoapp.data.Jurnal;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class FormLaporan extends javax.swing.JInternalFrame {

    PenerimaanBarangController pbCont = new PenerimaanBarangController(FladeoApp.emf);
    TransaksiPembelianController trCont = new TransaksiPembelianController(FladeoApp.emf);
    CetakLaporanController laporanCont = new CetakLaporanController(FladeoApp.emf);
    JurnalController jCont = new JurnalController(FladeoApp.emf);
    DefaultTableModel pbModel, trModel, kasModel;
    String tampil;
    DecimalFormat myFormatter = new DecimalFormat("###,###.##");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
    FormUtama formUtama = FormUtama.staticUtama;
    /**
     * Creates new form FormLaporan
     */
    public FormLaporan(String tampil) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        this.tampil = tampil;
        formUtama.buttonOff();
        tampilTable();
    }
    
    private void tampilTable(){
        if(tampil.equalsIgnoreCase("PenerimaanBarang")){
            tablePenerimaanBarang();
            formatTglPb();
            showTablePenerimaanBarang();
        }else if(tampil.equalsIgnoreCase("Transaksi")){
            tableTransaksi();
            formatTglTransaksi();
            showTableTransaksi();
            renderTableTransaksiTotal();
        }else if(tampil.equalsIgnoreCase("Jurnal")){
            tableKas();
            formatTglKas();
            showTableKas();
            renderTableKas();
        }else if(tampil.equalsIgnoreCase("Other")){
            System.out.println("Boom");
        }
    }
    
    /*
        Panel penerimaan barang
    */
    private void tablePenerimaanBarang(){
        pbModel=new DefaultTableModel();
        pbModel.addColumn("No. Tanda Terima");
        pbModel.addColumn("Tgl. Terima Barang");
        pbModel.addColumn("Nomor PO");
        pbModel.addColumn("Tgl. Buat");
        pbModel.addColumn("Tgl. Kirim");
        pbModel.addColumn("Supplier");
        pbModel.addColumn("Total Qty");
        pbModel.addColumn("Username");
        tablePO.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
    }
    
    private void showTablePenerimaanBarang(){
        pbModel.getDataVector().removeAllElements();
        pbModel.fireTableDataChanged();
        List<Object[]> listPO = pbCont.findAllPenerimaanBarang(jdcPb1.getDate(), jdcPb2.getDate());
        for(Object[] po : listPO){
            pbModel.addRow(po);
        }
        tablePO.setModel(pbModel);
    }
    
    private void formatTglPb(){
        jdcPb1.setMaxSelectableDate(new Date());
        jdcPb2.setMaxSelectableDate(new Date());
        jdcPb1.setLocale(Locale.forLanguageTag("id-ID"));
        jdcPb2.setLocale(Locale.forLanguageTag("id-ID"));
        firstDateLastDatePb();
        jdcPb1.setDateFormatString("dd MMMM yyyy");
        jdcPb2.setDateFormatString("dd MMMM yyyy");
    }
    
    private void firstDateLastDatePb(){
        Object[] obj=pbCont.firstDateLastDate();
        if(obj[0]==null && obj[1]==null){
            jdcPb1.setDate(new Date());
            jdcPb2.setDate(new Date());
        }else{
            jdcPb1.setDate((Date) obj[0]);
            jdcPb2.setDate((Date) obj[1]);
        }
    }
    /*
        Akhir penerimaan barang
    */
    
    /*
        Transaksi 
    */
    private void tableTransaksi(){
        trModel=new DefaultTableModel();
        trModel.addColumn("No.Transaksi");
        trModel.addColumn("No. Invoice");
        trModel.addColumn("No. Tanda Terima");
        trModel.addColumn("Tgl Terima Barang");
        trModel.addColumn("No. PO");
        trModel.addColumn("Supplier");
        trModel.addColumn("Tgl. PO");
        trModel.addColumn("Tgl. Kirim");
        trModel.addColumn("Total Qty");
        trModel.addColumn("Total Beli");
        trModel.addColumn("Total Transaksi");
        tableTransaksi.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
    }
    
    private void showTableTransaksi(){
        trModel.getDataVector().removeAllElements();
        trModel.fireTableDataChanged();
        List<Object[]> listTransaksi = trCont.findAllTransaksiPembelianByDate(jdcTransaksiMulai.getDate(), jdcTransaksiAkhir.getDate());
        for(Object[] item : listTransaksi){
            trModel.addRow(item);
        }
        tableTransaksi.setModel(trModel);
    }
    
    private void formatTglTransaksi(){
        jdcTransaksiMulai.setMaxSelectableDate(new Date());
        jdcTransaksiAkhir.setMaxSelectableDate(new Date());
        jdcTransaksiMulai.setLocale(Locale.forLanguageTag("id-ID"));
        jdcTransaksiAkhir.setLocale(Locale.forLanguageTag("id-ID"));
        firstDateLastDateTransaksi();
        jdcTransaksiMulai.setDateFormatString("dd MMMM yyyy");
        jdcTransaksiAkhir.setDateFormatString("dd MMMM yyyy");
    }
    
    private void firstDateLastDateTransaksi(){
        Object[] obj=trCont.firstDateLastDate();
        if(obj[0]==null && obj[1]==null){
            jdcTransaksiMulai.setDate(new Date());
            jdcTransaksiAkhir.setDate(new Date());
        }else{
            jdcTransaksiMulai.setDate((Date) obj[0]);
            jdcTransaksiAkhir.setDate((Date) obj[1]);
        }
    }
    
    private void renderTableTransaksiTotal(){
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
        tableTransaksi.getColumnModel().getColumn(8).setCellRenderer(tbr);
        tableTransaksi.getColumnModel().getColumn(9).setCellRenderer(tbr);
        tableTransaksi.getColumnModel().getColumn(10).setCellRenderer(tbr);
    }
    /*
        Akhir Transaksi
    */
    
    /*
        Kas
    */
    private void tableKas(){
        kasModel=new DefaultTableModel();
        kasModel.addColumn("No.Jurnal");
        kasModel.addColumn("No. Pembayaran");
        kasModel.addColumn("Tgl. Jurnal");
        kasModel.addColumn("Keterangan");
        tableKas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
    }
    
    private void showTableKas(){
        kasModel.getDataVector().removeAllElements();
        kasModel.fireTableDataChanged();
        List<Jurnal> listKas = jCont.findAllTransaksiPembelianByDate(jdcKasMulai.getDate(), jdcKasAkhir.getDate());
        for(Jurnal item : listKas){
            Object[] obj = new Object[4];
            obj[0] = item.getNoJurnal();
            obj[1] = item.getNoPembayaran();
            obj[2] = (Date) item.getTglJurnal();
            obj[3] = item.getKet();
            kasModel.addRow(obj);
        }
        tableKas.setModel(kasModel);
    }
//    
    private void formatTglKas(){
        jdcKasMulai.setMaxSelectableDate(new Date());
        jdcKasAkhir.setMaxSelectableDate(new Date());
        jdcKasMulai.setLocale(Locale.forLanguageTag("id-ID"));
        jdcKasAkhir.setLocale(Locale.forLanguageTag("id-ID"));
        firstDateLastDateKas();
        jdcKasMulai.setDateFormatString("dd MMMM yyyy");
        jdcKasAkhir.setDateFormatString("dd MMMM yyyy");
    }
    
    private void firstDateLastDateKas(){
        Object[] obj=jCont.firstDateLasDate();
        if(obj[0]==null && obj[1]==null){
            jdcKasMulai.setDate(new Date());
            jdcKasAkhir.setDate(new Date());
        }else{
            jdcKasMulai.setDate((Date) obj[0]);
            jdcKasAkhir.setDate((Date) obj[1]);
        }
    }
    
    private void renderTableKas(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Date){
                    value = sdf.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tableKas.getColumnModel().getColumn(2).setCellRenderer(tbr);
    }
    
    /*
        Akhir Kas
    */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPenerimaanBarang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cetakLaporanPb = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePO = new javax.swing.JTable();
        jdcPb1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jdcPb2 = new com.toedter.calendar.JDateChooser();
        btnTampilDataPb = new javax.swing.JButton();
        panelOther = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        cetakLaporan1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePO1 = new javax.swing.JTable();
        jdcMulai1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jdcAkhir1 = new com.toedter.calendar.JDateChooser();
        panelTransaksi = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        cetakLaporanTransaksi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
        jdcTransaksiMulai = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jdcTransaksiAkhir = new com.toedter.calendar.JDateChooser();
        btnTampilDataTransaksi = new javax.swing.JButton();
        panelJurnal = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        cetakLaporanKas = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableKas = new javax.swing.JTable();
        jdcKasMulai = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jdcKasAkhir = new com.toedter.calendar.JDateChooser();
        btnTampilDataKas = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPenerimaanBarang.setBackground(new java.awt.Color(255, 118, 117));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Laporan Penerimaan Barang");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal mulai");

        cetakLaporanPb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cetakLaporanPb.setText("Cetak Laporan");
        cetakLaporanPb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakLaporanPbActionPerformed(evt);
            }
        });

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

        jdcPb1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanggal Akhir");

        jdcPb2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTampilDataPb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTampilDataPb.setText("Tampil Data");
        btnTampilDataPb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilDataPbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPenerimaanBarangLayout = new javax.swing.GroupLayout(panelPenerimaanBarang);
        panelPenerimaanBarang.setLayout(panelPenerimaanBarangLayout);
        panelPenerimaanBarangLayout.setHorizontalGroup(
            panelPenerimaanBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPenerimaanBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPenerimaanBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPenerimaanBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelPenerimaanBarangLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcPb1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcPb2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTampilDataPb, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cetakLaporanPb, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelPenerimaanBarangLayout.setVerticalGroup(
            panelPenerimaanBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPenerimaanBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelPenerimaanBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPenerimaanBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPenerimaanBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcPb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jdcPb2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPenerimaanBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cetakLaporanPb)
                        .addComponent(btnTampilDataPb)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
        );

        getContentPane().add(panelPenerimaanBarang, "card2");

        panelOther.setBackground(new java.awt.Color(255, 118, 117));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Form Laporan LAIN");

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tanggal mulai");

        cetakLaporan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cetakLaporan1.setText("Cetak Laporan");
        cetakLaporan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakLaporan1ActionPerformed(evt);
            }
        });

        tablePO1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablePO1.setForeground(new java.awt.Color(51, 51, 51));
        tablePO1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Tanda Terima", "Tgl. Terima Barang", "Nomor PO", "Tgl. Buat", "Tgl. Kirim", "Supplier", "Total Qty", "Username"
            }
        ));
        jScrollPane2.setViewportView(tablePO1);

        jdcMulai1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tanggal Akhir");

        jdcAkhir1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelOtherLayout = new javax.swing.GroupLayout(panelOther);
        panelOther.setLayout(panelOtherLayout);
        panelOtherLayout.setHorizontalGroup(
            panelOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOtherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelOtherLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcMulai1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcAkhir1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cetakLaporan1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelOtherLayout.setVerticalGroup(
            panelOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOtherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(jdcMulai1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcAkhir1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cetakLaporan1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
        );

        getContentPane().add(panelOther, "card3");

        panelTransaksi.setBackground(new java.awt.Color(255, 118, 117));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Form Laporan Transaksi");

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tanggal mulai");

        cetakLaporanTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cetakLaporanTransaksi.setText("Cetak Laporan");
        cetakLaporanTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakLaporanTransaksiActionPerformed(evt);
            }
        });

        tableTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableTransaksi.setForeground(new java.awt.Color(51, 51, 51));
        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Transaksi", "No. Tanda Terima", "No. Invoice", "Total Transaksi"
            }
        ));
        jScrollPane3.setViewportView(tableTransaksi);

        jdcTransaksiMulai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tanggal Akhir");

        jdcTransaksiAkhir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTampilDataTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTampilDataTransaksi.setText("Tampil Data");
        btnTampilDataTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilDataTransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTransaksiLayout = new javax.swing.GroupLayout(panelTransaksi);
        panelTransaksi.setLayout(panelTransaksiLayout);
        panelTransaksiLayout.setHorizontalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelTransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcTransaksiMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcTransaksiAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTampilDataTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cetakLaporanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelTransaksiLayout.setVerticalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcTransaksiMulai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jdcTransaksiAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cetakLaporanTransaksi)
                        .addComponent(btnTampilDataTransaksi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
        );

        getContentPane().add(panelTransaksi, "card2");

        panelJurnal.setBackground(new java.awt.Color(255, 118, 117));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Form Laporan Pengeluaran Kas");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tanggal mulai");

        cetakLaporanKas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cetakLaporanKas.setText("Cetak Laporan");
        cetakLaporanKas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakLaporanKasActionPerformed(evt);
            }
        });

        tableKas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableKas.setForeground(new java.awt.Color(51, 51, 51));
        tableKas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Transaksi", "No. Tanda Terima", "No. Invoice", "Total Transaksi"
            }
        ));
        jScrollPane4.setViewportView(tableKas);

        jdcKasMulai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tanggal Akhir");

        jdcKasAkhir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTampilDataKas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTampilDataKas.setText("Tampil Data");
        btnTampilDataKas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilDataKasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelJurnalLayout = new javax.swing.GroupLayout(panelJurnal);
        panelJurnal.setLayout(panelJurnalLayout);
        panelJurnalLayout.setHorizontalGroup(
            panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJurnalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelJurnalLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcKasMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcKasAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTampilDataKas, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cetakLaporanKas, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelJurnalLayout.setVerticalGroup(
            panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJurnalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcKasMulai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jdcKasAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cetakLaporanKas)
                        .addComponent(btnTampilDataKas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
        );

        getContentPane().add(panelJurnal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cetakLaporanPbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakLaporanPbActionPerformed

        int baris = tablePO.getRowCount();
        if(jdcPb2.getCalendar().before(jdcPb1.getCalendar())){
            JOptionPane.showMessageDialog(null, "Tanggal Tidak Valid!");
        }else{
            if(baris == -1){
                JOptionPane.showMessageDialog(null, "Tidak ada data yang tampil!");
            }else{
                laporanCont.cetakPenerimaanBarang(jdcPb1.getDate(), jdcPb2.getDate());
            }
        }
    }//GEN-LAST:event_cetakLaporanPbActionPerformed

    private void cetakLaporan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakLaporan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cetakLaporan1ActionPerformed

    private void btnTampilDataPbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilDataPbActionPerformed
        if(jdcPb2.getCalendar().before(jdcPb1.getCalendar())){
            JOptionPane.showMessageDialog(null, "Tanggal Tidak Valid!");
        }else{
            showTablePenerimaanBarang();
        }
    }//GEN-LAST:event_btnTampilDataPbActionPerformed

    private void cetakLaporanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakLaporanTransaksiActionPerformed
        int baris = tableTransaksi.getRowCount();
        if(jdcTransaksiAkhir.getCalendar().before(jdcTransaksiMulai.getCalendar())){
            JOptionPane.showMessageDialog(null, "Tanggal Tidak Valid!");
        }else{
            if(baris == -1){
                JOptionPane.showMessageDialog(null, "Tidak ada data yang tampil!");
            }else{
                laporanCont.cetakTransaksi(jdcTransaksiMulai.getDate(), jdcTransaksiAkhir.getDate());
            }
        }
    }//GEN-LAST:event_cetakLaporanTransaksiActionPerformed

    private void btnTampilDataTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilDataTransaksiActionPerformed
        if(jdcTransaksiAkhir.getCalendar().before(jdcTransaksiMulai.getCalendar())){
            JOptionPane.showMessageDialog(null, "Tanggal Tidak Valid!");
        }else{
            showTableTransaksi();
        }
    }//GEN-LAST:event_btnTampilDataTransaksiActionPerformed

    private void cetakLaporanKasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakLaporanKasActionPerformed
        int baris = tableKas.getRowCount();
        if(jdcKasAkhir.getCalendar().before(jdcKasMulai.getCalendar())){
            JOptionPane.showMessageDialog(null, "Tanggal Tidak Valid!");
        }else{
            if(baris == -1){
                JOptionPane.showMessageDialog(null, "Tidak ada data yang tampil!");
            }else{
                laporanCont.cetakPengeluaranKas(jdcKasMulai.getDate(), jdcKasAkhir.getDate());
            }
        }
    }//GEN-LAST:event_cetakLaporanKasActionPerformed

    private void btnTampilDataKasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilDataKasActionPerformed
        if(jdcKasAkhir.getCalendar().before(jdcKasMulai.getCalendar())){
            JOptionPane.showMessageDialog(null, "Tanggal Tidak Valid!");
        }else{
            showTableKas();
        }
    }//GEN-LAST:event_btnTampilDataKasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTampilDataKas;
    private javax.swing.JButton btnTampilDataPb;
    private javax.swing.JButton btnTampilDataTransaksi;
    private javax.swing.JButton cetakLaporan1;
    private javax.swing.JButton cetakLaporanKas;
    private javax.swing.JButton cetakLaporanPb;
    private javax.swing.JButton cetakLaporanTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private com.toedter.calendar.JDateChooser jdcAkhir1;
    private com.toedter.calendar.JDateChooser jdcKasAkhir;
    private com.toedter.calendar.JDateChooser jdcKasMulai;
    private com.toedter.calendar.JDateChooser jdcMulai1;
    private com.toedter.calendar.JDateChooser jdcPb1;
    private com.toedter.calendar.JDateChooser jdcPb2;
    private com.toedter.calendar.JDateChooser jdcTransaksiAkhir;
    private com.toedter.calendar.JDateChooser jdcTransaksiMulai;
    private javax.swing.JPanel panelJurnal;
    private javax.swing.JPanel panelOther;
    private javax.swing.JPanel panelPenerimaanBarang;
    private javax.swing.JPanel panelTransaksi;
    private javax.swing.JTable tableKas;
    private javax.swing.JTable tablePO;
    private javax.swing.JTable tablePO1;
    private javax.swing.JTable tableTransaksi;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JPanel getPanelOther() {
        return panelOther;
    }

    public javax.swing.JPanel getPanelPenerimaanBarang() {
        return panelPenerimaanBarang;
    }

    public JPanel getPanelTransaksi() {
        return panelTransaksi;
    }

    public JPanel getPanelJurnal() {
        return panelJurnal;
    }
    
    
}
