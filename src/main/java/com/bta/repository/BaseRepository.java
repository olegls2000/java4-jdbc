package com.bta.repository;

import com.bta.domain.Country;

import java.util.List;

public interface BaseRepository<E> {
    List<E> findAll();

    boolean save(E entity);

    boolean update(E entity);

    boolean delete(long id);

    boolean deleteByName(String name);
}
