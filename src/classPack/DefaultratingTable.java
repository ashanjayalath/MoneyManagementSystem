package classPack;

import AdminForm.AdminLoanDetails;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class DefaultratingTable {
    AdminLoanDetails ad=new AdminLoanDetails();
        public void firest(){
      
        String rate="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro","root","");
            String sql = "Select * from micro_rating where Rating_Id='default'";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Double day = rs.getDouble("Day_By_Day");
                Double d_rate = rs.getDouble("D_rate");
                Double week_1 = rs.getDouble("Week_1");
                Double week_1_rate = rs.getDouble("W_1_rate");
                Double week_2 = rs.getDouble("Week_2");
                Double week_2_rate = rs.getDouble("W_2_rate");
                Double month = rs.getDouble("Month");
                Double month_rate = rs.getDouble("Month_rate");
                Double year = rs.getDouble("Year");
                Double year_rate = rs.getDouble("Year_rate");
                rate=rs.getString("Rating_Id");

                
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
