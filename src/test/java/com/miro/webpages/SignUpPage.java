package com.miro.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage {

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    private static String PAGE_URL = "https://miro.com/signup/";

    // Locators
    @FindBy(xpath = "//h1[normalize-space()='Get started free today']")
    private WebElement header;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = ".speero-dropdown")
    private WebElement howDidYouHearMainDropdown;

    @FindBy(id = "signup-terms")
    private WebElement signUpTerms;

    @FindBy(id = "signup-subscribe")
    private WebElement signUpSubscribe;

    @FindBy(css = "button[type='submit']")
    private WebElement getStartedNowButton;

    @FindBy(xpath = "//div[normalize-space()='Please enter your password.']")
    private WebElement enterYourPassword;

    public SignUpPage(WebDriver driver){
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
        driver.get(PAGE_URL);
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return header.getText().contains("Get started free today");
    }

    public void clickGetStartedNow(){
        getStartedNowButton.click();
    }

    public void selectHowDidYouHearItem(String itemText){
        howDidYouHearMainDropdown.click();
        driver.findElement(By.linkText(itemText)).click();
    }

    public void selectSignUpTerms(){
        jsExecutor.executeScript("arguments[0].click();", signUpTerms);
    }

    public void selectSignUpSubscribe(){
        jsExecutor.executeScript("arguments[0].click();", signUpSubscribe);
    }

    public boolean requiredPasswordMessageExists(){
        return enterYourPassword.isDisplayed();
    }
}
