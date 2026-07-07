package dto.transaction;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionRequest(

        @NotNull(message = "Customer ID is required")
        Long customerId,

        @NotBlank(message = "Beneficiary name is required")
        @Size(min = 2, max = 100,
                message = "Beneficiary name must be between 2 and 100 characters")
        String beneficiaryName,

        @NotBlank(message = "Beneficiary account is required")
        @Size(min = 5, max = 50,
                message = "Beneficiary account must be between 5 and 50 characters")
        String beneficiaryAccount,

        @NotBlank(message = "Destination country is required")
        @Size(min = 2, max = 100,
                message = "Destination country must be between 2 and 100 characters")
        String destinationCountry,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01",
                message = "Amount must be greater than 0")
        @Digits(integer = 15, fraction = 2,
                message = "Amount format is invalid")
        BigDecimal amount,

        @NotBlank(message = "Currency is required")
        @Pattern(
                regexp = "^[A-Z]{3}$",
                message = "Currency must be a valid 3-letter ISO code (e.g. USD, INR, EUR)"
        )
        String currency

) {
}
