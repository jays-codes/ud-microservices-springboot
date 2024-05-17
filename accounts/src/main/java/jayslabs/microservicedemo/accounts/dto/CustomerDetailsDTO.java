package jayslabs.microservicedemo.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
		name="CustomerDetails",
		description="Holds Customer, Account, Cards and Loans information"
)
public class CustomerDetailsDTO {
	
	@Schema(
			description="Customer name",
			example="John Doe"
	)
	@NotEmpty(message="Name cannot be empty")
	@Size(min=5, max=30)
	private String name;
	
	@NotEmpty(message="Email cannot be empty")
	@Email(message="Email address should be of valid format")
	private String email;
	
	@NotEmpty(message="Mobile cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
	private String mobileNumber;
	
	@Schema(
			description="Customer Account Details"
	)
	private AccountsDTO acctsdto;

	@Schema(
			description="Customer Cards Details"
	)
	private CardsDTO cardsdto;
	
	@Schema(
			description="Customer Loans Details"
	)
	private LoansDTO loansdto;
}
