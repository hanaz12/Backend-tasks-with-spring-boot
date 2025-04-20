package com.example.demo.Controller;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Exceptions.SuccessResponse;
import com.example.demo.Service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService OrderService;

    @GetMapping
    public ResponseEntity<SuccessResponse> getorders() {
        List<OrderDTO> orders=OrderService.getAllOrders();
        SuccessResponse response = new SuccessResponse("orders retrieved successfully", HttpStatus.OK.value(), orders);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getOrderById(@PathVariable int id) {
        OrderDTO Order=OrderService.getOrderById(id);
        SuccessResponse response = new SuccessResponse("Order retrieved successfully", HttpStatus.OK.value(), Order);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<SuccessResponse> addOrder(@Valid @RequestBody OrderDTO OrderDTO) {
        OrderDTO Order=OrderService.addOrder(OrderDTO);
        SuccessResponse response = new SuccessResponse("Order added successfully", HttpStatus.OK.value(), Order);
        return ResponseEntity.ok(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<SuccessResponse> updateOrder(@PathVariable int id, @Valid @RequestBody OrderDTO OrderDTO) {
        OrderDTO Order=OrderService.updateOrder(OrderDTO, id);
        SuccessResponse response = new SuccessResponse("Order updated successfully", HttpStatus.OK.value(), Order);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<SuccessResponse> deleteOrder(@PathVariable int id) {
        OrderService.deleteOrderById(id);
        SuccessResponse response = new SuccessResponse("Order deleted successfully", HttpStatus.OK.value(), null);
        return ResponseEntity.ok(response);
    }
}

