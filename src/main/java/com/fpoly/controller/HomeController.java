package com.fpoly.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.entity.ProductType;
import com.fpoly.entity.User;
import com.fpoly.repository.ProductTypeRepository;
import com.fpoly.repository.UserRepository;
import com.fpoly.service.CookieService;
import com.fpoly.service.MailService;
import com.fpoly.service.SessionService;
import com.fpoly.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	ProductTypeRepository pdtResp;
	@Autowired
	UserRepository userResp;
	@Autowired
	CookieService cookie;
	@Autowired
	UserService userService;
	@Autowired
	SessionService session;
	@Autowired
	MailService mailservice;
	@Autowired
	HttpServletRequest req;

	@GetMapping("/index")
	public String homePage(Model model) {
		System.out.println(cookie.getValue("userId"));
		return "index";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("/checkout")
	public String checkout() {
		return "checkout";
	}

	@GetMapping("/edit")
	public String edit(Model model, @CookieValue(name = "userId") Integer userId) {
		User user = userService.edit(userId);
		if (user != null) {
			model.addAttribute("userProfile", user);
			return "edit";
		}
		return "redirect:/home/index";
	}

	@PostMapping("/edit")
	public String edit(User userProfile, @CookieValue(name = "userId") Integer userId) {
		if (userProfile != null) {
			Optional<User> user = userResp.findById(userId);
			user.get().setFullname(userProfile.getFullname());
			user.get().setAddress(userProfile.getAddress());
			user.get().setNumberPhone(userProfile.getNumberPhone());
			userResp.save(user.get());
		}
		return "redirect:/home/edit";
	}

	@GetMapping("/login")
	public String login( @RequestParam(name="message", required = false) String message,Model model) {
		model.addAttribute("message", message);
		return "login";
	}

	@PostMapping("/login")
	public String checkLogin(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		boolean checkLogin = userService.login(username, password);
		if (checkLogin == false) {
			model.addAttribute("message", "Username or Password is Invalid");
			return "login";
		}
		return "redirect:/home/index";
	}

	@GetMapping("/logout")
	public String logout() {
		userService.logout();
		return "redirect:/home/index";
	}

	@GetMapping("/register")
	public String signup(Model model) {

		return "register";
	}

	@PostMapping("/register")
	public String signupBtn(@ModelAttribute User user) throws IOException, MessagingException {
		String code = "";
		for (int i = 0; i < 6; i++) {
			String ranNum = String.valueOf(ThreadLocalRandom.current().nextInt(0, 9));
			code = code + ranNum;
		}
		if (!user.equals(null)) {
			session.set("numberCode", code);
			session.set("user", user);
			mailservice.send(user.getEmail(), code);
			session.setMaxInactiveInterval(120);
		}
		return "redirect:/home/register/auth";
	}

	@GetMapping("/register/auth")
	public String auth(Model model) {
		String[] host = req.getRequestURI().split("/");
		for (String w : host) {
			if (w.equalsIgnoreCase("register")) {
				model.addAttribute("uri", w);
			}
		}
		return "authentication";
	}

	@PostMapping("/register/auth")
	public String autho(@RequestParam("ma") String ma, Model model) {
		if (ma.equals(session.get("numberCode", ""))) {
			User user = (User) session.get("user");
			userService.save(user);
			return "redirect:/home/index";
		}
		model.addAttribute("message", "Mã xác thực chưa chính xác!");
		return "redirect:" + req.getRequestURI();
	}

	@GetMapping("/resetpass/auth")
	public String resetpass(Model model, @CookieValue(name = "userId") Integer userId) throws MessagingException {
		String[] host = req.getRequestURI().split("/");
		for (String w : host) {
			if (w.equalsIgnoreCase("resetpass")) {
				model.addAttribute("uri", w);
			}
		}
		User user = userService.edit(userId);
		String code = "";
		for (int i = 0; i < 6; i++) {
			String ranNum = String.valueOf(ThreadLocalRandom.current().nextInt(0, 9));
			code = code + ranNum;
		}

		if (user != null) {
			session.set("Coderesetpass", code);
			session.setMaxInactiveInterval(120);
			mailservice.send(user.getEmail(), code);
		}

		System.out.println(user.getEmail());
		System.out.println("Code Change: " + session.get("Coderesetpass", ""));

		return "authentication";
	}

	@PostMapping("/resetpass/auth")
	public String resetpassq(@RequestParam("ma") String ma, Model model) {
		if (ma.equals(session.get("Coderesetpass", ""))) {
			return "redirect:/home/resetpass";
		}
		model.addAttribute("message", "Mã xác thực chưa chính xác!");
		return "redirect:/home/index";
	}

	@GetMapping("/resetpass")
	public String resetpassw() {
		return "resetpass";
	}

	@PostMapping("/resetpass")
	public String resetpassa(@RequestParam("pass") Optional<String> pass,
			@RequestParam("repass") Optional<String> repass, Model model,
			@CookieValue(name = "userId") Integer userId) {
		if (pass.get().equalsIgnoreCase(repass.get())) {
			String passnew = pass.get();
			Optional<User> u = userResp.findById(userId);
			u.get().setPassword(passnew);
			userService.save(u.get());
			userService.logout();
			return "redirect:/home/index";
		}
		model.addAttribute("message", "Mật khẩu mới chưa đúng!");
		return "resetpass";
	}

	@ModelAttribute("categories")
	public List<ProductType> getCategories() {
		List<ProductType> list = pdtResp.findAll();
		return list;
	}
}
