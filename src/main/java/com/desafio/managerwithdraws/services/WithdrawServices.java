package com.desafio.managerwithdraws.services;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class WithdrawServices {

    Jedis jedis = new Jedis();

    public Integer getWithdraws(String key) {
        Integer test = Integer.parseInt(jedis.get(key));
        return test;
    }

    public void setWithdraws(String key) {
        jedis.set(key,"0");
    }

    public void incrementWithdraws(String key) {
        int oldWithdraw = Integer.parseInt(jedis.get(key));
        int newWithdraw = oldWithdraw + 1;
        jedis.set(key, Integer.toString(newWithdraw));
    }
}
