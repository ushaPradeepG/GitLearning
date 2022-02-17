package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addPlace;

public class TestDataBuild {
	
	public addPlace addAPIPayload(String name,String language,String address)
	{
		addPlace p=new addPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("293129312");
	   p.setWebsite("dasdasd");
	   p.setName(name);
	   List<String> myList=new ArrayList<String>();
	   myList.add("shoePark");
	   myList.add("shop");
	   p.setTypes(myList);
	   Location l=new Location();
	   l.setLng(323);
	   l.setLat(231);
	   p.setLocation(l);
	   return p;
	}
	
	

	public String deletePlacePayload(String place_id)
	{
		
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
	}
}
