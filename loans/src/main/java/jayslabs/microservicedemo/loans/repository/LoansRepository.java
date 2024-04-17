package jayslabs.microservicedemo.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import jayslabs.microservicedemo.loans.entity.Loans;

public interface LoansRepository extends JpaRepository<Loans, Long> {
	Optional<Loans> findByMobileNumber(String mobileNumber);
	Optional<Loans> findByLoanNumber(String loanNumber);

	@Transactional
	void deleteByMobileNumber(String mobileNumber);

	
}
