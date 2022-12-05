package com.devduffy.gnomedepot.dto;

import java.util.Date;
import java.util.List;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;
	private String username;
    private String email;
    private String password;
    // private String firstName;
    // private String lastName;
    // private String address1;
    // private String address2;
    // private String city;
	// private String state;
	// private String postalCode;
	// private String country;
	// private String email;
	// private String phone;
    // private Date dateOfBirth;
	// private Date createDate;
    // private List<Order> orders;

	
	
	// public User toModel() {
	// 	return new User(id, username, password, firstName, lastName, address1, address2, city, state, postalCode, country, email, phone, dateOfBirth, createDate, orders);
	// }
    public User toModel() {
		return new User(username, email, password);
	}
	
}