package com.desafio.managerwithdraws.services;

import com.desafio.managerwithdraws.entities.Account;
import com.desafio.managerwithdraws.entities.Withdraw;
import com.desafio.managerwithdraws.repositories.AccountRepository;
import com.desafio.managerwithdraws.repositories.WithdrawRepositories;
import com.desafio.managerwithdraws.services.exceptions.WithdrawNotFound;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class WithdrawServices {

    private final Jedis jedis = new Jedis();
    private final Gson gson = new Gson();
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final WithdrawRepositories withdrawRepositories;
    private final AccountRepository accountRepository;

    public WithdrawServices(KafkaTemplate<String, String> kafkaTemplate, WithdrawRepositories withdrawRepositories, AccountRepository accountRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.withdrawRepositories = withdrawRepositories;
        this.accountRepository = accountRepository;
    }

    public List<Withdraw> listAll() {
        return withdrawRepositories.findAll();
    }

    public List<Withdraw> withdrawByAccount(Long id) {
        return withdrawRepositories.findByIdAccount(id);
    }


    public Withdraw withdrawById(Long idWithdraw) {
        idIsExist(idWithdraw);
        Withdraw withdraw = withdrawRepositories.findById(idWithdraw).get();
        return withdraw;
    }

    @KafkaListener(topics = "newAccount", groupId = "group-id")
    private void setWithdraws(String data) {

        Account account = gson.fromJson(data, Account.class);
        accountRepository.save(account);

        String key = Long.toString(account.getId());
        String limit = Integer.toString(account.getLimitWithdraw());
        jedis.set(key, limit);
    }

    @KafkaListener(topics = "newWithdraw", groupId = "group-id")
    private void updateFreeWithdraws(String data) {

        Withdraw withdraw = gson.fromJson(data, Withdraw.class);
        withdrawRepositories.save(withdraw);
        int oldWithdraw = Integer.parseInt(jedis.get(Long.toString(withdraw.getIdAccount())));

        if (oldWithdraw != 0) {
            int updateWithdraw = oldWithdraw - 1;
            jedis.set(Long.toString(withdraw.getIdAccount()), Integer.toString(updateWithdraw));
        }
        updateAccount(withdraw.getIdAccount());
    }

    private Withdraw idIsExist(Long id) {
        return withdrawRepositories.findById(id).orElseThrow(() -> new WithdrawNotFound("O saque com o id " + id +" não existe"));
    }

    private void updateAccount(Long id) {
        Account account = accountRepository.getById(id);
        account.setWithdrawalsMade(account.getWithdrawalsMade() + 1);
        accountRepository.save(account);
    }
}
