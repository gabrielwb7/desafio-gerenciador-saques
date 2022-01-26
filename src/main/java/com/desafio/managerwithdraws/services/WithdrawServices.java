package com.desafio.managerwithdraws.services;

import com.desafio.managerwithdraws.entities.Withdraw;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class WithdrawServices {

    Jedis jedis = new Jedis();

//    private final KafkaTemplate<String, String> kafkaTemplate;
//
    private Gson gson = new Gson();

//    public WithdrawServices(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

//    public void getWithdraws(Long key) {
//        Withdraw withdraw = new Withdraw(key, jedis.get(String.valueOf(key)));
//        kafkaTemplate.send("new-withdraw", gson.toJson(withdraw));
//    }

    public Integer getWithdraws(String key) {
        Integer test = Integer.parseInt(jedis.get(key));
        return test;
    }

    @KafkaListener(topics = "newAccount", groupId = "group-id")
    private void setWithdraws(String data) {
        Withdraw withdraw = gson.fromJson(data, Withdraw.class);
        String key = Long.toString(withdraw.getId());
        String limit = Integer.toString(withdraw.getLimit());
        jedis.set(key, limit);
    }

    @KafkaListener(topics = "newWithdraw", groupId = "group-id")
    private void incrementWithdraws(String newWithdraw) {
        int oldWithdraw = Integer.parseInt(jedis.get(newWithdraw));
        int updateWithdraw = oldWithdraw - 1;
        jedis.set(newWithdraw, Integer.toString(updateWithdraw));
    }
}
