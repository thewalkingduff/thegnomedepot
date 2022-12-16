package com.devduffy.gnomedepot.dto;

import com.devduffy.gnomedepot.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
	private String name;
    private String category;
    private String image;
	private String description;
    private Double stars;
	private Integer quantityInStock;
	private Integer ratingCount;
	private Double price;

    public Product toModel() {
        return new Product(id, name, category, image, description, stars,
        quantityInStock, ratingCount, price);
    }
}