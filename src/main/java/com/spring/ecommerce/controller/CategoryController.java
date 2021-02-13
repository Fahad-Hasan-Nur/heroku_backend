package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.CategoryDto;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.service.CategoryService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Category} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-01-31
 *************************************************************************/
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

	private final CategoryService service;

	/*************************************************************************
	 * Create a new Product
	 * 
	 * @param ob {@link Category} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Category}
	 *************************************************************************/
	@PostMapping
	public Category addCategory(@RequestBody Category category, HttpServletRequest rq, HttpServletResponse rs) {
		category.setCreatedBy(Long.valueOf(rq.getHeader("createdBy")));
		category.setCreatedByEmp(rq.getHeader("createdByEmp") + " (ID:" + rq.getHeader("createdBy") + ")");
		rs.setStatus(HttpServletResponse.SC_CREATED);
		return service.create(category, rs);
	}

	/*************************************************************************
	 * Get all Category {@link Category}
	 * 
	 * @return {@link List< Category>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<CategoryDto> getAllCategory() {
		return service.getAllCategory();
	}

	/*************************************************************************
	 * Get all active {@link Category}
	 * 
	 * @return {@link List< Category>}
	 *************************************************************************/
	@GetMapping("/getAll/active")
	public List<CategoryDto> getAllActiveCategory() {
		return service.getAllActiveCategory();
	}

	/*************************************************************************
	 * Get Category {@link Category} by Id
	 * 
	 * @return {@link Category}
	 *************************************************************************/
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable String id) {
		return service.getCategoryById(id);
	}

	/*************************************************************************
	 * Update {@link Category}
	 * 
	 * @param ob {@link Category} object
	 * @return {@link Category}
	 *************************************************************************/
	@PutMapping
	public Category update(@Valid @RequestBody Category ob) {
		return service.update(ob);
	}

	/*************************************************************************
	 * Delete {@link Category}
	 * 
	 * @param ob {@link Category} object
	 * @return {@link Category}
	 *************************************************************************/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return service.deleteById(id);
	}

}
