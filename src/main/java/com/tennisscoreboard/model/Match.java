package com.tennisscoreboard.model;

import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.scorecalculation.Score;
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
        private Long id;

        @ManyToOne
        @JoinColumn(name = "Player1", nullable = false)
        private Player player1;

        @ManyToOne
        @JoinColumn(name = "Player2", nullable = false)
        private Player player2;

        @ManyToOne
        @JoinColumn(name = "Winner", nullable = false)
        private Player winner;

        public Match(Player player1, Player player2) {
                this.player1 = player1;
                this.player2 = player2;
        }
    }


