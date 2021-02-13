package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.model.Admin;
import com.spring.ecommerce.service.AdminService;
import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Admin} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-02-13
 *************************************************************************/
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

	private final AdminService service;

	/*************************************************************************
	 * Create a new Admin
	 * 
	 * @param ob {@link Admin} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Admin}
	 *************************************************************************/
	@PostMapping
	public Admin addAdmin(@RequestBody @Validated Admin admin) {
		return service.create(admin);
	}

	/*************************************************************************
	 * Get all Admin {@link Admin}
	 * 
	 * @return {@link List< Admin>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<Admin> getAllAdmin() {
		return service.getAllAdmin();
	}

	/*************************************************************************
	 * Get Admin {@link Admin} by Id
	 * 
	 * @return {@link Admin}
	 *************************************************************************/

	@GetMapping("/{id}")
	public Admin getAdminById(@PathVariable String id) {
		return service.getAdminById(id);
	}

	/*************************************************************************
	 * Get Admin {@link Admin} by Email
	 * 
	 * @return {@link Admin}
	 *************************************************************************/

	@GetMapping("/{email}")
	public Admin getAdminByEmail(@PathVariable String email) {
		return service.getAdminByEmail(email);
	}

	/*************************************************************************
	 * Update {@link Admin}
	 * 
	 * @param ob {@link Admin} object
	 * @return {@link Admin}
	 *************************************************************************/
	@PutMapping
	public Admin update(@RequestBody Admin ob) {
		return service.update(ob);
	}

	/*************************************************************************
	 * Delete {@link Admin}
	 * 
	 * @param ob {@link Admin} object
	 * @return {@link Admin}
	 *************************************************************************/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return service.deleteById(id);
	}
}
