package com.tennisscoreboard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

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
    private int id;

    @Column(name = "name")
    private String name;

}
