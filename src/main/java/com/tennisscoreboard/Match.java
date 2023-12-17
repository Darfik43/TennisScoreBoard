package com.tennisscoreboard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchId", nullable = false, unique = true)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerOne_id", referencedColumnName = "PlayerId")
    private Player playerOne;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerTwo_id", referencedColumnName = "PlayerId")
    private Player playerTwo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winnerId", referencedColumnName = "PlayerId")
    private Player winner;
}
