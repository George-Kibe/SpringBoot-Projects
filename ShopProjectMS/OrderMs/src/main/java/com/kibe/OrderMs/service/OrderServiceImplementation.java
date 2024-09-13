package com.kibe.OrderMs.service;

import com.kibe.OrderMs.dto.OrderRequest;
import com.kibe.OrderMs.dto.OrderResponse;
import com.kibe.OrderMs.entity.Order;
import com.kibe.OrderMs.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        // map order request to order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        // save to order repository
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
       return orderRepository.findAll()
               .stream()
               .map(order -> new OrderResponse(order.getId(), order.getOrderNumber(),
                       order.getSkuCode(), order.getPrice(), order.getQuantity()))
               .toList();
    }
}
