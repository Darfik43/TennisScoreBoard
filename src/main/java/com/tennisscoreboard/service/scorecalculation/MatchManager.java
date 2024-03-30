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

    private void setWinner(String playerName) {
        if (playerName.equals(player1Name)) {
            currentMatchService.getCurrentMatch(uuid).
                    setWinner(currentMatchService.getCurrentMatch(uuid).getPlayer1());
        } else {
            currentMatchService.getCurrentMatch(uuid).
                    setWinner(currentMatchService.getCurrentMatch(uuid).getPlayer2());
        }
    }
}
