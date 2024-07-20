package com.hcpark.tobyspring6;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Payment(Long orderId, String currency, BigDecimal payAmount, BigDecimal exchangeRate, BigDecimal convertedPayAmount, LocalDateTime validUntil) {
}
