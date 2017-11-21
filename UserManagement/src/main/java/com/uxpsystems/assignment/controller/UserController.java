package com.uxpsystems.assignment.controller;

import java.util.List;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserServiceDAO;
import com.uxpsystems.assignment.service.UserServiceDAOImpl;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * @author nagayya
 *
 * This class exposes REST web services for managing (CRUD Operations) of basic User management application
 * Please note that this application will store the given users to in-memory database
 *
 */
@CrossOrigin//(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@ComponentScan("com.uxpsystems.assignment")
//@SpringBootApplication
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
	User getUser(String username) {
		List<User> users = userServiceDAO.getUsersByName(username);
		return (users != null && users.size() > 0) ? users.get(0) : null;
	}

	
	@RequestMapping(value ="/user", method=RequestMethod.POST)
	User addUser(@RequestBody User user) {
		userServiceDAO.createNewUer(user);
		return user;
	}

	@RequestMapping(value ="/user", method=RequestMethod.PUT)
	User updateUser(@RequestBody User user) {
		return userServiceDAO.updateUser(user);
	}

	@RequestMapping(value ="/user", method=RequestMethod.DELETE)
	boolean deleteUser(int userid) {
		return userServiceDAO.deleteUser(userid);
	}
/*
	@RequestMapping(value ="/getAll", method=RequestMethod.GET)
	List<User> getAllUsers() {
		return userDAOImpl.getAllUsers();
	}
*/

	/*
	@RequestMapping(value ="/getAll", method=RequestMethod.GET)
	List<Contact> getAll() {
		return allContacts;
	}

	@RequestMapping(value ="/sortByName", method=RequestMethod.GET)
	List<Contact> sortByName() {
		Collections.sort(allContacts, new Comparator<Contact>(){
			  public int compare(Contact p1, Contact p2){
			    return p1.getName().compareTo(p2.getName());
			  }
			});

//		allContacts.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
		return allContacts;
	}

	@RequestMapping(value ="/addContacts", method=RequestMethod.POST)
	List<Contact> addContacts(@Valid @RequestBody List<Contact> contacts) {
		allContacts.addAll(contacts);
		return contacts;
	}

	@RequestMapping(value ="/removeContact", method=RequestMethod.POST)
	Contact removeContact(@Valid @RequestBody Contact contact) {
		Contact tebeRemoved = getFirstSerachByName(contact.getName());
		allContacts.remove(tebeRemoved);
		return contact;

	}

	@RequestMapping(value ="/updateContact", method=RequestMethod.POST)
	Contact updateContact(@Valid @RequestBody Contact contact) {
		Contact tebeUpdated = getFirstSerachByName(contact.getName());
		allContacts.remove(tebeUpdated);
		allContacts.add(contact);
		return contact;

	}

	@RequestMapping(value ="/getContactByName", method=RequestMethod.GET)
	List<Contact> getContactByName(String name) {
		if (name == null || name.length() < 1) {
			return null;
		}
		List<Contact> outContacts = new ArrayList<Contact>();
		for (Contact contact: allContacts) {
			if (contact.getName().contains(name)) {
				outContacts.add(contact);
			}
		}

		return outContacts;
	}

	@RequestMapping(value ="/getFirstSerachByName", method=RequestMethod.GET)
	Contact getFirstSerachByName(String name) {
		if (name == null || name.length() < 1) {
			return null;
		}
		for (Contact contact: allContacts) {
			if (contact.getName().contains(name)) {
				return contact;
			}
		}

		return null;
	}


	@RequestMapping(value ="/searchContacts", method=RequestMethod.POST)
	List<Contact> searchContacts(@RequestBody Contact inContact) {
		List<Contact> outContacts = new ArrayList<Contact>();
		for (Contact contact: allContacts) {
			if ( (inContact.getName() == null || contact.getName().contains(inContact.getName())) &&
					(inContact.getPhone_number() == null || contact.getPhone_number().contains(inContact.getPhone_number())) &&
					(inContact.getAddress() == null || contact.getAddress().contains(inContact.getAddress())) ) {
				outContacts.add(contact);
			}
		}

		return outContacts;
	}
	 */


	public UserServiceDAO getUserDAOImpl() {
		return userServiceDAO;
	}


	public void setUserDAOImpl(UserServiceDAO userDAO) {
		this.userServiceDAO = userDAO;
	}



}
