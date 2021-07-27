package com.ncs.tellerapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.tellerapplication.model.Account;
import com.ncs.tellerapplication.model.Customer;
import com.ncs.tellerapplication.model.Transaction;
import com.ncs.tellerapplication.repository.CustomerRepository;
import com.ncs.tellerapplication.repository.TransactionRepository;
import com.ncs.tellerapplication.service.TransactionService;


@RestController
public class TransactionController {
	
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	@Autowired
	TransactionService transactionService;

	@PostMapping("/tx")
	public ResponseEntity<Transaction> save(@RequestBody Transaction tx) {

		return transactionService.saveTransaction(tx);
	}

	@GetMapping("/tx")
	public ResponseEntity<List<Transaction>> findAll() {

		return ResponseEntity.ok(transactionRepository.findAll());

	}
	
	@GetMapping("/tx-approve/{id}")
	public ResponseEntity approveTransacton(@PathVariable(value="id") int id) {

		return ResponseEntity.ok(transactionService.approveTransaction(id));

	}

	
}
