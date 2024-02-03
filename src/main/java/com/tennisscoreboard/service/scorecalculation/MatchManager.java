package com.tennisscoreboard.service.scorecalculation;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;

import java.util.UUID;

public class MatchManager {

    CurrentMatchServiceImpl currentMatchService = CurrentMatchServiceImpl.getInstance();
    private final Score score;
    private final String player1Name;
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

    public void getScore() {
        //TODO
    }
}
