# training-testng-advanced
Basic TestNG examples in Java

# Driver versions used in the project

GekoDriver v. 0.19.1
ChromeDriver v. 2.35

# Browser parameter can be used for tests
See the example in runMethodsParallel.xml suite file.

Supported values are:
value="firefox"
value="chrome"
value="safari"

# Demo 1
Running in parallel the no results search test method which uses its own data provider on Chrome browser.
The TestNG xml suite file to be run is: runMethodsParallel.xml

1. The test class DataProviderPrimitiveTests.java:
    1.1 Parallel attribute for the data provider has been set:
    @DataProvider(name = "invalid_search_data", parallel = true)
    1.2 Thread count attribute has been set for the loginAndSearchNoResults test method:
    @Test(groups = "negative_tests", dataProvider = "invalid_search_data", threadPoolSize = 3)
2. The test suite file runMethodsParallel.xml:
    2.1 Attribute parallel has been set to methods:
    <suite name="Parallel Methods Suite" parallel="methods">
    2.2 The specific test methods has been chosen using the <classes></classes> and <methods></methods> tags.

# Demo 2

# Demo 3