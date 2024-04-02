package com.tennisscoreboard;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.dao.MatchDao;
import com.tennisscoreboard.service.dao.PlayerDao;

import java.util.UUID;

public class AppStartService {

    String[] playersNames = {
            "Rinky Hijikata", "Christopher Eubanks", "Duje Ajdukovic", "Brandon Nakashima", "Jaime Faria",
            "D. Jorda Sanchis", "Dominik Koepfer", "Pablo Llamas Ruiz"
    };
    Player[] players = new Player[playersNames.length];
    MatchDao matchDao = MatchDao.getInstance();
    PlayerDao playerDao = PlayerDao.getInstance();
    Player player;
    Match match;

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
