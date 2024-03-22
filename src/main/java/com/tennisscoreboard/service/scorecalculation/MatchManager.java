package com.tennisscoreboard.service.scorecalculation;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchManager {

    CurrentMatchServiceImpl currentMatchService = CurrentMatchServiceImpl.getInstance();
    private final Score score;
    @Getter
    private final String player1Name;
    @Getter
    private final String player2Name;

    public MatchManager(UUID uuid) {
        this.player1Name = currentMatchService.getCurrentMatch(uuid).getPlayer1().getName();
        this.player2Name = currentMatchService.getCurrentMatch(uuid).getPlayer2().getName();
        this.score = new Score(player1Name, player2Name);
    }

    public static MatchManager getInstance(UUID uuid) {
        return new MatchManager(uuid);
    }

    public void initNewMatch() {
        score.initialize();
    }

    public void playerWonPoint(String playerName) {
        score.updateScore(playerName);
    }

    public Map<String, Integer> getMatchScore() {
        return score.getScore();
    }

    public Map<String, Integer> getSetScore() {
        return score.getSetScore();
    }

    public Map<String, TennisPoint> getGameScore() {
        return score.getGameScore();
    }
}
