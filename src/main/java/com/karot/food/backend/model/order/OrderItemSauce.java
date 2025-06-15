package com.karot.food.backend.model.order;

import com.karot.food.backend.model.DippingSauce;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_item_sauces")
public class OrderItemSauce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "sauce_id")
    private DippingSauce sauce;
}
