package com.uxpsystems.assignment.controller;



import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uxpsystems.assignment.model.User;


public class ContactValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		if (user.getUsername() == null || user.getUsername().length() < 1) {
			errors.rejectValue("name", "name.empty");
		}

	}

}
