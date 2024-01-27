package com.tennisscoreboard.service.scorecalculation;

import java.util.HashMap;
import java.util.Map;

public class SetScore {
    private final Map<String, Integer> setScore;
    private GameScore gameScore;
    private final String player1Name;
    private final String player2Name;
    private TieBreakScore tieBreakScore;

    protected SetScore(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.setScore = new HashMap<>();
        this.gameScore = new GameScore(player1Name, player2Name);
    }

    public void initializeSetScore() {
        setScore.put(player1Name, 0);
        setScore.put(player2Name, 0);
        gameScore.initializeGameScore();
    }

    public void player1WinsPoint() {
        updateSetScore(player1Name);
    }

    public void player2WinsPoint() {
        updateSetScore(player2Name);
    }

    private void updateSetScore(String playerName) {
        setScore.put(playerName, setScore.get(playerName) + 1);
        if (isTieBreak()) {
            startTieBreak();
        }
    }

    public Map<String, Integer> getSetScore() {
        return new HashMap<>(setScore);
    }

    private boolean isTieBreak() {
        return setScore.get(player1Name) == 6 && setScore.get(player2Name) == 6;
    }

    private void startTieBreak() {
        this.tieBreakScore = new TieBreakScore(player1Name, player2Name);
        tieBreakScore.initializeTieBreakScore();
    }

    public boolean isSetFinished() {
        if (((Math.abs(setScore.get(player1Name) - setScore.get(player2Name))) >= 2)
                && setScore.get(player1Name) == 7 || setScore.get(player2Name) == 7) {
            return true;
        } else {
            return false;
        }
    }
}
