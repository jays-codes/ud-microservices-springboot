package jayslabs.microservicedemo.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jayslabs.microservicedemo.accounts.dto.AccountsDTO;
import jayslabs.microservicedemo.accounts.dto.CardsDTO;
import jayslabs.microservicedemo.accounts.dto.CustomerDetailsDTO;
import jayslabs.microservicedemo.accounts.dto.LoansDTO;
import jayslabs.microservicedemo.accounts.entity.Accounts;
import jayslabs.microservicedemo.accounts.entity.Customer;
import jayslabs.microservicedemo.accounts.exception.ResourceNotFoundException;
import jayslabs.microservicedemo.accounts.mapper.AccountsMapper;
import jayslabs.microservicedemo.accounts.mapper.CustomerMapper;
import jayslabs.microservicedemo.accounts.repository.AccountsRepository;
import jayslabs.microservicedemo.accounts.repository.CustomerRepository;
import jayslabs.microservicedemo.accounts.service.ICustomerService;
import jayslabs.microservicedemo.accounts.service.client.CardsFeignClient;
import jayslabs.microservicedemo.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

	private AccountsRepository acctsrepo;
	private CustomerRepository custrepo;
	private CardsFeignClient cardsfeign;
	private LoansFeignClient loansfeign;
	
	@Override
	public CustomerDetailsDTO fetchCustomerDetails(String mobile, String corrId) {
		Customer customer = custrepo.findByMobileNumber(mobile).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobile)
				);
		Long id = customer.getCustomerId();
		Accounts acct = acctsrepo.findByCustomerId(id).orElseThrow(
				() -> new ResourceNotFoundException("Accounts", "customerId", id.toString())
				);
		AccountsDTO acctsdto = AccountsMapper.mapToAccountsDTO(acct, new AccountsDTO());
		CustomerDetailsDTO custdetdto = CustomerMapper.mapToCustomerDetailsDTO(customer, new CustomerDetailsDTO());
		custdetdto.setAcctsdto(acctsdto);
		
		ResponseEntity<LoansDTO> loansdto = loansfeign.fetchLoanDetails(corrId, mobile);
		if (null!=loansdto) {
			custdetdto.setLoansdto(loansdto.getBody());			
		}

		ResponseEntity<CardsDTO> cardsdto = cardsfeign.fetchCardDetails(corrId, mobile);
		if (null!=cardsdto) {
			custdetdto.setCardsdto(cardsdto.getBody());			
		}
		
		return custdetdto;		

	}

}
