package com.tennisscoreboard.service.dao;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;

public interface MatchDaoInterface {

    Match getMatchById(int id);
    void createMatch(Match match);
    void updateMatchScore(int matchId, int winningPlayerId);

}
