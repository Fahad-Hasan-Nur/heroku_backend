package com.spring.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the ecommerce_brand database table.
 *
 * @author Fahad Hasan
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_brand")
public class Brand extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String name;
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String code;
	
	private String description;
	
	
}
