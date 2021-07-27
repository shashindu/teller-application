package com.ncs.tellerapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.tellerapplication.model.Customer;
import com.ncs.tellerapplication.repository.CustomerRepository;


@RestController
public class CustomerController {
	
	
	@Autowired
	CustomerRepository customerRepository;

	@PostMapping("/customer")
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {

		return ResponseEntity.ok(customerRepository.save(customer));

	}

	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> findAll() {

		return ResponseEntity.ok(customerRepository.findAll());

	}
	
}
