package com.spring.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Brand;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;

public interface ProductRepo extends JpaRepository<Product,String>{

	List<Product> findByName(String name);

	List<Product> findAllByIsActiveOrderByIdDesc(boolean isActive);

	List<Product> findAllByCategory(Category ob);

	List<Product> findByBrand(Brand ob);
	

}
