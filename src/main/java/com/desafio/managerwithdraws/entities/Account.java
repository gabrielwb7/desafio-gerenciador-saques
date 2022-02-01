package com.desafio.managerwithdraws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private Long id;
    @Column
    private Integer limitWithdraw;
    @Column
    private Integer withdrawalsMade = 0;

}
