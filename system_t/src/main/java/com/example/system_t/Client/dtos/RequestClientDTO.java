package com.example.system_t.Client.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


public record RequestClientDTO(String name, @NotBlank String email, @NotNull BigDecimal balance, @NotNull BigDecimal credit_limit) {
}
