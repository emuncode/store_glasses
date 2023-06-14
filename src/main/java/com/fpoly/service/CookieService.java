package com.fpoly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest req;
	@Autowired
	HttpServletResponse resp;

	public Cookie get(String name) {
		Cookie[] ck = req.getCookies();
		if (ck != null) {
			for (Cookie cookie : ck) {
				if (cookie.getName().equalsIgnoreCase(name)) {
					return cookie;
				} else
					return null;
			}
		}
		return null;
	}

	public String getValue(String name) {
		Cookie[] ck = req.getCookies();
		if (ck != null) {
			for (Cookie cookie : ck) {
				if (cookie.getName().equalsIgnoreCase(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public Cookie add(String name, String value, int hours) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours * 60 * 60);
		cookie.setPath("/");
		return cookie;

	}

	public void remove(String name) {
		Cookie ok = new Cookie(name, "");
		ok.setPath("/");
		ok.setMaxAge(0);
		resp.addCookie(ok);
	}
//	/**
//	 * @param name tên cookie cần đọc
//	 * @return đối tượng cookie đọc được hoặc null nếu không tồn tại
//	 */
//	public Cookie get(String name) {
//		Cookie[] cookies = req.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equalsIgnoreCase(name)) {
//					return cookie;
//				}
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * Đọc giá trị của cookie từ request
//	 * @param name tên cookie cần đọc
//	 * @param defaultValue
//	 * @return chuỗi giá trị đọc được
//	 */
//	public String getValue(String name, String defaultValue) {
//		Cookie cookie = get(name);
//		if (cookie != null) {
//			return cookie.getValue();
//		}
//		return defaultValue;
//	}
//
//	/**
//	 * Tạo và gửi cookie về client
//	 * @param name tên cookie
//	 * @param value giá trị cookie
//	 * @param hours thời hạn(giờ)
//	 * @return đối tượng cookie đã tạo
//	 */
//	public Cookie add(String name, String value, int hours) {
//		Cookie cookie = new Cookie(name, value);
//		cookie.setMaxAge(hours * 60 * 60);
//		cookie.setPath("/");
//		resp.addCookie(cookie);
//		return cookie;
//	}
//
//	/**
//	 * Xóa cookie khỏi client
//	 * @param name tên cookie cần xóa
//	 */
//	public void remove(String name) {
//		add(name, "", 0);
//	}
}
