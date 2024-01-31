package com.tennisscoreboard.service.dao;
import com.tennisscoreboard.DatabaseHandler;
import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerDao implements Dao<Player> {

    @Override
    public Optional<Player> getById(Long id) {
        Player player = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            player = session.get(Player.class, id);
            session.getTransaction().commit();
        } catch (HibernateError e) {
            System.out.println("This player doesn't exist");
        }
        return Optional.ofNullable(player);
    }

//    @Override
//    public Player getPlayerByName(String playerName) {
//        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Player WHERE name = :name ", Player.class)
//                    .setParameter("name", playerName).uniqueResult();
//        }
//    }

    @Override
    public void create(Player player) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Can't create player");
        }
    }
}
