package com.kibe.OrderMs.repository;

import com.kibe.OrderMs.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
