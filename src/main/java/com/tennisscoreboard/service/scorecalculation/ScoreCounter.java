package com.tennisscoreboard.service.scorecalculation;

public interface  ScoreCounter {
    void startNew();
    void updateScore(String playerName);
}
