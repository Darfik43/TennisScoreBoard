package com.tennisscoreboard.services.DAOs;
import com.tennisscoreboard.DatabaseHandler;
import com.tennisscoreboard.model.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
            Query<Player> query = session.createQuery("FROM Player WHERE name = :name", Player.class);
            query.setParameter("name", playerName);
            player = query.uniqueResult();
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
