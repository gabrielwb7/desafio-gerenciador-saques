package com.desafio.managerwithdraws.controller;

import com.desafio.managerwithdraws.entities.Withdraw;
import com.desafio.managerwithdraws.services.WithdrawServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/withdrawals")
public class WithdrawController {

    @Autowired
    private WithdrawServices withdrawServices;

    @GetMapping
    public ResponseEntity<List<Withdraw>> withdrawList() {
        return ResponseEntity.ok().body(withdrawServices.listAll());
    }

    @GetMapping(value = "/withdraws")
    public ResponseEntity<List<Withdraw>> withdrawByAccount(@RequestParam Long idAccount) {
        return ResponseEntity.ok().body(withdrawServices.withdrawByAccount(idAccount));
    }

    @GetMapping(value = "/v1")
    public ResponseEntity<Withdraw> withdrawById(@RequestParam Long idWithdraw) {
        return ResponseEntity.ok().body(withdrawServices.withdrawById(idWithdraw));
    }

}


