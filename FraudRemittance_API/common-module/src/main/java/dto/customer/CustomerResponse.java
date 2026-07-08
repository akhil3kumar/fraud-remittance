package dto.customer;

import lombok.Builder;

@Builder
public record CustomerResponse (
        Long customerId,
        String name,
        Integer age,
        String address
){
}
