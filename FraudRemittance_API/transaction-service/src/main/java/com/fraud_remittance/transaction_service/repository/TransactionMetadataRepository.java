package com.fraud_remittance.transaction_service.repository;

import com.fraud_remittance.transaction_service.entity.TransactionMetadata;
import com.netflix.spectator.api.histogram.PercentileBuckets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMetadataRepository extends JpaRepository<TransactionMetadata, Long> {
    Page<TransactionMetadata> findByTransactionId(Long transactionId, Pageable pageable);
}
