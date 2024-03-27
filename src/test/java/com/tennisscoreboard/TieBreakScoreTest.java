package com.tennisscoreboard;

import com.tennisscoreboard.service.scorecalculation.TieBreakScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TieBreakScoreTest {

    private TieBreakScore tieBreakScore;

    @BeforeEach
    void setUp() {
        tieBreakScore = new TieBreakScore("Player1", "Player2");
    }

    @Test
    void startNewTieBreak() {
        tieBreakScore.startNew();

        Map<String, Integer> score = tieBreakScore.getTieBreakScore();
        assertEquals(0, (int) score.get("Player1"));
        assertEquals(0, (int) score.get("Player2"));
        assertFalse(tieBreakScore.isFinished);
    }

    @Test
    void updateScoreRegular() {
        tieBreakScore.startNew();

        tieBreakScore.updateScore("Player1");

        Map<String, Integer> score = tieBreakScore.getTieBreakScore();
        assertEquals(1, (int) score.get("Player1"));
        assertEquals(0, (int) score.get("Player2"));
        assertFalse(tieBreakScore.isFinished);
    }

    @Test
    void updateScorePlayer1Wins() {
        tieBreakScore.startNew();

        for (int i = 0; i < 7; i++) {
            tieBreakScore.updateScore("Player1");
        }

        Map<String, Integer> score = tieBreakScore.getTieBreakScore();
        assertEquals(7, (int) score.get("Player1"));
        assertTrue(tieBreakScore.isFinished);
    }

    @Test
    void isNotFinishedWhile2PointsAdvantage() {
        tieBreakScore.startNew();

        for (int i = 0; i < 6; i++) {
            tieBreakScore.updateScore("Player1");
            tieBreakScore.updateScore("Player2");
        }

        Map<String, Integer> score = tieBreakScore.getTieBreakScore();
        assertEquals(6, (int) score.get("Player1"));
        assertEquals(6, (int) score.get("Player2"));
        assertFalse(tieBreakScore.isFinished);
    }

    @Test
    void isFinishedPlayerWins2PointsAdvantage() {
        tieBreakScore.startNew();

        for (int i = 0; i < 6; i++) {
            tieBreakScore.updateScore("Player1");
            tieBreakScore.updateScore("Player2");
        }
        tieBreakScore.updateScore("Player2");
        tieBreakScore.updateScore("Player2");

        Map<String, Integer> score = tieBreakScore.getTieBreakScore();
        assertEquals(8, (int) score.get("Player2"));
        assertEquals(6, (int) score.get("Player1"));
        assertTrue(tieBreakScore.isFinished);
    }
}
