package com.ncs.tellerapplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ncs.tellerapplication.repository.TransactionRepository;

@Service
public class InterestCalculateService {
	
	Logger logger = LoggerFactory.getLogger(InterestCalculateService.class);
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Scheduled(cron="0 30 1 * * ?")
	public void calculateInterest() {
		
		try {
			transactionRepository.executeInterestCalculatorJob();
		} catch (Exception e) {
			logger.error("Unexpected error occured during proc execution !");
		}
		
	
	}

}
