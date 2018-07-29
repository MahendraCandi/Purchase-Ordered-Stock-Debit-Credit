package fladeoapp.form;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FormBackupDatabase extends javax.swing.JInternalFrame {

    FormUtama formUtama = FormUtama.staticUtama;
    String path = null;
    String fileName;
    /**
     * Creates new form FormBackupDatabase
     */
    public FormBackupDatabase() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        formUtama.buttonOff();
        txtPath.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        btnBrowsePath = new javax.swing.JButton();
        btnBackup = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 118, 117));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Backup Database");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Letak File");

        txtPath.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPath.setForeground(new java.awt.Color(51, 51, 51));
        txtPath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPathKeyTyped(evt);
            }
        });

        btnBrowsePath.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBrowsePath.setText("Browse Path");
        btnBrowsePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowsePathActionPerformed(evt);
            }
        });

        btnBackup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBackup.setText("Backup Database");
        btnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBrowsePath))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(btnBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowsePath))
                .addGap(18, 18, 18)
                .addComponent(btnBackup)
                .addContainerGap(246, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPathKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPathKeyTyped

    }//GEN-LAST:event_txtPathKeyTyped

    private void btnBrowsePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowsePathActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        String tgl = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
            if(fc.getSelectedFile() == null){
                JOptionPane.showMessageDialog(null, "Nama File tidak boleh kosong");
                return;
            }
            File file = fc.getSelectedFile();
            path = file.getAbsolutePath();
            path = path.replace("\\", "/");
            path = path + "-" + tgl + ".sql";
            txtPath.setText(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnBrowsePathActionPerformed

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackupActionPerformed
        Process process = null;
        String dbName = "fladeoDb";
        String user = "root";
        String sqlDumpPath = "C:/xampp/mysql/bin/mysqldump.exe -u " + user + " -B " + dbName + " -r ";
        if(txtPath.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Pilih letak penyimpanan file!");
            return;
        }
        try {
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec(sqlDumpPath + path);
            
            int processComplete = process.waitFor();
            if(processComplete == 0){
                getToolkit().beep();
                JOptionPane.showMessageDialog(null, "Backup berhasil!");
            }else{
                getToolkit().beep();
                JOptionPane.showMessageDialog(null, "Backup gagal!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan! " + e.getMessage());
        }
    }//GEN-LAST:event_btnBackupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackup;
    private javax.swing.JButton btnBrowsePath;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables
}