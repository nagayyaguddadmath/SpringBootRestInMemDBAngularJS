package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.uxpsystems.assignment.dao.UserDAO;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.model.UserStatus;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;

	private static int userId = 1;

	@Override
	public List<User> getUsersByName(String username) throws Exception {

		List<User> users = userDAO.getUsersByName(username);
		if (users == null || users.size() < 1) {
			throw new Exception("User Not found in the database");
		}

		return users;
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public User createNewUer(User user) throws Exception {

		if ( (user.getUsername() == null || user.getUsername().length() < 1)  ||
				(user.getPassword() == null || user.getPassword().length() < 1)) {
			throw new Exception("UserName/Password cannot be blank");
		} else if (!user.getStatus().equals(UserStatus.Activated)) {
			throw new Exception("Cannot create Deactivated user!!");
		}
		User userObj = new User();
		userObj.setUserid(userId++);
		userObj.setUsername(user.getUsername());
		userObj.setPassword(user.getPassword());
		userObj.setStatus(UserStatus.Activated);

		return userDAO.createNewUer(userObj);
	}

	@Override
	public User deleteUser(int userid) throws Exception {
		User user = userDAO.findUserById(userid);
		user.setStatus(UserStatus.Deactivated);

		return userDAO.deleteUser(user);
	}

	@Override
	public User updateUser(User user) throws Exception {
		if (user.getUserid() == 0 ||  (user.getUsername() == null || user.getUsername().length() < 1)  ||
				(user.getPassword() == null || user.getPassword().length() < 1)) {
			throw new Exception("User not found hence cannot update");
		}

		userDAO.findUserById(user.getUserid());
		return userDAO.updateUser(user);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
