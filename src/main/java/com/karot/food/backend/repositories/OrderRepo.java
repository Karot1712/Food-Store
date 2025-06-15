package com.karot.food.backend.repositories;

import com.karot.food.backend.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
}
