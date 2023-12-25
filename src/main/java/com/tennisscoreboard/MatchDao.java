package com.tennisscoreboard;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class MatchDao {
    private static final String FILTER_BY_NAME = "FROM matches WHERE matches.playerOne.name = :playerName or matches.playerTwo.name = :playerName";
    private static final String ALL_MATCHES = "FROM matches";

    public void saveMatch(Match match) {
        Transaction transaction;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        }
    }

    public List<Match> getAllMatches() {
        Transaction transaction;
        List<Match> matches = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Match.class);
            Root rootEntry = cq.from(Match.class);
            CriteriaQuery all = cq.select(rootEntry);
            TypedQuery allQuery = session.createQuery(all);
            matches = (List<Match>) allQuery.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }
}
