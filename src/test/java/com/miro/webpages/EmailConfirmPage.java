package com.miro.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailConfirmPage {

    WebDriver driver;
    WebDriverWait wait;

    private static String PAGE_URL = "https://miro.com/email-confirm/";

    @FindBy(xpath = "//h1[normalize-space()='Check your email']")
    private WebElement checkYourEmail;

    public EmailConfirmPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public boolean existsCheckYourEmail(){
        wait.until(ExpectedConditions.visibilityOf(checkYourEmail));
        return checkYourEmail.isDisplayed();
    }

}
