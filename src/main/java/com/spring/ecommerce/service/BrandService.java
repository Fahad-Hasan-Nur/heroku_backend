package com.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.spring.ecommerce.dto.BrandDto;
import com.spring.ecommerce.model.Brand;



/*************************************************************************
* {@link  Brand} service class 
* 
* @author Fahad Hasan
* @since  2021-1-29
*************************************************************************/
public interface BrandService {

	/*************************************************************************
     * Create a new  Brand
     * @param ob {@link  Brand} object
     * @return {@link  Brand}
     *************************************************************************/
	 Brand create( Brand ob,HttpServletResponse rs);
	
	  /*************************************************************************
     * Get all  {@link  Brand} 
     * @return {@link List< Brand>}
     *************************************************************************/
	List< BrandDto> getAllBrand();
	
	  /*************************************************************************
     * Get all  {@link  Brand} by isActive Flag
     * @return {@link List< Brand>}
     *************************************************************************/
	List<BrandDto> getAllActiveBrand();
}
