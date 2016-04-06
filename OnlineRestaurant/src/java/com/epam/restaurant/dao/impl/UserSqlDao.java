package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.restaurant.dao.name.ParameterName.*;

/**
 * Dao implementation for MySQL database and User entity.
 */
public class UserSqlDao extends AbstractSqlDao<User, Long> {

    private static final Logger LOGGER = Logger.getLogger(UserSqlDao.class);
    /**
     * Resource bundle with MySQL DB queries
     */
    private ResourceBundle dbBundle = ResourceBundle.getBundle(BUNDLE);

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private final static UserSqlDao instance = new UserSqlDao();

    public static GenericDao getInstance() {
        return instance;
    }

    private class PersistUser extends User {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString(USER_SELECT);
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString(USER_INSERT);
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString(USER_UPDATE);
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString(USER_DELETE);
    }

    @Override
    public User create() throws DaoException {
        User user = new User();
        return persist(user);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistUser student = new PersistUser();

                student.setId(rs.getInt(ID));
                student.setName(rs.getString(NAME));
                student.setSurname(rs.getString(SURNAME));
                student.setLogin(rs.getString(LOGIN));
                student.setHash(rs.getString(PASSWORD));
                student.setEmail(rs.getString(EMAIL));
                student.setRole(User.Role.valueOf(rs.getString(ROLE)));
                student.setPayCard(rs.getString(PAY_CARD_ID));
                result.add(student);
            }
            LOGGER.info("ResultSet parsed");
        } catch (SQLException e) {
            throw new DaoException("Exception in parseResultSet method", e);
        }

        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getLogin());
            statement.setString(4, object.getHash());
            statement.setString(5, object.getEmail());
            statement.setString(6, object.getRole().toString());
            statement.setString(7, object.getPayCard());
            statement.setLong(8, object.getId());
            LOGGER.info("Statement for update prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForUpdate method");
            throw new DaoException("Exception in prepareStatementForUpdate method", e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getLogin());
            statement.setString(4, object.getHash());
            statement.setString(5, object.getEmail());
            statement.setString(6, object.getRole().toString());
            statement.setString(7, object.getPayCard());
            LOGGER.info("Statement for insert prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForInsert method");
            throw new DaoException("Exception in prepareStatementForInsert method", e);
        }
    }

    /**
     * Get user from MySQL database with specific login
     * @param login user login
     * @return user with specific login or null if user with such login doesn't exist
     * @throws DaoException
     */
    public User getByLogin(String login) throws DaoException {
        List<User> list;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            String sql = dbBundle.getString(USER_WITH_LOGIN);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

            if (list == null || list.size() == 0) {
                return null;
            }
            if (list.size() > 1) {
                LOGGER.error("Received more than one record");
                throw new SQLException("Received more than one record");
            }

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("Exception");
            throw new DaoException("Exception");
        } finally {
            try {
                if (connection != null) {
                    pool.returnConnection(connection);
                    LOGGER.info("Connection returned successfully");
                }
            } catch (ConnectionPoolException e) {
                LOGGER.error("Exception during returning connection");
                throw new DaoException("Dao Exception", e);
            }
        }
        return list.iterator().next();
    }


}
