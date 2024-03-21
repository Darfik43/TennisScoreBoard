package com.tennisscoreboard.servlet;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;
import com.tennisscoreboard.service.scorecalculation.MatchManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/increaseScore")
public class IncreaseScoreServlet extends HttpServlet {

    private final CurrentMatchServiceImpl currentMatchService = CurrentMatchServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchIdParam = request.getParameter("matchId");
        if (matchIdParam != null) {
            UUID uuid = UUID.fromString(matchIdParam);
            try {
                MatchManager matchManager = (MatchManager) request.getAttribute("matchManager");

                String action = request.getParameter("action");
                if ("player1".equals(action)) {
                    matchManager.playerWonPoint(matchManager.getPlayer1Name());
                } else if ("player2".equals(action)) {
                    matchManager.playerWonPoint(matchManager.getPlayer2Name());
                }

                request.getSession().setAttribute("currentMatch", currentMatchService.getCurrentMatch(uuid));

                response.sendRedirect("match-score");
                return;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/error.jsp");
    }
}

