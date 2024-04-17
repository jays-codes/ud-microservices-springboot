package jayslabs.microservicedemo.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(
		name="Loans",
		description="Holds Loan information"
)
public class LoansDTO {

	@NotEmpty(message="Mobile cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
	private String mobileNumber;

	@NotEmpty(message="Loan No. cannot be null or empty")
	@Pattern(regexp="(^$|[0-9]{12})", message="Loan No. must be 12 digits")
	@Schema(
			description = "Loan Number of the customer", example = "548732457654"
	)
	private String loanNumber;

	@NotEmpty(message="Loan Type cannot be null or empty")
    @Schema(
            description = "Type of the loan", example = "Home Loan"
    )
	private String loanType;

	@Positive(message="Total loan amount should be greater than zero")	
    @Schema(
            description = "Total loan amount", example = "100000"
    )
	private int totalLoan;

	@PositiveOrZero(message="Total loan amount paid should be equal or greater than zero")
    @Schema(
            description = "Total loan amount paid", example = "1000"
    )
	private int amountPaid;

	@PositiveOrZero(message="Total loan outstanding should be equal or greater than zero")
    @Schema(
            description = "Total outstanding amount against a loan", example = "99000"
    )
	private int outstandingAmount;	
}
