package com.nwt_kts_project.CulturalOfferings.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MapPage {
	
	private WebDriver driver;
	
	@FindBy(id = "expression")
	private WebElement searchLocation;
	
	@FindBy(id = "applyFilter")
	private WebElement searchButton;
	
	@FindBy(id = "selectCategory")
	private WebElement selectCategory;
	
	@FindBy(id = "myProfileRoute")
	private WebElement myProfileRouteButton;
	
	@FindBy(id = "signOut")
	private WebElement signOutButton;
	
	@FindBy(id = "tableOffer")
	private WebElement tableRowOnClick;
	
	public MapPage() {
		super();
	}

	public MapPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void ensureIsDisplayedLocation() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("expression")));
    }
	public void ensureIsClickableSearchButton() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("applyFilter")));
    }
	public void ensureIsClicableCategory() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("selectCategory")));
    }
	
	public void ensureIsPageDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("mapHomePage")));
	}

	public WebElement getSearchLocation() {
		return searchLocation;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getSelectCategory() {
		return selectCategory;
	}

	public WebElement getMyProfileRouteButton() {
		return myProfileRouteButton;
	}

	public WebElement getSignOutButton() {
		return signOutButton;
	}

	public WebElement getTableRowOnClick() {
		return tableRowOnClick;
	}
	
	

}
