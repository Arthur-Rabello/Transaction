package com.example.system_t.Transaction.Dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record TransactionRequestDTO ( Long clientId,  Long creditorId, @NotBlank Timestamp expiry_date, @NotNull Double interest_rate, @NotNull BigDecimal amount_owed){



}
