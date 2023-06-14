package com.fpoly.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producttype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Column(name = "name")
	private String typeName;
}
