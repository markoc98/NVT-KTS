package com.nwt_kts_project.CulturalOfferings.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.nwt_kts_project.CulturalOfferings.model.Newsletter;


import static com.nwt_kts_project.CulturalOfferings.constants.NewsletterConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class NewsletterServiceIntegrationTest {
	
	@Autowired
	private NewsletterService newsService;
	
	
	@Test
	public void testFindAllNews() {
		List<Newsletter> newsList = newsService.findAll();
		assertEquals(1, newsList.size());
		
	}
	
	@Test
	public void testFindById() {
		Newsletter news = newsService.findOne(DB_NEWS_ID);
		assertEquals(DB_NEWS_ID, news.getId());
	}
	
	@Test
	public void testCreateNews() throws Exception {
		Newsletter n = new Newsletter(NEW_NEWS_TITLE,NEW_NEWS_CONTENT,NEW_NEWS_DATE);
		Newsletter nCreated = newsService.create(n);
		
		assertEquals(NEW_NEWS_TITLE, nCreated.getTitle());
	}
	
	@Test
	public void testUpdateNews() throws Exception{
		Newsletter n = new Newsletter(NEW_NEWS_TITLE,NEW_NEWS_CONTENT,NEW_NEWS_DATE);
		Newsletter nCreated = newsService.update(n,DB_DEL_NEWS_ID);
		
		assertEquals(NEW_NEWS_TITLE, nCreated.getTitle());
	}
	
	@Test
	public void testDeleteNews() throws Exception {
		newsService.delete(DB_DEL_NEWS_ID);
		
		Newsletter savedNews = new Newsletter(DB_DEL_NEWS_TITLE,DB_DEL_NEWS_CONTENT,DB_DEL_NEWS_DATE);
		savedNews.setId(DB_DEL_NEWS_ID);
	}
	

}
