package com.kibe.OrderMs.service;

import com.kibe.OrderMs.client.InventoryClient;
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
    private final InventoryClient inventoryClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        var productIsInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (productIsInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            // save to order repository
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + " is not in stock");
        }

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
