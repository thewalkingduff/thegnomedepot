package com.devduffy.gnomedepot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devduffy.gnomedepot.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	// @Query("SELECT p FROM Product p where p.name = :name")
	// public List<Product> findByProductName(String name);

	// // select * from Product where lower(name) like lower('%name%') order by name desc
	// public List<Product> findByNameContainingIgnoreCaseOrderByNameDesc(String name);
	

	// @Query("SELECT p FROM Product p where p.name like :name OR p.category like :category")
	// public List<Product> findByNameOrCategory(String name, String category);

	// public Product findById(Integer id);
	Optional<Product> findById(Integer id);
	List<Product> findByName(String name);
	List<Product> findByCategory(String category);
	List<Product> findByStars(Integer stars);
	List<Product> findAll();
}
