package com.tennisscoreboard.service.dao;

import com.tennisscoreboard.model.Player;

public interface PlayerDaoInterface {
    Player getPlayerById(Long id);
    Player getPlayerByName(String playerName);
    void createPlayer(Player player);
}
