package com.thuvien.config;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=QuanLyThuVien;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "integratedSecurity=true;";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(URL);
    }
    public static void main(String[] args) {
    try (Connection con = getConnection()) {
        if (con != null) {
            System.out.println("-> Kết nối SQL Server thành công!");
        }
    } catch (Exception e) {
        System.out.println("-> Kết nối thất bại: " + e.getMessage());
    }
}
    
}