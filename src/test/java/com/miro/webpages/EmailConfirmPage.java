package com.miro.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailConfirmPage {

    WebDriver driver;

    private static String PAGE_URL = "https://miro.com/email-confirm/";

    @FindBy(xpath = "//h1[normalize-space()='Check your email']")
    private WebElement checkYourEmail;

    public EmailConfirmPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean existsCheckYourEmail(){
        return checkYourEmail.isDisplayed();
    }

}
