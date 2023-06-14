package com.fpoly.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.entity.Product;
import com.fpoly.entity.ProductType;
import com.fpoly.repository.ProductRepository;
import com.fpoly.repository.ProductTypeRepository;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
	@Autowired
	ProductRepository pdResp;
	@Autowired
	ProductTypeRepository pdtResp;

	@GetMapping("/")
	public String demo(@ModelAttribute("product") Product entity) {
		return "admin/product";
	}

	@PostMapping("/save")
	public String save(Model model, @ModelAttribute("product") Optional<Product> entity) {
		if (entity.isPresent()) {
			pdResp.save(entity.get());
		} else {
			model.addAttribute("message", "Failed to save");
		}
		return "redirect:/admin/product/";
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("product", pdResp.findById(id).get());
		return "admin/product";
	}

	@PostMapping("/update/{id}")
	public String update(@ModelAttribute("product") Optional<Product> entity) throws IOException {
		if (entity.isPresent()) {
			pdResp.saveAndFlush(entity.get());
		}
		return "redirect:/admin/product/";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		pdResp.deleteById(id);
		return "redirect:/admin/product/";
	}

	@ModelAttribute("listProduct")
	public List<Product> showTable() {
		return pdResp.findAll();
	}

	@ModelAttribute("listProductType")
	public List<ProductType> showType() {
		return pdtResp.findAll();
	}
}
