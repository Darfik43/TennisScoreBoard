package com.tennisscoreboard.service.scorecalculation;

import java.util.HashMap;
import java.util.Map;

public class TieBreakScore {
    private final Map<String, Integer> tieBreakScore;
    private final String player1Name;
    private final String player2Name;

    public TieBreakScore(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.tieBreakScore = new HashMap<>();
    }

    public void initializeTieBreakScore() {
        tieBreakScore.put(player1Name, 0);
        tieBreakScore.put(player2Name, 0);
    }

    public void player1WinsPoint() {
        tieBreakScore.put(player1Name, tieBreakScore.get(player1Name) + 1);
    }

    public void player2WinsPoint() {
        tieBreakScore.put(player2Name, tieBreakScore.get(player2Name) + 1);
    }

    private void updateTieBreakScore(String winningPlayer) {
        tieBreakScore.put(winningPlayer, tieBreakScore.get(winningPlayer) + 1);
    }

    public Map<String, Integer> getTieBreakScore() {
        return new HashMap<>(tieBreakScore);
    }

    public boolean isTieBreakFinished() {
        int player1TieBreakScore = tieBreakScore.get(player1Name);
        int player2TieBreakScore = tieBreakScore.get(player2Name);

        return (Math.abs(player1TieBreakScore - player2TieBreakScore) >= 2)
                && (player1TieBreakScore >= 7 || player2TieBreakScore >= 7);
    }
}