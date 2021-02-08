package com.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.ecommerce.dto.SubCategoryDto;
import com.spring.ecommerce.model.SubCategory;

/*************************************************************************
 * {@link SubCategory} service class
 * 
 * @author Fahad Hasan
 * @since 2021-1-31
 *************************************************************************/
public interface SubCategoryService {

	/*************************************************************************
	 * Create a new SubCategory
	 * 
	 * @param ob {@link SubCategory} object
	 * @return {@link SubCategory}
	 *************************************************************************/
	SubCategory create(SubCategory ob, HttpServletResponse rs);

	/*************************************************************************
	 * Get all {@link SubCategory}
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	List<SubCategoryDto> getAllSubCategory();

	/*************************************************************************
	 * Get all {@link SubCategory} by isActive Flag
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	List<SubCategoryDto> getAllActiveSubCategory();

	/*************************************************************************
	 * Get all SubCategory {@link SubCategory} by Category Id
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	List<SubCategoryDto> getAllSubCategoryByCategoryId(String id);
}
