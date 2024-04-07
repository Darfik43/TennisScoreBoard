package com.tennisscoreboard.controllers;

import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.services.PlayersService;
import com.tennisscoreboard.services.currentmatch.CurrentMatchServiceImpl;
import com.tennisscoreboard.services.scorecalculation.MatchManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/nmatch")
public class NewMatchServlet extends HttpServlet {
    private final PlayersService playersService = PlayersService.getInstance();

    private final CurrentMatchServiceImpl currentMatchService = CurrentMatchServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1Name = request.getParameter("player1Name");
        String player2Name = request.getParameter("player2Name");


        try {
            Player player1 = playersService.createPlayer(player1Name);
            Player player2 = playersService.createPlayer(player2Name);

            UUID uuid = UUID.randomUUID();

            currentMatchService.startNewMatch(player1, player2, uuid);
            MatchManager matchManager = MatchManager.getInstance(uuid);
            matchManager.initNewMatch();

            request.getSession().setAttribute(uuid.toString(), matchManager);
            response.sendRedirect("match-score?matchId=" + uuid);
        } catch (HibernateException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }
}