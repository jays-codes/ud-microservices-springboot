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
	
	/**
	 * @param dto - LoansDTO object
	 * @return boolean - whether or not Update Loan is successful
	 **/
	boolean updateLoan(LoansDTO dto);
	
	/**
	 * @param mobile- Mobile Number
	 * @return boolean - whether or not Delete Loan is successful
	 **/
	boolean deleteLoan(String mobile);
}
