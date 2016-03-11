package com.epam.restaurant.dao;

import java.io.Serializable;

/**
 * Root interface for persistance objects.
 * @param <PK> <PK> primary key type
 */
public interface Identified<PK extends Serializable> {

    /**
     * Return unique object identifier
     **/
    PK getId();
}