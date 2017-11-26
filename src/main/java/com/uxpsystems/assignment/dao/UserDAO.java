package com.uxpsystems.assignment.dao;

import java.util.List;

import com.uxpsystems.assignment.model.User;

public interface UserDAO {

	List<User> getUsersByName(String username) throws Exception;
	
	List<User> getAllUsers();
	
	User createNewUer(User user) throws Exception;
	
	User deleteUser(User userid) throws Exception ;
	
	User updateUser(User user) throws Exception;
	
	User findUserById(int userid) throws Exception;

}
