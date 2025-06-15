package com.karot.food.backend.service.impl;

import com.karot.food.backend.DTO.order.OrderRequest;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.exception.NotFoundException;
import com.karot.food.backend.mapper.EntityDtoMapper;
import com.karot.food.backend.model.*;
import com.karot.food.backend.model.order.Order;
import com.karot.food.backend.model.order.OrderItem;
import com.karot.food.backend.model.order.OrderItemSauce;
import com.karot.food.backend.repositories.DippingSauceRepo;
import com.karot.food.backend.repositories.OrderItemRepo;
import com.karot.food.backend.repositories.OrderRepo;
import com.karot.food.backend.repositories.ProductRepo;
import com.karot.food.backend.service.interf.OrderService;
import com.karot.food.backend.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final UserService userService;
    private final ProductRepo productRepo;
    private final DippingSauceRepo sauceRepo;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response placeOrder(OrderRequest request) {
        User user = userService.getLoginUser();

        List<OrderItem> orderItems = request.getItemRequests().stream().map(orderRequest->{
            Product product = productRepo.findById(orderRequest.getProductId())
                    .orElseThrow(()->new NotFoundException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderRequest.getQuantity());
            orderItem.setPrice(priceByQuantity(product.getPrice(), orderRequest.getQuantity()));

            //handle sauces
            if(orderRequest.getSauces() != null && !orderRequest.getSauces().isEmpty()){
                List<OrderItemSauce> sauces = orderRequest.getSauces().stream().map(sauceRequest -> {
                    DippingSauce sauce = sauceRepo.findById(sauceRequest.getSauceId())
                            .orElseThrow(()-> new NotFoundException("Sauce not found"));

                    OrderItemSauce itemSauce = new OrderItemSauce();
                    itemSauce.setSauce(sauce);
                    itemSauce.setPrice(sauce.getPrice());
                    itemSauce.setQuantity(sauceRequest.getQuantity());
                    itemSauce.setOrderItem(orderItem);
                    return itemSauce;
                }).collect(Collectors.toList());
                orderItem.setSauces(sauces);
            }
            return orderItem;
        }).collect(Collectors.toList());

        Order order = new Order();
        order.setUser(user);
        order.setAddress(request.getAddress());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setContactNumber(request.getContactNumber());
        order.setOrderItemList(orderItems);

        int totalPrice = orderItems.stream().mapToInt(item->{
            int itemPrice = item.getPrice();
            if(item.getSauces() != null){
                itemPrice += item.getSauces().stream()
                        .mapToInt(s -> s.getPrice() * s.getQuantity())
                        .sum();
            }
            return itemPrice;
        }).sum();

        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepo.save(order);
        return Response.builder()
                .status(200)
                .message("Place order successfully")
                .order(entityDtoMapper.mapOrderToDto(savedOrder))
                .build();
    }



    public Integer priceByQuantity(Integer price, Integer quantity){
        return price * quantity;
    }

}
