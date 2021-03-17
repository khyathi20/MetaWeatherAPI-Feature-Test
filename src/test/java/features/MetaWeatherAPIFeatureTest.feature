Feature: Validating MetaWeather API feature

	As a MetaWeather API client, 
	I want to retrieve “tomorrows” weather for “Nottingham”
	
	Scenario: Verify if Meta weather API is displaying the tomorrows weather for Nottingham and the temperature in response field is of type float instead of integer
	
	 	Given user calls "LocationSearchAPI" as GET http Request with "Nottingham"
	 	And "woeid" in response body is "30720"
		When user calls "LocationAPI" with GET http Request with woeid and "tomorrow"
		Then the API call got success with status code 200
		And "applicable_date" in response body has tomorrows date displayed
		And verify the response field datatypes
			| ResponseField  | DataType |
			| max_temp       | Integer  |
			| min_temp       | Integer  |
		    | the_temp       | Integer  |
	 
	

	Scenario Outline: Verify if LocationDay API is being Successful for multiple locations and dates and the temperature in response field is of type float instead of integer
	
		Given user calls "LocationSearchAPI" as GET http Request with "<locationname>"
		And "title" in response body is "<locationname>"
		And "woeid" in response body is "<woeid>"
		When user calls "LocationAPI" with GET http Request with woeid and "<yyyy/mm/dd>"
		Then the API call got success with status code 200
		And "applicable_date" in response body is "<yyyy-mm-dd>"
		And verify the response field datatypes
			| ResponseField  | DataType   |
			| max_temp       | Integer    |
			| min_temp       | Integer    |
		    | the_temp       | Integer    |

		Examples:
			| locationname | woeid | yyyy/mm/dd | yyyy-mm-dd |
			| Nottingham   | 30720 | 2021/3/22  | 2021-03-22 |
			| Edinburgh    | 19344 | 2021/3/12  | 2021-03-12 |










