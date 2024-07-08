package jayslabs.microservicedemo.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import jayslabs.microservicedemo.accounts.constants.AccountsConstants;
import jayslabs.microservicedemo.accounts.dto.AccountsDTO;
import jayslabs.microservicedemo.accounts.dto.AccountsMsgDTO;
import jayslabs.microservicedemo.accounts.dto.CustomerDTO;
import jayslabs.microservicedemo.accounts.entity.Accounts;
import jayslabs.microservicedemo.accounts.entity.Customer;
import jayslabs.microservicedemo.accounts.exception.CustomerAlreadyExistsException;
import jayslabs.microservicedemo.accounts.exception.ResourceNotFoundException;
import jayslabs.microservicedemo.accounts.mapper.AccountsMapper;
import jayslabs.microservicedemo.accounts.mapper.CustomerMapper;
import jayslabs.microservicedemo.accounts.repository.AccountsRepository;
import jayslabs.microservicedemo.accounts.repository.CustomerRepository;
import jayslabs.microservicedemo.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private static final Logger log = LoggerFactory.getLogger(AccountsServiceImpl.class);

	private AccountsRepository acctsrepo;
	private CustomerRepository custrepo;
	private final StreamBridge streambridge;
	
	@Override
	public void createAccount(CustomerDTO custdto) {		
		Customer cust = CustomerMapper.mapToCustomer(custdto, new Customer());
		Optional<Customer> customer = custrepo.findByMobileNumber(custdto.getMobileNumber());
		if (customer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registered with give mobile number " 
					+ custdto.getMobileNumber());
		}
		
		Customer savedCust = custrepo.save(cust);
		Accounts savedacct = acctsrepo.save(createNewAccount(savedCust));
		sendCommunication(savedacct,savedCust);
	}

	private void sendCommunication(Accounts acct, Customer cust) {
		var msgdto = new AccountsMsgDTO(acct.getAccountNumber(), cust.getName(), cust.getEmail(),
				cust.getMobileNumber());
		log.info("Sending Communication request for details: {}", msgdto);
		var result = streambridge.send("sendCommunication-out-0", msgdto);
		log.info("Comm request successfully triggered ? : {}", result);
	}
	
	private Accounts createNewAccount(Customer cust) {
		Accounts newacct = new Accounts();
		newacct.setCustomerId(cust.getCustomerId());
		
		long randacctno = 1000000000L + new Random().nextInt(900000000);
		
		newacct.setAccountNumber(randacctno);
		newacct.setAccountType(AccountsConstants.SAVINGS);
		newacct.setBranchAddress(AccountsConstants.ADDRESS);
		
		return newacct;
	}


	@Override
	public CustomerDTO fetchAccount(String mobile) {
		
		Customer customer = custrepo.findByMobileNumber(mobile).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobile)
				);
		Long id = customer.getCustomerId();
		Accounts acct = acctsrepo.findByCustomerId(id).orElseThrow(
				() -> new ResourceNotFoundException("Accounts", "customerId", id.toString())
				);
		AccountsDTO acctsdto = AccountsMapper.mapToAccountsDTO(acct, new AccountsDTO());
		CustomerDTO custdto = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
		custdto.setAcctsdto(acctsdto);
		
		return custdto;		
	}


	@Override
	public boolean updateAccount(CustomerDTO custdto) {
		
		AccountsDTO acctsdto = custdto.getAcctsdto();
		if (acctsdto==null) return false;
		
		Long acctnum = acctsdto.getAccountNumber();
		Accounts acct = acctsrepo.findById(acctnum).orElseThrow(
				() -> new ResourceNotFoundException("Accounts", "accountNumber", acctnum.toString())
				);

		AccountsMapper.mapToAccounts(acctsdto, acct);
		acct = acctsrepo.save(acct);
		
		Long custId = acct.getCustomerId();
		Customer cust = custrepo.findById(custId).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "customerId", custId.toString())
				);
		
		
		cust = CustomerMapper.mapToCustomer(custdto, cust);
		custrepo.save(cust);
		
		return true;
	}


	@Override
	public boolean deleteAccount(String mobile) {
		
		Customer cust = custrepo.findByMobileNumber(mobile).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobile)
				);
		Long custId = cust.getCustomerId();
		
		acctsrepo.deleteByCustomerId(custId);
		custrepo.deleteById(custId);
		return true;
	}

	@Override
	public boolean updateCommStatus(Long acctnum) {
		boolean isUpdated = false;
		
		if (acctnum!=null) {
			Accounts acct = acctsrepo
					.findById(acctnum)
					.orElseThrow(() -> new ResourceNotFoundException("Account","AccountNumber",acctnum.toString())
					);
			acct.setCommunicationSw(true);
			acctsrepo.save(acct);
			isUpdated = true;
		}
		
		return isUpdated;
	}
}
