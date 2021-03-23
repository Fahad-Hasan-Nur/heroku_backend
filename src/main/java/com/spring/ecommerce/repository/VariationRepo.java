package com.spring.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Variation;


public interface VariationRepo extends JpaRepository<Variation, String> {

	List<Variation> findAllByProduct(Product product);
}
