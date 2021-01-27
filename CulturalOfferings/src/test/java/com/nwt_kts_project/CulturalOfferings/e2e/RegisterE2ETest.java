package com.nwt_kts_project.CulturalOfferings.e2e;

import static org.junit.Assert.assertEquals;

import com.nwt_kts_project.CulturalOfferings.Pages.LoginPage;
import com.nwt_kts_project.CulturalOfferings.Pages.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterE2ETest {

    public String alertMessageSuccess = "Registered Successfully! Check your email for confirmation.";
    public String alertMessageAlreadyExists = "User with that username or email already exists.";
    private WebDriver driver;

    private RegisterPage registerPage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        registerPage = PageFactory.initElements(driver, RegisterPage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void SignUpTestSuccess() throws InterruptedException {
        driver.get("http://localhost:4200/register");

        justWait(1000);

        registerPage.getUsername().sendKeys("novidsssfuser");

        registerPage.getEmail().sendKeys("noviusesdssfdr@gmail.com");

        registerPage.getPassword().sendKeys("sifra");

        registerPage.getRegisterBtn().click();

        justWait(15000);

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals(alertMessageSuccess, text);

        alert.accept();

        registerPage.ensureIsNotVisibleEmail();
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

    }

    @Test
    public void alreadyHaveAccount() throws InterruptedException {
        driver.get("http://localhost:4200/register");

        justWait(1000);
        this.registerPage.getLink().click();
        justWait(1000);

        registerPage.ensureIsNotVisibleEmail();
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

    }

    @Test
    public void SignUpTestFailedAlreadyExists() throws InterruptedException {
        driver.get("http://localhost:4200/register");

        justWait(1000);

        registerPage.getUsername().sendKeys("user");

        registerPage.getEmail().sendKeys("user@gmail.com");

        registerPage.getPassword().sendKeys("sifra");

        registerPage.getRegisterBtn().click();

        justWait(1000);

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals(alertMessageAlreadyExists, text);

        alert.accept();
    }

    @Test
    public void SignUpTestFailedInputsMissing() throws InterruptedException {
        driver.get("http://localhost:4200/register");

        justWait(1000);

        registerPage.getUsername().sendKeys("aa");

        registerPage.getEmail().sendKeys("aa");

        registerPage.getPassword().sendKeys("");

        assertEquals(false,registerPage.getRegisterBtn().isEnabled());

    }
    private void justWait(int milliseconds) throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(milliseconds);
        }
    }
}
