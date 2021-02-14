package com.spring.ecommerce.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.spring.ecommerce.model.User;
import com.spring.ecommerce.service.AuthService;
import com.spring.ecommerce.security.jwt.AuthenticationRequest;
import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Auth} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-02-03
 *************************************************************************/
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	private final AuthService service;

	/*************************************************************************
	 * User Login
	 * 
	 * @param ob {@link AuthenticationRequest} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link JWT}
	 *************************************************************************/
	@PostMapping("/user/login")
	public ResponseEntity<?> userLogin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		return service.createTokenForUser(authenticationRequest);
	}
	
	/*************************************************************************
	 * Admin Login
	 * 
	 * @param ob {@link AuthenticationRequest} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link JWT}
	 *************************************************************************/
	@PostMapping("/admin/login")
	public ResponseEntity<?> adminLogin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		return service.createTokenForAdmin(authenticationRequest);
	}

	/*************************************************************************
	 * Create a new User
	 * 
	 * @param ob {@link User} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link User}
	 *************************************************************************/
	@PostMapping("/user/register")
	public String registerUser(@RequestBody User user, HttpServletResponse rs) {

		rs.setStatus(HttpServletResponse.SC_CREATED);
		return service.create(user, rs);
	}

	/*************************************************************************
	 * Activate newly created USer
	 * 
	 * @param ob {@link Token} String
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link String}
	 *************************************************************************/
	@GetMapping("/confirm-account")
	public String confirmUserAccount(@RequestParam String token, HttpServletResponse rs) {
		return service.confirmUserAccount(token, rs);
	}
}
