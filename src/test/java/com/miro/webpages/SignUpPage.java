package com.miro.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;
    private WebDriverWait wait;

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
    private WebElement enterYourPasswordError;

    @FindBy(xpath = "//div[normalize-space()='Please agree with the Terms to sign up.']")
    private WebElement agreeWithTermsError;

    @FindBy(xpath = "//div[normalize-space()='Please enter your email address.']")
    private WebElement enterYourEmailError;

    @FindBy(xpath = "//div[normalize-space()='Please enter your name.']")
    private WebElement enterYourNameError;

    @FindBy(xpath = "//a[normalize-space()='Sign in']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@class='signup__error-item']")
    private WebElement signUpErrorItem;

    @FindBy(xpath = "//div[@class='signup__input-hint-text']")
    private WebElement inputHint;

    @FindBy(xpath = "//div[@class='signup__input-hint-text signup__input-hint-text--color-soso']")
    private WebElement inputHintSoSo;

    public SignUpPage(WebDriver driver){
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
        driver.get(PAGE_URL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return header.getText().contains("Get started free today");
    }

    public void clickGetStartedNow(){
        getStartedNowButton.click();
    }

    public void clickSignIn(){
        signInButton.click();
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

    public void setUserName(String userName){
        name.sendKeys(userName);
    }

    public void setEmail(String userEmail){
        email.sendKeys(userEmail);
    }

    public void setPassword(String userPassword){
        password.sendKeys(userPassword);
    }

    public boolean requiredPasswordMessageExists(){
        wait.until(ExpectedConditions.visibilityOf(enterYourPasswordError));
        return enterYourPasswordError.isDisplayed();
    }

    public boolean enterYourNameErrorExists(){
        wait.until(ExpectedConditions.visibilityOf(enterYourNameError));
        return enterYourNameError.isDisplayed();
    }

    public boolean enterYourEmailErrorExists(){
        wait.until(ExpectedConditions.visibilityOf(enterYourEmailError));
        return enterYourEmailError.isDisplayed();
    }

    public boolean agreeWithTermsErrorExists(){
        wait.until(ExpectedConditions.visibilityOf(agreeWithTermsError));
        return agreeWithTermsError.isDisplayed();
    }

    public String getSignUpErrorItemText(){
        wait.until(ExpectedConditions.visibilityOf(signUpErrorItem));
        return signUpErrorItem.getText();
    }

    public String getInputHintText(){
        wait.until(ExpectedConditions.visibilityOf(inputHint));
        return inputHint.getText();
    }

    public boolean inputHintSoSoExists(){
        wait.until(ExpectedConditions.visibilityOf(inputHintSoSo));
        return inputHintSoSo.isDisplayed();
    }
}
