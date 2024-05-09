package jayslabs.microservicedemo.cards.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="cards")
@Getter
@Setter
public class CardsContactInfoDTO{
	private String message;
	private Map<String, String> contactDetails;
	List<String> onCallSupport;
}