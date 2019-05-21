package com.mastering.spring.springboot.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mastering.spring.springboot.SpringSecurityApplication;
import com.mastering.spring.springboot.bean.Todo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringSecurityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerIT {

//	@Autowired
//	private TestRestTemplate template;
//
//	HttpHeaders headers = createHeaders("user-name", "user-password");
//
//	HttpHeaders createHeaders(String username, String password) {
//		return new HttpHeaders() {
//			{
//				String auth = username + ":" + password;
//				byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
//				String authHeader = "Basic " + new String(encodedAuth);
//				set("Authorization", authHeader);
//			}
//		};
//	}

	@LocalServerPort
	private int port;

	@Test
	public void retrieveTodos() throws Exception {
		String expected = "[" + "{id:1,user:Jack,desc:\"Learn Spring MVC\",done:false}" + ","
				+ "{id:2,user:Jack,desc:\"Learn Struts\",done:false}" + "]";
		String uri = "/users/Jack/todos";
		ResponseEntity<String> response = getOAuthTemplate().getForEntity(createUrl(uri), String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private OAuth2RestTemplate getOAuthTemplate() {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
		resource.setUsername("user2");
		resource.setPassword("user2-password");
		resource.setAccessTokenUri(createUrl("/oauth/token"));
		resource.setClientId("YourClientID");
		resource.setClientSecret("TopSecretClientPassword");
		resource.setGrantType("password");
		OAuth2RestTemplate oauthTemplate = new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext());
		return oauthTemplate;
	}

	@Test
	public void retrieveTodo() throws Exception {
		String expected = "{id:1,user:Jack,desc:\"Learn Spring MVC\",done:false}";

		ResponseEntity<String> response = getOAuthTemplate().getForEntity(createUrl("/users/Jack/todos/1"),
				String.class);

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void addTodo() throws Exception {
		Todo todo = new Todo(-1, "Jill", "Learn Hibernate", new Date(), false);
		URI location = getOAuthTemplate().postForLocation(createUrl("/users/Jill/todos"), todo);
		assertThat(location.getPath(), containsString("/users/Jill/todos/"));
	}

	private String createUrl(String uri) {
		return "http://localhost:" + port + uri;
	}
}