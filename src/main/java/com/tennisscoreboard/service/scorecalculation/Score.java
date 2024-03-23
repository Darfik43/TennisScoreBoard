package com.tennisscoreboard.service.scorecalculation;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;

import java.util.HashMap;
import java.util.Map;

//Score(MatchScore) - Score of sets within a Match
public class Score implements ScoreCounter {
    private final SetScore setScore;
    private final GameScore gameScore;
    private final Map<String, Integer> matchScore;
    private boolean isFinished;
    private final String player1Name;
    private final String player2Name;

    public Score(String player1Name, String player2Name) {
        this.matchScore = new HashMap<>();
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.setScore = new SetScore(player1Name, player2Name);
        this.gameScore = new GameScore(player1Name, player2Name);
        this.isFinished = false;
    }

    @Override
    public void initialize() {
        matchScore.put(player1Name, 0);
        matchScore.put(player2Name, 0);
        setScore.initialize();
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
        isFinished = matchScore.get(player1Name) == 2 || matchScore.get(player2Name) == 2;
    }

    public Map<String, Integer> getScore() {
        //return matchScore;
        return matchScore;
    }

    public Map<String, Integer> getSetScore() {
        return setScore.getScore();
    }

    public Map<String, TennisPoint> getGameScore() {
        return gameScore.getGameScore();
    }


    public void updateScore(String playerName) {
//TODO Handle one of the players won

        if (!isFinished) {
            if (isGameFinished()) {
                setScore.updateScore(playerName);
                gameScore.initialize();
                if (isSetFinished()) {
                    matchScore.put(playerName, matchScore.get(playerName) + 1);
                    updateFinishedStatus();
                    setScore.initialize();
                }
            } else {
                gameScore.updateScore(playerName);
            }
        }
    }

    public boolean isSetFinished() {
        return setScore.isFinished();
    }

    public boolean isGameFinished() {
        return gameScore.isFinished();
    }
}
