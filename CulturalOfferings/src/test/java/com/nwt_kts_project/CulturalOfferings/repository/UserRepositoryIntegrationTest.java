package com.nwt_kts_project.CulturalOfferings.repository;

import com.nwt_kts_project.CulturalOfferings.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        User found = userRepository.findByEmail(DB_USER_EMAIL);

        assertEquals(DB_USER_EMAIL, found.getEmail());
    }
    @Test
    public void testFindByEmailDoesntExist() {
        User found = userRepository.findByEmail("nonexistantemail@gmaill.com");

        assertNull(found);
    }
}
