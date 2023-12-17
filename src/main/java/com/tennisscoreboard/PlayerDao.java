package com.tennisscoreboard;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlayerDao {

    public void savePlayer(Player player) {
        Transaction transaction;
        DatabaseHandler dbHandler = new DatabaseHandler();
        try (Session session = dbHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            session.flush();
            transaction.commit();
        }
    }

    public static final String GET_PLAYER_BY_NAME = "SELECT FROM players WHERE name = :paramName";

    public Player getPlayer(String playerName) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        try (Session session = dbHandler.getSessionFactory().openSession()) {
            Query<Player> query = session.createQuery(GET_PLAYER_BY_NAME, Player.class);
            query.setParameter("paramName", playerName);
            List<Player> players = query.getResultList();

            return players.get(0);

            // NullPointerException
        }
    }

    public boolean isPlayerExisting(Player player) {
        if (getPlayer(player.toString()) != null) {
            return true;
        } else {
            return false;
        }
    }
}