package com.kibe.OrderMs.service;

import com.kibe.OrderMs.client.InventoryClient;
import com.kibe.OrderMs.dto.OrderRequest;
import com.kibe.OrderMs.dto.OrderResponse;
import com.kibe.OrderMs.entity.Order;
import com.kibe.OrderMs.event.OrderPlacedEvent;
import com.kibe.OrderMs.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImplementation.class);

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
            // send message to kafka topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), orderRequest.userDetails().email());
            log.info("Start - sending OrderPlacedEvent {} to kafka Topic order placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - sending OrderPlacedEvent {} to kafka Topic order placed", orderPlacedEvent);
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
