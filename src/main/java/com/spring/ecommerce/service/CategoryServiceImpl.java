package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.CategoryDto;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.repository.CategoryRepo;

import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link CategoryServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-1-29
 *************************************************************************/
@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;

	/*************************************************************************
	 * Create a new Category
	 * 
	 * @param ob {@link Category} object
	 * @return {@link Category}
	 *************************************************************************/
	@Override
	public Category create(Category category, HttpServletResponse rs) {
		try {
			return categoryRepo.save(category);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return category;
		}
	}

	/*************************************************************************
	 * Get all Category {@link Category}
	 * 
	 * @return {@link Category}
	 *************************************************************************/
	@Override
	public List<CategoryDto> getAllCategory() {
		return categoryRepo.findAll().stream().map(this::getCategoryDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all Category {@link Category} by isActive flag
	 * 
	 * @return {@link Category}
	 *************************************************************************/
	@Override
	public List<CategoryDto> getAllActiveCategory() {
		return categoryRepo.findAllByIsActiveOrderByIdDesc(true).stream().map(this::getCategoryDtoFromEntity)
				.collect(Collectors.toList());
	}

	public CategoryDto getCategoryDtoFromEntity(Category ob) {
		CategoryDto obj = new CategoryDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
