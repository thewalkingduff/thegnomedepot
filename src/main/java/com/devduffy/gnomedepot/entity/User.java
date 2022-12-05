package com.devduffy.gnomedepot.entity;


import java.util.Date;
import java.util.List;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.devduffy.gnomedepot.dto.UserDTO;
import com.devduffy.gnomedepot.validation.Age;
import com.devduffy.gnomedepot.validation.EmailUnique;
import com.devduffy.gnomedepot.validation.Username;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	// @Username(message = "Username cannot contain special or uppercase charcaters")
	// @NonNull
	// @NotBlank(message = "Username cannot be blank")
    // @Size(min = 7, message = "Username is too short")
	// @Size(max =45, message = "Username can not be longer than 45 characters")
	@Column(name="username", nullable = false)
	private String username;

	@NonNull
	// @Pattern(regexp = "^[a-zA-Z0-9!@#]+$", message = "Password can only contain lowercase, uppercase, and special caracters")
	// @NotBlank(message = "Password cannot be blank")
	// @Size(min = 7, message = "Password is too short")
	// @Size(max =200, message = "Password can not be longer than 200 characters")
    @Column(name="password", nullable = false)
    private String password;

    @Column(name="firstname", nullable = false)
	private String firstName;


    @Column(name="lastname", nullable = false)
	private String lastName;

	@Column(name="address1", nullable = false)
    private String address1;

    @Column(name="address2")
    private String address2;

    @Column(name="city", nullable = false)
    private String city;

	@Column(name="state", nullable = false)
	private String state;

	@Column(name="postal_code", nullable = false)
	private String postalCode;

	@Column(name="country", nullable = false)
	private String country;

	@Column(name="email", nullable = false, unique = true)
	private String email;

	// @NotBlank(message = "Phone cannot be blank")
	@Column(name="phone")
	private String phone;

	// @Age(message = "Must be at least 18")
	// @NonNull
    // @Past(message = "date of birth must be in the past")
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="date_of_birth", nullable = false)
    private Date dateOfBirth;

	@Column(name = "create_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@ToString.Exclude
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Order> orders;

	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		
	}

	public UserDTO toDTO () {
		return new UserDTO();
	}

}