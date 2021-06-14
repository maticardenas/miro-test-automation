package com.miro.data;

import com.miro.core.TestCasesData;
import com.miro.utils.MiroTestUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MiroDataProvider {

    TestCasesData dataManager;

    public MiroDataProvider() throws IOException, InvalidFormatException {
         dataManager = new TestCasesData();
    }

    @DataProvider(name = "requiredFields")
    public Object[][] requiredFieldsTestData() throws IOException, InvalidFormatException {

        Object[][] retrievedData = dataManager.getTestData("RequiredFieldsTest");

        Object[][] testData = new Object[retrievedData.length][5];

        for (int row = 0; row < retrievedData.length; row++) {
            String scenario = "EMPTY";
            for (int col = 0; col < retrievedData[row].length; col++) {
                String fieldScenario = retrievedData[row][col].toString();
                scenario += (fieldScenario.equals("EMPTY") || fieldScenario.equals("NOT SELECTED")) ? "_" + MiroTestUtils.getFieldNameByColumn(col)  : "";
                testData[row][col+1] = retrievedData[row][col];
            }
            testData[row][0] = scenario;
        }

        return testData;
    }

    @DataProvider(name = "shortPasswords")
    public Object[][] shortPasswordsTestData(){
        return new Object[][]{
                {"a"}, {"bb"}, {"ccc"}, {"dddd"},
                {"eeeee"},{"ffffff"},{"ggggggg"}
        };
    }

    @DataProvider(name = "unsafePasswords")
    public Object[][] unsafePasswordsTestData(){
        return new Object[][]{
                {"aaaaaaaa"}, {"11111111"}, {"a1a1a1a1a1"},
                {"2b2b2b2b2b"}
        };
    }
}
