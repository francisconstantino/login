package com.accenture.login.resource.user;

import java.util.Base64;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.login.domain.User;
import com.accenture.login.domain.UserRepository;

@Service
@Path("/loginservice")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// localhost:8081/loginservice/getUser
	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User userInfo() {
		return userRepository.findByUsernameAndPassword("dj123", "1234");
	}

	@GET
	@Path("/getUserLast")
	public List<User> getByLastName() {
		return userRepository.findByLastName("Barrion");
	}

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogin(@HeaderParam("Authorization") String authString)
			throws JSONException {

		JSONObject json = new JSONObject();
		if (doesUserExist(authString)) {
			if (isUserAdmin(authString)) {
				json.put("INFO", "User is an admin");
				return Response.status(200).entity(json.toString())
						.type(MediaType.APPLICATION_JSON).build();
			} else {
				json.put("INFO", "User is valid");
				return Response.status(200).entity(json.toString())
						.type(MediaType.APPLICATION_JSON).build();
			}
		} else {
			json.put("INFO", "User Doesn't Exist in Database");
			return Response.status(403).entity(json.toString())
					.type(MediaType.APPLICATION_JSON).build();
		}

	}

	private boolean doesUserExist(String authString) {

		// sample authString = BASIC sadfdsadfasdf
		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];
		byte[] bytes = Base64.getDecoder().decode(authInfo);
		String decodedAuth = new String(bytes);

		// sample decodedAuth = sally:1234
		String[] credentials = decodedAuth.split(":");

		User user = userRepository.findByUsernameAndPassword(credentials[0],
				credentials[1]);

		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isUserAdmin(String authString) {
		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];
		byte[] bytes = Base64.getDecoder().decode(authInfo);
		String decodedAuth = new String(bytes);

		// sample decodedAuth = sally:1234
		String[] credentials = decodedAuth.split(":");
		User user = userRepository.findByUsernameAndPassword(credentials[0],
				credentials[1]);
		if (user.getRole().equals("admin")) {
			return true;
		} else {
			return false;
		}
	}
}
