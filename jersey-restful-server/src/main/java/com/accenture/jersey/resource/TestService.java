package com.accenture.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("testservices")
public class TestService {

	//http://localhost:8081/Java/Bootcamp/testservices/getUsername
	@GET
	@Path("/getUsername")
	public String getCustomerName(@QueryParam("name") String name) {
		return "Customer: " + name;
	}

	//http://localhost:8081/Java/Bootcamp/testservices/getParameterName/Francis
	@GET
	@Path("/getParameterName/{name}/{age}")
	public String getParameterName(@PathParam("name") String name,
			@PathParam("age") String age) {
		return "Customer: " + name + age;
	}

	//http://localhost:8081/Java/Bootcamp/testservices/getHeader
	@GET
	@Path("/getHeader")
	public String getHeader(@HeaderParam("User-Agent") String agent) {
		return "User-Agent: " + agent;
	}


}
