package com.fpoly.controller.admin;

import com.fpoly.entity.Order;
import com.fpoly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class AdminOrder {
    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public String index(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);

        return "admin/order";
    }
}
