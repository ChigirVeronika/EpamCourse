package com.epam.restaurant.dao;

import com.epam.restaurant.dao.exception.DaoException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for work with persistance objects
 * @param <T> object type
 * @param <PK> primary key type
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /**
     * Creates new record in database
     * @return created object
     * @throws DaoException
     */
    T create() throws DaoException;

    /**
     * Create new record from object
     * @param object create new record from this object
     * @return created object
     * @throws DaoException
     */
    T persist(T object) throws DaoException;//returns new copy

    /**
     * Return object with primary key 'key' or null
     * @param key primary key of object
     * @return object with primary key 'key' or null
     * @throws DaoException
     */
    T getByPK(PK key) throws DaoException;

    /**
     * Update object in database
     * @param object to update
     * @throws DaoException
     */
    void update(T object) throws DaoException;

    /**
     * Delete record from database
     * @param object to delete
     * @throws DaoException
     */
    void delete(T object) throws DaoException;

    /**
     * Return list of all items from database
     * @return list of all items
     * @throws DaoException
     */
    List<T> getAll() throws DaoException;
}
