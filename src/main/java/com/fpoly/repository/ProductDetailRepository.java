package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.ProductDetail;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
	List<ProductDetail> findByProductId(Integer productid);
	
	ProductDetail findByProductIdAndColorId(Integer productid, String colorid);
}
