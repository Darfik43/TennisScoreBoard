package com.tennisscoreboard.service;

import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;

public class PlayersService {

    private static PlayersService playersService;

    public static PlayersService getInstance() {
        if (playersService == null) {
            playersService = new PlayersService();
        }

        return playersService;
        }

    public Player createPlayer(String playerName) {
        Player player = new Player();
        player.setName(playerName);
        return player;
    }
}
