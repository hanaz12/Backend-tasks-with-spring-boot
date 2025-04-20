package com.example.demo.Mapper;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.OrderItemDTO;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface OrderMapper {


    OrderDTO toDTO(Order order);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toEntity(OrderDTO orderDTO);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    void updateOrderFromDTO(OrderDTO orderDTO, @MappingTarget Order order);


    OrderItemDTO toItemDTO(OrderItem orderItem);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toItemEntity(OrderItemDTO orderItemDTO);
}