package com.ncs.tellerapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.tellerapplication.model.Account;
import com.ncs.tellerapplication.model.Customer;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	List<Account> findAllByCustomer(Customer customer);

}
