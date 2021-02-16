package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.BrandDto;
import com.spring.ecommerce.model.Brand;
import com.spring.ecommerce.repository.BrandRepo;
import com.spring.ecommerce.repository.ImageRepo;
import com.spring.ecommerce.repository.ProductRepo;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BrandServiceImpl implements BrandService {

	private final BrandRepo brandRepo;
	private final ImageRepo imageRepo;
	private final ProductRepo productRepo;

	/*************************************************************************
	 * Create a new Brand
	 * 
	 * @param ob {@link Brand} object
	 * @return {@link Brand}
	 *************************************************************************/
	@Override
	public Brand create(Brand brand) {
		try {
			brand.setImage(imageRepo.findByName(brand.getImageName()).orElse(null));
			return brandRepo.save(brand);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
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

	/*************************************************************************
     * Get Brand {@link  Brand} by Id
     * 
     * @return {@link  Brand}
     *************************************************************************/
	@Override
	public Brand getBrandById(String id) {
		return brandRepo.findById(id).orElse(null);
	}
	/*************************************************************************
	 * Update {@link  Brand}
	 * 
	 * @param ob {@link  Brand} object
	 * @return {@link  Brand}
	 *************************************************************************/
	@Override
	public  Brand update(Brand ob) {
		try {
			return brandRepo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to update  Brand: ", e);
			return ob;
		}
	}
	
	/*************************************************************************
	 * Delete {@link  Brand}
	 * 
	 * @param ob {@link  Brand} object
	 * @return {@link  Brand}
	 *************************************************************************/
	@Override
	public  ResponseEntity<?> deleteById(String id) {
		try {
			Brand ob =brandRepo.findById(id).orElse(null);
			productRepo.deleteInBatch(productRepo.findByBrand(ob));
			imageRepo.deleteById(ob.getImage().getId());
			brandRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to delete  Brand: ", e);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.BAD_REQUEST);
		}
	}
	public BrandDto getBrandDtoFromEntity(Brand ob) {
		BrandDto obj = new BrandDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
