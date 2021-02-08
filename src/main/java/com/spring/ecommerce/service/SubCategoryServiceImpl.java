package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.ecommerce.dto.SubCategoryDto;
import com.spring.ecommerce.model.SubCategory;
import com.spring.ecommerce.repository.CategoryRepo;
import com.spring.ecommerce.repository.SubCategoryRepo;

import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link SubCategoryServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-1-31
 *************************************************************************/
@Service
@Transactional
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {
	@Autowired
	private SubCategoryRepo subCategoryRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	/*************************************************************************
	 * Create a new SubCategory
	 * 
	 * @param ob {@link SubCategory} object
	 * @return {@link SubCategory}
	 *************************************************************************/
	@Override
	public SubCategory create(SubCategory subCategory, HttpServletResponse rs) {
		try {
			subCategory.setCategory(categoryRepo.findById(subCategory.getCategoryId()).orElse(null));
			return subCategoryRepo.save(subCategory);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return subCategory;
		}
	}

	/*************************************************************************
	 * Get all SubCategory {@link SubCategory}
	 * 
	 * @return {@link SubCategory}
	 *************************************************************************/
	@Override
	public List<SubCategoryDto> getAllSubCategory() {
		return subCategoryRepo.findAll().stream().map(this::getSubCategoryDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all Brand {@link SubCategory} by isActive flag
	 * 
	 * @return {@link SubCategory}
	 *************************************************************************/
	@Override
	public List<SubCategoryDto> getAllActiveSubCategory() {
		return subCategoryRepo.findAllByIsActiveOrderByIdDesc(true).stream().map(this::getSubCategoryDtoFromEntity)
				.collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all SubCategory {@link SubCategory} by Category Id
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	@Override
	public List<SubCategoryDto> getAllSubCategoryByCategoryId(String id) {

		return subCategoryRepo.findAllByCategory(categoryRepo.findById(id).orElse(null)).stream().map(this::getSubCategoryDtoFromEntity)
				.collect(Collectors.toList());
	}

	public SubCategoryDto getSubCategoryDtoFromEntity(SubCategory ob) {
		ob.setCategoryId(ob.getCategory().getId());
		ob.setCategoryName(ob.getCategory().getName());
		SubCategoryDto obj = new SubCategoryDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
