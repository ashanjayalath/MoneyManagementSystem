package micro;

import LoanForms.LoanGet;
import java.awt.Color;
import classPack.DatabaseConnection;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.sql.*;
import java.util.Locale;
import javax.swing.Action;
public class Loging extends javax.swing.JFrame {


    public String fname,lname,name;
    public Loging() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        //call moving function
        logInterface1.initMoving2(Loging.this);
        PasswordBox.setEchoChar((char) 0);
        getDate();
        getTime();
        DatabaseConnection data = new DatabaseConnection();
        data.connect();
        //logInterface1.setSize(0,500);
        //panelBorder1.setSize(0, 500);        
    }


    public void close(){
        WindowEvent closeWindow=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
    
    
    public void getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String d = sdf.format(date);
        lblGetDate.setText(d);
    }
    Timer t;
    SimpleDateFormat st;
    public void getTime() {
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                st = new SimpleDateFormat("hh:mm:ss a");

                String time = st.format(dt);
                lblGetTime.setText(time);
            }
        });
        t.start();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        logInterface1 = new swing.LogInterface();
        mainPanelAllRound2 = new swing.mainPanelAllRound();
        mainPanelAllRound1 = new swing.mainPanelAllRound();
        PasswordBox = new javax.swing.JPasswordField();
        mainPanelAllRound3 = new swing.mainPanelAllRound();
        UsernameBox = new javax.swing.JTextField();
        lblLogin = new javax.swing.JButton();
        ShowCheck = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        lblforget = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblGetDate = new javax.swing.JLabel();
        lblGetTime = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        Minimize = new javax.swing.JLabel();
        mainPanelAllRound4 = new swing.mainPanelAllRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 20, 150, 80);

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 90, 30, 40);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contact number: +94 11 557 2424");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 420, 200, 13);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(" TeleFax: +94 11 557 2425");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(240, 420, 160, 13);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email: bitinvestment@sltnet.lk");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 440, 190, 13);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText(" Website: www.bitinvestment.lk");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(240, 440, 180, 14);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Company Address: BIT Investments PVT, No 328, Nawala Road, Nugegoda.");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 460, 410, 13);

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("IT INVESTMENTS LIMITED (PVT)");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(80, 90, 320, 40);

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bPlaintLess.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(50, 90, 30, 40);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Loan/global.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 80, 410, 360);
        getContentPane().add(logInterface1);
        logInterface1.setBounds(0, 0, 500, 500);

        mainPanelAllRound2.setBackground(new java.awt.Color(255, 255, 255));
        mainPanelAllRound2.setMinimumSize(new java.awt.Dimension(480, 500));
        mainPanelAllRound2.setPreferredSize(new java.awt.Dimension(480, 500));

        mainPanelAllRound1.setBackground(new java.awt.Color(242, 242, 242));
        mainPanelAllRound1.setMinimumSize(new java.awt.Dimension(260, 30));
        mainPanelAllRound1.setPreferredSize(new java.awt.Dimension(260, 30));

        PasswordBox.setBackground(new java.awt.Color(242, 242, 242));
        PasswordBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        PasswordBox.setForeground(new java.awt.Color(153, 153, 153));
        PasswordBox.setText("Enter Password");
        PasswordBox.setToolTipText("Password");
        PasswordBox.setBorder(null);
        PasswordBox.setPreferredSize(new java.awt.Dimension(1, 20));
        PasswordBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordBoxFocusLost(evt);
            }
        });
        PasswordBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PasswordBoxMouseClicked(evt);
            }
        });
        PasswordBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordBoxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PasswordBoxKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout mainPanelAllRound1Layout = new javax.swing.GroupLayout(mainPanelAllRound1);
        mainPanelAllRound1.setLayout(mainPanelAllRound1Layout);
        mainPanelAllRound1Layout.setHorizontalGroup(
            mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelAllRound1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(PasswordBox, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        mainPanelAllRound1Layout.setVerticalGroup(
            mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelAllRound1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(PasswordBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        mainPanelAllRound3.setBackground(new java.awt.Color(242, 242, 242));
        mainPanelAllRound3.setMinimumSize(new java.awt.Dimension(260, 30));

        UsernameBox.setBackground(new java.awt.Color(242, 242, 242));
        UsernameBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        UsernameBox.setForeground(new java.awt.Color(153, 153, 153));
        UsernameBox.setText("Enter Employ Id");
        UsernameBox.setToolTipText("User Name");
        UsernameBox.setBorder(null);
        UsernameBox.setPreferredSize(new java.awt.Dimension(1, 20));
        UsernameBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UsernameBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                UsernameBoxFocusLost(evt);
            }
        });
        UsernameBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UsernameBoxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UsernameBoxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UsernameBoxMouseExited(evt);
            }
        });
        UsernameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameBoxActionPerformed(evt);
            }
        });
        UsernameBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsernameBoxKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelAllRound3Layout = new javax.swing.GroupLayout(mainPanelAllRound3);
        mainPanelAllRound3.setLayout(mainPanelAllRound3Layout);
        mainPanelAllRound3Layout.setHorizontalGroup(
            mainPanelAllRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelAllRound3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(UsernameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        mainPanelAllRound3Layout.setVerticalGroup(
            mainPanelAllRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelAllRound3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(UsernameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        lblLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLogin.setText("Log in");
        lblLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblLoginActionPerformed(evt);
            }
        });

        ShowCheck.setBackground(new java.awt.Color(255, 255, 255));
        ShowCheck.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ShowCheck.setText("Show Password");
        ShowCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ShowCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ShowCheckMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ShowCheckMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ShowCheckMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ShowCheckMouseReleased(evt);
            }
        });
        ShowCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowCheckActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel1.setText("Login");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        lblforget.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblforget.setText("Forget Password ?");
        lblforget.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblforget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblforgetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblforgetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblforgetMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblforgetMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblforgetMouseReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Don't have an account ? Create your account.");

        lblGetDate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblGetDate.setMaximumSize(new java.awt.Dimension(50, 14));
        lblGetDate.setMinimumSize(new java.awt.Dimension(50, 14));
        lblGetDate.setPreferredSize(new java.awt.Dimension(50, 14));

        lblGetTime.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblGetTime.setMaximumSize(new java.awt.Dimension(50, 14));
        lblGetTime.setMinimumSize(new java.awt.Dimension(50, 14));
        lblGetTime.setPreferredSize(new java.awt.Dimension(50, 14));

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/closeB.png"))); // NOI18N
        close.setToolTipText("Close");
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.setName(""); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeMousePressed(evt);
            }
        });

        Minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/minimizeB.png"))); // NOI18N
        Minimize.setToolTipText("Minimize");
        Minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MinimizeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MinimizeMousePressed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelAllRound2Layout = new javax.swing.GroupLayout(mainPanelAllRound2);
        mainPanelAllRound2.setLayout(mainPanelAllRound2Layout);
        mainPanelAllRound2Layout.setHorizontalGroup(
            mainPanelAllRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelAllRound2Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(mainPanelAllRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLogin)
                    .addGroup(mainPanelAllRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelAllRound2Layout.createSequentialGroup()
                            .addComponent(ShowCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblforget))
                        .addComponent(mainPanelAllRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelAllRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanelAllRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addGroup(mainPanelAllRound2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel4)))
                    .addGroup(mainPanelAllRound2Layout.createSequentialGroup()
                        .addComponent(lblGetDate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblGetTime, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Minimize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90))
        );
        mainPanelAllRound2Layout.setVerticalGroup(
            mainPanelAllRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelAllRound2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(mainPanelAllRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelAllRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(close)
                        .addComponent(Minimize))
                    .addComponent(lblGetTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGetDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(244, 244, 244)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanelAllRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(mainPanelAllRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(mainPanelAllRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblforget)
                    .addComponent(ShowCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLogin)
                .addContainerGap())
        );

        getContentPane().add(mainPanelAllRound2);
        mainPanelAllRound2.setBounds(370, 0, 430, 500);

        mainPanelAllRound4.setBackground(new java.awt.Color(153, 255, 204));

        javax.swing.GroupLayout mainPanelAllRound4Layout = new javax.swing.GroupLayout(mainPanelAllRound4);
        mainPanelAllRound4.setLayout(mainPanelAllRound4Layout);
        mainPanelAllRound4Layout.setHorizontalGroup(
            mainPanelAllRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        mainPanelAllRound4Layout.setVerticalGroup(
            mainPanelAllRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(mainPanelAllRound4);
        mainPanelAllRound4.setBounds(0, 0, 800, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void PasswordBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordBoxFocusGained
        if(PasswordBox.getText().equals("Enter Password")){
            PasswordBox.setText("");
            PasswordBox.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_PasswordBoxFocusGained

    private void PasswordBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordBoxFocusLost
        if(PasswordBox.getText().equals("")){
            PasswordBox.setEchoChar((char) 0);
            PasswordBox.setText("Enter Password");
            PasswordBox.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_PasswordBoxFocusLost

    private void PasswordBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordBoxMouseClicked
        PasswordBox.setForeground(Color.BLACK);
        PasswordBox.setEchoChar('\u25cf');
    }//GEN-LAST:event_PasswordBoxMouseClicked

    private void PasswordBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordBoxKeyTyped
        if (ShowCheck.isSelected()) {
            PasswordBox.setEchoChar((char) 0);
        } else {
            PasswordBox.setEchoChar('\u25cf');
        }
    }//GEN-LAST:event_PasswordBoxKeyTyped

    private void UsernameBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameBoxFocusGained
        if(UsernameBox.getText().equals("Enter Employ Id")){
            UsernameBox.setText("");
            UsernameBox.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_UsernameBoxFocusGained

    private void UsernameBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameBoxFocusLost
        if(UsernameBox.getText().equals("")){
            UsernameBox.setText("Enter Employ Id");
            UsernameBox.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_UsernameBoxFocusLost

    private void UsernameBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsernameBoxMouseClicked
        UsernameBox.setForeground(Color.BLACK);
    }//GEN-LAST:event_UsernameBoxMouseClicked

    private void UsernameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameBoxActionPerformed

    private void UsernameBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameBoxKeyPressed
        // TODO add your handling code here:
        UsernameBox.setForeground(Color.BLACK);
    }//GEN-LAST:event_UsernameBoxKeyPressed

    private void lblLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblLoginActionPerformed
        String user=UsernameBox.getText().toUpperCase();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();            
            String sql = "Select * from micro_log where EmployId=? and Password=?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, user);
            ps1.setString(2, PasswordBox.getText());
            
            
            String quary="SELECT FirstName,LastName FROM micro_employe WHERE EmployId ='"+user+"'";
            PreparedStatement ps2 = conn.prepareStatement(quary);
            ResultSet rs = ps2.executeQuery();
            while(rs.next()){
                fname=rs.getString("FirstName");
                lname=rs.getString("LastName");
            }
            
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            if(rs1.next()){
                if(UsernameBox.getText().toUpperCase().equals("ADMIN")){
                    Main am=new Main("Company Owner",fname+" "+lname);
                    am.show();
                    dispose();
                }else{
                    //String id=rs2.getString("EmployId");
                    DatabaseConnection.EMP=user;
                    Employee a=new Employee(fname+" "+lname,user);
                    a.show();
                    dispose();
                }
            }
            else{
                UsernameBox.setForeground(Color.red);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }       
    }//GEN-LAST:event_lblLoginActionPerformed

    private void ShowCheckMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowCheckMouseEntered
        // TODO add your handling code here:
        ShowCheck.setForeground(Color.decode("#0575E6"));
    }//GEN-LAST:event_ShowCheckMouseEntered

    private void ShowCheckMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowCheckMouseExited
        // TODO add your handling code here:
        ShowCheck.setForeground(Color.BLACK);
    }//GEN-LAST:event_ShowCheckMouseExited

    private void ShowCheckMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowCheckMousePressed
        // TODO add your handling code here:
        ShowCheck.setForeground(Color.decode("#00F260"));
    }//GEN-LAST:event_ShowCheckMousePressed

    private void ShowCheckMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowCheckMouseReleased
        // TODO add your handling code here:
        ShowCheck.setForeground(Color.decode("#0575E6"));
    }//GEN-LAST:event_ShowCheckMouseReleased

    private void ShowCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowCheckActionPerformed
        //set password show item
        if (ShowCheck.isSelected()) {
            PasswordBox.setEchoChar((char) 0);
        } else {
            PasswordBox.setEchoChar('\u25cf');
        }
    }//GEN-LAST:event_ShowCheckActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void lblforgetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblforgetMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Please Contact Our Security Center", "Security Center", JOptionPane.OK_CANCEL_OPTION);
    }//GEN-LAST:event_lblforgetMouseClicked

    private void lblforgetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblforgetMouseEntered
        // TODO add your handling code here:
        lblforget.setForeground(Color.decode("#00F260"));
    }//GEN-LAST:event_lblforgetMouseEntered

    private void lblforgetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblforgetMouseExited
        // TODO add your handling code here:
        lblforget.setForeground(Color.black);
    }//GEN-LAST:event_lblforgetMouseExited

    private void lblforgetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblforgetMousePressed
        // TODO add your handling code here:
        lblforget.setForeground(Color.decode("#0575E6"));
    }//GEN-LAST:event_lblforgetMousePressed

    private void lblforgetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblforgetMouseReleased
        // TODO add your handling code here:
        lblforget.setForeground(Color.decode("#00F260"));
    }//GEN-LAST:event_lblforgetMouseReleased

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        //window close en program
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        // TODO add your handling code here:
        close.setEnabled(false);
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        // TODO add your handling code here:
        close.setEnabled(true);
    }//GEN-LAST:event_closeMouseExited

    private void closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMousePressed
        // TODO add your handling code here:
        close.setEnabled(true);
    }//GEN-LAST:event_closeMousePressed

    private void MinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizeMouseClicked
        //set minimize button
        this.setExtendedState(Loging.ICONIFIED);
    }//GEN-LAST:event_MinimizeMouseClicked

    private void MinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizeMouseEntered
        // TODO add your handling code here:
        Minimize.setEnabled(false);
    }//GEN-LAST:event_MinimizeMouseEntered

    private void MinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizeMouseExited
        // TODO add your handling code here:
        Minimize.setEnabled(true);
    }//GEN-LAST:event_MinimizeMouseExited

    private void MinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinimizeMousePressed
        // TODO add your handling code here:
        Minimize.setEnabled(true);
    }//GEN-LAST:event_MinimizeMousePressed

    private void UsernameBoxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsernameBoxMouseEntered

    }//GEN-LAST:event_UsernameBoxMouseEntered

    private void UsernameBoxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsernameBoxMouseExited

    }//GEN-LAST:event_UsernameBoxMouseExited

    private void PasswordBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordBoxKeyPressed
        // TODO add your handling code here:
        PasswordBox.setForeground(Color.BLACK);
    }//GEN-LAST:event_PasswordBoxKeyPressed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Loging.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loging.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loging.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loging.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loging().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Minimize;
    private javax.swing.JPasswordField PasswordBox;
    private javax.swing.JCheckBox ShowCheck;
    private javax.swing.JTextField UsernameBox;
    private javax.swing.JLabel close;
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
    private javax.swing.JLabel lblGetDate;
    private javax.swing.JLabel lblGetTime;
    private javax.swing.JButton lblLogin;
    private javax.swing.JLabel lblforget;
    private swing.LogInterface logInterface1;
    private swing.mainPanelAllRound mainPanelAllRound1;
    private swing.mainPanelAllRound mainPanelAllRound2;
    private swing.mainPanelAllRound mainPanelAllRound3;
    private swing.mainPanelAllRound mainPanelAllRound4;
    // End of variables declaration//GEN-END:variables
}
