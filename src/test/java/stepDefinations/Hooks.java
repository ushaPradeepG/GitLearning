package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before
	public void beforeDeletePlace() throws IOException
	{
		//1.write a code to get the place id
		//2.execute if place-id is only null
		
		
		stepDefination m=new stepDefination();
		if(stepDefination.place_id == null)
		{
		m.add_place_payload_with("usha", "Kannada", "ksjdahkjsa");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_created_maps_to_using("usha", "GetPlaceAPI");
		}
		
	}

}
