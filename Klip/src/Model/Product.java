package Model;

import Database.DB_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product {
    private int idProduct;
    private String namaProduct;
    private double harga;
    private String deskripsi;
    
    public Product(int idProduct, String namaProduct, 
            String deskripsi, double harga){
        this.idProduct = idProduct;
        this.namaProduct = namaProduct;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }
    
    public Product(String namaProduct, 
            String deskripsi, double harga){
        this.namaProduct = namaProduct;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public double getHarga() {
        return harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
