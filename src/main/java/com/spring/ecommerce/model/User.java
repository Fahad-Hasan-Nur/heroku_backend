package com.spring.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_user database table.
 *
 * @author Fahad Hasan
 * @since 2021-01-31
 */
@Data
@Entity
@Table(name = "ecommerce_user")
@ToString(callSuper = true)
public class User implements Serializable {
	private static final long serialVersionUID = 4240005902936474749L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Setter(value = AccessLevel.PRIVATE)
	@Column(updatable = false, nullable = false)
	String id;

	@NotNull
	private String Name;

	@NotNull
	@Email
	@Column(unique = true)
	private String email;

	@NotNull
	@Size(min = 6, max = 15)
	private String password;

	private String image;

	@NotNull
	private Date birthDate;

	@NotNull
	private String gender;
	
	@NotNull
	private boolean active;

}
