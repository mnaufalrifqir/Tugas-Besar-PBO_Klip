package Model;

import Database.DB_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class Penjual extends User{
    public Penjual(int idUser, String namaUser, String email,
            String password, String roles){
        super(idUser, namaUser, email, password, roles);
    }
    
    public ArrayList<User> getAllUser(){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            Statement stt = con.createStatement();
            ArrayList<User> arr = new ArrayList();
            ResultSet res;
        
            res = stt.executeQuery("SELECT idUser, namaUser, email, password, roles FROM user");
            while(res.next()){
                int idUser = res.getInt("idUser");
                String namaUser = res.getString("namaUser");
                String email = res.getString("email");
                String password = res.getString("password");
                String roles = res.getString("roles");
                User u = new User(idUser, namaUser, email, password, roles);
                arr.add(u);

            }
            return arr;
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public ArrayList<Product> getAllProduct(){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            ArrayList<Product> arr = new ArrayList();
            Statement stt = con.createStatement();
            ResultSet res;
            
            res = stt.executeQuery("SELECT idProduct, namaProduct, deskripsi, harga FROM product;");
            while(res.next()){
                int idProduct = res.getInt("idProduct");
                String nama = res.getString("namaProduct");
                String desc = res.getString("deskripsi");
                double price = res.getDouble("harga");
                Product p = new Product(idProduct, nama, desc, price);
                arr.add(p);
            }
            return arr;
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public void addProduct(String namaProduct, String deskripsi, double harga){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            Statement stt = con.createStatement();
        
            stt.executeUpdate("INSERT INTO product (namaProduct, deskripsi, harga)" + 
                    "VALUES ('"+namaProduct+"','"+deskripsi+"', '"+harga+"');");
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void deleteProduct(int id){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            Statement stt = con.createStatement();
        
            stt.executeUpdate("DELETE FROM product WHERE idProduct = '"+id+"';");
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
    
    
