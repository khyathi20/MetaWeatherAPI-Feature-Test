                                            TESTSUITE OVERVIEW

   PROJECT: 

   The purpose of this test suite is to create automation tests for the Metaweather APIs for the feature :
   
   As a MetaWeather API client, I want to retrieve “tomorrows” weather for “Nottingham”


  INSTALLATION:
   
  IDE (Eclipse or equivalent)
  Java 8 JDK
  Maven
  Maven plugin should be installed in IDE
  Cucumber and Gherkin plugins should be installed in IDE
  
  API REFERENCES USED:
  
  /api/location/search/?query=(query)
  /api/location/(woeid)/
  /api/location/(woeid)/(date)/
  

  TESTSUITE STRUCTURE:
 
	✓	Features package-Test scenarios are included in this package 
	✓	stepDefinitions package- Includes step definitions for the scenarios in features.
	✓	Resources package - Includes Util class which contains common utility functions
                            global.properties to set the base Url
                            APIResources class has list of API resources being used
	✓	logging.txt - This is the file where requests and responses are logged when we run the tests.
	✓	Cucumber.Options package - contains the test runner class which initiates the tests to run based on 
	                               the options provided.
	✓	When the tests are run reports are generated in target>jsonReports>cucumber-report.json

  EXECUTION STEPS:

	✓	Import the project into IDE as maven project.
	✓	Convert the project into cucumber project. 
                  Right click on the project>configure>convert into cucumber project
	✓	Tests can be run from the IDE or through the command line.
	✓	To run through command line: 
		       open the terminal>go to the project directory>type the command “mvn test”(make sure you are in the same directory of pom.xml.
	✓	To run through IDE:
                 To run all the feature files right-click on TestRunner.java>Run as>Junit Test 
                 To run single feature file  right-click on feature file (with .feature)>Run as>cucumber feature 
                 



