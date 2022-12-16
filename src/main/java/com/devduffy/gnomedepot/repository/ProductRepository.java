package com.devduffy.gnomedepot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	@Query("SELECT p FROM Product p where p.name = :name")
	public List<Product> findByProductName(String name);

	@Query(value = "SELECT * FROM products p where p.category like %:category% and p.id != :prodId limit 1", nativeQuery = true)
	Product findSimilarProducts(String category, Integer prodId);

	Optional<Product> findById(Integer id);
	List<Product> findAll();
	List<Product> findByNameIgnoreCaseContaining(String name);
}
