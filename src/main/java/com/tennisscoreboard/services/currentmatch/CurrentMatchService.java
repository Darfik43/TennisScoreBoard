package com.tennisscoreboard.services.currentmatch;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;

import java.util.List;
import java.util.UUID;

public interface CurrentMatchService {
    void startNewMatch(Player player1, Player player2, UUID uuid);

    void endMatch(UUID matchId);

    void saveFinishedMatch(Match match);
}
