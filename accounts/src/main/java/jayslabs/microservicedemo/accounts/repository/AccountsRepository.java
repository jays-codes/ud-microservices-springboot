package jayslabs.microservicedemo.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import jayslabs.microservicedemo.accounts.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long>{
	Optional<Accounts> findByCustomerId(Long customerId);
	
	@Transactional
	void deleteByCustomerId(Long customerId);
}
