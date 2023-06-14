package com.fpoly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.entity.ProductDetail;
import com.fpoly.repository.ProductDetailRepository;
import com.fpoly.repository.ProductRepository;

@Service
public class ProductDetailService {
	@Autowired
	ProductDetailRepository repo;


	public List<ProductDetail> findByProductId(Integer productId) {
		return repo.findByProductId(productId);
	}


	public ProductDetail findByProductIdAndColorId(Integer productId, String colorid) {
		return repo.findByProductIdAndColorId(productId, colorid);
	}
	
	
}
