package com.tennisscoreboard.model;

import com.tennisscoreboard.model.Player;
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
//    @Column(name = "matchId", nullable = false, unique = true)
    private int id;

    @ManyToOne//(cascade = CascadeType.ALL)
    //@JoinColumn(name = "playerOne_id", referencedColumnName = "playerId")
    private Player playerOne;

    @ManyToOne//(cascade = CascadeType.ALL)
    //JoinColumn(name = "playerTwo_id", referencedColumnName = "playerId")
    private Player playerTwo;

    @ManyToOne//(cascade = CascadeType.ALL)
   //@JoinColumn(name = "winnerId", referencedColumnName = "playerId")
    private Player winner;
}
