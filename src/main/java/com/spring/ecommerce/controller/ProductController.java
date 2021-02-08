package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Product} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-01-29
 *************************************************************************/
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

	@Autowired
	private ProductServiceImpl service;

	/*************************************************************************
	 * Create a new Product
	 * 
	 * @param ob {@link Product} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Product}
	 *************************************************************************/
	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		
		return service.create(product);
	}

	/*************************************************************************
	 * Get all Product {@link Product}
	 * 
	 * @return {@link List< Product>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<ProductDto> getAllProduct() {
		return service.getAllProducts();
	}

	 /*************************************************************************
     * Get all  active {@link  Product}
     * 
     * @return {@link List< Product>}
     *************************************************************************/
    @GetMapping("/getAll/active")
    public List<ProductDto> getAllActiveProduct() {
    	return service.getAllActiveProducts();
    }

//	@PostMapping("/addProducts")
//	public List<Product> addProducts(@RequestBody List<Product> product) {
//		return productservice.saveProducts(product);
//	}
//	
//	@GetMapping("/product/{id}")
//	public Product getProductById(@PathVariable Long id) {
//		return productservice.getProductById(id);
//	}
//	
//	@GetMapping("/product/search/{name}")
//	public List<Product> searchProductByName(@PathVariable String name) {
//		return productservice.getProductByName(name);
//	}
//	
//	@PutMapping("/update")
//	public Product updateProduct(@RequestBody Product product) {
//		return productservice.updateProduct(product);
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	public String deleteProduct(@PathVariable Long id) {
//		return productservice.deleteProduct(id);
//	}
}
