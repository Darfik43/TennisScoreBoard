package com.tennisscoreboard;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class MatchDao {
    private static final String FILTER_BY_NAME = "FROM matches WHERE matches.playerOne.name = :playerName or matches.playerTwo.name = :playerName";
    private static final String ALL_MATCHES = "FROM matches";

    public void saveMatch(Match match) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Transaction transaction;
        PlayerDao playerDao = new PlayerDao();
        try (Session session = dbHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
//            if (match.getPlayerOne().getId() == 0) {
                playerDao.savePlayer(match.getPlayerOne());
//            }
//            if (match.getPlayerTwo().getId() == 0) {
                playerDao.savePlayer(match.getPlayerTwo());
//            }
            match.getPlayerOne().setId(playerDao.getPlayer(match.getPlayerOne().toString()).getId());
            match.getPlayerTwo().setId(playerDao.getPlayer(match.getPlayerTwo().toString()).getId());

            transaction.commit();
        }
    }

    public List<Match> getAllMatches() {
        Transaction transaction;
        DatabaseHandler dbHandler = new DatabaseHandler();
        List<Match> allMatches = null;
        try (Session session = dbHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Match> query = session.createQuery("FROM matches", Match.class);
            allMatches = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allMatches;
    }
}
