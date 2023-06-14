package com.fpoly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
	@Autowired
	HttpSession session;

	/**
	 * @param key
	 * @return if = null return null, else return param
	 */
	public Object get(String key) {
		Object param = session.getAttribute(key);
		if (param != null) {
			return param;
		}
		return null;
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return if = null return defaultValue, else return param
	 */
	public Object get(String key, Object defaultValue) {
		Object param = session.getAttribute(key);
		if (param != null) {
			return param;
		}
		return defaultValue;
	}

	/**
	 * Thay đổi hoặc tạo mới attribute trong session
	 * @param name  tên attribute
	 * @param value giá trị attribute
	 */
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}

	/**
	 * Xóa attribute trong session
	 * @param name tên attribute cần xóa
	 */
	public void remove(String name) {
		session.removeAttribute(name);
	}
	
	public void setMaxInactiveInterval(int s) {
		session.setMaxInactiveInterval(s);
	}
	
	
}
