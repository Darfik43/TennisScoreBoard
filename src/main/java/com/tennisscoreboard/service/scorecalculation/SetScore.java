package com.tennisscoreboard.service.scorecalculation;

import java.util.HashMap;
import java.util.Map;


// SetScore - Score of games within a set
public class SetScore implements ScoreCounter {
    private final Map<String, Integer> setScore;
    private final String player1Name;
    private final String player2Name;
    private TieBreakScore tieBreakScore;
    private boolean isFinished;

    public SetScore(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.setScore = new HashMap<>();
        this.isFinished = false;
    }


    @Override
    public void initialize() {
        setScore.put(player1Name, 0);
        setScore.put(player2Name, 0);
        isFinished = false;
    }

    public void player1WinsPoint() {
        updateScore(player1Name);
    }

    public void player2WinsPoint() {
        updateScore(player2Name);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private void updateFinishedStatus() {
        isFinished = ((Math.abs(setScore.get(player1Name) - setScore.get(player2Name))) >= 2)
                && (setScore.get(player1Name) == 7 || setScore.get(player2Name) == 7);
    }

    public Map<String, Integer> getScore() {
        return setScore;
    }

    @Override
    public void updateScore(String playerName) {
        setScore.put(playerName, setScore.get(playerName) + 1);
        updateFinishedStatus();
    }


    private void startTieBreak() {
        this.tieBreakScore = new TieBreakScore(player1Name, player2Name);
        tieBreakScore.initialize();
    }
}
