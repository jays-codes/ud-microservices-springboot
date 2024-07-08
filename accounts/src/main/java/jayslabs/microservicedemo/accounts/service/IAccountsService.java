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
	 * @return boolean - whether or not Update Account is successful
	 **/
	boolean updateAccount(CustomerDTO custdto);
	
	/**
	 * @param custdto - CustomerDTO object
	 * @return boolean - whether or not Delete Account is successful
	 **/
	boolean deleteAccount(String mobile);

	/**
	 * @param acctnum
	 */
	boolean updateCommStatus(Long acctnum);

}
