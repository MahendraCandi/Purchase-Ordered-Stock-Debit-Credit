package fladeoapp.form;

import fladeoapp.FladeoApp;
import fladeoapp.controller.JurnalController;
import fladeoapp.data.Jurnal;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class FormJurnalHeader extends javax.swing.JInternalFrame {

    JurnalController jCont = new JurnalController(FladeoApp.emf);
    DefaultTableModel model;
    FormUtama formUtama = FormUtama.staticUtama;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
    Jurnal jurnal = new Jurnal();
    /**
     * Creates new form FormJurnalHeader
     */
    public FormJurnalHeader() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        formUtama.buttonOff();
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("No.Jurnal");
        model.addColumn("No. Pembayaran");
        model.addColumn("Tgl. Jurnal");
        model.addColumn("Keterangan");
        tableJurnal.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        showTable();
        renderTableKas();
    }
    
    private void showTable(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        List<Jurnal> listKas = jCont.findAllTransaksiPembelian();
        for(Jurnal item : listKas){
            Object[] obj = new Object[4];
            obj[0] = item.getNoJurnal();
            obj[1] = item.getNoPembayaran();
            obj[2] = (Date) item.getTglJurnal();
            obj[3] = item.getKet();
            model.addRow(obj);
        }
        tableJurnal.setModel(model);
    }
    
    private void showTableSearch(String cari){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        List<Jurnal> listKas = jCont.searchTransaksiPembelian(cari);
        for(Jurnal item : listKas){
            Object[] obj = new Object[4];
            obj[0] = item.getNoJurnal();
            obj[1] = item.getNoPembayaran();
            obj[2] = (Date) item.getTglJurnal();
            obj[3] = item.getKet();
            model.addRow(obj);
        }
        tableJurnal.setModel(model);
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
        tableJurnal.getColumnModel().getColumn(2).setCellRenderer(tbr);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelJurnal = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableJurnal = new javax.swing.JTable();
        btnTambahJurnal = new javax.swing.JButton();
        btnLihatDetail = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();

        getContentPane().setLayout(new java.awt.CardLayout());

        panelJurnal.setBackground(new java.awt.Color(255, 118, 117));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Form Jurnal");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        tableJurnal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableJurnal.setForeground(new java.awt.Color(51, 51, 51));
        tableJurnal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Transaksi", "No. Tanda Terima", "No. Invoice", "Total Transaksi"
            }
        ));
        jScrollPane4.setViewportView(tableJurnal);

        btnTambahJurnal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTambahJurnal.setText("Tambah Jurnal");
        btnTambahJurnal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahJurnalActionPerformed(evt);
            }
        });

        btnLihatDetail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLihatDetail.setText("Lihat Detail");
        btnLihatDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatDetailActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cari");

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelJurnalLayout = new javax.swing.GroupLayout(panelJurnal);
        panelJurnal.setLayout(panelJurnalLayout);
        panelJurnalLayout.setHorizontalGroup(
            panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJurnalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(panelJurnalLayout.createSequentialGroup()
                        .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelJurnalLayout.createSequentialGroup()
                        .addComponent(btnTambahJurnal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLihatDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelJurnalLayout.setVerticalGroup(
            panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJurnalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahJurnal)
                    .addComponent(btnLihatDetail)
                    .addComponent(jLabel1)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelJurnal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahJurnalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahJurnalActionPerformed
        jurnal = null;
        FormJurnal fj = new FormJurnal(jurnal);
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(fj);
        fj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTambahJurnalActionPerformed

    private void btnLihatDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatDetailActionPerformed
        int row = tableJurnal.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(null, "Pilih data yang mau dilihat!");
        }else{
            jurnal = jCont.findJurnal(tableJurnal.getValueAt(row, 0).toString());
            FormJurnal fj = new FormJurnal(jurnal);
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(fj);
            fj.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLihatDetailActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(txtCari.getText().isEmpty()){
                showTable();
            }else{
                showTableSearch(txtCari.getText());
            }
        }
    }//GEN-LAST:event_txtCariKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLihatDetail;
    private javax.swing.JButton btnTambahJurnal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel panelJurnal;
    private javax.swing.JTable tableJurnal;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
