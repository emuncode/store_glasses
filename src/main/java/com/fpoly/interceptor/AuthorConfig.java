package com.fpoly.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthorConfig implements WebMvcConfigurer {
	@Autowired
	AuthorInterceptor author;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(author)
		.addPathPatterns("/order/**","/home/checkout","/home/edit","/home/logout","/home/resetpass","/cart/**","/admin/**")
		.excludePathPatterns("/home/index","/","/shop/**","/detail/**");
	}

}