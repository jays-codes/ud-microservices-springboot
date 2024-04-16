package jayslabs.microservicedemo.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
		name="Accounts",
		description="Holds Account information"
)
public class AccountsDTO {
	
	@NotEmpty(message="Account No. cannot be null or empty")
	@Pattern(regexp="(^$|[0-9]{10})", message="Account No. must be 10 digits")
	@Schema(
			description="System generated"
	)
	private Long accountNumber;
	
	@NotEmpty(message="Account Type. cannot be null or empty")
	private String accountType;
	
	@NotEmpty(message="Branch Address cannot be null or empty")
	private String branchAddress;
}
