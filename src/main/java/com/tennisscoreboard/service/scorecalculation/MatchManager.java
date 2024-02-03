package com.tennisscoreboard.service.scorecalculation;

import com.tennisscoreboard.model.Match;

public class MatchManager {
    private final Score score;
    private String player1Name;
    private String player2Name;

    public MatchManager(Match match) {
        this.player1Name = match.getPlayer1().getName();
        this.player2Name = match.getPlayer2().getName();
        this.score = new Score(player1Name, player2Name);
    }

    public static MatchManager getInstance(Match match) {
        return new MatchManager(match);
    }



    public void startNewMatch() {
        Score score = new Score()
    }
}
