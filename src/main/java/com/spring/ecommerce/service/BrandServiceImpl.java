package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.BrandDto;
import com.spring.ecommerce.model.Brand;
import com.spring.ecommerce.repository.BrandRepo;

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
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepo brandRepo;

	/*************************************************************************
	 * Create a new Brand
	 * 
	 * @param ob {@link Brand} object
	 * @return {@link Brand}
	 *************************************************************************/
	@Override
	public Brand create(Brand brand, HttpServletResponse rs) {
		try {
			return brandRepo.save(brand);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return brand;
		}
	}

	/*************************************************************************
	 * Get all Brand {@link Brand}
	 * 
	 * @return {@link Brand}
	 *************************************************************************/
	@Override
	public List<BrandDto> getAllBrand() {
		return brandRepo.findAll().stream().map(this::getBrandDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all Brand {@link Brand} by isActive flag
	 * 
	 * @return {@link Brand}
	 *************************************************************************/
	@Override
	public List<BrandDto> getAllActiveBrand() {
		return brandRepo.findAllByIsActiveOrderByIdDesc(true).stream().map(this::getBrandDtoFromEntity)
				.collect(Collectors.toList());
	}

	public BrandDto getBrandDtoFromEntity(Brand ob) {
		BrandDto obj = new BrandDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
