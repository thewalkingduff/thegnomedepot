package com.devduffy.gnomedepot.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devduffy.gnomedepot.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	// @Query("SELECT p FROM Product p where p.name = :name")
	// public List<Product> findByProductName(String name);

	// // select * from Product where lower(name) like lower('%name%') order by name desc
	// public List<Product> findByNameContainingIgnoreCaseOrderByNameDesc(String name);
	

	// @Query("SELECT p FROM Product p where p.name like :name OR p.category like :category")
	// public List<Product> findByNameOrCategory(String name, String category);

	// public Product findById(Integer id);
	List<Product> findByProductId(Integer id);
	List<Product> findByProductName(String name);
	List<Product> findByProductCategory(String category);
	List<Product> findByProductStars(Integer stars);
}
