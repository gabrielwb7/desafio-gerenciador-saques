package com.desafio.managerwithdraws.repositories;

import com.desafio.managerwithdraws.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
