package com.tennisscoreboard;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseHandler {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
