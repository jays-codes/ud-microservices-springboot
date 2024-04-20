package jayslabs.microservicedemo.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import jayslabs.microservicedemo.cards.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long> {
	Optional<Cards> findByMobileNumber(String mobileNumber);
	Optional<Cards> findByCardNumber(String loanNumber);

	@Transactional
	void deleteByMobileNumber(String mobileNumber);
}
