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

import com.fpoly.entity.User;
import com.fpoly.repository.UserRepository;

@Controller
@RequestMapping("/admin/account")
public class AdminAccountController {
	@Autowired
	UserRepository userResp;

	@GetMapping("/")
	public String index(@ModelAttribute("account") User entity) {
		return "admin/account";
	}

	@ModelAttribute("listUser")
	public List<User> showTable() {
		return userResp.findAll();
//		return userResp.findByIsAdmin(false);
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("account", userResp.findById(id).get());
		return "admin/account";
	}

	@PostMapping("/update/{id}")
	public String update(@ModelAttribute("account") Optional<User> entity) throws IOException {
		if (entity.isPresent()) {
			userResp.saveAndFlush(entity.get());
		}
		return "redirect:/admin/account/";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		userResp.deleteById(id);
		return "redirect:/admin/account/";
	}

	@PostMapping("/save")
	public String save(Model model, @ModelAttribute("account") Optional<User> entity) {
		if (entity.isPresent()) {
			userResp.save(entity.get());
		} else {
			model.addAttribute("message", "Failed to save");
		}
		return "redirect:/admin/account/";
	}
}
