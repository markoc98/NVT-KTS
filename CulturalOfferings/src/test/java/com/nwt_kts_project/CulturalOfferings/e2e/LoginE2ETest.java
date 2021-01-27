package com.nwt_kts_project.CulturalOfferings.e2e;

import static org.junit.Assert.assertEquals;
import com.nwt_kts_project.CulturalOfferings.Pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginE2ETest {

    private WebDriver driver;

    private LoginPage loginPage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void LogInTestSuccess() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait();

        loginPage.getEmail().sendKeys("user");
        loginPage.getPassword().sendKeys("user");
        loginPage.getLoginBtn().click();
        justWait();
        loginPage.ensureIsNotVisibleUsername();

        assertEquals("http://localhost:4200/maps", driver.getCurrentUrl());

    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(3000);
        }
    }
}
