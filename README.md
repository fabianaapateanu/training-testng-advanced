# training-testng-advanced
Advanced TestNG examples in Java

## Project resources
* src/main/resources/drivers/mac - chromedriver v. 2.35
* src/main/resources/drivers/mac - geckodriver v. 0.19.1
* src/main/resources/drivers/windows - chromedriver.exe v.2.35
* src/main/resources/drivers/windows - geckodriver.exe 0.19.1

## Project structure
* src/main/java/common - webdriver custom driver and helpers
* src/main/java/training/basic - page object classes and dataprovider object class for the demos
    * /dataprovider
    * /pageObject
    * LoginUserHelper.java - helper class for basic login information
* src/test/java
    * /basic - test classes for basic examples
    * /dataprovider - test classes for dataprovider examples

## Parallel use of WebDriver
For being able to run the tests in parallel and having another instance of WebDriver available we have added in `CustomDriver.java`
the thread safe driver usage:

`private static ThreadLocal<WebDriver> myDriver = new ThreadLocal<WebDriver>();`

## Browser parameter
browserName parameter should be used for running the tests. You will need to use this mandatory parameter providing it in the xml suite file, 
this means you will not be able to run the tests directly from IntelliJ with right-click, only through the suite xml file.
See the example in `runMethodsParallel.xml` suite file and in the `DataProviderPrimitiveTests.java` at `@BeforeMethod` setup method.

Supported values are:
* value="firefox"
* value="chrome"
* value="safari"

`<parameter name="browserName" value="chrome"></parameter>`

## Running the Demos
1. You will need to create yourself a valid GitHub account
2. For login tests and purposes you will need to add your login information:
    * `LoginUserHelper.java` in methods `readValidUsername()`, `readValidPassword()`
    

## Demo 1
Running in parallel the test class `DataProviderPrimitiveTests` which uses its own data providers on Chrome browser.
The TestNG xml suite file to be run is: `runMethodsParallel.xml`

1. In the test class `DataProviderPrimitiveTests.java`:
    1. Parallel attribute for the data providers has been set:
        * `@DataProvider(name = "invalid_search_data", parallel = true)`
        * `@DataProvider(name = "valid_search_data", parallel = true)`
    2. Thread count attribute has been set for both test methods:
        * `@Test(groups = "positive_tests", dataProvider = "valid_search_data", threadPoolSize = 10)`
        * `@Test(groups = "negative_tests", dataProvider = "invalid_search_data", threadPoolSize = 10)`
2. The test suite file `runMethodsParallel.xml`:
   1. Attribute parallel has been set to methods in the xml file:
        * `<suite name="Parallel Methods Suite" parallel="methods">`
   2. The browserName parameter has been set:
        * `<parameter name="browserName" value="chrome"></parameter>`
   3. The test class name has been specified
   4. Running the suite xml file will run sequentially each data provider, but each test method will be run in parallel

## Demo 2
A custom test listener implementation of the popular `ITestListener` listener which allows custom actions
for when a test method passes or fails, or at the beginning or end of the test run.
The TestNG xml suite file to be run is: `runCustomListener.xml` 

1. In the class `CustomeListenerExample.java`: 
    1. Have implemented the interface `ITestListener`, meaning added implementation for each needed method from the interface 
    2. Have implemented a method which accesses test method name & result `setMethodDetails(String resultStatus, ITestResult testResult)` which is used to print this information
    3. Have used the method `setMethodDetails(...)` in the `onTestSuccess(...)`, `onTestFailure(...)`, `onTestSkipped(...)` methods in the listener

2. The test suite file `runCustomListener.xml`:
    1. The new listener has been added to the listener attribute in the `<listeners></listeners>` tag:
        * `<listener class-name="advanced.CustomTestListener"/>`
    2. When running this suite, all tests will run sequentially, and we should see in the logs the specific information we have added through the custom implementation

## Demo 3
TestNG default reports and ReporterNG example

1. Run again the `runCustomListener.xml`:
    * For IntelliJ IDEA from the run configuration, Listeners tab, you need to check the "Use Default Reporters" option
    * The `/test-output` directory will be generated in which you can find & open the `index.html` file, which is the default HTML report
    
2. The suite file `runReporterNg.xml`:
    1. Uses two specific listeners from ReporterNG:
        * `<listener class-name="org.uncommons.reportng.HTMLReporter"/>`
        * `<listener class-name="org.uncommons.reportng.JUnitXMLReporter"/>`
     2. These listeners are available through the dependencies added in the `pom.xml` file
     3. After running the suite file the `/test-output` directory will be generated in which you can find a HTML & a XML report
   
## Demo 4
Example of running in parallel at test level.
1. Suite file `runTestsParallel.xml` will run:
    1. `searchWithMultipleResults(String searchQuery)` test on Chrome
    2. `searchWithNoResults(String searchQuery)` test on Firefox
  
##Practice and play :exclamation:
* Go to https://ive.endava.com and login with you endava account (username & password from your computer)
* Make sure to talk with one of your colleagues to login also
* Go to section Quizzes and search for the quiz with name "TestNG & JUnit basics"
* Invite you colleague and play together the quiz! 
