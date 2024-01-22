package com.tennisscoreboard.service.dao;

import com.tennisscoreboard.DatabaseHandler;
import com.tennisscoreboard.model.Match;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDao implements MatchDaoInterface {


    @Override
    public Match getMatchById(int id) {
        return null;
    }

    @Override
    public void createMatch(Match match) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateMatchScore(int matchId, int winningPlayerId) {
        //TODO
    }
}
