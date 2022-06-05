package LoanForms;

import classPack.DatabaseConnection;
import java.awt.Color;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import scrollbar.ScrollBarCustom;

public class LoanPayment extends javax.swing.JPanel {

    private ArrayList<Double> monthPay=new ArrayList<Double>();//get default monthly payment
    private ArrayList<String> paymentStore=new ArrayList<String>();//get payment values


    private String profit,payment,keyWord="",loanNumber,CustomerName,loanName,loanType,loanAmount,loanIshuDate,CustomerId,empId;
    private String getDefaultPayment;
    private double blance,amount,paymentValue,getTotal,rate;
    private double getDueAmount;
    private double loadFuturePay;
    private String dueAmount,loanStatus;
    private int mon,rat,pay=0,loanTerms,a=1;
    private double totalDefaultMonthlyPayment,tempMonth,due;
    //private int choose;
    private double futePay;

    private double tem;
    
    public LoanPayment() {
        initComponents();

        ScrollBarCustom sc=new ScrollBarCustom();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        sc.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane1.setHorizontalScrollBar(sc);
    }
    public void clearAllTableData(){

        DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
        while(true){
            if(loanTable.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
    }
    public void loadLoanData(String getId,int type){//0 personal 1 business
       java.sql.Connection conn =DatabaseConnection.connect();
        try {
            if(type==0){//personal
               
                String sql = "SELECT * FROM personal_loan WHERE CustomerIdNumber='"+getId+"' ";
                PreparedStatement pst = conn.prepareStatement(sql);           
                ResultSet rs = pst.executeQuery();

                while(rs.next()){
                    loanIshuDate=rs.getString("loanIshuDate");loanName=rs.getString("loanName");loanType=rs.getString("loanType");
                    loanTerms=rs.getInt("loanTerms");loanAmount=rs.getString("loanAmount" );profit=rs.getString("profit" );
                    getDefaultPayment=rs.getString("monthlyPayment");payment=rs.getString("payment" );dueAmount=rs.getString("dueAmount");
                    loanStatus=rs.getString("loanStatus" );CustomerId=rs.getString("CustomerIdNumber" );getTotal=rs.getDouble("TotalAmount");
                    loadFuturePay=rs.getDouble("futurePayment");loanNumber=rs.getString("loanNumber");empId=rs.getString("Employid");
                    rate=rs.getDouble("loanInterestRate");

                    cus_nic.setText(CustomerId);loan_number.setText(loanNumber);
                    loan_Amount.setText(loanAmount);Due_Amount.setText(dueAmount);loan_Name.setText(loanName);
                    Loan_Type.setText(loanType);Loan_Ishu_Date.setText(loanIshuDate);Loan_satus.setText(loanStatus);
                    future_payment.setText(String.valueOf(loadFuturePay));
                }
            }else if(type==1){//business
                String sql = "SELECT * FROM business_loan WHERE BusinessRegisteredNo='"+getId+"' ";
                PreparedStatement pst = conn.prepareStatement(sql);           
                ResultSet rs = pst.executeQuery();

                while(rs.next()){
                    loanIshuDate=rs.getString("loanIshuDate");loanName=rs.getString("loanName");loanType=rs.getString("loanType");
                    loanTerms=rs.getInt("loanTerms");loanAmount=rs.getString("loanAmount" );profit=rs.getString("profit" );
                    getDefaultPayment=rs.getString("monthlyPayment");payment=rs.getString("payment" );dueAmount=rs.getString("dueAmount");
                    loanStatus=rs.getString("loanStatus" );CustomerId=rs.getString("BusinessRegisteredNo" );getTotal=rs.getDouble("TotalAmount");
                    loadFuturePay=rs.getDouble("futurePayment");loanNumber=rs.getString("loanNumber");empId=rs.getString("Employid");
                    rate=rs.getDouble("loanInterestRate");

                    cus_nic.setText(CustomerId);loan_number.setText(loanNumber);
                    loan_Amount.setText(loanAmount);Due_Amount.setText(dueAmount);loan_Name.setText(loanName);
                    Loan_Type.setText(loanType);Loan_Ishu_Date.setText(loanIshuDate);Loan_satus.setText(loanStatus);
                    future_payment.setText(String.valueOf(loadFuturePay));
                }            
                
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }        
    }
    public void getPersonLoanDetails(String getId){//+++++++++++++++++++++++++++++++++++++
        double test = 0;
        DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
        
        try{
            loadLoanData(getId,0);
            loadName(getId,0);
            cus_name.setText(CustomerName);
            due=Double.valueOf(dueAmount);
            if(!loanStatus.equals("Active")){
                JOptionPane.showMessageDialog(null,"This loan Not Avtivated..\nPlease Active firstly");
                payBtn.setEnabled(false);
                activation.setEnabled(true);
                deactivation.setEnabled(false);
                loanClose.setEnabled(false);
                futuePayBtn.setEnabled(false);
            }else{
                payBtn.setEnabled(true);
                activation.setEnabled(false);
                deactivation.setEnabled(true);
                loanClose.setEnabled(true);
                futuePayBtn.setEnabled(true);
            }
            if(Loan_satus.getText().equals("Active"))
            {
                Loan_satus.setForeground(Color.GREEN);
            }else if(Loan_satus.getText().equals("Dieactivate"))
            {
                Loan_satus.setForeground(Color.BLUE);
            }else{
                Loan_satus.setForeground(Color.RED);
            }
            String mo[]=getDefaultPayment.split(",");
            String ra[]=profit.split(",");    
            String pa[]=payment.split(",");
            for(String p:pa){//get how many terms we are paid loan amount,counting terms
                if(p.isEmpty()){pay=0;}
                else{pay=pa.length;}
            }

            Loan_Terms.setText(String.valueOf(mo.length));
            Loan_Terms_cal.setText(String.valueOf(loanTerms));
            int r=0;


            for(String v:mo){
                monthPay.add(Double.parseDouble(v));
                test+=Double.parseDouble(v);

                if(r>=pay){
                    model.addRow(new Object[]{r+1,ra[r],mo[r],""});
                }else{
                    model.addRow(new Object[]{r+1,ra[r],mo[r],pa[r]});            
                }
                r++;
            }

            totalDefaultMonthlyPayment=test;

            totalPayment.setText(String.valueOf(getTotal-due));
            Loan_Total.setText(String.valueOf(totalDefaultMonthlyPayment)); 
            lblPaymentDisplay.setText(String.valueOf(monthPay.get(pay)));
            if(getTotal>0){
                totalPaybleAmount.setText(String.valueOf(totalDefaultMonthlyPayment-getTotal));
            }else{totalPaybleAmount.setText("");}
            if(Double.valueOf(dueAmount)>=1){
                lblPaymentDisplay2.setText(String.valueOf(monthPay.get(pay)+Double.valueOf(dueAmount)));
            }else{
                lblPaymentDisplay2.setText("");
            }

        }catch(Exception v){
            System.out.println(v.getMessage());
        }
        
    } //load data
    public void getBusinessLoanDetails(String getId){
        double test = 0;
        DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
        loadLoanData(getId,1);
        loadName(getId,1);
        cus_name.setText(CustomerName);
        due=Double.valueOf(dueAmount);
        if(!loanStatus.equals("Active")){
            JOptionPane.showMessageDialog(null,"This loan Not Avtivated..\nPlease Active firstly");
            payBtn.setEnabled(false);
            activation.setEnabled(true);
            deactivation.setEnabled(false);
            loanClose.setEnabled(false);
            futuePayBtn.setEnabled(false);
        }else{
            payBtn.setEnabled(true);
            activation.setEnabled(false);
            deactivation.setEnabled(true);
            loanClose.setEnabled(true);
            futuePayBtn.setEnabled(true);}
        if(Loan_satus.getText().equals("Active")){
            Loan_satus.setForeground(Color.GREEN);
        }else if(Loan_satus.getText().equals("Dieactivate")){
            Loan_satus.setForeground(Color.BLUE);
        }else{Loan_satus.setForeground(Color.RED);}
        String mo[]=getDefaultPayment.split(",");
        String ra[]=profit.split(",");    
        String pa[]=payment.split(",");
        for(String p:pa){//get how many terms we are paid loan amount,counting terms
            if(p.isEmpty()){pay=0;}
            else{pay=pa.length;}
        }

        Loan_Terms.setText(String.valueOf(mo.length));
        Loan_Terms_cal.setText(String.valueOf(loanTerms));
        int r=0;


        for(String v:mo){
            monthPay.add(Double.parseDouble(v));
            test+=Double.parseDouble(v);

            if(r>=pay){
                model.addRow(new Object[]{r+1,ra[r],mo[r],""});
            }else{
                model.addRow(new Object[]{r+1,ra[r],mo[r],pa[r]});            
            }
            r++;
        }

        totalDefaultMonthlyPayment=test;

        totalPayment.setText(String.valueOf(getTotal-due));
        Loan_Total.setText(String.valueOf(totalDefaultMonthlyPayment)); 
        lblPaymentDisplay.setText(String.valueOf(monthPay.get(pay)));
        if(getTotal>0){
            totalPaybleAmount.setText(String.valueOf(totalDefaultMonthlyPayment-getTotal));
        }else{totalPaybleAmount.setText("");}
        if(Double.valueOf(dueAmount)>=1){
            lblPaymentDisplay2.setText(String.valueOf(monthPay.get(pay)+Double.valueOf(dueAmount)));
        }else{
            lblPaymentDisplay2.setText("");
        }

        
        
    } //load data    
    public void setPayment(String getId){
        due=Double.valueOf(dueAmount);
        //update data
        try{
            java.sql.Connection con =DatabaseConnection.connect();
            amount+=Double.parseDouble(getAmountPayble.getText());//get pay amount

            if(due>amount){due-=amount;getDueAmount+=due;tem+=amount;ccc.setText(String.valueOf(tem));}//amount=0;
            else{amount-=due;getDueAmount=0;}
            paymentValue=Double.valueOf(monthPay.get(pay));//get monthly actually payment
            Loan_Terms_cal.setText(String.valueOf(loanTerms-1));//set terms            

            if(amount>=paymentValue){//check amount value more than or equal monthly actually payment
                if(amount>(totalDefaultMonthlyPayment-getTotal)){checkFinalValue();
                }else{blance=amount-paymentValue;
                    if(due>=1){
                        if(blance>due){blance-=due;}
                        else{due-=blance;blance=0;}        
                    }
                    if(blance==0){paymentStore.add(String.valueOf(amount));getTotal+=paymentValue;}//amount
                    else{
                        if(keyWord.equals("settle")){double R=totalDefaultMonthlyPayment-getTotal;
                            getTotal+=amount;paymentStore.add(String.valueOf(amount));amount=0;
                        }else{loadFuturePay+=blance;paymentStore.add(String.valueOf(amount));
                            getTotal+=paymentValue;}//amount
                    }
                }
            }else{
                if(keyWord.equals("settle")){double R=totalDefaultMonthlyPayment-getTotal;getTotal+=amount;
                    paymentStore.add(String.valueOf(amount));amount=0;
                }else{blance=paymentValue-amount;getDueAmount+=blance;paymentStore.add(String.valueOf(amount));getTotal+=paymentValue;}}
            for(String xc:paymentStore){//set values in payment,(storing database)
                payment+=xc+",";
            }
            
            String sql="UPDATE personal_loan SET loanTerms='"+Integer.parseInt(Loan_Terms_cal.getText())+"',payment='"+payment+"',dueAmount='"+getDueAmount+"',futurePayment='"+loadFuturePay+"',TotalAmount='"+getTotal+"' WHERE CustomerIdNumber='"+getId+"' ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();     
            //clear all data in tempory uses
            for(int v=0;v<paymentStore.size();v++){paymentStore.remove(v);}             

            con.close();
        }catch(Exception z){System.out.println(z);}
        SharesUpdateProfit();
        getTotal=0;totalDefaultMonthlyPayment=0;amount=0;
                
    }//update
    public void loadName(String getId,int type){//0 per 1 buss
        java.sql.Connection conn =DatabaseConnection.connect();
        try{
        if(type==0){
            String sql = "SELECT CustomerFullName FROM c_details WHERE CustomerIdNumber='"+getId+"' ";
                PreparedStatement pst = conn.prepareStatement(sql);           
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    CustomerName=rs.getString("CustomerFullName");
                }
            }else if(type==1){
            String sql = "SELECT BusinessName FROM c_business WHERE BusinessRegisteredNo='"+getId+"' ";
                PreparedStatement pst = conn.prepareStatement(sql);           
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    CustomerName=rs.getString("BusinessName");
                }

            }
        conn.close(); 
    }catch(Exception x){
        System.err.println(x.getMessage());
    }
    }
    public void searchPastFuturePayment(String getId){
        try{
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql="SELECT * FROM  personal_loan_complete WHERE ='"+getId+"'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                double getVal=rs.getDouble("futurePayment");
                if(getVal>0){
                    futePay=getVal;
                }
            }
        }catch(Exception c){
            System.out.println(c);
        } 
    }
    public void SetActivation(String getId){
        Date d=new Date();
        SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date= getFormat.format(d);
        searchPastFuturePayment(getId);
        try{
            java.sql.Connection con =DatabaseConnection.connect();
            String sql="UPDATE personal_loan SET loanIshuDate='"+date+"' , loanStatus='Active' ,futurePayment='"+futePay+"' WHERE CustomerIdNumber='"+getId+"' ";//  loanIshuDate='"+date+"'
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute(); 
            
            JOptionPane.showMessageDialog(null,"Activation Success");

        }catch(Exception c){
            System.out.println(c);
        }            
    }
    public void SetDeativation(String getId){
        try{
            java.sql.Connection con =DatabaseConnection.connect();
            String sql="UPDATE personal_loan SET loanStatus='Dieactivate' WHERE CustomerIdNumber='"+getId+"' ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute(); 
            
            JOptionPane.showMessageDialog(null,"Dieactivate Success");

        }catch(Exception c){
            System.out.println(c);
        }            
    }
    public void checkFinalValue(){
        //checking the total payment and your sum of payments value less than the total payment
        //is it tru mean yo want pay some payment satlle your loan amount
        if(totalDefaultMonthlyPayment>getTotal){double du=Double.valueOf(dueAmount);
            if(amount>(totalDefaultMonthlyPayment-getTotal-du)){System.out.println("hbbh");
                if(JOptionPane.showConfirmDialog(null,"If you want settle loan?\nplease pay the only Rs."+(totalDefaultMonthlyPayment-getTotal-du)+"only.")==0){
                    getAmountPayble.setText(String.valueOf(totalDefaultMonthlyPayment-getTotal-du));
                }
            }
        }
    }
    public void SharesUpdateProfit(){
        Double pay=Double.parseDouble(getAmountPayble.getText());
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sqlOne="SELECT * FROM micro_shares WHERE shares_call_id='share' ";
            PreparedStatement pst = conn.prepareStatement(sqlOne);
            ResultSet rs = pst.executeQuery();
            double shares=0;
            while(rs.next()){
                shares=rs.getDouble("total_shares");
            }double NewShare=shares+pay;
            String sql = "UPDATE micro_shares SET total_shares='"+NewShare+"' WHERE share_id = 1 "; 
            PreparedStatement pst2 = conn.prepareStatement(sql);
            pst2.execute();  
            conn.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void loanSettle(String getId){
        try {
            loadLoanData(getId,0);
            java.sql.Connection conn =DatabaseConnection.connect();
            
            String quary="INSERT INTO personal_loan_complete(loanIshuDate,loanName,loanType,loanInterestRate,loanTerms,loanAmount,monthlyPayment,profit,payment,dueAmount,loanStatus,CustomerIdOrBusinessReg,EmployId,loanCategory,loanNumber) VALUES ('"+loanIshuDate+"','"+loanName+"','"+loanType+"','"+rate+"','"+loanTerms+"','"+loanAmount+"','"+getDefaultPayment+"','"+profit+"','"+payment+"','"+dueAmount+"','Settle','"+CustomerId+"','"+empId+"','Personal','"+loanNumber+"')";
            PreparedStatement pre =conn.prepareStatement(quary);
            pre.execute();
            
            String sql="DELETE FROM `personal_loan` WHERE CustomerIdNumber='"+getId+"' ";
            PreparedStatement pre2 =conn.prepareStatement(sql);
            pre2.execute();    
            
            String sql3="DELETE FROM `c_guarantor_details` WHERE CustomerIdNumber='"+getId+"' ";
            PreparedStatement pre3 =conn.prepareStatement(sql3);
            pre3.execute();    
            
            
            
            conn.close();
        } catch (Exception e) {
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        getIdOrReg = new javax.swing.JTextField();
        getAmountPayble = new javax.swing.JTextField();
        payBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        loadBtn = new javax.swing.JButton();
        lblPaymentDisplay = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        loanTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        Loan_Terms_cal = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Loan_Total = new javax.swing.JLabel();
        totalPayment = new javax.swing.JLabel();
        Due_Amount = new javax.swing.JLabel();
        future_payment = new javax.swing.JLabel();
        totalPaybleAmount = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cus_name = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Loan_Terms = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        loan_Name = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Loan_Type = new javax.swing.JLabel();
        loan_number = new javax.swing.JLabel();
        Loan_Ishu_Date = new javax.swing.JLabel();
        loan_Amount = new javax.swing.JLabel();
        Loan_satus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cus_nic = new javax.swing.JLabel();
        futuePayBtn = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblPaymentDisplay2 = new javax.swing.JLabel();
        loanClose = new javax.swing.JButton();
        activation = new javax.swing.JButton();
        deactivation = new javax.swing.JButton();
        ccc = new javax.swing.JTextField();
        pl = new javax.swing.JRadioButton();
        bl = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(244, 244, 244));
        setMinimumSize(new java.awt.Dimension(925, 620));
        setPreferredSize(new java.awt.Dimension(925, 620));

        jScrollPane1.setBorder(null);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMinimumSize(new java.awt.Dimension(910, 500));
        jPanel10.setPreferredSize(new java.awt.Dimension(910, 500));

        jLabel73.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel73.setText("Loan Payment Option");

        jLabel3.setText("Loan Amount");

        jLabel42.setText("Loan Number");

        getIdOrReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getIdOrRegActionPerformed(evt);
            }
        });
        getIdOrReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                getIdOrRegKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                getIdOrRegKeyReleased(evt);
            }
        });

        getAmountPayble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getAmountPaybleActionPerformed(evt);
            }
        });

        payBtn.setText("Pay");
        payBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBtnActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        loadBtn.setText("Load Data");
        loadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBtnActionPerformed(evt);
            }
        });

        lblPaymentDisplay.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblPaymentDisplay.setText("Null");
        lblPaymentDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPaymentDisplayMouseClicked(evt);
            }
        });

        loanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Terms", "Profit", "Payments", "Payment History"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(loanTable);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Loan Terms :");

        Loan_Terms_cal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Loan_Terms_cal.setText("Null");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Total Amount :");

        Loan_Total.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Loan_Total.setText("Null");

        totalPayment.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        totalPayment.setText("Null");

        Due_Amount.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Due_Amount.setText("Null");

        future_payment.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        future_payment.setText("Null");

        totalPaybleAmount.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        totalPaybleAmount.setText("Null");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Future Payment:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Due Amount :");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Loan Ishu Date :");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Loan Number :");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Loan Status :");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Customer Name :");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Customer Nic No :");

        cus_name.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cus_name.setText("Null");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Loan Amount :");

        Loan_Terms.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Loan_Terms.setText("Null");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Loan Name :");

        loan_Name.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        loan_Name.setText("Null");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Loan Type :");

        Loan_Type.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Loan_Type.setText("Null");

        loan_number.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        loan_number.setText("Null");

        Loan_Ishu_Date.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Loan_Ishu_Date.setText("Null");

        loan_Amount.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        loan_Amount.setText("Null");

        Loan_satus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Loan_satus.setText("Null");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Loan Terms :");

        cus_nic.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cus_nic.setText("Null");

        futuePayBtn.setText("Pay Future Balance");
        futuePayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                futuePayBtnActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("Total :");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setText("Total Payment:");

        lblPaymentDisplay2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblPaymentDisplay2.setText("Null");
        lblPaymentDisplay2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPaymentDisplay2MouseClicked(evt);
            }
        });

        loanClose.setText("Loan Close");
        loanClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loanCloseActionPerformed(evt);
            }
        });

        activation.setText("Activation");
        activation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activationActionPerformed(evt);
            }
        });

        deactivation.setText("Deactivation");

        ccc.setEditable(false);

        pl.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(pl);
        pl.setSelected(true);
        pl.setText("PL");

        bl.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(bl);
        bl.setText("BL");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(10, 10, 10)
                                        .addComponent(getAmountPayble, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblPaymentDisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(loadBtn)
                                        .addGap(47, 47, 47)
                                        .addComponent(payBtn))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(cus_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel15)
                                        .addGap(21, 21, 21)
                                        .addComponent(cus_name, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel2)
                                        .addGap(37, 37, 37)
                                        .addComponent(loan_number, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel4)
                                        .addGap(40, 40, 40)
                                        .addComponent(loan_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel1)
                                        .addGap(46, 46, 46)
                                        .addComponent(Loan_Terms, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel6)
                                        .addGap(48, 48, 48)
                                        .addComponent(loan_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel5)
                                        .addGap(56, 56, 56)
                                        .addComponent(Loan_Type, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel8)
                                        .addGap(29, 29, 29)
                                        .addComponent(Loan_Ishu_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(deactivation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(activation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(181, 181, 181)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(futuePayBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(loanClose, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(47, 47, 47)
                                                .addComponent(Loan_satus, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(pl)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bl))
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(getIdOrReg, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(lblPaymentDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7)))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ccc, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel73))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(437, 437, 437)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(32, 32, 32)
                                .addComponent(Due_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(future_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(32, 32, 32)
                                .addComponent(Loan_Terms_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(65, 65, 65)
                                .addComponent(Loan_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(19, 19, 19)
                                .addComponent(totalPaybleAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(16, 16, 16)
                                .addComponent(totalPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(489, 489, 489))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jSeparator7)
                .addGap(415, 415, 415))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel73)
                .addGap(6, 6, 6)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pl)
                            .addComponent(bl))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getIdOrReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(lblPaymentDisplay))))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getAmountPayble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(lblPaymentDisplay2))))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loadBtn)
                            .addComponent(payBtn))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(cus_nic))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(cus_name))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(loan_number))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(loan_Amount))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(Loan_Terms))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(loan_Name))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(Loan_Type))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(Loan_Ishu_Date))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(Loan_satus)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7))
                    .addComponent(ccc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(Loan_Terms_cal)
                    .addComponent(jLabel22)
                    .addComponent(Loan_Total))
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(totalPayment)
                    .addComponent(jLabel9)
                    .addComponent(Due_Amount))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(future_payment)
                    .addComponent(jLabel21)
                    .addComponent(totalPaybleAmount))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(activation)
                    .addComponent(loanClose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deactivation)
                    .addComponent(futuePayBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void getIdOrRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getIdOrRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_getIdOrRegActionPerformed

    private void getAmountPaybleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getAmountPaybleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_getAmountPaybleActionPerformed

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        if(!getAmountPayble.getText().isEmpty()){
            setPayment(getIdOrReg.getText());
            clearAllTableData();
            getPersonLoanDetails(getIdOrReg.getText());
        }

    }//GEN-LAST:event_payBtnActionPerformed

    private void getIdOrRegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_getIdOrRegKeyReleased

    }//GEN-LAST:event_getIdOrRegKeyReleased

    private void loadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBtnActionPerformed
        clearAllTableData();
        if(pl.isSelected()){
            getPersonLoanDetails(getIdOrReg.getText());
        }else if(bl.isSelected()){
            getBusinessLoanDetails(getIdOrReg.getText());
        }
    }//GEN-LAST:event_loadBtnActionPerformed

    private void futuePayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_futuePayBtnActionPerformed
        double du=Double.valueOf(dueAmount);
        if(loadFuturePay>0){
            if(a==1 || a==2){a=JOptionPane.showConfirmDialog(null,"Do you want pay Loan using customer saving ?");}   
            if(a==0){
                if(monthPay.get(pay)+du>loadFuturePay){
                    getAmountPayble.setText(String.valueOf((monthPay.get(pay)+du)-loadFuturePay));
                    lblPaymentDisplay2.setText(String.valueOf((monthPay.get(pay)+du)-Double.valueOf(loadFuturePay)));
                    amount+=loadFuturePay; 
                    loadFuturePay=0;
                }else{
                    getAmountPayble.setText(String.valueOf(monthPay.get(pay)+du));
                    loadFuturePay=loadFuturePay-(monthPay.get(pay)+du);                        
                    clearAllTableData();
                    setPayment(getIdOrReg.getText());
                    getPersonLoanDetails(getIdOrReg.getText());                        
                }

            }
        }
    }//GEN-LAST:event_futuePayBtnActionPerformed

    private void loanCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loanCloseActionPerformed
        double du=Double.valueOf(dueAmount);
        
        if(JOptionPane.showConfirmDialog(null,"If you want settle loan?\nplease pay the only Rs."+(totalDefaultMonthlyPayment-getTotal)+"only.")==0){
            if(JOptionPane.showConfirmDialog(null,"Are You Sure?","Loan Settlement",JOptionPane.YES_NO_OPTION)==0){
                getAmountPayble.setText(String.valueOf(totalDefaultMonthlyPayment-getTotal-du));
                keyWord="settle";
                setPayment(getIdOrReg.getText());
                clearAllTableData();
                getPersonLoanDetails(getIdOrReg.getText());  
                loanSettle(getIdOrReg.getText());
                JOptionPane.showMessageDialog(null,"Loan Setle Succes fully..");
            }
            
        }
    }//GEN-LAST:event_loanCloseActionPerformed

    private void activationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activationActionPerformed
        SetActivation(getIdOrReg.getText());
        clearAllTableData();
        getPersonLoanDetails(getIdOrReg.getText());     
        
    }//GEN-LAST:event_activationActionPerformed

    private void lblPaymentDisplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaymentDisplayMouseClicked
       getAmountPayble.setText(lblPaymentDisplay.getText());
    }//GEN-LAST:event_lblPaymentDisplayMouseClicked

    private void lblPaymentDisplay2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaymentDisplay2MouseClicked
        getAmountPayble.setText(lblPaymentDisplay2.getText());
    }//GEN-LAST:event_lblPaymentDisplay2MouseClicked

    private void getIdOrRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_getIdOrRegKeyPressed
        
    }//GEN-LAST:event_getIdOrRegKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Due_Amount;
    private javax.swing.JLabel Loan_Ishu_Date;
    private javax.swing.JLabel Loan_Terms;
    private javax.swing.JLabel Loan_Terms_cal;
    private javax.swing.JLabel Loan_Total;
    private javax.swing.JLabel Loan_Type;
    private javax.swing.JLabel Loan_satus;
    private javax.swing.JButton activation;
    private javax.swing.JRadioButton bl;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField ccc;
    private javax.swing.JLabel cus_name;
    private javax.swing.JLabel cus_nic;
    private javax.swing.JButton deactivation;
    private javax.swing.JButton futuePayBtn;
    private javax.swing.JLabel future_payment;
    private javax.swing.JTextField getAmountPayble;
    private javax.swing.JTextField getIdOrReg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblPaymentDisplay;
    private javax.swing.JLabel lblPaymentDisplay2;
    private javax.swing.JButton loadBtn;
    private javax.swing.JButton loanClose;
    private javax.swing.JTable loanTable;
    private javax.swing.JLabel loan_Amount;
    private javax.swing.JLabel loan_Name;
    private javax.swing.JLabel loan_number;
    private javax.swing.JButton payBtn;
    private javax.swing.JRadioButton pl;
    private javax.swing.JLabel totalPaybleAmount;
    private javax.swing.JLabel totalPayment;
    // End of variables declaration//GEN-END:variables
}
