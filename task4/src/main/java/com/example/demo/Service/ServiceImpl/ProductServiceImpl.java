package com.example.demo.Service.ServiceImpl;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Exceptions.CategoryNotFoundException;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Model.Category;
import com.example.demo.Model.Product;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDTO AddProduct(ProductDTO productDTO) throws CategoryNotFoundException {
        List<Category> categories = productDTO.getCategories().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new CategoryNotFoundException(categoryId)))
                .collect(Collectors.toList());


        Product product = productMapper.toEntity(productDTO);
        product.setCategories(categories);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    @Transactional
    public ProductDTO UpdateProduct(ProductDTO productDTO, int id) throws CategoryNotFoundException, ProductNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));


        List<Category> categories = productDTO.getCategories().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new CategoryNotFoundException(categoryId)))
                .collect(Collectors.toList());

        productMapper.updateProductFromDTO(productDTO, existingProduct);
        existingProduct.setCategories(categories);


        Product updatedProduct = productRepository.save(existingProduct);


        return productMapper.toDTO(updatedProduct);

    }

    @Override
    public void DeleteProduct(int id) throws ProductNotFoundException {
    Product product=
            productRepository
                    .findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProduct(int id) throws ProductNotFoundException {
       Product product=productRepository
               .findById(id)
               .orElseThrow(() -> new ProductNotFoundException(id));
       return productMapper.toDTO(product);
    }
}
