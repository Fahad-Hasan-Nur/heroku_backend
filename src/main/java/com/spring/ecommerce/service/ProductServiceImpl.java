package com.spring.ecommerce.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.BrandRepo;
import com.spring.ecommerce.repository.CategoryRepo;
import com.spring.ecommerce.repository.ProductRepo;
import com.spring.ecommerce.repository.SubCategoryRepo;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

/*************************************************************************
 * {@link ProductServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-1-29
 *************************************************************************/
@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private BrandRepo brandRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private SubCategoryRepo typeRepo;

	/*************************************************************************
	 * Create a new Product
	 * 
	 * @param ob {@link Product} object
	 * @return {@link Product}
	 *************************************************************************/
	public Product create(Product product) {
		try {
			product.setBrand(brandRepo.findById(product.getBrandId()).orElse(null));
			product.setCategory(categoryRepo.findById(product.getCategoryId()).orElse(null));
			product.setSubCategory(typeRepo.findById(product.getSubCategoryId()).orElse(null));
			return productRepo.save(product);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			return product;
		}
	}

	/*************************************************************************
	 * Get all Product {@link Product}
	 * 
	 * @return {@link Product}
	 *************************************************************************/
	@Override
	public List<ProductDto> getAllProducts() {
		return productRepo.findAll().stream().map(this::getProjectDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all Product {@link Product} by isActive flag
	 * 
	 * @return {@link Product}
	 *************************************************************************/
	@Override
	public List<ProductDto> getAllActiveProducts() {
		return productRepo.findAllByIsActiveOrderByIdDesc(true).stream().map(this::getProjectDtoFromEntity)
				.collect(Collectors.toList());
	}

	public ProductDto getProjectDtoFromEntity(Product ob) {
		ob.setBrandId(ob.getBrand().getId());
		ob.setBrandName(ob.getBrand().getName());
		ob.setCategoryId(ob.getBrand().getId());
		ob.setCategoryName(ob.getCategory().getName());
		ob.setSubCategoryId(ob.getSubCategory().getId());
		ob.setSubCategoryName(ob.getSubCategory().getName());
		ProductDto obj = new ProductDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
