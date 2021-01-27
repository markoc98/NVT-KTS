package com.nwt_kts_project.CulturalOfferings.controller;

import com.nwt_kts_project.CulturalOfferings.model.CulturalOffering;
import com.nwt_kts_project.CulturalOfferings.service.CulturalOfferingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.nwt_kts_project.CulturalOfferings.dto.*;

import javax.transaction.Transactional;

import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.*;
import static com.nwt_kts_project.CulturalOfferings.constants.CulturalOfferingConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CulturalOfferingService cultOffService;

    private String accessToken;

    public void login(String username, String password) {

        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/api/auth/login",
                new UserLoginDTO(username, password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();

    }

    @Test
    public void testGetCultOff()
    {
        login(DB_USER_USERNAME, DB_USER_PASSWORD);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<CulturalOfferingDTO> responseEntity = restTemplate.exchange("/api/cultural/1", HttpMethod.GET, httpEntity, CulturalOfferingDTO.class);

        CulturalOfferingDTO cultOff = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cultOff.getName(), DB_CULTOFF_NAME);
    }

    @Test
    public void testGetAllCultOff()
    {
        login(DB_USER_USERNAME, DB_USER_PASSWORD);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<CulturalOfferingDTO[]> responseEntity = restTemplate.exchange("/api/cultural/pageable?page=0&size=10", HttpMethod.GET, httpEntity, CulturalOfferingDTO[].class);

        CulturalOfferingDTO[] cultOffs = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cultOffs.length, CONTROLLER_FINDALL_COUNT-1);
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testCreateCultOff()
    {
        login(DB_ADMIN_USERNAME, DB_ADMIN_PASSWORD);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new CulturalOfferingDTO(null , CONTROLLER_CREATE_CULTOFF_LOCATION, CONTROLLER_CREATE_CULTOFF_NAME, CONTROLLER_CREATE_CULTOFF_DESCRIPTION), headers);

        ResponseEntity<CulturalOfferingDTO> responseEntity = restTemplate.exchange("/api/cultural", HttpMethod.POST, httpEntity, CulturalOfferingDTO.class);

        CulturalOfferingDTO cultOff = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(cultOff);
        assertEquals(cultOff.getName(), CONTROLLER_CREATE_CULTOFF_NAME);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateCultOff()
    {
        login(DB_ADMIN_USERNAME, DB_ADMIN_PASSWORD);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new CulturalOfferingDTO(null, CONTROLLER_CREATE_CULTOFF_LOCATION, CONTROLLER_CREATE_CULTOFF_NAME, CONTROLLER_UPDATE_CULTOFF_DESCRIPTION), headers);

        ResponseEntity<CulturalOfferingDTO> responseEntity = restTemplate.exchange("/api/cultural/update/6", HttpMethod.PUT, httpEntity, CulturalOfferingDTO.class);

        CulturalOfferingDTO cultOff = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(cultOff);

        assertEquals(CONTROLLER_UPDATE_CULTOFF_DESCRIPTION, cultOff.getDescription());
    }
}
