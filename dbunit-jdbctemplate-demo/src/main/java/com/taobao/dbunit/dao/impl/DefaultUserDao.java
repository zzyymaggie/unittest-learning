package com.taobao.dbunit.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.taobao.dbunit.dao.UserDao;
import com.taobao.dbunit.entity.User;
import org.springframework.jdbc.core.RowMapper;


public class DefaultUserDao extends BaseDao implements UserDao {

    private static String QUERY_BY_NICK = "select * from user where user.nick = ?";

    private static String REMOVE_USER = "delete from user where user.nick = ?";

    private static String INSERT_USER = "insert into user(nick,password) values(?, ?)";

    private static String UPDATE_USER = "update user set user.password = ? where user.nick = ?";

    @Override
    public User getUserByNick(String nick) {
        return (User) getJdbcTemplate().queryForObject(QUERY_BY_NICK, new Object[]{nick}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int index) throws SQLException {
                User user = new User();
                user.setNick(rs.getString("nick"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
    }

    @Override
    public void remove(String nick) {
        getJdbcTemplate().update(REMOVE_USER, new Object[]{nick});
    }

    @Override
    public void save(User user) {
        getJdbcTemplate().update(INSERT_USER, new Object[]{user.getNick(), user.getPassword()});
    }

    @Override
    public void update(User user) {
        getJdbcTemplate().update(UPDATE_USER, new Object[]{user.getPassword(), user.getNick()});
    }

}