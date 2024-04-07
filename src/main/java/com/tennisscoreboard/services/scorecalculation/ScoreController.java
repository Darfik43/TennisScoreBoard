package com.tennisscoreboard.services.scorecalculation;

import java.util.HashMap;
import java.util.Map;

public class ScoreController implements ScoreCounter {
    private final SetScore setScore;
    private final GameScore gameScore;
    private final Map<String, Integer> matchScore;
    private final TieBreakScore tieBreakScore;
    public boolean isFinished;
    private final String player1Name;
    private final String player2Name;

    public ScoreController(String player1Name, String player2Name) {
        this.matchScore = new HashMap<>();
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.setScore = new SetScore(player1Name, player2Name);
        this.tieBreakScore = new TieBreakScore(player1Name, player2Name);
        this.gameScore = new GameScore(player1Name, player2Name);
        this.isFinished = false;
    }

    @Override
    public void startNew() {
        matchScore.put(player1Name, 0);
        matchScore.put(player2Name, 0);
        setScore.startNew();
        tieBreakScore.startNew();
        gameScore.startNew();
        isFinished = false;
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

    public Integer getPlayerTieBreakScore(String playerName) {
        return tieBreakScore.getPlayerTieBreakScore(playerName);
    }

    public Integer getPlayerGameScore(String playerName) {
        return gameScore.getPlayerGameScore(playerName);
    }

    public Integer getPlayerSetScore(String playerName) {
        return setScore.getPlayerSetScore(playerName);
    }

    public Integer getPlayerMatchScore(String playerName) {
        return matchScore.get(playerName);
    }

    private void updateMatchScoreAndResetSet(String playerName) {
        matchScore.put(playerName, matchScore.get(playerName) + 1);
        setScore.startNew();
        updateFinishedStatus();
    }


    public void updateScore(String playerName) {
        if (!setScore.isTieBreak) {
            updateGameScore(playerName);
        } else {
            updateTieBreakScore(playerName);
        }
    }

    private void updateGameScore(String playerName) {
        gameScore.updateScore(playerName);
        if (gameScore.isFinished) {
            endGameAndUpdateMatch(playerName);
        }
    }

    private void endGameAndUpdateMatch(String playerName) {
        setScore.updateScore(playerName);
        gameScore.startNew();

        if (setScore.isFinished) {
            endSetAndUpdateMatch(playerName);
        }
    }

    private void updateTieBreakScore(String playerName) {
        tieBreakScore.updateScore(playerName);
        if (tieBreakScore.isFinished) {
            endSetAndUpdateMatch(playerName);
        }
    }

    private void endSetAndUpdateMatch(String playerName) {
        updateMatchScoreAndResetSet(playerName);
        tieBreakScore.startNew();
    }
}
