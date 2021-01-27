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
    public void logInTestSuccessUser() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait(2000);

        loginPage.getUsername().sendKeys("user");
        loginPage.getPassword().sendKeys("user");

        loginPage.ensureIsClickableLoginBtn();
        loginPage.getLoginBtn().click();

        justWait(5000);

        loginPage.ensureIsNotVisibleUsername();

        assertEquals("http://localhost:4200/maps", driver.getCurrentUrl());

    }

    @Test
    public void logInTestSuccessAdmin() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait(2000);

        loginPage.getUsername().sendKeys("admin");
        loginPage.getPassword().sendKeys("admin");
        loginPage.getLoginBtn().click();
        justWait(3000);
        loginPage.ensureIsNotVisibleUsername();

        assertEquals("http://localhost:4200/admin-homepage", driver.getCurrentUrl());

    }

    @Test
    public void logInTestFailed() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait(2000);

        loginPage.getUsername().sendKeys("radenijeuser");
        loginPage.getPassword().sendKeys("losasifra");
        loginPage.getLoginBtn().click();
        justWait(2000);
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals("Wrong username or password!", text);

    }

    @Test
    public void dontHaveAccount() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait(1000);
        this.loginPage.getLink().click();
        justWait(1000);

        assertEquals("http://localhost:4200/register", driver.getCurrentUrl());

    }

    private void justWait(int milliseconds) throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(milliseconds);
        }
    }
}
