package com.accenture.jersey.resource.json;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/userservice")
public class UserService {

	//http://localhost:8081/Java/Bootcamp/userservice/getuser
	@GET
	@Path("/getuser")
	@Produces(MediaType.APPLICATION_JSON)
	public User userinfo() {
		User userinfo = new User();
		userinfo.setContactno(123234);
		userinfo.setCountry("Philippines");
		userinfo.setId(12222);
		userinfo.setName("Java");
		return userinfo;
	}

	@POST
	@Path("/getuserinfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User usergetinfo(User user) {
		return user;
	}

	//http://localhost:8081/Java/Bootcamp/userservice/login
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String user) throws JSONException{
		JSONObject json = new JSONObject(user);
		String email = json.getString("email");
		String password = json.getString("password");
		
		JSONObject jsonresponse = new JSONObject();

		if("dj@accenture.com".equalsIgnoreCase(email) && "1234".equalsIgnoreCase(password)) {
			jsonresponse.put("Info", "Valid Credentials");
			return Response.status(200).entity(jsonresponse.toString())
					.type(MediaType.APPLICATION_JSON).build();
		} else {
			jsonresponse.put("Error", "Login Failed");
			return Response.status(403).entity(jsonresponse.toString())
					.type(MediaType.APPLICATION_JSON).build();
		}
	}
}
