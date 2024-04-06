package com.tennisscoreboard.service;

import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;
import com.tennisscoreboard.service.dao.PlayerDao;

import java.util.Optional;

public class PlayersService {

    private static PlayersService playersService;
    private static final PlayerDao playerDao = PlayerDao.getInstance();

    public static PlayersService getInstance() {
        if (playersService == null) {
            playersService = new PlayersService();
        }

        return playersService;
        }

    public Player createPlayer(String playerName) {
        Optional<Player> player = playerDao.getPlayerByName(playerName);

        return player.orElseGet(() -> {
            Player newPlayer = new Player();
            newPlayer.setName(playerName);
            return newPlayer;
        });
    }
}
