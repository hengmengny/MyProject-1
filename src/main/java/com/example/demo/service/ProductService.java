package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResponseException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        final var checkProduct = productRepository.findById(id);
        if (checkProduct.isEmpty()) {
            throw new ResponseException("Product Not Found : " + id);
        }
        product.setId(id);
        return productRepository.save(product);

    }

    public void deleteProductById(Integer id) {
        final var checkProduct = productRepository.findById(id);
        if (checkProduct.isEmpty()) {
            throw new ResponseException("Product Not Found : " + id);
        }
        productRepository.deleteById(id);
    }

}
