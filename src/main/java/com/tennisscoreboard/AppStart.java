package com.tennisscoreboard;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionListener;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppStart implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MatchDao matchDao = new MatchDao();
        final ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("matchDao", matchDao);
        startApp();
    }

    public void startApp()  {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            MatchDao matchDao = new MatchDao();
            PlayerDao playerDao = new PlayerDao();

            Player player1 = new Player();
            player1.setName("J.Wick");
            Player player2 = new Player();
            player2.setName("B.Gates");


            playerDao.savePlayer(player1);
            System.out.println(playerDao.getPlayer("J.Wick"));
            playerDao.savePlayer(player2);
            System.out.println(playerDao.getPlayer("B.Gates"));




            transaction.commit();

        }
    }
}
