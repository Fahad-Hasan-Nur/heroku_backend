package com.spring.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDto {

	private String id;
	private String name;
	private String code;
	private String brandId;
	private String brandName;
	private String categoryId;
	private String categoryName;
	private String subCategoryId;
	private String subCategoryName;
	private double price;
	private int quantity;
}
