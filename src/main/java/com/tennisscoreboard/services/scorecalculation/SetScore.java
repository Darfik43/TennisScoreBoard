package com.tennisscoreboard.services.scorecalculation;

import java.util.HashMap;
import java.util.Map;


// SetScore - Score of games within a set
public class SetScore implements ScoreCounter {
    private final Map<String, Integer> setScore;
    private final String player1Name;
    private final String player2Name;
    public boolean isFinished;
    public boolean isTieBreak;

    public SetScore(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.setScore = new HashMap<>();
        this.isFinished = false;
        this.isTieBreak = false;
    }


    @Override
    public void startNew() {
        setScore.put(player1Name, 0);
        setScore.put(player2Name, 0);
        isFinished = false;
        isTieBreak = false;
    }

    public Map<String, Integer> getScore() {
        return setScore;
    }

    public Integer getPlayerSetScore(String playerName) {
        return setScore.get(playerName);
    }

    @Override
    public void updateScore(String playerName) {
        setScore.put(playerName, setScore.get(playerName) + 1);
        updateTieBreakStatus();
        updateFinishedStatus();
    }

    private void updateFinishedStatus() {
        isFinished = ((Math.abs(setScore.get(player1Name) - setScore.get(player2Name))) >= 2)
                && (setScore.get(player1Name) == 7 || setScore.get(player2Name) == 7);
    }
    private void updateTieBreakStatus() {
        isTieBreak = getScore().get(player1Name) == 6
                && getScore().get(player2Name) == 6;
    }
}
