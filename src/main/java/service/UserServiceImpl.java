package service;

import dao.RoleDao;
import dao.UserDao;
import entity.Role;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import valid.ValidUser;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User findByUserName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    @Transactional
    public void save(ValidUser validUser) {
        User user = new User();

        user.setUserName(validUser.getUserName());
        user.setPassword(passwordEncoder.encode(validUser.getPassword()));
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setEmail(validUser.getEmail());
        user.setRoles(Collections.singletonList(roleDao.findRoleByName("ROLE_CLIENT")));

        userDao.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findUserByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}





























