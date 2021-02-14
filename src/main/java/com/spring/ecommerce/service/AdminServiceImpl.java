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

import com.spring.ecommerce.dto.AdminDto;
import com.spring.ecommerce.model.Admin;
import com.spring.ecommerce.repository.AdminRepo;
import com.spring.ecommerce.repository.ImageRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link ProductServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-02-13
 *************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AdminServiceImpl implements AdminService{
	
	private final ImageRepo imageRepo;
	private final AdminRepo adminRepo;

	/*************************************************************************
	 * Create a new Admin
	 * 
	 * @param ob {@link Admin} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Admin}
	 *************************************************************************/
	@Override
	public Admin create(Admin admin) {
		try {
			admin.setImage(imageRepo.findById(admin.getImageId()).orElse(null));
			return adminRepo.save(admin);
		} catch (Exception e) {
			log.warn("Failed to create  Admin: ", e);
			return admin;
		}
	}

	/*************************************************************************
	 * Get all Admin {@link Admin}
	 * 
	 * @return {@link List< Admin>}
	 *************************************************************************/
	@Override
	public List<AdminDto> getAllAdmin() {
		return adminRepo.findAll().stream().map(this::getAdminDtoFromEntity)
				.collect(Collectors.toList());
	}

	/*************************************************************************
     * Get Admin {@link  Admin} by Id
     * 
     * @return {@link  Admin}
     *************************************************************************/
	@Override
	public AdminDto getAdminDtoById(String id) {
		return adminRepo.findById(id).map(this::getAdminDtoFromEntity).orElse(null);
	}
	
	/*************************************************************************
     * Get Admin {@link  Admin} by Email
     * 
     * @return {@link  Admin}
     *************************************************************************/
	@Override
	public AdminDto getAdminDtoByEmail(String email) {
		return this.getAdminDtoFromEntity(adminRepo.findByEmail(email));
	}
	/*************************************************************************
	 * Update {@link  Admin}
	 * 
	 * @param ob {@link  Admin} object
	 * @return {@link  Admin}
	 *************************************************************************/
	@Override
	public  Admin update(Admin ob) {
		try {
			return adminRepo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to update  Product: ", e);
			return ob;
		}
	}
	
	/*************************************************************************
	 * Delete {@link  Admin}
	 * 
	 * @param ob {@link  Admin} object
	 * @return {@link  Admin}
	 *************************************************************************/
	@Override
	public  ResponseEntity<?> deleteById(String id) {
		try {
			adminRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to update  Product: ", e);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.BAD_REQUEST);
		}
	}
	
	public AdminDto getAdminDtoFromEntity(Admin ob) {
		if(ob.getImage()!=null) {
			ob.setImageId(ob.getImage().getId());
		}
		AdminDto obj = new AdminDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
