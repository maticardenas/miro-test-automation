package com.miro.tests;

import com.miro.core.TestBase;
import com.miro.webpages.EmailConfirmPage;
import com.miro.webpages.SignInPage;
import com.miro.webpages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;


public class SignUpTest extends TestBase {
    SignUpPage signUpPage;

//    @BeforeClass
//    public static void setupClass(){
//        //WebDriverManager.chromedriver().setup();
//    }

    @BeforeMethod
    public void setupTest() {
        signUpPage = new SignUpPage(driver);
    }

    @Test(groups = {"smokeTest"})
    public void checkMainOpensSuccessfully() {
        Assert.assertTrue(signUpPage.isPageOpened());
    }

    @Test(groups = {"smokeTest"})
    public void getStartedWithoutFill() {
        // Given
        signUpPage.clickGetStartedNow();

        // When - Then
        Assert.assertTrue(signUpPage.requiredPasswordMessageExists());
    }

    @Test(groups = {"smokeTests"})
    public void signInButtonRedirectsSuccessfully() throws InterruptedException {
        // Given - When
        signUpPage.clickSignIn();

        // Then
        SignInPage signInPage = new SignInPage(driver);
        Assert.assertTrue(signInPage.existsSignInTitle());
    }

    @Test(groups = {"smokeTest"})
    public void signUpPositiveAllFields(){
        // Given - When
        int userNumber = new Random().nextInt(1000);
        signUpPage.setUserName("test_user_" + userNumber);
        signUpPage.setEmail("test_user_" + userNumber + "@test.com");
        signUpPage.setPassword("test_user_pass_" + userNumber);
        signUpPage.selectHowDidYouHearItem("TV");
        signUpPage.selectSignUpTerms();
        signUpPage.selectSignUpSubscribe();
        signUpPage.clickGetStartedNow();

        // Then
        EmailConfirmPage emailConfirm = new EmailConfirmPage(driver);
        Assert.assertTrue(emailConfirm.existsCheckYourEmail());
    }









//    @Test
//    public void selectAnyItem(){
////        signUpPage.selectHowDidYouHearItem("Through work or school");
//        signUpPage.selectSignUpTerms();
//        signUpPage.selectSignUpSubscribe();
//    }

}
