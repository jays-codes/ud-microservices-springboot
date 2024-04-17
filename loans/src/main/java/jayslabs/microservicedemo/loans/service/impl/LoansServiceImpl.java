package jayslabs.microservicedemo.loans.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import jayslabs.microservicedemo.loans.constants.LoansConstants;
import jayslabs.microservicedemo.loans.entity.Loans;
import jayslabs.microservicedemo.loans.exception.LoansAlreadyExistsException;
import jayslabs.microservicedemo.loans.repository.LoansRepository;
import jayslabs.microservicedemo.loans.service.ILoansService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

	private LoansRepository loansrepo;
	
	@Override
	public void createLoan(String mobile) {
		if (loansrepo.findByMobileNumber(mobile).isPresent()) {
			throw new LoansAlreadyExistsException("Loan already registered with given mobile number " 
					+ mobile);
		}
		loansrepo.save(createNewLoan(mobile));
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
}
