package dao;

import configuration.CustomAuthenticationSuccessHandler;
import configuration.DispatcherInitializer;
import configuration.Filter;
import configuration.MvcConfig;
import daoImpl.UserDaoImpl;
import entity.User;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;

import static org.mockito.Mockito.spy;
import static org.springframework.test.util.AssertionErrors.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CustomAuthenticationSuccessHandler.class, DispatcherInitializer.class, Filter.class,
        MvcConfig.class})
@WebAppConfiguration()
@ComponentScan(basePackages = {"service", "dao", "valid"})
public class UserDaoTest {

    @Mock
    private UserService userService;

    @Autowired
    private UserDao userDao;


    @Test
    @Transactional
    public void UserServiceTest () {
        User user = userDao.findUserByName("admin");
        assertNotNull("OK", user);
        assertEquals("User find OK", user.getUserName(), "admin");

        user = userDao.findUserByName("test");
        assertNull("OK" , user);
    }
}