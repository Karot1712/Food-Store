package com.karot.food.backend.mapper;

import com.karot.food.backend.DTO.*;
import com.karot.food.backend.DTO.order.OrderDto;
import com.karot.food.backend.DTO.order.OrderItemDto;
import com.karot.food.backend.DTO.order.OrderItemSauceDto;
import com.karot.food.backend.model.*;
import com.karot.food.backend.model.order.Order;
import com.karot.food.backend.model.order.OrderItem;
import com.karot.food.backend.model.order.OrderItemSauce;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {
    //User -> DTO
    public UserDto mapUserToDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole().name());

        return userDto;
    }

    //Category -> DTO
    public CategoryDto mapCategoryToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    //Product -> DTO
    public ProductDto mapProductToDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setStatus(productDto.getStatus());

        return productDto;
    }

    //DippingSauce -> DTO
    public DippingSauceDto mapDippingSauceToDto(DippingSauce dippingSauce){
        DippingSauceDto dippingSauceDto = new DippingSauceDto();

        dippingSauceDto.setId(dippingSauce.getId());
        dippingSauceDto.setName(dippingSauce.getName());
        dippingSauceDto.setPrice(dippingSauce.getPrice());

        return dippingSauceDto;
    }
    //Address -> DTO
    public AddressDto mapAddressToDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        return addressDto;
    }

    //OrderItemSauce -> DTO
    public OrderItemSauceDto mapOrderItemSauceToDto(OrderItemSauce orderItemSauce){
        OrderItemSauceDto sauceDto = new OrderItemSauceDto();
        sauceDto.setSauceId(orderItemSauce.getId());
        sauceDto.setPrice(orderItemSauce.getPrice());
        sauceDto.setQuantity(orderItemSauce.getQuantity());
        return sauceDto;
    }

    //OrderItem -> DTO
    public OrderItemDto mapOrderItemToDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        if(orderItem.getSauces() != null){
            orderItemDto.setSauceDtos(orderItem.getSauces().stream()
                    .map(this::mapOrderItemSauceToDto)
                    .toList());
        }
        return orderItemDto;
    }

    //OrderItem -> DTO + Product
    public OrderItemDto mapOrderItemToDtoWithProduct(OrderItem orderItem){
        OrderItemDto orderItemDto = mapOrderItemToDto(orderItem);
        if(orderItem.getProduct() != null ){
            ProductDto productDto = mapProductToDto(orderItem.getProduct());
            orderItemDto.setProduct(productDto);
        }
        return orderItemDto;
    }

    //Order -> DTO + OrderItemList + User
    public OrderDto mapOrderToDto(Order order){
        OrderDto orderDto = new OrderDto();
        order.setId(order.getId());
        orderDto.setTotalPrice(order.getTotalPrice());
        //*
        orderDto.setAddress(mapAddressToDto(order.getAddress()));
        //*
        orderDto.setPaymentMethod(order.getPaymentMethod());
        orderDto.setContactNumber(order.getContactNumber());
        if(order.getOrderItemList() != null){
            orderDto.setOrderItemList(order.getOrderItemList()
                    .stream()
                    .map(this::mapOrderItemToDtoWithProduct)
                    .toList());
        }
        if(order.getUser() != null){
            orderDto.setUser(mapUserToDto(order.getUser()));
        }

        return orderDto;
    }


}
