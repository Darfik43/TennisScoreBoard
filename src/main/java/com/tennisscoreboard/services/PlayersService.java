package com.tennisscoreboard.services;

import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.services.DAOs.PlayerDao;

public class PlayersService {

    private static PlayersService playersService;

    public static PlayersService getInstance() {
        if (playersService == null) {
            playersService = new PlayersService();
        }

        return playersService;
        }

    public Player createPlayer(String playerName) {
            Player newPlayer = new Player();
            newPlayer.setName(playerName);
            return newPlayer;
    }
}
