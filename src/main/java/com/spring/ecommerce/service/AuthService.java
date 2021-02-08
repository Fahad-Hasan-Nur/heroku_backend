package com.spring.ecommerce.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.spring.ecommerce.model.User;
import com.spring.ecommerce.security.jwt.AuthenticationRequest;

/*************************************************************************
* {@link  Auth} service class 
* 
* @author Fahad Hasan
* @since  2021-02-03
*************************************************************************/
public interface AuthService {

	/*************************************************************************
	 * Create a new User
	 * 
	 * @param ob {@link User} object
	 * @return {@link User}
	 *************************************************************************/
	String create(User ob, HttpServletResponse rs);
	/*************************************************************************
	 * Activate newly created USer
	 * 
	 * @param ob {@link Token} String
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link String}
	 *************************************************************************/
	String confirmUserAccount( String token, HttpServletResponse rs);
	/*************************************************************************
	 * User Login
	 * 
	 * @param ob {@link AuthenticationRequest} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link JWT}
	 * @throws Exception 
	 *************************************************************************/
	 ResponseEntity<?> createToken(AuthenticationRequest authenticationRequest) throws Exception;
}
