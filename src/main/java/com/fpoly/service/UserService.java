package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.entity.User;
import com.fpoly.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	HttpServletResponse resp;
	@Autowired
	SessionService session;
	@Autowired
	CookieService cookie;
	public static int HOURS = 12;

	public User save(User u) {
		return userRepository.save(u);
	}

	public void logout() {
		session.remove("user");
		cookie.remove("userId");
	}

	public boolean login(String username, String password) {
		User user = userRepository.findByEmail(username);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				session.set("user", user);
				System.out.println(session.get("user"));

				resp.addCookie(cookie.add("userId", String.valueOf(user.getId()), HOURS));
				resp.addCookie(cookie.add("isAdmin", String.valueOf(user.getIsAdmin()), HOURS));

				return true;
			}
		}
		return false;
	}

	public User edit(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}
}
