package com.fraud_remittance.transaction_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_metadata")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionMetadata {

    @Id
    private Long transactionId;

    @Column(name = "transaction_time")
    private LocalDateTime timestamp;

    private Long merchantId;
}