package com.epam.restaurant.dao.exception;

import com.epam.restaurant.dao.impl.UserSqlDao;
import com.epam.restaurant.entity.User;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.testng.annotations.Test;

/**
 * Test of DaoException thrown.
 */
public class DaoExceptionTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testDaoException() throws DaoException {
        UserSqlDao dao = new UserSqlDao();

        exception.expect(DaoException.class);
        User user = dao.getByLogin("");
    }

}