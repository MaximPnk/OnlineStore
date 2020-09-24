package dao;

import entity.User;

public interface UserDao {

    User findUserByName (String userName);

    void save(User user);
}
