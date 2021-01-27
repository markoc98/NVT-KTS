package com.nwt_kts_project.CulturalOfferings.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.nwt_kts_project.CulturalOfferings.dto.CategoryDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserLoginDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserTokenStateDTO;
import com.nwt_kts_project.CulturalOfferings.model.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import static com.nwt_kts_project.CulturalOfferings.constants.CategoryConstants.*;
import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.DB_ADMIN_PASSWORD;
import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.DB_ADMIN_USERNAME;

import org.springframework.transaction.annotation.Transactional;


import com.nwt_kts_project.CulturalOfferings.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CategoryControllerIntegrationTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private CategoryService categoryService;
	
    private String accessToken;
    
    public void login(String username, String password) {

        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/api/auth/login",
                new UserLoginDTO(username, password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }
    
    @Test
    public void testGetCategory() {
    	
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

    	HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        
        ResponseEntity<CategoryDTO> responseEntity =
                restTemplate.exchange("/api/categories/1", HttpMethod.GET, httpEntity, CategoryDTO.class);

        CategoryDTO category = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(category);
        assertEquals(DB_CATEGORY, category.getName());
    }
    
    @Test
    public void testGetAllCategories() {
    	
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<CategoryDTO[]> responseEntity =
                restTemplate.exchange("/api/categories/pageable?page=0&size=10", HttpMethod.GET, httpEntity, CategoryDTO[].class);

        CategoryDTO[] categories = responseEntity.getBody();
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(DB_CATEGORIES_COUNT, categories.length);
        //assertEquals(DB_CATEGORY, categories[0].getName());
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testCreateCategory() throws Exception {
    	
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new CategoryDTO(null, NEW_CATEGORY), headers);
        
        ResponseEntity<CategoryDTO> responseEntity =
                restTemplate.exchange("/api/categories", HttpMethod.POST, httpEntity, CategoryDTO.class);

        CategoryDTO category = responseEntity.getBody();
        
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(category);
        assertEquals(NEW_CATEGORY, category.getName());

    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateCategory() throws Exception {
    	
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new CategoryDTO(2L, DB_NEW_CATEGORY), headers);

        ResponseEntity<CategoryDTO> responseEntity =
                restTemplate.exchange("/api/categories/2", HttpMethod.PUT, httpEntity, CategoryDTO.class);

        CategoryDTO category = responseEntity.getBody();

        // provera odgovora servera
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(category);
        
        assertEquals(DB_CATEGORY_ID2, category.getId());
        assertEquals(DB_NEW_CATEGORY, category.getName());

        // provera da li je izmenjen slog u bazi
        Category dbCategory = categoryService.findOne(DB_CATEGORY_ID2);
        
        assertEquals(DB_CATEGORY_ID2, category.getId());
        assertEquals(DB_NEW_CATEGORY, category.getName());

        // vracanje podatka na staru vrednost
        dbCategory.setName(DB_CATEGORY2);
        categoryService.update(dbCategory, dbCategory.getId());
    }
    
    @Test
    //@Transactional
    //@Rollback(true)
    public void testDeleteCategory() throws Exception {
    	
        login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        // ubacimo kategoriju koju cemo brisati
        Category category = categoryService.create(new Category(NEW_CATEGORY4));
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(null,headers);
        
        // preuzmemo trenutni broj kategorija
        int size = categoryService.findAll().size();

        // poziv REST servisa za brisanje
        String url = "/api/categories/" + category.getId();
        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);

        // provera odgovora servera
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // mora biti jedan manje slog sada nego pre
        assertEquals(size - 1, categoryService.findAll().size());
    }

}
