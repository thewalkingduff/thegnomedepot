package com.devduffy.gnomedepot.entity;

import java.util.Date;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.devduffy.gnomedepot.validation.Age;
import com.devduffy.gnomedepot.validation.Username;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import lombok.ToString;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Username(message = "cannot contain special or uppercase charcaters")
	@NonNull
	@NotBlank(message = "Username cannot be blank")
    @Size(min = 7, message = "Username is too short")
	@Column(name="username", nullable = false)
	private String username;

	@NonNull
	@NotBlank(message = "Password cannot be blank")
	@Size(min = 7, message = "Password is too short")
    @Column(name="password", nullable = false)
    private String password;

	@NonNull
	@NotBlank(message = "First name cannot be blank")
    @Size(min = 2, message = "First name is too short")
    @Column(name="firstname", nullable = false)
	private String firstName;

	@NonNull
	@NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, message = "Last name is too short")
    @Column(name="lastname", nullable = false)
	private String lastName;

	@NonNull
	@NotBlank(message = "Street address cannot be blank")
    @Column(name="address1", nullable = false)
    private String address1;

    @Column(name="address2")
    private String address2;

	@NonNull
	@NotBlank(message = "City cannot be blank")
    @Column(name="city", nullable = false)
    private String city;

	@NotBlank(message = "State cannot be blank")
	@Column(name="state", nullable = false)
	private String state;

	@NonNull
	@NotBlank(message = "Zip Code cannot be blank")
	@Column(name="postal_code", nullable = false)
	private String postalCode;

	@NonNull
	@NotBlank(message = "Country cannot be blank")
	@Column(name="country", nullable = false)
	private String country;

	@NonNull
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid Email")
	@Column(name="email", nullable = false, unique = true)
	private String email;

	@NonNull
	@NotBlank(message = "Phone cannot be blank")
	@Column(name="phone")
	private String phone;

	@Age(message = "Must be at least 18")
	@NonNull
    @Past(message = "date of birth must be in the past")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="date_of_birth", nullable = false)
    private Date dateOfBirth;

	@ToString.Exclude
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Order> orders;


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


}