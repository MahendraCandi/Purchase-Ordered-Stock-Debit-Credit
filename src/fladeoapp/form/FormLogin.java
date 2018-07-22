package fladeoapp.form;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.persistence.EntityManager;
import fladeoapp.FladeoApp;
import fladeoapp.data.User;
import java.awt.event.KeyEvent;
import javax.persistence.Query;
import java.util.List;
import javax.swing.JOptionPane;

public class FormLogin extends javax.swing.JFrame {
    private String nama;
    private String hakAkses;
    private String username;
    private String password;
    
    int xx;
    int xy;
    
    /**
     * Creates new form FormLogin
     */
    public FormLogin() {
        initComponents();
        setLocationRelativeTo(null);
        setShape(new RoundRectangle2D.Double(0, 0, 400, 450, 10, 10));
        user();
        password();
    }
    
    
    public void user(){
        txtLogin.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                txtLogin.setText("");
                txtLogin.setForeground(Color.WHITE);
            }
        });
        txtLogin.setForeground((new Color(204, 204, 204)));
        txtLogin.setText("fnc1");
    }
    
    public void password(){
        txtPassword.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                txtPassword.setText("");
                txtPassword.setForeground(Color.WHITE);
            }
        });
        txtLogin.setForeground((new Color(204, 204, 204)));
        txtPassword.setText("1234");
    }
    
    private void login(){
        EntityManager em=null;
        try {
            em=FladeoApp.emf.createEntityManager();
            Query q=em.createQuery("SELECT u FROM User u WHERE u.username = :username");
            q.setParameter("username", txtLogin.getText());
            List<User> list = q.getResultList();
            if(!list.isEmpty()){
                for(User us : list){
                    nama = us.getNama();
                    hakAkses = us.getHakAkses();
                    username = us.getUsername();
                    password = us.getPassword();
                }
                if(txtPassword.getText().equals(password)){
                    User userLogin = new User();
                    userLogin.setUsername(username);
                    userLogin.setHakAkses(hakAkses);
                    userLogin.setNama(nama);
                    
                    FormUtama formUtama = new FormUtama(userLogin);
                    JOptionPane.showMessageDialog(this, "Selamat datang, "+nama+"","Welcome", JOptionPane.INFORMATION_MESSAGE);
                    if(hakAkses.equalsIgnoreCase("FINANCE")){
                        formUtama.getMasterItemAkun().setVisible(true);
                        formUtama.getTransItemPembelian().setVisible(true);
                        formUtama.getTransItemPembayaran().setVisible(true);
                        formUtama.getTransItemJurnal().setVisible(true);
                        formUtama.getLapItemPembelian().setVisible(true);
                        formUtama.getLapItemKas().setVisible(true);
                        
                        formUtama.getMasterItemSupplier().setVisible(false);
                        formUtama.getMasterItemBarang().setVisible(false);
                        formUtama.getTransItemPO().setVisible(false);
                        formUtama.getTransItemPB().setVisible(false);
                        formUtama.getLapItemTerimaBarang().setVisible(false);
                        
                    }else if(hakAkses.equalsIgnoreCase("PURCHASING")){
                        formUtama.getMasterItemSupplier().setVisible(true);
                        formUtama.getMasterItemBarang().setVisible(true);
                        formUtama.getTransItemPO().setVisible(true);
                        formUtama.getTransItemPB().setVisible(true);
                        formUtama.getLapItemTerimaBarang().setVisible(true);
                        
                        formUtama.getMasterItemAkun().setVisible(false);
                        formUtama.getTransItemPembelian().setVisible(false);
                        formUtama.getTransItemPembayaran().setVisible(false);
                        formUtama.getTransItemJurnal().setVisible(false);
                        formUtama.getLapItemPembelian().setVisible(false);
                        formUtama.getLapItemKas().setVisible(false);
                    }
                    
                    this.dispose();
                    formUtama.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Password salah!");
                    txtPassword.setText("");
                    txtPassword.requestFocus();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Username tidak ditemukan!");
                txtLogin.setText("");
                txtPassword.setText("");
                txtLogin.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtLogin = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(214, 48, 49));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 450));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fladeo Logo.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, 70));

        btnClose.setBackground(new java.awt.Color(153, 0, 0));
        btnClose.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("X");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jPanel2.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        jPanel4.setBackground(new java.awt.Color(255, 118, 117));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Masukan Username");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Form Login");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, 40));

        jPanel5.setBackground(new java.awt.Color(255, 118, 117));
        jPanel5.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Masukan Password");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, -1, 30));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 130, 10));

        txtLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(255, 255, 255));
        txtLogin.setText("FIN-001");
        txtLogin.setBorder(null);
        txtLogin.setCaretColor(new java.awt.Color(255, 255, 255));
        txtLogin.setOpaque(false);
        jPanel1.add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 253, 30));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 250, 10));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setText("jPasswordField1");
        txtPassword.setBorder(null);
        txtPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPassword.setOpaque(false);
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 250, 30));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 250, 10));

        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 250, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnLoginActionPerformed(null);
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if("".equals(txtLogin.getText())&&"".equals(txtPassword.getText())){
            JOptionPane.showMessageDialog(null, "Masukan Username dan Password!");
        }else if("".equals(txtLogin.getText())){
            JOptionPane.showMessageDialog(null, "Masukan Username!");
        }else if("".equals(txtPassword.getText())){
            JOptionPane.showMessageDialog(null, "Masukan password!");
        }else{
            login();
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x -xx, y -xy);
    }//GEN-LAST:event_formMouseDragged

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
    
}
