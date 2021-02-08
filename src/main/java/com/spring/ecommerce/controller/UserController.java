package com.spring.ecommerce.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.UserDto;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link User} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-02-01
 *************************************************************************/
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	@Autowired
	private UserService service;

	// TODO remove
	@GetMapping("/getAll")
	public List<UserDto> getAllUser() {
		return service.getAllUser();
	}

}
