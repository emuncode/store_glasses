package com.fpoly.controller;

import java.util.List;

import com.fpoly.repository.ImageRepository;
import com.fpoly.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.entity.Product;
import com.fpoly.entity.ProductDetail;
import com.fpoly.service.ProductDetailService;
import com.fpoly.service.ProductService;

@Controller
@RequestMapping("/detail")
public class ProductDetailController {
	@Autowired
	ProductDetailService productDetailService;
	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ImageRepository imageRepository;

	@GetMapping
	public String detail() {
		return "detail";
	}

	@GetMapping("/{productId}/{ProductType}")
	public String getById(@PathVariable("productId") Integer productId,@PathVariable("ProductType") String ProductType, Model model) {
		Product pd = productService.getReferenceById(productId);
		model.addAttribute("product", pd);
		List<ProductDetail> listpdd = productDetailService.findByProductId(productId);
		model.addAttribute("listpdd", listpdd);

		return "detail";
	}

	@RequestMapping("/{productId}/{colorid}")
	public String getcolor(@PathVariable("productId") Integer productId,@PathVariable("colorid") String colorid, Model model) {
		Product pd = productService.getReferenceById(productId);
		model.addAttribute("product", pd);
		List<ProductDetail> listpdd = productDetailService.findByProductId(productId);
		model.addAttribute("listpdd", listpdd);
		ProductDetail pdd = productDetailService.findByProductIdAndColorId(productId, colorid);
		model.addAttribute("pdd", pdd);
		return "detail";
	}
}
