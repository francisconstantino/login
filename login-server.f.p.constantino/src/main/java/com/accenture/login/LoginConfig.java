package com.accenture.login;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.accenture.login.resource.user.UserService;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.accenture.login.domain"
})
public class LoginConfig extends ResourceConfig{

	public LoginConfig() {
		register(UserService.class);
	}
}
