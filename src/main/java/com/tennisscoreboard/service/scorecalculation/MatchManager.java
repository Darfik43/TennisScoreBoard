package com.tennisscoreboard.service.scorecalculation;

import com.tennisscoreboard.model.Match;
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

    private final UUID uuid;

    public MatchManager(UUID uuid) {
        this.player1Name = currentMatchService.getCurrentMatch(uuid).getPlayer1().getName();
        this.player2Name = currentMatchService.getCurrentMatch(uuid).getPlayer2().getName();
        this.scoreController = new ScoreController(player1Name, player2Name);
        this.uuid = uuid;
    }

    public static MatchManager getInstance(UUID uuid) {
        return new MatchManager(uuid);
    }

    public void initNewMatch() {
        scoreController.startNew();
    }

    public void playerWonPointAndCheckFinishedMatch(String playerName) {
        scoreController.updateScore(playerName);

        if (scoreController.isFinished) {
            setWinner(playerName);
            currentMatchService.saveFinishedMatch(currentMatchService.getCurrentMatch(uuid));
            currentMatchService.endMatch(uuid);
        }
    }

    public Integer getPlayerMatchScore(String playerName) {
        return scoreController.getPlayerMatchScore(playerName);
    }

    public Integer getPlayerSetScore(String playerName) {
        return scoreController.getPlayerSetScore(playerName);
    }

    public Integer getPlayerGameScore(String playerName) {
        return scoreController.getPlayerGameScore(playerName);
    }

    public Integer getPlayerTieBreakScore(String playerName) {
        return scoreController.getPlayerTieBreakScore(playerName);
    }

    private void setWinner(String playerName) {
        if (playerName.equals(player1Name)) {
            currentMatchService.getCurrentMatch(uuid).
                    setWinner(currentMatchService.getCurrentMatch(uuid).getPlayer1());
        } else {
            currentMatchService.getCurrentMatch(uuid).
                    setWinner(currentMatchService.getCurrentMatch(uuid).getPlayer2());
        }
    }

    public boolean checkIsFinished() {
        return scoreController.isFinished;
    }
}
