package com.uxpsystems.assignment.service;

import java.util.List;

import com.uxpsystems.assignment.model.User;

public interface UserServiceDAO {

	List<User> getUsersByName(String username);
	
	List<User> getAllUsers();
	
	void createNewUer(User user);
	
	boolean deleteUser(int userid);
	
	User updateUser(User user);
	
}
