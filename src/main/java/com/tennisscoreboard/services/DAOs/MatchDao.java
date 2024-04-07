package com.tennisscoreboard.services.DAOs;

import com.tennisscoreboard.DatabaseHandler;
import com.tennisscoreboard.model.Match;
import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MatchDao implements Dao<Match> {

    private static MatchDao matchDao;
    public static MatchDao getInstance() {

        if (matchDao == null) {
            matchDao = new MatchDao();
        }

        return matchDao;
    }

    public Optional<Match> getById(UUID uuid) {
        Match match = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            match = session.get(Match.class, uuid);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException("There is no much with this UUID", e);
        }
        return Optional.ofNullable(match);
    }

    public List<Match> getMatchesByName(String playerName) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            String hql = "SELECT m FROM Match m JOIN m.player1 p1 JOIN m.player2 p2 JOIN m.winner w " +
                    "WHERE p1.name = :name OR p2.name = :name";
            Query query = session.createQuery(hql, Match.class);
            query.setParameter("name", playerName);
            List<Match> matches = query.getResultList();

            if (matches.isEmpty()) {
                throw new HibernateException("No matches found for player: " + playerName);
            }
            return matches;

        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException(playerName + " hasn't played any game yet", e);
        }
    }

    public List<Match> getPastMatches() {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            String hql = "FROM Match WHERE winner IS NOT NULL";
            Query query = session.createQuery(hql, Match.class);
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException("There are no past matches", e);
        }
    }

    @Override
    public void create(Match match) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException("Couldn't create match");
        }
    }
}
