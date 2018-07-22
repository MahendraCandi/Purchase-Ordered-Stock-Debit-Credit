package fladeoapp.form;

import fladeoapp.FladeoApp;
import fladeoapp.controller.CetakLaporanController;
import fladeoapp.controller.PenerimaanBarangController;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormLaporan extends javax.swing.JInternalFrame {

    PenerimaanBarangController pbCont = new PenerimaanBarangController(FladeoApp.emf);
    CetakLaporanController laporanCont = new CetakLaporanController(FladeoApp.emf);
    DefaultTableModel pbModel;
    String tampil;
    /**
     * Creates new form FormLaporan
     */
    public FormLaporan(String tampil) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        this.tampil = tampil;
        tampilTable();
    }
    
    private void tampilTable(){
        if(tampil.equalsIgnoreCase("PenerimaanBarang")){
            tablePenerimaanBarang();
            formatTglPb();
            showTablePenerimaanBarang();
            System.out.println("BoomPb");
        }else if(tampil.equalsIgnoreCase("Other")){
            System.out.println("Boom");
        }
    }
    
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

        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPenerimaanBarang.setBackground(new java.awt.Color(255, 118, 117));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Laporan");

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTampilDataPb;
    private javax.swing.JButton cetakLaporan1;
    private javax.swing.JButton cetakLaporanPb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.toedter.calendar.JDateChooser jdcAkhir1;
    private com.toedter.calendar.JDateChooser jdcMulai1;
    private com.toedter.calendar.JDateChooser jdcPb1;
    private com.toedter.calendar.JDateChooser jdcPb2;
    private javax.swing.JPanel panelOther;
    private javax.swing.JPanel panelPenerimaanBarang;
    private javax.swing.JTable tablePO;
    private javax.swing.JTable tablePO1;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JPanel getPanelOther() {
        return panelOther;
    }

    public javax.swing.JPanel getPanelPenerimaanBarang() {
        return panelPenerimaanBarang;
    }
}
