package LoanForms;

import AdminForm.AdminLoanDetails;
import classPack.ValidityCheck;
import classPack.AgeChecking;
import classPack.DatabaseConnection;
import static classPack.DatabaseConnection.EMP;
import classPack.LoanCalculation;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import scrollbar.ScrollBarCustom;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import micro.Employee;
public class LoanGet extends javax.swing.JPanel {


    public double getBsum() {
        return bsum;
    }
    public int getBmobile() {
        return bmobile;
    }
    public int getbFix() {
        return bFix;
    }
    public String getbName() {
        return bName;
    }
    public String getBreg() {
        return breg;
    }
    public String getBnature() {
        return bnature;
    }
    public String getbAddress() {
        return bAddress;
    }
    public String getbPost() {
        return bPost;
    }
    public String getbEmail() {
        return bEmail;
    }
    public String getbBusienssLocation() {
        return bBusienssLocation;
    }
    public String getbDuration() {
        return bDuration;
    }
    public String getbLocation() {
        return bLocation;
    }
    public String getbExpeince() {
        return bExpeince;
    }
    public String getbAvailability() {
        return bAvailability;
    }
    public String getC_ID() {
        return c_ID;
    }
    public String getC_Status() {
        return c_Status;
    }
    public String getC_Name() {
        return c_Name;
    }
    public String getC_Add() {
        return c_Add;
    }
    public String getC_email() {
        return c_email;
    }
    public String getC_Gen() {
        return c_Gen;
    }
    public String getC_dob() {
        return c_dob;
    }
    public String getC_Relig() {
        return c_Relig;
    }
    public String getC_Nati() {
        return c_Nati;
    }
    public String getC_Gs() {
        return c_Gs;
    }
    public String getC_Pos() {
        return c_Pos;
    }
    public String getC_Dur() {
        return c_Dur;
    }
    public String getC_Mar() {
        return c_Mar;
    }
    public int getC_HPhone() {
        return c_HPhone;
    }
    public int getC_LPhone() {
        return c_LPhone;
    }

    private int selectedIndex,a,b,c,d;
    private double SumofInsured,LoanAmount;
    private String c_ID,c_Status,c_Name,c_Add,c_email,c_Gen,c_dob,c_Relig,c_Nati,c_Gs,c_Pos,c_Dur,c_Mar;
    private int c_HPhone,c_LPhone;
    private String g_Name,g_Nic,g_Add,g_email;
    private int g_HandPhone,g_LandPhone;
    private String t_loanName,t_loanType,t_state,t_formNo;
    private double t_loanAmount,t_loanRate;
    private int t_terms;
    private int deleat;
    private String bName,breg,bnature,bAddress,bPost,bEmail,bBusienssLocation,bDuration,bLocation,bExpeince,bAvailability;
    private String b_LoanName,b_LoanType,b_reg,b_LoanStatus,b_formNo;
    private Double b_LoanAmount,b_interestRate;
    private int b_terms;
    
    private int bmobile,bFix;
    private double bsum;
    private String getFNumber;
    private String approllNic="n",approllBReg="n";
    private String getLoanName;
    

    

    public void deleatePersonal(){
        String id="";
        
        if(c_Nic.getText().length()>1){
            if(c_Nic.getText().length()==9){id="19"+c_Nic.getText();
            }else{id=c_Nic.getText();}
        }else{
            id=FinalSearch.getText();
        }
        
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "DELETE FROM c_personal_loan_tempory WHERE `CustomerIdNumber`='"+id+"' ";//WHERE Rating_Id_name='"+getData+"' 
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            deleat=1;
            
            String sql2 = "DELETE FROM c_details WHERE `CustomerIdNumber`='"+id+"' ";
            PreparedStatement pst2 = conn.prepareStatement(sql2);
            pst2.execute();
            
            
            String sql3 = "DELETE FROM c_guarantor_details WHERE c_guarantor_details.CustomerIdNumber='"+id+"' ";
            PreparedStatement pst3 = conn.prepareStatement(sql3);
            pst3.execute();
            
            
        JOptionPane.showMessageDialog(null,"Data Deleate sucessfull..");
        conn.close();            
        }catch(Exception v){
            System.err.println(v);
        }
    }
    public void deleateBusiness(){
        String id="";
        if(b_regNo.getText().length()>1){
            id=b_regNo.getText();
        }else{
            id=FinalSearch.getText();;
        }
        
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "DELETE FROM c_business_loan_tempory WHERE `BusinessRegisteredNo`='"+id+"' ";//WHERE Rating_Id_name='"+getData+"' 
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            deleat=2;
        JOptionPane.showMessageDialog(null,"Deleate sucessfull..");              
        conn.close();            
        }catch(Exception v){
            System.err.println(v);
        }
    }
    public LoanGet() {
        initComponents();
                 
        loanReciever.setSelectedIndex(0);
        
        ScrollBarCustom sc = new ScrollBarCustom();
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        sc.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane2.setHorizontalScrollBar(sc);

        ScrollBarCustom sc1 = new ScrollBarCustom();
        jScrollPane3.setVerticalScrollBar(new ScrollBarCustom());
        sc1.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane3.setHorizontalScrollBar(sc1);


        ScrollBarCustom sc3 = new ScrollBarCustom();
        jScrollPane7.setVerticalScrollBar(new ScrollBarCustom());
        sc3.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane7.setHorizontalScrollBar(sc3);
        
        ScrollBarCustom sc4=new ScrollBarCustom();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        sc4.setOrientation(JScrollBar.HORIZONTAL);
        jScrollPane1.setHorizontalScrollBar(sc4);
        
        
    }
    public void approall(){
        int type=loanReciever.getSelectedIndex();
        String formNumber;
        String setValue="Request";
        if(getFormNumber.getText().length()>1){
            try {
                java.sql.Connection conn =DatabaseConnection.connect();
                if(type==0){
                   
                    String newNic;
                    if(c_Nic.getText().length()==9){
                        newNic="19"+c_Nic.getText();
                    }else{
                        newNic=c_Nic.getText();
                    }  
                    String sql="UPDATE c_personal_loan_tempory SET loanStatus='"+setValue+"' WHERE CustomerIdNumber='"+newNic+"' ";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();      

                    String sql2 = "SELECT loanStatus FROM c_personal_loan_tempory";
                    PreparedStatement pst2 = conn.prepareStatement(sql2);
                    ResultSet rs = pst2.executeQuery();   
                    while (rs.next()) {
                        String vb=rs.getString("loanStatus");
                        lblStatus.setText(vb);
                    }
                }else{//load and save bussiness
                    
                    String reg=b_regNo.getText();
                   
                    
                    String sql="UPDATE c_business_loan_tempory SET loanStatus='"+setValue+"' WHERE BusinessRegisteredNo='"+reg+"' ";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();      

                    String sql2 = "SELECT loanStatus FROM c_business_loan_tempory";
                    PreparedStatement pst2 = conn.prepareStatement(sql2);
                    ResultSet rs = pst2.executeQuery();   
                    while (rs.next()) {
                        String vb=rs.getString("loanStatus");
                        lblStatus.setText(vb);
                    }
                }
                if(lblStatus.getText()=="Request"){
                    lblStatus.setForeground(Color.BLUE);
                }else if(lblStatus.getText()=="Pending"){
                    lblStatus.setForeground(Color.ORANGE);
                }else if(lblStatus.getText()=="Active"){
                    lblStatus.setForeground(Color.GREEN);
                }else{
                    lblStatus.setForeground(Color.RED);
                }

                JOptionPane.showMessageDialog(null,"Done");
                conn.close();

            } catch (Exception e) {
                System.out.println(e);
            }//catch(DuplicateEntityException)
        }else{
            JOptionPane.showMessageDialog(null,"Please enter Form Number");
            
        }
    }
    public void approallUpdate(String forNumber){
        int type=loanReciever.getSelectedIndex();
        String newNic="";
        String reg="";
        if(!getFNumber.isEmpty()){
            try {
                java.sql.Connection conn =DatabaseConnection.connect();
                if(type==0){
                    
                    if(c_Nic.getText().length()>=1){
                        if(c_Nic.getText().length()==9){
                            newNic="19"+c_Nic.getText();
                        }else{
                            newNic=c_Nic.getText();
                        }  
                    }else if(FinalSearch.getText().length()>=1){
                        if(FinalSearch.getText().length()==9){
                            newNic="19"+FinalSearch.getText();
                        }else{
                            newNic=FinalSearch.getText();
                        }                          
                    }else{
                        JOptionPane.showMessageDialog(null,"Error Nic");
                    }
                    String sql="UPDATE c_personal_loan_tempory SET loanStatus='Request',comment='' WHERE CustomerIdNumber='"+newNic+"' ";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();      

                    String sql2 = "SELECT loanStatus FROM c_personal_loan_tempory";
                    PreparedStatement pst2 = conn.prepareStatement(sql2);
                    ResultSet rs = pst2.executeQuery();   
                    while (rs.next()) {
                        String vb=rs.getString("loanStatus");
                        lblStatus.setText(vb);
                    }
                }else{//load and save bussiness
                    
                    if(b_regNo.getText().length()>=1){
                        reg=b_regNo.getText();
                    }else{
                        reg=FinalSearch.getText();
                    }
                    String sql="UPDATE c_business_loan_tempory SET loanStatus='Request',comment='' WHERE BusinessRegisteredNo='"+reg+"' ";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();      

                    String sql2 = "SELECT loanStatus FROM c_business_loan_tempory";
                    PreparedStatement pst2 = conn.prepareStatement(sql2);
                    ResultSet rs = pst2.executeQuery();   
                    while (rs.next()) {
                        String vb=rs.getString("loanStatus");
                        lblStatus.setText(vb);
                    }
                }
                if(lblStatus.getText()=="Request"){
                    lblStatus.setForeground(Color.BLUE);
                }else if(lblStatus.getText()=="Pending"){
                    lblStatus.setForeground(Color.ORANGE);
                }else if(lblStatus.getText()=="Active"){
                    lblStatus.setForeground(Color.GREEN);
                }else{
                    lblStatus.setForeground(Color.RED);
                }

                JOptionPane.showMessageDialog(null,"Done");
                conn.close();

            } catch (Exception e) {
                System.out.println(e);
            }//catch(DuplicateEntityException)
        }else{
            JOptionPane.showMessageDialog(null,"Please enter Form Number");
            
        }
    }    
    public void finalReportLoad(){
        if(FinalSearch.getText().isEmpty()){
            if(loanReciever.getSelectedIndex()==0){//personal
                String nic="";
                if(c_Nic.getText().length()==9){
                    nic="19"+c_Nic.getText();
                }else if(c_Nic.getText().length()==11){
                    nic=c_Nic.getText();
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Nic");
                }
                approllNic=nic;
                finalReportLoad(nic);
            }else{//business
                approllBReg=b_regNo.getText();
                finalReportLoad(b_regNo.getText());
            }
            
        }else{
            //finalReportLoad(FinalSearch.getText());
            ValidityCheck k=new ValidityCheck();
            boolean result;int count=0;
            int length =FinalSearch.getText().length();
            if(length ==9 || length==11){
                while(length>0){
                    result=k.onlyTypeDigits(FinalSearch.getText().charAt(length));
                    if(!result){//fals = number
                        count++;
                    }
                    length--;
                }
            }
            if(length==count){
            //if(loanReciever.getSelectedIndex()==0){//personal
                String nic="";
                if(c_Nic.getText().length()==9){
                    nic="19"+c_Nic.getText();
                }else if(c_Nic.getText().length()==11){
                    nic=c_Nic.getText();
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Nic");
                }
                approllNic=nic;
                finalReportLoad(nic);
            }else{//business
                approllBReg=b_regNo.getText();
                finalReportLoad(b_regNo.getText());
            }       
        }
    }
    public void callValid(){
        ValidityCheck valid = new ValidityCheck();
        if (valid.checkMobilePhoneNumber(c_Mobile_Number.getText()) == false) {
            c_Mobile_Number.setForeground(Color.red);
        } else {
            c_Mobile_Number.setForeground(Color.GREEN);
        }
        if (valid.checkEmail(c_Email.getText()) == false) {
            c_Email.setForeground(Color.red);
        } else {
            c_Email.setForeground(Color.GREEN);
        }
        if (valid.checkLandPhoneNumber(c_Land_Phone_Number.getText()) == false) {
            c_Land_Phone_Number.setForeground(Color.red);
        } else {
            c_Land_Phone_Number.setForeground(Color.GREEN);
        }
        if (valid.checkNic(c_Nic.getText()) == false) {
            c_Nic.setForeground(Color.red);
        } else {
            c_Nic.setForeground(Color.GREEN);
        }
    }
    public void update(String getId,String getUpName,int v){
        DefaultTableModel model = (DefaultTableModel) tblGuarantorsDetails.getModel();
        String rate="";
        String id=getId;
        int a=v;
        String getName=getUpName; 
        if(tblGuarantorsDetails.getRowCount()>=1){
            String Gname,Nic,GAddress,Gemail,newNic;
            int Hphone,Lphone;
            //int id;
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                //java.sql.Connection conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro","root","");
                java.sql.Connection conn =DatabaseConnection.connect();    
                    Gname=(String) model.getValueAt(a, 0);
                    Nic=(String) model.getValueAt(a, 1);
                    Hphone=(int) model.getValueAt(a, 2);
                    Lphone=(int) model.getValueAt(a, 3);
                    GAddress=(String) model.getValueAt(a, 4);
                    Gemail=(String) model.getValueAt(a, 5);

                    String sql = "UPDATE c_guarantor_details SET Guarantor_Name='"+Gname+"',Guarantor_Nic='"+Nic+"',Guarantor_Phone_Number='"+Hphone+"',Guarantor_Land_Phone='"+Lphone+"',Guarantor_Home_Address='"+GAddress+"',Guarantor_Email='"+Gemail+"' WHERE Guarantor_Nic='"+id+"' "; 

                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();

                JOptionPane.showMessageDialog(null,"Sucesss...");
                model.setRowCount(0);
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }    
    public void tableRefresh(String getRateName,int tableOne){
        DefaultTableModel model = (DefaultTableModel) tblGuarantorsDetails.getModel();
        
        clearAllTableData();
        String rate_name=getRateName;
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM c_guarantor_details WHERE CustomerIdNumber='"+rate_name+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(tableOne==0){
                while (rs.next()) {
                    Object w[]={rs.getString("Guarantor_Name"),rs.getString("Guarantor_Nic"),rs.getInt("Guarantor_Phone_Number"),rs.getInt("Guarantor_Land_Phone"),rs.getString("Guarantor_Home_Address"),rs.getString("Guarantor_Email")};
                    model.addRow(w);
                }
            }else{
                DefaultTableModel model2 = (DefaultTableModel) tblFinalReporG.getModel();
                while (rs.next()) {
                    Object w[]={rs.getString("Guarantor_Name"),rs.getString("Guarantor_Nic"),rs.getInt("Guarantor_Phone_Number"),rs.getInt("Guarantor_Land_Phone"),rs.getString("Guarantor_Home_Address"),rs.getString("Guarantor_Email")};
                    model2.addRow(w);
                }                
            }

            //ratingName.addItem(getRateName);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void secondLoadDataComboBox(){
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            //String sql = "SELECT Rating_Id_name FROM micro_rating_keys GROUP BY Rating_Id_name HAVING COUNT(*) > 1";
            String sql = "SELECT Rating_Id_name FROM micro_rating GROUP BY Rating_Id_name HAVING COUNT(*) > 1 & COUNT(*) <= 1";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String Rating_Id_name = rs.getString("Rating_Id_name");                               
                //ratingName.addItem(Rating_Id_name);
               // ratingName1.addItem(Rating_Id_name);
            }
            
            String sql2 = "SELECT * FROM micro_rating_keys";
            PreparedStatement pst1 = conn.prepareStatement(sql2);
            
            ResultSet rs1 = pst1.executeQuery();   
            while (rs1.next()) {
                String vb=rs1.getString("Rating_Id_name");
                //lblDisplay.setText(vb);
                //lblDisplayDefaultRating.setText(vb);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void LoanCalLoad(){
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM micro_rating,micro_rating_keys WHERE micro_rating_keys.Rating_Id_name = micro_rating.Rating_Id_name ";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                DefaultTableModel model = (DefaultTableModel) tblGuarantorsDetails.getModel();
                Object w[]={rs.getDouble("Day_By_Day"),rs.getDouble("D_rate"),rs.getDouble("Week_1"),rs.getDouble("W_1_rate"),rs.getDouble("Week_2"),rs.getDouble("W_2_rate"),rs.getDouble("Month"),rs.getDouble("Month_rate"),rs.getDouble("Year"),rs.getDouble("Year_rate"),rs.getString("Rating_Id_name")};
                model.addRow(w);

            }
            

            //ratingName.addItem(getRateName);
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void tableDataUpdate(String getUpdate){       
        try{
            java.sql.Connection conn =DatabaseConnection.connect();            
            String sql= "UPDATE `c_guarantor_details` SET `Guarantor_Name`='"+txtGirantorName.getText()+"',`Guarantor_Phone_Number`='"+txtGirantorHPhone.getText()+"',`Guarantor_Land_Phone`='"+txtGirantorLPhone.getText()+"',`Guarantor_Home_Address`='"+txtGirantorHomeAddress.getText()+"',`Guarantor_Email`='"+txtGirantorEmail.getText()+"',`CustomerIdNumber`='"+c_Nic.getText()+"' WHERE Guarantor_Nic='"+getUpdate+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            conn.close();
        }catch(Exception z){
            JOptionPane.showMessageDialog(null,"Error \n\n"+z.getMessage());
        }

    }
    public void tableDataInsert(String name){
        DefaultTableModel model = (DefaultTableModel) tblGuarantorsDetails.getModel();
        String getName=name;
        String rate="";
        if(tblGuarantorsDetails.getRowCount()>=1){
            
            Double day,d_rate,week_1,week_1_rate,week_2,week_2_rate,month,month_rate,year,year_rate;
            try {
                java.sql.Connection conn =DatabaseConnection.connect();
                for(int a=0;a<model.getRowCount();a++) {

                    day = (Double) model.getValueAt(a, 1);
                    d_rate = (Double) model.getValueAt(a, 2);
                    week_1 = (Double) model.getValueAt(a, 3);
                    week_1_rate = (Double) model.getValueAt(a, 4);
                    week_2 = (Double) model.getValueAt(a, 5);
                    week_2_rate = (Double) model.getValueAt(a, 6);
                    month = (Double) model.getValueAt(a, 7);
                    month_rate = (Double) model.getValueAt(a, 8);
                    year = (Double) model.getValueAt(a, 9);
                    year_rate = (Double) model.getValueAt(a, 10);

                    String sql = "INSERT INTO micro_rating(Day_By_Day,D_rate,Week_1,W_1_rate,Week_2,W_2_rate,Month,Month_rate,Year,Year_rate,Rating_Id_name) VALUES('"+day+"','"+d_rate+"','"+week_1+"','"+week_1_rate+"','"+week_2+"','"+week_2_rate+"','"+month+"','"+month_rate+"','"+year+"','"+year_rate+"','"+getName+"')";
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
        }

    }
    public void getSharesData(){
        String name="share";
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM micro_shares WHERE shares_call_id='"+name+"' ";//shares_call_id
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String total=String.valueOf(rs.getDouble("total_shares"));
            }
    
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }   
    public void clearAllTableData(){
                
       // ratingName.setSelectedItem("");
      //  btnRefrsh.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) tblGuarantorsDetails.getModel();
        while(true){
            if(tblGuarantorsDetails.getRowCount()==0){
                break;
            }
            model.removeRow(0);
        }
    }
    public void tableClear(){
        DefaultTableModel model = (DefaultTableModel)tblGuarantorsDetails.getModel();
        while(model.getRowCount()>0){
            for(int p=0;p<model.getRowCount();p++){
                model.removeRow(p);
            }
        }
    }
    public void clearCustomerData(){
        cFullName.setText("");comboFname.setSelectedItem("");cAddress.setText("");cYear.setText("");
        comboMonth.setSelectedIndex(0);comboDay.setSelectedIndex(0);cComboReligion.setSelectedIndex(0);  male.isSelected();
        comboNationality.setSelectedIndex(0);c_Mobile_Number.setText("");c_Land_Phone_Number.setText("");
        c_Email.setText("");cGSDiv.setText("");cPotalCode.setText("");cDurYear.setText("");cDurMonth.setText("");
        cMariSingle.isSelected();c_ID="";c_Status="";c_Name="";c_HPhone=0;c_LPhone=0;c_Add="";c_email="";
        c_Gen="";c_dob="";c_Relig="";c_Nati="";c_Gs="";c_Pos="";c_Dur="";c_Mar="";
        
        
    }
    public void clearBusinessData(){
        b_Name.setText("");b_NatureBus.setText("");b_Add1.setText("");b_Add2.setText("");b_PostCode.setText("");
        b_Hphone.setText("");b_Lphone.setText("");b_Email.setText("");b_LocatOwner.setSelectedItem(0); b_DurYear.setText("");
        b_DurMonth.setText("");b_Location.setSelectedItem(0);b_ExpYear.setText("");b_ExpMonth.setText(""); b_AYes.isSelected();
        b_SumInsured.setText("");bName="";breg="";bnature="";bAddress="";bPost="";bEmail="";bBusienssLocation="";bDuration="";bLocation="";
        bExpeince="";bAvailability="";bmobile=0;bFix=0;bsum=0;

    }
    public void clearBasicLoanData(){
        t_loanName="";t_loanType="";t_loanAmount=0;t_loanRate=0;t_terms=0;
    }
    public void customerDataLoad(String getId){//customer detail
        String id=getId;
        if(id.length()==9){
            id="19"+getId;
        }
        clearCustomerData();
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM c_details WHERE CustomerIdNumber='"+id+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                  c_ID =rs.getString("CustomerIdNumber");c_Status =rs.getString("CustomerStatus");
                  c_Name  =rs.getString("CustomerFullName");c_HPhone =rs.getInt("CustomerHandPhone");
                  c_LPhone =rs.getInt("CustomerLandPhone");c_Add =rs.getString("CustomerHomeAddress");
                  c_email =rs.getString("CustomerEmailAddress");c_Gen=rs.getString("CustomerGender");
                  c_dob =rs.getString("CustomerDOB");c_Relig  =rs.getString("CustomerReligion");
                  c_Nati =rs.getString("CustomerNationality");c_Gs  =rs.getString("CustomerGSDivisionNameNo");
                  c_Pos =rs.getString("CustomerPostalCode");c_Dur =rs.getString("CustomerDurationOfStay");
                  c_Mar =rs.getString("CustomerMaritalStatus");
            } 

        cFullName.setText(getC_Name());
        comboFname.setSelectedItem(getC_Status());//.getSelectedItem();
        cAddress.setText(getC_Add());
        
        String dobTemp[]=getC_dob().split("-");
        cYear.setText(dobTemp[0]);
        comboMonth.setSelectedItem(dobTemp[1]);
        comboDay.setSelectedItem(dobTemp[2]);
        
        
        cComboReligion.setSelectedItem(getC_Relig());
        
        if( getC_Gen().equals("Male")){male.isSelected();
        }else{female.isSelected();}
        comboNationality.setSelectedItem(getC_Nati());
        c_Mobile_Number.setText("0"+String.valueOf(getC_HPhone()));
        c_Land_Phone_Number.setText("0"+String.valueOf(getC_LPhone()));
        c_Email.setText(getC_email());
        cGSDiv.setText(getC_Gs());
        cPotalCode.setText(getC_Pos());
        
        String durTemp[]=getC_Dur().split("/");
        cDurYear.setText(durTemp[0]);
        cDurMonth.setText(durTemp[1]);
        
        
        if( getC_Mar().equals("Single")){cMariSingle.isSelected();}
        else if(getC_Mar().equals("Widow")){cMariWidow.isSelected();
        }else if(getC_Mar().equals("Married")){cMariMarried.isSelected();
        }else{cMariDivorce.isSelected();}
  
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void businessDataLoad(String getId){
        clearBusinessData();
        try {
            String sql = "SELECT * FROM `c_business` WHERE BusinessRegisteredNo='"+getId+"' ";
            //DatabaseConnection c=new DatabaseConnection();
            java.sql.Connection conn =DatabaseConnection.connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
         
                  bName=rs.getString("BusinessName");breg=rs.getString("BusinessRegisteredNo");
                  bnature=rs.getString("NatureofBusiness");bAddress=rs.getString("BusinessAddress");
                  bEmail=rs.getString("BusinessEmail");bBusienssLocation=rs.getString("BusinessLocationOwnership");
                  bDuration=rs.getString("DurationAtPresentLocation");bLocation=rs.getString("BusinessLocation");
                  bExpeince=rs.getString("ExperienceInBusiness");bAvailability=rs.getString("AvailabilityofInsurancePolicy");
                  bmobile=rs.getInt("MobilePhoneNumber");bFix=rs.getInt("FixedPhoneNumber");
                  bsum=rs.getDouble("SumofInsured");bPost=rs.getString("BPostalCode");
            } 
            b_Name.setText(getbName());b_NatureBus.setText(getBnature());
            String getAddress[]=getbAddress().split("~~");
            b_Add1.setText(getAddress[0]);
            b_Add2.setText(getAddress[1]);
            b_regNo.setText(getBreg());b_PostCode.setText(getbPost());
            b_Hphone.setText(String.valueOf(getBmobile()));b_Lphone.setText(String.valueOf(getbFix()));
            b_Email.setText(getbEmail());
            b_LocatOwner.setSelectedItem(getbBusienssLocation());
            String Dur[]=getbDuration().split("/");
            b_DurYear.setText(Dur[0]);
            b_DurMonth.setText(Dur[1]);           
            b_Location.setSelectedItem(getbLocation());
            String Exp[]=getbExpeince().split("/");
            b_ExpYear.setText(Exp[0]);
            b_ExpMonth.setText(Exp[1]);
            
            if(getbAvailability().equals("Yes")){
               b_AYes.isSelected();
            }else{
               b_ANo.isSelected() ;
            }
            b_SumInsured.setText(String.valueOf(getBsum()));
            
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error \n\n"+e.getMessage());
        }
    }
    public void customerDataUpdate(String getId,boolean getUpdateOrInesrt){//insert=true update=false
        String newNic=getId;
        if(c_Nic.getText().length()==9){newNic="19"+c_Nic.getText();
        }else{newNic=c_Nic.getText();}

        String fName,cStatus,address,date,religion,gen,nationalaty,email,GS,postal,duration,marrid,hp,lp;
        int hphone,lphone;
        fName=cFullName.getText();
        cStatus=(String)comboFname.getSelectedItem();
        address=cAddress.getText();
        date=cYear.getText()+"-"+comboMonth.getSelectedItem()+"-"+comboDay.getSelectedItem();
        religion=(String) cComboReligion.getSelectedItem().toString();
        if(male.isSelected()){gen="Male";
        }else{gen="Female";}
        nationalaty=(String) comboNationality.getSelectedItem().toString();
        hp=c_Mobile_Number.getText();
        lp=c_Land_Phone_Number.getText();
        email=c_Email.getText();
        GS=cGSDiv.getText();
        postal=cPotalCode.getText();
        duration=cDurYear.getText() +"/"+cDurMonth.getText();
        if(cMariSingle.isSelected()){marrid="Single";
        }else if(cMariWidow.isSelected()){marrid="Widow";
        }else if(cMariMarried.isSelected()){marrid="Married";
        }else{marrid="Divorce";}        
        if(!fName.isEmpty() && !address.isEmpty() && !newNic.isEmpty() && !date.isEmpty() && !religion.isEmpty() && !gen.isEmpty() && !GS.isEmpty() && !postal.isEmpty() && !duration.isEmpty() &&(!hp.isEmpty() || !lp.isEmpty())){
            if(lp.isEmpty()){
                lp="0";
            }else if(hp.isEmpty()){
                hp="0";
            }
            hphone=Integer.valueOf(hp);
            lphone=Integer.valueOf(lp);

            try{
                java.sql.Connection conn =DatabaseConnection.connect();
                if(getUpdateOrInesrt){
                    String quary="INSERT INTO c_details (CustomerIdNumber,CustomerStatus,CustomerFullName,CustomerHandPhone,CustomerLandPhone,CustomerHomeAddress,CustomerEmailAddress,CustomerGender,CustomerDOB,CustomerReligion,CustomerNationality,CustomerGSDivisionNameNo,CustomerPostalCode,CustomerDurationOfStay,CustomerMaritalStatus) VALUES ('"+newNic+"','"+cStatus+"','"+fName+"','"+hphone+"','"+lphone+"','"+address+"','"+email+"','"+gen+"','"+date+"','"+religion+"','"+nationalaty+"','"+GS+"','"+postal+"','"+duration+"','"+marrid+"')";
                    PreparedStatement pre =conn.prepareStatement(quary);
                    pre.execute();
                }else{
                    String sql = "UPDATE c_details SET CustomerStatus='"+cStatus+"',CustomerFullName='"+fName+"',CustomerHandPhone='"+hphone+"',CustomerLandPhone='"+lphone+"',CustomerHomeAddress='"+address+"',CustomerEmailAddress='"+email+"',CustomerGender='"+gen+"',CustomerDOB='"+date+"',CustomerReligion='"+religion+"',CustomerNationality='"+nationalaty+"',CustomerGSDivisionNameNo='"+GS+"',CustomerPostalCode='"+postal+"',CustomerDurationOfStay='"+duration+"',CustomerMaritalStatus='"+marrid+"' WHERE CustomerIdNumber='"+newNic+"' "; 
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();               
                }
                JOptionPane.showMessageDialog(null,"Success !");
                conn.close();

            }catch(Exception c){
                JOptionPane.showMessageDialog(null,"Faild \n\n"+c.getMessage());
            }
        }else{
            if(fName.isEmpty()){
                lblc_name.setForeground(Color.red);
            }else if(address.isEmpty()){
                lblc_add.setForeground(Color.red);
            }else if(newNic.isEmpty()){
                lblc_nic.setForeground(Color.red);
                JOptionPane.showMessageDialog(null,"You can't empty National Id Field");
            }else if(hp.isEmpty() && lp.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please add Mobile Number or Land phone Number");
                if(hp.isEmpty()){
                    lblc_mobi.setForeground(Color.red);
                }else{
                    lblc_mobi.setForeground(Color.black);
                }
                if(lp.isEmpty()){
                    lblc_land.setForeground(Color.red);
                }else{
                    lblc_land.setForeground(Color.black);
                }
            }else if(date.isEmpty()){
                lblc_dob.setForeground(Color.red);
            }else if(gen.isEmpty()){
                lblc_gen.setForeground(Color.red);
            }else if(email.isEmpty()){
                lblc_email.setForeground(Color.red);
            }else if(GS.isEmpty()){
                lblc_gs.setForeground(Color.red);
            }else if(postal.isEmpty()){
                lblc_postal.setForeground(Color.red);
            }else if(duration.isEmpty()){
                lblc_dur.setForeground(Color.red);
            }
        }
    }
    public void businessDataUpdate(String getId,boolean getUpdateOrInesrt){//insert=true update=false
        String BReg=getId;
        String BN,Nature,BAdd,BPost,BLO,DAtPresL,BLocation,ExpInBus,AvaofInsPol,HP,LP,BEmail;
        int hphone,lphone;
        double sum;
        BN=b_Name.getText();
        Nature=b_NatureBus.getText();
        BAdd=b_Add1.getText()+"~~"+b_Add2.getText();
        BPost=b_PostCode.getText();
        BLO=(String) b_LocatOwner.getSelectedItem();
        DAtPresL=b_DurYear.getText()+"/"+b_DurMonth.getText();           
        BLocation=(String) b_Location.getSelectedItem();
        ExpInBus=b_ExpYear.getText()+"/"+b_ExpMonth.getText();
        BEmail=b_Email.getText();
        if(b_AYes.isSelected()){
            AvaofInsPol="Yes";
            sum=Double.parseDouble(b_SumInsured.getText());
        }else{
            AvaofInsPol="No";
            sum=0;
        }
        HP=b_Hphone.getText();
        LP=b_Lphone.getText();
        
        if(BEmail.isEmpty()){
            BEmail=" ";
        }
        

        if (!BN.isEmpty() && !BReg.isEmpty() && !Nature.isEmpty() && !BAdd.isEmpty() && !BPost.isEmpty() && !BLO.isEmpty() && !DAtPresL.isEmpty() && !BLocation.isEmpty() && !ExpInBus.isEmpty() && !AvaofInsPol.isEmpty() &&(!HP.isEmpty() || !LP.isEmpty())) { 
            if(LP.isEmpty()){
                LP="0";
            }else if(HP.isEmpty()){
                HP="0";
            }            
            
            hphone=Integer.valueOf(HP);
            lphone=Integer.valueOf(LP);
            try{
                //Class.forName("com.mysql.jdbc.Driver");
                //java.sql.Connection conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro","root","");
                java.sql.Connection conn =DatabaseConnection.connect();
                if(getUpdateOrInesrt){
                    String quary="INSERT INTO c_business (BusinessName,BusinessRegisteredNo,NatureofBusiness,BusinessAddress,BusinessEmail,BPostalCode,BusinessLocationOwnership,DurationAtPresentLocation,MobilePhoneNumber,FixedPhoneNumber,BusinessLocation,ExperienceInBusiness,AvailabilityofInsurancePolicy,SumofInsured,EmployId) VALUES ('"+BN+"','"+BReg+"','"+Nature+"','"+BAdd+"','"+BEmail+"','"+BPost+"','"+BLO+"','"+DAtPresL+"','"+hphone+"','"+lphone+"','"+BLocation+"','"+ExpInBus+"','"+AvaofInsPol+"','"+sum+"','"+EMP+"')";
                    PreparedStatement pre =conn.prepareStatement(quary);
                    pre.execute();
                }else{//BN,Nature,BAdd,BPost,BLO,DAtPresL,BLocation,ExpInBus,AvaofInsPol,HP,LP,BEmail;
                    String sql = " UPDATE c_business SET BusinessName='"+BN+"',NatureofBusiness='"+Nature+"',BusinessAddress='"+BAdd+"',BusinessEmail='"+BEmail+"',BPostalCode='"+BPost+"',BusinessLocationOwnership='"+BLO+"',DurationAtPresentLocation='"+DAtPresL+"',MobilePhoneNumber='"+HP+"',FixedPhoneNumber='"+LP+"',BusinessLocation='"+BLocation+"',ExperienceInBusiness='"+ExpInBus+"',AvailabilityofInsurancePolicy='"+AvaofInsPol+"',SumofInsured='"+sum+"' ,EmployId='"+EMP+"' WHERE BusinessRegisteredNo='"+BReg+"' "; 
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute(); 
                }
                JOptionPane.showMessageDialog(null,"Success !");
                conn.close();

            }catch(Exception c){
                JOptionPane.showMessageDialog(null,"Error !\n\n"+c.getMessage());
            }        
        }else{
            if(BN.isEmpty()){
                lblBN.setForeground(Color.red);
            }else if(BReg.isEmpty()){
                lblBReg.setForeground(Color.red);
            }else if(Nature.isEmpty()){
                lblNature.setForeground(Color.red);
            }else if(BAdd.isEmpty()){
                lblBAdd.setForeground(Color.red);
            }else if(HP.isEmpty() && LP.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please add Mobile Number or Land phone Number");
                if(HP.isEmpty()){
                    lblmob.setForeground(Color.red);
                }else{
                    lblmob.setForeground(Color.black);
                }
                if(LP.isEmpty()){
                    lblLand.setForeground(Color.red);
                }else{
                    lblLand.setForeground(Color.black);
                }
            }
            
            else if(BPost.isEmpty()){
                lblBPost.setForeground(Color.red);
            }else if(BLO.isEmpty()){
                lblBLO.setForeground(Color.red);
            }else if(DAtPresL.isEmpty()){
                lblDAtPresL.setForeground(Color.red);
            }else if(BLocation.isEmpty()){
                lblBLocation.setForeground(Color.red);
            }else if(ExpInBus.isEmpty()){
                lblExpInBus.setForeground(Color.red);
            }else if(AvaofInsPol.isEmpty()){
                lblAvaofInsPol.setForeground(Color.red);
            }
        }
    }    
    public void finalReportDataGet(String getId){//persona loan datda load
        String id=getId;
        if(sPLoan.isSelected()){
            customerDataLoad(id);
            try{
                java.sql.Connection conn =DatabaseConnection.connect();
                String sql = "SELECT * FROM c_personal_loan_tempory WHERE CustomerIdNumber='"+id+"'";
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    t_loanAmount=rs.getDouble("loanAmount");
                    t_loanName=rs.getString("loanName");
                    t_loanType=rs.getString("loanType");
                    t_terms=rs.getInt("loanTerms");
                    t_loanRate=rs.getDouble("loanInterestRate");
                    t_state=rs.getString("loanStatus");
                    getFNumber=rs.getString("formNo");
                    if(getFNumber.length()>=1){
                        getFormNumber.setText(getFNumber);
                    }
                }

                conn.close();
            }catch(Exception v){
                System.out.println(v);
                JOptionPane.showMessageDialog(null,"Faild \n\n"+v.getMessage());
            }
        }else if(sBLoan.isSelected()){
            businessDataLoad(id);
            try{
                //Class.forName("com.mysql.jdbc.Driver");
               // java.sql.Connection conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro","root","");
                java.sql.Connection conn =DatabaseConnection.connect();
                String sql = "SELECT * FROM c_business_loan_tempory WHERE BusinessRegisteredNo='"+id+"'";
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    t_loanAmount=rs.getDouble("loanAmount");
                    t_loanName=rs.getString("loanName");
                    t_loanType=rs.getString("loanType");
                    t_terms=rs.getInt("loanTerms");
                    t_loanRate=rs.getDouble("loanInterestRate");
                    t_state=rs.getString("loanStatus");
                    getFNumber=rs.getString("formNo");
                    if(getFNumber.length()>=1){
                        getFormNumber.setText(getFNumber);
                    }
                }
                conn.close();
            }catch(Exception v){
                System.out.println(v);
            }
        }else{
            
        }          
    }
    public void finalReportLoad(String getvalue){
        
        DefaultTableModel model = (DefaultTableModel)tblFinalReport.getModel();
        while(model.getRowCount()>0){
            for(int p=0;p<model.getRowCount();p++){
                model.removeRow(p);
            }
        }
        DefaultTableModel mode = (DefaultTableModel)tblFinalReporG.getModel();
            while(mode.getRowCount()>0){
                for(int p=0;p<mode.getRowCount();p++){
                    mode.removeRow(p);
                }
            }
        if(sPLoan.isSelected()){//personal
            if(getvalue.length()==9){
                getvalue="19"+getvalue;
            }
            if(getvalue.length()==11){
                finalReportDataGet(getvalue);
                customerDataLoad(getvalue);
                model.addRow(new Object[]{"__________Loan Reciver__________",""});model.addRow(new Object[]{"",""});
                model.addRow(new Object[]{"Full name", getC_Name()});model.addRow(new Object[]{"Permanent Address", getC_Add()});
                model.addRow(new Object[]{"National Id No", getC_ID()});model.addRow(new Object[]{"Date of Birth", getC_dob()});
                model.addRow(new Object[]{"Religion", getC_Relig()});model.addRow(new Object[]{"Gender", getC_Gen()});
                model.addRow(new Object[]{"Nationality", getC_Nati()});model.addRow(new Object[]{"Mobile Phone Number", getC_HPhone()});
                model.addRow(new Object[]{"Land Phone Number", getC_LPhone()});model.addRow(new Object[]{"E-Mail Address", getC_email()});
                model.addRow(new Object[]{"G/S Division Name & No", getC_Gs()});model.addRow(new Object[]{"Postal Code", getC_Pos()});
                model.addRow(new Object[]{"Duration of Stay", getC_Dur()});model.addRow(new Object[]{"Marital Status", getC_Mar()});
                model.addRow(new Object[]{"",""});
                model.addRow(new Object[]{"__________Basic Loan Details__________",""});model.addRow(new Object[]{"",""});
                model.addRow(new Object[]{"Loan Name",t_loanName});model.addRow(new Object[]{"Loan Type",t_loanType});
                model.addRow(new Object[]{"Requested Loan Amount",t_loanAmount});model.addRow(new Object[]{"Loan Amount Custo",t_loanAmount});
                model.addRow(new Object[]{"Period of Loan",""});model.addRow(new Object[]{"Terms",t_terms});
                model.addRow(new Object[]{"Interest Rate",t_loanRate});model.addRow(new Object[]{"",""});
                lblStatus.setText(t_state);
            }
            try {
                DefaultTableModel model2 = (DefaultTableModel) tblFinalReporG.getModel();
                while(model2.getRowCount()>0){
                    for(int p=0;p<model2.getRowCount();p++){
                        model2.removeRow(p);
                    }
                }
                tableRefresh(getvalue,1);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Faild \n\n"+e.getMessage());
            }
        }else if(sBLoan.isSelected()){//bussiness
                finalReportDataGet(getvalue);
                businessDataLoad(getvalue);
                model.addRow(new Object[]{"__________Loan Reciver__________",""});model.addRow(new Object[]{"",""});
                model.addRow(new Object[]{"Business name", getbName()});
                model.addRow(new Object[]{"Permanent Address", getbAddress()});
                model.addRow(new Object[]{"Business Email", getbEmail()});
                model.addRow(new Object[]{"Business Reg No", getBreg()});
                model.addRow(new Object[]{"Nature of Business", getBnature()});
                model.addRow(new Object[]{"Business Location", getbLocation()});
                model.addRow(new Object[]{"Business Location Ownership", getbLocation()});
                model.addRow(new Object[]{"Duration At Present Location", getbDuration()});
                model.addRow(new Object[]{"Experience In Business", getbExpeince()});
                model.addRow(new Object[]{"Availability of Insurance Policy", getbAvailability()});
                model.addRow(new Object[]{"Mobile Phone Number", getBmobile()});
                model.addRow(new Object[]{"Fixed Phone Number", getbFix()});
                model.addRow(new Object[]{"Business Postal Code", getbPost()});
                model.addRow(new Object[]{"Sum of Insured", getBsum()}); 
                
                
                model.addRow(new Object[]{"",""});
                model.addRow(new Object[]{"__________Basic Loan Details__________",""});model.addRow(new Object[]{"",""});
                model.addRow(new Object[]{"Loan Name",t_loanName});model.addRow(new Object[]{"Loan Type",t_loanType});
                model.addRow(new Object[]{"Requested Loan Amount",t_loanAmount});model.addRow(new Object[]{"Loan Amount Custo",t_loanAmount});
                model.addRow(new Object[]{"Period of Loan",""});model.addRow(new Object[]{"Terms",t_terms});
                model.addRow(new Object[]{"Interest Rate",t_loanRate});model.addRow(new Object[]{"",""});
                lblStatus.setText(t_state);
        }else{
            DefaultTableModel model2 = (DefaultTableModel)tblFinalReport.getModel();
            while(model2.getRowCount()>0){
                for(int p=0;p<model2.getRowCount();p++){
                    model2.removeRow(p);
                }
            }
            DefaultTableModel mode3 = (DefaultTableModel)tblFinalReporG.getModel();
            while(mode3.getRowCount()>0){
                for(int p=0;p<mode3.getRowCount();p++){
                    mode3.removeRow(p);
                }
            }
        } 
        

    }
    public void tableClearTwo(){
        DefaultTableModel model = (DefaultTableModel)tblDis.getModel();
        while(model.getRowCount()>0){
            for(int p=0;p<model.getRowCount();p++){
                model.removeRow(p);
            }
        }
    }
    public void tableLoaderEqual(){
        LoanCalculation lon=new LoanCalculation();   
        double interest,getA,getB,getTempLaonAmount;
        int terms;
        int loanA=comboLoanAmount.getSelectedIndex();
        int getPerBus=loanReciever.getSelectedIndex();
        int getLoanName=comboLoanNames.getSelectedIndex();
        int getLoanType=comboLoanBlance.getSelectedIndex();
        if(loanA!=0){
            LoanAmount=Double.parseDouble(comboLoanAmount.getItemAt(comboLoanAmount.getSelectedIndex()));
            interest=(double) Double.valueOf(cmbRate.getSelectedItem().toString());
        }else{
            getTempLaonAmount=Double.parseDouble(txtAmountCustom.getText());
            LoanAmount=getTempLaonAmount;
            interest=Double.parseDouble(txtRateCustom.getText());

        }
        
        terms=Integer.parseInt(txtTerms.getText());
        double getPayment=0;
        double getInterest=0;
        String type="";
        if(getPerBus==0){//personal
            if(getLoanName==0){//diriya
                getPayment=lon.equatedInstalment(LoanAmount, interest, terms);//week1 week2
                getInterest=lon.equatedCalProfit;
                type="one";
            }else{//sahana
                 //callequalInstement
                lon.equalInstalmentOriginalValues(LoanAmount,interest,terms);//month year              
            }
        }else{//bussiness
            if(getLoanName==0){//easy
                getPayment=lon.equatedInstalmentNormal(LoanAmount, interest, terms);//day
                getInterest=lon.equatedNormalCalProfit;
                type="one";
            }
            else if(getLoanName==1){//swa
                getPayment=lon.equatedInstalment(LoanAmount, interest, terms);//week1 week2
                getInterest=lon.equatedCalProfit;
                type="one";
            }else{//sav
                lon.equalInstalmentOriginalValues(LoanAmount,interest,terms);//month year                
            }            
        }

        tableClearTwo();
        //double getMP[]=(double[]) lon.monthlyPayment.clone();
        //double getIV[]=(double[]) lon.interestValue.clone();
        int a=1;
        while(a<=lon.monthlyPayment.size()){
            DefaultTableModel model = (DefaultTableModel)tblDis.getModel();
            model.addRow(new Object[]{a,lon.interestValue.get(a-1),lon.monthlyPayment.get(a-1)});
            a++;
        }
        if(type.equals("one")){
            
            DefaultTableModel model = (DefaultTableModel)tblDis.getModel();
            model.addRow(new Object[]{1,getInterest,getPayment});
            
        }       
  
    }    
    public void basicLoanDataInsertUpdate(String type){//1 insert 2 update
        String formNumber;
        String temporyWord="save";
        String newNic;

        b_LoanName=(String) comboLoanNames.getSelectedItem();
        b_LoanType=(String) comboLoanBlance.getSelectedItem();
        
        
        b_terms=Integer.parseInt(txtTerms.getText());
        b_interestRate=(double) Double.valueOf(cmbRate.getSelectedItem().toString());
        b_reg=b_regNo.getText();
        b_LoanStatus="";
        
        if(c_Nic.getText().length()==9){
            newNic="19"+c_Nic.getText();
        }else{
            newNic=c_Nic.getText();
        }        

        t_loanName=(String) comboLoanNames.getSelectedItem();
        t_loanType=(String) comboLoanBlance.getSelectedItem();

        if(comboLoanAmount.getSelectedIndex()!=0){
            t_loanAmount=Double.valueOf(comboLoanAmount.getSelectedItem().toString());
        }else{
            t_loanAmount=Double.valueOf(txtAmountCustom.getText());
        }
        t_loanRate=Double.parseDouble(cmbRate.getSelectedItem().toString());
        t_terms=Integer.parseInt(txtTerms.getText());
        
        if(loanReciever.getSelectedIndex()==0){
            formNumber="PL"+newNic;
            if(c_Nic.getText().length()>1){
                try{
                    java.sql.Connection conn =DatabaseConnection.connect();
                    if(type.equals("insert")){
                        String sql = "INSERT INTO c_personal_loan_tempory(loanName,loanType,loanStatus,loanAmount,loanTerms,loanInterestRate,CustomerIdNumber,EmployId,formNo,comment) VALUES ('"+t_loanName+"','"+t_loanType+"','"+temporyWord+"','"+t_loanAmount+"','"+t_terms+"','"+t_loanRate+"','"+newNic+"','"+EMP+"','"+formNumber+"','')";
                        PreparedStatement pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null,"Data Insert Sucess...");
                        getFormNumber.setText(newNic);
                    }else if(type.equals("update")){
                        //basicLoanLoad(newNic);
                       // if(t_formNo.equals(newNic)){
                            //id samanada samanainm ok update
                            String sql = "UPDATE c_personal_loan_tempory SET loanName='"+t_loanName+"' ,loanType='"+t_loanType+"',loanAmount='"+t_loanAmount+"', loanTerms='"+t_terms+"', loanInterestRate='"+t_loanRate+"' WHERE CustomerIdNumber='"+newNic+"' "; 
                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();  
                            JOptionPane.showMessageDialog(null,"Data Update Sucess...");
                        //}else{
                        //    JOptionPane.showMessageDialog(null,"Uer Loan Aprovelled..\nCannot Edit now");
                        //}
                    }
                    conn.close();
                }catch(Exception v){
                    System.out.println(v);
                    JOptionPane.showMessageDialog(null,"Faild \n\n"+v.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null,"Unsucess full");
            }
        }else{
            formNumber="BL"+b_reg;
            if(b_regNo.getText().length()>1){
                try{
                    java.sql.Connection conn =DatabaseConnection.connect();
                    if(type.equals("insert")){
                        String sql="   INSERT INTO `c_business_loan_tempory`(`loanName`, `loanType`, `loanInterestRate`, `loanAmount`, `loanTerms`, `loanStatus`, `comment`, `BusinessRegisteredNo`, `EmployId`,formNo) VALUES ('"+b_LoanName+"','"+b_LoanType+"','"+b_interestRate+"','"+b_LoanAmount+"','"+b_terms+"','"+b_LoanStatus+"','"+" "+"','"+b_reg+"','"+EMP+"','"+formNumber+"')   ";
                        PreparedStatement pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null,"Data Insert Sucess...");
                        getFormNumber.setText(b_reg);
                    }else if(type.equals("update")){
                       // basicLoanLoadBusiness(b_reg);
                       // if(b_formNo.equals(b_reg)){
                            String sql=" UPDATE `c_business_loan_tempory` SET `loanName`='"+b_LoanName+"',`loanType`='"+b_LoanType+"',`loanInterestRate`='"+b_interestRate+"',`loanAmount`='"+b_LoanAmount+"',`loanTerms`='"+b_terms+"',`loanStatus`='"+b_LoanStatus+"' WHERE BusinessRegisteredNo='"+b_reg+"' ";//,`comment`='"+" "+"' ,formNo='"+" "+"'
                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();  
                            JOptionPane.showMessageDialog(null,"Data Update Sucess...");
                       // }else{
                       //     JOptionPane.showMessageDialog(null,"Uer Loan Aprovelled..\nCannot Edit now");
                       // }
                    }
                    conn.close();
                }catch(Exception v){
                    System.out.println(v);
                    JOptionPane.showMessageDialog(null,"Faild \n\n"+v.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null,"Unsucess full");
            }
        }
    }
    public void basicLoanLoadBusiness(String reg){
        String b_LoanName = null,b_LoanType = null;
        Double b_LoanAmount = null,b_interestRate = null;
        int b_terms = 0;
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM c_business_loan_tempory WHERE BusinessRegisteredNo='"+reg+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                b_LoanAmount=rs.getDouble("loanAmount");
                b_interestRate=rs.getDouble("loanInterestRate");
                b_LoanName=rs.getString("loanName");
                b_LoanType=rs.getString("loanType");
                b_terms=rs.getInt("loanTerms");
                b_formNo=rs.getString("formNo");
            } 
            comboLoanNames.setSelectedItem(b_LoanName);
            comboLoanBlance.setSelectedItem(b_LoanType);
            double value=b_LoanAmount;
            if ((int)value < value){
                txtAmountCustom.setText(String.valueOf(value));
            }else{
                comboLoanAmount.setSelectedItem(String.valueOf((int)value));
                if(comboLoanAmount.getSelectedIndex()==0){
                    txtAmountCustom.setText(String.valueOf((int)value));
                }
            }
            txtTerms.setText(String.valueOf(b_terms));
            //txtRate.setText(String.valueOf(String.valueOf(b_interestRate))); 
            cmbRate.setSelectedItem((String.valueOf(b_interestRate)));
        conn.close();
        tableLoaderEqual();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No Loan Request");
        }        
    }
    public void basicLoanLoad(String id){
        try {
            java.sql.Connection conn =DatabaseConnection.connect();
            String sql = "SELECT * FROM c_personal_loan_tempory WHERE CustomerIdNumber='"+id+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                t_loanAmount=rs.getDouble("loanAmount");
                t_loanRate=rs.getDouble("loanInterestRate");
                t_loanName=rs.getString("loanName");
                t_loanType=rs.getString("loanType");
                t_terms=rs.getInt("loanTerms");
                t_formNo=rs.getString("formNo");
            } 
            comboLoanNames.setSelectedItem(t_loanName);
            comboLoanBlance.setSelectedItem(t_loanType);
            double value=t_loanAmount;
            if ((int)value < value){
                txtAmountCustom.setText(String.valueOf(value));
            }else{
                comboLoanAmount.setSelectedItem(String.valueOf((int)value));
                if(comboLoanAmount.getSelectedIndex()==0){
                    txtAmountCustom.setText(String.valueOf((int)value));
                }
            }
            txtTerms.setText(String.valueOf(t_terms));
            //txtRate.setText(String.valueOf(String.valueOf(t_loanRate))); 
            cmbRate.setSelectedItem((String.valueOf(t_loanRate)));
        conn.close();
        tableLoaderEqual();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"No Loan Request");
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel39 = new javax.swing.JLabel();
        gender = new javax.swing.ButtonGroup();
        maridStatus = new javax.swing.ButtonGroup();
        insurance = new javax.swing.ButtonGroup();
        searchLoan = new javax.swing.ButtonGroup();
        loanTabbed = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        cFullName = new javax.swing.JTextField();
        lblc_postal = new javax.swing.JLabel();
        cMariDivorce = new javax.swing.JRadioButton();
        lblc_name = new javax.swing.JLabel();
        cPotalCode = new javax.swing.JTextField();
        comboFname = new javax.swing.JComboBox<>();
        lblc_dur = new javax.swing.JLabel();
        lblc_dob = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblc_reli = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblc_nat = new javax.swing.JLabel();
        cDurYear = new javax.swing.JTextField();
        comboNationality = new javax.swing.JComboBox<>();
        lblc_add = new javax.swing.JLabel();
        cDurMonth = new javax.swing.JTextField();
        lblc_mobi = new javax.swing.JLabel();
        lblc_land = new javax.swing.JLabel();
        lblc_email = new javax.swing.JLabel();
        lblc_mari = new javax.swing.JLabel();
        lblc_gen = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        cMariMarried = new javax.swing.JRadioButton();
        lblc_gs = new javax.swing.JLabel();
        cMariSingle = new javax.swing.JRadioButton();
        cGSDiv = new javax.swing.JTextField();
        cMariWidow = new javax.swing.JRadioButton();
        c_Mobile_Number = new javax.swing.JTextField();
        c_Land_Phone_Number = new javax.swing.JTextField();
        c_Email = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblGuarantorsDetails = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel73 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        btnSave = new javax.swing.JButton();
        comboMonth = new javax.swing.JComboBox<>();
        comboDay = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblc_nic = new javax.swing.JLabel();
        c_Nic = new javax.swing.JTextField();
        cAddress = new javax.swing.JTextField();
        clearAll = new javax.swing.JButton();
        btnDefaultValues = new javax.swing.JButton();
        btnOneColomRemove = new javax.swing.JButton();
        btnUpdateAndSave = new javax.swing.JButton();
        btnSave1 = new javax.swing.JButton();
        cYear = new javax.swing.JTextField();
        lblageLimit = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtGirantorName = new javax.swing.JTextField();
        txtGirantorNic = new javax.swing.JTextField();
        txtGirantorHPhone = new javax.swing.JTextField();
        txtGirantorLPhone = new javax.swing.JTextField();
        txtGirantorHomeAddress = new javax.swing.JTextField();
        txtGirantorEmail = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnDefaultValues1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        cComboReligion = new javax.swing.JComboBox<>();
        clearAll1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        b_Name = new javax.swing.JTextField();
        lblBPost = new javax.swing.JLabel();
        lblBReg = new javax.swing.JLabel();
        b_PostCode = new javax.swing.JTextField();
        lblDAtPresL = new javax.swing.JLabel();
        lblNature = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblBLO = new javax.swing.JLabel();
        b_DurYear = new javax.swing.JTextField();
        b_LocatOwner = new javax.swing.JComboBox<>();
        lblBAdd = new javax.swing.JLabel();
        b_DurMonth = new javax.swing.JTextField();
        lblmob = new javax.swing.JLabel();
        b_Add2 = new javax.swing.JTextField();
        lblLand = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblExpInBus = new javax.swing.JLabel();
        lblBLocation = new javax.swing.JLabel();
        b_Hphone = new javax.swing.JTextField();
        b_Lphone = new javax.swing.JTextField();
        b_Email = new javax.swing.JTextField();
        b_Location = new javax.swing.JComboBox<>();
        b_ExpYear = new javax.swing.JTextField();
        b_ExpMonth = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblAvaofInsPol = new javax.swing.JLabel();
        b_AYes = new javax.swing.JRadioButton();
        b_ANo = new javax.swing.JRadioButton();
        lblSumofInsured = new javax.swing.JLabel();
        b_SumInsured = new javax.swing.JTextField();
        Save = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        b_NatureBus = new javax.swing.JTextField();
        b_Add1 = new javax.swing.JTextField();
        b_regNo = new javax.swing.JTextField();
        lblBN = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        btnDefaultValues2 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        txtAmountCustom = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        txtPeriodYear = new javax.swing.JTextField();
        txtPeriodMonth = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        txtPeriodWeek = new javax.swing.JTextField();
        txtPeriodDay = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txtTerms = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboLoanNames = new javax.swing.JComboBox<>();
        comboLoanBlance = new javax.swing.JComboBox<>();
        comboLoanAmount = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDis = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cmbRate = new javax.swing.JComboBox<>();
        txtRateCustom = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        FinalSearch = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblFinalReport = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblFinalReporG = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        sPLoan = new javax.swing.JRadioButton();
        sBLoan = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        aprool = new javax.swing.JButton();
        loanReciever = new javax.swing.JComboBox<>();
        getFormNumber = new javax.swing.JTextField();

        jLabel39.setText(" Y");

        setBackground(new java.awt.Color(244, 244, 244));
        setMinimumSize(new java.awt.Dimension(800, 497));

        loanTabbed.setBackground(new java.awt.Color(255, 255, 255));
        loanTabbed.setMinimumSize(new java.awt.Dimension(800, 450));
        loanTabbed.setPreferredSize(new java.awt.Dimension(800, 450));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMinimumSize(new java.awt.Dimension(1000, 900));

        cFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cFullNameActionPerformed(evt);
            }
        });
        cFullName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cFullNameKeyPressed(evt);
            }
        });

        lblc_postal.setText("Postal Code");

        cMariDivorce.setBackground(new java.awt.Color(255, 255, 255));
        maridStatus.add(cMariDivorce);
        cMariDivorce.setText("Divorced");
        cMariDivorce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cMariDivorceActionPerformed(evt);
            }
        });

        lblc_name.setText("Full Name");

        comboFname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rev", "Mr", "Mrs", "Miss" }));
        comboFname.setSelectedIndex(1);
        comboFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFnameActionPerformed(evt);
            }
        });

        lblc_dur.setText("Duration of Stay");

        lblc_dob.setText("Date of Birth");

        jLabel10.setText(" Y");

        lblc_reli.setText("Religion");

        jLabel11.setText("M");

        lblc_nat.setText("Nationality");

        cDurYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDurYearActionPerformed(evt);
            }
        });
        cDurYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cDurYearKeyPressed(evt);
            }
        });

        comboNationality.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sri Lankan", "Other" }));
        comboNationality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNationalityActionPerformed(evt);
            }
        });

        lblc_add.setText("Permanent Address");

        cDurMonth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cDurMonthKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cDurMonthKeyReleased(evt);
            }
        });

        lblc_mobi.setText("Mobile Phone Number");

        lblc_land.setText("Land Phone Number");
        lblc_land.setMaximumSize(new java.awt.Dimension(103, 14));
        lblc_land.setMinimumSize(new java.awt.Dimension(103, 14));
        lblc_land.setPreferredSize(new java.awt.Dimension(103, 14));

        lblc_email.setText("E-Mail Address");

        lblc_mari.setText("Marital Status");

        lblc_gen.setText("Gender");

        male.setBackground(new java.awt.Color(255, 255, 255));
        gender.add(male);
        male.setSelected(true);
        male.setText("Male");
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });

        female.setBackground(new java.awt.Color(255, 255, 255));
        gender.add(female);
        female.setText("Female");
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });

        cMariMarried.setBackground(new java.awt.Color(255, 255, 255));
        maridStatus.add(cMariMarried);
        cMariMarried.setText("Married");
        cMariMarried.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cMariMarriedActionPerformed(evt);
            }
        });

        lblc_gs.setText("G/S Division Name & No.");

        cMariSingle.setBackground(new java.awt.Color(255, 255, 255));
        maridStatus.add(cMariSingle);
        cMariSingle.setSelected(true);
        cMariSingle.setText("Single");
        cMariSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cMariSingleActionPerformed(evt);
            }
        });

        cMariWidow.setBackground(new java.awt.Color(255, 255, 255));
        maridStatus.add(cMariWidow);
        cMariWidow.setText("Widow");

        c_Mobile_Number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_Mobile_NumberActionPerformed(evt);
            }
        });
        c_Mobile_Number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c_Mobile_NumberKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                c_Mobile_NumberKeyReleased(evt);
            }
        });

        c_Land_Phone_Number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_Land_Phone_NumberActionPerformed(evt);
            }
        });
        c_Land_Phone_Number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c_Land_Phone_NumberKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                c_Land_Phone_NumberKeyReleased(evt);
            }
        });

        c_Email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c_EmailKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                c_EmailKeyReleased(evt);
            }
        });

        tblGuarantorsDetails.setModel(new javax.swing.table.DefaultTableModel(
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
        tblGuarantorsDetails.setRowHeight(24);
        tblGuarantorsDetails.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblGuarantorsDetails.setShowVerticalLines(false);
        tblGuarantorsDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGuarantorsDetailsMouseClicked(evt);
            }
        });
        tblGuarantorsDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblGuarantorsDetailsKeyReleased(evt);
            }
        });
        jScrollPane13.setViewportView(tblGuarantorsDetails);

        jLabel72.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel72.setText("Guarantor’s Details");

        jLabel73.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel73.setText("Customer’s Details");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        comboMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        comboDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel38.setText(" Y");

        jLabel40.setText(" M");

        jLabel41.setText("D");

        lblc_nic.setText("National Id No");

        c_Nic.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                c_NicFocusGained(evt);
            }
        });
        c_Nic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_NicActionPerformed(evt);
            }
        });
        c_Nic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c_NicKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                c_NicKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                c_NicKeyTyped(evt);
            }
        });

        cAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cAddressActionPerformed(evt);
            }
        });

        clearAll.setText("Clear all Table Data");
        clearAll.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                clearAllAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        clearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllActionPerformed(evt);
            }
        });

        btnDefaultValues.setText("Load Enterd Data");
        btnDefaultValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefaultValuesActionPerformed(evt);
            }
        });

        btnOneColomRemove.setText("Delete Selected Row");
        btnOneColomRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOneColomRemoveActionPerformed(evt);
            }
        });

        btnUpdateAndSave.setText("Data Edit & Update");
        btnUpdateAndSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateAndSaveActionPerformed(evt);
            }
        });

        btnSave1.setText("Save");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        cYear.setMinimumSize(null);
        cYear.setPreferredSize(null);
        cYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cYearActionPerformed(evt);
            }
        });
        cYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cYearKeyPressed(evt);
            }
        });

        lblageLimit.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblageLimit.setForeground(new java.awt.Color(255, 0, 0));
        lblageLimit.setText(" ");

        jLabel34.setText("Guarantor's Name  ");
        jLabel34.setMaximumSize(null);
        jLabel34.setMinimumSize(null);
        jLabel34.setPreferredSize(new java.awt.Dimension(98, 20));

        jLabel37.setText("NIC No");

        jLabel43.setText("Hand Phone Number");
        jLabel43.setMaximumSize(null);
        jLabel43.setMinimumSize(null);
        jLabel43.setPreferredSize(new java.awt.Dimension(108, 14));

        jLabel44.setText("Land Phone Number");
        jLabel44.setMaximumSize(null);
        jLabel44.setMinimumSize(null);
        jLabel44.setPreferredSize(new java.awt.Dimension(108, 20));

        jLabel45.setText("Email Address");
        jLabel45.setMaximumSize(null);
        jLabel45.setMinimumSize(null);
        jLabel45.setPreferredSize(new java.awt.Dimension(98, 20));

        jLabel46.setText("Home Address");

        txtGirantorNic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGirantorNicKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGirantorNicKeyReleased(evt);
            }
        });

        txtGirantorHPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGirantorHPhoneKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGirantorHPhoneKeyReleased(evt);
            }
        });

        txtGirantorEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGirantorEmailActionPerformed(evt);
            }
        });

        jButton3.setText("Edit & Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnDefaultValues1.setText("Load Enterd Data");
        btnDefaultValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefaultValues1ActionPerformed(evt);
            }
        });

        jButton6.setText("Clear All");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        cComboReligion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buddhism", "Hinduism", "Islam", "Christianity", "Other" }));

        clearAll1.setText("Clear Data");
        clearAll1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                clearAll1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        clearAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAll1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane13))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(clearAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOneColomRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDefaultValues, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(376, 376, 376)
                .addComponent(clearAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateAndSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jSeparator6))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txtGirantorName, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGirantorHPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txtGirantorNic, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(351, 351, 351)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txtGirantorHomeAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(152, 152, 152)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGirantorEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(txtGirantorLPhone)))))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblc_dob)
                .addGap(49, 49, 49)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblageLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(comboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cYear, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(167, 167, 167)
                .addComponent(lblc_postal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cGSDiv, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(cPotalCode)))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblc_gen)
                .addGap(77, 77, 77)
                .addComponent(male)
                .addGap(18, 18, 18)
                .addComponent(female)
                .addGap(391, 391, 391)
                .addComponent(lblc_mari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(cMariSingle)
                .addGap(0, 0, 0)
                .addComponent(cMariWidow)
                .addGap(0, 0, 0)
                .addComponent(cMariDivorce)
                .addGap(0, 0, 0)
                .addComponent(cMariMarried, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblc_reli)
                .addGap(69, 69, 69)
                .addComponent(cComboReligion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(324, 324, 324)
                .addComponent(lblc_dur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(cDurYear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addGap(4, 4, 4)
                .addComponent(cDurMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(lblc_name, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(cFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(comboFname, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)
                                .addComponent(lblc_mobi))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(lblc_add)
                                        .addGap(1, 1, 1)
                                        .addComponent(cAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(lblc_nic)
                                        .addGap(34, 34, 34)
                                        .addComponent(c_Nic, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(155, 155, 155)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblc_email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblc_land, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_Mobile_Number)
                            .addComponent(c_Email)
                            .addComponent(c_Land_Phone_Number)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(640, 640, 640)
                        .addComponent(lblc_gs)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jSeparator7))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(btnSave1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDefaultValues1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel73))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblc_nat, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(comboNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel72)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel73)
                .addGap(6, 6, 6)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblc_name)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(c_Mobile_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblc_mobi)))))
                .addGap(12, 12, 12)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblc_land, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblc_add)
                            .addComponent(c_Land_Phone_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblc_nic))
                    .addComponent(c_Nic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblc_email)
                    .addComponent(c_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblc_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lblageLimit)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel41))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(comboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel40))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel38)
                                    .addComponent(cYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblc_gs)
                        .addGap(11, 11, 11)
                        .addComponent(lblc_postal))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(cGSDiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cPotalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblc_reli))
                    .addComponent(cComboReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblc_dur))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel10))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(cDurYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel11))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(cDurMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblc_gen))
                    .addComponent(male)
                    .addComponent(female)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblc_mari))
                    .addComponent(cMariSingle)
                    .addComponent(cMariWidow)
                    .addComponent(cMariDivorce)
                    .addComponent(cMariMarried))
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblc_nat, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDefaultValues1)
                    .addComponent(btnSave1)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addGap(50, 50, 50)
                .addComponent(jLabel72)
                .addGap(6, 6, 6)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGirantorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGirantorHPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGirantorNic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGirantorLPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGirantorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGirantorHomeAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnUpdateAndSave)
                    .addComponent(clearAll1))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOneColomRemove)
                    .addComponent(btnDefaultValues)
                    .addComponent(clearAll))
                .addGap(141, 141, 141))
        );

        jScrollPane2.setViewportView(jPanel10);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        loanTabbed.addTab("   Customer and Guarantor’s Details   ", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBorder(null);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(884, 400));

        lblBPost.setText("Postal Code");

        lblBReg.setText("Registered No");

        lblDAtPresL.setText("Duration at Present Location");

        lblNature.setText("Nature of Business");

        jLabel20.setText("Years");

        jLabel22.setText("Months");

        lblBLO.setText("Business Location Ownership");

        b_DurYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_DurYearActionPerformed(evt);
            }
        });
        b_DurYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_DurYearKeyPressed(evt);
            }
        });

        b_LocatOwner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Owned", "Rent", "With third party", "Other" }));
        b_LocatOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_LocatOwnerActionPerformed(evt);
            }
        });

        lblBAdd.setText("Business Address");

        b_DurMonth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_DurMonthKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_DurMonthKeyReleased(evt);
            }
        });

        lblmob.setText("Mobile Phone Number");

        b_Add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_Add2ActionPerformed(evt);
            }
        });

        lblLand.setText("Land Phone Number");

        lblEmail.setText("E-Mail");

        lblExpInBus.setText("Experience in Business");

        lblBLocation.setText("Business Location");

        b_Hphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_HphoneActionPerformed(evt);
            }
        });
        b_Hphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_HphoneKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_HphoneKeyReleased(evt);
            }
        });

        b_Lphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_LphoneActionPerformed(evt);
            }
        });
        b_Lphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_LphoneKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_LphoneKeyReleased(evt);
            }
        });

        b_Email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_EmailKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_EmailKeyReleased(evt);
            }
        });

        b_Location.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shop", "Home", "Market", "Other" }));
        b_Location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_LocationActionPerformed(evt);
            }
        });

        b_ExpYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ExpYearActionPerformed(evt);
            }
        });
        b_ExpYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_ExpYearKeyPressed(evt);
            }
        });

        b_ExpMonth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_ExpMonthKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_ExpMonthKeyReleased(evt);
            }
        });

        jLabel21.setText("Years");

        jLabel29.setText("Months");

        lblAvaofInsPol.setText("Availability of Insurance Policy");

        insurance.add(b_AYes);
        b_AYes.setSelected(true);
        b_AYes.setText("Yes");
        b_AYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_AYesActionPerformed(evt);
            }
        });

        insurance.add(b_ANo);
        b_ANo.setText("No");
        b_ANo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ANoActionPerformed(evt);
            }
        });

        lblSumofInsured.setText("Sum of Insured");

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel74.setText("Business Details");

        b_NatureBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_NatureBusActionPerformed(evt);
            }
        });

        b_Add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_Add1ActionPerformed(evt);
            }
        });

        lblBN.setText("Business Name");

        jButton7.setText("Edit & Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnDefaultValues2.setText("Load  Data");
        btnDefaultValues2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefaultValues2ActionPerformed(evt);
            }
        });

        jButton10.setText("Clear All");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel74))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(b_Add2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(lblBLocation)
                        .addGap(101, 101, 101)
                        .addComponent(b_Location, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNature)
                                    .addComponent(lblBAdd)
                                    .addComponent(lblBN))
                                .addGap(96, 96, 96)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b_NatureBus, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(b_Add1)
                                    .addComponent(b_Name))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblmob)
                                    .addComponent(lblLand)
                                    .addComponent(lblEmail))
                                .addGap(82, 82, 82)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b_Hphone, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(b_Lphone)
                                    .addComponent(b_Email)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(2, 2, 2)
                                .addComponent(b_ExpMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addComponent(b_regNo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblExpInBus)
                                    .addComponent(lblAvaofInsPol)
                                    .addComponent(lblSumofInsured)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBReg)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblBPost)
                                                .addComponent(lblDAtPresL))
                                            .addGap(48, 48, 48)
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                    .addComponent(jLabel20)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(b_DurYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(35, 35, 35)
                                                    .addComponent(jLabel22)
                                                    .addGap(10, 10, 10)
                                                    .addComponent(b_DurMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(b_PostCode, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                            .addComponent(lblBLO, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(53, 53, 53)
                                            .addComponent(b_LocatOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnDefaultValues2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(b_ExpYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(b_SumInsured, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(b_AYes)
                                .addGap(18, 18, 18)
                                .addComponent(b_ANo)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel74)
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblmob)
                                    .addComponent(b_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBN))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblLand)
                                    .addComponent(b_NatureBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNature, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEmail)
                                    .addComponent(b_Add1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBAdd)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(b_Hphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(b_Lphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(b_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b_Location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b_Add2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBLocation))))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(b_regNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBReg)))
                            .addComponent(lblExpInBus)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel21))
                            .addComponent(b_ExpYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel29))
                            .addComponent(b_ExpMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblBLO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(b_AYes)
                                        .addComponent(lblAvaofInsPol))
                                    .addComponent(b_ANo)))))
                    .addComponent(b_LocatOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(lblBPost)
                                .addGap(18, 18, 18)
                                .addComponent(lblDAtPresL))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(b_PostCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b_DurYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_DurMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel22))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_SumInsured, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSumofInsured))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Save)
                            .addComponent(btnDefaultValues2))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton10))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jScrollPane3.setViewportView(jPanel11);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        loanTabbed.addTab("   Business Details  ", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane7.setBorder(null);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        txtAmountCustom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAmountCustomKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAmountCustomKeyReleased(evt);
            }
        });

        jLabel51.setText("Loan Amount custom");

        jLabel52.setText("Loan Type");

        jLabel53.setText("Period of Loan");

        jLabel75.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel75.setText("Basic Loan Details");

        txtPeriodYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPeriodYearActionPerformed(evt);
            }
        });
        txtPeriodYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPeriodYearKeyPressed(evt);
            }
        });

        txtPeriodMonth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPeriodMonthKeyPressed(evt);
            }
        });

        jLabel76.setText("Years");

        jLabel77.setText("Months");

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel35.setText("Terms");

        txtPeriodWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPeriodWeekActionPerformed(evt);
            }
        });
        txtPeriodWeek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPeriodWeekKeyPressed(evt);
            }
        });

        txtPeriodDay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPeriodDayKeyPressed(evt);
            }
        });

        jLabel78.setText("Weeks");

        jLabel79.setText("Day");

        txtTerms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTermsActionPerformed(evt);
            }
        });
        txtTerms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTermsKeyPressed(evt);
            }
        });

        jButton9.setText("Calculate");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel36.setText("Interest Rate");

        jLabel1.setText("Loan Name");

        comboLoanNames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoanNamesActionPerformed(evt);
            }
        });

        comboLoanBlance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day By Day", "Week One", "Week Two", "Month", "Year" }));
        comboLoanBlance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoanBlanceActionPerformed(evt);
            }
        });

        comboLoanAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoanAmountActionPerformed(evt);
            }
        });

        tblDis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Terms", "Interest", "Payment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
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
        jScrollPane5.setViewportView(tblDis);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("%");

        jLabel54.setText("Requested Loan Amount");

        jButton8.setText("Clear All");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cmbRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRateActionPerformed(evt);
            }
        });

        txtRateCustom.setToolTipText("Enter Custom Interest rate");
        txtRateCustom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRateCustomKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel51)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel75)))
                .addGap(663, 663, 663))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jSeparator9))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                            .addComponent(jLabel54)
                                            .addGap(278, 278, 278))
                                        .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(140, 140, 140)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(comboLoanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboLoanBlance, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboLoanNames, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                            .addComponent(jLabel76)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(txtPeriodYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                            .addComponent(jLabel78)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(txtPeriodWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(txtTerms, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                            .addComponent(jLabel79)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(txtPeriodDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                            .addComponent(jLabel77)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(txtPeriodMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                    .addComponent(cmbRate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel2))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                    .addComponent(txtAmountCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtRateCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(19, 19, 19))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel75)
                .addGap(6, 6, 6)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addGap(320, 320, 320))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboLoanNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboLoanBlance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboLoanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel36))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAmountCustom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel51)
                                    .addComponent(txtRateCustom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPeriodYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel77)
                                    .addComponent(txtPeriodMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel78)
                                    .addComponent(txtPeriodWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel79)
                                    .addComponent(txtPeriodDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txtTerms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jScrollPane7.setViewportView(jPanel13);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        loanTabbed.addTab("   Basic Loan Details   ", jPanel4);

        jScrollPane1.setBorder(null);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel80.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel80.setText("Basic Loan Details");

        FinalSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FinalSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FinalSearchKeyReleased(evt);
            }
        });

        jButton11.setText("Search");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        tblFinalReport.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblFinalReport.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFinalReport.setDragEnabled(true);
        tblFinalReport.setGridColor(new java.awt.Color(255, 255, 255));
        tblFinalReport.setRowHeight(24);
        tblFinalReport.setShowHorizontalLines(false);
        tblFinalReport.setShowVerticalLines(false);
        jScrollPane6.setViewportView(tblFinalReport);

        tblFinalReporG.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFinalReporG.setRowHeight(24);
        tblFinalReporG.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblFinalReporG.setShowVerticalLines(false);
        tblFinalReporG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFinalReporGMouseClicked(evt);
            }
        });
        tblFinalReporG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblFinalReporGKeyReleased(evt);
            }
        });
        jScrollPane14.setViewportView(tblFinalReporG);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Loan Status :");

        lblStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblStatus.setText("Pending");

        sPLoan.setBackground(new java.awt.Color(255, 255, 255));
        searchLoan.add(sPLoan);
        sPLoan.setSelected(true);
        sPLoan.setText("P Loan");
        sPLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sPLoanActionPerformed(evt);
            }
        });

        sBLoan.setBackground(new java.awt.Color(255, 255, 255));
        searchLoan.add(sBLoan);
        sBLoan.setText("B Loan");
        sBLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sBLoanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addGap(686, 861, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(FinalSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sPLoan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sBLoan)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblStatus)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel80)
                .addGap(6, 6, 6)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FinalSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11)
                    .addComponent(jLabel3)
                    .addComponent(lblStatus)
                    .addComponent(sPLoan)
                    .addComponent(sBLoan))
                .addGap(406, 406, 406)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(125, 125, 125)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(322, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(jPanel6);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        loanTabbed.addTab("     Final Report     ", jPanel5);

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        aprool.setText("Get Approvel");
        aprool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aproolActionPerformed(evt);
            }
        });

        loanReciever.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personal Loan", "Business Loan" }));
        loanReciever.setBorder(null);
        loanReciever.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loanRecieverActionPerformed(evt);
            }
        });

        getFormNumber.setToolTipText("add 8 digits");
        getFormNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFormNumberActionPerformed(evt);
            }
        });
        getFormNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                getFormNumberKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(416, 416, 416)
                        .addComponent(loanReciever, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(getFormNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(aprool, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                    .addComponent(loanTabbed, javax.swing.GroupLayout.DEFAULT_SIZE, 1105, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(loanTabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 447, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aprool)
                    .addComponent(jButton2)
                    .addComponent(loanReciever, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getFormNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void b_ExpYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ExpYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_ExpYearActionPerformed

    private void b_LocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_LocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_LocationActionPerformed

    private void b_LphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_LphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_LphoneActionPerformed

    private void b_HphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_HphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_HphoneActionPerformed

    private void b_Add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_Add2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_Add2ActionPerformed

    private void b_LocatOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_LocatOwnerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_LocatOwnerActionPerformed

    private void b_DurYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_DurYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_DurYearActionPerformed

    private void txtPeriodYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPeriodYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPeriodYearActionPerformed

    private void txtPeriodWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPeriodWeekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPeriodWeekActionPerformed

    private void txtTermsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTermsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTermsActionPerformed

    private void b_NatureBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_NatureBusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_NatureBusActionPerformed

    private void b_Add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_Add1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_Add1ActionPerformed

    private void loanRecieverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loanRecieverActionPerformed
        int comboGetChecked = loanReciever.getSelectedIndex();
        //Week 1, Week 2, Month, Years
        //W, M, Y
        if (comboGetChecked == 0) {
            loanTabbed.setSelectedIndex(0);
            loanTabbed.setEnabledAt(0, true);
            loanTabbed.setEnabledAt(1, false);
            comboLoanNames.removeAllItems();
            comboLoanNames.addItem("Diriya");
            comboLoanNames.addItem("Sahana");
            sPLoan.setSelected(true);
            
            
        } else if (comboGetChecked == 1) {
            loanTabbed.setSelectedIndex(1);
            loanTabbed.setEnabledAt(1, true);
            loanTabbed.setEnabledAt(0, false);
            comboLoanNames.removeAllItems();
            comboLoanNames.addItem("Easy Loan");comboLoanNames.addItem("Swashakthi");
            comboLoanNames.addItem("Saviya");
            sBLoan.setSelected(true);
        } else {
            loanTabbed.setSelectedIndex(3);
        }
    }//GEN-LAST:event_loanRecieverActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        businessDataUpdate(b_regNo.getText(), true);
    }//GEN-LAST:event_SaveActionPerformed

    private void b_HphoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_HphoneKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            b_Hphone.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),10,b_Hphone.getText().length())){
                b_Hphone.setEditable(true);
            }else{
                b_Hphone.setEditable(false);
            }            
        }else{
            b_Hphone.setEditable(false);
        }
        
        
    }//GEN-LAST:event_b_HphoneKeyPressed

    private void b_HphoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_HphoneKeyReleased
        ValidityCheck valid = new ValidityCheck();
        if (valid.checkMobilePhoneNumber(b_Hphone.getText())) {
            b_Hphone.setForeground(Color.GREEN);
        } else {
            b_Hphone.setForeground(Color.red);
        }
    }//GEN-LAST:event_b_HphoneKeyReleased

    private void b_LphoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_LphoneKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            b_Lphone.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),10,b_Lphone.getText().length())){
                b_Lphone.setEditable(true);
            }else{
                b_Lphone.setEditable(false);
            }            
        }else{
            b_Lphone.setEditable(false);
        }
    }//GEN-LAST:event_b_LphoneKeyPressed

    private void b_LphoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_LphoneKeyReleased
        ValidityCheck valid = new ValidityCheck();
        if (valid.checkMobilePhoneNumber(b_Lphone.getText())) {
            b_Lphone.setForeground(Color.GREEN);
        } else {
            b_Lphone.setForeground(Color.red);
        }
    }//GEN-LAST:event_b_LphoneKeyReleased

    private void b_DurYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_DurYearKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            b_DurYear.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),2,b_DurYear.getText().length())){
                b_DurYear.setEditable(true);
            }else{
                b_DurYear.setEditable(false);
            }            
        }else{
            b_DurYear.setEditable(false);
        }        

    }//GEN-LAST:event_b_DurYearKeyPressed

    private void b_DurMonthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_DurMonthKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            b_DurMonth.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),2,b_DurMonth.getText().length())){
                b_DurMonth.setEditable(true);
            }else{
                b_DurMonth.setEditable(false);
            }            
        }else{
            b_DurMonth.setEditable(false);
        }  
    }//GEN-LAST:event_b_DurMonthKeyPressed

    private void b_ExpMonthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_ExpMonthKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            b_ExpMonth.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),2,b_ExpMonth.getText().length())){
                b_ExpMonth.setEditable(true);
            }else{
                b_ExpMonth.setEditable(false);
            }            
        }else{
            b_ExpMonth.setEditable(false);
        }  
    }//GEN-LAST:event_b_ExpMonthKeyPressed

    private void b_ExpYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_ExpYearKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            b_ExpYear.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),2,b_ExpYear.getText().length())){
                b_ExpYear.setEditable(true);
            }else{
                b_ExpYear.setEditable(false);
            }            
        }else{
            b_ExpYear.setEditable(false);
        }  
    }//GEN-LAST:event_b_ExpYearKeyPressed

    private void b_EmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_EmailKeyPressed
        ValidityCheck valid = new ValidityCheck();
            if(valid.lengthLimited(evt.getKeyChar(),50,b_Email.getText().length())){
                b_Email.setEditable(true);
            }else{
                b_Email.setEditable(false);
            }   
    }//GEN-LAST:event_b_EmailKeyPressed

    private void b_EmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_EmailKeyReleased
        ValidityCheck valid = new ValidityCheck();
        if (valid.checkEmail(b_Email.getText()) == false) {
            b_Email.setForeground(Color.red);
        } else {
            b_Email.setForeground(Color.GREEN);
        }
    }//GEN-LAST:event_b_EmailKeyReleased

    private void b_AYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_AYesActionPerformed

            b_SumInsured.setEnabled(true);
            SumofInsured=Double.valueOf(b_SumInsured.getText());
   
    }//GEN-LAST:event_b_AYesActionPerformed

    private void b_ANoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ANoActionPerformed

            b_SumInsured.setEnabled(false);
            SumofInsured=0;

    }//GEN-LAST:event_b_ANoActionPerformed

    private void b_DurMonthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_DurMonthKeyReleased
        int c=Integer.parseInt(b_DurMonth.getText());
        if(c>11){
            b_DurMonth.setText("");
        }
    }//GEN-LAST:event_b_DurMonthKeyReleased

    private void comboLoanBlanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoanBlanceActionPerformed
        AdminLoanDetails ad=new AdminLoanDetails();

        int tw=comboLoanNames.getSelectedIndex();
        int comGetChe = loanReciever.getSelectedIndex();
        int cv=comboLoanBlance.getSelectedIndex();
        if(comGetChe==0 && tw==0){
            if(cv==0){
                comboLoanAmount.removeAllItems();
                cmbRate.removeAllItems();
                //week1 amount
                comboLoanAmount.addItem("Custom Amount");
                for(String h:ad.getDay()){
                    comboLoanAmount.addItem(h);
                }cmbRate.addItem("");
                for(String r:ad.getDayr()){
                    cmbRate.addItem(r);
                }

            }else{
                comboLoanAmount.removeAllItems();
                cmbRate.removeAllItems();
                //week 2 amount
                comboLoanAmount.addItem("Custom Amount");
                for(String h:ad.getWeekTwo()){
                    comboLoanAmount.addItem(h);
                }cmbRate.addItem("");
                for(String r:ad.getWeekTwor()){
                    cmbRate.addItem(r);
                }
            }
            
        }else if(comGetChe==0 && tw==1){
            if(cv==0){
                comboLoanAmount.removeAllItems();
                cmbRate.removeAllItems();
                //month amount
                comboLoanAmount.addItem("Custom Amount");
                for(String h:ad.getMonth()){
                    comboLoanAmount.addItem(h);
                }cmbRate.addItem("");
                for(String r:ad.getMonthr()){
                    cmbRate.addItem(r);
                }
            }else{
                comboLoanAmount.removeAllItems();
                cmbRate.removeAllItems();
                //year amount
                comboLoanAmount.addItem("Custom Amount");
                for(String h:ad.getYear()){
                    comboLoanAmount.addItem(h);
                }cmbRate.addItem("");
                for(String r:ad.getYearr()){
                    cmbRate.addItem(r);
                }              
            }               
        }else if(comGetChe==1 && tw==0){
            comboLoanAmount.removeAllItems();
            cmbRate.removeAllItems();
            //day by day  
            comboLoanAmount.addItem("Custom Amount");
            for(String h:ad.getDay()){
                    comboLoanAmount.addItem(h);
                }cmbRate.addItem("");
            for(String r:ad.getDayr()){
                    cmbRate.addItem(r);
                } 
            
        }else if(comGetChe==1 && tw==1){
            if(cv==0){
                comboLoanAmount.removeAllItems();
                cmbRate.removeAllItems();
                //week1 amount
                comboLoanAmount.addItem("Custom Amount");
                for(String h:ad.getWeekOne()){
                    comboLoanAmount.addItem(h);
                }cmbRate.addItem("");
                for(String r:ad.getWeekOner()){
                    cmbRate.addItem(r);
                } 
            }else{
                comboLoanAmount.removeAllItems();
                cmbRate.removeAllItems();
                //week 2 amount
                comboLoanAmount.addItem("Custom Amount");
                for(String h:ad.getWeekTwo()){
                    comboLoanAmount.addItem(h);
                }cmbRate.addItem("");
                for(String r:ad.getWeekTwor()){
                    cmbRate.addItem(r);
                }
            }
        }else if(comGetChe==1 && tw==2){
            if(cv==0){
                comboLoanAmount.removeAllItems();
                cmbRate.removeAllItems();
                //month amount
                comboLoanAmount.addItem("Custom Amount");
                for(String h:ad.getMonth()){
                    comboLoanAmount.addItem(h);
                }cmbRate.addItem("");
                for(String r:ad.getMonthr()){
                    cmbRate.addItem(r);
                }
            }else{
                comboLoanAmount.removeAllItems();
                cmbRate.removeAllItems();
                //year amount
                comboLoanAmount.addItem("Custom Amount");
                for(String h:ad.getYear()){
                    comboLoanAmount.addItem(h);
                }cmbRate.addItem("");
                for(String r:ad.getYearr()){
                    cmbRate.addItem(r);
                }
            }              
        }
        
        
        
    }//GEN-LAST:event_comboLoanBlanceActionPerformed

    private void comboLoanNamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoanNamesActionPerformed
        int comGetChe = loanReciever.getSelectedIndex();
        int tw=comboLoanNames.getSelectedIndex();
        if(comGetChe==0 && tw==0){
            comboLoanBlance.removeAllItems();
            comboLoanBlance.addItem("Week One");
            comboLoanBlance.addItem("Week Two");
        }else if(comGetChe==0 && tw==1){
            comboLoanBlance.removeAllItems();
            comboLoanBlance.addItem("Month");
            comboLoanBlance.addItem("Year");                
        }
        
        if(comGetChe==1 && tw==0){
            comboLoanBlance.removeAllItems();
            comboLoanBlance.addItem("Day By Day");
        }else if(comGetChe==1 && tw==1){
            comboLoanBlance.removeAllItems();
            comboLoanBlance.addItem("Week One");
            comboLoanBlance.addItem("Week Two");              
        }else if(comGetChe==1 && tw==2){
            comboLoanBlance.removeAllItems();
            comboLoanBlance.addItem("Month");
            comboLoanBlance.addItem("Year");                
        }
                
        
    }//GEN-LAST:event_comboLoanNamesActionPerformed

    private void comboLoanAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoanAmountActionPerformed
        //txtRate.setText("20%");
        //cmbRate.setSelectedIndex(comboLoanAmount.getSelectedIndex());
        int tw=comboLoanNames.getSelectedIndex();
        int comGetChe = loanReciever.getSelectedIndex();
        int cv=comboLoanBlance.getSelectedIndex();
        int com=comboLoanAmount.getSelectedIndex();
      
        try{
            if(comboLoanAmount.getSelectedIndex()==0){
                cmbRate.setSelectedIndex(0);
            }else if(comboLoanAmount.getSelectedIndex()>=1){
                cmbRate.setSelectedIndex(comboLoanAmount.getSelectedIndex());
            }
        }catch(Exception v){
            System.out.println(v);
        }

        
        
        if(comGetChe==0 && tw==0){
            if(cv==0){//week1
                txtTerms.setText("12");
                txtPeriodYear.setText("0");
                txtPeriodMonth.setText("3");
                txtPeriodWeek.setText("0");
                txtPeriodDay.setText("0");
                
                
            }else{
                txtTerms.setText("24");
                txtPeriodYear.setText("0");
                txtPeriodMonth.setText("6");
                txtPeriodWeek.setText("0");
                txtPeriodDay.setText("0");
                
            }            
        }else if(comGetChe==0 && tw==1){
            if(cv==0){
                txtTerms.setText("12");
                txtPeriodYear.setText("1");
                txtPeriodMonth.setText("0");
                txtPeriodWeek.setText("0");
                txtPeriodDay.setText("0");
            }else{
                txtTerms.setText("60");
                txtPeriodYear.setText("5");
                txtPeriodMonth.setText("0");
                txtPeriodWeek.setText("0");
                txtPeriodDay.setText("0");
            }
            
        }else if(comGetChe==1 && tw==0){
                txtTerms.setText("1");
                txtPeriodYear.setText("0");
                txtPeriodMonth.setText("0");
                txtPeriodWeek.setText("0");
                txtPeriodDay.setText("1");
  
        }else if(comGetChe==1 && tw==1){
            if(cv==0){//week1
                txtTerms.setText("12");
                txtPeriodYear.setText("0");
                txtPeriodMonth.setText("3");
                txtPeriodWeek.setText("0");
                txtPeriodDay.setText("0");                
            }else{
                txtTerms.setText("24");
                txtPeriodYear.setText("0");
                txtPeriodMonth.setText("6");
                txtPeriodWeek.setText("0");
                txtPeriodDay.setText("0");                
            }     
        }else if(comGetChe==1 && tw==2){
            if(cv==0){ 
                txtTerms.setText("12");
                txtPeriodYear.setText("1");
                txtPeriodMonth.setText("0");
                txtPeriodWeek.setText("0");
                txtPeriodDay.setText("0");                
            }else{
                txtTerms.setText("60");
                txtPeriodYear.setText("5");
                txtPeriodMonth.setText("0");
                txtPeriodWeek.setText("0");
                txtPeriodDay.setText("0");                
            }               
                         
        }        
        
        
    }//GEN-LAST:event_comboLoanAmountActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        tableLoaderEqual();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if(loanReciever.getSelectedIndex()==0){
            if(c_Nic.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"You cant Empty National Id Field.");
            }else{
                basicLoanDataInsertUpdate("insert");//inset
            }
        }else{
            if(b_regNo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"You cant Empty National Id Field.");
            }else{
                basicLoanDataInsertUpdate("insert");//inset
            }
        }

        
    }//GEN-LAST:event_saveActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tblFinalReporGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFinalReporGMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblFinalReporGMouseClicked

    private void tblFinalReporGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblFinalReporGKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblFinalReporGKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        finalReportLoad();
        if(lblStatus.getText()=="Request"){
            lblStatus.setForeground(Color.BLUE);
        }else if(lblStatus.getText()=="Pending"){
            lblStatus.setForeground(Color.ORANGE);
        }else if(lblStatus.getText()=="Active"){
            lblStatus.setForeground(Color.GREEN);
        }else{
            lblStatus.setForeground(Color.RED);
        }
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void aproolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aproolActionPerformed
        finalReportLoad();
        //approllNic="n",approllBReg="n";
        /*if(!getFormNumber.getText().isEmpty()){
            approall();
        }else{
            JOptionPane.showMessageDialog(null,"Please Enter Form Number");
        }*/
        if(getFNumber.equals(approllNic) || getFNumber.equals(approllBReg)){
            approall();
        }else{
            approallUpdate(getFormNumber.getText());
        }
        
        
    }//GEN-LAST:event_aproolActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        businessDataUpdate(b_regNo.getText(),false);//update
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnDefaultValues2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefaultValues2ActionPerformed
        businessDataLoad(b_regNo.getText());//insert save
        basicLoanLoadBusiness(b_regNo.getText());
        finalReportLoad(b_regNo.getText());
    }//GEN-LAST:event_btnDefaultValues2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        clearBusinessData();
        b_regNo.setText("");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtAmountCustomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountCustomKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtAmountCustom.setEditable(true);            
        }else{
            txtAmountCustom.setEditable(false);
        }
    }//GEN-LAST:event_txtAmountCustomKeyPressed

    private void txtAmountCustomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountCustomKeyReleased

    }//GEN-LAST:event_txtAmountCustomKeyReleased

    private void txtPeriodYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeriodYearKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtPeriodYear.setEditable(true);            
        }else{
            txtPeriodYear.setEditable(false);
        }
    }//GEN-LAST:event_txtPeriodYearKeyPressed

    private void txtPeriodMonthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeriodMonthKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtPeriodMonth.setEditable(true);            
        }else{
            txtPeriodMonth.setEditable(false);
        }
    }//GEN-LAST:event_txtPeriodMonthKeyPressed

    private void txtPeriodWeekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeriodWeekKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtPeriodWeek.setEditable(true);            
        }else{
            txtPeriodWeek.setEditable(false);
        }
    }//GEN-LAST:event_txtPeriodWeekKeyPressed

    private void txtPeriodDayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeriodDayKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtPeriodDay.setEditable(true);            
        }else{
            txtPeriodDay.setEditable(false);
        }
    }//GEN-LAST:event_txtPeriodDayKeyPressed

    private void txtTermsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTermsKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtTerms.setEditable(true);            
        }else{
            txtTerms.setEditable(false);
        }
    }//GEN-LAST:event_txtTermsKeyPressed

    private void getFormNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_getFormNumberKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.lengthLimited(evt.getKeyChar(),8,getFormNumber.getText().length())){
            getFormNumber.setEditable(true);
        }else{
            getFormNumber.setEditable(false);
        } 
    }//GEN-LAST:event_getFormNumberKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(deleat==0){
            deleatePersonal();
            if(deleat==0){
                deleateBusiness();
                deleat=0;
            }else{
                deleat=0;
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void FinalSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FinalSearchKeyPressed
        if(sPLoan.isSelected()){
            ValidityCheck valid = new ValidityCheck();
            if(valid.lengthLimited(evt.getKeyChar(),11,FinalSearch.getText().length())){
                if(valid.onlyTypeDigits(evt.getKeyChar())){
                    FinalSearch.setEditable(true);            
                }else{
                    FinalSearch.setEditable(false);
                }
                
            }else{
                FinalSearch.setEditable(false);
            }
        }else{
            FinalSearch.setEditable(true);
            FinalSearch.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_FinalSearchKeyPressed

    private void FinalSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FinalSearchKeyReleased
        if(sPLoan.isSelected()){
            ValidityCheck valid = new ValidityCheck();
            if (!valid.checkNic(FinalSearch.getText())) {
                FinalSearch.setForeground(Color.red);
            }else {
                FinalSearch.setForeground(Color.GREEN);  
            }
        }else{
            //FinalSearch.setEditable(true);
            FinalSearch.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_FinalSearchKeyReleased

    private void sPLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sPLoanActionPerformed
        FinalSearch.setText("");
    }//GEN-LAST:event_sPLoanActionPerformed

    private void sBLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sBLoanActionPerformed
        FinalSearch.setText("");
    }//GEN-LAST:event_sBLoanActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        basicLoanDataInsertUpdate("update");
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        clearCustomerData();
        c_Nic.setText("");

    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnDefaultValues1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefaultValues1ActionPerformed

        String newNic="";
        if(c_Nic.getText().length()==9){newNic="19"+c_Nic.getText();
        }else{newNic=c_Nic.getText();}
        customerDataLoad(newNic);
        basicLoanLoad(newNic);
        clearAllTableData();
        tableRefresh(newNic,0);
        finalReportLoad(newNic);
        
        
        
    }//GEN-LAST:event_btnDefaultValues1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        customerDataUpdate(c_Nic.getText(), false);//update
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtGirantorHPhoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGirantorHPhoneKeyReleased
        ValidityCheck valid = new ValidityCheck();
        if (valid.checkMobilePhoneNumber(txtGirantorHPhone.getText())) {
            txtGirantorHPhone.setForeground(Color.GREEN);
        } else {
            txtGirantorHPhone.setForeground(Color.red);
        }
    }//GEN-LAST:event_txtGirantorHPhoneKeyReleased

    private void txtGirantorHPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGirantorHPhoneKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            txtGirantorHPhone.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),10,txtGirantorHPhone.getText().length())){
                txtGirantorHPhone.setEditable(true);
            }else{
                txtGirantorHPhone.setEditable(false);
            }
        }else{
            txtGirantorHPhone.setEditable(false);
        }
    }//GEN-LAST:event_txtGirantorHPhoneKeyPressed

    private void txtGirantorNicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGirantorNicKeyReleased
        ValidityCheck valid = new ValidityCheck();
        AgeChecking age=new AgeChecking();
        if (!valid.checkNic(txtGirantorNic.getText())) {
            txtGirantorNic.setForeground(Color.red);
        }else {
            txtGirantorNic.setForeground(Color.GREEN);
            if(age.checkAge(txtGirantorNic.getText())){
                txtGirantorNic.setForeground(Color.GREEN);

            }else{
                txtGirantorNic.setForeground(Color.red);          }
        }
        //lblageLimit.setText(age.getGetMsg());
    }//GEN-LAST:event_txtGirantorNicKeyReleased

    private void txtGirantorNicKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGirantorNicKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.lengthLimited(evt.getKeyChar(),11,txtGirantorNic.getText().length())){
            txtGirantorNic.setEditable(true);
        }else{
            txtGirantorNic.setEditable(false);
        }
    }//GEN-LAST:event_txtGirantorNicKeyPressed

    private void cYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cYearKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            cYear.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),4,cYear.getText().length())){
                cYear.setEditable(true);
            }else{
                cYear.setEditable(false);
            }
        }else{
            cYear.setEditable(false);
        }
    }//GEN-LAST:event_cYearKeyPressed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        customerDataUpdate(c_Nic.getText(), true);//data insert
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnUpdateAndSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateAndSaveActionPerformed

        String newNic;
        if(txtGirantorNic.getText().length()==9){
            newNic="19"+txtGirantorNic.getText();
        }else{
            newNic=txtGirantorNic.getText();
        }
        tableDataUpdate(newNic);
        clearAllTableData();
        tableRefresh(newNic,0);
    }//GEN-LAST:event_btnUpdateAndSaveActionPerformed

    private void btnOneColomRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOneColomRemoveActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblGuarantorsDetails.getModel();
        int getSelectedRowIndex[]=tblGuarantorsDetails.getSelectedRows();
        String Nic;
        int answer = JOptionPane.showConfirmDialog(null,"Do you want Deleate That Recode ?");//0 yes,1 no,2 cancel
        if(answer==0){
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                //java.sql.Connection conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro","root","");
                java.sql.Connection conn =DatabaseConnection.connect();
                for(Integer vc:getSelectedRowIndex){
                    Nic=model.getValueAt(vc,1).toString();
                    String sql = "DELETE FROM c_guarantor_details WHERE Guarantor_Nic='"+Nic+"' ";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                }
                // JOptionPane.showMessageDialog(null,"Data Delete Success..");
                conn.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        //String getSelect=(String) ratingName.getSelectedItem();

        String newNic;
        if(c_Nic.getText().length()==9){
            newNic="19"+c_Nic.getText();
        }else{
            newNic=c_Nic.getText();
        }

        clearAllTableData();
        tableRefresh(newNic,0);
    }//GEN-LAST:event_btnOneColomRemoveActionPerformed

    private void btnDefaultValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefaultValuesActionPerformed
        String newNic;
        if(c_Nic.getText().length()==9){
            newNic="19"+c_Nic.getText();
        }else{
            newNic=c_Nic.getText();
        }

        String Nic;
        if(txtGirantorNic.getText().length()==9){
            Nic="19"+txtGirantorNic.getText();
        }else{
            Nic=txtGirantorNic.getText();
        }

        tableRefresh(newNic,0);
    }//GEN-LAST:event_btnDefaultValuesActionPerformed

    private void clearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllActionPerformed
        clearAllTableData();
    }//GEN-LAST:event_clearAllActionPerformed

    private void clearAllAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_clearAllAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_clearAllAncestorAdded

    private void cAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cAddressActionPerformed

    private void c_NicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_NicKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_c_NicKeyTyped

    private void c_NicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_NicKeyReleased
        ValidityCheck valid = new ValidityCheck();
        AgeChecking age=new AgeChecking();
        if (!valid.checkNic(c_Nic.getText())) {
            c_Nic.setForeground(Color.red);
        }else {
            c_Nic.setForeground(Color.GREEN);
            if(age.checkAge(c_Nic.getText())){
                c_Nic.setForeground(Color.GREEN);
                cYear.setText(String.valueOf(age.getYear()));
            }else{
                c_Nic.setForeground(Color.red);
                cYear.setText("");            }
        }
        lblageLimit.setText(age.getGetMsg());
    }//GEN-LAST:event_c_NicKeyReleased

    private void c_NicKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_NicKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.lengthLimited(evt.getKeyChar(),11,c_Nic.getText().length())){
            c_Nic.setEditable(true);
        }else{
            c_Nic.setEditable(false);
        }
    }//GEN-LAST:event_c_NicKeyPressed

    private void c_NicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_NicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_NicActionPerformed

    private void c_NicFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_c_NicFocusGained

    }//GEN-LAST:event_c_NicFocusGained

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblGuarantorsDetails.getModel();

        String Gname, Gemail, GAddress, Hphone, Lphone, Nic,emp;
        int Hphone2, Lphone2;
        String newNic;
        if(c_Nic.getText().length()==9){
            newNic="19"+c_Nic.getText();
        }else{
            newNic=c_Nic.getText();
        }

        Gname = txtGirantorName.getText();
        Hphone = txtGirantorHPhone.getText();
        Lphone = txtGirantorLPhone.getText();
        GAddress = txtGirantorHomeAddress.getText();
        Gemail = txtGirantorEmail.getText();

        if(Hphone.length() >=1){
        }else{Hphone="0";}

        if(Lphone.length() >=1){
        }else{Lphone="0";}

        if(Gemail.length() >= 1){
        }else{txtGirantorEmail.setText(" ");}

        if(txtGirantorNic.getText().length()==9){
            Nic="19"+txtGirantorNic.getText();
        }else{
            Nic=txtGirantorNic.getText();
        }

        if (!Gname.isEmpty() && !Nic.isEmpty() && !GAddress.isEmpty() &&(!Hphone.isEmpty() || !Lphone.isEmpty())) {
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                //java.sql.Connection conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro","root","");
                java.sql.Connection conn =DatabaseConnection.connect();
                Hphone2 = Integer.valueOf(Hphone);
                Lphone2 = Integer.valueOf(Lphone);

                String quary="INSERT INTO c_guarantor_details(Guarantor_Name,Guarantor_Nic,Guarantor_Phone_Number,Guarantor_Land_Phone,Guarantor_Home_Address,Guarantor_Email,CustomerIdNumber) VALUES ('"+Gname+"','"+Nic+"','"+Hphone2+"','"+Lphone2+"','"+GAddress+"','"+Gemail+"','"+newNic+"')";
                PreparedStatement pre =conn.prepareStatement(quary);
                pre.execute();

                conn.close();
                JOptionPane.showMessageDialog(null,"Success !");

            }
            catch (Exception e) {
                System.out.println(e);
            }
        }else{
            if(Gname.isEmpty()){
                emp="Name";
            }else if(Nic.isEmpty()){
                emp="National Id Number";
            }else{
                emp="Address";
            }
            JOptionPane.showMessageDialog(null,"You can't Empty "+emp+" field");
            if(Hphone.isEmpty() && Lphone.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please add Your Mobile Phone Number Or Land Phone Number");
            }
        }
        clearAllTableData();
        tableRefresh(newNic,0);
        txtGirantorName.setText(" ");
        txtGirantorName.setText(" ");
        txtGirantorHPhone.setText(" ");
        txtGirantorLPhone.setText(" ");
        txtGirantorHomeAddress.setText(" ");
        txtGirantorEmail.setText(" ");
        

    }//GEN-LAST:event_btnSaveActionPerformed

    private void tblGuarantorsDetailsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGuarantorsDetailsKeyReleased
        selectedIndex = tblGuarantorsDetails.getSelectedRow();
    }//GEN-LAST:event_tblGuarantorsDetailsKeyReleased

    private void tblGuarantorsDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGuarantorsDetailsMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblGuarantorsDetails.getModel();   
        int selectedRowIndex=tblGuarantorsDetails.getSelectedRow();

        txtGirantorName.setText(model.getValueAt(selectedRowIndex,0).toString());
        txtGirantorNic.setText(model.getValueAt(selectedRowIndex,1).toString());
        txtGirantorHPhone.setText(model.getValueAt(selectedRowIndex,2).toString());
        txtGirantorLPhone.setText(model.getValueAt(selectedRowIndex,3).toString());
        txtGirantorHomeAddress.setText(model.getValueAt(selectedRowIndex,4).toString());
        txtGirantorEmail.setText(model.getValueAt(selectedRowIndex,5).toString());
        
        
    }//GEN-LAST:event_tblGuarantorsDetailsMouseClicked

    private void c_EmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_EmailKeyReleased
        ValidityCheck valid = new ValidityCheck();
        if (valid.checkEmail(c_Email.getText()) == false) {
            c_Email.setForeground(Color.red);
        } else {
            c_Email.setForeground(Color.GREEN);
        }
    }//GEN-LAST:event_c_EmailKeyReleased

    private void c_EmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_EmailKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.lengthLimited(evt.getKeyChar(),50,c_Email.getText().length())){
            c_Email.setEditable(true);
        }else{
            c_Email.setEditable(false);
        }
    }//GEN-LAST:event_c_EmailKeyPressed

    private void c_Land_Phone_NumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_Land_Phone_NumberKeyReleased
        ValidityCheck valid = new ValidityCheck();
        if (valid.checkLandPhoneNumber(c_Land_Phone_Number.getText()) == false) {
            c_Land_Phone_Number.setForeground(Color.red);
        } else {
            c_Land_Phone_Number.setForeground(Color.GREEN);
        }
    }//GEN-LAST:event_c_Land_Phone_NumberKeyReleased

    private void c_Land_Phone_NumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_Land_Phone_NumberKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            c_Land_Phone_Number.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),10,c_Land_Phone_Number.getText().length())){
                c_Land_Phone_Number.setEditable(true);
            }else{
                c_Land_Phone_Number.setEditable(false);
            }
        }else{
            c_Land_Phone_Number.setEditable(false);
        }
    }//GEN-LAST:event_c_Land_Phone_NumberKeyPressed

    private void c_Land_Phone_NumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_Land_Phone_NumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_Land_Phone_NumberActionPerformed

    private void c_Mobile_NumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_Mobile_NumberKeyReleased
        ValidityCheck valid = new ValidityCheck();
        if (valid.checkMobilePhoneNumber(c_Mobile_Number.getText())) {
            c_Mobile_Number.setForeground(Color.GREEN);
        } else {
            c_Mobile_Number.setForeground(Color.red);
        }
    }//GEN-LAST:event_c_Mobile_NumberKeyReleased

    private void c_Mobile_NumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_Mobile_NumberKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            c_Mobile_Number.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),10,c_Mobile_Number.getText().length())){
                c_Mobile_Number.setEditable(true);
            }else{
                c_Mobile_Number.setEditable(false);
            }
        }else{
            c_Mobile_Number.setEditable(false);
        }
    }//GEN-LAST:event_c_Mobile_NumberKeyPressed

    private void c_Mobile_NumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_Mobile_NumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_Mobile_NumberActionPerformed

    private void cMariSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cMariSingleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cMariSingleActionPerformed

    private void cMariMarriedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cMariMarriedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cMariMarriedActionPerformed

    private void femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleActionPerformed

    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maleActionPerformed

    private void cDurMonthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cDurMonthKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            cDurMonth.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),2,cDurMonth.getText().length())){
                cDurMonth.setEditable(true);
            }else{
                cDurMonth.setEditable(false);
            }
        }else{
            cDurMonth.setEditable(false);
        }
        
        
    }//GEN-LAST:event_cDurMonthKeyPressed

    private void comboNationalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNationalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboNationalityActionPerformed

    private void cDurYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cDurYearKeyPressed
        ValidityCheck valid = new ValidityCheck();
        if(valid.onlyTypeDigits(evt.getKeyChar())){
            cDurYear.setEditable(true);
            if(valid.lengthLimited(evt.getKeyChar(),2,cDurYear.getText().length())){
                cDurYear.setEditable(true);
            }else{
                cDurYear.setEditable(false);
            }
        }else{
            cDurYear.setEditable(false);
        }
    }//GEN-LAST:event_cDurYearKeyPressed

    private void cDurYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDurYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cDurYearActionPerformed

    private void comboFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFnameActionPerformed
        if(comboFname.getSelectedIndex()==0 || comboFname.getSelectedIndex()==1){//Rev Mr Mrs Miss
            male.setSelected(true);
        }else if(comboFname.getSelectedIndex()==2 || comboFname.getSelectedIndex()==3){
            female.setSelected(true);
        }
    }//GEN-LAST:event_comboFnameActionPerformed

    private void cMariDivorceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cMariDivorceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cMariDivorceActionPerformed

    private void cFullNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cFullNameKeyPressed

    }//GEN-LAST:event_cFullNameKeyPressed

    private void cFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cFullNameActionPerformed

    private void cYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cYearActionPerformed

    private void txtGirantorEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGirantorEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGirantorEmailActionPerformed

    private void clearAll1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_clearAll1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_clearAll1AncestorAdded

    private void clearAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAll1ActionPerformed
        txtGirantorName.setText(" ");
        txtGirantorNic.setText(" ");
        txtGirantorHPhone.setText(" ");
        txtGirantorLPhone.setText(" ");
        txtGirantorHomeAddress.setText(" ");
        txtGirantorEmail.setText(" ");
    }//GEN-LAST:event_clearAll1ActionPerformed

    private void getFormNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getFormNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_getFormNumberActionPerformed

    private void cmbRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRateActionPerformed
        
    }//GEN-LAST:event_cmbRateActionPerformed

    private void b_ExpMonthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_ExpMonthKeyReleased
        int c=Integer.parseInt(b_ExpMonth.getText());
        if(c>11){
            b_ExpMonth.setText("");
        }
    }//GEN-LAST:event_b_ExpMonthKeyReleased

    private void cDurMonthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cDurMonthKeyReleased
        int c=Integer.parseInt(cDurMonth.getText());
        if(c>11){
            cDurMonth.setText("");
        }
    }//GEN-LAST:event_cDurMonthKeyReleased

    private void txtRateCustomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRateCustomKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRateCustomKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FinalSearch;
    private javax.swing.JButton Save;
    private javax.swing.JButton aprool;
    private javax.swing.JRadioButton b_ANo;
    private javax.swing.JRadioButton b_AYes;
    private javax.swing.JTextField b_Add1;
    private javax.swing.JTextField b_Add2;
    private javax.swing.JTextField b_DurMonth;
    private javax.swing.JTextField b_DurYear;
    private javax.swing.JTextField b_Email;
    private javax.swing.JTextField b_ExpMonth;
    private javax.swing.JTextField b_ExpYear;
    private javax.swing.JTextField b_Hphone;
    private javax.swing.JComboBox<String> b_LocatOwner;
    private javax.swing.JComboBox<String> b_Location;
    private javax.swing.JTextField b_Lphone;
    private javax.swing.JTextField b_Name;
    private javax.swing.JTextField b_NatureBus;
    private javax.swing.JTextField b_PostCode;
    private javax.swing.JTextField b_SumInsured;
    private javax.swing.JTextField b_regNo;
    private javax.swing.JButton btnDefaultValues;
    private javax.swing.JButton btnDefaultValues1;
    private javax.swing.JButton btnDefaultValues2;
    private javax.swing.JButton btnOneColomRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnUpdateAndSave;
    private javax.swing.JTextField cAddress;
    private javax.swing.JComboBox<String> cComboReligion;
    private javax.swing.JTextField cDurMonth;
    private javax.swing.JTextField cDurYear;
    private javax.swing.JTextField cFullName;
    private javax.swing.JTextField cGSDiv;
    private javax.swing.JRadioButton cMariDivorce;
    private javax.swing.JRadioButton cMariMarried;
    private javax.swing.JRadioButton cMariSingle;
    private javax.swing.JRadioButton cMariWidow;
    private javax.swing.JTextField cPotalCode;
    private javax.swing.JTextField cYear;
    private javax.swing.JTextField c_Email;
    private javax.swing.JTextField c_Land_Phone_Number;
    private javax.swing.JTextField c_Mobile_Number;
    private javax.swing.JTextField c_Nic;
    private javax.swing.JButton clearAll;
    private javax.swing.JButton clearAll1;
    private javax.swing.JComboBox<String> cmbRate;
    private javax.swing.JComboBox<String> comboDay;
    private javax.swing.JComboBox<String> comboFname;
    private javax.swing.JComboBox<String> comboLoanAmount;
    private javax.swing.JComboBox<String> comboLoanBlance;
    private javax.swing.JComboBox<String> comboLoanNames;
    private javax.swing.JComboBox<String> comboMonth;
    private javax.swing.JComboBox<String> comboNationality;
    private javax.swing.JRadioButton female;
    private javax.swing.ButtonGroup gender;
    private javax.swing.JTextField getFormNumber;
    private javax.swing.ButtonGroup insurance;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblAvaofInsPol;
    private javax.swing.JLabel lblBAdd;
    private javax.swing.JLabel lblBLO;
    private javax.swing.JLabel lblBLocation;
    private javax.swing.JLabel lblBN;
    private javax.swing.JLabel lblBPost;
    private javax.swing.JLabel lblBReg;
    private javax.swing.JLabel lblDAtPresL;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblExpInBus;
    private javax.swing.JLabel lblLand;
    private javax.swing.JLabel lblNature;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSumofInsured;
    public javax.swing.JLabel lblageLimit;
    private javax.swing.JLabel lblc_add;
    private javax.swing.JLabel lblc_dob;
    private javax.swing.JLabel lblc_dur;
    private javax.swing.JLabel lblc_email;
    private javax.swing.JLabel lblc_gen;
    private javax.swing.JLabel lblc_gs;
    private javax.swing.JLabel lblc_land;
    private javax.swing.JLabel lblc_mari;
    private javax.swing.JLabel lblc_mobi;
    private javax.swing.JLabel lblc_name;
    private javax.swing.JLabel lblc_nat;
    private javax.swing.JLabel lblc_nic;
    private javax.swing.JLabel lblc_postal;
    private javax.swing.JLabel lblc_reli;
    private javax.swing.JLabel lblmob;
    private javax.swing.JComboBox<String> loanReciever;
    private javax.swing.JTabbedPane loanTabbed;
    private javax.swing.JRadioButton male;
    private javax.swing.ButtonGroup maridStatus;
    private javax.swing.JRadioButton sBLoan;
    private javax.swing.JRadioButton sPLoan;
    private javax.swing.JButton save;
    private javax.swing.ButtonGroup searchLoan;
    private javax.swing.JTable tblDis;
    private javax.swing.JTable tblFinalReporG;
    private javax.swing.JTable tblFinalReport;
    private javax.swing.JTable tblGuarantorsDetails;
    private javax.swing.JTextField txtAmountCustom;
    private javax.swing.JTextField txtGirantorEmail;
    private javax.swing.JTextField txtGirantorHPhone;
    private javax.swing.JTextField txtGirantorHomeAddress;
    private javax.swing.JTextField txtGirantorLPhone;
    private javax.swing.JTextField txtGirantorName;
    private javax.swing.JTextField txtGirantorNic;
    private javax.swing.JTextField txtPeriodDay;
    private javax.swing.JTextField txtPeriodMonth;
    private javax.swing.JTextField txtPeriodWeek;
    private javax.swing.JTextField txtPeriodYear;
    private javax.swing.JTextField txtRateCustom;
    private javax.swing.JTextField txtTerms;
    // End of variables declaration//GEN-END:variables
}
