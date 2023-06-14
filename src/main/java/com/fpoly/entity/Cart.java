package com.fpoly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "createdat")
	private Date createdAt = new Date();
	
	@Column(name = "productdetailid")
	private Integer productDetailId;
	
	@Column(name = "userid")
	private int userId;

	@Transient
	public double getTotal() {
		return productDetail.getPrice() * quantity;
	}

	@ManyToOne
	@JoinColumn(name = "productdetailid", insertable = false, updatable = false)
	@JsonIgnore
	ProductDetail productDetail;

	@OneToMany(mappedBy = "cart")
	List<OrderDetail> orderDetails;
}
