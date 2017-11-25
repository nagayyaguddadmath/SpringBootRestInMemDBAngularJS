package com.uxpsystems.assignment.model;

import javax.persistence.AttributeConverter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PasswordEncoder  implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String arg0) {
		byte[] encodedBytes = Base64.getEncoder().encode(arg0.getBytes(StandardCharsets.UTF_8));
		//		System.out.println("encodedBytes " + new String(encodedBytes));
		return new String(encodedBytes);
	}

	@Override
	public String convertToEntityAttribute(String arg0) {
		byte[] decodedBytes = Base64.getDecoder().decode(arg0.getBytes(StandardCharsets.UTF_8));
		//		System.out.println("decodedBytes " + new String(decodedBytes));

		return new String(decodedBytes);
	}

}
