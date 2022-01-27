package com.desafio.managerwithdraws.controller;

import com.desafio.managerwithdraws.services.WithdrawServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/withdrawals")
public class WithdrawController {

    @Autowired
    private WithdrawServices withdrawServices;

//    @GetMapping
//    public ResponseEntity<List<Withdraw>> withdrawList() {
//        return ResponseEntity.ok().body(withdrawServices.withdrawList());
//    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Withdraw> accountById(@RequestParam Long id) {
//        return ResponseEntity.ok().body(withdrawServices.withdrawById(id));
//

}


