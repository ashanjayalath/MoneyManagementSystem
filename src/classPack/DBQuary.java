package classPack;

import AdminForm.AdminCompanyShares;
import GetterSetter.Shares;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DBQuary extends DatabaseConnection{
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
            System.out.println(e);
        }
    }
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
    
    
    
    
    
    
    
    
    
    
    
}
