package com.desafio.managerwithdraws.controller;

import com.desafio.managerwithdraws.services.WithdrawServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/withdrawals")
public class WithdrawController {

    @Autowired
    private WithdrawServices withdrawServices;

    @GetMapping(value = "/{id}")
    public Integer getWithdrawls(@PathVariable Long id) {
        return withdrawServices.getWithdraws(Long.toString(id));
    }

    @PostMapping(value = "/v1/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void setWithdrawls(@PathVariable Long id) {
        String key = Long.toString(id);
        withdrawServices.setWithdraws(key);
    }

    @PutMapping("/{idAccount}")
    public void incrementWithdraws(@PathVariable Long idAccount) {
        withdrawServices.incrementWithdraws(Long.toString(idAccount));
    }
}
