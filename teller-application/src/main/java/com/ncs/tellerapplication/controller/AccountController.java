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
import com.ncs.tellerapplication.repository.AccountRepository;
import com.ncs.tellerapplication.repository.CustomerRepository;
import com.ncs.tellerapplication.repository.TransactionRepository;


@RestController
public class AccountController {
	
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	

	@PostMapping("/account")
	public ResponseEntity<Account> save(@RequestBody Account account) {

		return ResponseEntity.ok(accountRepository.save(account));

	}

	@GetMapping("/account")
	public ResponseEntity<List<Account>> findAll() {

		return ResponseEntity.ok(accountRepository.findAll());

	}

	
	@GetMapping("account/{id}")
    public ResponseEntity findTxsByAccId(@PathVariable(value="id") int id) {
			
		Account account = accountRepository.findById(id).get();
		return ResponseEntity.ok(transactionRepository.findAllByAccount(account));
	}
	
	@GetMapping("accountByCustomer/{id}")
    public ResponseEntity findAccountsBycustomerId(@PathVariable(value="id") int id) {
	
		
		Customer customer = customerRepository.findById(id).get();
		return ResponseEntity.ok(accountRepository.findAllByCustomer(customer));
	}
	
}
