package com.tennisscoreboard.service.currentmatch;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.PlayersService;
import com.tennisscoreboard.service.dao.MatchDao;
import com.tennisscoreboard.service.dao.PlayerDao;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchServiceImpl implements CurrentMatchService {

    private static CurrentMatchServiceImpl currentMatchService;
    private static final Map<UUID, Match> currentMatches = new ConcurrentHashMap<>();
    private final MatchDao matchDao = MatchDao.getInstance();
    private final PlayerDao playerDao = PlayerDao.getInstance();
    private final PlayersService playersService = PlayersService.getInstance();

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
        Player player1 = match.getPlayer1();
        Player player2 = match.getPlayer2();

        Optional<Player> existingPlayer1 = playerDao.getPlayerByName(player1.getName());
        Optional<Player> existingPlayer2 = playerDao.getPlayerByName(player2.getName());

        Player newPlayer1 = existingPlayer1.orElseGet(() -> {
            Player p1 = playersService.createPlayer(player1.getName());
            playerDao.create(p1);
            return p1;
        });

        Player newPlayer2 = existingPlayer2.orElseGet(() -> {
            Player p2 = playersService.createPlayer(player2.getName());
            playerDao.create(p2);
            return p2;
        });

        match.setPlayer1(newPlayer1);
        match.setPlayer2(newPlayer2);

        if (match.getWinner().getName().equals(newPlayer1.getName())) {
            match.setWinner(newPlayer1);
        } else {
            match.setWinner(newPlayer2);
        }

        matchDao.create(match);
    }
}

