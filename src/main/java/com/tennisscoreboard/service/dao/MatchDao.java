package com.tennisscoreboard.service.dao;

import com.tennisscoreboard.DatabaseHandler;
import com.tennisscoreboard.model.Match;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MatchDao implements Dao<Match> {


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
