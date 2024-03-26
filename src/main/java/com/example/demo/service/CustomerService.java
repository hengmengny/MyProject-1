package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResponseException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public List<Customer> getListCostomer() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Object updateCustomer(Integer id, Customer customer) {
        final var dbCustomId = customerRepository.existsById(id);
        if (!dbCustomId) {
            throw new ResponseException("Customer Not Found : " + id);
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        final var dbCustomer = customerRepository.existsById(id);
        if (!dbCustomer) {
            throw new ResponseException("Customer Not Found : " + id);
        }
        customerRepository.deleteById(id);
    }
}
