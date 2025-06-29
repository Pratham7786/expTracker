package com.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class userEntity 
{
	Integer userid;
	
	@NotBlank(message = "Please enter First Name")
	@Pattern(regexp = "[a-zA-Z\\s]+",message = "Please enter a valid First Name")
	String firstname;
	
	@NotBlank(message = "Please enter Last Name")
	@Pattern(regexp = "[a-zA-Z\\s]+",message = "Please enter a valid Last Name")
	String lastname;
	
	@NotBlank(message = "Please enter Email")
	@Email(message = "Please enter valid Email")
	String email;
	
	@NotBlank(message = "Please enter Password")
	String password;
	
	@NotBlank(message = "Please select Gender")
	String gender;
	
	String role = "user";
	
	LocalDate createdat = LocalDate.now();
	
	String profilepicpath;
}