package com.spring.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the ecommerce_product_type database table.
 *
 * @author Fahad Hasan
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ecommerce_product_type")
public class SubCategory extends AbstractPersistableEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String name;
	
	@NotNull
	private String code;
	
	@NotNull
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "category_id",referencedColumnName = "id")
	@JsonIgnore
	private Category category;
	
	@Transient
	private String categoryId;
	
	@Transient
	private String categoryName;
}
