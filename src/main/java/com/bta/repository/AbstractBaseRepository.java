package com.bta.repository;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractBaseRepository  {

    protected Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("C:\\Oleg\\java4-jdbc\\src\\main\\resources\\jdbc.properties");
            properties.load(fis);
        } catch (Exception e) {
            System.out.println("Problem with jdbc.properties file");
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));
    }
}
