package com.miro.tests;

import com.miro.core.TestBase;
import com.miro.data.MiroDataProvider;
import com.miro.utils.MiroTestUtils;
import com.miro.utils.RequiredFieldsScenarios;
import com.miro.webpages.EmailConfirmPage;
import com.miro.webpages.SignInPage;
import com.miro.webpages.SignUpPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class SignUpTest extends TestBase {
    SignUpPage signUpPage;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(){
        signUpPage.test = test;
    }

    @BeforeMethod(alwaysRun = true)
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

    @Test(groups = {"smokeTest"})
    public void signInButtonRedirectsSuccessfully() {
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
//        signUpPage.selectHowDidYouHearItem("TV");
        signUpPage.selectSignUpTerms();
        signUpPage.selectSignUpSubscribe();
        signUpPage.clickGetStartedNow();

        // Then
        EmailConfirmPage emailConfirm = new EmailConfirmPage(driver);
        Assert.assertTrue(emailConfirm.existsCheckYourEmail());
    }

    @Test(groups = {"regressionTest"})
    public void signUpPositiveWithoutSubscribe(){
        // Given
        int userNumber = new Random().nextInt(1000);
        signUpPage.setUserName("test_user_" + userNumber);
        signUpPage.setEmail("test_user_" + userNumber + "@test.com");
        signUpPage.setPassword("test_user_pass_" + userNumber);
//        signUpPage.selectHowDidYouHearItem("TV");
        signUpPage.selectSignUpTerms();

        // When
        signUpPage.clickGetStartedNow();

        // Then
        EmailConfirmPage emailConfirm = new EmailConfirmPage(driver);
        Assert.assertTrue(emailConfirm.existsCheckYourEmail());
    }


    @Test(groups = {"regressionTest"})
    public void invalidEmailFormat(){
        // Given
        int userNumber = new Random().nextInt(1000);
        signUpPage.setUserName("test_user_" + userNumber);
        signUpPage.setEmail("an_invalid_email");
        signUpPage.setPassword("test_user_pass_" + userNumber);
        signUpPage.selectSignUpTerms();
        signUpPage.selectSignUpSubscribe();

        // When
        signUpPage.clickGetStartedNow();

        // Then
        String expectedMessage = "look like an email address. Please check it for typos and try again.";
        Assert.assertTrue(signUpPage.getSignUpErrorItemText().contains(expectedMessage));
    }


    @Test(groups = {"regressionTest"}, dataProvider = "shortPasswords", dataProviderClass = MiroDataProvider.class)
    public void shortPassword(String password){
        // Given
        int userNumber = new Random().nextInt(1000);
        signUpPage.setUserName("test_user_" + userNumber);
        signUpPage.setEmail("test_user_" + userNumber + "@test.com");

        // When
        signUpPage.setPassword(password);

        // Then
        String expectedMessage = "Please use 8+ characters for secure password";
        Assert.assertEquals(expectedMessage, signUpPage.getInputHintText());
    }

    @Test(groups = {"regressionTest"}, dataProvider = "unsafePasswords", dataProviderClass = MiroDataProvider.class)
    public void unsafePassword(String password){
        // Given
        int userNumber = new Random().nextInt(1000);
        signUpPage.setUserName("test_user_" + userNumber);
        signUpPage.setEmail("test_user_" + userNumber + "@test.com");

        // When
        signUpPage.setPassword(password);

        // Then
        Assert.assertTrue(signUpPage.inputHintSoSoExists());
    }

    @Test(groups = {"regressionTest"}, dataProvider = "requiredFields", dataProviderClass = MiroDataProvider.class)
    public void signUpNegativeRequiredFields(String scenario, String userName, String email, String password, String agreeMiroTerms){
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
        MiroTestUtils.validateErrorMessage(signUpPage, expectedRequiredMessages);

    }

    @Test(groups = {"regressionTest"})
    public void continueSignUpWithGoogle(){
        // Given - When
        signUpPage.clickSignUpWithGoogle();

        // Then
        Assert.assertTrue(signUpPage.continueSignUpExists());
    }

    @Test(groups = {"regressionTest"})
    public void continueSignUpWithGoogleWithoutTerms(){
        // Given - When
        signUpPage.clickSignUpWithGoogle();
        signUpPage.clickContinueToSignUpGoogle();

        // Then
        Assert.assertTrue(signUpPage.agreeWithTermsGoogleErrorExists());
    }

    @Test(groups = {"regressionTest"})
    public void continueSignUpWithGoogleSuccessful(){
        // Given - When
        signUpPage.clickSignUpWithGoogle();
        signUpPage.selectSignUpTermsGoogle();
        signUpPage.selectSignUpSubscribeGoogle();
        signUpPage.clickContinueToSignUpGoogle();

        // Then
        Assert.assertTrue(driver.getTitle().contains("Google Accounts"));
    }


}
