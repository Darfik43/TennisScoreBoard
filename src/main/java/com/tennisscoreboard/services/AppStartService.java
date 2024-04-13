package com.tennisscoreboard.services;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.services.DAOs.MatchDao;
import com.tennisscoreboard.services.DAOs.PlayerDao;

import java.util.UUID;

public class AppStartService {

    private final String[] playersNames = {
            "Rinky Hijikata", "Christopher Eubanks", "Duje Ajdukovic", "Brandon Nakashima", "Jaime Faria",
            "D. Jorda Sanchis", "Dominik Koepfer", "Pablo Llamas Ruiz", "Carlos Alcaraz", " Daniil Medvedev"
    };
    private final Player[] players = new Player[playersNames.length];
    private final MatchDao matchDao = MatchDao.getInstance();
    private final PlayerDao playerDao = PlayerDao.getInstance();
    private Player player;
    private Match match;

    public void createStartPlayers() {
        for (int i = 0; i < playersNames.length; i++) {
            player = new Player(playersNames[i]);
            players[i] = player;
            playerDao.create(player);
        }
    }

    public void createStartMatches() {
        for (int i = 0; i <= playersNames.length - 2; i += 2) {
            match = new Match(UUID.randomUUID(), players[i], players[i + 1], players[i]);
            matchDao.create(match);
        }
    }
}
