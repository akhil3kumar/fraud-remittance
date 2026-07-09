package dto.transaction;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TransactionMetadataResponse (
        Long transactionId,
        LocalDateTime timestamp,
        Long merchantId
){
}
