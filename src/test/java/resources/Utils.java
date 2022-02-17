package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;

	public static RequestSpecification requestSpecification() throws IOException
	{
		int i=1;
			
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging"+i+".txt"));
		 RequestSpecification req=new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURI")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
				 .setContentType(ContentType.JSON).build();
		 i++;
			return req;
		}
		i++;
		return req;
		
	}
	 
	
	public static String getGlobalValues(String key) throws IOException 
	{
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Usha Pradeep\\eclipse-workspace\\ApiFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
	    String keyvalue=prop.getProperty(key);
		return keyvalue;
		
	}
	
	public static String getJsonPath(Response response,String key) throws IOException 
	{
		JsonPath js=new JsonPath(response.asString());
		
		return js.get(key);
		
	}

}

