package com.tennisscoreboard.service.currentmatch;

import com.tennisscoreboard.model.Match;

import java.util.List;

public interface CurrentMatchService {
    void addMatch(Match match);
    void removeMatch(int matchId);
    List<Match> getAllCurrentMatches();
}
