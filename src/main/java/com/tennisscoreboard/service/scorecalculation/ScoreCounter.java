package com.tennisscoreboard.service.scorecalculation;

public interface  ScoreCounter {
    void startNew();
    void player1WinsPoint();
    void player2WinsPoint();
    boolean getIsFinished();
    void updateScore(String playerName);
}
