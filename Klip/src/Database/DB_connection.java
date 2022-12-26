/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
/**
 *
 * @author haikal
 */
public class DB_connection {
    private static Connection con;
    
    public DB_connection(){
        try{
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klip_db?zeroDateTimeBehavior=convertToNull", "root", "");
            System.out.println("Connection Berhasil!");
        }catch (SQLException e){
            System.out.println("Connection Gagal!");
        }
    }
    
    public Connection getConnection(){
        return this.con;
    }
}
