package com.tennisscoreboard.service.scorecalculation;

import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

public class MatchManager {

    CurrentMatchServiceImpl currentMatchService = CurrentMatchServiceImpl.getInstance();
    private final ScoreController scoreController;
    @Getter
    private final String player1Name;
    @Getter
    private final String player2Name;

    public MatchManager(UUID uuid) {
        this.player1Name = currentMatchService.getCurrentMatch(uuid).getPlayer1().getName();
        this.player2Name = currentMatchService.getCurrentMatch(uuid).getPlayer2().getName();
        this.scoreController = new ScoreController(player1Name, player2Name);
    }

    public static MatchManager getInstance(UUID uuid) {
        return new MatchManager(uuid);
    }

    public void initNewMatch() {
        scoreController.startNew();
    }

    public void playerWonPoint(String playerName) {
        scoreController.updateScore(playerName);
    }

    public Map<String, Integer> getMatchScore() {
        return scoreController.getScore();
    }

    public Map<String, Integer> getSetScore() {
        return scoreController.getSetScore();
    }

    public Map<String, TennisPoint> getGameScore() {
        return scoreController.getGameScore();
    }
    public Map<String, Integer> getTieBreakScore() {
        return scoreController.getTieBreakScore();
    }
}
