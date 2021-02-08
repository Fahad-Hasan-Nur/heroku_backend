package com.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.spring.ecommerce.dto.CategoryDto;
import com.spring.ecommerce.model.Category;



/*************************************************************************
* {@link Category} service class 
* 
* @author Fahad Hasan
* @since  2021-1-29
*************************************************************************/
public interface CategoryService {

	/*************************************************************************
     * Create a new Category
     * @param ob {@link Category} object
     * @return {@link Category}
     *************************************************************************/
	Category create(Category ob,HttpServletResponse rs);
	
	  /*************************************************************************
     * Get all  {@link Category} 
     * @return {@link List<Category>}
     *************************************************************************/
	List<CategoryDto> getAllCategory();
	
	  /*************************************************************************
     * Get all  {@link Category} by isActive Flag
     * @return {@link List<Category>}
     *************************************************************************/
	List<CategoryDto> getAllActiveCategory();
}
