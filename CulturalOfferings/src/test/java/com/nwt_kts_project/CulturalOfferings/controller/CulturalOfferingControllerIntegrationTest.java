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
import com.nwt_kts_project.CulturalOfferings.dto.CulturalOfferingDTO;

import javax.transaction.Transactional;

import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.DB_ADMIN_PASSWORD;
import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.DB_ADMIN_USERNAME;
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
        login(DB_ADMIN_USERNAME, DB_ADMIN_PASSWORD);

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
        login(DB_ADMIN_USERNAME, DB_ADMIN_PASSWORD);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<CulturalOfferingDTO[]> responseEntity = restTemplate.exchange("/api/cultural", HttpMethod.GET, httpEntity, CulturalOfferingDTO[].class);

        CulturalOfferingDTO[] cultOffs = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cultOffs.length, DB_CULTOFF_COUNT);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateCultOff()
    {
        login(DB_ADMIN_USERNAME, DB_ADMIN_PASSWORD);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new CulturalOfferingDTO(1L , DB_NEW_CULTOFF_LOCATION, DB_NEW_CULTOFF_NAME, DB_NEW_CULTOFF_DESCRIPTION));

        ResponseEntity<CulturalOfferingDTO> responseEntity = restTemplate.exchange("/api/cultural", HttpMethod.POST, httpEntity, CulturalOfferingDTO.class);

        CulturalOfferingDTO cultOff = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(cultOff);
        assertEquals(cultOff.getName(), DB_NEW_CULTOFF_NAME);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateCultOff()
    {
        login(DB_ADMIN_USERNAME, DB_ADMIN_PASSWORD);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new CulturalOfferingDTO(2L, DB_CULTOFF_LOCATION, DB_CULTOFF_NAME, DB_UPDATE_CULTOFF_DESCRIPTION));

        ResponseEntity<CulturalOfferingDTO> responseEntity = restTemplate.exchange("/api/cultural/update/2", HttpMethod.PUT, httpEntity, CulturalOfferingDTO.class);

        CulturalOfferingDTO cultOff = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(cultOff);

        assertEquals(DB_UPDATE_CULTOFF_DESCRIPTION, cultOff.getDescription());
        CulturalOffering cultOffering = cultOffService.findOne(DB_CULTOFF_ID);

        assertEquals(cultOffering.getName(), DB_CULTOFF_NAME);
    }
}
