package com.fpoly.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "link")
	private String link;

	@Column(name = "productdetailid")
	private Integer productdetailId;

	@ManyToOne
	@JoinColumn(name = "productdetailid", insertable = false, updatable = false)
	ProductDetail productDetail;
}
