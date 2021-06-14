package com.miro.webpages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    WebDriver driver;
    WebDriverWait wait;

    private static String PAGE_URL = "https://miro.com/login/";

    @FindBy(xpath = "//h1[normalize-space()='Sign in']")
    private WebElement signInTitle;

    public SignInPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public boolean existsSignInTitle(){
        wait.until(ExpectedConditions.visibilityOf(signInTitle));
        return signInTitle.isDisplayed();
    }

}
