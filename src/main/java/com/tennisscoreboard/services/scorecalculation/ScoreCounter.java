package com.tennisscoreboard.services.scorecalculation;

public interface  ScoreCounter {
    void startNew();
    void updateScore(String playerName);
}
