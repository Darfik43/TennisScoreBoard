package com.tennisscoreboard.service.dao;
import com.tennisscoreboard.DatabaseHandler;
import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerDao implements Dao<Player> {

    private static PlayerDao playerDao;
    public static PlayerDao getInstance() {

        if (playerDao == null) {
            playerDao = new PlayerDao();
        }

        return playerDao;
    }

    public Optional<Player> getPlayerByName(String playerName) {
        Player player = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            player = session.get(Player.class, playerName);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException(playerName + " doesn't exist");
        }
        return Optional.ofNullable(player);
    }

    @Override
    public void create(Player player) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException("An error happened during the player creation");
        }
    }
}
