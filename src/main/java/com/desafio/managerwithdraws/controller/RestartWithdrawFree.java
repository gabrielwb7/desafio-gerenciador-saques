package com.desafio.managerwithdraws.controller;

import com.desafio.managerwithdraws.entities.Account;
import com.desafio.managerwithdraws.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Component
@EnableScheduling
@AllArgsConstructor
public class RestartWithdrawFree {

    private final AccountRepository accountRepository;
    private final Jedis jedis = new Jedis();
    private final long MINUTE = 60000;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Scheduled(fixedDelay = MINUTE)
    public void restartWithdraw () {
        String currentDate = dateTimeFormatter.format(LocalDateTime.now());
        String lastDay = dateTimeFormatter.format(LocalDate.now().with(lastDayOfMonth()).atTime(23, 59));

        if (currentDate.equals(lastDay)) {
            List<Account> accountList = accountRepository.findAll();
            for (Account account : accountList) {
                String key = Long.toString(account.getId());
                String value = Integer.toString(account.getLimitWithdraw());

                jedis.set(key,value);
            }
        }
    }
}
