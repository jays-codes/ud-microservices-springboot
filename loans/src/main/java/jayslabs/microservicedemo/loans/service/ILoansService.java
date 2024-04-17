package jayslabs.microservicedemo.loans.service;

import jayslabs.microservicedemo.loans.dto.LoansDTO;

public interface ILoansService {

	/**
	 * @param mobile - String
	 **/
	void createLoan(String mobile);
	
	/**
	 * @param mobile - String
	 **/
	LoansDTO fetchLoan(String mobile);
}
