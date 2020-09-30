package service;


import configuration.CustomAuthenticationSuccessHandler;
import configuration.DispatcherInitializer;
import configuration.Filter;
import configuration.MvcConfig;
import dao.UserDao;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CustomAuthenticationSuccessHandler.class, DispatcherInitializer.class, Filter.class,
        MvcConfig.class})
@WebAppConfiguration()
@ComponentScan(basePackages = {"service", "dao", "configuration", "valid"})
public class UserServiceImplTest {


    private final UserService userService = Mockito.mock(UserService.class);

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void UserServiceTest () {

        User user = new User();
        user.setUserName("admin");
        user.setLastName("last");
        user.setFirstName("first");
        user.setEmail("a@a.ru");

        when(userService.findByUserName("admin")).thenReturn(null);
        when(userService.findByUserName("")).thenReturn(null);

        User realUser = userDao.findUserByName("admin");
        assertNotNull("OK" , realUser);

        assertEquals("OK", user.getFirstName(), realUser.getFirstName());
        assertEquals("OK", user.getEmail(), realUser.getEmail());
        assertEquals("OK", user.getLastName(), realUser.getLastName());
        assertEquals("OK", user.getUserName(), realUser.getUserName());
    }
}