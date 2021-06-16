# Miro Test Automation

This is a small framework with positive & negative / smoke & regression front-end automated test cases for Miro Sign Up Page (https://miro.com/signup/) 

## Requirements

* Java 11

* Maven

* Any Browser installed (Chrome, Edge, Firefox)

## Usage Instructions

## Browser Parametrization

The preferred browser by user can be speficied before suite execution. This can be done modifying the parameters at the main testng execution files at root of the project folder. Available values are: EDGE, FIREFOX and CHROME. By default browser is set to EDGE.

Files: 

* testng_smoke.xml
* testng_regression.xml

For example:

```xml
<suite name="Miro-Suite" verbose="1" >
    <test name="Smoke">
        <parameter name="browser" value="EDGE"/>
        <groups>
            <run>
                <include name="smokeTest"></include>
            </run>
        </groups>
        <classes>
            <class name="com.miro.tests.SignUpTest" />
        </classes>
    </test>
</suite>
```


## EXECUTION


### 1) Smoke tests

On the project folder, execute following command. It will execute 4 smoke basic test cases in aproximately ~12/13 seconds.

```bash
mvn -PSmoke test
```

### 2) Regression tests

On the project folder, execute following command. It will execute full suite of tests, 32 test cases in aproximately 4 minutes.

```bash
mvn -PRegression test
```


## REPORTING

Once finished the execution, the tool generates a report at target/surefire-reports/ExtentReport.html, however there is a shortcut at root folder of the project called miro_report.html where you can access it directly

<img src="/images/report_1.jpg" height="250" width="500" />

<img src="/images/report_failed_sample.jpg" height="250" width="500" />

<img src="/images/repo_charts.jpg" height="250" width="500" />







