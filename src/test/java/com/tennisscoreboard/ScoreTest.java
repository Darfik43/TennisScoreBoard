package com.tennisscoreboard;

import com.tennisscoreboard.service.scorecalculation.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {

    private Score matchScore;

    @BeforeEach
    void setUp() {
        matchScore = new Score("Player1", "Player2");
    }

    @Test
    void startNew() {
        matchScore.startNew();

        Map<String, Integer> score = matchScore.getScore();
        assertEquals(0, (int) score.get("Player1"));
        assertEquals(0, (int) score.get("Player2"));
        assertFalse(matchScore.isFinished);
    }

    @Test
    void updateScoreRegular() {
        matchScore.startNew();
        Map<String, Integer> score = matchScore.getScore();

        while (score.get("Player1") != 1) {
            matchScore.updateScore("Player1");
        }

        assertEquals(1, (int) score.get("Player1"));
        assertEquals(0, (int) score.get("Player2"));
        assertFalse(matchScore.isFinished);
    }

    @Test
    void checkIsMatchFinished() {
        matchScore.startNew();
        Map<String, Integer> score = matchScore.getScore();

        while (score.get("Player2") != 2) {
            matchScore.updateScore("Player2");
        }

        assertEquals(2, (int) score.get("Player2"));
        assertEquals(0, (int) score.get("Player1"));
        assertTrue(matchScore.isFinished);
    }

    @Test
    void checkMatchIsNotFinishedIfDraw() {
        matchScore.startNew();
        Map<String, Integer> score = matchScore.getScore();

        while (score.get("Player2") != 1) {
            matchScore.updateScore("Player2");
        }
        while (score.get("Player1") != 1) {
            matchScore.updateScore("Player1");
        }

        assertEquals(1, (int) score.get("Player2"));
        assertEquals(1, (int) score.get("Player1"));
        assertFalse(matchScore.isFinished);

    }
}
