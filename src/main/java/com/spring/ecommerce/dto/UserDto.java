package com.spring.ecommerce.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {

		private String id;
		private String name;
		private String email;
		private String image;
		private Date birthDate;
		private String gender;
		private boolean active;
		
		

}
