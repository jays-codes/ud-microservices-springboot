package jayslabs.microservicedemo.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
	
	@NotEmpty(message="Name cannot be empty")
	@Size(min=5, max=30)
	private String name;
	
	@NotEmpty(message="Email cannot be empty")
	@Email(message="Email address should be of valid format")
	private String email;
	
	@NotEmpty(message="Mobile cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
	private String mobileNumber;
	
	private AccountsDTO acctsdto;
}
