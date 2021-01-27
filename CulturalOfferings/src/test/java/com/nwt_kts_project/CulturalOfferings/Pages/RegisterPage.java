package com.nwt_kts_project.CulturalOfferings.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private WebDriver driver;

    private LoginPage loginPage;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "href-register")
    private WebElement link;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "submit")
    private WebElement registerButton;

    public RegisterPage() {
        super();
    }

    public WebElement getLink() {
        return link;
    }

    public RegisterPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getRegisterBtn() {
        return registerButton;
    }

    public void ensureIsDisplayedUsername() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("username")));
    }
    public void ensureIsClickableLoginBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("submit")));
    }


    public void ensureIsNotVisibleEmail() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("email")));
    }
}
