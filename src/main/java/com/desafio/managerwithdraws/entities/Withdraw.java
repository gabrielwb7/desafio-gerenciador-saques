package com.desafio.managerwithdraws.entities;

import lombok.Data;

@Data
public class Withdraw {

    private Long id;
    private int limit;

    public Withdraw(Long id, int limit) {
        this.id = id;
        this.limit = limit;
    }
}
