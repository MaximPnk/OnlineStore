package service;

import entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import valid.ValidUser;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(ValidUser validUser);
}
