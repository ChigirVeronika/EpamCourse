package com.epam.restaurant.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @param <T> object type
 * @param <PK> primary key type
 */
public interface GenericDao<T , PK extends Long> {

    T create() throws SQLException;

    T persist(T object)  throws SQLException;//returns new copy

    T getByPK(PK key) throws SQLException;

    void update(T object) throws SQLException;

    void delete(T object) throws SQLException;

    List<T> getAll() throws SQLException;
}
