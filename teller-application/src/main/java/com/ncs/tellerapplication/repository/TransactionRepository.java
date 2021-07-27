package com.ncs.tellerapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ncs.tellerapplication.model.Account;
import com.ncs.tellerapplication.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	public static final String FIND_PENDING_APPROVAL_TX = "SELECT * FROM transaction where status=? AND acc_no=?;";

	@Query(value = FIND_PENDING_APPROVAL_TX, nativeQuery = true)
	public List<Object[]> findPendingApprvalTx(String status, int accNo);

	List<Transaction> findAllByAccount(Account account);
	
	@Query(value = "CALL CAL_INTEREST();", nativeQuery = true)
	public void executeInterestCalculatorJob();

	

}
