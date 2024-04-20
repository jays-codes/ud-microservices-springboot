package jayslabs.microservicedemo.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jayslabs.microservicedemo.cards.dto.CardsDTO;
import lombok.Data;

@Data
@Schema(
		name="Cards",
		description="Holds Card information"
)
public class CardsDTO {

	@NotEmpty(message="Mobile cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
	private String mobileNumber;

	@NotEmpty(message="Card No. cannot be null or empty")
	@Pattern(regexp="(^$|[0-9]{12})", message="Card No. must be 12 digits")
	@Schema(
			description = "Card No. of the customer", example = "548732457654"
	)
	private String cardNumber;
	
	@NotEmpty(message="Card Type cannot be null or empty")
    @Schema(
            description = "Type of the card", example = "Credit Card"
    )
	private String cardType;

	@Positive(message="Total Limit should be greater than zero")	
    @Schema(
            description = "Total limit amount", example = "100000"
    )
	private int totalLimit;
	
	@PositiveOrZero(message="Total loan amount used should be equal or greater than zero")
    @Schema(
            description = "Total loan amount used", example = "1000"
    )	
	private int amountUsed;

	@PositiveOrZero(message="Total available should be equal or greater than zero")
    @Schema(
            description = "Total available amount for this card", example = "99000"
    )	
	private int availableAmount;
}
