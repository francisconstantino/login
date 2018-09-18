package com.accenture.jersey.resource.security;

import java.util.Base64;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/securityservice")
public class SecurityService {

	//http://localhost:8081/Java/Bootcamp/securityservice/order/1
	/*
	 *
	 * Gets header information and does isUserAuthenticated method
	 */
	@GET
	@Path("/order/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderById(@PathParam("orderId") int orderID,
			@HeaderParam("Authorization") String authString) throws JSONException {

		JSONObject json = new JSONObject();
		if(isUserAuthenticated(authString)) {
			json.put("INFO", "Authorized User");
			return Response.status(200).entity(json.toString())
					.type(MediaType.APPLICATION_JSON).build();
		} else {
			json.put("INFO", "Unauthorized User");
			return Response.status(403).entity(json.toString())
					.type(MediaType.APPLICATION_JSON).build();
		}

	}

	private boolean isUserAuthenticated(String authString) {

		//sample authString = BASIC sadfdsadfasdf
		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];
		byte[] bytes = Base64.getDecoder().decode(authInfo);
		String decodedAuth = new String(bytes);

		//sample decodedAuth = sally:1234
		String[] credentials = decodedAuth.split(":");

		if(credentials[0].equals("djab") && credentials[1].equals("1234")) {
			return true;
		} else {
			return false;
		}
	}
}
