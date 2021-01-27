package com.nwt_kts_project.CulturalOfferings.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminHomePage {
	
	private WebDriver driver;
	
	//CULT OFF
	
	@FindBy(id = "cultOffTable")
    private WebElement cultOffTable;
	
	//add cult off
	
	@FindBy(id = "addCultOffButton")
    private WebElement addCultOffButton;
	
	@FindBy(id = "cultOffNameAdd")
    private WebElement cultOffNameAdd;
	
	@FindBy(id = "cultOffLocationAdd")
    private WebElement cultOffLocationAdd;
	
	@FindBy(id = "cultOffDescriptionAdd")
    private WebElement cultOffDescriptionAdd;
	
	@FindBy(id = "cultOffCategoryAdd")
    private WebElement cultOffCategoryAdd;
	
	@FindBy(id = "addCultOffButtonOk")
	private WebElement addCultOffButtonOk;
	
	@FindBy(id = "addCultOffButtonNo")
	private WebElement addCultOffButtonNo;
	
	//edit cult off
	
	@FindBy(id = "editCultOffButton")
    private WebElement editCultOffButton;
	
	@FindBy(id = "cultOffNameEdit")
    private WebElement cultOffNameEdit;
	
	@FindBy(id = "cultOffLocationEdit")
    private WebElement cultOffLocationEdit;
	
	@FindBy(id = "cultOffDescriptionEdit")
    private WebElement cultOffDescriptionEdit;
	
	@FindBy(id = "editCultOffButtonOk")
	private WebElement editCultOffButtonOk;
	
	@FindBy(id = "editCultOffButtonNo")
	private WebElement editCultOffButtonNo;
	
	// delete cult off
	
	@FindBy(id = "deleteCultOffButton")
    private WebElement deleteCultOffButton;
	
	// getters and setters for cult off

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getAddCultOffButton() {
		return addCultOffButton;
	}

	public void setAddCultOffButton(WebElement addCultOffButton) {
		this.addCultOffButton = addCultOffButton;
	}

	public WebElement getCultOffNameAdd() {
		return cultOffNameAdd;
	}

	public void setCultOffNameAdd(WebElement cultOffNameAdd) {
		this.cultOffNameAdd = cultOffNameAdd;
	}

	public WebElement getCultOffLocationAdd() {
		return cultOffLocationAdd;
	}

	public void setCultOffLocationAdd(WebElement cultOffLocationAdd) {
		this.cultOffLocationAdd = cultOffLocationAdd;
	}

	public WebElement getCultOffDescriptionAdd() {
		return cultOffDescriptionAdd;
	}

	public void setCultOffDescriptionAdd(WebElement cultOffDescriptionAdd) {
		this.cultOffDescriptionAdd = cultOffDescriptionAdd;
	}

	public WebElement getCultOffCategoryAdd() {
		return cultOffCategoryAdd;
	}

	public void setCultOffCategoryAdd(WebElement cultOffCategoryAdd) {
		this.cultOffCategoryAdd = cultOffCategoryAdd;
	}

	public WebElement getAddCultOffButtonOk() {
		return addCultOffButtonOk;
	}

	public void setAddCultOffButtonOk(WebElement addCultOffButtonOk) {
		this.addCultOffButtonOk = addCultOffButtonOk;
	}

	public WebElement getAddCultOffButtonNo() {
		return addCultOffButtonNo;
	}

	public void setAddCultOffButtonNo(WebElement addCultOffButtonNo) {
		this.addCultOffButtonNo = addCultOffButtonNo;
	}

	public WebElement getEditCultOffButton() {
		return editCultOffButton;
	}

	public void setEditCultOffButton(WebElement editCultOffButton) {
		this.editCultOffButton = editCultOffButton;
	}

	public WebElement getCultOffNameEdit() {
		return cultOffNameEdit;
	}

	public void setCultOffNameEdit(WebElement cultOffNameEdit) {
		this.cultOffNameEdit = cultOffNameEdit;
	}

	public WebElement getCultOffLocationEdit() {
		return cultOffLocationEdit;
	}

	public void setCultOffLocationEdit(WebElement cultOffLocationEdit) {
		this.cultOffLocationEdit = cultOffLocationEdit;
	}

	public WebElement getCultOffDescriptionEdit() {
		return cultOffDescriptionEdit;
	}

	public void setCultOffDescriptionEdit(WebElement cultOffDescriptionEdit) {
		this.cultOffDescriptionEdit = cultOffDescriptionEdit;
	}

	public WebElement getEditCultOffButtonOk() {
		return editCultOffButtonOk;
	}

	public void setEditCultOffButtonOk(WebElement editCultOffButtonOk) {
		this.editCultOffButtonOk = editCultOffButtonOk;
	}

	public WebElement getEditCultOffButtonNo() {
		return editCultOffButtonNo;
	}

	public void setEditCultOffButtonNo(WebElement editCultOffButtonNo) {
		this.editCultOffButtonNo = editCultOffButtonNo;
	}

	public WebElement getDeleteCultOffButton() {
		return deleteCultOffButton;
	}

	public void setDeleteCultOffButton(WebElement deleteCultOffButton) {
		this.deleteCultOffButton = deleteCultOffButton;
	}
	
	public AdminHomePage() {
	}

	public AdminHomePage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void ensureIsDisplayedEditCultOffButton() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("editCultOffButton")));
    }
	
	public void ensureIsClickableEditCultOffButton() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("editCultOffButton")));
    }
	
	public void ensureIsClickableEditCultOffButtonOk() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("editCultOffButtonOk")));
    }
	
	public void ensureIsDisplayedCultOffTable() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("cultOffTable")));
    }
	
	public void ensureIsDisplayedDeleteCultOffButton() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("deleteCultOffButton")));
    }
	
	public void ensureIsClickableDeleteCultOffButton() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id("deleteCultOffButton")));
    }
	
	public void ensureIsDisplayedAddCultOffButton() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("addCultOffButton")));
    }
	
	public void ensureIsDisplayedAddCultOffButtonOk() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("addCultOffButtonOk")));
    }

}
