package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class AuthControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void signUpText(){

        HttpEntity<Object> httpEntity = new HttpEntity<Object>( new UserDTO(null,"usernameee@gmail.com","emaildsaadsads","password"));

        ResponseEntity<UserDTO> responseEntity =
                restTemplate.exchange("/api/auth/sign-up", HttpMethod.POST,httpEntity, UserDTO.class);

        System.out.println(responseEntity.getStatusCode());

    }
}
