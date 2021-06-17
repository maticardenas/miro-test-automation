# Miro Test Automation

This is a small framework with positive & negative / smoke & regression front-end automated test cases for Miro Sign Up Page (https://miro.com/signup/).

For full details MiroTestAutomation.pdf file can be checked at repo's root folder.

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

#### Test cases included:

* Check page opens successfully
* Get Started without fill 
* Sign In button redirects successfully
* Sign Up User Positive Test Case

### 2) Regression tests

On the project folder, execute following command. It will execute full suite of tests, 28 test cases in aproximately 3 minutes.

```bash
mvn -PRegression test
```

##### Test cases included:

 -> ALL smokes tests plus:
 
* Sign up positive without subscribe 
* Invalid email format
* Short password - Parametrized -> 7 test cases in total
* Unsafe password - Parametrized -> 4 tests cases in total
* Sign Up required fields - Parametrized (from Excel Sheet) -> 8 test cases in total


## REPORTING

Once finished the execution, the tool generates a report at target/surefire-reports/ExtentReport.html.


## SOLUTION DESIGN

Design of solution and more descriptions can be checked at file MiroTestAutomation.pdf provided in the root folder of the repository




