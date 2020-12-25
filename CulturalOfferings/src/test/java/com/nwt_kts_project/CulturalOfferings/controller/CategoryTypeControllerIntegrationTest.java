package com.nwt_kts_project.CulturalOfferings.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nwt_kts_project.CulturalOfferings.dto.CategoryTypeDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserLoginDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserTokenStateDTO;
import com.nwt_kts_project.CulturalOfferings.model.CategoryType;
import com.nwt_kts_project.CulturalOfferings.service.CategoryTypeService;

import static com.nwt_kts_project.CulturalOfferings.constants.CategoryTypeConstants.*;
import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.DB_ADMIN_PASSWORD;
import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.DB_ADMIN_USERNAME;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CategoryTypeControllerIntegrationTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private CategoryTypeService categoryTypeService;
	
    private String accessToken;
    
    public void login(String username, String password) {

        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/api/auth/login",
                new UserLoginDTO(username, password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }
    
    @Test
    public void testGetCategoryType() {
    	
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

    	HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        
        ResponseEntity<CategoryTypeDTO> responseEntity =
                restTemplate.exchange("/api/categoryTypes/1", HttpMethod.GET, httpEntity, CategoryTypeDTO.class);

        CategoryTypeDTO categoryType = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(categoryType);
        assertEquals(DB_CATEGORY_TYPE, categoryType.getName());
    }
    
    @Test
    public void testGetAllCategoryTypes() throws Exception {
    	
    	login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        
        ResponseEntity<CategoryTypeDTO[]> responseEntity =
                restTemplate.exchange("/api/categoryTypes/", HttpMethod.GET, httpEntity, CategoryTypeDTO[].class);

        CategoryTypeDTO[] categoryTypes = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(DB_CATEGORY_TYPES_COUNT, categoryTypes.length - 1);
        //assertEquals(DB_CATEGORY_TYPE, categoryTypes[0].getName());
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testCreateCategoryType() throws Exception {
    	
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new CategoryTypeDTO(null, NEW_CATEGORY_TYPE), headers);
        
        ResponseEntity<CategoryTypeDTO> responseEntity =
                restTemplate.exchange("/api/categoryTypes/catId/1", HttpMethod.POST, httpEntity, CategoryTypeDTO.class);

        CategoryTypeDTO categoryType = responseEntity.getBody();
        
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(categoryType);
        assertEquals(NEW_CATEGORY_TYPE, categoryType.getName());

    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateCategoryType() throws Exception {
    	
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new CategoryTypeDTO(2L, DB_NEW_CATEGORY_TYPE), headers);

        ResponseEntity<CategoryTypeDTO> responseEntity =
                restTemplate.exchange("/api/categoryTypes/catId/2/id/2", HttpMethod.PUT, httpEntity, CategoryTypeDTO.class);

        CategoryTypeDTO categoryType = responseEntity.getBody();

        // provera odgovora servera
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(categoryType);
        
        assertEquals(DB_CATEGORY_TYPE_ID2, categoryType.getId());
        assertEquals(DB_NEW_CATEGORY_TYPE, categoryType.getName());

        // provera da li je izmenjen slog u bazi
        CategoryType dbCategoryType = categoryTypeService.findOne(DB_CATEGORY_TYPE_ID2);
        
        assertEquals(DB_CATEGORY_TYPE_ID2, categoryType.getId());
        assertEquals(DB_NEW_CATEGORY_TYPE, categoryType.getName());

        // vracanje podatka na staru vrednost
        dbCategoryType.setName(DB_CATEGORY_TYPE2);
        categoryTypeService.update(dbCategoryType, dbCategoryType.getId(), 2L);
    }
    
    @Test
    //@Transactional
    //@Rollback(true)
    public void testDeleteCategoryType() throws Exception {
    	
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        // ubacimo kategoriju koju cemo brisati
        CategoryType categoryType = categoryTypeService.create(new CategoryType(NEW_CATEGORY_TYPE4), 1L);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(null,headers);
        
        // preuzmemo trenutni broj kategorija
        int size = categoryTypeService.findAll().size();

        // poziv REST servisa za brisanje
        String url = "/api/categoryTypes/" + categoryType.getId();
        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);

        // provera odgovora servera
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // mora biti jedan manje slog sada nego pre
        assertEquals(size - 1, categoryTypeService.findAll().size());
    }

}
