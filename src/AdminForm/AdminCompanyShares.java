
package AdminForm;

import GetterSetter.Shares;
import classPack.DBQuary;
import classPack.ValidityCheck;
import java.util.ArrayList;


public class AdminCompanyShares extends javax.swing.JPanel {


    public AdminCompanyShares() {
        initComponents();
        getData();
  
    }

    public void getData(){
        Shares s=new Shares();
        DBQuary.getSharesData(s);
        lblShareHolders.setText(String.valueOf(s.getHolder()));
        lblOwner_share.setText(String.valueOf(s.getOwner()));
        lblOther_share.setText(String.valueOf(s.getOther()));
        lblTotalProfitIncome.setText(String.valueOf(s.getOtherLoan()));
        lblTotal_share.setText(String.valueOf(s.getTotal()));
        lblTotal_Ishu.setText(String.valueOf(s.getIshuMoney()));
    }
    
    public void updateData(Shares s){
        ArrayList<String> tempo=new ArrayList<>();
        double SH=0;double OwnerS=0;double OtherS=0;double TLP=0;
        getData();

        if(!txtOwner.getText().isBlank()){
            double z=Double.parseDouble(txtOwner.getText());
            lblOwner_share.setText(String.valueOf(Double.valueOf(lblOwner_share.getText())+z));
            s.setOwner(Double.valueOf(lblOwner_share.getText()));
            OwnerS=Double.parseDouble(txtOwner.getText());
            txtOwner.setText("");
            tempo.add("OW");
            
        }if(!txtShareHolder.getText().isBlank()){
            double z=Double.parseDouble(txtShareHolder.getText());
            lblShareHolders.setText(String.valueOf(Double.valueOf(lblShareHolders.getText())+z));
            s.setHolder(Double.valueOf(lblShareHolders.getText()));
            SH=Double.parseDouble(txtShareHolder.getText());
            txtShareHolder.setText("");
            tempo.add("SH");

        }if(!txtincome.getText().isBlank()){
            double z=Double.parseDouble(txtincome.getText());
            lblTotalProfitIncome.setText(String.valueOf(Double.valueOf(lblTotalProfitIncome.getText())+z)); 
            s.setOtherLoan(Double.valueOf(lblTotalProfitIncome.getText()));
            TLP=Double.parseDouble(txtincome.getText());
            txtincome.setText("");
            tempo.add("TLP");

        }if(!txtOther.getText().isBlank()){       
            double z=Double.parseDouble(txtOther.getText());
            lblOther_share.setText(String.valueOf(Double.valueOf(lblOther_share.getText())+z));  
            s.setOther(Double.valueOf(lblOther_share.getText()));
            OtherS=Double.parseDouble(txtOther.getText());
            txtOther.setText("");
            tempo.add("OT");

        }
        
        s.setPrecent(Double.valueOf(txtIshuPrecent.getText()));
        double ca=Double.valueOf(lblTotal_share.getText());
        lblTotal_share.setText(String.valueOf(Double.valueOf(lblTotal_share.getText())+SH+TLP+OtherS+OwnerS));
        if(Double.valueOf(lblTotal_share.getText())>ca){
            tempo.add("TS");
        }
        s.setTotal(Double.valueOf(lblTotal_share.getText()));
        
        //lblTotal_Ishu.setText(String.valueOf(((getTotal+SH+TLP+OtherS+OwnerS)/100)*precent));

        String up=DBQuary.updateSharesData(s,tempo);
        DBQuary.updateAndinsert(up);
        getData();

    }
    
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel73 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblShareHolders = new javax.swing.JLabel();
        lblTotalProfitIncome = new javax.swing.JLabel();
        lblOwner_share = new javax.swing.JLabel();
        lblOther_share = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblTotal_share = new javax.swing.JLabel();
        txtShareHolder = new javax.swing.JTextField();
        txtincome = new javax.swing.JTextField();
        txtOwner = new javax.swing.JTextField();
        txtOther = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblTotal_Ishu = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIshuPrecent = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 400));
        setMinimumSize(new java.awt.Dimension(900, 400));
        setPreferredSize(new java.awt.Dimension(900, 400));

        jLabel73.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel73.setText("Company Shares");

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setText("Share Holders :");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("Total Profit Income :");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Ownar Shares :");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("Other Shares :");

        lblShareHolders.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblShareHolders.setText("0");

        lblTotalProfitIncome.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblTotalProfitIncome.setText("0");

        lblOwner_share.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblOwner_share.setText("0");

        lblOther_share.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblOther_share.setText("0");

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel10.setText("Total Shares :");

        lblTotal_share.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblTotal_share.setText("0");

        txtShareHolder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtShareHolderKeyPressed(evt);
            }
        });

        txtincome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtincomeKeyPressed(evt);
            }
        });

        txtOwner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOwnerKeyPressed(evt);
            }
        });

        txtOther.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOtherKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel11.setText("Total Ishu Money :");

        lblTotal_Ishu.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblTotal_Ishu.setText("0");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel5.setText("Ishu Presentage :");

        txtIshuPrecent.setText("40");
        txtIshuPrecent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIshuPrecentKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1))
                            .addGap(25, 25, 25)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                .addComponent(txtOther)
                                .addComponent(lblShareHolders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtShareHolder)
                                .addComponent(txtincome)
                                .addComponent(lblOther_share, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblOwner_share, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTotalProfitIncome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtOwner))
                            .addGap(107, 107, 107)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtIshuPrecent, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTotal_share, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTotal_Ishu, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblShareHolders)
                    .addComponent(jLabel10)
                    .addComponent(lblTotal_share))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtShareHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTotalProfitIncome)
                    .addComponent(jLabel11)
                    .addComponent(lblTotal_Ishu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtincome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblOwner_share)
                    .addComponent(jLabel5)
                    .addComponent(txtIshuPrecent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblOther_share))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOther, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Shares share=new Shares();
        updateData(share);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtShareHolderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShareHolderKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtShareHolder.setEditable(true);
        }else{
            txtShareHolder.setEditable(false);
        }
    }//GEN-LAST:event_txtShareHolderKeyPressed

    private void txtincomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtincomeKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtincome.setEditable(true);
        }else{
            txtincome.setEditable(false);
        }
    }//GEN-LAST:event_txtincomeKeyPressed

    private void txtOwnerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOwnerKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtOwner.setEditable(true);
        }else{
            txtOwner.setEditable(false);
        }
    }//GEN-LAST:event_txtOwnerKeyPressed

    private void txtOtherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtherKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtOther.setEditable(true);
        }else{
            txtOther.setEditable(false);
        }
    }//GEN-LAST:event_txtOtherKeyPressed

    private void txtIshuPrecentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIshuPrecentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIshuPrecentKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblOther_share;
    private javax.swing.JLabel lblOwner_share;
    private javax.swing.JLabel lblShareHolders;
    private javax.swing.JLabel lblTotalProfitIncome;
    private javax.swing.JLabel lblTotal_Ishu;
    private javax.swing.JLabel lblTotal_share;
    private javax.swing.JTextField txtIshuPrecent;
    private javax.swing.JTextField txtOther;
    private javax.swing.JTextField txtOwner;
    private javax.swing.JTextField txtShareHolder;
    private javax.swing.JTextField txtincome;
    // End of variables declaration//GEN-END:variables
}
