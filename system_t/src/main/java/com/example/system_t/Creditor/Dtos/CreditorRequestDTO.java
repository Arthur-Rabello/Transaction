package com.example.system_t.Creditor.Dtos;


import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


public record CreditorRequestDTO(String name, @NotNull BigDecimal amount) {
}
