package com.accenture.login.controller;

import java.util.Base64;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.login.Application;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(Application.class);

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam String username, String password,
			ModelAndView mv) {
		String url = "http://localhost:8081/userservice/login";
		String authString = username + ":" + password;
		String authStr = Base64.getEncoder().encodeToString(
				authString.getBytes());

		Client client = new Client();
		WebResource wr = client.resource(url);
		ClientResponse response = wr.type(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic " + authStr)
				.get(ClientResponse.class);

		String output = response.getEntity(String.class);


		if (output.contains("User is an admin")) {
			mv.setViewName("crudDisplay");
		} else if (output.contains("User is valid")) {
			mv.addObject("username", username);
			mv.setViewName("welcome");
		} else {
			mv.setViewName("index");
		}
		return mv;
	}

	@RequestMapping("/home")
	public String homeView() {
		return "crudDisplay";
	}
	@RequestMapping("/add")
	public String add() {
		return "add";
	}

	@RequestMapping("/addUser")
	public ModelAndView addUser(@RequestParam String firstName, String lastName,
			String username, String password, String email, String role, ModelAndView mv) throws JSONException {

		JSONObject json = new JSONObject();
		json.put("firstName", firstName);
		json.put("lastName", lastName);
		json.put("username", username);
		json.put("password", password);
		json.put("email", email);
		json.put("role", role);

		Client client = new Client();
		WebResource wr = client.resource("http://localhost:8081/userservice/addUser");

		ClientResponse response = wr.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, json.toString());

		mv.setViewName("crudDisplay");
		return mv;
	}

	@RequestMapping("/update")
	public ModelAndView updateView(@RequestParam String id, ModelAndView mv) {
		System.out.println(id);
		mv.addObject("id", id);
		mv.setViewName("updateData");
		return mv;
	}

	@RequestMapping("/delete")
	public String deleteView() {
		return "delete";
	}

	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser (@RequestParam String id, ModelAndView mv) {
		Client client = new Client();
		WebResource wr = client.resource("http://localhost:8081/userservice/deleteUser/" + id);
		System.out.println(id);
		ClientResponse response = wr.delete(ClientResponse.class);
		mv.setViewName("delete");
		return mv;
	}

	@RequestMapping("/updateUser")
	public ModelAndView updateUser(@RequestParam String id, String username,
			String password, String firstName, String lastName, String email, String role, ModelAndView mv) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("firstName", firstName);
		json.put("lastName", lastName);
		json.put("username", username);
		json.put("password", password);
		json.put("email", email);
		json.put("role", role);

		Client client = new Client();
		WebResource wr = client.resource("http://localhost:8081/userservice/updateUser/" + id);

		ClientResponse response = wr.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, json.toString());
		mv.setViewName("crudDisplay");
		return mv;

	}
}
