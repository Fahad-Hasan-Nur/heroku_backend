package com.spring.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the ecommerce_admin database table.
 *
 * @author Fahad Hasan
 * @since 2021-02-13
 */
@Data
@Entity
@Table(name = "ecommerce_admin")
@ToString(callSuper = true)
public class Admin implements Serializable {
	private static final long serialVersionUID = 4240005902936474749L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Setter(value = AccessLevel.PRIVATE)
	@Column(updatable = false, nullable = false)
	String id;

	@NotNull
	private String name;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	private String password;

	@ManyToOne
	@JoinColumn(name = "image_id",referencedColumnName = "id")
	@JsonIgnore
	private Image image;
	
	@Transient
	private String imageId;
	
	@Transient
	private String imageName;

	@NotNull
	private Date birthDate;

	@NotNull
	private String gender;
	
	@NotNull
	@Column(unique = true)
	private String phoneNumber;
	
	@NotNull
	private String role;
	
	@NotNull
	private boolean active;

}
