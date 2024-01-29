package com.tennisscoreboard.service.scorecalculation;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;

import java.util.HashMap;
import java.util.Map;

public class Score implements ScoreCounter {
    private final SetScore setScore;
    private final Map<String, Integer> matchScore;
    private boolean isFinished;
    private final String player1Name;
    private final String player2Name;

    public Score(Match match) {
        this.matchScore = new HashMap<>();
        this.player1Name = match.getPlayer1().getName();
        this.player2Name = match.getPlayer2().getName();
        this.setScore = new SetScore(player1Name, player2Name);
        this.isFinished = false;
    }

    @Override
    public void initialize() {
        matchScore.put(player1Name, 0);
        matchScore.put(player2Name, 0);
        setScore.initialize();
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
        isFinished = matchScore.get(player1Name) == 2 || matchScore.get(player2Name) == 2;
    }

    public Map<String, Integer> getScore() {
        return new HashMap<>(matchScore);
    }

    public void updateScore(String playerName) {
        if (setScore.isFinished()) {
            matchScore.put(playerName, matchScore.get(playerName) + 1);
            updateFinishedStatus();
        }
    }
}
