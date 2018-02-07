# training-testng-advanced
Basic TestNG examples in Java

## Driver versions used in the project

GekoDriver v. 0.19.1
ChromeDriver v. 2.35

## Browser parameter
browserName parameter can be used for running the tests.
See the example in runMethodsParallel.xml suite file and in the DataProviderPrimitiveTests.java at @BeforeMethod setup method.

Supported values are:
* value="firefox"
* value="chrome"
* value="safari"

`<parameter name="browserName" value="chrome"></parameter>`

## Demo 1
Running in parallel the no results search test method `searchWithNoResults()` which uses its own data provider on Chrome browser.
The TestNG xml suite file to be run is: runMethodsParallel.xml
 
1. In the test class `DataProviderPrimitiveTests.java`:
    1. Parallel attribute for the data provider has been set:
        * `@DataProvider(name = "invalid_search_data", parallel = true)`
    2. Thread count attribute has been set for the `loginAndSearchNoResults()` test method:
        * `@Test(groups = "negative_tests", dataProvider = "invalid_search_data", threadPoolSize = 3)`
2. The test suite file runMethodsParallel.xml:
   1. Attribute parallel has been set to methods in the xml file:
        * `<suite name="Parallel Methods Suite" parallel="methods">`
   2. The browserName parameter has been set:
        * `<parameter name="browserName" value="chrome"></parameter>`
   3. The specific test method has been chosen using the `<classes></classes>` and `<methods></methods>` tags.

## Demo 2
A custom test listener implementation of the popular `ITestListener` listener which allows custom actions
for when a test method passes or fails, or at the beginning or end of the test run.
The TestNG xml suite file to be run is: runCustomListener.xml

1. In the class `CustomeListenerExample.java`: 
    1. Have implemented the interface `ITestListener`, meaning added implementation for each needed method from the interface 
    2. Have implemented a method which accesses test method name & result `setMethodDetails(String resultStatus, ITestResult testResult)` which is used to print this information
    3. Have used the method `setMethodDetails(...)` in the `onTestSuccess(...)`, `onTestFailure(...)`, `onTestSkipped(...)` methods in the listener

2. The test suite file runCustomListener.xml:
    1. The new listener has been added to the listener attribute in the `<listeners></listeners>` tag:
        * `<listener class-name="advanced.CustomTestListener"/>`
    2. When running this suite we should see in the logs the specific information we have added through the custom implementation

## Demo 3
TestNG default reports and ReporterNG example