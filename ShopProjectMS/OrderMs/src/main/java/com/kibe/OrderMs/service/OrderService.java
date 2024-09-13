package com.kibe.OrderMs.service;

import com.kibe.OrderMs.dto.OrderRequest;
import com.kibe.OrderMs.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    public void placeOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrders();
}
