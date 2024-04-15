package jayslabs.microservicedemo.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import jayslabs.microservicedemo.accounts.constants.AccountsConstants;
import jayslabs.microservicedemo.accounts.dto.CustomerDTO;
import jayslabs.microservicedemo.accounts.entity.Accounts;
import jayslabs.microservicedemo.accounts.entity.Customer;
import jayslabs.microservicedemo.accounts.exception.CustomerAlreadyExistsException;
import jayslabs.microservicedemo.accounts.mapper.CustomerMapper;
import jayslabs.microservicedemo.accounts.repository.AccountsRepository;
import jayslabs.microservicedemo.accounts.repository.CustomerRepository;
import jayslabs.microservicedemo.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private AccountsRepository acctsrepo;
	private CustomerRepository custrepo;
	
	@Override
	public void createAccount(CustomerDTO custdto) {		
		Customer cust = CustomerMapper.mapToCustomer(custdto, new Customer());
		Optional<Customer> customer = custrepo.findByMobileNumber(custdto.getMobileNumber());
		if (customer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registerred with give mobile number " 
					+ custdto.getMobileNumber());
		}
		
		cust.setCreatedAt(LocalDateTime.now());
		cust.setCreatedBy("anonymous");
		Customer savedCust = custrepo.save(cust);
		acctsrepo.save(createNewAccount(savedCust));
	}

	
	private Accounts createNewAccount(Customer cust) {
		Accounts newacct = new Accounts();
		newacct.setCustomerId(cust.getCustomerId());
		
		long randacctno = 1000000000L + new Random().nextInt(900000000);
		
		newacct.setAccountNumber(randacctno);
		newacct.setAccountType(AccountsConstants.SAVINGS);
		newacct.setBranchAddress(AccountsConstants.ADDRESS);
		newacct.setCreatedAt(LocalDateTime.now());
		newacct.setCreatedBy("anonymous");
		
		return newacct;
	}
}
