package com.epam.restaurant.dao;

import com.epam.restaurant.dao.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

/**
 * @param <T> object type
 * @param <PK> primary key type
 */
public interface GenericDao<T , PK> {//todo id

    T create() throws DaoException;

    T persist(T object) throws DaoException;//returns new copy

    T getByPK(PK key) throws DaoException;

    void update(T object) throws DaoException;

    void delete(T object) throws DaoException;

    List<T> getAll() throws DaoException;

    T getByName(String name) throws DaoException;

    List<T> getAllFromRecord(PK key) throws DaoException;
}
