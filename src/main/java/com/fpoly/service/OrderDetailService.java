package com.fpoly.service;

import com.fpoly.entity.Cart;
import com.fpoly.entity.Order;
import com.fpoly.entity.OrderDetail;

import com.fpoly.repository.OrderDetailRepository;
import com.fpoly.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public void saveAll(Integer orderId, List<Cart> carts) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();

        for (Cart cart : carts) {
            orderDetail.setOrderId(orderId);
            orderDetail.setCartId(cart.getId());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setPrice(cart.getTotal());
            
            orderDetails.add(orderDetail);
        }

        orderDetailRepository.saveAll(orderDetails);
    }
}
