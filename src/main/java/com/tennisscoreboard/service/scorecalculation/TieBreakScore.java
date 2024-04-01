package com.tennisscoreboard.service.scorecalculation;

import java.util.HashMap;
import java.util.Map;

public class TieBreakScore implements ScoreCounter {
    private final Map<String, Integer> tieBreakScore;
    private final String player1Name;
    private final String player2Name;
    public boolean isFinished;

    public TieBreakScore(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.tieBreakScore = new HashMap<>();
        this.isFinished = false;
    }



    @Override
    public void startNew() {
        tieBreakScore.put(player1Name, 0);
        tieBreakScore.put(player2Name, 0);
        isFinished = false;
    }

    private void updateFinishedStatus() {
        isFinished = (Math.abs(tieBreakScore.get(player1Name) - tieBreakScore.get(player2Name)) >= 2)
                && (tieBreakScore.get(player1Name) >= 7 || tieBreakScore.get(player2Name) >= 7);
    }

    @Override
    public void updateScore(String playerName) {
            tieBreakScore.put(playerName, tieBreakScore.get(playerName) + 1);
            updateFinishedStatus();
    }


    public Map<String, Integer> getTieBreakScore() {
        return new HashMap<>(tieBreakScore);
    }

    public Integer getPlayerTieBreakScore(String playerName) {
        return tieBreakScore.get(playerName);
    }
}