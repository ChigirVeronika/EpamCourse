package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
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

    private final static UserSqlDao instance = new UserSqlDao();

    public static GenericDao getInstance(){
        return instance;
    }

    private class PersistUser extends User {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString("USER.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("USER.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("USER.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("USER.DELETE");
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

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setLogin(rs.getString("login"));
                student.setHash(rs.getString("password"));
                student.setEmail(rs.getString("email"));
                student.setRole(User.Role.valueOf(rs.getString("role")));
                student.setPayCard(rs.getString("pay_card_id"));
                result.add(student);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception",e);
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
        } catch (SQLException e) {
            throw new DaoException("Exception",e);
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
        } catch (SQLException e) {
        throw new DaoException("Exception",e);
        }
    }

    public User getByName(String login) throws DaoException {
        List<User> list;
        try (Connection connection = pool.getConnection()) {

            String sql = dbBundle.getString("USER.WITH_LOGIN");
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
            throw new DaoException("Exception",e);
        }
        return list.iterator().next();
    }

    @Override
    public List<User> getAllFromRecord(Long key) throws DaoException {
        return null;
    }


}
