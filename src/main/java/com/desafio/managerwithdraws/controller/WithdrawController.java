package com.desafio.managerwithdraws.controller;

import com.desafio.managerwithdraws.services.WithdrawServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/withdrawals")
public class WithdrawController {

    @Autowired
    private WithdrawServices withdrawServices;

    @GetMapping(value = "/{id}")
    public Integer getWithdrawls(@PathVariable Long id) {
      return withdrawServices.getWithdraws(Long.toString(id));
    }

}
