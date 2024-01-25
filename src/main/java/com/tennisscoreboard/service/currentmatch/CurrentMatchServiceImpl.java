package com.tennisscoreboard.service.currentmatch;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.dao.MatchDao;

import java.util.*;

public class CurrentMatchServiceImpl implements CurrentMatchService {

    private final Map<UUID, Match> currentMatches = new HashMap<>();
    private final MatchDao matchDao;

    public CurrentMatchServiceImpl(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    @Override
    public void startNewMatch(Player player1, Player player2) {
        Match match = new Match(player1, player2);
        UUID matchId = UUID.randomUUID();
        currentMatches.put(matchId, match);
    }

    @Override
    public void removeMatch(UUID matchId) {
        currentMatches.remove(matchId);
    }

    @Override
    public List<Match> getAllCurrentMatches() {
        return new ArrayList<>(currentMatches.values());
    }

    @Override
    public void saveFinishedMatch(Match match) {
        matchDao.createMatch(match);
    }
}

