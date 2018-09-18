package com.accenture.jersey.resource.xml;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/countryservice")
public class CountryService {

	//http://localhost:8081/Java/Bootcamp/countryservice/getCountry
	@GET
	@Path("/getCountry")
	@Produces(MediaType.APPLICATION_XML)
	public Country getCountry() {
		Country country = new Country("Philippines", "Manila", 123344);
		return country;
	}

	//http://localhost:8081/Java/Bootcamp/countryservice/getcountryDetails
	//accepts country of xml format
	@POST
	@Path("/getcountryDetails")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Country getCountryDetails(Country country) {
		return country;
	}
}
