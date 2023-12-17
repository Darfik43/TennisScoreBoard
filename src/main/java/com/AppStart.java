package com;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppStart {

    public void startApp() {
        addStartMatches();
    }

    public void addStartMatches() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        try (Session session = dbHandler.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            MatchDao matchDao = new MatchDao();
            PlayerDao playerDao = new PlayerDao();

            Player player1 = new Player();
            player1.setName("J.Wick");
            Player player2 = new Player();
            player1.setName("B.Gates");
            Player player3 = new Player();
            player1.setName("D.Walt");
            Player player4 = new Player();
            player1.setName("P.Armel");
            Player player5 = new Player();
            player1.setName("S.Black");
            Player player6 = new Player();
            player1.setName("A.Adams");

            playerDao.savePlayer(player1);
            playerDao.savePlayer(player2);
            playerDao.savePlayer(player3);
            playerDao.savePlayer(player4);
            playerDao.savePlayer(player5);
            playerDao.savePlayer(player6);

            // Matches declaration;
        }
    }
}
