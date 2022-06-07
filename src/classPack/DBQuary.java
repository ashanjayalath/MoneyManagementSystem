package classPack;

import GetterSetter.Business;
import GetterSetter.BusinessLoanTempory;
import GetterSetter.Customers;
import GetterSetter.PersonalLoanComplete;
import GetterSetter.PersonalLoanTempory;
import GetterSetter.Rating;
import GetterSetter.RatingKey;
import GetterSetter.Shares;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


public class DBQuary extends DatabaseConnection{
    
    //All Share Countrollers
    public static String updateSharesData(Shares share,ArrayList<String> change){
        String txt = "";
        int count=0;
        while(change.size()>count){
            switch (change.get(count)){
                case "SH":
                    txt += String.format("share_holder=\'%f\' ",share.getHolder());
                    break;
                case "OW":
                    txt += String.format("owner_shares=\'%f\' ",share.getOwner());
                    break;
                case "OT":
                    txt +=String.format( "other_shares=\'%f\' " ,share.getOther());
                    break;
                case "TLP":
                    txt +=String.format( "total_loan_profit=\'%f\' " ,share.getOtherLoan());
                    break;
                case "TS":
                    txt +=String.format( "total_shares=\'%f\' " ,share.getTotal());
                    break;  
                case "TIM":
                    txt +=String.format( "total_ishu_money=\'%f\' " ,share.getIshuMoney());
                    break;                      
            }if(change.size()-1!=count){
                txt+=",";
            }
            count++;
        }
        return String.format("UPDATE micro_shares SET %S WHERE shares_call_id='share' ",txt);
    }
    public static void getSharesData(Shares share){
        try {
            java.sql.Connection conn =connect();
            String sql = "SELECT * FROM micro_shares WHERE shares_call_id='share' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                share.setTotal(rs.getDouble("total_shares"));
                share.setIshuMoney(rs.getDouble("total_ishu_money"));
                share.setHolder(rs.getDouble("share_holder"));
                share.setOwner(rs.getDouble("owner_shares"));
                share.setOther(rs.getDouble("other_shares"));
                share.setOtherLoan(rs.getDouble("total_loan_profit"));   
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());;
        }
    }
    
    //All Personal loan Tempory Controllers
    public static void getPersonalLoanTemporary(PersonalLoanTempory personal,int loanId){
        try{
            String sql="SELECT * FROM c_personal_loan_tempory WHERE loanId='"+loanId+"' ";
            java.sql.Connection conn =connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();   
            while (rs.next()) {
                personal.setLoanName(rs.getString("loanName"));
                personal.setLoanType(rs.getString("loanType"));
                personal.setLoanStatus(rs.getString("loanStatus"));
                personal.setLoanAmount(rs.getDouble("loanAmount"));
                personal.setLoanTerms(rs.getInt("loanTerms"));
                personal.setLoanInterestRate(rs.getDouble("loanInterestRate"));
                personal.setCustomerIdNumber(rs.getString("CustomerIdNumber"));
                personal.setEmployId(rs.getString("EmployId"));
                personal.setFormNo(rs.getString("formNo"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
 
    } 
    public static void getPersonalLoanTemporaryTwo(PersonalLoanTempory personal){
        try{
            java.sql.Connection conn =connect();
            String sql = "SELECT loanStatus FROM c_personal_loan_tempory";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();  
            while (rs.next()) {
                personal.setLoanStatus(rs.getString("loanStatus"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    } 
    public static void getPersonalLoanTemporaryThree(PersonalLoanTempory personal,String loanId){
        try{
            String sql = "SELECT * FROM c_personal_loan_tempory WHERE CustomerIdNumber='"+loanId+"'";
            java.sql.Connection conn =connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();   
            while (rs.next()) {
                personal.setLoanName(rs.getString("loanName"));
                personal.setLoanType(rs.getString("loanType"));
                personal.setLoanStatus(rs.getString("loanStatus"));
                personal.setLoanAmount(rs.getDouble("loanAmount"));
                personal.setLoanTerms(rs.getInt("loanTerms"));
                personal.setLoanInterestRate(rs.getDouble("loanInterestRate"));
                personal.setCustomerIdNumber(rs.getString("CustomerIdNumber"));
                personal.setEmployId(rs.getString("EmployId"));
                personal.setFormNo(rs.getString("formNo"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
 
    } 
    public static void delelePersonalLoanTemporary(String id){
        try {
            java.sql.Connection conn =connect();
            String sql = "DELETE FROM c_personal_loan_tempory WHERE `CustomerIdNumber`='"+id+"' ";//WHERE Rating_Id_name='"+getData+"' 
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void updatePersonalLoanTemporary(String newNic){
        try {
            java.sql.Connection conn =connect();
            String sql="UPDATE c_personal_loan_tempory SET loanStatus='Request' WHERE CustomerIdNumber='"+newNic+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();

            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void updatePersonalLoanTemporaryTwo(String newNic){
        try {
            java.sql.Connection conn =connect();
            String sql="UPDATE c_personal_loan_tempory SET loanStatus='Request',comment='' WHERE CustomerIdNumber='"+newNic+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute(); 

            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void insertPersonalLoanTemporary(ArrayList<String> p){
        try {
            java.sql.Connection conn =connect();
            String sql = "INSERT INTO c_personal_loan_tempory(loanName,loanType,loanStatus,loanAmount,loanTerms,loanInterestRate,CustomerIdNumber,EmployId,formNo,comment) VALUES ('"+p.get(0)+"','"+p.get(1)+"','"+p.get(2)+"','"+Double.parseDouble(p.get(3))+"','"+Integer.parseInt(p.get(4))+"','"+Double.parseDouble(p.get(5))+"','"+p.get(6)+"','"+p.get(7)+"','"+p.get(8)+"','"+p.get(9)+"')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void updatePersonalLoanTemporaryThree(ArrayList<String> p,String newNic){
        try {
            java.sql.Connection conn =connect();
            String sql = "UPDATE c_personal_loan_tempory SET loanName='"+p.get(0)+"' ,loanType='"+p.get(1)+"',loanAmount='"+Double.parseDouble(p.get(3))+"', loanTerms='"+Integer.parseInt(p.get(4))+"', loanInterestRate='"+Double.parseDouble(p.get(5))+"' WHERE CustomerIdNumber='"+newNic+"' "; 
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();  

            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }    
        

    //All Business loan Tempory Controllers
    public static void getBusinessLoanTemporary(BusinessLoanTempory Business,int loanId){
        try{
            String sql="SELECT * FROM c_business_loan_tempory WHERE loanId='"+loanId+"' ";
            java.sql.Connection conn =connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();   
            while (rs.next()) {
                Business.setLoanName(rs.getString("loanName"));
                Business.setLoanType(rs.getString("loanType"));
                Business.setLoanAmount(rs.getDouble("loanAmount"));
                Business.setLoanTerms(rs.getInt("loanTerms"));
                Business.setLoanInterestRate(rs.getDouble("loanInterestRate"));
                Business.setBusinessRegisteredNo(rs.getString("BusinessRegisteredNo"));
                Business.setEmployId(rs.getString("EmployId"));
                Business.setFormNo(rs.getString("formNo"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        
        
        
        
        
    } 
    public static void getBusinessLoanTemporaryTwo(BusinessLoanTempory Business){
        try{
            java.sql.Connection conn =connect();
            String sql = "SELECT loanStatus FROM c_business_loan_tempory";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();  
            while (rs.next()) {
                Business.setLoanStatus(rs.getString("loanStatus"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    } 
    public static void getBusinessLoanTemporaryThree(BusinessLoanTempory Business,String loanId){
        try{
            String sql = "SELECT * FROM c_business_loan_tempory WHERE BusinessRegisteredNo='"+loanId+"'";
            java.sql.Connection conn =connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();   
            while (rs.next()) {
                Business.setLoanName(rs.getString("loanName"));
                Business.setLoanType(rs.getString("loanType"));
                Business.setLoanAmount(rs.getDouble("loanAmount"));
                Business.setLoanTerms(rs.getInt("loanTerms"));
                Business.setLoanInterestRate(rs.getDouble("loanInterestRate"));
                Business.setBusinessRegisteredNo(rs.getString("BusinessRegisteredNo"));
                Business.setEmployId(rs.getString("EmployId"));
                Business.setFormNo(rs.getString("formNo"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        
        
        
        
        
    } 
    public static void deleteBusinessLoanTemporary(int id){
        try{
            java.sql.Connection conn =connect();
            String sql = "DELETE FROM c_business_loan_tempory WHERE `loanId`='"+id+"' "; 
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
                        
        conn.close();            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void deleteBusinessLoanTemporaryTwo(String id){
        try{
            java.sql.Connection conn =connect();
            String sql = "DELETE FROM c_business_loan_tempory WHERE `BusinessRegisteredNo`='"+id+"' "; 
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            pst.close();
            conn.close();            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void updateBusinessLoanTemporary(String reg){
        try {
            java.sql.Connection conn =connect();
            String sql="UPDATE c_business_loan_tempory SET loanStatus='Request' WHERE BusinessRegisteredNo='"+reg+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();  

            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void updateBusinessLoanTemporaryTwo(ArrayList<String> b,String reg){
        try {
            java.sql.Connection conn =connect();
            String sql=" UPDATE `c_business_loan_tempory` SET `loanName`='"+b.get(0)+"',`loanType`='"+b.get(1)+"',`loanInterestRate`='"+Double.parseDouble(b.get(2))+"',`loanAmount`='"+Double.parseDouble(b.get(3))+"',`loanTerms`='"+Integer.parseInt(b.get(4))+"',`loanStatus`='"+b.get(5)+"' WHERE BusinessRegisteredNo='"+reg+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();  

            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }    
    public static void insertBusinessLoanTemporary(ArrayList<String> b,String reg){
        try {
            java.sql.Connection conn =connect();
            String sql="   INSERT INTO `c_business_loan_tempory`(`loanName`, `loanType`, `loanInterestRate`, `loanAmount`, `loanTerms`, `loanStatus`, `comment`, `BusinessRegisteredNo`, `EmployId`,formNo) VALUES ('"+b.get(0)+"','"+b.get(1)+"','"+Double.parseDouble(b.get(2))+"','"+Double.parseDouble(b.get(3))+"','"+Integer.parseInt(b.get(4))+"','"+b.get(5)+"','"+b.get(6)+"','"+b.get(7)+"','"+b.get(8)+"','"+b.get(9)+"')   ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }     
    
    
    
    //All Personal loan Complete Controllers
    public static void getPersonalLoanCompleteData(PersonalLoanComplete personal,String sql){
        try {
            java.sql.Connection conn =connect();
            PreparedStatement pst = conn.prepareStatement(sql);           
            ResultSet rs = pst.executeQuery();           
            while(rs.next()){
                personal.setMonthlyPayment(rs.getString("monthlyPayment"));
                personal.setPayment(rs.getString("payment"));
                personal.setCustomerIdOrBusinessReg(rs.getString("CustomerIdOrBusinessReg"));
                personal.setLoanCategory(rs.getString("loanCategory"));
                personal.setLoanIshuDate(rs.getDate("loanIshuDate"));
                personal.setLoanNumber(rs.getString("loanNumber"));
                personal.setLoanName(rs.getString("loanName"));
                personal.setLoanType(rs.getString("loanType"));
                personal.setLoanAmount(rs.getDouble("loanAmount"));
                personal.setLoanInterestRate(rs.getDouble("loanInterestRate"));
                personal.setLoanTerms(rs.getInt("loanTerms"));
                personal.setEmployId(rs.getString("EmployId"));  
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
     
    //Common insert and update
    public static void updateAndinsert(String sql){
        try {
            java.sql.Connection conn =connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Sucesss...");
            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
            
    } 
    
    
    
   
    //All Rating Key Controllers
    public static void deleteRatingKey(){
        try{
            java.sql.Connection conn =connect();
            String sql = "DELETE FROM micro_rating_keys";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void getRatingKey(RatingKey rate){
        try{   
            java.sql.Connection conn =connect();
            String sql = "SELECT * FROM micro_rating_keys";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                rate.setRating_Id_name(rs.getString("Rating_Id_name"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void insertRatingKey(RatingKey rate,String getName){
        try{   
            java.sql.Connection conn =connect();
            String sql1 = "INSERT INTO micro_rating_keys SELECT Rating_Id_name FROM micro_rating WHERE Rating_Id_name='"+getName+"' GROUP BY Rating_Id_name HAVING COUNT(*) > 1 & COUNT(*) <= 1";
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    
    
    //All Rating Controllers
    public static void deleteRating(String getData){
        try{
            java.sql.Connection conn =connect();
            String sql = "DELETE FROM micro_rating WHERE Rating_Id_name='"+getData+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            conn.close();
            JOptionPane.showMessageDialog(null,"Delete Succesfull..");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }        
    }
    public static void getRating(Rating rate){
        try{   
            java.sql.Connection conn =connect();
            String sql = "SELECT Rating_Id_name FROM micro_rating GROUP BY Rating_Id_name HAVING COUNT(*) > 1 & COUNT(*) <= 1";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                rate.setRating_Id_name(rs.getString("Rating_Id_name"));
            }
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    
    //All Customer Details Controllers
    public static void deleteCustomers(String id){
        try{
            java.sql.Connection conn =connect();
            String sql = "DELETE FROM c_details WHERE `CustomerIdNumber`='"+id+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();            
                
            pst.close();
            conn.close();            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void getCustomers(Customers customer,String id){
        try {
            java.sql.Connection conn =connect();
            
            String sql = "SELECT * FROM c_details WHERE CustomerIdNumber='"+id+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                  customer.setCustomerIdNumber(rs.getString("CustomerIdNumber"));
                  customer.setCustomerStatus(rs.getString("CustomerStatus"));
                  customer.setCustomerFullName(rs.getString("CustomerFullName"));
                  customer.setCustomerHandPhone(rs.getInt("CustomerHandPhone"));
                  customer.setCustomerLandPhone(rs.getInt("CustomerLandPhone"));
                  customer.setCustomerHomeAddress(rs.getString("CustomerHomeAddress"));
                  customer.setCustomerEmailAddress(rs.getString("CustomerEmailAddress"));
                  customer.setCustomerGender(rs.getString("CustomerGender"));
                  customer.setCustomerDOB(rs.getDate("CustomerDOB"));
                  customer.setCustomerReligion(rs.getString("CustomerReligion"));
                  customer.setCustomerNationality(rs.getString("CustomerNationality"));
                  customer.setCustomerGSDivisionNameNo(rs.getString("CustomerGSDivisionNameNo"));
                  customer.setCustomerPostalCode(rs.getString("CustomerPostalCode"));
                  customer.setCustomerDurationOfStay(rs.getString("CustomerDurationOfStay"));
                  customer.setCustomerMaritalStatus(rs.getString("CustomerMaritalStatus"));
            } 
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void insertCustomer(Customers customer,ArrayList<String> c){
        //CustomerIdNumber,CustomerStatus,CustomerFullName,CustomerHandPhone,CustomerLandPhone,CustomerHomeAddress,CustomerEmailAddress,CustomerGender,CustomerDOB,CustomerReligion,CustomerNationality,CustomerGSDivisionNameNo,CustomerPostalCode,CustomerDurationOfStay,CustomerMaritalStatus
        try {
            java.sql.Connection conn =connect();
            String quary="INSERT INTO c_details (CustomerIdNumber,CustomerStatus,CustomerFullName,CustomerHandPhone,CustomerLandPhone,CustomerHomeAddress,CustomerEmailAddress,CustomerGender,CustomerDOB,CustomerReligion,CustomerNationality,CustomerGSDivisionNameNo,CustomerPostalCode,CustomerDurationOfStay,CustomerMaritalStatus) VALUES ('"+c.get(0)+"','"+c.get(1)+"','"+c.get(2)+"','"+Integer.parseInt(c.get(3))+"','"+Integer.parseInt(c.get(4))+"','"+c.get(5)+"','"+c.get(6)+"','"+c.get(7)+"','"+Date.parse(c.get(8))+"','"+c.get(9)+"','"+c.get(10)+"','"+c.get(11)+"','"+c.get(12)+"','"+c.get(13)+"','"+c.get(14)+"')";
            PreparedStatement pre =conn.prepareStatement(quary);
            pre.execute();
            
            pre.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void updateCustomer(Customers customer,ArrayList<String> c){
        try {
            java.sql.Connection conn =connect();
            String sql = "UPDATE c_details SET CustomerStatus='"+c.get(1)+"',CustomerFullName='"+c.get(2)+"',CustomerHandPhone='"+Integer.parseInt(c.get(3))+"',CustomerLandPhone='"+Integer.parseInt(c.get(4))+"',CustomerHomeAddress='"+c.get(5)+"',CustomerEmailAddress='"+c.get(6)+"',CustomerGender='"+c.get(7)+"',CustomerDOB='"+Date.parse(c.get(8))+"',CustomerReligion='"+c.get(9)+"',CustomerNationality='"+c.get(10)+"',CustomerGSDivisionNameNo='"+c.get(11)+"',CustomerPostalCode='"+c.get(12)+"',CustomerDurationOfStay='"+c.get(13)+"',CustomerMaritalStatus='"+c.get(14)+"' WHERE CustomerIdNumber='"+c.get(0)+"' "; 
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();

            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }    
    
    //All Business Details Controllers
    public static void getBusiness(Business business,String getId){
        try {
            java.sql.Connection conn =connect();
            String sql = "SELECT * FROM `c_business` WHERE BusinessRegisteredNo='"+getId+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
         
                  business.setBusinessName(rs.getString("BusinessName"));
                  business.setBusinessRegisteredNo(rs.getString("BusinessRegisteredNo"));
                  business.setNatureofBusiness(rs.getString("NatureofBusiness"));
                  business.setBusinessAddress(rs.getString("BusinessAddress"));
                  business.setBusinessEmail(rs.getString("BusinessEmail"));
                  business.setBusinessLocationOwnership(rs.getString("BusinessLocationOwnership"));
                  business.setDurationAtPresentLocation(rs.getString("DurationAtPresentLocation"));
                  business.setBusinessLocation(rs.getString("BusinessLocation"));
                  business.setExperienceInBusiness(rs.getString("ExperienceInBusiness"));
                  business.setAvailabilityofInsurancePolicy(rs.getString("AvailabilityofInsurancePolicy"));
                  business.setMobilePhoneNumber(rs.getInt("MobilePhoneNumber"));
                  business.setFixedPhoneNumber(rs.getInt("FixedPhoneNumber"));
                  business.setSumofInsured(rs.getDouble("SumofInsured"));
                  business.setBPostalCode(rs.getString("BPostalCode"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void updateBusiness(Business business,ArrayList<String> b){
        //BusinessName,BusinessRegisteredNo,NatureofBusiness,BusinessAddress,BusinessEmail,BPostalCode,BusinessLocationOwnership,DurationAtPresentLocation,MobilePhoneNumber,FixedPhoneNumber,BusinessLocation,ExperienceInBusiness,AvailabilityofInsurancePolicy,SumofInsured,EmployId
        try {
            java.sql.Connection conn =connect();
            String sql = " UPDATE c_business SET BusinessName='"+b.get(0)+"',NatureofBusiness='"+b.get(2)+"',BusinessAddress='"+b.get(3)+"',BusinessEmail='"+b.get(4)+"',BPostalCode='"+b.get(5)+"',BusinessLocationOwnership='"+b.get(6)+"',DurationAtPresentLocation='"+b.get(7)+"',MobilePhoneNumber='"+Integer.parseInt(b.get(8))+"',FixedPhoneNumber='"+Integer.parseInt(b.get(9))+"',BusinessLocation='"+b.get(10)+"',ExperienceInBusiness='"+b.get(11)+"',AvailabilityofInsurancePolicy='"+b.get(12)+"',SumofInsured='"+Double.parseDouble(b.get(13))+"' ,EmployId='"+b.get(14)+"' WHERE BusinessRegisteredNo='"+b.get(1)+"' "; 
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            pst.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }    
    public static void insertBusiness(Business business,ArrayList<String> b){
        //BusinessName,BusinessRegisteredNo,NatureofBusiness,BusinessAddress,BusinessEmail,BPostalCode,BusinessLocationOwnership,DurationAtPresentLocation,MobilePhoneNumber,FixedPhoneNumber,BusinessLocation,ExperienceInBusiness,AvailabilityofInsurancePolicy,SumofInsured,EmployId
        try {
            java.sql.Connection conn =connect();
            String quary="INSERT INTO c_business (BusinessName,BusinessRegisteredNo,NatureofBusiness,BusinessAddress,BusinessEmail,BPostalCode,BusinessLocationOwnership,DurationAtPresentLocation,MobilePhoneNumber,FixedPhoneNumber,BusinessLocation,ExperienceInBusiness,AvailabilityofInsurancePolicy,SumofInsured,EmployId) VALUES ('"+b.get(0)+"','"+b.get(1)+"','"+b.get(2)+"','"+b.get(3)+"','"+b.get(4)+"','"+b.get(5)+"','"+b.get(6)+"','"+b.get(7)+"','"+b.get(8)+"','"+b.get(9)+"','"+b.get(10)+"','"+b.get(11)+"','"+b.get(12)+"','"+b.get(13)+"','"+b.get(14)+"')";
            PreparedStatement pre =conn.prepareStatement(quary);
            pre.execute();
            
            pre.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }    
    
    //All Guarantors Details Controllers
    public static void deleteGuarantors(String id){
        try{
            java.sql.Connection conn =connect();
            String sql = "DELETE FROM c_guarantor_details WHERE c_guarantor_details.CustomerIdNumber='"+id+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();           
                
            pst.close();
            conn.close();            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void deleteGuarantorsTwo(String Nic){
        try{
            java.sql.Connection conn =connect();
            String sql = "DELETE FROM c_guarantor_details WHERE Guarantor_Nic='"+Nic+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();           
                
            pst.close();
            conn.close();            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void updateGuarantors(ArrayList<String> p,String id){
        //Gname,Nic,Hphone,Lphone,GAddress,Gemail,id
        try {
            java.sql.Connection conn =connect();
            String sql = "UPDATE c_guarantor_details SET Guarantor_Name='"+p.get(0)+"',Guarantor_Nic='"+p.get(1)+"',Guarantor_Phone_Number='"+Integer.parseInt(p.get(2))+"',Guarantor_Land_Phone='"+Integer.parseInt(p.get(3))+"',Guarantor_Home_Address='"+p.get(4)+"',Guarantor_Email='"+p.get(5)+"' WHERE Guarantor_Nic='"+id+"' "; 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }    
    public static void insertGuarantors(ArrayList<String> g){
        try {
            java.sql.Connection conn =connect();
            
            String quary="INSERT INTO c_guarantor_details(Guarantor_Name,Guarantor_Nic,Guarantor_Phone_Number,Guarantor_Land_Phone,Guarantor_Home_Address,Guarantor_Email,CustomerIdNumber) VALUES ('"+g.get(0)+"','"+g.get(1)+"','"+Integer.parseInt(g.get(2))+"','"+Integer.parseInt(g.get(3))+"','"+g.get(4)+"','"+g.get(5)+"','"+g.get(6)+"')";
            PreparedStatement pre =conn.prepareStatement(quary);
            pre.execute();
            
            pre.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    

}
