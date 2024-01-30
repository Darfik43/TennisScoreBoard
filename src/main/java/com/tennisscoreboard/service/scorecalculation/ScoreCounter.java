package com.tennisscoreboard.service.scorecalculation;

import java.util.Map;
import java.util.Optional;

public interface  ScoreCounter {
    void initialize();
    void player1WinsPoint();
    void player2WinsPoint();
    boolean isFinished();
    void updateScore(String playerName);
}
