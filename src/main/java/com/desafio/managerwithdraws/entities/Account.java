package com.desafio.managerwithdraws.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
