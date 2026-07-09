package com.fraud_remittance.customer_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    private Long customerId;

    private String name;

    private Integer age;

    private String address;

}
