package com.tennisscoreboard;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppStart {

    public void startApp() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        try (Session session = dbHandler.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            MatchDao matchDao = new MatchDao();
            PlayerDao playerDao = new PlayerDao();

            Player player1 = new Player();
            player1.setName("J.Wick");
            Player player2 = new Player();
            player2.setName("B.Gates");


            playerDao.savePlayer(player1);
            playerDao.savePlayer(player2);


            Match m1 = new Match();
            m1.setId(1);
            m1.setPlayerOne(player1);
            m1.setPlayerTwo(player2);
            m1.setWinner(player1);

            transaction.commit();

            System.out.println("_______________");
            System.out.println("Player1's name " + player1.getName());
            System.out.println("Player2's name " + player2.getName());
            System.out.println("_______________");
        }
    }
}
