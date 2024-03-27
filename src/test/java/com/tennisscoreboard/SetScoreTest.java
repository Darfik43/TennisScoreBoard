package com.tennisscoreboard;

import com.tennisscoreboard.service.scorecalculation.SetScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SetScoreTest {

    private SetScore setScore;

    @BeforeEach
    void setUp() {
        setScore = new SetScore("Player1", "Player2");
    }

    @Test
    void startNewSet() {
        setScore.startNew();

        Map<String, Integer> score = setScore.getScore();
        assertEquals(0, (int) score.get("Player1"));
        assertEquals(0, (int) score.get("Player2"));
        assertFalse(setScore.isFinished);
        assertFalse(setScore.isTieBreak);
    }

    @Test
    void updateScoreRegular() {
        setScore.startNew();

        setScore.updateScore("Player1");
        Map<String, Integer> score = setScore.getScore();
        assertEquals(1, (int) score.get("Player1"));
        assertEquals(0, (int) score.get("Player2"));
        assertFalse(setScore.isFinished);
        assertFalse(setScore.isTieBreak);
    }

    @Test
    void updateScoreIsTieBreak() {
        setScore.startNew();
        for (int i = 0; i < 6; i++) {
            setScore.updateScore("Player1");
            setScore.updateScore("Player2");
        }
        assertTrue(setScore.isTieBreak);
        assertFalse(setScore.isFinished);
    }

    @Test
    void updateScoreSetWon() {
        setScore.startNew();

        for (int i = 0; i < 7; i++) {
            setScore.updateScore("Player1");
        }

        Map<String, Integer> score = setScore.getScore();
        assertTrue(setScore.isFinished);
        assertEquals(7, (int) score.get("Player1"));
    }
}
