Feature: Validating LocationSearch API


	Scenario Outline: Verifing if LocationSearch API gives the correct woied for location provided
	
		Given user calls "LocationSearchAPI" as GET http Request with "<locationname>"
		Then the API call got success with status code 200
		And "title" in search response body is "<locationname>"
		And "woeid" in search response body is "<woeid>"

		Examples:
			| locationname | woeid |
			| Nottingham   | 30720 | 
			| Edinburgh    | 19344 | 
			| Edinburgh    | 30720 |
			| Nottingham   | 19344 |










