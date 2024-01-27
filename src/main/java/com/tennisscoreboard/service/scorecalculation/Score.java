package com.tennisscoreboard.service.scorecalculation;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private final SetScore setScore;
    private final Map<String, Integer> matchScore;
    private Match match;

    private final String player1Name;
    private final String player2Name;

    public Score(Match match) {
        this.matchScore = new HashMap<>();
        this.player1Name = match.getPlayer1().getName();
        this.player2Name = match.getPlayer2().getName();
        this.setScore = new SetScore(player1Name, player2Name);
    }

    public void initializeMatchScore() {
        matchScore.put(player1Name, 0);
        matchScore.put(player2Name, 0);
        setScore.initializeSetScore();
    }

    public void player1WinsPoint() {
        updateMatchScore(player1Name);
    }

    public void player2WinsPoint() {
        updateMatchScore(player2Name);
    }

    private void updateMatchScore(String playerName) {
        matchScore.put(playerName, matchScore.get(playerName) + 1);
    }

    public Map<String, Integer> getMatchScore() {
        return new HashMap<>(matchScore);
    }

    public boolean isMatchFinished() {
        return matchScore.get(player1Name) == 2 || matchScore.get(player2Name) == 2;
    }
}
