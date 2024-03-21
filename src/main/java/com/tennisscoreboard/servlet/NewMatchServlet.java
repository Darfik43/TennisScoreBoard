package com.tennisscoreboard.servlet;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.PlayersService;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;
import com.tennisscoreboard.service.scorecalculation.MatchManager;
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
        MatchManager matchManager = MatchManager.getInstance(uuid);
        matchManager.initNewMatch();
        request.setAttribute("matchManager", matchManager);
        request.setAttribute("player1Name", matchManager.getPlayer1Name());
        request.setAttribute("player2Name", matchManager.getPlayer2Name());
        request.setAttribute("matchId,", uuid);
        //new
        request.getRequestDispatcher("match-score?matchId=" + uuid).forward(request, response);
        //
        //response.sendRedirect("match-score?matchId=" + uuid);
    }
}