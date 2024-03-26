package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class ProductController {
    private final ProductService productService;

    @GetMapping("products")
    public ResponseEntity<List<ProductResponse>> getProducts() {

        final var products = productService
                .getProducts()
                .stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getQty()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("products")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody Product product) {
        final var createProduct = productService.createProduct(product);
        final var products = new ProductResponse(
                createProduct.getId(),
                createProduct.getName(),
                createProduct.getPrice(),
                createProduct.getQty());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("products/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int id, @RequestBody Product product) {
        final var updateProduct = productService.updateProduct(id, product);
        final var products = new ProductResponse(
                updateProduct.getId(),
                updateProduct.getName(),
                updateProduct.getPrice(),
                updateProduct.getQty());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("products/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
