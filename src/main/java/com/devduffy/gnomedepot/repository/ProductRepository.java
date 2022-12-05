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
public interface ProductRepository extends CrudRepository<Product, Integer> {
	// @Query("SELECT p FROM Product p where p.name = :name")
	// public List<Product> findByProductName(String name);

	// // select * from Product where lower(name) like lower('%name%') order by name desc
	// public List<Product> findByNameContainingIgnoreCaseOrderByNameDesc(String name);
	

	// @Query("SELECT p FROM Product p where p.name like :name OR p.category like :category")
	// public List<Product> findByNameOrCategory(String name, String category);

	Optional<Product> findById(Integer id);
	List<Product> findAll();
	
	// @Query(value = "select * from products p where p.name = :word", nativeQuery = true)
	// @Query("select p from products p where p.name like %:word%")
	// List<Product> findByKeyword(String word);

	List<Product> findByNameIgnoreCaseContaining(String name);
	List<Product> productSearchResults = new ArrayList<Product>();
	
}
