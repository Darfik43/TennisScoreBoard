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

@WebServlet("/increaseScore")
public class IncreaseScoreServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchIdParam = request.getParameter("matchId");
        if (matchIdParam != null) {
            UUID uuid = UUID.fromString(matchIdParam);
            try {
                MatchManager matchManager = (MatchManager) request.getSession().getAttribute(uuid.toString());

                String action = request.getParameter("action");
                if ("player1".equals(action)) {
                    matchManager.playerWonPointAndCheckFinishedMatch(matchManager.getPlayer1Name());
                } else if ("player2".equals(action)) {
                    matchManager.playerWonPointAndCheckFinishedMatch(matchManager.getPlayer2Name());
                }

                if (matchManager.checkIsFinished()) {
                    response.sendRedirect("result?matchId=" + uuid);
                } else {
                    response.sendRedirect("match-score?matchId=" + uuid);
                }
                return;
            } catch (HibernateException e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        response.sendRedirect(request.getContextPath() + "/error.jsp");
    }
}

