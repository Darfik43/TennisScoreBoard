package com.tennisscoreboard;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.dao.MatchDao;
import com.tennisscoreboard.service.dao.PlayerDao;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionListener;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppStart implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        MatchDao matchDao = new MatchDao();
//        final ServletContext servletContext = sce.getServletContext();
//        servletContext.setAttribute("matchDao", matchDao);
//        startApp();
//    }

    public void startApp()  {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            MatchDao matchDao = new MatchDao(session);
//            PlayerDao playerDao = new PlayerDao(session);
//
//            Player player1 = new Player();
//            player1.setName("J.Wick");
//            Player player2 = new Player();
//            player2.setName("B.Gates");
//
//
//            playerDao.create(player1);
//            System.out.println(playerDao.getPlayerByName("J.Wick"));
//            playerDao.create(player2);
//            System.out.println(playerDao.getPlayerByName("B.Gates"));
//
//            Match m1 = new Match();
//
//            m1.setPlayer1(player1);
//            m1.setPlayer2(player2);
//            m1.setWinner(player1);
//            matchDao.create(m1);
//            System.out.println(matchDao.getById(1));

//            transaction.commit();

        }
    }
}
