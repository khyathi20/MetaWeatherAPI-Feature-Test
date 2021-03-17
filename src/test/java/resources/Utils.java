package resources;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	//public static final String jsonPath = "/src/test/java/resources/consolidated_weather.json";
	PrintStream log;

	{
		try {
			log = new PrintStream(new FileOutputStream("logging.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public RequestSpecification requestSpecification(String locationName) throws IOException {

		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("query", locationName)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();

		return req;
	}

	public RequestSpecification requestSpecification() throws IOException {

		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();

		return req;
	}

	public static String getGlobalValue(String key) throws IOException {

		Properties prop = new Properties();
		String path = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path + "/src/test/java/resources/global.properties");
		prop.load(fis);

		return prop.getProperty(key);
	}
	
	public static String getTomorrowDate( String format){
	
	 Date date;
	 Format formatter;
	 Calendar calendar = Calendar.getInstance();
     calendar.add(Calendar.DATE, 1);
	 date = calendar.getTime();
	 formatter = new SimpleDateFormat(format);
	 return formatter.format(date);
	 
    }
}
