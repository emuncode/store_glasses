package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.entity.Cart;
import com.fpoly.service.CartService;
import com.fpoly.service.CookieService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CookieService cookie;

	@Autowired
	CartService cartService;

	@GetMapping("/index")
	public String homePage(Model model) {
		List<Cart> cartItems = cartService.getAll(Integer.parseInt(cookie.getValue("userId")));
		model.addAttribute("cartItems", cartItems);
		return "cart";
	}

	@PostMapping("/save")
	public String saveProduct(Cart cart) {
		Integer userId = Integer.parseInt(cookie.getValue("userId"));
		cart.setUserId(userId);
//		System.out.println(cart.toString());
		cartService.addToCart(cart);
		return "redirect:/cart/index";
	}

	@ModelAttribute("totalOrders")
	public double totalOrders() {
		final double[] totalOrders = {0};
		List<Cart> cartItems = cartService.getAll(Integer.parseInt(cookie.getValue("userId")));
		cartItems.stream().forEach(s -> {
			totalOrders[0] += s.getTotal();
		});

		return totalOrders[0];
	}
}
