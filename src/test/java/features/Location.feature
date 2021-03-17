Feature: Validating Location API

	Scenario Outline: Verify if Location API is being Successful and temperature fields in response are float type instead of integer type 
		Given user calls "LocationAPI" as GET http Request with "<woeid>"
		Then the API call got success with status code 200
		And "title" in response body is "<locationname>"
		And "woeid" in response body is "<woeid>"
		And validate consolidated_weather response field datatypes
			| ResponseField  | DataType   |
			| max_temp       | Integer    |
			| min_temp       | Integer    |
		    | the_temp       | Integer    |

		Examples:
			| locationname | woeid |
			| Nottingham   | 30720 | 
			| Edinburgh    | 19344 | 
			
			











