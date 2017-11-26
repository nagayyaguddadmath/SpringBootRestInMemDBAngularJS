package com.uxpsystems.assignment;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.uxpsystems.assignment.dao.UserDAOImpl;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.model.UserStatus;
import com.uxpsystems.assignment.service.UserServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

public class UserServiceUnitTest {

	private static EntityManager entityManager;
	private static Query query;
	private static UserServiceImpl userServiceImpl = new UserServiceImpl();
	private static User user;


	@BeforeClass
	public static void setUp(){
		entityManager = mock(EntityManager.class);
		query = mock(Query.class);
		
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.setEm(entityManager);
		userServiceImpl.setUserDAO(userDAO);

		user = new User();
		user.setUserid(1);
		user.setUsername("username");
		user.setPassword("password");
		user.setStatus(UserStatus.Activated);
	}


	@Test
	public void findUserByNameSuccessCheck() throws Exception {
		List<User> usersExpected = new ArrayList<User>();
		usersExpected.add(user);

		Mockito.doReturn(query).when(entityManager).createQuery(anyString());
		Mockito.doReturn(query).when(query).setParameter(anyString(), anyString());
		Mockito.doReturn(usersExpected).when(query).getResultList();

		List<User> usersActual = userServiceImpl.getUsersByName("username");
		assertTrue("Should have return user", usersActual.size() == usersExpected.size());
		assertTrue("Should have return user", usersActual.get(0).getUsername() == usersExpected.get(0).getUsername());
	}

	@Test
	public void createUserSuccessCheck() throws Exception {

		user.setStatus(UserStatus.Activated);

		User usersActual = userServiceImpl.createNewUer(user);
		assertTrue("Should have return user", user.getUsername().equals(usersActual.getUsername()));
		assertTrue("Should have return user", user.getPassword().equals(usersActual.getPassword()));
		assertTrue("Should have return user", user.getStatus().equals(usersActual.getStatus()));
	}

	@Test
	public void updateUserCheck() throws Exception {
		String newusername = "usernameUPDATED";
		user.setUsername(newusername);

		Mockito.doReturn(user).when(entityManager).merge(user);

		User usersActual = userServiceImpl.updateUser(user);
		assertTrue("Should have return user", newusername.equals(usersActual.getUsername()));
	}

	@Test
	public void deleteUserCheck() throws Exception {
		List<User> usersExpected = new ArrayList<User>();
		usersExpected.add(user);

		Mockito.doReturn(query).when(entityManager).createQuery(anyString());
		Mockito.doReturn(query).when(query).setParameter(anyString(), anyString());
		Mockito.doReturn(usersExpected).when(query).getResultList();
		Mockito.doReturn(user).when(entityManager).merge(user);

		User usersActual = userServiceImpl.deleteUser(1);

		assertTrue("Should have return user", user.getUsername().equals(usersActual.getUsername()));
		//rollback change
		user.setStatus(UserStatus.Activated);
	}

	@Test(expected = Exception.class)
	public void userNameBlankInCreateThrowException() throws Exception {
		User user = new User();
		user.setPassword("password");
		user.setStatus(UserStatus.Activated);

		userServiceImpl.createNewUer(user);
		fail("Should have thrown exceptio");
	}

	@Test(expected = Exception.class)
	public void passwordBlankInCreateThrowException() throws Exception {
		User user = new User();
		user.setUsername("username");
		user.setStatus(UserStatus.Activated);

		userServiceImpl.createNewUer(user);
		fail("Should have thrown exceptio");
	}

	@Test(expected = Exception.class)
	public void userNameAndPasswordBlankInCreateThrowException() throws Exception {
		User user = new User();
		user.setStatus(UserStatus.Activated);

		userServiceImpl.createNewUer(user);
		fail("Should have thrown exceptio");
	}

}
