package com.fpoly.controller.admin;

import com.fpoly.entity.Order;
import com.fpoly.entity.OrderDetail;
import com.fpoly.entity.User;
import com.fpoly.service.OrderDetailService;
import com.fpoly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/orderDetail")
public class AdminOrderDetail {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/")
    public String index(Model model, @RequestParam("id") Integer orderId) {
        List<OrderDetail> orderDetails = orderService.getById(orderId).get().getOrderDetails();
        model.addAttribute("orderDetails", orderDetails);
        System.out.println(orderDetails);
        return "forward:/admin/order";
    }
}
