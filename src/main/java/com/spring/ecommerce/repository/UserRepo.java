package com.spring.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.User;

public interface UserRepo extends JpaRepository<User, String> {

	User findByEmail(String email);

	User findByEmailIgnoreCase(String email);

	User findByEmailAndActive(String email,boolean active);

}
