package com.devduffy.gnomedepot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", nullable = false)
	private String name;

    @Column(name="category", nullable = false)
    private String category;

    @Column(name="image", nullable = false)
    private String image;

    @Column(name="description", nullable = false)
    private String description;

	@Column(name="stars", nullable = false)
	private Double stars;

	@Column(name="quantity_in_stock")
	private Integer quantityInStock;

	@Column(name="rating_count")
	private Integer ratingCount;

	@Column(name="price")
	private Double price;
	
}
