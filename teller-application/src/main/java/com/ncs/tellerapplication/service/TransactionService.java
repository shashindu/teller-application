package com.ncs.tellerapplication.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ncs.tellerapplication.model.Account;
import com.ncs.tellerapplication.model.Transaction;
import com.ncs.tellerapplication.repository.AccountRepository;
import com.ncs.tellerapplication.repository.TransactionRepository;
import com.ncs.tellerapplication.util.TxStatus;
import com.ncs.tellerapplication.util.TxTypeEnum;

@Service
public class TransactionService {

	Logger logger = LoggerFactory.getLogger(Transaction.class);

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	public ResponseEntity saveTransaction(Transaction tx) {

		Account account = accountRepository.findById(tx.getAccount().getAccNo()).get();
		
		if (tx.getTxType().equals(TxTypeEnum.DEBITED.name())) {

			List<Object[]> txList = transactionRepository.findPendingApprvalTx(TxStatus.PENDING_APPROVAL.name(),tx.getAccount().getAccNo());
			if (txList == null || txList.isEmpty()) {
				if (tx.getTxType().equals(TxTypeEnum.DEBITED.name()) && tx.getAmount().doubleValue() > 1000.00) {
					tx.setStatus(TxStatus.PENDING_APPROVAL.name());

				}else {
					tx.setStatus(TxStatus.COMPLETED.name());
				}

				// Valid transaction - Have enough balance
				if (tx.getTxType().equals(TxTypeEnum.DEBITED.name()) && account.getClosingBalance().doubleValue() > tx.getAmount().doubleValue()) {

					// Update the closing balance of account
					BigDecimal balance = account.getClosingBalance().subtract(tx.getAmount());
					account.setClosingBalance(balance);
					accountRepository.save(account);
					logger.info("Transaction saved !!");
					return ResponseEntity.ok(transactionRepository.save(tx));

				}else {

					logger.error("Transaction has been aborted due to not enough balance");
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not enough balance !");

				}

			} else {

				logger.error("This acccount has one pending transaction to be approved !");
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body("You have one transaction pending for approval !");

			}

		}else if (tx.getTxType().equals(TxTypeEnum.CREDITED.name())) {
			
					// Update the closing balance of account
					logger.info("closing balance {} amount {} !!", account.getClosingBalance(), tx.getAmount());
					BigDecimal balance = account.getClosingBalance().add(tx.getAmount());
					account.setClosingBalance(balance);
					accountRepository.save(account);
					tx.setStatus(TxStatus.COMPLETED.name());
					logger.info("Transaction saved !!");
					return ResponseEntity.ok(transactionRepository.save(tx));

		}
		return null;
		

	}
	
	
	
	
	
	
	public ResponseEntity approveTransaction(int id) {
			
		Transaction transaction = transactionRepository.findById(id).get();
		
		if(transaction != null && transaction.getStatus().equals(TxStatus.PENDING_APPROVAL.name())) {
			transaction.setStatus(TxStatus.COMPLETED.name());			
			return ResponseEntity.ok(transactionRepository.save(transaction));
		}else {
			
			logger.error("No transaction found !");
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("No transaction found for for given ID !");
		}

		
	}

}
