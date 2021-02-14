package com.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.spring.ecommerce.dto.AdminDto;
import com.spring.ecommerce.model.Admin;
import com.spring.ecommerce.model.Category;

/*************************************************************************
 * {@link Category} service class
 * 
 * @author Fahad Hasan
 * @since 2021-02-13
 *************************************************************************/
public interface AdminService {

	/*************************************************************************
	 * Create a new Admin
	 * 
	 * @param ob {@link Admin} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Admin}
	 *************************************************************************/
	Admin create(Admin ob);

	/*************************************************************************
	 * Get all Admin {@link Admin}
	 * 
	 * @return {@link List< Admin>}
	 *************************************************************************/
	List<AdminDto> getAllAdmin();

	/*************************************************************************
	 * Get Admin {@link Admin} by Id
	 * 
	 * @return {@link Admin}
	 *************************************************************************/
	AdminDto getAdminDtoById(String id);

	/*************************************************************************
	 * Get Admin {@link Admin} by Email
	 * 
	 * @return {@link Email}
	 *************************************************************************/
	AdminDto getAdminDtoByEmail(String email);
	/*************************************************************************
	 * Update {@link Admin}
	 * 
	 * @param ob {@link Admin} object
	 * @param rs
	 * @return {@link Admin}
	 *************************************************************************/
	Admin update(Admin ob);

	/*************************************************************************
	 * Delete {@link Admin}
	 * 
	 * @param ob {@link Admin} object
	 * @param rs
	 * @return {@link Admin}
	 *************************************************************************/
	ResponseEntity<?> deleteById(String id );
}
