package com.desafio.managerwithdraws.services.exceptions;

public class WithdrawNotFound extends RuntimeException {
    public WithdrawNotFound(String msg) {
        super(msg);
    }
}
