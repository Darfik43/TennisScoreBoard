package com.tennisscoreboard.service;

import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;

public class PlayersService {

    private CurrentMatchServiceImpl currentMatchService;

    public Player createPlayer(String playerName) {
        Player player = new Player();
        player.setName(playerName);
        return player;
    }
}
