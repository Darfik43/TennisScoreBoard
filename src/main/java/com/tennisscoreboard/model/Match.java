package com.tennisscoreboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Match {
        @Id
        //@GeneratedValue(strategy = GenerationType.IDENTITY)
        private UUID id;

        @ManyToOne
        @JoinColumn(name = "player1_id", nullable = false)
        private Player player1;

        @ManyToOne
        @JoinColumn(name = "player2_id", nullable = false)
        private Player player2;

        @ManyToOne
        @JoinColumn(name = "winner_id", nullable = false)
        private Player winner;

        public Match(Player player1, Player player2) {
                this.player1 = player1;
                this.player2 = player2;
        }
    }


