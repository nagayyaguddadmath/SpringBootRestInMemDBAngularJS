package com.uxpsystems.assignment.dao;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<UserStatus, String> {

	@Override
	public String convertToDatabaseColumn(UserStatus status) {
		if (status == null)
			return null;
		return status.getStatus();
	}

	@Override
	public UserStatus convertToEntityAttribute(String status) {
		return UserStatus.getStatus(status);
	}

}