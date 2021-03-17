package stepDefinitions;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import resources.APIResources;
import resources.Utils;

public class StepDefinition extends Utils {

	RequestSpecification reqSpec;
	Response response;
	String woeidRes;
	
	
	@Given("user calls {string} as GET http Request with {string}")
	public void user_calls_to_search_for_location_with(String resourceApi, String param) throws IOException {
        
		System.out.println("resourceApi:" + resourceApi);
		System.out.println("param:" + param);
		APIResources resourceAPI = APIResources.valueOf(resourceApi);
		if(resourceApi.equalsIgnoreCase("LocationAPI"))
		{
			reqSpec = given().spec(requestSpecification());
			response = reqSpec.when().get(resourceAPI.getResource()+param+"/");	
		}
		else if(resourceApi.equalsIgnoreCase("LocationSearchAPI"))
		{
			reqSpec = given().spec(requestSpecification(param));
			response = reqSpec.when().get(resourceAPI.getResource());
		}
			
	}

	@When("user calls {string} with GET http Request with woeid and {string}")
	public void user_calls_with_http_request_with_date(String resource,String date) throws IOException {

		reqSpec = given().spec(requestSpecification());
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		if(date.equalsIgnoreCase("tomorrow"))
		{
			date = getTomorrowDate("yyyy/MM/dd");
		}

		System.out.println(resourceAPI.getResource()+ woeidRes + "/" + date + "/");
		response = reqSpec.when().get(resourceAPI.getResource() + woeidRes + "/" + date + "/");

	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(int status) {

		assertEquals(status, response.getStatusCode());
	}

	@Then("{string} in search response body is {string}")
	public void in_search_response_body_is(String keyValue, String Expectedvalue) {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		List titleKey = js.getList(keyValue);

		for (int i = 0; i < titleKey.size(); i++) {

			if (keyValue.equalsIgnoreCase("woeid")) {
				woeidRes = titleKey.get(i).toString();
			}
			assertEquals(Expectedvalue, titleKey.get(i).toString());
		}

	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		String titleKey = js.get(keyValue).toString();

			if (keyValue.equalsIgnoreCase("woeid")) {
				
				woeidRes = titleKey.substring(1,titleKey.length()-1 );
				System.out.println("woeidRes:"+woeidRes);
				
			}
			
			assertTrue(titleKey.contains(Expectedvalue));

	}

	@Then("{string} in response body is type {string}")
	public void in_response_body_is_type(String keyValue, String ExpectedvalueType) {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		List titleKey = js.getList(keyValue);
		for (int i = 0; i < titleKey.size(); i++) {
			if(ExpectedvalueType.equals("Float"))
				assertEquals(titleKey.get(i).getClass(), Float.class);
			else
				assertTrue(false);

			}
		}


	@Then("verify the response field datatypes")
	public void validate_the_response_field_datatypes(DataTable fieldAndType) {

		List<Map<String,String>> fieldAndTypeVal = fieldAndType.asMaps();

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		for (Map<String,String> fieldTypePair : fieldAndTypeVal)
		{
			String fieldName = fieldTypePair.get("ResponseField");
			String fieldType = fieldTypePair.get("DataType");

			List titleKey = js.getList(fieldName);

			Iterator titleIterator=titleKey.iterator();
			while(titleIterator.hasNext())
			{
				if (fieldType.equals("Float"))
					assertEquals(titleIterator.next().getClass(), Float.class);
				else
					assertTrue(fieldName+" is of not of type "+fieldType,false);
			}
		}
	}
	
	@Then("{string} in response body has tomorrows date displayed")
	public void in_response_body_is_displayed(String keyValue) {
		String Expectedvalue=getTomorrowDate("yyyy-MM-dd");
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		List titleKey = js.getList(keyValue);

		for (int i = 0; i < titleKey.size(); i++) {

			if (keyValue.equalsIgnoreCase("woeid")) {
				woeidRes = titleKey.get(i).toString();
			}
			assertEquals(Expectedvalue, titleKey.get(i).toString());
		}

	}
	
	@Then("validate consolidated_weather response field datatypes")
	public void validate_consolidated_weather_response_field_datatypes(DataTable fieldAndType) {
		
		List<Map<String,String>> fieldAndTypeVal = fieldAndType.asMaps();

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		List<Map<String,String>> weatherResp=js.get("consolidated_weather");
		for (Map<String,String> fieldTypePair : fieldAndTypeVal)
		{
			String fieldName = fieldTypePair.get("ResponseField");
			String fieldType = fieldTypePair.get("DataType");

			List titleKey=new ArrayList();
			for (int i=0;i<weatherResp.size();i++)
			{
				titleKey.add(weatherResp.get(i).get(fieldName));
			}

			Iterator titleIterator=titleKey.iterator();
			while(titleIterator.hasNext())
			{
				if (fieldType.equals("Float"))
					assertEquals(titleIterator.next().getClass(), Float.class);
				else
					assertTrue(fieldName+" is of not of type "+fieldType,false);
			}
		}

	}
}








