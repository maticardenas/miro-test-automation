package com.miro.webpages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    WebDriver driver;

    private static String PAGE_URL = "https://miro.com/login/";

    @FindBy(xpath = "//h1[normalize-space()='Sign in']")
    private WebElement signInTitle;

    public SignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean existsSignInTitle(){
        return signInTitle.isDisplayed();
    }

}
