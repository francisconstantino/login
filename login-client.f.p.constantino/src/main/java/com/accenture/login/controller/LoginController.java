package com.accenture.login.controller;

import java.util.Base64;

import javax.ws.rs.core.MediaType;

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

	private static Logger log = LoggerFactory
			.getLogger(Application.class);

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam String username, String password, ModelAndView mv) {
		String url = "http://localhost:8081/loginservice/login";
		String authString = username + ":" + password;
		String authStr = Base64.getEncoder().encodeToString(authString.getBytes());

		Client client = new Client();
		WebResource wr = client.resource(url);
		ClientResponse response = wr.type(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic " + authStr).get(ClientResponse.class);
		String output = response.getEntity(String.class);

		mv.addObject("response", output);
		mv.setViewName("welcome");
		return mv;
	}
}
