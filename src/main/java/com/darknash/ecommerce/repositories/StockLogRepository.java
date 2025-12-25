package com.darknash.ecommerce.repositories;

import com.darknash.ecommerce.entities.StockLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockLogRepository extends JpaRepository<StockLog, UUID> {
}
