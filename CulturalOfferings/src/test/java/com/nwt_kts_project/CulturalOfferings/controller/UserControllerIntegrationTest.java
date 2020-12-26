package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.dto.UserDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserLoginDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserTokenStateDTO;
import com.nwt_kts_project.CulturalOfferings.model.User;
import com.nwt_kts_project.CulturalOfferings.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;

import java.util.List;

import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    private String accessToken;

    public void login(String username, String password) {

        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/api/auth/login",
                new UserLoginDTO(username, password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();

    }

    @Test
    public void testGetUser()
    {
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
         HttpHeaders headers = new HttpHeaders();
         headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
         HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<UserDTO> responseEntity =
                restTemplate.exchange("/api/users/1", HttpMethod.GET, httpEntity, UserDTO.class);

        UserDTO user = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user.getEmail(), DB_ADMIN_EMAIL);
    }
    @Test
    public void testGetUserFailed()
    {
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<UserDTO> responseEntity =
                restTemplate.exchange("/api/users/312123", HttpMethod.GET, httpEntity, UserDTO.class);

        UserDTO user = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(user);
    }

    @Test
    public void testGetAllUsers()
    {
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<UserDTO[]> responseEntity =
                restTemplate.exchange("/api/users/", HttpMethod.GET, httpEntity, UserDTO[].class);

        UserDTO[] users = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users.length, DB_USERS_COUNT);
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testCreateUser()
    {
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new UserDTO(null, NEW_USER_EMAIL, NEW_USER_USERNAME, NEW_USER_PASSWORD), headers);

        ResponseEntity<UserDTO> responseEntity =
                restTemplate.exchange("/api/users",
                        HttpMethod.POST, httpEntity,
                        UserDTO.class);


        UserDTO user = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(user);
        assertEquals(user.getEmail(), NEW_USER_EMAIL);
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testCreateUserAlreadyExistsEmail()
    {
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new UserDTO(null, DB_USER_EMAIL, NEW_USER_USERNAME, NEW_USER_PASSWORD), headers);

        ResponseEntity<UserDTO> responseEntity =
                restTemplate.exchange("/api/users",
                        HttpMethod.POST, httpEntity,
                        UserDTO.class);


        UserDTO user = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(user);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateUserAlreadyExistsUsername()
    {
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new UserDTO(null, NEW_USER_EMAIL, DB_USER_USERNAME, NEW_USER_PASSWORD), headers);

        ResponseEntity<UserDTO> responseEntity =
                restTemplate.exchange("/api/users",
                        HttpMethod.POST, httpEntity,
                        UserDTO.class);


        UserDTO user = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(user);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateUser() throws Exception {
        login(DB_ADMIN_USERNAME, DB_ADMIN_PASSWORD);

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new UserDTO(2L, DB_USER_USERNAME, DB_USER_USERNAME, DB_NEW_PASSWORD), headers);
        ResponseEntity<UserDTO> responseEntity =
                restTemplate.exchange("/api/users/update/2",
                        HttpMethod.PUT, httpEntity,
                        UserDTO.class);


        UserDTO user = responseEntity.getBody();
        // provera odgovora servera
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(user);

        assertEquals(DB_USER_ID, user.getId());
        assertEquals(DB_NEW_PASSWORD, user.getPassword());

        // provera da li je izmenjen slog u bazi

        User user1 = userService.findOne(DB_USER_ID);

        assertEquals(DB_USER_ID, user1.getId());
        assertEquals(DB_NEW_PASSWORD, user1.getPassword());

        // vracanje podatka na staru vrednost
        user1.setPassword(DB_USER_PASSWORD);
        userService.update(user1, user1.getId());
    }

  //  @Transactional
  //  @Rollback(true)
    @Test
    public void testDeleteUser() throws Exception {
        login(DB_ADMIN_USERNAME, DB_ADMIN_PASSWORD);

        // ubacimo usera kojeg cemo brisati
        User user = userService.create(new User(NEW_USER_EMAIL2, NEW_USER_USERNAME2, NEW_USER_PASSWORD2));



        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(null,headers);


        // preuzmemo trenutni broj usera
        List<User> users = userService.findAll();
        int size = userService.findAll().size();

        String url = "/api/users/delete/" + user.getId();
        //String url = "/api/users/delete/7";
        // poziv REST servisa za brisanje
        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(url,
                        HttpMethod.DELETE, httpEntity,  Void.class);
        //nece da nadji novo upisanog usera unutar responseEntityja a van ga nalazi ?!
        // provera odgovora servera
         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // mora biti jedan manje slog sada nego pre
         assertEquals(size - 1, userService.findAll().size());
    }
    @Test
    public void testDeleteUserDoesntExist() throws Exception {
        login(DB_ADMIN_USERNAME, DB_ADMIN_PASSWORD);


        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(null, headers);


        // preuzmemo trenutni broj usera
        List<User> users = userService.findAll();
        int size = userService.findAll().size();

        String url = "/api/users/delete/1321231312";
        //String url = "/api/users/delete/7";
        // poziv REST servisa za brisanje
        ResponseEntity<Void> responseEntity =
                restTemplate.exchange(url,
                        HttpMethod.DELETE, httpEntity, Void.class);
        //nece da nadji novo upisanog usera unutar responseEntityja a van ga nalazi ?!
        // provera odgovora servera
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // mora biti jedan manje slog sada nego pre
        assertEquals(size, userService.findAll().size());
    }
}
