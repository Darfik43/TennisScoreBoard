package com.tennisscoreboard.servlet;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.PlayersService;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/nmatch")
public class NewMatchServlet extends HttpServlet {
    private final PlayersService playersService = PlayersService.getInstance();

    private final CurrentMatchServiceImpl currentMatchService = CurrentMatchServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1Name = request.getParameter("player1Name");
        String player2Name = request.getParameter("player2Name");


        Player player1 = playersService.createPlayer(player1Name);
        Player player2 = playersService.createPlayer(player2Name);
        UUID uuid = UUID.fromString(request.getParameter("matchId"));


        currentMatchService.startNewMatch(player1, player2, uuid);

        request.setAttribute("matchId,", uuid);
        response.sendRedirect("match-score?matchId=" + uuid);
    }
}