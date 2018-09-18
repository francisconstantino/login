package com.accenture.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/jerseyresource")
public class JerseyResource {

	//http://localhost:8081/Java/Bootcamp/jerseyresource/testresponse
	@GET
	@Path("/testresponse")
	public String responsefromServer() {
		return "Response from Server";
	}

	//http://localhost:8081/Java/Bootcamp/jerseyresource/httptestresponse
	@GET
	@Path("/httptestresponse")
	public Response httpresponsefromServer() {
		return Response.status(403).entity("Response from Server").build();
	}
}
