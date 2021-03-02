package com.bta;

import com.bta.domain.Country;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("C:\\Oleg\\java4-jdbc\\src\\main\\resources\\jdbc.properties");
        Properties properties = new Properties();
        properties.load(fis);

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            final String queryCountries = "select * from country";
            PreparedStatement preparedStatement = connection.prepareStatement(queryCountries);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Country> countries = new ArrayList<>();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                countries.add(new Country(id, name));
            }
            System.out.println(countries);
        } catch (SQLException exception) {
            System.out.println("Connection Failed");
        }
    }
}
