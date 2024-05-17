package jayslabs.microservicedemo.accounts.service;

import jayslabs.microservicedemo.accounts.dto.CustomerDetailsDTO;

public interface ICustomerService {

	/**
	 * @param mobile - String
	 **/
	CustomerDetailsDTO fetchCustomerDetails(String mobile);
}
