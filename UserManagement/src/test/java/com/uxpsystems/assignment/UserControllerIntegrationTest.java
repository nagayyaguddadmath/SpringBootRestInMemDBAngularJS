package com.uxpsystems.assignment;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

//import com.uxpsystems.assignment.config.WebConfig;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.model.UserStatus;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration//(classes = {WebConfig.class})
public class UserControllerIntegrationTest {

	private static final String BASE_URI = "http://localhost:8080/user";
	private String userNameAuth = "admin";
	private String userPassAuth = "password";

	@Autowired
	private RestTemplate template;


	HttpHeaders createHeaders(final String username, final String password){
		return new HttpHeaders() {{
			String auth = username + ":" + password;
			byte[] encodedAuth = Base64.encodeBase64( 
					auth.getBytes(Charset.forName("US-ASCII")) );
			String authHeader = "Basic " + new String( encodedAuth );
			set( "Authorization", authHeader );
		}};
	}
	// =========================================== Create New User ========================================

	//	@Test
	public void test_create_new_user_success1(){
		User newUser = new User();
		newUser.setUsername("new username");
		newUser.setPassword("password");
		newUser.setStatus(UserStatus.Activated);

		HttpEntity<User> httpEntity  = new HttpEntity<User>(newUser, createHeaders(userNameAuth, userPassAuth));
		//		User user = template.postForObject(BASE_URI, newUser, User.class);
		ResponseEntity<User> result =template.exchange(BASE_URI, HttpMethod.POST, httpEntity , User.class);
		User user = result.getBody();

		assertThat(user, notNullValue());
		assertThat(user.getUsername(), is("new username"));
		assertThat(user.getPassword(), is("password"));
		assertThat(user.getStatus(), is(UserStatus.Activated));
	}


}
