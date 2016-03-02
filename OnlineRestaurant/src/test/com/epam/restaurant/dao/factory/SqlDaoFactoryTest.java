package com.epam.restaurant.dao.factory;

import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.impl.DishSqlDao;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Вероника on 02.03.2016.
 */
public class SqlDaoFactoryTest {

    @Test
    public void testGetDao() throws Exception {
        SqlDaoFactory sqlDaoFactory= new SqlDaoFactory();
        GenericDao dao = sqlDaoFactory.getDao(SqlDaoFactory.DaoType.DISH);
        assertEquals(dao, DishSqlDao.getInstance());
    }
}