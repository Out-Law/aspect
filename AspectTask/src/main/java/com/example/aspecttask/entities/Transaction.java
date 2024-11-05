package com.example.aspecttask.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_seq")
    @SequenceGenerator(name = "events_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "transaction_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal transactionAmount;

    @Column(name = "transaction_time", nullable = false)
    private OffsetDateTime transactionTime = OffsetDateTime.now();

}
