package com.fpoly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.entity.Product;
import com.fpoly.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository repo;
	
	public List<Product> getAll() {
		return repo.findAll();
	}

	public Product getReferenceById(Integer id) {
		return repo.getReferenceById( id);
	}
}
