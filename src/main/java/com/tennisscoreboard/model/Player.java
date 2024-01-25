package com.tennisscoreboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "playerId")
    private long id;

    @Column(name = "name")
    private String name;

}
