package com.example.system_t.Transaction;


import com.example.system_t.Transaction.Dtos.TransactionRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
@EqualsAndHashCode

public class Transaction extends RepresentationModel<Transaction> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT")
    @Column(unique = true)
    private Timestamp expiry_date;

    private Double interest_rate;

    private BigDecimal amount_owed;

    @Column(unique = true)
    private Long clientId;
    @Column(unique = true)
    private Long creditorId;

    public Transaction(TransactionRequestDTO data){


        this.creditorId = data.creditorId();
        this.clientId = data.clientId();
        this.expiry_date = data.expiry_date();
        this.interest_rate = data.interest_rate();
        this.amount_owed = data.amount_owed();
    }



}
