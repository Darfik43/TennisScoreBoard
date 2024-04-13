package com.tennisscoreboard.controllers;
import com.tennisscoreboard.services.scorecalculation.MatchManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("matchId"));


        try {
            MatchManager matchManager = (MatchManager) req.getSession().getAttribute(uuid.toString());
            String player1Name =  matchManager.getPlayer1Name();
            String player2Name =  matchManager.getPlayer2Name();

            req.setAttribute("player1Name", player1Name);
            req.setAttribute("player2Name", player2Name);
            req.setAttribute("player1MatchScore", matchManager.getPlayerMatchScore(player1Name));
            req.setAttribute("player1SetScore", matchManager.getPlayerSetScore(player1Name));
            req.setAttribute("player1GameScore", matchManager.getPlayerGameScore(player1Name));
            req.setAttribute("player1TieBreakScore", matchManager.getPlayerTieBreakScore(player1Name));
            req.setAttribute("player2MatchScore", matchManager.getPlayerMatchScore(player2Name));
            req.setAttribute("player2SetScore", matchManager.getPlayerSetScore(player2Name));
            req.setAttribute("player2GameScore", matchManager.getPlayerGameScore(player2Name));
            req.setAttribute("player2TieBreakScore", matchManager.getPlayerTieBreakScore(player2Name));

            req.getRequestDispatcher("match.jsp").forward(req, resp);
        } catch (HibernateException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
