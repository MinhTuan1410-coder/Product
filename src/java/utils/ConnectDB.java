/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trần Minh Tuấn
 */
public class ConnectDB {
    private String hostName;
    private String port;
    private String dbName;
    private String user;
    private String password;

    public ConnectDB() {
        this.hostName = "localhost";
        this.port = "1433";
        this.dbName = "ProductIntro";
        this.user = "sa";
        this.password = "12345";
    }

    public ConnectDB(String hostName, String port, String dbName, String user, String password) {
        this.hostName = hostName;
        this.port = port;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }

    public String getConnectionString() {
        return String.format("jdbc:sqlserver://%s:%s;DatabaseName=%s;user=%s;Password=%s;",
                this.hostName, this.port, this.dbName, this.user, this.password);
    }

    public Connection getConnection() {
        Connection status = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            status = DriverManager.getConnection(getConnectionString());
            System.out.println("Successfully connected to the Database Human");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
}
