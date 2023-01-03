package Model;
import Database.DB_connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pembeli extends User{
    public Pembeli(int idUser, String namaUser, String email,
            String password, String roles){
        super(idUser, namaUser, email, password, roles);
    }
    
    public void addToCart(int id){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            Statement stt = con.createStatement();
           
            stt.executeUpdate("INSERT INTO carts (users_id, products_id)" + 
                    "VALUES ('"+this.getIdUser()+"','"+id+"');");
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public ArrayList<Product> getCart(){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            ArrayList<Product> arr = new ArrayList();
            ArrayList<Integer> arrID = new ArrayList();
            Statement stt = con.createStatement();
            
            ResultSet res = stt.executeQuery("SELECT products_id FROM carts WHERE users_id = '"+getIdUser()+"';");
            while(res.next()){
                int idProduct = res.getInt("products_id");
                arrID.add(idProduct);
            }
            for (Integer i : arrID){
                ResultSet res1 = stt.executeQuery("SELECT name, price, description FROM products WHERE id = '"+i+"';");
                while(res1.next()){
                    String nama = res1.getString("name");
                    double price = res1.getDouble("price");
                    String desc = res1.getString("description");
                    Product prod = new Product(i, nama, desc, price);
                    arr.add(prod);
                }
            }
            return arr;
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public ArrayList<Penjual> getAllPenjual(){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            ArrayList<Penjual> arr = new ArrayList();
            Statement stt = con.createStatement();
            ResultSet res;
            
            res = stt.executeQuery("SELECT * FROM users WHERE roles = 'ADMIN';");
            while(res.next()){
                int idPenjual = res.getInt("id");
                String nama = res.getString("name");
                String email = res.getString("email");
                String pass = res.getString("password");
                Penjual p = new Penjual(idPenjual, nama, email, pass, "ADMIN");
                arr.add(p);
            }
            return arr;
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public void delFromCart(int id){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            Statement stt = con.createStatement();
        
            stt.executeUpdate("DELETE FROM carts WHERE products_id = '"+id+"';");
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
