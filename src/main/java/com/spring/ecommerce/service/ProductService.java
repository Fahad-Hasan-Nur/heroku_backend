package com.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;


/*************************************************************************
* {@link Product} service class 
* 
* @author Fahad Hasan
* @since  2021-1-29
*************************************************************************/
public interface ProductService {

	/*************************************************************************
     * Create a new Product
     * @param ob {@link Product} object
     * @return {@link Product}
     *************************************************************************/
	Product create(Product ob);
	
	  /*************************************************************************
     * Get all  {@link Product} 
     * @return {@link List<Product>}
     *************************************************************************/
	List<ProductDto> getAllProducts();
	
	  /*************************************************************************
     * Get all  {@link Product} by isActive Flag
     * @return {@link List<Product>}
     *************************************************************************/
	List<ProductDto> getAllActiveProducts();
}
