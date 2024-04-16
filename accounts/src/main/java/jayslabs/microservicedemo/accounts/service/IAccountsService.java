package jayslabs.microservicedemo.accounts.service;

import jayslabs.microservicedemo.accounts.dto.CustomerDTO;

public interface IAccountsService {
	
	/**
	 * @param custdto - CustomerDTO object
	 **/
	void createAccount(CustomerDTO custdto);
	
	/**
	 * @param mobile - String
	 **/
	CustomerDTO fetchAccount(String mobile);

	
	/**
	 * @param custdto - CustomerDTO object
	 **/
	boolean updateAccount(CustomerDTO custdto);

}
