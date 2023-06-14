package com.fpoly.interceptor;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fpoly.entity.User;
import com.fpoly.service.SessionService;
import com.fpoly.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class AuthorInterceptor implements HandlerInterceptor {
	@Autowired
	SessionService session;
	@Autowired
	UserService usersv;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		User user = (User) session.get("user");
		String error = "";
		if (user == null) {
			error = "Please login!";
		} else if (!user.getIsAdmin() && uri.startsWith("/admin/")) { 	
			error = "Access denied!";
			usersv.logout();
		}
		if (error.length() > 0) {
			session.set("security-uri", uri);
			response.sendRedirect("/home/login?message=" +error);
			return false;
		}
		return true;
	}

	
}
