package com.tennisscoreboard;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlayerDao {

    public void savePlayer(Player player) {
        Transaction transaction;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        }

    }

    public static final String GET_PLAYER_BY_NAME = "FROM players WHERE name = :paramName";

    public Player getPlayer(String playerName) {
        Player player = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Player where name= :name", Player.class);
            query.setParameter("name", playerName);
            player = (Player) query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    public boolean isPlayerExisting(Player player) {
        if (getPlayer(player.toString()) != null) {
            return true;
        } else {
            return false;
        }
    }
}
