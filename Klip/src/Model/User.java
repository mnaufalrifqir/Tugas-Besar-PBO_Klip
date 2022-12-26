package Model;

import Database.DB_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private int idUser;
    private String namaUser;
    private String email;
    private String password;
    private String roles;
    
    public User(int idUser, String namaUser, String email,
            String password, String roles){
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    
    public User(String namaUser, String email,
            String password){
        this.namaUser = namaUser;
        this.email = email;
        this.password = password;
    }
    
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    public User getOneUser(){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            Statement stt = con.createStatement();
            ResultSet res;
        
            res = stt.executeQuery("SELECT idUser, namaUser, email, password, roles FROM user");
            while(res.next()){
                int idUser = res.getInt("idUser");
                String namaUser = res.getString("namaUser");
                String email = res.getString("email");
                String password = res.getString("password");
                String roles = res.getString("roles");
                if (this.email.equals(email) && this.password.equals(password)){
                    User u = new User(idUser, namaUser, email, password, roles);
                    return u;
                }
            }
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public boolean register(){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            Statement stt = con.createStatement();
            ResultSet res;
            
            // Cek apakah email sudah ada di database
            res = stt.executeQuery("SELECT email FROM user");
            while(res.next()){
                String email = res.getString("email");
                if (email.equals(this.email)){
                    return false;
                }
            }
            if (true){
                stt.executeUpdate("INSERT INTO user (namaUser, email, password)" + 
                    "VALUES ('"+namaUser+"','"+email+"', '"+password+"');");
            }
        }catch (SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }
    
    public boolean login(){
        try{
            DB_connection c = new DB_connection();
            Connection con = c.getConnection();
            Statement stt = con.createStatement();
            ResultSet res;
            boolean isRegistered = false;
            String email, password;
            
            // Cek apakah user sudah terdaftar di database
            res = stt.executeQuery("SELECT email FROM user");
            while(res.next()){
                email = res.getString("email");
                if (this.email.equals(email)){
                    isRegistered = true;
                }
            }
            // Jika teregister, lanjut ke proses login
            if (isRegistered){
                res = stt.executeQuery("SELECT email, password FROM user");
                while(res.next()){
                    email = res.getString("email");
                    password = res.getString("password");
                    if (this.email.equals(email) && this.password.equals(password)){
                        return true;
                    }
                }
            }
        }catch (SQLException e){
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
