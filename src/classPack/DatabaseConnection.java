package classPack;
import java.sql.*;
import javax.swing.JOptionPane;
public class DatabaseConnection {
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static final String CONN_STRING="jdbc:mysql://localhost:3306/micro";

    public static String EMP;
    
    public static Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
        }catch(SQLException e){
            System.err.println(e);
        }
        return conn;
    }

}
