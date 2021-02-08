package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.UserDto;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.UserRepo;


/*************************************************************************
 * {@link UserServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-2-1
 *************************************************************************/
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	// TODO remove
	@Override
	public List<UserDto> getAllUser() {
		return userRepo.findAll().stream().map(this::getUserDtoFromEntity).collect(Collectors.toList());
	}

	public UserDto getUserDtoFromEntity(User ob) {
		UserDto obj = new UserDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
