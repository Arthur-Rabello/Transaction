package com.example.system_t.Client;


import com.example.system_t.Client.dtos.RequestClientDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "client")
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)

public class Client extends RepresentationModel<Client> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private BigDecimal credit_limit;

    public Client(RequestClientDTO data){
        this.balance = data.balance();
        this.email = data.email();
        this.name = data.name();
        this.credit_limit = data.credit_limit();
    }

    public void save(Client clientData) {
    }
}
