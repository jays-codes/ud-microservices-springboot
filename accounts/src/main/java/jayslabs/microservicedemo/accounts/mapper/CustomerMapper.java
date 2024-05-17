package jayslabs.microservicedemo.accounts.mapper;

import jayslabs.microservicedemo.accounts.dto.CustomerDTO;
import jayslabs.microservicedemo.accounts.dto.CustomerDetailsDTO;
import jayslabs.microservicedemo.accounts.entity.Customer;

public class CustomerMapper {
	public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDto) {
		customerDto.setName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		return customerDto;
	}

	public static Customer mapToCustomer(CustomerDTO customerDto, Customer customer) {
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		return customer;
	}

	public static CustomerDetailsDTO mapToCustomerDetailsDTO(Customer cust, CustomerDetailsDTO dto) {
		dto.setName(cust.getName());
		dto.setEmail(cust.getEmail());
		dto.setMobileNumber(cust.getMobileNumber());
		return dto;
	}
}
