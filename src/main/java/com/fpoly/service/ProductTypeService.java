package com.fpoly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.entity.ProductType;
import com.fpoly.repository.ProductTypeRepository;

@Service
public class ProductTypeService {
	@Autowired
	ProductTypeRepository repo;
	
	public List<ProductType> getAll() {
		return repo.findAll();
	}
}
