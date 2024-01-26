package com.tennisscoreboard.service.dao;
import com.tennisscoreboard.DatabaseHandler;
import com.tennisscoreboard.model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PlayerDao implements PlayerDaoInterface {

    private final Session session;

    public PlayerDao(Session session) {
        this.session = session;
    }
    @Override
    public Player getPlayerById(Long id) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            return session.get(Player.class, id);
        }
    }

    @Override
    public Player getPlayerByName(String playerName) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            return session.createQuery("FROM Player WHERE name = :name ", Player.class)
                    .setParameter("name", playerName).uniqueResult();
        }
    }

    @Override
    public void createPlayer(Player player) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        }
    }
}
