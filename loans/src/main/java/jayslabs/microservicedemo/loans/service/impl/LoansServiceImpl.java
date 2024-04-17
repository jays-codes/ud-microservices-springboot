package jayslabs.microservicedemo.loans.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import jayslabs.microservicedemo.loans.constants.LoansConstants;
import jayslabs.microservicedemo.loans.dto.LoansDTO;
import jayslabs.microservicedemo.loans.entity.Loans;
import jayslabs.microservicedemo.loans.exception.LoansAlreadyExistsException;
import jayslabs.microservicedemo.loans.exception.ResourceNotFoundException;
import jayslabs.microservicedemo.loans.mapper.LoansMapper;
import jayslabs.microservicedemo.loans.repository.LoansRepository;
import jayslabs.microservicedemo.loans.service.ILoansService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

	private LoansRepository repo;
	
	@Override
	public void createLoan(String mobile) {
		if (repo.findByMobileNumber(mobile).isPresent()) {
			throw new LoansAlreadyExistsException("Loan already registered with given mobile number " 
					+ mobile);
		}
		repo.save(createNewLoan(mobile));
	}

	private Loans createNewLoan(String mobile) {
		Loans loan = new Loans();
		loan.setMobileNumber(mobile);
		Long randnum = 100000000000L + new Random().nextInt(900000000);
		loan.setLoanNumber(randnum.toString());
		loan.setLoanType(LoansConstants.PRIVATE);
		loan.setAmountPaid(0);
		loan.setTotalLoan(LoansConstants.INIT_LOAN);
		loan.setAmountPaid(0);
		loan.setOutstandingAmount(LoansConstants.INIT_LOAN);
		return loan;
	}

	@Override
	public LoansDTO fetchLoan(String mobile) {
		Loans loan = repo.findByMobileNumber(mobile).orElseThrow(
				()-> new ResourceNotFoundException("Loans", "Mobile Number", mobile.toString())
				);
		
		LoansDTO dto = LoansMapper.mapToLoansDTO(loan, new LoansDTO());
		
		return dto;
	}

	@Override
	public boolean updateLoan(LoansDTO dto) {
		String loannum = dto.getLoanNumber();
		
		if (loannum.isEmpty() || loannum.isBlank()) return false;
		
		Loans loan = repo.findByLoanNumber(loannum).orElseThrow(
				()-> new ResourceNotFoundException("Loans", "Loan Number", loannum)
				);
		
		String newnum = dto.getMobileNumber();		
		Optional<Loans> findloan = repo.findByMobileNumber(newnum);
		if (findloan.isPresent()) {
			if (
					findloan.get().getLoanNumber()
					.equalsIgnoreCase(loan.getLoanNumber())==false) {
				throw new LoansAlreadyExistsException("Loan already registered with given mobile number " 
						+ newnum);
			}							
		}
		
		loan = LoansMapper.mapToLoans(dto, loan);
		repo.save(loan);
		
		return true;
	}

	@Override
	public boolean deleteLoan(String mobile) {
		Loans loan = repo.findByMobileNumber(mobile).orElseThrow(
				()-> new ResourceNotFoundException("Loans", "Mobile Number", mobile.toString())
				);

		repo.deleteByMobileNumber(mobile);
		return true;
	}
}
