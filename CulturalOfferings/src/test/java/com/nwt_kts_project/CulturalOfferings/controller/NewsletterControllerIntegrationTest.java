package com.nwt_kts_project.CulturalOfferings.controller;

import static com.nwt_kts_project.CulturalOfferings.constants.UserConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

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

import com.nwt_kts_project.CulturalOfferings.dto.NewsletterDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserLoginDTO;
import com.nwt_kts_project.CulturalOfferings.dto.UserTokenStateDTO;
import com.nwt_kts_project.CulturalOfferings.model.Newsletter;
import com.nwt_kts_project.CulturalOfferings.service.NewsletterService;
import static com.nwt_kts_project.CulturalOfferings.constants.NewsletterConstants.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class NewsletterControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private NewsletterService newsService;
	
	private String accessToken;
	
	public void login(String username, String password) {

        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/api/auth/login",
                new UserLoginDTO(username, password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();

    }
	
	@Test
	public void testGetNewsletter() {
		login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", accessToken);
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<NewsletterDTO> responseEntity =
                restTemplate.exchange("/api/newsletters/1", HttpMethod.GET, httpEntity, NewsletterDTO.class);

        NewsletterDTO newsletter = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newsletter.getTitle(), NEW_NEWS_TITLE);
		
	}
	
	@Test
	public void testGetAllNewsletters() {
		login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", accessToken);
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);


        ResponseEntity<NewsletterDTO[]> responseEntity =
                restTemplate.exchange("/api/newsletters/", HttpMethod.GET, httpEntity, NewsletterDTO[].class);

        NewsletterDTO[] newsletter = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newsletter.length, DB_NEWS_COUNT);
		
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testCreateNews() throws Exception { 
		login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
			
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new NewsletterDTO(555L, NEW_NEWS_TITLE,NEW_NEWS_CONTENT,NEW_NEWS_DATE),headers);
        
        ResponseEntity<NewsletterDTO> responseEntity = 
        		restTemplate.exchange("/api/newsletters/create/1", HttpMethod.POST,httpEntity,NewsletterDTO.class);
		
        NewsletterDTO newsdto = responseEntity.getBody();
        
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(newsdto);
        assertEquals(newsdto.getTitle(), NEW_NEWS_TITLE);
        assertEquals(newsdto.getContent(), NEW_NEWS_CONTENT);
        assertEquals(newsdto.getDate(), NEW_NEWS_DATE);
	}
	
	@Test
    @Transactional
    @Rollback(true)
    public void testUpdateNews() throws Exception {
		
		login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(new NewsletterDTO(DB_NEWS_TITLE,DB_NEWS_CONTENT,DB_NEWS_DATE), headers);
        ResponseEntity<NewsletterDTO> responseEntity =
                restTemplate.exchange("/api/newsletters/1",
                        HttpMethod.PUT, httpEntity,
                        NewsletterDTO.class);


        NewsletterDTO newsDto = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(newsDto);

        assertEquals(DB_NEWS_ID, newsDto.getId());
        assertEquals(DB_NEWS_TITLE, newsDto.getTitle());
        assertEquals(DB_NEWS_CONTENT, newsDto.getContent());
        assertEquals(DB_NEWS_DATE, newsDto.getDate());
        
        Newsletter dbNews = newsService.findOne(DB_NEWS_ID);
        assertEquals(DB_NEWS_ID, dbNews.getId());
        assertEquals(DB_NEWS_TITLE, dbNews.getTitle());
        assertEquals(DB_NEWS_CONTENT, dbNews.getContent());
        assertEquals(DB_NEWS_DATE, dbNews.getDate());
        
        //vrati na staro
        dbNews.setTitle(NEW_NEWS_TITLE);
        dbNews.setContent(NEW_NEWS_CONTENT);
        dbNews.setDate(NEW_NEWS_DATE);
        newsService.update(dbNews, dbNews.getId());

    }
	
	@Test
	 public void testDeleteNews() throws Exception {
		 
		 login(DB_ADMIN_USERNAME,DB_ADMIN_PASSWORD);
     
		 Newsletter newsletter = newsService.create(new Newsletter(NEW_NEWS_TITLE,NEW_NEWS_TITLE,NEW_NEWS_DATE));
		 
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Authorization", accessToken);
		 
		 HttpEntity<Object> httpEntity = new HttpEntity<Object>(null,headers);
		 List<Newsletter> newsList = newsService.findAll();
		 int size = newsService.findAll().size();

		 String url = "/api/newsletters/" + newsletter.getId();
		 ResponseEntity<Void> responseEntity =
				 restTemplate.exchange(url,
						 HttpMethod.DELETE, httpEntity,  Void.class);
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		 assertEquals(size - 1, newsService.findAll().size());
	    }

}
