package AdminForm;

import LoanForms.*;
import classPack.DatabaseConnection;
import static classPack.DatabaseConnection.EMP;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import micro.Employee;
import micro.Main;

public class AdminSettings extends javax.swing.JPanel {
    private boolean re;
    public AdminSettings() {
        initComponents();
        newPass.setEchoChar((char) 0);
        newPass2.setEchoChar((char) 0);
    }
    
    public void oldPasswordCheck(){
       
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "Select * from micro_log where EmployId=? and Password=?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, "admin");
            ps1.setString(2, currentPassword.getText());
            
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {//both are same
                re= true;
            }else{
                re= false;
                message.setForeground(Color.RED);
                message.setText("Password Wrong Baby..");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    public void passwordUpdate(String newPass){
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            
            String sql= " UPDATE `micro_log` SET Password='"+newPass+"' WHERE EmployId = 'admin' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
         
            message.setForeground(Color.GREEN);
            message.setText("Changed sucess full");
            JOptionPane.showMessageDialog(null,"Password Change Sucsess..");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public void checkComparePassword(){
        if(!newPass.getText().equals(newPass2.getText())){
            message.setForeground(Color.red);
            message.setText("Something went wrong");
        }else{
            if(currentPassword.getText().equals(newPass.getText())){
            message.setForeground(Color.red);
            message.setText("Enter Diferent Password");                
            }else{
                passwordUpdate(newPass2.getText());
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanelAllRound1 = new swing.mainPanelAllRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        currentPassword = new javax.swing.JTextField();
        newPass = new javax.swing.JPasswordField();
        newPass2 = new javax.swing.JPasswordField();
        PasswordShow = new javax.swing.JLabel();
        PasswordShow2 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        passwordSet = new javax.swing.JButton();
        message = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanelAllRound1.setBackground(new java.awt.Color(255, 255, 255));
        mainPanelAllRound1.setFont(new java.awt.Font("Alef", 0, 12)); // NOI18N

        jLabel1.setText("Current Password :");

        jLabel2.setText("Enter New Password :");

        jLabel3.setText("Re-type Password :");

        currentPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        currentPassword.setMinimumSize(new java.awt.Dimension(7, 25));
        currentPassword.setPreferredSize(new java.awt.Dimension(60, 25));

        newPass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        newPass.setForeground(new java.awt.Color(153, 153, 153));
        newPass.setText("Enter New Password");
        newPass.setToolTipText("Enter New Password");
        newPass.setMinimumSize(new java.awt.Dimension(1, 25));
        newPass.setPreferredSize(new java.awt.Dimension(127, 25));
        newPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                newPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                newPassFocusLost(evt);
            }
        });
        newPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newPassMouseClicked(evt);
            }
        });
        newPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                newPassKeyTyped(evt);
            }
        });

        newPass2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        newPass2.setForeground(new java.awt.Color(153, 153, 153));
        newPass2.setText("Re-type Password");
        newPass2.setToolTipText("Re-type Password");
        newPass2.setPreferredSize(new java.awt.Dimension(119, 25));
        newPass2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                newPass2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                newPass2FocusLost(evt);
            }
        });
        newPass2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newPass2MouseClicked(evt);
            }
        });

        PasswordShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/unlock.png"))); // NOI18N
        PasswordShow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PasswordShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PasswordShowMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PasswordShowMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PasswordShowMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PasswordShowMouseReleased(evt);
            }
        });

        PasswordShow2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/unlock.png"))); // NOI18N
        PasswordShow2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PasswordShow2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PasswordShow2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PasswordShow2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PasswordShow2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PasswordShow2MouseReleased(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel75.setText("Profile Settings");

        passwordSet.setText("Save Changes");
        passwordSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordSetActionPerformed(evt);
            }
        });

        message.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        message.setText("  ");

        javax.swing.GroupLayout mainPanelAllRound1Layout = new javax.swing.GroupLayout(mainPanelAllRound1);
        mainPanelAllRound1.setLayout(mainPanelAllRound1Layout);
        mainPanelAllRound1Layout.setHorizontalGroup(
            mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelAllRound1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelAllRound1Layout.createSequentialGroup()
                        .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(20, 20, 20)
                        .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelAllRound1Layout.createSequentialGroup()
                                .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(message, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(newPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(newPass2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PasswordShow)
                                    .addComponent(PasswordShow2)))
                            .addComponent(passwordSet)))
                    .addComponent(jLabel75)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        mainPanelAllRound1Layout.setVerticalGroup(
            mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelAllRound1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(5, 5, 5)
                .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PasswordShow)
                    .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(newPass, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PasswordShow2)
                    .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(newPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(3, 3, 3)
                .addComponent(message)
                .addGap(2, 2, 2)
                .addComponent(passwordSet)
                .addContainerGap(298, Short.MAX_VALUE))
        );

        add(mainPanelAllRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 580));
    }// </editor-fold>//GEN-END:initComponents

    private void PasswordShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordShowMouseClicked

    }//GEN-LAST:event_PasswordShowMouseClicked

    private void PasswordShowMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordShowMouseReleased
        newPass.setForeground(Color.BLACK);        
        newPass.setEchoChar('\u25cf');
    }//GEN-LAST:event_PasswordShowMouseReleased

    private void newPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPassFocusLost
        if(newPass.getText().equals("")){
            newPass.setEchoChar((char) 0);
            newPass.setText("Enter New Password");
            newPass.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_newPassFocusLost

    private void newPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPassFocusGained
        if(newPass.getText().equals("Enter New Password")){
            newPass.setText("");
            newPass.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_newPassFocusGained

    private void newPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newPassKeyTyped

    }//GEN-LAST:event_newPassKeyTyped

    private void newPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newPassMouseClicked
        newPass.setForeground(Color.BLACK);
        newPass.setEchoChar('\u25cf');
    }//GEN-LAST:event_newPassMouseClicked

    private void PasswordShowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordShowMouseExited

    }//GEN-LAST:event_PasswordShowMouseExited

    private void PasswordShowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordShowMousePressed
        newPass.setForeground(Color.BLACK);
        newPass.setEchoChar((char) 0);
    }//GEN-LAST:event_PasswordShowMousePressed

    private void PasswordShow2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordShow2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordShow2MouseClicked

    private void PasswordShow2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordShow2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordShow2MouseExited

    private void PasswordShow2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordShow2MousePressed
        newPass2.setForeground(Color.BLACK);
        newPass2.setEchoChar((char) 0);
    }//GEN-LAST:event_PasswordShow2MousePressed

    private void PasswordShow2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordShow2MouseReleased
        newPass2.setForeground(Color.BLACK);        
        newPass2.setEchoChar('\u25cf');
    }//GEN-LAST:event_PasswordShow2MouseReleased

    private void newPass2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPass2FocusGained
        if(newPass2.getText().equals("Re-type Password")){
            newPass2.setText("");
            newPass2.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_newPass2FocusGained

    private void newPass2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPass2FocusLost
        if(newPass2.getText().equals("")){
            newPass2.setEchoChar((char) 0);
            newPass2.setText("Re-type Password");
            newPass2.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_newPass2FocusLost

    private void newPass2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newPass2MouseClicked
        newPass2.setForeground(Color.BLACK);
        newPass2.setEchoChar('\u25cf');
    }//GEN-LAST:event_newPass2MouseClicked

    private void passwordSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordSetActionPerformed
        oldPasswordCheck();
        if(re){
           checkComparePassword(); 
        }else{
            message.setForeground(Color.RED);
            message.setText("Something wrong..");
        }
        
        
    }//GEN-LAST:event_passwordSetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PasswordShow;
    private javax.swing.JLabel PasswordShow2;
    private javax.swing.JTextField currentPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JSeparator jSeparator9;
    private swing.mainPanelAllRound mainPanelAllRound1;
    private javax.swing.JLabel message;
    private javax.swing.JPasswordField newPass;
    private javax.swing.JPasswordField newPass2;
    private javax.swing.JButton passwordSet;
    // End of variables declaration//GEN-END:variables
}
