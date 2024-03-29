package com.desafio.managerwithdraws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Withdraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idAccount;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String tax;

    public Withdraw(Long idAccount, Double amount, String date, Double tax) {
        this.idAccount = idAccount;
        this.amount = String.valueOf(amount);
        this.date = date;
        this.tax = String.valueOf(tax);
    }
}
