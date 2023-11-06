package com.example.system_t.Client.dtos;


import com.example.system_t.Client.Client;
import java.math.BigDecimal;


public record ClientResponseDTO(Long id, String name, String email, BigDecimal balance, BigDecimal credit_limit) {

    public ClientResponseDTO(Client client){
        this(client.getId(), client.getName(), client.getEmail(), client.getBalance(), client.getCredit_limit());
    }
}
