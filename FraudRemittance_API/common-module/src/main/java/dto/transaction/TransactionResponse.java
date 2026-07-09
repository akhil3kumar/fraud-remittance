package dto.transaction;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionResponse (
        Long transactionId,
        Long customerId,
        BigDecimal amount
){
}