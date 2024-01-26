package com.tennisscoreboard.service.scorecalculation;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;

import java.util.HashMap;

public class Score {
    private HashMap<String, Integer> matchScore;
    private Match match;
    private final String player1Name;
    private final String player2Name;

    public Score(Match match) {
        SetScore setScore = new SetScore();
        this.player1Name = match.getPlayer1().getName();
        this.player2Name = match.getPlayer2().getName();
    }

    public void createMatchScore() {
        matchScore.put(match.getPlayer1().getName(), 0);
        matchScore.put(match.getPlayer2().getName(), 0);
    }
    public void player1WinsPoint() {
        matchScore.put(player1Name, matchScore.get(player1Name + 1));
    }

    public void player2WinsPoint() {
        matchScore.put(player2Name, matchScore.get(player2Name + 1));
    }

    public boolean isMatchFinished() {
        return matchScore.get(player1Name) == 2 || matchScore.get(player2Name) == 2;
    }
}
