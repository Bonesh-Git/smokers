package com.bonesh.paya.model.service;

import com.bonesh.paya.model.entity.Customer;
import com.bonesh.paya.model.repository.CustomerRepository;

public class CustomerService {
    private final CustomerRepository customerRepository = new
            CustomerRepository();
    public void registerCustomer(Customer customer) {
        if (customer.getFirstName() == null ||
                customer.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (customer.getLastName() == null ||
                customer.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");

        }
        if (customer.getNationalCode() == null ||
                customer.getNationalCode().isEmpty()) {
            throw new IllegalArgumentException("National code cannot be empty");

        }
        customerRepository.save(customer);

}}
