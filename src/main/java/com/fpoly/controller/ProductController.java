package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.entity.Product;
import com.fpoly.entity.ProductType;
import com.fpoly.repository.ProductRepository;
import com.fpoly.repository.ProductTypeRepository;
import com.fpoly.service.CookieService;
import com.fpoly.service.ProductService;
import com.fpoly.service.ProductTypeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop")
public class ProductController {
	@Autowired
	ProductTypeRepository pdtResp;
	@Autowired
	ProductRepository pdResp;
	@Autowired
	ProductService productService;
	@Autowired
	ProductTypeService productTypeService;
	@Autowired
	HttpSession session;
	@Autowired
	CookieService cookie;

	@ModelAttribute("categories")
	public List<ProductType> getCategories() {
		List<ProductType> list = pdtResp.findAll();
		return list;
	}

	@ModelAttribute("userId")
	public String getUserId() {
		return cookie.getValue("userId");
	}

	@GetMapping({ "/", "/index" })
	public String homePage(Model model) {
		List<Product> products = pdResp.findAll();
		model.addAttribute("products", products);
		return "shop";
	}

	@GetMapping("/{idProductType}")
	public String sort(Model model, @PathVariable("idProductType") String idProductType) {
		if (idProductType != null) {
			List<Product> products = pdResp.findByProductTypeId(idProductType);
			model.addAttribute("products", products);
		}
		return "shop";
	}

	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("products", productService.getAll());
		return "shop";
	}

}
