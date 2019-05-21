package com.mastering.spring.springboot.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastering.spring.springboot.SpringSecurityApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSecurityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicControllerIT {

	private static final String LOCAL_HOST = "http://localhost:";

	@LocalServerPort
	private int port;

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

	@Test
	public void welcome() throws Exception {
		ResponseEntity<String> response = getOAuthTemplate().getForEntity(createURL("/welcome"), String.class);
		// ResponseEntity<String> response = template.getForEntity("/welcome",
		// String.class);
		assertThat(response.getBody(), equalTo("Hello World"));
	}

	@Test
	public void welcomeWithObject() throws Exception {
		ResponseEntity<String> response = getOAuthTemplate().getForEntity(createURL("/welcome-with-object"),
				String.class);
		assertThat(response.getBody(), containsString("Hello World"));
	}

	@Test
	public void welcomeWithParameter() throws Exception {
		ResponseEntity<String> response = getOAuthTemplate()
				.getForEntity(createURL("/welcome-with-parameter/name/Buddy"), String.class);
		assertThat(response.getBody(), containsString("Hello World, Buddy"));
	}

	private OAuth2RestTemplate getOAuthTemplate() {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
		resource.setUsername("user2");
		resource.setPassword("user2-password");
		resource.setAccessTokenUri(createURL("/oauth/token"));
		resource.setClientId("YourClientID");
		resource.setClientSecret("TopSecretClientPassword");
		resource.setGrantType("password");
		// resource.setAuthenticationScheme(AuthenticationScheme.header);
		// resource.
		OAuth2RestTemplate oauthTemplate = new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext());
		return oauthTemplate;
	}

	private String createURL(String uri) {
		return LOCAL_HOST + port + uri;
	}

}