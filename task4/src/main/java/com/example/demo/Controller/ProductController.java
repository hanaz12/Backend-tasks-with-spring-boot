package com.example.demo.Controller;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Exceptions.SuccessResponse;
import com.example.demo.Service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<SuccessResponse> getProducts() {
        List<ProductDTO> products=productService.getProducts();
        SuccessResponse response = new SuccessResponse("Products retrieved successfully", HttpStatus.OK.value(), products);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getProductById(@PathVariable int id) {
        ProductDTO product=productService.getProduct(id);
        SuccessResponse response = new SuccessResponse("Product retrieved successfully", HttpStatus.OK.value(), product);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<SuccessResponse> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO product=productService.AddProduct(productDTO);
        SuccessResponse response = new SuccessResponse("Product added successfully", HttpStatus.OK.value(), product);
        return ResponseEntity.ok(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<SuccessResponse> updateProduct(@PathVariable int id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO product=productService.UpdateProduct(productDTO, id);
        SuccessResponse response = new SuccessResponse("Product updated successfully", HttpStatus.OK.value(), product);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<SuccessResponse> deleteProduct(@PathVariable int id) {
        productService.DeleteProduct(id);
        SuccessResponse response = new SuccessResponse("Product deleted successfully", HttpStatus.OK.value(), null);
        return ResponseEntity.ok(response);
    }
}
