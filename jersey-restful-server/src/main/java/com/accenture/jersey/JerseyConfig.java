package com.accenture.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.accenture.jersey.resource.JerseyResource;
import com.accenture.jersey.resource.TestService;
import com.accenture.jersey.resource.json.UserService;
import com.accenture.jersey.resource.security.SecurityService;
import com.accenture.jersey.resource.xml.CountryService;

@Configuration
public class JerseyConfig extends ResourceConfig{

	public JerseyConfig() {
		register(JerseyResource.class);
		register(TestService.class);
		register(CountryService.class);
		register(UserService.class);
		register(SecurityService.class);
	}
}
