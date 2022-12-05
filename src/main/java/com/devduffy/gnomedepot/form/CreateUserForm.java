package com.devduffy.gnomedepot.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.devduffy.gnomedepot.validation.EmailUnique;
import com.devduffy.gnomedepot.validation.Username;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserForm {
	
	@Email(message = "Invalid Email")
	@EmailUnique(message = "Email is already in use")
	@NotBlank(message = "Email cannot be blank")
	@NotEmpty(message = "Email is required.")
	@Length(max = 200, message = "Email must be less than 200 characters.")
	private String email;

    @Username(message = "Username cannot contain special or uppercase charcaters")
	@NotBlank(message = "Username cannot be blank")
    @Size(min = 7, message = "Username is too short")
	@Size(max =45, message = "Username can not be longer than 45 characters")
	private String username;
	
	@Pattern(regexp = "^[a-zA-Z0-9!@#]+$", message = "Password can only contain lowercase, uppercase, and special caracters")
	@Length(min = 8, message = "Password must be longer than 8 characters.")
	@Length(max = 25, message = "Password must be shorter than 25 characters.")
	private String password;
	
	@NotEmpty(message = "Confirm password is required.")
	private String confirmPassword;
	
	@NotBlank(message = "First name cannot be blank")
    @Size(min = 2, message = "First name is too short")
	@Size(max =45, message = "Firstname can not be longer than 45 characters")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, message = "Last name is too short")
	@Size(max =45, message = "Last name can not be longer than 45 characters")
	private String lastName;

	@NotBlank(message = "Street address cannot be blank") 
	@Size(max =100, message = "Address can not be longer than 100 characters")
	private String address;
	
	@NotBlank(message = "City cannot be blank")
	@Length(max = 45, message = "City must be less than 45 characters.")
	private String city;
	
	@NotBlank(message = "State cannot be blank")
	@Length(max = 45, message = "State must be less than 45 characters.")
	private String state;
	
	@NotBlank(message = "Zip Code cannot be blank")
	@Length(max = 45, message = "Zip code must be less than 45 characters.")
	private String zip;
	
	@Length(max = 45, message = "Phone number must be less than 45 characters.")
	private String phone;
	
}

