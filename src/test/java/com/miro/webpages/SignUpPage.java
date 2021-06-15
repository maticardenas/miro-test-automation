package com.miro.webpages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.testng.Reporter;

public class SignUpPage {

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;
    private WebDriverWait wait;
    protected static Logger logger = LogManager.getLogger(SignUpPage.class.getName());
    public static ExtentTest test;

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

    @FindBy(id = "a11y-signup-with-google")
    private WebElement signUpWithGoogle;

    @FindBy(xpath = "//div[normalize-space()='Continue to sign up']")
    private WebElement continueToSignUp;

    @FindBy(id = "tos-signup-terms")
    private WebElement signUpTermsGoogle;

    @FindBy(id = "tos-signup-subscribe")
    private WebElement signUpSubscribeGoogle;

    @FindBy(xpath = "//div[@class='socialtos__btn-error socialtos__btn-error--show']")
    private WebElement agreeWithTermsGoogleError;

    public SignUpPage(WebDriver driver){
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
        driver.get(PAGE_URL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        reportLog("Checking SingUpPage is opened");
        return header.getText().contains("Get started free today");
    }

    public void clickGetStartedNow(){
        reportLog("Click at GetStartedNow");
        getStartedNowButton.click();
    }

    public void clickSignIn(){
        reportLog("Click at SignIn");
        signInButton.click();
    }

    public void selectHowDidYouHearItem(String itemText){
        howDidYouHearMainDropdown.click();
        driver.findElement(By.linkText(itemText)).click();
    }

    public void selectSignUpTerms(){
        reportLog("Select SignUpTerms");
        jsExecutor.executeScript("arguments[0].click();", signUpTerms);
    }

    public void selectSignUpSubscribe(){
        reportLog("Select SignUpSubscribe");
        jsExecutor.executeScript("arguments[0].click();", signUpSubscribe);
    }

    public void setUserName(String userName){
        reportLog("Type UserName");
        name.sendKeys(userName);
    }

    public void setEmail(String userEmail){
        reportLog("Type Email");
        email.sendKeys(userEmail);
    }

    public void setPassword(String userPassword){
        reportLog("Type Password");
        password.sendKeys(userPassword);
    }

    public boolean requiredPasswordMessageExists(){
        reportLog("Checking required PasswordMessage Exists");
        wait.until(ExpectedConditions.visibilityOf(enterYourPasswordError));
        return enterYourPasswordError.isDisplayed();
    }

    public boolean enterYourNameErrorExists(){
        reportLog("Checking required Name Message Exists");
        wait.until(ExpectedConditions.visibilityOf(enterYourNameError));
        return enterYourNameError.isDisplayed();
    }

    public boolean enterYourEmailErrorExists(){
        reportLog("Checking required Email Message Exists");
        wait.until(ExpectedConditions.visibilityOf(enterYourEmailError));
        return enterYourEmailError.isDisplayed();
    }

    public boolean agreeWithTermsErrorExists(){
        reportLog("Checking required Agree with Terms Message Exists");
        wait.until(ExpectedConditions.visibilityOf(agreeWithTermsError));
        return agreeWithTermsError.isDisplayed();
    }

    public String getSignUpErrorItemText(){
        reportLog("Checking Sign Up Error Item Text Exists");
        wait.until(ExpectedConditions.visibilityOf(signUpErrorItem));
        return signUpErrorItem.getText();
    }

    public String getInputHintText(){
        reportLog("Checking Input Hint Text Exists");
        wait.until(ExpectedConditions.visibilityOf(inputHint));
        return inputHint.getText();
    }

    public boolean inputHintSoSoExists(){
        reportLog("Checking Input Hint So So Password Message Exists");
        wait.until(ExpectedConditions.visibilityOf(inputHintSoSo));
        return inputHintSoSo.isDisplayed();
    }

    public boolean continueSignUpExists(){
        reportLog("Checking Continue Sign Up Window Exists");
        wait.until(ExpectedConditions.visibilityOf(continueToSignUp));
        return continueToSignUp.isDisplayed();
    }

    public boolean agreeWithTermsGoogleErrorExists(){
        reportLog("Checking Agree With Terms Google Error Meessage Exists");
        wait.until(ExpectedConditions.visibilityOf(agreeWithTermsGoogleError));
        return agreeWithTermsGoogleError.isDisplayed();
    }

    public void clickContinueToSignUpGoogle(){
        reportLog("Click at Continue to Sign Up Google");
        continueToSignUp.click();
    }

    public void clickSignUpWithGoogle(){
        reportLog("Click Sign Up with Google");
        signUpWithGoogle.click();
    }

    public void selectSignUpTermsGoogle(){
        reportLog("Select Sign Up Terms Google");
        jsExecutor.executeScript("arguments[0].click();", signUpTermsGoogle);
    }

    public void selectSignUpSubscribeGoogle(){
        reportLog("Click Sign Up Subscribe with Google");
        jsExecutor.executeScript("arguments[0].click();", signUpSubscribeGoogle);
    }

    public void reportLog(String message) {
        if (test != null)
            test.log(LogStatus.INFO, message);
        logger.info("Message: " + message);
        Reporter.log(message);
    }
}
