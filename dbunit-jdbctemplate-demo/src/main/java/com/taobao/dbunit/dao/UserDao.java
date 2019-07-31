package com.taobao.dbunit.dao;

import com.taobao.dbunit.entity.User;

public interface UserDao {
    public User getUserByNick(String nick);

    public void remove(String nick);

    public void save(User user);
    public void update(User user);

}
