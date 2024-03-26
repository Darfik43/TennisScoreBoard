package com.tennisscoreboard.service.scorecalculation;

import java.util.HashMap;
import java.util.Map;


    //GameScore - Score within a game
public class GameScore implements ScoreCounter {
    private final Map<String, TennisPoint> gameScore;
    private final String player1Name;
    private final String player2Name;
    private boolean isFinished;

    public GameScore(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.gameScore = new HashMap<>();
        this.isFinished = false;
    }


    @Override
    public void startNew() {
        gameScore.put(player1Name, TennisPoint.LOVE);
        gameScore.put(player2Name, TennisPoint.LOVE);
        this.isFinished = false;
    }

    public void player1WinsPoint() {
        updateScore(player1Name);
    }

    public void player2WinsPoint() {
        updateScore(player2Name);
    }

    @Override
    public boolean getIsFinished() {
        return isFinished;
    }

    private void updateFinishedStatus(String playerName) {
        TennisPoint playerGameScore = gameScore.get(playerName);
        TennisPoint opponentGameScore = gameScore.get(getOpponentName(playerName));
        isFinished = (playerGameScore.ordinal() >= TennisPoint.FORTY.ordinal() && opponentGameScore.ordinal() < TennisPoint.FORTY.ordinal()) ||
                (playerGameScore == TennisPoint.ADVANTAGE && opponentGameScore == TennisPoint.FORTY);
    }

    public Map<String, TennisPoint> getScore() {
        return new HashMap<>(gameScore);
    }

    public void updateScore(String playerName) {
            TennisPoint currentScore = gameScore.get(playerName);
            updateFinishedStatus(playerName);

            if (currentScore == TennisPoint.LOVE) {
                gameScore.put(playerName, TennisPoint.FIFTEEN);
            } else if (currentScore == TennisPoint.FIFTEEN) {
                gameScore.put(playerName, TennisPoint.THIRTY);
            } else if (currentScore == TennisPoint.THIRTY) {
                gameScore.put(playerName, TennisPoint.FORTY);
            } else if (currentScore == TennisPoint.FORTY || currentScore == TennisPoint.ADVANTAGE) {
                handleAdvantage(playerName);
            }

    }

    private void handleAdvantage(String playerName) {
        TennisPoint opponentScore = gameScore.get(getOpponentName(playerName));

        if (opponentScore == TennisPoint.FORTY) {
            gameScore.put(playerName, TennisPoint.ADVANTAGE);
        } else if (opponentScore == TennisPoint.ADVANTAGE) {
            gameScore.put(playerName, TennisPoint.ADVANTAGE);
            gameScore.put(getOpponentName(playerName), TennisPoint.FORTY);
        }


    }

    private String getOpponentName(String playerName) {
        return playerName.equals(player1Name) ? player2Name : player1Name;
    }

    public Map<String, TennisPoint> getGameScore() {
        return gameScore;
    }
}




