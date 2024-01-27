package com.tennisscoreboard.service.scorecalculation;

import java.util.HashMap;
import java.util.Map;

public class GameScore {
    private final Map<String, TennisPoint> gameScore;
    private final String player1Name;
    private final String player2Name;

    protected GameScore(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.gameScore = new HashMap<>();
    }

    public void initializeGameScore() {
        gameScore.put(player1Name, TennisPoint.LOVE);
        gameScore.put(player2Name, TennisPoint.LOVE);
    }

    public void player1WinsPoint() {
        updateGameScore(player1Name);
    }

    public void player2WinsPoint() {
        updateGameScore(player2Name);
    }

    private void updateGameScore(String playerName) {
        TennisPoint currentScore = gameScore.get(playerName);

        if (currentScore == TennisPoint.LOVE) {
            gameScore.put(playerName, TennisPoint.FIFTEEN);
        } else if (currentScore == TennisPoint.FIFTEEN) {
            gameScore.put(playerName, TennisPoint.THIRTY);
        } else if (currentScore == TennisPoint.THIRTY) {
            gameScore.put(playerName, TennisPoint.FORTY);
        } else if (currentScore == TennisPoint.FORTY) {
            handleAdvantage(playerName);
        }
    }

    private void handleAdvantage(String playerName) {
        TennisPoint opponentScore = gameScore.get(getOpponentName(playerName));

        if (opponentScore == TennisPoint.FORTY) {
            gameScore.put(playerName, TennisPoint.FORTY);
            gameScore.put(getOpponentName(playerName), TennisPoint.FORTY);
        } else if (opponentScore == TennisPoint.ADVANTAGE) {
            gameScore.put(playerName, TennisPoint.FORTY);
            gameScore.put(getOpponentName(playerName), TennisPoint.FORTY);
        } else {
            gameScore.put(playerName, TennisPoint.LOVE);
            gameScore.put(getOpponentName(playerName), TennisPoint.LOVE);
        }
    }

    private String getOpponentName(String playerName) {
        return playerName.equals(player1Name) ? player2Name : player1Name;
    }

    public Map<String, TennisPoint> getGameScore() {
        return new HashMap<>(gameScore);
    }

    public boolean isGameFinished() {
        TennisPoint player1GameScore = gameScore.get(player1Name);
        TennisPoint player2GameScore = gameScore.get(player2Name);

        return (player1GameScore.ordinal() >= TennisPoint.FORTY.ordinal() && player2GameScore.ordinal() < TennisPoint.FORTY.ordinal()) ||
                (player2GameScore.ordinal() >= TennisPoint.FORTY.ordinal() && player1GameScore.ordinal() < TennisPoint.FORTY.ordinal()) ||
                (player1GameScore == TennisPoint.ADVANTAGE || player2GameScore == TennisPoint.ADVANTAGE);
    }
}




