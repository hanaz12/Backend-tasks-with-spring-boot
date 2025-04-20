package com.example.demo.Service.ServiceImpl;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Exceptions.OrderNotFoundException;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Mapper.OrderMapper;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderItem;
import com.example.demo.Model.Product;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDTO addOrder(OrderDTO orderDTO) throws ProductNotFoundException {


        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }


        Order order = orderMapper.toEntity(orderDTO);
        System.out.println("after ordermapper to entity");

        List<OrderItem> items = orderDTO.getItems().stream()
                .map(orderItemDTO -> {

                    Product product = productRepository.findById(orderItemDTO.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException(orderItemDTO.getProductId()));
                    OrderItem orderItem = orderMapper.toItemEntity(orderItemDTO);
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toList());


        order.setItems(items);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(OrderDTO orderDTO, int id) throws ProductNotFoundException, OrderNotFoundException {
        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));


        orderMapper.updateOrderFromDTO(orderDTO, existingOrder);


        List<OrderItem> items = orderDTO.getItems().stream()
                .map(orderItemDTO -> {
                    Product product = productRepository.findById(orderItemDTO.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException(orderItemDTO.getProductId()));

                    OrderItem orderItem = orderMapper.toItemEntity(orderItemDTO);
                    orderItem.setOrder(existingOrder);
                    return orderItem;
                })
                .collect(Collectors.toList());


        existingOrder.getItems().clear();
        existingOrder.getItems().addAll(items);


        Order updatedOrder = orderRepository.save(existingOrder);


        return orderMapper.toDTO(updatedOrder);
    }
    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(int id) throws OrderNotFoundException {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(()-> new OrderNotFoundException(id));
        return orderMapper.toDTO(order);
    }

    @Override
    @Transactional
    public void deleteOrderById(int id) throws OrderNotFoundException {
    Order order = orderRepository
            .findById(id)
            .orElseThrow(()-> new OrderNotFoundException(id));
    orderRepository.delete(order);
    }
}
