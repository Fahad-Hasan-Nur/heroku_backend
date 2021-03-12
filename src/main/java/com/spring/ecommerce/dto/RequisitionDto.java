package com.spring.ecommerce.dto;

import lombok.Data;

@Data
public class RequisitionDto {
	
	private String id;
	private String productId;
	private String productName;
	private long totalCost;
	private int cartoonSize;
	private int cartoonPerLot;
	private String userId;
	private String status;
	private String size;
	private String createdBy;
	private String updatedBy;
	
}

