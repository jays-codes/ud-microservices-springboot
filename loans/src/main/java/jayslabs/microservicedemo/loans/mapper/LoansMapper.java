package jayslabs.microservicedemo.loans.mapper;

import jayslabs.microservicedemo.loans.dto.LoansDTO;
import jayslabs.microservicedemo.loans.entity.Loans;

public class LoansMapper {
	public static LoansDTO mapToLoansDTO(Loans loan, LoansDTO dto) {
		dto.setMobileNumber(loan.getMobileNumber());
		dto.setLoanNumber(loan.getLoanNumber());
		dto.setLoanType(loan.getLoanType());
		dto.setTotalLoan(loan.getTotalLoan());
		dto.setAmountPaid(loan.getAmountPaid());
		dto.setOutstandingAmount(loan.getOutstandingAmount());
		return dto;
	}
	
	public static Loans mapToLoans(LoansDTO dto, Loans loan) {
		loan.setMobileNumber(dto.getMobileNumber());
		loan.setLoanNumber(dto.getLoanNumber());
		loan.setLoanType(dto.getLoanType());
		loan.setTotalLoan(dto.getTotalLoan());
		loan.setAmountPaid(dto.getAmountPaid());
		loan.setOutstandingAmount(dto.getOutstandingAmount());
		return loan;
	}
}
