package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.SubCategoryDto;
import com.spring.ecommerce.model.SubCategory;
import com.spring.ecommerce.service.SubCategoryService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link SubCategory} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-01-31
 *************************************************************************/
@RestController
@RequestMapping("/api/subCategory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class SubCategoryController {

	@Autowired
	private SubCategoryService service;

	/*************************************************************************
	 * Create a new SubCategory
	 * 
	 * @param ob {@link SubCategory} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link SubCategory}
	 *************************************************************************/
	@PostMapping
	public SubCategory addProduct(@RequestBody SubCategory subCategory, HttpServletRequest rq, HttpServletResponse rs) {
		subCategory.setCreatedBy(Long.valueOf(rq.getHeader("createdBy")));
		subCategory.setCreatedByEmp(rq.getHeader("createdByEmp") + " (ID:" + rq.getHeader("createdBy") + ")");
		rs.setStatus(HttpServletResponse.SC_CREATED);
		return service.create(subCategory, rs);
	}

	/*************************************************************************
	 * Get all Product {@link SubCategory}
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<SubCategoryDto> getAllSubCategory() {
		return service.getAllSubCategory();
	}

	 /*************************************************************************
     * Get all  active {@link  SubCategory}
     * 
     * @return {@link List< SubCategory>}
     *************************************************************************/
    @GetMapping("/getAll/active")
    public List<SubCategoryDto> getAllActiveSubCategory() {
    	return service.getAllActiveSubCategory();
    }
    /*************************************************************************
	 * Get all SubCategory {@link SubCategory} by Category Id
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	@GetMapping("/getAll/{id}")
	public List<SubCategoryDto> getAllSubCategoryByCategoryId(@PathVariable String id) {
		return service.getAllSubCategoryByCategoryId(id);
	}

}
