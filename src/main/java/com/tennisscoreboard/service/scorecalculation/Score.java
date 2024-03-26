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
    private final TieBreakScore tieBreakScore;
    private boolean isFinished;
    private boolean isTieBreak;
    private final String player1Name;
    private final String player2Name;

    public Score(String player1Name, String player2Name) {
        this.matchScore = new HashMap<>();
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.setScore = new SetScore(player1Name, player2Name);
        this.tieBreakScore = new TieBreakScore(player1Name, player2Name);
        this.gameScore = new GameScore(player1Name, player2Name);
        this.isFinished = false;
    }

    @Override
    public void initialize() {
        matchScore.put(player1Name, 0);
        matchScore.put(player2Name, 0);
        setScore.initialize();
        tieBreakScore.initialize();
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
        return matchScore;
    }

    public Map<String, Integer> getSetScore() {
        return setScore.getScore();
    }

    public Map<String, TennisPoint> getGameScore() {
        return gameScore.getGameScore();
    }

    public Map<String, Integer> getTieBreakScore() {
        return tieBreakScore.getTieBreakScore();
    }


    public void updateScore(String playerName) {

        if (!isTieBreak) {
            gameScore.updateScore(playerName);
            if (isGameFinished() && !isTieBreak) {
                setScore.updateScore(playerName);
                gameScore.initialize();
                isTieBreak();
                if (isSetFinished()) {
                    matchScore.put(playerName, matchScore.get(playerName) + 1);
                    updateFinishedStatus();
                    tieBreakScore.initialize();
                    setScore.initialize();
                }
            }
        } else {
            tieBreakScore.updateScore(playerName);
            if (tieBreakScore.isTieBreakFinished()) {
                matchScore.put(playerName, matchScore.get(playerName) + 1);
                tieBreakScore.initialize();
                setScore.initialize();
                isTieBreak();
            }
        }
    }


    public boolean isSetFinished() {
        return setScore.isFinished();
    }

    public boolean isGameFinished() {
        return gameScore.isFinished();
    }

    private void isTieBreak() {
        isTieBreak = setScore.getScore().get(player1Name) == 6
                && setScore.getScore().get(player2Name) == 6;
    }
}
