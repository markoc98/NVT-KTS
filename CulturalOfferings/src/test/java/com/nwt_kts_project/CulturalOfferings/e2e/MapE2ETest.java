package com.nwt_kts_project.CulturalOfferings.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nwt_kts_project.CulturalOfferings.Pages.LoginPage;
import com.nwt_kts_project.CulturalOfferings.Pages.MapPage;

public class MapE2ETest {
	
	public static final String HOME_PAGE = "http://localhost:4200";
	
	private WebDriver driver;
	
	private MapPage mapPage;
	private LoginPage loginPage;
	
	@Before
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();	
		mapPage = PageFactory.initElements(this.driver, MapPage.class);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		driver.get(HOME_PAGE + "/login");
		justWait();
		
		this.loginPage.getUsername().sendKeys("user");
		this.loginPage.getPassword().sendKeys("user");
		this.loginPage.getLoginBtn().click();
		
		justWait();
		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test 
	public void testShowMapPage() throws InterruptedException {
		driver.get(HOME_PAGE+ "/maps");
		justWait();
		
		this.mapPage.ensureIsPageDisplayed();
		
		assertEquals(HOME_PAGE + "/maps", this.driver.getCurrentUrl());
	}
	
	@Test
	public void testFetchDataForCulturalOffering() throws InterruptedException {
		
		driver.get(HOME_PAGE+ "/maps");
		justWait();
		
		this.mapPage.ensureIsPageDisplayed();
		
		justWait();
		
		Select categories = new Select(driver.findElement(By.id("selectCategory")));
		
		List<WebElement> offers = driver.findElements(By.id("tableOffers"));
		justWait();
		
		this.mapPage.getSearchLocation().sendKeys("Novi Sad");
		justWait();
		categories.selectByVisibleText("Institutions");
		categories.selectByIndex(2);
		justWait();
		this.mapPage.getSearchButton().click();
		
		justWait();
		
		assertEquals(HOME_PAGE + "/maps", this.driver.getCurrentUrl());
		assertNotEquals(0, offers.size());
	
	}
	
	@Test
	public void testNoInput() throws InterruptedException {
		
		driver.get(HOME_PAGE+ "/maps");
		justWait();
		
		this.mapPage.ensureIsPageDisplayed();
		justWait();
		
		
		
		Select categories = new Select(driver.findElement(By.id("selectCategory")));
		
		List<WebElement> offers = driver.findElements(By.id("tableOffers"));
		
		justWait();
		
		this.mapPage.getSearchLocation().sendKeys("");
		justWait();
		categories.selectByVisibleText("Events");
		categories.selectByIndex(0);
		justWait();
		this.mapPage.getSearchButton().click();
		
		justWait();
		Alert alert = driver.switchTo().alert();
		String txt = alert.getText();
		alert.accept();
		assertEquals(HOME_PAGE + "/maps", this.driver.getCurrentUrl());
		assertEquals(1, offers.size());
		assertEquals("Culutral Offering with given location does not exist.",txt);
	
	}
	
	@Test
	public void testWrongInput() throws InterruptedException {
		
		driver.get(HOME_PAGE+ "/maps");
		justWait();
		
		this.mapPage.ensureIsPageDisplayed();
		justWait();
		
		Select categories = new Select(driver.findElement(By.id("selectCategory")));
		categories.selectByVisibleText("Events");
		categories.selectByIndex(0);
		this.mapPage.getSearchLocation().sendKeys("11TESTTEST111");
		this.mapPage.getSearchButton().click();
		
		justWait();
		List<WebElement> offers = driver.findElements(By.id("tableOffers"));
		
		justWait();
		
		assertEquals(HOME_PAGE + "/maps", this.driver.getCurrentUrl());
		assertEquals(1, offers.size());
			
	}
	
	@Test
	public void testSwitchToDialog() throws InterruptedException {
		
		driver.get(HOME_PAGE+ "/maps");
		justWait();
		
		this.mapPage.ensureIsPageDisplayed();
		
		justWait();
		
		Select categories = new Select(driver.findElement(By.id("selectCategory")));
		
		List<WebElement> offers = driver.findElements(By.id("tableOffers"));
		justWait();
		
		this.mapPage.getSearchLocation().sendKeys("Novi Sad");
		justWait();
		categories.selectByVisibleText("Institutions");
		categories.selectByIndex(2);
		justWait();
		this.mapPage.getSearchButton().click();
		
		justWait();
		
		WebElement rows = driver.findElement(By.xpath("//*[@id='tableOffers']/tbody/tr[1]"));
		rows.click();
		justWait();
		
		this.mapPage.ensureIsDialogDisplayed();
		justWait();
		
		Boolean isPresent = driver.findElements(By.id("dialogWindow")).size() > 0;
		
		assertEquals(true, isPresent);
		
		
	}
		
	
	private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(3000);
        }
    }

}
