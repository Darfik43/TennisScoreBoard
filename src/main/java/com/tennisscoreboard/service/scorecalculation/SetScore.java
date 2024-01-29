package com.tennisscoreboard.service.scorecalculation;

import java.util.HashMap;
import java.util.Map;

public class SetScore implements ScoreCounter {
    private final Map<String, Integer> setScore;
    private GameScore gameScore;
    private final String player1Name;
    private final String player2Name;
    private TieBreakScore tieBreakScore;
    private boolean isFinished;

    public SetScore(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.setScore = new HashMap<>();
        this.gameScore = new GameScore(player1Name, player2Name);
        this.isFinished = false;
    }


    @Override
    public void initialize() {
        setScore.put(player1Name, 0);
        setScore.put(player2Name, 0);
        gameScore.initialize();
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
                && setScore.get(player1Name) == 7 || setScore.get(player2Name) == 7;
    }

    public Map<String, Integer> getScore() {
        return new HashMap<>(setScore);
    }

    public void updateScore(String playerName) {
        if (isTieBreak()) {
            tieBreakScore.initialize();
        }
        if (gameScore.isFinished() && !isFinished) {
            setScore.put(playerName, setScore.get(playerName) + 1);
            gameScore.initialize();
        }
        updateFinishedStatus();
    }


    private boolean isTieBreak() {
        return setScore.get(player1Name) == 6 && setScore.get(player2Name) == 6;
    }

    private void startTieBreak() {
        this.tieBreakScore = new TieBreakScore(player1Name, player2Name);
        tieBreakScore.initialize();
    }
}
