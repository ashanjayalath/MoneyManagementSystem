package swing;

import java.awt.event.ActionListener;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mainPanelAllRound1 = new swing.mainPanelAllRound();
        EmpSearchBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        TimeDisplay = new javax.swing.JLabel();
        DateDisplay = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        mailbutton = new javax.swing.JLabel();
        notifibutton = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        EmpName = new javax.swing.JLabel();
        EMP = new javax.swing.JLabel();
        colorSquared1 = new swing.colorSquared();

        setBackground(new java.awt.Color(255, 153, 0));
        setMinimumSize(new java.awt.Dimension(950, 60));
        setPreferredSize(new java.awt.Dimension(950, 60));

        jPanel1.setBackground(new java.awt.Color(51, 255, 51));
        jPanel1.setOpaque(false);

        mainPanelAllRound1.setBackground(new java.awt.Color(255, 255, 255));

        EmpSearchBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        EmpSearchBox.setBorder(null);
        EmpSearchBox.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        EmpSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpSearchBoxActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-24.png"))); // NOI18N

        javax.swing.GroupLayout mainPanelAllRound1Layout = new javax.swing.GroupLayout(mainPanelAllRound1);
        mainPanelAllRound1.setLayout(mainPanelAllRound1Layout);
        mainPanelAllRound1Layout.setHorizontalGroup(
            mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelAllRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EmpSearchBox, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addGap(59, 59, 59))
        );
        mainPanelAllRound1Layout.setVerticalGroup(
            mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelAllRound1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(mainPanelAllRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(EmpSearchBox)
                    .addComponent(jLabel2))
                .addGap(2, 2, 2))
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 102));
        jPanel2.setOpaque(false);

        TimeDisplay.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TimeDisplay.setForeground(new java.awt.Color(255, 255, 255));

        DateDisplay.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        DateDisplay.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DateDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(TimeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DateDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 51));
        jPanel3.setOpaque(false);

        mailbutton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mailbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/emailOpen.png"))); // NOI18N
        mailbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        notifibutton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notifibutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/notification0.png"))); // NOI18N
        notifibutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mailbutton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(notifibutton)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(notifibutton)
                    .addComponent(mailbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel4.setOpaque(false);

        EmpName.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        EmpName.setForeground(new java.awt.Color(255, 255, 255));
        EmpName.setText("Not Set Name");
        EmpName.setMaximumSize(new java.awt.Dimension(134, 20));
        EmpName.setMinimumSize(new java.awt.Dimension(134, 20));
        EmpName.setPreferredSize(new java.awt.Dimension(134, 18));
        EmpName.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                EmpNameAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        EMP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        EMP.setText("Not set Employee Id");
        EMP.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                EMPAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EMP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EmpName, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(EmpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(EMP, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanelAllRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanelAllRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout colorSquared1Layout = new javax.swing.GroupLayout(colorSquared1);
        colorSquared1.setLayout(colorSquared1Layout);
        colorSquared1Layout.setHorizontalGroup(
            colorSquared1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        colorSquared1Layout.setVerticalGroup(
            colorSquared1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addComponent(colorSquared1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(colorSquared1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void EmpSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpSearchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmpSearchBoxActionPerformed

    private void EMPAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_EMPAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_EMPAncestorAdded

    private void EmpNameAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_EmpNameAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_EmpNameAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DateDisplay;
    private javax.swing.JLabel EMP;
    private javax.swing.JLabel EmpName;
    private javax.swing.JTextField EmpSearchBox;
    private javax.swing.JLabel TimeDisplay;
    private swing.colorSquared colorSquared1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel mailbutton;
    private swing.mainPanelAllRound mainPanelAllRound1;
    private javax.swing.JLabel notifibutton;
    // End of variables declaration//GEN-END:variables


    public void setEMP(String b) {
        EMP.setText(b);
    }
    public void setEmpName(String c) {
        EmpName.setText(c);
    }
    public void setTimeDisplay(String v){
        TimeDisplay.setText(v);
    }
    

    public void setDateDisplay(String d){
        DateDisplay.setText(d);
    }
}
