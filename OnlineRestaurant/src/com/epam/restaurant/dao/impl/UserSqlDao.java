package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.DaoFactory;
import com.epam.restaurant.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Вероника on 09.02.2016.
 */
public class UserSqlDao extends AbstractSqlDao<User, Long> {

    /**
     * Resource bundle with MySQL DB queries
     */
    private ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();


    private class PersistUser extends User {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public UserSqlDao(DaoFactory parentFactory) {
        super(parentFactory);
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString("USERS.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("USERS.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("USERS.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("USERS.DELETE");
    }

    @Override
    public User create() throws SQLException {
        User user = new User();
        return persist(user);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<User> result = new LinkedList<>();

        while (rs.next()) {
            PersistUser student = new PersistUser();
            student.setId(rs.getInt("id"));
            student.setLogin(rs.getString("login"));
            student.setHash(rs.getString("password"));
            student.setEmail(rs.getString("email"));
            student.setRole(User.Role.valueOf(rs.getString("role")));
            result.add(student);
        }

        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getHash());
        statement.setString(3, object.getEmail());
        statement.setString(4, object.getRole().toString());
        statement.setInt(5, object.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getHash());
        statement.setString(3, object.getEmail());
        statement.setString(4, object.getRole().toString());
    }


    /**
     * Get user from MySQL database with specific login
     * @param login user login
     * @return user with specific login or null if user with such login doesn't exist
     * @throws SQLException
     */
    public User getByLogin(String login) throws SQLException {
        List<User> list;
        try (Connection connection = pool.getConnection()) {

            String sql = dbBundle.getString("USERS.WITH_LOGIN");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

            if (list == null || list.size() == 0) {
                return null;
            }
            if (list.size() > 1) {
                throw new SQLException("Received more than one record.");
            }

        }catch (ConnectionPoolException |SQLException e) {
            throw new SQLException(e);//TODO!!!
        }
        return list.iterator().next();
    }
}
