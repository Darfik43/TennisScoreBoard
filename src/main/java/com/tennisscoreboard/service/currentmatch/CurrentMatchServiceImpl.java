package com.tennisscoreboard.service.currentmatch;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.dao.MatchDao;
import com.tennisscoreboard.service.dao.PlayerDao;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchServiceImpl implements CurrentMatchService {

    private static CurrentMatchServiceImpl currentMatchService;
    private static final Map<UUID, Match> currentMatches = new ConcurrentHashMap<>();
    private final MatchDao matchDao = MatchDao.getInstance();
    private final PlayerDao playerDao = PlayerDao.getInstance();

    public static CurrentMatchServiceImpl getInstance() {

        if (currentMatchService == null) {
            currentMatchService = new CurrentMatchServiceImpl();
        }

        return currentMatchService;
    }


    @Override
    public void startNewMatch(Player player1, Player player2, UUID uuid) {
        Match match = new Match(uuid, player1, player2);
        currentMatches.put(uuid, match);
    }

    @Override
    public void endMatch(UUID matchId) {
        currentMatches.remove(matchId);
    }

    @Override
    public List<Match> getAllCurrentMatches() {
        return new ArrayList<>(currentMatches.values());
    }

    public Match getCurrentMatch(UUID uuid) {
        return currentMatches.get(uuid);
    }

    @Override
    public void saveFinishedMatch(Match match) {
        playerDao.create(match.getPlayer1());
        playerDao.create(match.getPlayer2());
        matchDao.create(match);
    }
}

