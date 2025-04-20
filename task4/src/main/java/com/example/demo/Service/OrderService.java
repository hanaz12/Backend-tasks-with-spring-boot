package com.example.demo.Service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Exceptions.OrderNotFoundException;
import com.example.demo.Exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OrderService {
    @Transactional
    OrderDTO addOrder(OrderDTO orderDTO) throws ProductNotFoundException;
    @Transactional
    OrderDTO updateOrder(OrderDTO orderDTO ,int id) throws ProductNotFoundException;
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(int id) throws OrderNotFoundException;
    @Transactional
    void deleteOrderById(int id) throws OrderNotFoundException;
}
