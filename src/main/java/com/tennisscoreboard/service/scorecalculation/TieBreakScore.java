package com.tennisscoreboard.service.scorecalculation;

import java.util.HashMap;
import java.util.Map;

public class TieBreakScore implements ScoreCounter {
    private final Map<String, Integer> tieBreakScore;
    private final String player1Name;
    private final String player2Name;
    private boolean isFinished;

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

    public void player1WinsPoint() {
        updateScore(player1Name);
    }

    public void player2WinsPoint() {
        updateScore(player2Name);
    }

    @Override
    public boolean getIsFinished() {
        return isFinished;
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

    public boolean isTieBreakFinished() {
        int player1TieBreakScore = tieBreakScore.get(player1Name);
        int player2TieBreakScore = tieBreakScore.get(player2Name);

        return (Math.abs(player1TieBreakScore - player2TieBreakScore) >= 2)
                && (player1TieBreakScore >= 7 || player2TieBreakScore >= 7);
    }
}