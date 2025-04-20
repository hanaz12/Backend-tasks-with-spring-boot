package com.example.demo.Service;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Exceptions.CategoryNotFoundException;
import com.example.demo.Exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ProductService {

    @Transactional
    ProductDTO AddProduct(ProductDTO product) throws CategoryNotFoundException;
    @Transactional
    ProductDTO UpdateProduct(ProductDTO product , int id) throws CategoryNotFoundException, ProductNotFoundException;
    @Transactional
    void DeleteProduct(int id) throws ProductNotFoundException;

    List<ProductDTO> getProducts();

    ProductDTO getProduct(int id) throws ProductNotFoundException;

}
