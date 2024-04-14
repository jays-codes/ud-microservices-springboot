package jayslabs.microservicedemo.accounts.service.impl;

import org.springframework.stereotype.Service;

import jayslabs.microservicedemo.accounts.dto.CustomerDTO;
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
		
	}

}
