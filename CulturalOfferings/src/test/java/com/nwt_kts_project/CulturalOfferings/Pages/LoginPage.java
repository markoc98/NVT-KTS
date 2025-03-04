package com.nwt_kts_project.CulturalOfferings.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "submit")
    private WebElement loginBtn;

    @FindBy(id = "href-login")
    private WebElement link;

    public LoginPage() {
        super();
    }

    public LoginPage(WebDriver driver) {
        super();
        this.driver = driver;
    }
    
    public WebElement getUsername() {
        return username;
    }
    public WebElement getLink() {
        return link;
    }


    public WebElement getPassword() {
        return password;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    public void ensureIsDisplayedUsername() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("username")));
    }
    public void ensureIsClickableLoginBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("submit")));
    }

    public void ensureIsNotVisibleUsername() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("username")));
    }


}