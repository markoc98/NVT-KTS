package com.nwt_kts_project.CulturalOfferings.e2e;

import static org.junit.Assert.assertEquals;
import com.nwt_kts_project.CulturalOfferings.Pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    public void LogInTestSuccessUser() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait();

        loginPage.getUsername().sendKeys("user");
        loginPage.getPassword().sendKeys("user");

        loginPage.ensureIsClickableLoginBtn();
        loginPage.getLoginBtn().click();

        justWait();

        loginPage.ensureIsNotVisibleUsername();

        assertEquals("http://localhost:4200/maps", driver.getCurrentUrl());

    }

    @Test
    public void LogInTestSuccessAdmin() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait();

        loginPage.getUsername().sendKeys("admin");
        loginPage.getPassword().sendKeys("admin");
        loginPage.getLoginBtn().click();
        justWait();
        loginPage.ensureIsNotVisibleUsername();

        assertEquals("http://localhost:4200/admin-homepage", driver.getCurrentUrl());

    }

    @Test
    public void LogInTestFailed() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait();

        loginPage.getUsername().sendKeys("radenijeuser");
        loginPage.getPassword().sendKeys("losasifra");
        loginPage.getLoginBtn().click();
        justWait();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        assertEquals("Wrong username or password!", text);

    }
    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(3000);
        }
    }
}
