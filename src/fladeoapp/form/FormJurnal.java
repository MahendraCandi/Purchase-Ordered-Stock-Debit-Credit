package fladeoapp.form;

import fladeoapp.FladeoApp;
import fladeoapp.controller.DataPerkiraanController;
import fladeoapp.controller.DetailJurnalController;
import fladeoapp.controller.JurnalController;
import fladeoapp.controller.PelunasanPembayaranController;
import fladeoapp.data.DataPerkiraan;
import fladeoapp.data.DetailJurnal;
import fladeoapp.data.Jurnal;
import fladeoapp.data.PelunasanPembayaran;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FormJurnal extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    Jurnal jurnal  = new Jurnal();
    DetailJurnal detailJurnal = new DetailJurnal();
    PelunasanPembayaran pembayaran = new PelunasanPembayaran();
    DataPerkiraan perkiraan = new DataPerkiraan();
    JurnalController jCont = new JurnalController(FladeoApp.emf);
    DetailJurnalController detailCont = new DetailJurnalController(FladeoApp.emf);
    PelunasanPembayaranController pbCont = new PelunasanPembayaranController(FladeoApp.emf);
    DataPerkiraanController pCont = new DataPerkiraanController(FladeoApp.emf);
    DefaultTableModel model;
    FormUtama formUtama = FormUtama.staticUtama;
    String kodePerkiraan1, kodePerkiraan2;
    double debit1, debit2, kredit1, kredit2;
    DecimalFormat myFormatter = new DecimalFormat("###,###.##");
    Number number;
    
    /**
     * Creates new form FormJurnal
     */
    public FormJurnal(Jurnal jurnal) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model = (DefaultTableModel) tableJurnal.getModel();
        this.jurnal = jurnal;
        tableJurnal.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tidakAktif();
        validasiJurnal();
    }
    
    private void validasiJurnal(){
        if(jurnal != null){
            formUtama.buttonOff();
            txtNoJurnal.setText(jurnal.getNoJurnal());
            jdcTgl.setDate(jurnal.getTglJurnal());
            jdcTgl.setLocale(Locale.forLanguageTag("id-ID"));
            jdcTgl.setDateFormatString("dd MMMM yyyy");
            Object[] objNo = {jurnal.getNoPembayaran()};
            cmbPembayaran.setModel(new DefaultComboBoxModel(objNo));
            txtKeterangan.setText(jurnal.getKet());
            panelJurnal.setVisible(false);
            btnTambah.setVisible(false);
            btnHapus.setVisible(false);
            List<Object[]> list = detailCont.findDetailToListByKode(jurnal.getNoJurnal());
            int i = 1;
            for(Object[] item : list){
                detailJurnal = (DetailJurnal) item[0];
                Object[] obj = new Object[5];
                obj[0] = i;
                obj[1] = detailJurnal.getKdPerkiraan();
                obj[2] = (String) item[1];
                obj[3] = myFormatter.format(detailJurnal.getDebet());
                obj[4] = myFormatter.format(detailJurnal.getKredit());
                model.addRow(obj);
                i++;
            }
        }
    }
    
    
    private void tidakAktif(){
        txtNoJurnal.setEnabled(false);
        jdcTgl.setEnabled(false);
        cmbPembayaran.setEnabled(false);
        txtKeterangan.setEnabled(false);
        txtKdPerkiraan1.setEnabled(false);
        txtKdPerkiraan2.setEnabled(false);
        cmbNmPerkiraan1.setEnabled(false);
        cmbNmPerkiraan2.setEnabled(false);
        txtDebit1.setEnabled(false);
        txtDebit2.setEnabled(false);
        txtKredit1.setEnabled(false);
        txtKredit2.setEnabled(false);
        btnTambah.setEnabled(false);
        btnHapus.setEnabled(false);
        formUtama.buttonOff();
        formUtama.getTambahBtn().setEnabled(true);
    }

    private void comboBoxPembayaran(){
        List<PelunasanPembayaran> listPembayaran = pbCont.findAllPembayaranNotExistInJurnal();
        List<String> listNoPembayaran = new ArrayList<>();
        listNoPembayaran.add("Pilih");
        for(PelunasanPembayaran pp : listPembayaran){
            listNoPembayaran.add(pp.getNoPembayaran());
        }
        cmbPembayaran.setModel(new DefaultComboBoxModel(listNoPembayaran.toArray()));
    }
    
    private void comboBoxPerkiraan(){
        List<DataPerkiraan> listPerkiraan = pCont.findAllPerkiraan();
        List<String> listNmPerkiraan = new ArrayList<>();
        listNmPerkiraan.add("Pilih");
        for (DataPerkiraan dp  : listPerkiraan) {
            listNmPerkiraan.add(dp.getNmPerkiraan());
        }
        cmbNmPerkiraan1.setModel(new DefaultComboBoxModel(listNmPerkiraan.toArray()));
        cmbNmPerkiraan2.setModel(new DefaultComboBoxModel(listNmPerkiraan.toArray()));
        
    }
    
    private void findKodePerkiraan1(){
        perkiraan = new DataPerkiraan();
        if(cmbNmPerkiraan1.getSelectedItem().equals("Pilih")){
            kodePerkiraan1 = "";
            txtKdPerkiraan1.setText("");
            txtDebit1.setText("");
            txtKredit1.setText("");
        }else{
            kodePerkiraan1 = cmbNmPerkiraan1.getSelectedItem().toString();
            perkiraan = pCont.findOneDataPerkiraanByNama(kodePerkiraan1);
            txtKdPerkiraan1.setText(perkiraan.getKdPerkiraan());
        }
    }
    
    private void findKodePerkiraan2(){
        perkiraan = new DataPerkiraan();
        if(cmbNmPerkiraan2.getSelectedItem().equals("Pilih")){
            kodePerkiraan2 = "";
            txtKdPerkiraan2.setText("");
            txtDebit2.setText("");
            txtKredit2.setText("");
        }else{
            kodePerkiraan2 = cmbNmPerkiraan2.getSelectedItem().toString();
            perkiraan = pCont.findOneDataPerkiraanByNama(kodePerkiraan2);
            txtKdPerkiraan2.setText(perkiraan.getKdPerkiraan());
        }
    }
    
    private void findDebitKredit1(){
        String kode = cmbPembayaran.getSelectedItem().toString();
        pembayaran = pbCont.findPelunasanPembayaran(kode);
        debit1 = pembayaran.getTotalBayar();
        kredit1 = 0;
        txtDebit1.setText(myFormatter.format(debit1));
        txtKredit1.setText(myFormatter.format(kredit1));
    }
    
    private void findDebitKredit2(){
        String kode = cmbPembayaran.getSelectedItem().toString();
        pembayaran = pbCont.findPelunasanPembayaran(kode);
        debit2 = 0;
        kredit2 = pembayaran.getTotalBayar();
        txtDebit2.setText(myFormatter.format(debit2));
        txtKredit2.setText(myFormatter.format(kredit2));
    }
    
    private void clearJurnal(){
        txtKdPerkiraan1.setText("");
        txtKdPerkiraan2.setText("");
        txtDebit1.setText("");
        txtDebit2.setText("");
        txtKredit1.setText("");
        txtKredit2.setText("");
        cmbNmPerkiraan1.setSelectedIndex(0);
        cmbNmPerkiraan2.setSelectedIndex(0);
    }
    
    private void addData1ToTable(){
        Object[] obj = new Object[5];
        obj[1] = txtKdPerkiraan1.getText();
        obj[2] = cmbNmPerkiraan1.getSelectedItem();
        obj[3] = txtDebit1.getText();
        obj[4] = txtKredit1.getText();
        model.addRow(obj);
        noTable();
    }
    
    private void addData2ToTable(){
        Object[] obj = new Object[5];
        obj[1] = txtKdPerkiraan2.getText();
        obj[2] = cmbNmPerkiraan2.getSelectedItem();
        obj[3] = txtDebit2.getText();
        obj[4] = txtKredit2.getText();
        model.addRow(obj);
        noTable();
    }
    
    private void noTable(){
        int row=model.getRowCount(); 
        for(int a=0; a<row ;a++){
            String no=String.valueOf(a+1);
            tableJurnal.setValueAt(no, a, 0); 
        }
    }
    
    private void clearList(){
        int row=tableJurnal.getRowCount();
        while(row>0){
            row--;
            model.removeRow(row);
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

        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtNoJurnal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        jdcTgl = new com.toedter.calendar.JDateChooser();
        panelJurnal = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtKdPerkiraan1 = new javax.swing.JTextField();
        cmbNmPerkiraan1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtDebit1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtKredit1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtKdPerkiraan2 = new javax.swing.JTextField();
        cmbNmPerkiraan2 = new javax.swing.JComboBox<>();
        txtDebit2 = new javax.swing.JTextField();
        txtKredit2 = new javax.swing.JTextField();
        cmbPembayaran = new javax.swing.JComboBox<>();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableJurnal = new javax.swing.JTable();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 118, 117));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tgl. Jurnal");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Jurnal");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No. Jurnal");

        txtNoJurnal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoJurnal.setForeground(new java.awt.Color(51, 51, 51));
        txtNoJurnal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoJurnalKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("No. Pembayaran");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Keterangan");

        txtKeterangan.setColumns(20);
        txtKeterangan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKeterangan.setLineWrap(true);
        txtKeterangan.setRows(5);
        txtKeterangan.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtKeterangan);

        jdcTgl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        panelJurnal.setBackground(new java.awt.Color(255, 118, 117));
        panelJurnal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jurnal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kode Perkiraan");

        txtKdPerkiraan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKdPerkiraan1.setForeground(new java.awt.Color(51, 51, 51));

        cmbNmPerkiraan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbNmPerkiraan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNmPerkiraan1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama Perkiraan");

        txtDebit1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDebit1.setForeground(new java.awt.Color(51, 51, 51));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Debit");

        txtKredit1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKredit1.setForeground(new java.awt.Color(51, 51, 51));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kredit");

        txtKdPerkiraan2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKdPerkiraan2.setForeground(new java.awt.Color(51, 51, 51));
        txtKdPerkiraan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKdPerkiraan2ActionPerformed(evt);
            }
        });

        cmbNmPerkiraan2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbNmPerkiraan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNmPerkiraan2ActionPerformed(evt);
            }
        });

        txtDebit2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDebit2.setForeground(new java.awt.Color(51, 51, 51));

        txtKredit2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKredit2.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelJurnalLayout = new javax.swing.GroupLayout(panelJurnal);
        panelJurnal.setLayout(panelJurnalLayout);
        panelJurnalLayout.setHorizontalGroup(
            panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJurnalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(txtKdPerkiraan1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(txtKdPerkiraan2))
                .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJurnalLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cmbNmPerkiraan2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDebit2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKredit2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJurnalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbNmPerkiraan1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelJurnalLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 66, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDebit1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtKredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelJurnalLayout.setVerticalGroup(
            panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJurnalLayout.createSequentialGroup()
                .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelJurnalLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKredit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJurnalLayout.createSequentialGroup()
                        .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKdPerkiraan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbNmPerkiraan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDebit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJurnalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKdPerkiraan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNmPerkiraan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDebit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKredit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        cmbPembayaran.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPembayaranActionPerformed(evt);
            }
        });

        btnTambah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTambah.setText("Tambah Jurnal");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHapus.setText("Hapus Jurnal");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnKembali.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnKembali.setText("Kembali");
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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNoJurnal, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jdcTgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbPembayaran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(panelJurnal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus))
                    .addComponent(btnKembali))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNoJurnal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdcTgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelJurnal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnHapus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableJurnal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableJurnal.setForeground(new java.awt.Color(51, 51, 51));
        tableJurnal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Kode Perkiraan", "Nama Perkiraan", "Debit", "Kredit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableJurnal);
        if (tableJurnal.getColumnModel().getColumnCount() > 0) {
            tableJurnal.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableJurnal.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoJurnalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoJurnalKeyTyped

    }//GEN-LAST:event_txtNoJurnalKeyTyped

    private void txtKdPerkiraan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKdPerkiraan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKdPerkiraan2ActionPerformed

    private void cmbNmPerkiraan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNmPerkiraan1ActionPerformed
        findKodePerkiraan1();
        if(!cmbPembayaran.getSelectedItem().equals("Pilih")){
            findDebitKredit1();
        }
        
    }//GEN-LAST:event_cmbNmPerkiraan1ActionPerformed

    private void cmbNmPerkiraan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNmPerkiraan2ActionPerformed
        findKodePerkiraan2();
        if(!cmbPembayaran.getSelectedItem().equals("Pilih")){
            findDebitKredit2();
        }
    }//GEN-LAST:event_cmbNmPerkiraan2ActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        double debit = 0 , kredit = 1;
        if(!txtDebit1.getText().isEmpty() || !txtDebit2.getText().isEmpty()){
            try {
                number = myFormatter.parse(txtDebit1.getText());
                debit = number.doubleValue();
                number = myFormatter.parse(txtKredit2.getText());
                kredit = number.doubleValue();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(txtKdPerkiraan1.getText().isEmpty() || txtKdPerkiraan2.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Kode perkiraan kosong!");
            }else if(txtDebit1.getText().isEmpty() || txtDebit2.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Nominal debit kredit kosong! Pilih nomor pembayaran lebih dahulu!");
            }else if(debit != kredit){
                JOptionPane.showMessageDialog(null, "Nominal debit kredit tidak balance!");
            }else{
                addData1ToTable();
                addData2ToTable();
            }
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        clearList();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void cmbPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPembayaranActionPerformed
        if(tableJurnal.getRowCount() > 0){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Nomor Pembayaran berubah!");
            clearJurnal();
        }
    }//GEN-LAST:event_cmbPembayaranActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        FormJurnalHeader fj = new FormJurnalHeader();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(fj);
        fj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbNmPerkiraan1;
    private javax.swing.JComboBox<String> cmbNmPerkiraan2;
    private javax.swing.JComboBox<String> cmbPembayaran;
    private javax.swing.JDialog jDialog1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdcTgl;
    private javax.swing.JPanel panelJurnal;
    private javax.swing.JTable tableJurnal;
    private javax.swing.JTextField txtDebit1;
    private javax.swing.JTextField txtDebit2;
    private javax.swing.JTextField txtKdPerkiraan1;
    private javax.swing.JTextField txtKdPerkiraan2;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtKredit1;
    private javax.swing.JTextField txtKredit2;
    private javax.swing.JTextField txtNoJurnal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        jdcTgl.setEnabled(true);
        cmbPembayaran.setEnabled(true);
        txtKeterangan.setEnabled(true);
        cmbNmPerkiraan1.setEnabled(true);
        cmbNmPerkiraan2.setEnabled(true);
        btnTambah.setEnabled(true);
        btnHapus.setEnabled(true);
        jdcTgl.setMaxSelectableDate(new Date());
        jdcTgl.setLocale(Locale.forLanguageTag("id-ID"));
        jdcTgl.setDateFormatString("dd MMMM yyyy");
        
        formUtama.getSimpanBtn().setEnabled(true);
    }

    @Override
    public void bersih() {
        txtNoJurnal.setText(jCont.nomorOtomatis());
        jdcTgl.setDate(null);
        txtKeterangan.setText("");
        comboBoxPembayaran();
        comboBoxPerkiraan();
        clearJurnal();
        clearList();
        cmbPembayaran.setSelectedIndex(0);
    }

    @Override
    public void simpan() {
        int row = tableJurnal.getRowCount();
        if(cmbPembayaran.getSelectedItem().equals("Pilih")){
            JOptionPane.showMessageDialog(null, "Pilih nomor pembayaran!");
        }else if(jdcTgl.getDate() == null){
            JOptionPane.showMessageDialog(null, "Tanggal Jurnal tidak boleh kosong!");
        }else if(txtKeterangan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Masukan Keterangan!");
        }else if(cmbNmPerkiraan1.getSelectedItem().equals("Pilih") || cmbNmPerkiraan2.getSelectedItem().equals("Pilih") ){
            JOptionPane.showMessageDialog(null, "Nama Perkiraan tidak valid!");
        }else if(txtKdPerkiraan1.getText().isEmpty() || txtKdPerkiraan2.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Kode perkiraan tidak lengkap!");
        }else if(txtDebit1.getText().isEmpty() || txtDebit2.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debit Kredit tidak lengkap!");
        }else if(row == 0){
            JOptionPane.showMessageDialog(null, "Data table masih kosong!");
        }else{
            try {
                jurnal = new Jurnal();
                jurnal.setNoJurnal(txtNoJurnal.getText());
                jurnal.setNoPembayaran(cmbPembayaran.getSelectedItem().toString());
                jurnal.setTglJurnal(jdcTgl.getDate());
                jurnal.setKet(txtKeterangan.getText());
                jCont.save(jurnal);

                detailJurnal = new DetailJurnal();
                for(int i = 0; i < row; i++){
                    detailJurnal.setNoJurnal(txtNoJurnal.getText());
                    detailJurnal.setKdPerkiraan(tableJurnal.getValueAt(i, 1).toString());
                    number = myFormatter.parse(tableJurnal.getValueAt(i, 3).toString());
                    detailJurnal.setDebet(number.doubleValue());
                    number = myFormatter.parse(tableJurnal.getValueAt(i, 4).toString());
                    detailJurnal.setKredit(number.doubleValue());
                    detailCont.save(detailJurnal);
                }
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                btnKembaliActionPerformed(null);
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
