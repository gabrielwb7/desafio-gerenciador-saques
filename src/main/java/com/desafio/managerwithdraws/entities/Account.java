package com.desafio.managerwithdraws.entities;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Account {

    private Long id;
    private int limit;

    public Account(Long id, int limit) {
        this.id = id;
        this.limit = limit;
    }
}
