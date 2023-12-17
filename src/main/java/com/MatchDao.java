package com;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchDao {
    private static final String FILTER_BY_NAME = "FROM matches WHERE matches.playerOne.name = :playerName or matches.playerTwo.name = :playerName";
    private static final String ALL_MATCHES = "FROM matches";

    public void saveMatch(Match match) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Transaction transaction = null;
        PlayerDao playerDao = new PlayerDao();
        try (Session session = dbHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (match.getPlayerOne().getId() == 0) {
                playerDao.savePlayer(match.getPlayerOne());
            }
            if (match.getPlayerTwo().getId() == 0) {
                playerDao.savePlayer(match.getPlayerTwo());
            }
            match.getPlayerOne().setId(playerDao.getPlayer(match.getPlayerOne().toString()).getId());
            match.getPlayerTwo().setId(playerDao.getPlayer(match.getPlayerTwo().toString()).getId());
        }
    }

    public Match getAllMatches() {
       //TODO
    }

}
