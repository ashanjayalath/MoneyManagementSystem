package AdminForm;

import GetterSetter.BusinessLoanTempory;
import GetterSetter.PersonalLoanTempory;
import GetterSetter.Shares;
import LoanForms.LoanGet;
import classPack.DBQuary;
import classPack.DatabaseConnection;
import classPack.LoanCalculation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import scrollbar.ScrollBarCustom;


public class AdminDashboard extends javax.swing.JPanel {    
    private double payment,dueAmount;
    private String setMonth="";
    private String setRate="";
    public AdminDashboard() {
        initComponents();
        
        ScrollBarCustom sc1=new ScrollBarCustom();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        sc1.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane1.setHorizontalScrollBar(sc1);        
        
        ScrollBarCustom sc2=new ScrollBarCustom();
        jScrollPane6.setVerticalScrollBar(new ScrollBarCustom());
        sc2.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane6.setHorizontalScrollBar(sc2); 
        
        ScrollBarCustom sc3=new ScrollBarCustom();
        jScrollPane7.setVerticalScrollBar(new ScrollBarCustom());
        sc3.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane7.setHorizontalScrollBar(sc3);         
        
        
    }

    PersonalLoanTempory personalTempory=new PersonalLoanTempory();
    BusinessLoanTempory business=new BusinessLoanTempory();
    Shares share=new Shares();
    public void tableLoad(String getName){
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM c_personal_loan_tempory WHERE loanStatus='"+getName+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);           
            ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Object w[]={rs.getInt("loanId"),rs.getString("CustomerIdNumber"),rs.getString("loanName"),rs.getString("loanType"),rs.getDouble("loanAmount"),rs.getInt("loanTerms"),rs.getString("EmployId"),rs.getString("formNo")};//
                    model.addRow(w);
                }
            //ratingName.addItem(getRateName);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//personal
    public void tableLoadHold(String getName,int type){
        DefaultTableModel model = (DefaultTableModel) personalHoldTable.getModel();//personal
        DefaultTableModel model2 = (DefaultTableModel) bussinessHoldTable.getModel();//business
        String sql;

        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            if(type==1){
                //model = (DefaultTableModel) personalHoldTable.getModel();
                sql = "SELECT * FROM c_personal_loan_tempory WHERE loanStatus='"+getName+"' ";
            }else{
               // model = (DefaultTableModel) bussinessHoldTable.getModel();   
                sql = "SELECT * FROM c_business_loan_tempory WHERE loanStatus='"+getName+"' ";
            }
            
            
            PreparedStatement pst = conn.prepareStatement(sql);           
            ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    if(type==1){
                        Object w[]={rs.getInt("loanId"),rs.getString("CustomerIdNumber"),rs.getString("loanName"),rs.getString("loanType"),rs.getDouble("loanAmount"),rs.getInt("loanTerms"),rs.getString("EmployId"),rs.getString("formNo"),rs.getString("comment")};//
                        model.addRow(w);
                    }else{
                        Object w[]={rs.getInt("loanId"),rs.getString("BusinessRegisteredNo"),rs.getString("loanName"),rs.getString("loanType"),rs.getDouble("loanAmount"),rs.getInt("loanTerms"),rs.getString("EmployId"),rs.getString("formNo"),rs.getString("comment")};
                        model2.addRow(w);
                    }
                    
                }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//personal,business   
    public void clearAllTableDataHold(int type){
        if(type==1){
        DefaultTableModel model = (DefaultTableModel) personalHoldTable.getModel();
        while(true){
            if(personalHoldTable.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
        }else{
        DefaultTableModel model = (DefaultTableModel) bussinessHoldTable.getModel();
        while(true){
            if(bussinessHoldTable.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }            
        }
    }    //personal and business
    public void clearAllTableData(){

        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        while(true){
            if(customerTable.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
    }
    public void callReject(){
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        int id;
        int []getSelectedRowIndex=customerTable.getSelectedRows();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            for(Integer a:getSelectedRowIndex) {
                id = (int) model.getValueAt(a, 0);
                String sql = "DELETE FROM c_personal_loan_tempory WHERE `loanId`='"+id+"' ";//WHERE Rating_Id_name='"+getData+"' 
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
            }            
        conn.close();            
        }catch(Exception v){
            System.err.println(v);
        }
    }
    public void holdCommentPersonal(String comment){
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        int id;
        int getSelectedRowIndex=customerTable.getSelectedRow();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            id = (int) model.getValueAt(getSelectedRowIndex, 0);
            String sql = "UPDATE  c_personal_loan_tempory SET comment='"+comment+"' WHERE loanId='"+id+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void callAccept(){
        try{
            DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
            int []getSelectedRowIndex=customerTable.getSelectedRows();
            String loanStatus ="Accept";
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            Date d=new Date();
            String loanIshuDate=df.format(d);
            
            
            java.sql.Connection conn =DatabaseConnection.connect();
            for(Integer a:getSelectedRowIndex) {
                int loanId = (int) model.getValueAt(a, 0);
                DBQuary.getPersonalLoanTemporary(personalTempory, loanId);
                getPersonalLoan(personalTempory.getLoanName(),personalTempory.getLoanAmount(),personalTempory.getLoanInterestRate(),personalTempory.getLoanTerms());//loan name,amount,interes,terms
                String sql2 = "INSERT INTO personal_loan(loanIshuDate,loanName,loanType,loanInterestRate,loanTerms,loanAmount,monthlyPayment,profit,payment,dueAmount,loanStatus,CustomerIdNumber,EmployId,TotalAmount,futurePayment,loanNumber,comment) VALUES ('"+loanIshuDate+"','"+personalTempory.getLoanName()+"','"+personalTempory.getLoanType()+"','"+personalTempory.getLoanInterestRate()+"','"+personalTempory.getLoanTerms()+"','"+personalTempory.getLoanAmount()+"','"+setMonth+"','"+setRate+"','','"+dueAmount+"','"+loanStatus+"','"+personalTempory.getCustomerIdNumber()+"','"+personalTempory.getEmployId()+"','','','"+personalTempory.getFormNo()+"','')";
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                pst2.execute();
                
                
            }
            
        JOptionPane.showMessageDialog(null,"Accept...");
        //model.setRowCount(0);
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }        
        callReject();
        clearAllTableData();
        
}
    public void getPersonalLoan(String loanName,double loanAmount,double loanRate,int loanTerms){//amount,interest,term
        LoanCalculation lon=new LoanCalculation();
        //this.loanTerms=loanTerms;
        if(loanName.equals("Diriya") || loanName.equals("Swashakthi")){//diriya
            lon.equatedInstalment(loanAmount, loanRate, loanTerms);//week1 week2
        }else if(loanName.equals("Sahana") || loanName.equals("Saving")){//sahana
             //callequalInstement
            lon.equalInstalment(loanAmount,loanRate,loanTerms);//month year              
        }
        else if(loanName.equals("Easy")){//easy
            lon.equatedInstalmentNormal(loanAmount, loanRate, loanTerms);//day
        }else{

        }
        for(Double c:lon.monthlyPayment){
            //getMonthlyPayment.add(c);
            setMonth=setMonth+c;
            if(lon.monthlyPayment.size()-1!=c){
                setMonth=setMonth+",";
            }
        }       
        System.out.println(setMonth);
        for(Double x:lon.interestValue){
            //getProfit.add(x);
            setRate=setRate+x;
            if(lon.interestValue.size()-1!=x){
                setRate=setRate+",";
            }            
        }

    }
    public void callPending(String satus){
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        int []getSelectedRowIndex=customerTable.getSelectedRows();
        int id;
        //String status="Pending";
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            for(Integer a:getSelectedRowIndex) {
                id=(int) model.getValueAt(a, 0);
            
                String sql = "UPDATE c_personal_loan_tempory SET loanStatus='"+satus+"' WHERE loanId='"+id+"' "; 

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();

            }
            //ratingName.addItem(getName);
            JOptionPane.showMessageDialog(null,"Sucesss...");
            model.setRowCount(0);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        clearAllTableData();
        tableLoad("Request");
        
    }
    public void tableLoadBusiness(String getName){
        DefaultTableModel model = (DefaultTableModel) businessTable.getModel();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM c_business_loan_tempory WHERE loanStatus='"+getName+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);           
            ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Object w[]={rs.getInt("loanId"),rs.getString("BusinessRegisteredNo"),rs.getString("loanName"),rs.getString("loanType"),rs.getDouble("loanAmount"),rs.getInt("loanTerms"),rs.getString("EmployId"),rs.getString("formNo")};//
                    model.addRow(w);
                }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//business
    public void clearAllTableDataBusiness(){

        DefaultTableModel model = (DefaultTableModel) businessTable.getModel();
        while(true){
            if(businessTable.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
    }//business
    public void callRejectBusiness(){
        DefaultTableModel model = (DefaultTableModel) businessTable.getModel();
        int []getSelectedRowIndex=businessTable.getSelectedRows();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            for(Integer a:getSelectedRowIndex) {
                int id = (int) model.getValueAt(a, 0);
                DBQuary.deleteBusinessLoanTemporary(id);
            }            
        conn.close();            
        }catch(Exception v){
            System.err.println(v);
        }
    }//business
    public void holdCommentBusiness(String comment){
        DefaultTableModel model = (DefaultTableModel) businessTable.getModel();
        int id;
        int getSelectedRowIndex=businessTable.getSelectedRow();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            id = (int) model.getValueAt(getSelectedRowIndex, 0);
            String sql = "UPDATE  c_business_loan_tempory SET comment='"+comment+"' WHERE loanId='"+id+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            //ratingName.addItem(getRateName);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//business
    public void callAcceptBusiness(){
        LoanCalculation lon=new LoanCalculation();

        //accept recodes
        //1.select the row and get datas and pass the data loan table
        //2.after the delet tempory table data
        //customerTable
        DefaultTableModel model = (DefaultTableModel) businessTable.getModel();
        int []getSelectedRowIndex=businessTable.getSelectedRows();
        String loanStatus ="Accept";
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date d=new Date();
        String loanIshuDate=df.format(d);
        
        //form number is printed in every form
        try{
            java.sql.Connection conn =DatabaseConnection.connect();
            for(Integer a:getSelectedRowIndex) {
                int loanId = (int) model.getValueAt(a, 0);
                DBQuary.getBusinessLoanTemporary(business, loanId);
                //getPersonalLoan(loanName,loanAmount,loanRate,loanTerms);//loan name,amount,interes,terms
                getPersonalLoan(business.getLoanName(),business.getLoanAmount(),business.getLoanInterestRate(),business.getLoanTerms());//loan name,amount,interes,terms
                
                String sql2 = "INSERT INTO business_loan(loanIshuDate,loanName,loanType,loanInterestRate,loanTerms,loanAmount,monthlyPayment,profit,payment,dueAmount,loanStatus,BusinessRegisteredNo,EmployId,TotalAmount,futurePayment,loanNumber,comment) VALUES ('"+loanIshuDate+"','"+business.getLoanName()+"','"+business.getLoanType()+"','"+business.getLoanInterestRate()+"','"+business.getLoanTerms()+"','"+business.getLoanAmount()+"','"+setMonth+"','"+setRate+"','"+payment+"','"+dueAmount+"','"+loanStatus+"','"+business.getBusinessRegisteredNo()+"','"+business.getEmployId()+"','','','"+business.getFormNo()+"','')";
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                pst2.execute();
                
                
            }
            
        JOptionPane.showMessageDialog(null,"Accept...");
        //model.setRowCount(0);
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }        
        callRejectBusiness();
        clearAllTableDataBusiness();
        
}//business
    public void getBusinessLoan(String loanName,double loanAmount,double loanRate,int loanTerms){//amount,interest,term
        LoanCalculation lon=new LoanCalculation();
        //this.loanTerms=loanTerms;
        if(loanName.equals("Diriya") || loanName.equals("Swashakthi")){//diriya
            lon.equatedInstalment(loanAmount, loanRate, loanTerms);//week1 week2
        }else if(loanName.equals("Sahana") || loanName.equals("Saving")){//sahana
             //callequalInstement
            lon.equalInstalment(loanAmount,loanRate,loanTerms);//month year              
        }
        else if(loanName.equals("Easy")){//easy
            lon.equatedInstalmentNormal(loanAmount, loanRate, loanTerms);//day
        }else{

        }
        for(Double c:lon.monthlyPayment){
            //getMonthlyPayment.add(c);
            setMonth=setMonth+c;
            if(lon.monthlyPayment.size()-1!=c){
                setMonth=setMonth+",";
            }
        }       
        System.out.println(setMonth);
        for(Double x:lon.interestValue){
            //getProfit.add(x);
            setRate=setRate+x;
            if(lon.interestValue.size()-1!=x){
                setRate=setRate+",";
            }            
        }

    }
    public void callPendingBusiness(String satus){
        DefaultTableModel model = (DefaultTableModel) businessTable.getModel();
        int []getSelectedRowIndex=businessTable.getSelectedRows();
        int id;
        //String status="Pending";
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            for(Integer a:getSelectedRowIndex) {
                id=(int) model.getValueAt(a, 0);
            
                String sql = "UPDATE c_business_loan_tempory SET loanStatus='"+satus+"' WHERE loanId='"+id+"' "; 

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();

            }
            //ratingName.addItem(getName);
            JOptionPane.showMessageDialog(null,"Sucesss...");
            model.setRowCount(0);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        clearAllTableDataBusiness();
        tableLoadBusiness("Request");
        
    }
    public void approallHold(String type,int action){//pesonal,business
        //java.sql.Connection conn =DatabaseConnection.connect();
        int getSelectRow=personalHoldTable.getSelectedRow();
        int getSelectRow2=bussinessHoldTable.getSelectedRow();
        String setValue="";
        if(action==0){
            setValue="save";
        }else if(action==1){
            setValue="Request";
        }

        if(type.equals("personal")){
            try {
                DefaultTableModel model = (DefaultTableModel) personalHoldTable.getModel();
                String nic=model.getValueAt(getSelectRow,1).toString();
                java.sql.Connection conn =DatabaseConnection.connect();
                String sql="UPDATE c_personal_loan_tempory SET loanStatus='"+setValue+"' WHERE CustomerIdNumber='"+nic+"' ";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();      

                JOptionPane.showMessageDialog(null,"Done");
                conn.close();
            }catch(Exception c){
                
            }
        }else if(type.equals("business")){//load and save bussiness
                try{
                    DefaultTableModel model = (DefaultTableModel) bussinessHoldTable.getModel();
                    String reg=model.getValueAt(getSelectRow2,1).toString();
                    java.sql.Connection conn =DatabaseConnection.connect();
                    String sql="UPDATE c_business_loan_tempory SET loanStatus='"+setValue+"' WHERE BusinessRegisteredNo='"+reg+"' ";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();      


                JOptionPane.showMessageDialog(null,"Done");
                conn.close();

            } catch (Exception e) {
                System.out.println(e);
            }//catch(DuplicateEntityException)
         }

    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ccc = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        btnAccept = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        btnPending = new javax.swing.JButton();
        btnHold = new javax.swing.JButton();
        loadPending = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        businessTable = new javax.swing.JTable();
        btnAccept1 = new javax.swing.JButton();
        btnReject1 = new javax.swing.JButton();
        btnPending1 = new javax.swing.JButton();
        btnHold1 = new javax.swing.JButton();
        loadPending1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        personalHoldTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        bussinessHoldTable = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(244, 244, 244));

        jScrollPane1.setBorder(null);

        ccc.setBackground(new java.awt.Color(255, 255, 255));

        jLabel73.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel73.setText("Personal Loan Request");
        jLabel73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel73MouseClicked(evt);
            }
        });

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Customer Id", "Loan Name", "Loan Type", "Loan Amount", "Loan Terms", "Employ Id", "Form Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerTable.setRowHeight(24);
        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(customerTable);

        btnAccept.setText("Accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        btnReject.setText("Reject");
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });

        btnPending.setText("Pending");
        btnPending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendingActionPerformed(evt);
            }
        });

        btnHold.setText("Hold");
        btnHold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoldActionPerformed(evt);
            }
        });

        loadPending.setText("Load Pending");
        loadPending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPendingActionPerformed(evt);
            }
        });

        jButton7.setText("Load Data");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cccLayout = new javax.swing.GroupLayout(ccc);
        ccc.setLayout(cccLayout);
        cccLayout.setHorizontalGroup(
            cccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cccLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(cccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(cccLayout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator7)
                    .addGroup(cccLayout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loadPending, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addGap(349, 349, 349)
                        .addComponent(btnAccept, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPending, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHold, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReject, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cccLayout.setVerticalGroup(
            cccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cccLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(cccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(btnAccept)
                    .addComponent(btnPending)
                    .addComponent(btnHold)
                    .addComponent(btnReject)
                    .addComponent(loadPending))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addGap(209, 209, 209))
        );

        jScrollPane1.setViewportView(ccc);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jTabbedPane1.addTab("     Personal Loan Request     ", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setBorder(null);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        businessTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "B Registation No", "Loan Name", "Loan Type", "Loan Amount", "Loan Terms", "Employ Id", "Form Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(businessTable);

        btnAccept1.setText("Accept");
        btnAccept1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccept1ActionPerformed(evt);
            }
        });

        btnReject1.setText("Reject");
        btnReject1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReject1ActionPerformed(evt);
            }
        });

        btnPending1.setText("Pending");
        btnPending1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPending1ActionPerformed(evt);
            }
        });

        btnHold1.setText("Hold");
        btnHold1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHold1ActionPerformed(evt);
            }
        });

        loadPending1.setText("Load Pending");
        loadPending1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPending1ActionPerformed(evt);
            }
        });

        jButton8.setText("Load Data");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel74.setText("Business Loan Request");
        jLabel74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel74MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 1211, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(loadPending1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                .addGap(751, 751, 751))
                            .addComponent(jLabel74))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(553, 553, 553)
                                .addComponent(btnAccept1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPending1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHold1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReject1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                            .addComponent(jScrollPane3))
                        .addGap(50, 50, 50))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(loadPending1)
                    .addComponent(btnAccept1)
                    .addComponent(btnPending1)
                    .addComponent(btnHold1)
                    .addComponent(btnReject1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3)
                .addGap(204, 204, 204))
        );

        jScrollPane6.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
        );

        jTabbedPane1.addTab("     Business Loan Request     ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setBorder(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel75.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel75.setText("Hold Loan Details");
        jLabel75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel75MouseClicked(evt);
            }
        });

        personalHoldTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Customer Id", "Loan Name", "Loan Type", "Loan Amount", "Loan Terms", "Employ Id", "Form Number", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        personalHoldTable.setRowHeight(24);
        personalHoldTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personalHoldTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                personalHoldTableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(personalHoldTable);

        bussinessHoldTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "B Registation No", "Loan Name", "Loan Type", "Loan Amount", "Loan Terms", "Employ Id", "Form Number", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bussinessHoldTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bussinessHoldTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(bussinessHoldTable);

        jButton9.setText("Load Data");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Load Data");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton1.setText("Re-Call");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Re-Call");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Re Accepted");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Re Accepted");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel76.setText("Personal Loan");
        jLabel76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel76MouseClicked(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel77.setText("Business Loan");
        jLabel77.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel77MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane4)
                            .addComponent(jSeparator9))
                        .addGap(50, 50, 50))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)))
                        .addGap(951, 951, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel77)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel76)
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("     Loan Hold     ", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel73MouseClicked

    }//GEN-LAST:event_jLabel73MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        clearAllTableData();
        tableLoad("Request");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        int selectedRow=customerTable.getSelectedRow();
        
        if(customerTable.getRowCount()>=1){
            int c=JOptionPane.showConfirmDialog(null,"Do you want Accept this?");
            if(c==0){
                try {
                    java.sql.Connection conn =DatabaseConnection.connect();                 
                    DBQuary.getSharesData(share);
                    double LoanAMount=(double) model.getValueAt(selectedRow,4);
                    double NewShare=share.getIshuMoney()+LoanAMount;
                    double NewTotal=share.getTotal()-LoanAMount;
                    
                    if(NewTotal>0){
                        String sql = "UPDATE micro_shares SET total_ishu_money='"+NewShare+"' , total_shares='"+NewTotal+"' WHERE share_id = 1 "; 
                        PreparedStatement pst2 = conn.prepareStatement(sql);
                        pst2.execute();  
                        callAccept();
                        tableLoad("Request");
                        
                        DBQuary.getSharesData(share);
                    }else{
                        JOptionPane.showMessageDialog(null,"Company shares Low..You can't Accept This Loan.");
                    }
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            
            }

        }
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnPendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendingActionPerformed
        //clearAllTableData();
        callPending("Pending");
        
//tableLoad("Request");
    }//GEN-LAST:event_btnPendingActionPerformed

    private void loadPendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadPendingActionPerformed
        clearAllTableData();
        tableLoad("Pending");
    }//GEN-LAST:event_loadPendingActionPerformed

    private void btnHoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoldActionPerformed
        holdCommentPersonal(JOptionPane.showInputDialog(null,"Add comment\nWhy you Hold This Request?"));
        callPending("Hold");
        clearAllTableData();
        tableLoad("Request");
    }//GEN-LAST:event_btnHoldActionPerformed

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectActionPerformed
        callReject();
        clearAllTableData();
        tableLoad("Request");
    }//GEN-LAST:event_btnRejectActionPerformed

    private void btnAccept1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccept1ActionPerformed

        DefaultTableModel model = (DefaultTableModel) businessTable.getModel();
        int selectedRow=businessTable.getSelectedRow();
        
        if(businessTable.getRowCount()>=1){
            int c=JOptionPane.showConfirmDialog(null,"Do you want Accept this?");
            if(c==0){
                try {
                    java.sql.Connection conn =DatabaseConnection.connect();
                    DBQuary.getSharesData(share);
                    double LoanAMount=(double) model.getValueAt(selectedRow,4);
                    double NewShare=share.getIshuMoney()+LoanAMount;
                    double NewTotal=share.getTotal()-LoanAMount;                    
                    if(NewTotal>0){
                        String sql = "UPDATE micro_shares SET total_ishu_money='"+NewShare+"' , total_shares='"+NewTotal+"' WHERE share_id = 1 "; 
                        PreparedStatement pst2 = conn.prepareStatement(sql);
                        pst2.execute(); 
                        
                        callAcceptBusiness();
                        tableLoadBusiness("Request");
                        DBQuary.getSharesData(share);
                        
                        
                    }else{
                        JOptionPane.showMessageDialog(null,"Company shares Low..You can't Accept This Loan.");
                    }
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            
            }

        }


    }//GEN-LAST:event_btnAccept1ActionPerformed

    private void btnReject1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReject1ActionPerformed
        callRejectBusiness();
        clearAllTableDataBusiness();
        tableLoadBusiness("Request");
    }//GEN-LAST:event_btnReject1ActionPerformed

    private void btnPending1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPending1ActionPerformed
        callPendingBusiness("Pending");
        
    }//GEN-LAST:event_btnPending1ActionPerformed

    private void btnHold1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHold1ActionPerformed
        holdCommentBusiness(JOptionPane.showInputDialog(null,"Add comment\nWhy you Hold This Request?"));
        callPendingBusiness("Hold");
        clearAllTableDataBusiness();
        tableLoadBusiness("Request");


    }//GEN-LAST:event_btnHold1ActionPerformed

    private void loadPending1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadPending1ActionPerformed
        clearAllTableDataBusiness();
        tableLoadBusiness("Pending");
    }//GEN-LAST:event_loadPending1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        clearAllTableDataBusiness();
        tableLoadBusiness("Request");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jLabel74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel74MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel74MouseClicked

    private void jLabel75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel75MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel75MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        clearAllTableDataHold(1);
        tableLoadHold("Hold",1);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        clearAllTableDataHold(0);
        tableLoadHold("Hold",0);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void personalHoldTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personalHoldTableMouseClicked
        DefaultTableModel model = (DefaultTableModel) personalHoldTable.getModel();
        ArrayList<String> dividedMessag=new ArrayList<String>();
        int getRow=personalHoldTable.getSelectedRow();
        int getColm=personalHoldTable.getSelectedColumn();
        if(getColm==8){
            String coment=model.getValueAt(getRow, 8).toString();
            String id=model.getValueAt(getRow, 1).toString();
            String g[]=coment.split("");
            String z="";String y[];int p=100;int x=0;
            for(String c:g){
                while(x<g.length){
                    if(x!=p){z+=g[x];
                    }else{z+="\n";dividedMessag.add(z);z="";x-=1;p+=100;}
                    x++;
                }
            }dividedMessag.add(z);
            String qq="";
            for(String m:dividedMessag){
                qq+=m;
            }
            Icon ico=new javax.swing.ImageIcon(getClass().getResource("/icon/bPlaint.png"));
            JOptionPane.showMessageDialog(null,qq,"Hold Loan Comment Window - "+id,JOptionPane.OK_OPTION,ico);
        }
        
    }//GEN-LAST:event_personalHoldTableMouseClicked

    private void personalHoldTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personalHoldTableMousePressed

    }//GEN-LAST:event_personalHoldTableMousePressed

    private void bussinessHoldTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bussinessHoldTableMouseClicked
        DefaultTableModel model = (DefaultTableModel) bussinessHoldTable.getModel();
        ArrayList<String> dividedMessag=new ArrayList<String>();
        int getRow=bussinessHoldTable.getSelectedRow();
        int getColm=bussinessHoldTable.getSelectedColumn();
        if(getColm==8){
            String coment=model.getValueAt(getRow, 8).toString();
            String id=model.getValueAt(getRow, 1).toString();
            String g[]=coment.split("");
            String z="";String y[];int p=100;int x=0;
            for(String c:g){
                while(x<g.length){
                    if(x!=p){z+=g[x];
                    }else{z+="\n";dividedMessag.add(z);z="";x-=1;p+=100;}
                    x++;
                }
            }dividedMessag.add(z);
            String qq="";
            for(String m:dividedMessag){
                qq+=m;
            }
            JOptionPane.showMessageDialog(null,qq,"Hold Loan Comment Window - "+id,JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_bussinessHoldTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        approallHold("personal",0);//0 save 1 request
        clearAllTableDataHold(1);
        tableLoadHold("Hold",1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        approallHold("business",0);
        clearAllTableDataHold(0);
        tableLoadHold("Hold",0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        approallHold("personal",1);
        clearAllTableDataHold(1);
        tableLoadHold("Hold",1);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        approallHold("business",1);
        clearAllTableDataHold(0);
        tableLoadHold("Hold",0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel76MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel76MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel76MouseClicked

    private void jLabel77MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel77MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel77MouseClicked

    private void customerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_customerTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnAccept1;
    private javax.swing.JButton btnHold;
    private javax.swing.JButton btnHold1;
    private javax.swing.JButton btnPending;
    private javax.swing.JButton btnPending1;
    private javax.swing.JButton btnReject;
    private javax.swing.JButton btnReject1;
    private javax.swing.JTable businessTable;
    private javax.swing.JTable bussinessHoldTable;
    private javax.swing.JPanel ccc;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton loadPending;
    private javax.swing.JButton loadPending1;
    private javax.swing.JTable personalHoldTable;
    // End of variables declaration//GEN-END:variables
}
