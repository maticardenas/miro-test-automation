package com.miro.tests;

import com.miro.webpages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SignUpTest {
    WebDriver driver;
    SignUpPage signUpPage;

    @BeforeClass
    public static void setupClass(){
        //WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void checkMainOpened(){
        Assert.assertTrue(signUpPage.isPageOpened());
    }

    @Test
    public void getStartedWithoutFill(){
        // Given
        signUpPage.clickGetStartedNow();

        // When - Then
        Assert.assertTrue(signUpPage.requiredPasswordMessageExists());
    }

//    @Test
//    public void selectAnyItem(){
////        signUpPage.selectHowDidYouHearItem("Through work or school");
//        signUpPage.selectSignUpTerms();
//        signUpPage.selectSignUpSubscribe();
//    }

}
