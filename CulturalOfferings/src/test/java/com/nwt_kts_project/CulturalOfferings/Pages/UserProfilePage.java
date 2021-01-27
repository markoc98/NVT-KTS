package com.nwt_kts_project.CulturalOfferings.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserProfilePage {

    private WebDriver driver;

    @FindBy(id = "subscription-table")
    private WebElement table;

    @FindBy(id = "home-button")
    private  WebElement homeButton;

    public UserProfilePage() {
        super();
    }


    public UserProfilePage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public void ensureIsDisplayedTable() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.id("subscription-table")));
    }


    public WebElement getTable() {
        return table;
    }

    public WebElement getHomeButton() {
        return homeButton;
    }
}
