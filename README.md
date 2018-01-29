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
   2. The specific test method has been chosen using the `<classes></classes>` and `<methods></methods>` tags.

## Demo 2
Custom test listener example

## Demo 3
TestNG default reports and custom reporter example