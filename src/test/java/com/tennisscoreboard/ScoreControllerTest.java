package com.tennisscoreboard;

import com.tennisscoreboard.services.scorecalculation.ScoreController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreControllerTest {

    private ScoreController matchScoreController;

    @BeforeEach
    void setUp() {
        matchScoreController = new ScoreController("Player1", "Player2");
    }

    @Test
    void startNew() {
        matchScoreController.startNew();

        Map<String, Integer> score = matchScoreController.getScore();
        assertEquals(0, (int) score.get("Player1"));
        assertEquals(0, (int) score.get("Player2"));
        assertFalse(matchScoreController.isFinished);
    }

    @Test
    void updateScoreRegular() {
        matchScoreController.startNew();
        Map<String, Integer> score = matchScoreController.getScore();

        while (score.get("Player1") != 1) {
            matchScoreController.updateScore("Player1");
        }

        assertEquals(1, (int) score.get("Player1"));
        assertEquals(0, (int) score.get("Player2"));
        assertFalse(matchScoreController.isFinished);
    }

    @Test
    void checkIsMatchFinished() {
        matchScoreController.startNew();
        Map<String, Integer> score = matchScoreController.getScore();

        while (score.get("Player2") != 2) {
            matchScoreController.updateScore("Player2");
        }

        assertEquals(2, (int) score.get("Player2"));
        assertEquals(0, (int) score.get("Player1"));
        assertTrue(matchScoreController.isFinished);
    }

    @Test
    void checkMatchIsNotFinishedIfDraw() {
        matchScoreController.startNew();
        Map<String, Integer> score = matchScoreController.getScore();

        while (score.get("Player2") != 1) {
            matchScoreController.updateScore("Player2");
        }
        while (score.get("Player1") != 1) {
            matchScoreController.updateScore("Player1");
        }

        assertEquals(1, (int) score.get("Player2"));
        assertEquals(1, (int) score.get("Player1"));
        assertFalse(matchScoreController.isFinished);

    }
}
