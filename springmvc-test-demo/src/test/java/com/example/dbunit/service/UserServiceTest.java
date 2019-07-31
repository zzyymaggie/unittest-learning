package com.example.dbunit.service;

import com.example.dbunit.dao.UserDao;
import com.example.dbunit.entity.User;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * UserService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 1, 2019</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-servlet.xml"})
public class UserServiceTest {
    private UserDao userDao;

    @InjectMocks
    private UserService userService = new UserService();

    @Before
    public void before() throws Exception {
        userDao = mock(UserDao.class);
        ReflectionTestUtils.setField(userService, "userDao", userDao);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: findUserByUserName(String userName)
     */
    @Test
    public void testFindUserByUserName() {
        User user = new User();
        user.setNick("tom");
        user.setPassword("1234");
        when(userDao.getUserByNick("tom")).thenReturn(user);

        User u = userService.findUserByUserName("tom");
        assertNotNull(u);
        assertEquals(user.getNick(), u.getNick());

        when(userDao.getUserByNick("tom")).thenReturn(null);
        assertNull(userService.findUserByUserName("tom"));

        when(userDao.getUserByNick("tom")).thenThrow(new RuntimeException("db exception"));
        try{
            userService.findUserByUserName("tom");
        }catch(Exception e) {
            assertEquals(e.getMessage(), "db exception");
        }
    }
} 
