package com.bta.repository;

import com.bta.domain.City;
import com.bta.domain.Country;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CountryRepositoryImpl extends AbstractBaseRepository implements CountryRepository {

    @Override
    public List<Country> findAll() {
        final List<Country> result = new ArrayList<>();
        try (Connection connection = getConnection()) {
            final String queryCountries = "SELECT * FROM country cn LEFT JOIN city ct ON cn.capital = ct.id";
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(queryCountries);
            while (resultSet.next()) {
                final long countryId = resultSet.getLong(1);
                final String countryName = resultSet.getString(2);
                final long countryPopulation = resultSet.getLong(3);
                final long capitalId = resultSet.getLong(4);
                final Country country = new Country(countryId, countryName, countryPopulation);
                if (capitalId != 0l) {
                    final long cityId = resultSet.getLong(5);
                    final String cityName = resultSet.getString(6);
                    final long cityPopulation = resultSet.getLong(7);
                    final long cityArea = resultSet.getLong(8);
                    final City city = new City(cityId, cityName, cityPopulation, cityArea);
                    country.setCapital(city);
                }
                result.add(country);
            }
        } catch (SQLException exception) {
            System.out.println("Connection Failed");
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public boolean save(Country country) {
        int affectedRows = 0;
        try (Connection connection = getConnection()) {
            final String query = "INSERT INTO COUNTRY (name, population) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setLong(2, country.getPopulation());
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Connection Failed");
            throw new RuntimeException(exception);
        }
        return affectedRows == 1;
    }

    @Override
    public boolean update(Country country) {
        int affectedRows = 0;
        try (Connection connection = getConnection()) {
            final String query = "UPDATE country SET name=?, population=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setLong(2, country.getPopulation());
            preparedStatement.setLong(3, country.getId());
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Connection Failed");
            throw new RuntimeException(exception);
        }
        return affectedRows == 1;
    }

    @Override
    public boolean delete(long id) {
        int affectedRows = 0;
        try (Connection connection = getConnection()) {
            final String query = "DELETE FROM country WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Connection Failed");
            throw new RuntimeException(exception);
        }
        return affectedRows == 1;
    }

    @Override
    public boolean deleteByName(String name) {
        int affectedRows = 0;
        try (Connection connection = getConnection()) {
            //final String query = "DELETE FROM country WHERE name=" + name;
            final String query = "DELETE FROM country WHERE name=?";
            // DELETE FROM country WHERE name=Italy OR 1==1;
            // DELETE FROM country WHERE name='Italy OR 1==1'     ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Connection Failed");
            throw new RuntimeException(exception);
        }
        return affectedRows == 1;
    }



}
