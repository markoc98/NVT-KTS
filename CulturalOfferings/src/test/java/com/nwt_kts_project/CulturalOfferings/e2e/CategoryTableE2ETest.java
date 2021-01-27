package com.nwt_kts_project.CulturalOfferings.e2e;

import com.nwt_kts_project.CulturalOfferings.Pages.AdminHomePage;
import com.nwt_kts_project.CulturalOfferings.Pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class CategoryTableE2ETest {
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
    public void AddCategorySuccess() throws InterruptedException {
        adminLogin();

        driver.findElement(By.id("mat-tab-label-0-1")).click();

        adminHomePage.ensureIsDisplayedAddCatButton();
        justWait(1000);

        adminHomePage.getCatAddButton().click();
        justWait(1000);

        driver.findElement(By.id("catDialogName")).sendKeys("Potpuno novi naziv");

        driver.findElement(By.id("catDropdown")).click();
        driver.findElement(By.id("Events")).click();

        driver.findElement(By.id("confirmCatDialog")).click();
        justWait(1000);

        assertEquals(false, isDialogPresent(driver));
    }

    @Test
    public void AddCategoryFail() throws InterruptedException {
        adminLogin();

        driver.findElement(By.id("mat-tab-label-0-1")).click();

        adminHomePage.ensureIsDisplayedAddCatButton();
        justWait(1000);

        adminHomePage.getCatAddButton().click();
        justWait(1000);

        driver.findElement(By.id("catDialogName")).sendKeys("Festivals");

        driver.findElement(By.id("catDropdown")).click();
        driver.findElement(By.id("Events")).click();

        driver.findElement(By.id("confirmCatDialog")).click();
        justWait(1000);

        assertEquals(true, isDialogPresent(driver));
    }

    private void justWait(int milliseconds) throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(milliseconds);
        }
    }

    private static boolean isDialogPresent(WebDriver driver) {
        try {
            driver.getTitle();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

}
