package com.nwt_kts_project.CulturalOfferings.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.nwt_kts_project.CulturalOfferings.Pages.AdminHomePage;
import com.nwt_kts_project.CulturalOfferings.Pages.LoginPage;

public class AdminHomePageE2ETest {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private AdminHomePage adminHomePage;
	
	@Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        adminHomePage = PageFactory.initElements(driver, AdminHomePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void adminLogin() throws InterruptedException{
    	
    	driver.get("http://localhost:4200/login");
        justWait(1000);
        loginPage.ensureIsDisplayedUsername();

        justWait(1000);
        loginPage.getUsername().sendKeys("admin");
        loginPage.getPassword().sendKeys("admin");

        justWait(1000);
        loginPage.ensureIsClickableLoginBtn();
        loginPage.getLoginBtn().click();

        justWait(3000);
        loginPage.ensureIsNotVisibleUsername();

        assertEquals("http://localhost:4200/admin-homepage", driver.getCurrentUrl());
    }
    
    public void adminLoginFunc() throws InterruptedException{
    	
    	driver.get("http://localhost:4200/login");
        justWait(1000);
        loginPage.ensureIsDisplayedUsername();

        justWait(1000);
        loginPage.getUsername().sendKeys("admin");
        loginPage.getPassword().sendKeys("admin");

        justWait(1000);
        loginPage.ensureIsClickableLoginBtn();
        loginPage.getLoginBtn().click();

        justWait(3000);
        loginPage.ensureIsNotVisibleUsername();

        assertEquals("http://localhost:4200/admin-homepage", driver.getCurrentUrl());
    }
    
    @Test
    public void doesCultOffTableShowDataSuccess() throws InterruptedException{
    	
    	adminLoginFunc();
    	    	
    	
		List<WebElement> cos = driver.findElements(By.id("cultOffTable"));
		justWait(1000);

    	
        assertEquals("http://localhost:4200/admin-homepage", driver.getCurrentUrl());
		assertNotEquals(0, cos.size());
    	
    }
    
    
    @Test
    public void doesCatTableShowDataSuccess() throws InterruptedException{
    	
    	adminLoginFunc();
    	
    	driver.findElement(By.id("mat-tab-label-0-1")).click();
    	
    	List<WebElement> cats = driver.findElements(By.id("categoryTable"));
		justWait(1000);
    	
        assertEquals("http://localhost:4200/admin-homepage", driver.getCurrentUrl());
		assertNotEquals(0, cats.size());
    	
    }
    
    
    @Test
    public void doesNewsTableShowData() throws InterruptedException{
    	
    	adminLoginFunc();
    	
    	driver.findElement(By.id("mat-tab-label-0-2")).click();
    	
    	
		List<WebElement> news = driver.findElements(By.id("newsletterTable"));
		justWait(1000);

    	
        assertEquals("http://localhost:4200/admin-homepage", driver.getCurrentUrl());
		assertNotEquals(0, news.size());
    	
    }
    
    private void justWait(int milliseconds) throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(milliseconds);
        }
    }

}
