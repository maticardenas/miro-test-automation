package com.miro.core;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.ITest;
import org.testng.ITestContext;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;


public class TestBase implements ITest {
    public static ExtentTest test;
    public static ExtentReports extent;
    protected WebDriver driver;
    protected static Logger logger = LogManager.getLogger(TestBase.class.getName());

    private ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void before() throws IOException, InvalidFormatException {
        Path filePath = Paths.get("target", "surefire-reports", "ExtentReport.html");
        extent = new ExtentReports(filePath.toString(), true);
    }


    /**
     *
     * Set driver to be used by all the test classes
     *
     * @author Matías Cárdenas
     *
     */
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String browser) {
        switch(browser) {
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                this.driver = new EdgeDriver();
                break;
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                break;
            default:
                reportLog("Browser not supported");
                break;
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    /**
     *
     * Define each test name (specially needed in parametrized tests).
     *
     * @author Matías Cárdenas
     *
     */
    @BeforeMethod(alwaysRun = true)
    public void BeforeMethod(Method method, Object[] testData, ITestContext ctx){
        String testNameStr = "";

        if (testData.length > 0) {
            testNameStr = method.getName() + "_" + testData[0];
            testName.set(method.getName() + "_" + testData[0]);

        } else
            testNameStr = method.getName();

        ctx.setAttribute("testName", testNameStr);
        test = extent.startTest(testNameStr,testNameStr);

        logger.info("RUNNING TEST: *** " + testNameStr + " ***");
        reportLog("RUNNING TEST: *** " + testNameStr + " ***");
    }


    /**
     *
     * Log test result
     *
     * @author Matías Cárdenas
     *
     */
    @AfterMethod(alwaysRun = true)
    public void endTest(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.FAIL, result.getName());
            test.log(LogStatus.FAIL,result.getThrowable());
            logger.info("TEST " + result.getName() + ": *** FAILED ***\n\n");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, result.getName());
            logger.info("TEST " + result.getName() + ": *** PASSED ***\n\n");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP,"Test Case : " + result.getName() + " has been skipped");
            logger.info("TEST " + result.getName() + ": *** SKIPPED ***\n\n");
        }
        extent.endTest(test);
    }

    @Override
    public String getTestName(){
        return testName.get();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        driver.close();
        extent.flush();
        extent.close();
    }

    public void reportLog(String message) {
        test.log(LogStatus.INFO, message);
        logger.info("Message: " + message);
        Reporter.log(message);
    }

}

