package com.nwt_kts_project.CulturalOfferings.e2e;

import static org.junit.Assert.assertEquals;

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



public class CulturalOfferingTableE2ETest {
	
	private WebDriver driver;
	private AdminHomePage adminHomePage;
	private LoginPage loginPage;
	
	
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
    
    @Test
    public void AddCulturalOfferingSuccess() throws InterruptedException {
    	
    	adminLogin();
    	
    	List<WebElement> rows = driver.findElements(By.xpath("//*[@id='cultOffTable']/tbody/tr/td[1]"));
    	
    	adminHomePage.ensureIsDisplayedAddCultOffButton();
    	justWait(1000);
    	
    	adminHomePage.getAddCultOffButton().click();
    	justWait(1000);
    	
    	adminHomePage.getCultOffNameAdd().sendKeys("Nov naziv add2");
        adminHomePage.getCultOffLocationAdd().sendKeys("Novi Sad");
        adminHomePage.getCultOffDescriptionAdd().sendKeys("Nov opis add");
        driver.findElement(By.id("cultOffCategoryAdd")).click();
        driver.findElement(By.id("Events")).click();

        
        adminHomePage.ensureIsDisplayedAddCultOffButtonOk();
        justWait(1000);
        
        adminHomePage.getAddCultOffButtonOk().click();
        
    	List<WebElement> newRows = driver.findElements(By.xpath("//*[@id='cultOffTable']/tbody/tr/td[1]"));
    	
    	assertEquals(newRows.size(), rows.size()+1);


        
    }
    
    @Test
    public void EditCulturalOfferingSuccess() throws InterruptedException {
    	
    	adminLogin();
        
        adminHomePage.ensureIsDisplayedEditCultOffButton();
        justWait(1000);
        
        adminHomePage.ensureIsClickableEditCultOffButton();
        justWait(1000);
        
        adminHomePage.getEditCultOffButton().click();
        justWait(1000);
        
        adminHomePage.getCultOffNameEdit().sendKeys("Nov naziv edit");
        adminHomePage.getCultOffLocationEdit().sendKeys("Novi Sad");
        adminHomePage.getCultOffDescriptionEdit().sendKeys("Nov opis edit");
        
        adminHomePage.ensureIsClickableEditCultOffButtonOk();
        justWait(1000);
        
        adminHomePage.getEditCultOffButtonOk().click();
        
    }
    
    @Test
    public void DeleteCulturalOfferingSuccess() throws InterruptedException{
    	
    	adminLogin();
    	
    	adminHomePage.ensureIsDisplayedCultOffTable();
    	justWait(1000);
    	
    	adminHomePage.ensureIsDisplayedDeleteCultOffButton();
    	justWait(1000);
    	
    	adminHomePage.ensureIsClickableDeleteCultOffButton();
    	justWait(1000);
    	
    	List<WebElement> rows = driver.findElements(By.xpath("//*[@id='cultOffTable']/tbody/tr/td[1]"));
    	
    	WebElement cultOffToDelete = driver.findElement(By.xpath("//*[@id='cultOffTable']/tbody/tr[4]/td[8]"));
    	cultOffToDelete.click();
    	justWait(2000);
    	
    	List<WebElement> newRows = driver.findElements(By.xpath("//*[@id='cultOffTable']/tbody/tr/td[1]"));
    	
    	assertEquals(newRows.size(), rows.size()-1);
    }
    
    private void justWait(int milliseconds) throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(milliseconds);
        }
    }

}
