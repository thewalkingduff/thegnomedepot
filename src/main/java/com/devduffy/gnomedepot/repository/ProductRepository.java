package com.devduffy.gnomedepot.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devduffy.gnomedepot.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	// @Query("SELECT p FROM Product p where p.name = :name")
	// public List<Product> findByProductName(String name);

	// // select * from Product where lower(name) like lower('%name%') order by name desc
	// public List<Product> findByNameContainingIgnoreCaseOrderByNameDesc(String name);
	
// SELECT * FROM products p where p.category like "%Boo%" and p.id != 6;
	// @Query("SELECT p FROM Product p where p.category like %:category% and p.id != :prodId limit 1")
	// Product findSimilarProducts(String category, Integer prodId);
	@Query(value = "SELECT * FROM products p where p.category like %:category% and p.id != :prodId limit 1", nativeQuery = true)
	Product findSimilarProducts(String category, Integer prodId);

	Optional<Product> findById(Integer id);
	List<Product> findAll();
	List<Product> findByNameIgnoreCaseContaining(String name);
	
	
}
