package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.addPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class stepDefination extends Utils
{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data=new TestDataBuild();
	 static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	 
	  
	   res=given().spec(requestSpecification()).body(data.addAPIPayload(name,language,address
			   ));
	   
	   
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource,String httpMethod) {
	    // Write code here that turns the phrase above into concrete actions
		
		
		 resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			
		APIResources resou=APIResources.valueOf(resource);
		String ActualResource=resou.getResource();
		System.out.println(ActualResource);
		if(httpMethod.equalsIgnoreCase("POST"))
			response=res.when().post(ActualResource).then().spec(resspec).extract().response();
		
		else if(httpMethod.equalsIgnoreCase("GET"))
			response=res.when().get(ActualResource).then().spec(resspec).extract().response();
		
		
		else if(httpMethod.equalsIgnoreCase("DELETE"))
			response=res.when().get(ActualResource).then().spec(resspec).extract().response();
		
		
	   
	}
	@Then("the API call got success with the status Code {int}")
	public void the_api_call_got_success_with_the_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
		
	   
	}
	@Then("{string} in response body  is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  
		
		assertEquals(getJsonPath(response, keyValue),expectedValue);
		
	}

	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_created_maps_to_using(String expectedName, String resource) throws IOException {
	
		
		 place_id=getJsonPath(response, "place_id");
		
		res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		//need to run the get api using get method so we can use the existing method,
		
		user_calls_with_http_request(resource,"GET");
		
		String actualName=getJsonPath(response, "name");
		System.out.println("nam is "+actualName);
		assertEquals(actualName,expectedName);
		
		
	   //after running the above method ," Response response" get updated with latest output.
		
	}


	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		//no need to write pojo for one key value pair 
		//eg place_id
	   res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	   System.out.println();
	   
	}

}
