package com.tennisscoreboard.service.dao;

import com.tennisscoreboard.DatabaseHandler;
import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.crypto.Data;
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
        } catch (HibernateError e) {
            System.out.println("This match doesn't exist");
        }
        return Optional.ofNullable(match);
    }

    public List<Match> getMatchesByName(String playerName) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            String hql = "SELECT m FROM Match m JOIN m.player1 p1 JOIN m.player2 p2 JOIN m.winner w " +
                    "WHERE p1.name = :name OR p2.name = :name";
            Query query = session.createQuery(hql, Match.class);
            query.setParameter("name", playerName);
            return query.getResultList();
        } catch (HibernateError e) {
            e.printStackTrace();
            System.out.println("Error message: " + e.getMessage()); //TODO
            return null;
        }
    }

    public List<Match> getPastMatches() {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            String hql = "FROM Match WHERE winner IS NOT NULL";
            Query query = session.createQuery(hql, Match.class);
            return query.getResultList();
        } catch (HibernateError e) {
            e.printStackTrace();
            //TODO
            return null;
        }
    }

    @Override
    public void create(Match match) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("createNewMatch Failed");
        }
    }
}
