package com.uxpsystems.assignment.controller;

import java.util.List;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserServiceDAO;
/**
 * @author nagayya
 *
 * This class exposes REST web services for managing (CRUD Operations) of basic User management application
 * Please note that this application will store the given users to in-memory database
 *
 */
@CrossOrigin
@RestController
@ComponentScan("com.uxpsystems.assignment")
@ImportResource("classpath:spring-database.xml")
@EnableAutoConfiguration
@EnableTransactionManagement
public class UserController {

	@Autowired
	private UserServiceDAO userServiceDAO;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(UserController.class, args);
	}

	@RequestMapping(value ="/user", method=RequestMethod.GET)
	User getSingleUser(String username) throws Exception{
		List<User> users = userServiceDAO.getUsersByName(username);
		if (users == null || users.size() < 1) {
			return null;
		}/* else if (users.size() > 1){
			throw new Exception("Error: More than one user found with this user name:" + username);
		}*/

		return users.get(0);
	}

	@RequestMapping(value ="/user", method=RequestMethod.POST)
	User addUser(@RequestBody User user) throws Exception {
		userServiceDAO.createNewUer(user);
		return user;
	}

	@RequestMapping(value ="/user", method=RequestMethod.PUT)
	User updateUser(@RequestBody User user) throws Exception{
		return userServiceDAO.updateUser(user);
	}

	@RequestMapping(value ="/user", method=RequestMethod.DELETE)
	User deleteUser(int userid) throws Exception {
		return userServiceDAO.deleteUser(userid);
	}

	@RequestMapping(value ="/user/getusers", method=RequestMethod.GET)
	List<User> getAllUsers(String username) throws Exception{
		return userServiceDAO.getUsersByName(username);
	}


	public UserServiceDAO getUserDAOImpl() {
		return userServiceDAO;
	}


	public void setUserDAOImpl(UserServiceDAO userDAO) {
		this.userServiceDAO = userDAO;
	}



}
