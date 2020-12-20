package com.nwt_kts_project.CulturalOfferings.repository;

import com.nwt_kts_project.CulturalOfferings.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.NEW_USER_EMAIL;
import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.NEW_USER_USERNAME;

import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.NEW_USER_PASSWORD;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class UserRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Before
    public void setUp() {
        entityManager.persist(new User(NEW_USER_EMAIL, NEW_USER_USERNAME, NEW_USER_PASSWORD));
    }

    @Test
    public void testFindByEmail() {
        User found = userRepository.findByEmail(NEW_USER_EMAIL);
        assertEquals(NEW_USER_EMAIL, found.getEmail());
    }
}
