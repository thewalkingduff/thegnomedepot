package com.devduffy.gnomedepot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
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
	
	@Column(name="username", nullable = false)
	private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="firstname", nullable = false)
	private String firstname;

    @Column(name="lastname", nullable = false)
	private String lastname;

    @Column(name="address1", nullable = false)
    private String address1;

    @Column(name="address2")
    private String address2;

    @Column(name="city", nullable = false)
    private String city;

	@Column(name="state", nullable = false)
	private String state;

	@Column(name="postal_code")
	private String postalCode;

	@Column(name="country")
	private String country;

	@Column(name="email", unique = true)
	private String email;

	@Column(name="phone")
	private String phone;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Order> orders;

}