package com.desafio.managerwithdraws.repositories;

import com.desafio.managerwithdraws.entities.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawRepositories extends JpaRepository<Withdraw, Long> {

    List<Withdraw> findByIdAccount(Long idAccount);
}
