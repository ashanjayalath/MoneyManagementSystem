package AdminForm;

import LoanForms.LoanGet;
import classPack.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PersonDetails extends javax.swing.JFrame {

    private String getvalue;
    public PersonDetails() {
        initComponents();
    }
    public PersonDetails(String index) {
        initComponents();
        this.getvalue=index;
        personalDataLoad();
    }
   public void personalDataLoad(){//pass reg,id  /  pl,bl
        LoanGet lo=new LoanGet();
        DefaultTableModel model = (DefaultTableModel)personalDetailsColect.getModel();
        while(model.getRowCount()>0){
            for(int p=0;p<model.getRowCount();p++){
                model.removeRow(p);
            }
        }
        DefaultTableModel mode = (DefaultTableModel)guarantorsDetailsColect.getModel();
            while(mode.getRowCount()>0){
                for(int p=0;p<mode.getRowCount();p++){
                    mode.removeRow(p);
                }
            }
        if(getvalue.substring(0,2).equals("PL")){
                lo.customerDataLoad(getvalue.substring(2,getvalue.length()));
                model.addRow(new Object[]{"Full name",lo.getC_Name()});model.addRow(new Object[]{"Permanent Address",lo.getC_Add()});
                model.addRow(new Object[]{"National Id No",lo.getC_ID()});model.addRow(new Object[]{"Date of Birth",lo.getC_dob()});
                model.addRow(new Object[]{"Religion",lo.getC_Relig()});model.addRow(new Object[]{"Gender",lo.getC_Gen()});
                model.addRow(new Object[]{"Nationality",lo.getC_Nati()});model.addRow(new Object[]{"Mobile Phone Number",lo.getC_HPhone()});
                model.addRow(new Object[]{"Land Phone Number",lo.getC_LPhone()});model.addRow(new Object[]{"E-Mail Address",lo.getC_email()});
                model.addRow(new Object[]{"G/S Division Name & No",lo.getC_Gs()});model.addRow(new Object[]{"Postal Code",lo.getC_Pos()});
                model.addRow(new Object[]{"Duration of Stay",lo.getC_Dur()});model.addRow(new Object[]{"Marital Status",lo.getC_Mar()});

              
            try {
                DefaultTableModel model2 = (DefaultTableModel) guarantorsDetailsColect.getModel();
                while(model2.getRowCount()>0){
                    for(int p=0;p<model2.getRowCount();p++){
                        model2.removeRow(p);
                    }
                }
                gurantorsDetails(getvalue.substring(2,getvalue.length()));
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Faild \n\n"+e.getMessage());
            }
        
        }else if(getvalue.substring(0,2).equals("BL")){//bussiness
                lo.businessDataLoad(getvalue.substring(2,getvalue.length()));
                model.addRow(new Object[]{"Business name",lo.getbName()});
                model.addRow(new Object[]{"Permanent Address",lo.getbAddress()});
                model.addRow(new Object[]{"Business Email",lo.getbEmail()});
                model.addRow(new Object[]{"Business Reg No",lo.getBreg()});
                model.addRow(new Object[]{"Nature of Business",lo.getBnature()});
                model.addRow(new Object[]{"Business Location",lo.getbLocation()});
                model.addRow(new Object[]{"Business Location Ownership",lo.getbLocation()});
                model.addRow(new Object[]{"Duration At Present Location",lo.getbDuration()});
                model.addRow(new Object[]{"Experience In Business",lo.getbExpeince()});
                model.addRow(new Object[]{"Availability of Insurance Policy",lo.getbAvailability()});
                model.addRow(new Object[]{"Mobile Phone Number",lo.getBmobile()});
                model.addRow(new Object[]{"Fixed Phone Number",lo.getbFix()});
                model.addRow(new Object[]{"Business Postal Code",lo.getbPost()});
                model.addRow(new Object[]{"Sum of Insured",lo.getBsum()}); 

        }else{
            DefaultTableModel model2 = (DefaultTableModel)personalDetailsColect.getModel();
            while(model2.getRowCount()>0){
                for(int p=0;p<model2.getRowCount();p++){
                    model2.removeRow(p);
                }
            }
            DefaultTableModel mode3 = (DefaultTableModel)guarantorsDetailsColect.getModel();
            while(mode3.getRowCount()>0){
                for(int p=0;p<mode3.getRowCount();p++){
                    mode3.removeRow(p);
                }
            }
        } 
        

    }
   public void gurantorsDetails(String rate_name){
       DefaultTableModel model = (DefaultTableModel) guarantorsDetailsColect.getModel();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM c_guarantor_details WHERE CustomerIdNumber='"+rate_name+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object w[]={rs.getString("Guarantor_Name"),rs.getString("Guarantor_Nic"),rs.getInt("Guarantor_Phone_Number"),rs.getInt("Guarantor_Land_Phone"),rs.getString("Guarantor_Home_Address"),rs.getString("Guarantor_Email")};
                model.addRow(w);
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
   }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        personalDetailsColect = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        guarantorsDetailsColect = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Details");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        personalDetailsColect.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        personalDetailsColect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        personalDetailsColect.setDragEnabled(true);
        personalDetailsColect.setGridColor(new java.awt.Color(255, 255, 255));
        personalDetailsColect.setRowHeight(24);
        personalDetailsColect.setShowHorizontalLines(false);
        personalDetailsColect.setShowVerticalLines(false);
        jScrollPane6.setViewportView(personalDetailsColect);

        guarantorsDetailsColect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Guarantor's Name", "NIC No", "Hand Phone Number", "Land Phone Number", "Home Address", "Email Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        guarantorsDetailsColect.setRowHeight(24);
        guarantorsDetailsColect.setSelectionBackground(new java.awt.Color(204, 204, 204));
        guarantorsDetailsColect.setShowVerticalLines(false);
        guarantorsDetailsColect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guarantorsDetailsColectMouseClicked(evt);
            }
        });
        guarantorsDetailsColect.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                guarantorsDetailsColectKeyReleased(evt);
            }
        });
        jScrollPane14.setViewportView(guarantorsDetailsColect);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void guarantorsDetailsColectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guarantorsDetailsColectMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_guarantorsDetailsColectMouseClicked

    private void guarantorsDetailsColectKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guarantorsDetailsColectKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_guarantorsDetailsColectKeyReleased

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
            java.util.logging.Logger.getLogger(PersonDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable guarantorsDetailsColect;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable personalDetailsColect;
    // End of variables declaration//GEN-END:variables
}
