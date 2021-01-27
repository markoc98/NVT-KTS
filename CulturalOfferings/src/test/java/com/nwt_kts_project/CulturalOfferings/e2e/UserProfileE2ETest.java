package com.nwt_kts_project.CulturalOfferings.e2e;

import com.nwt_kts_project.CulturalOfferings.Pages.LoginPage;
import com.nwt_kts_project.CulturalOfferings.Pages.MapPage;
import com.nwt_kts_project.CulturalOfferings.Pages.RegisterPage;
import com.nwt_kts_project.CulturalOfferings.Pages.UserProfilePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserProfileE2ETest {

    private WebDriver driver;

    private UserProfilePage userProfilePage;

    private LoginPage loginPage;

    private MapPage mapPage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        userProfilePage = PageFactory.initElements(driver, UserProfilePage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mapPage = PageFactory.initElements(driver, MapPage.class);


    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void showProfilePageLoadedSuccess() throws InterruptedException {
       //odemo na login
        driver.get("http://localhost:4200/login");
        justWait(1000);
        loginPage.ensureIsDisplayedUsername();

        //loginujemo se kao user
        justWait(1000);
        loginPage.getUsername().sendKeys("user");
        loginPage.getPassword().sendKeys("user");

        justWait(1000);
        loginPage.ensureIsClickableLoginBtn();
        loginPage.getLoginBtn().click();

        justWait(1000);
        loginPage.ensureIsNotVisibleUsername();

        //redirectovani smo na home page gde je mapa
        //proverimo da li smo tamo
        assertEquals("http://localhost:4200/maps", driver.getCurrentUrl());
        justWait(2000);

        //ovo treba da bude mapPage.button.click da odemo tamo al za sada je ovo
        mapPage.getMyProfileRouteButton().click();

        justWait(1000);

        //proverimo da li smo stigli na user profile
        userProfilePage.ensureIsDisplayedTable();
        assertEquals("http://localhost:4200/user-profile",driver.getCurrentUrl());
    }


    @Test
    public void unsubscribeSuccess() throws InterruptedException {
        //odemo na login
        driver.get("http://localhost:4200/login");
        justWait(1000);
        loginPage.ensureIsDisplayedUsername();

        //loginujemo se kao user
        justWait(1000);
        loginPage.getUsername().sendKeys("user");
        loginPage.getPassword().sendKeys("user");

        justWait(1000);
        loginPage.ensureIsClickableLoginBtn();
        loginPage.getLoginBtn().click();

        justWait(3000);
        loginPage.ensureIsNotVisibleUsername();

        //redirectovani smo na home page gde je mapa
        //proverimo da li smo tamo
        assertEquals("http://localhost:4200/maps", driver.getCurrentUrl());
        justWait(1000);

        //asdkjlajsd
        //ovo treba da bude mapPage.button.click da odemo tamo al za sada je ovo
        driver.get("http://localhost:4200/user-profile");

        //proverimo da li smo stigli na user profile
        justWait(1000);
        assertEquals("http://localhost:4200/user-profile",driver.getCurrentUrl());

        //proverimo da se prikazala tabela
        justWait(1000);
        userProfilePage.ensureIsDisplayedTable();

        //izbrojimo kolko imamo subscriptiona
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='subscription-table']/tbody/tr/td[1]"));
        System.out.println("rows: " +  rows.size());

        justWait(3000);
        //uzmemo drugi red i drugu celiju gde se nalazi dugme
        WebElement rowToUnsub = driver.findElement(By.xpath("//*[@id='subscription-table']/tbody/tr[2]/td[2]"));
        rowToUnsub.click();
        justWait(3000);
        //uzmemo novi broj elemenata
        List<WebElement> newRows = driver.findElements(By.xpath("//*[@id='subscription-table']/tbody/tr/td[1]"));
        System.out.println("new rows: " +  newRows.size());
        justWait(1000);

        assertEquals(newRows.size(),rows.size()-1);

    }

    private void justWait(int milliseconds) throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(milliseconds);
        }
    }
}
