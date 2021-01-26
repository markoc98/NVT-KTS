package com.nwt_kts_project.CulturalOfferings.service;

import com.nwt_kts_project.CulturalOfferings.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;


    @Test
    public void testFindAllUser() {
        List<User> found = userService.findAll();
        assertEquals(DB_USERS_COUNT, found.size());
    }

    @Test
    public void testFindByIdUser() {
        User found = userService.findOne(DB_USER_ID);
        assertEquals(DB_USER_ID, found.getId());
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User(NEW_USER_EMAIL1, NEW_USER_USERNAME1, NEW_USER_PASSWORD1);
        User created = userService.create(user);

        assertEquals(NEW_USER_USERNAME1, created.getUsername());
    }

    @Test
    public void testDeleteUser() throws Exception {
        userService.delete(DB_DEL_USER_ID);

        User savedUser = new User(DB_DEL_USER_EMAIL,DB_DEL_USER_USERNAME,DB_DEL_USER_PASSWORD);
        savedUser.setId(DB_DEL_USER_ID);
    }


}
