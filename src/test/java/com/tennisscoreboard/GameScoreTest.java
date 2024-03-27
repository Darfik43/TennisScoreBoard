package com.tennisscoreboard;

import com.tennisscoreboard.service.scorecalculation.GameScore;
import com.tennisscoreboard.service.scorecalculation.TennisPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameScoreTest {
    private GameScore gameScore;

    @BeforeEach
    void setUp() {
        gameScore = new GameScore("Player1", "Player2");
    }

    @Test
    void startNewGame() {
        gameScore.startNew();
        Map<String, TennisPoint> score = gameScore.getScore();
        assertEquals(TennisPoint.LOVE, score.get("Player1"));
        assertEquals(TennisPoint.LOVE, score.get("Player2"));
        assertFalse(gameScore.isFinished);
    }

    @Test
    void updateScoreLoveToFifteen() {
        gameScore.startNew();

        gameScore.updateScore("Player1");
        Map<String, TennisPoint> score = gameScore.getScore();
        assertEquals(TennisPoint.FIFTEEN, score.get("Player1"));
        assertFalse(gameScore.isFinished);
    }

    @Test
    void updateScoreFortyToAdvantage() {
        gameScore.startNew();

        gameScore.updateScore("Player1");
        gameScore.updateScore("Player1");
        gameScore.updateScore("Player1");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player1");

        Map<String, TennisPoint> score = gameScore.getScore();
        assertEquals(TennisPoint.ADVANTAGE, score.get("Player1"));
        assertFalse(gameScore.isFinished);
    }

    @Test
    void updateScorePlayer1Wins() {
        gameScore.startNew();

        gameScore.updateScore("Player1");
        gameScore.updateScore("Player1");
        gameScore.updateScore("Player1");
        gameScore.updateScore("Player1");

        Map<String, TennisPoint> score = gameScore.getScore();
        assertTrue(gameScore.isFinished);
    }

    @Test
    void isFinishedAfterPlayer2WinsAdvantage() {
        gameScore.startNew();

        gameScore.updateScore("Player1");
        gameScore.updateScore("Player1");
        gameScore.updateScore("Player1");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player2");

        assertTrue(gameScore.isFinished);
    }

    @Test
    void swapAdvantagePoints() {
        gameScore.startNew();

        gameScore.updateScore("Player1");
        gameScore.updateScore("Player1");
        gameScore.updateScore("Player1");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player2");
        gameScore.updateScore("Player1");
        gameScore.updateScore("Player2");

        Map<String, TennisPoint> score = gameScore.getScore();
        assertEquals(TennisPoint.ADVANTAGE, score.get("Player2"));
        assertEquals(TennisPoint.FORTY, score.get("Player1"));
    }

}
