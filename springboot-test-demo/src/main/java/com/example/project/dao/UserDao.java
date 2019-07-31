package com.example.project.dao;

import com.example.project.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    public User getUserByNick(String nick);

    public int getMatchCount(@Param("nick") String nick, @Param("password") String password);

    public void remove(String nick);

    public void save(User user);

    public void update(User user);
}
