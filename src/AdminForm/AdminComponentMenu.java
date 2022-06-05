package AdminForm;

import LoanForms.LoanGet;
import classPack.DatabaseConnection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class AdminComponentMenu extends javax.swing.JPanel {

    private String getPayment,payment;
    private int type;


    public AdminComponentMenu() {
        initComponents();
    }

    public void clearAllTableData(){

        DefaultTableModel model = (DefaultTableModel) loanPaymentTable.getModel();
        while(true){
            if(loanPaymentTable.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
    }    
    public void clearMainTable(){
        DefaultTableModel model = (DefaultTableModel) loanHistory.getModel();
        while(true){
            if(loanHistory.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
    }
    
    public void getCompleteLoanDetails(String getId){
       DefaultTableModel model = (DefaultTableModel) loanHistory.getModel();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM personal_loan_complete WHERE CustomerIdOrBusinessReg='"+getId+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);           
            ResultSet rs = pst.executeQuery();           
            while(rs.next()){
                Object w[]={rs.getString("CustomerIdOrBusinessReg"),rs.getString("loanCategory"),rs.getString("loanIshuDate"),rs.getString("loanNumber"),rs.getString("loanName"),rs.getString("loanType"),rs.getString("loanAmount"),rs.getString("loanInterestRate"),rs.getString("loanTerms"),rs.getString("EmployId")};//
                model.addRow(w);

            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }        
    
    }

    public void getPersonalData(String getId){
      DefaultTableModel model = (DefaultTableModel) loanHistory.getModel();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM personal_loan WHERE CustomerIdNumber='"+getId+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);           
            ResultSet rs = pst.executeQuery();           
            while(rs.next()){
                Object w[]={rs.getString("CustomerIdNumber"),rs.getString("loanStatus"),rs.getString("loanIshuDate"),rs.getString("loanNumber"),rs.getString("loanName"),rs.getString("loanType"),rs.getString("loanAmount"),rs.getString("loanInterestRate"),rs.getString("loanTerms"),rs.getString("EmployId")};//
                model.addRow(w);
                type=1;
            }   
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }    
    }//1
    public void getBusinessData(String getId){
      DefaultTableModel model = (DefaultTableModel) loanHistory.getModel();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM business_loan WHERE BusinessRegisteredNo='"+getId+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);           
            ResultSet rs = pst.executeQuery();           
            while(rs.next()){
                Object w[]={rs.getString("BusinessRegisteredNo"),rs.getString("loanStatus"),rs.getString("loanIshuDate"),rs.getString("loanNumber"),rs.getString("loanName"),rs.getString("loanType"),rs.getString("loanAmount"),rs.getString("loanInterestRate"),rs.getString("loanTerms"),rs.getString("EmployId")};//
                model.addRow(w);
                type=2;
            }
            
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }    
    }//2
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loan = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        loanHistory = new javax.swing.JTable();
        btnSearchComplete = new javax.swing.JButton();
        searchCompleteLoan = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        loanPaymentTable = new javax.swing.JTable();
        completeLoan = new javax.swing.JRadioButton();
        continueLoan = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel80.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel80.setText("Completed & Continue Loan History");

        loanHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Id or Business Reg", "Loan Category", "Loan Ishu Date", "Loan Number", "Loan Name", "Loan Type", "Loan Amount", "Loan Interest Rate", "Loan Terms", "Employ Id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        loanHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loanHistoryMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(loanHistory);

        btnSearchComplete.setText("Search");
        btnSearchComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCompleteActionPerformed(evt);
            }
        });

        loanPaymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Payment", "Payment History"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(loanPaymentTable);

        completeLoan.setBackground(new java.awt.Color(255, 255, 255));
        loan.add(completeLoan);
        completeLoan.setSelected(true);
        completeLoan.setText("Complete Loan");

        continueLoan.setBackground(new java.awt.Color(255, 255, 255));
        loan.add(continueLoan);
        continueLoan.setText("Continues Loan");

        jButton1.setText("Get Person / Business Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator10)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchCompleteLoan, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearchComplete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(completeLoan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(continueLoan)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addGap(44, 44, 44))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel80)
                .addGap(6, 6, 6)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchCompleteLoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchComplete)
                    .addComponent(completeLoan)
                    .addComponent(continueLoan)
                    .addComponent(jButton1))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(138, 138, 138))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCompleteActionPerformed
        clearAllTableData();
        clearMainTable();
        if(completeLoan.isSelected()){
            getCompleteLoanDetails(searchCompleteLoan.getText());
        }else{
            
            getPersonalData(searchCompleteLoan.getText());
            getBusinessData(searchCompleteLoan.getText());

        }
        
        
    }//GEN-LAST:event_btnSearchCompleteActionPerformed

    private void loanHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loanHistoryMouseClicked
        DefaultTableModel model2 = (DefaultTableModel) loanPaymentTable.getModel();
        int selectedRowIndex=loanHistory.getSelectedRow();
        TableModel model = loanHistory.getModel();

        String getId=(model.getValueAt(selectedRowIndex,0).toString());
        String loanNo=(model.getValueAt(selectedRowIndex,3).toString());
        clearAllTableData();
        if(completeLoan.isSelected()){
            try {
                java.sql.Connection conn =DatabaseConnection.connect();
                String sql = "SELECT monthlyPayment,payment FROM personal_loan_complete WHERE CustomerIdOrBusinessReg='"+getId+"' AND loanNumber='"+loanNo+"' ";
                PreparedStatement pst = conn.prepareStatement(sql);           
                ResultSet rs = pst.executeQuery();           
                while(rs.next()){
                    getPayment=rs.getString("monthlyPayment");
                    payment=rs.getString("payment");
                }
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            String []mon=getPayment.split(",");
            String []pay=payment.split(",");
            int a=0;
            int m=mon.length;//50
            int p=pay.length;//70
            int result=0;
            if(m>p){result=m;
            }else{result=p;}
            while(a<result){
                if(a>=m){model2.addRow(new Object[]{a+1,"",pay[a]});
                }else{model2.addRow(new Object[]{a+1,mon[a],pay[a]});}
                a++;
            }
        }else{
            try {
                java.sql.Connection conn =DatabaseConnection.connect();
                if(type==1){//personal
                    String sql = "SELECT monthlyPayment,payment FROM personal_loan WHERE CustomerIdNumber='"+getId+"' AND loanNumber='"+loanNo+"' ";
                    PreparedStatement pst = conn.prepareStatement(sql);           
                    ResultSet rs = pst.executeQuery();           
                    while(rs.next()){
                        getPayment=rs.getString("monthlyPayment");
                        payment=rs.getString("payment");
                    }
                }else if(type==2){//business
                    String sql2 = "SELECT monthlyPayment,payment FROM business_loan WHERE BusinessRegisteredNo='"+getId+"' AND loanNumber='"+loanNo+"' ";
                    PreparedStatement pst2 = conn.prepareStatement(sql2);           
                    ResultSet rs2 = pst2.executeQuery();           
                    while(rs2.next()){
                        getPayment=rs2.getString("monthlyPayment");
                        payment=rs2.getString("payment");
                    } 
                }else{                   
                }
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            String []mon=getPayment.split(",");
            String []pay=payment.split(",");
            int a=0;
            int m=mon.length;//50
            int p=pay.length;//70
            int result=0;
            if(m>p){result=m;
            }else{result=p;}
            while(a<result){
                if(a>=m){model2.addRow(new Object[]{a+1,"",pay[a]});
                }else{model2.addRow(new Object[]{a+1,mon[a],pay[a]});}
                a++;
            }
          
        }
       
    }//GEN-LAST:event_loanHistoryMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) loanHistory.getModel();
        
        int selectedRowIndex=loanHistory.getSelectedRow();
        String getId=(model.getValueAt(selectedRowIndex,3).toString());
        if(getId.substring(0,2).equals("PL")){
            //personal
            PersonDetails per=new PersonDetails(getId);
            per.show();
            per.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
        }else{
            //business
            PersonDetails per=new PersonDetails(getId);
            per.show();
            per.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
        }
    
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchComplete;
    private javax.swing.JRadioButton completeLoan;
    private javax.swing.JRadioButton continueLoan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.ButtonGroup loan;
    private javax.swing.JTable loanHistory;
    private javax.swing.JTable loanPaymentTable;
    private javax.swing.JTextField searchCompleteLoan;
    // End of variables declaration//GEN-END:variables
}
