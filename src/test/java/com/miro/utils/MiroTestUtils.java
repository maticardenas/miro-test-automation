package com.miro.utils;

import com.miro.utils.RequiredFieldsScenarios;
import com.miro.webpages.SignUpPage;
import org.junit.Assert;
import java.util.ArrayList;

public class MiroTestUtils {

    public static String getFieldNameByColumn(int column){
        switch (column){
            case 0: return "USER";
            case 1: return "EMAIL";
            case 2: return "PASSWORD";
            case 3: return "AGREE_TERMS";
            default: return "UKNOWN_COLUMN";
        }
    }


    /**
     *
     * Validates if each of the expected error messages is present in the Sign Up Page after a parametrized negative
     * test case execution
     *
     * @param signUpPage The SignUpPage PageObject object to access the elements to validate
     * @param expectedRequiredScenarios An ArrayList with all expected messages to validate against the presents at the
     *                                  page.
     *
     *
     * */
    public static void validateErrorMessage(SignUpPage signUpPage, ArrayList<RequiredFieldsScenarios> expectedRequiredScenarios){
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
}
