package dto.customer;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record AccountActivityResponse (
        Long accountActivityId,
        Long customerId,
        Double accountBalance,
        LocalDate lastLogin
){
}
