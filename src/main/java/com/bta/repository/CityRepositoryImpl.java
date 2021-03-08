package com.bta.repository;

import com.bta.domain.City;

import java.util.List;

public class CityRepositoryImpl extends AbstractBaseRepository implements CityRepository {
    @Override
    public List<City> findAll() {
        return null;
    }

    @Override
    public boolean save(City entity) {
        return false;
    }

    @Override
    public boolean update(City entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean deleteByName(String name) {
        return false;
    }
}
