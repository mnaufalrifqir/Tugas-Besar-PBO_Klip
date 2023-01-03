package Database;
import java.sql.*;

public class DB_connection {
    private static Connection con;
    
    public DB_connection(){
        try{
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klip_pbo?zeroDateTimeBehavior=convertToNull", "root", "");
            System.out.println("Connection Berhasil!");
        }catch (SQLException e){
            System.out.println("Connection Gagal!");
        }
    }
    
    public Connection getConnection(){
        return this.con;
    }
}
