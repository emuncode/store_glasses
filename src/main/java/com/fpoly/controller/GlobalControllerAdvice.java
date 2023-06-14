package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fpoly.entity.ProductType;
import com.fpoly.service.CookieService;
import com.fpoly.service.ProductTypeService;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    CookieService cookie;
    @Autowired
    ProductTypeService pdtService;

    @ModelAttribute("categories")
    public List<ProductType> getCategories() {
        List<ProductType> list = pdtService.getAll();
        return list;
    }

    @ModelAttribute("userId")
    public String getUserId() {
        return cookie.getValue("userId");
    }

    @ModelAttribute("isAdmin")
    public String checkAdmin() {
        return cookie.getValue("isAdmin");
    }
}
