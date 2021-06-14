package com.miro.tests;

import com.miro.core.TestBase;
import com.miro.webpages.EmailConfirmPage;
import com.miro.webpages.SignInPage;
import com.miro.webpages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum RequiredFieldsScenarios {
    EMPTY_USER,
    EMPTY_EMAIL,
    EMPTY_PASSWORD,
    NO_AGREE_TERMS
}


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
        // Given - When - Then
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

    @Test(groups = {"smokeTest"}, dataProvider = "requiredFields")
    public void signUpNegativeRequiredFields(String userName, String email, String password, String agreeMiroTerms){
        ArrayList<RequiredFieldsScenarios> expectedRequiredMessages = new ArrayList<>();

        if(!userName.equals("EMPTY"))
            signUpPage.setUserName("test_required_user");
        else
            expectedRequiredMessages.add(RequiredFieldsScenarios.EMPTY_USER);

        if(!password.equals("EMPTY"))
            signUpPage.setPassword("test_required_pass");
        else
            expectedRequiredMessages.add(RequiredFieldsScenarios.EMPTY_PASSWORD);

        if(!email.equals("EMPTY"))
            signUpPage.setEmail("test_email@test.com");
        else
            expectedRequiredMessages.add(RequiredFieldsScenarios.EMPTY_EMAIL);

        if(!agreeMiroTerms.equals("NOT SELECTED"))
            signUpPage.selectSignUpTerms();
        else
            expectedRequiredMessages.add(RequiredFieldsScenarios.NO_AGREE_TERMS);

        signUpPage.clickGetStartedNow();

        // Then
        validateErrorMessage(expectedRequiredMessages);

    }

    private void validateErrorMessage(ArrayList<RequiredFieldsScenarios> expectedRequiredScenarios){
        for (RequiredFieldsScenarios expectedMsg: expectedRequiredScenarios) {
            switch(expectedMsg){
                case EMPTY_USER:
                    Assert.assertTrue(signUpPage.enterYourNameErrorExists());
                    break;
                case EMPTY_EMAIL:
                    Assert.assertTrue(signUpPage.enterYourEmailErrorExists());
                    break;
                case EMPTY_PASSWORD:
                    Assert.assertTrue(signUpPage.requiredPasswordMessageExists());
                    break;
                case NO_AGREE_TERMS:
                    Assert.assertTrue(signUpPage.agreeWithTermsErrorExists());
                    break;
            }

        }
    }

    @DataProvider(name = "requiredFields")
    public Object[][] requiredFieldsTestData() throws IOException, InvalidFormatException {

        Object[][] retrievedData = dataManager.getTestData("RequiredFieldsTest");

        Object[][] testData = new Object[retrievedData.length][4];

        for (int row = 0; row < retrievedData.length; row++) {
            for (int col = 0; col < retrievedData[row].length; col++) {
                testData[row][col] = retrievedData[row][col];
            }
        }

        return testData;
    }
}
