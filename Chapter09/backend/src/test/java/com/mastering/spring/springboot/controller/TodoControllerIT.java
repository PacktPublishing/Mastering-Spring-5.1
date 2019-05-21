package com.mastering.spring.springboot.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mastering.spring.springboot.Application;
import com.mastering.spring.springboot.bean.Todo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerIT {

	@Autowired
	private TestRestTemplate template;

	HttpHeaders headers = createHeaders("user-name", "user-password");

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

	@Test
	public void retrieveTodos() throws Exception {
		String expected = "[" + "{id:1,user:Jack,desc:\"Learn Spring MVC\",done:false}" + ","
				+ "{id:2,user:Jack,desc:\"Learn Struts\",done:false}" + "]";

		ResponseEntity<String> response = template.exchange("/users/Jack/todos", HttpMethod.GET,
				new HttpEntity<String>(null, headers), String.class);

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void retrieveTodo() throws Exception {
		String expected = "{id:1,user:Jack,desc:\"Learn Spring MVC\",done:false}";

		ResponseEntity<String> response = template.getForEntity("/users/Jack/todos/1", String.class);

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void addTodo() throws Exception {
		Todo todo = new Todo(-1, "Jill", "Learn Hibernate", new Date(), false);
		URI location = template.postForLocation("/users/Jill/todos", todo);
		assertThat(location.getPath(), containsString("/users/Jill/todos/5"));
	}

	@Test
	public void updatedTodo() throws Exception {

		String expected = "{id:4,user:Jill,desc:\"Learn Spring MVC 5\",done:false}";

		Todo todo = new Todo(4, "Jill", "Learn Spring MVC 5", new Date(), false);

		ResponseEntity<String> response = template.exchange("/users/Jill/todos/" + todo.getId(), HttpMethod.PUT,
				new HttpEntity<>(todo, headers), String.class);

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void deleteTodo() throws Exception {

		ResponseEntity<String> response = template.exchange("/users/Jill/todos/3", HttpMethod.DELETE,
				new HttpEntity<>(null, headers), String.class);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}