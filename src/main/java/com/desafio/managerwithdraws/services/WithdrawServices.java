package com.desafio.managerwithdraws.services;

import com.desafio.managerwithdraws.entities.Withdraw;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class WithdrawServices {

    private final Jedis jedis = new Jedis();
    private final Gson gson = new Gson();
    private final KafkaTemplate<String, String> kafkaTemplate;

    public WithdrawServices(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "newAccount", groupId = "group-id")
    private void setWithdraws(String data) {
        Withdraw withdraw = gson.fromJson(data, Withdraw.class);
        String key = Long.toString(withdraw.getId());
        String limit = Integer.toString(withdraw.getLimit());
        jedis.set(key, limit);
    }

    @KafkaListener(topics = "newWithdraw", groupId = "group-id")
    private void updateFreeWithdraws(String newWithdraw) {
        int oldWithdraw = Integer.parseInt(jedis.get(newWithdraw));
        int updateWithdraw = oldWithdraw - 1;
        jedis.set(newWithdraw, Integer.toString(updateWithdraw));
    }
}
