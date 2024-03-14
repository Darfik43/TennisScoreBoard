package com.tennisscoreboard.servlet;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;
import com.tennisscoreboard.service.scorecalculation.MatchManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final CurrentMatchServiceImpl currentMatchService = CurrentMatchServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = (UUID) req.getSession().getAttribute("matchId");


        MatchManager matchManager = MatchManager.getInstance(uuid);
        matchManager.initNewMatch();
        req.setAttribute("currentMatch", currentMatchService.getCurrentMatch(uuid));
        req.setAttribute("player1Name", matchManager.getPlayer1Name());
        req.setAttribute("player2Name", matchManager.getPlayer2Name());
        req.setAttribute("score", matchManager.getScore());

        req.getRequestDispatcher("match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("matchId"));
        MatchManager matchManager = MatchManager.getInstance(uuid);
        matchManager.initNewMatch();

        String action = req.getParameter("action");
        if ("player1".equals(action)) {
            matchManager.playerWonPoint(matchManager.getPlayer1Name());
        } else if ("player2".equals(action)) {
            matchManager.playerWonPoint(matchManager.getPlayer2Name());
        }

        req.setAttribute("currentMatch", currentMatchService.getCurrentMatch(uuid));
        req.getRequestDispatcher("match.jsp").forward(req, resp);
    }
}
