package com.tennisscoreboard.servlet;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.service.dao.MatchDao;
import com.tennisscoreboard.service.scorecalculation.MatchManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/result")
public class HandleResultServlet extends HttpServlet {

    MatchDao matchDao = new MatchDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID matchId = UUID.fromString(request.getParameter("matchId"));
        Optional<Match> match = matchDao.getById(matchId);
        match.ifPresent(match1 -> {
            String winnerName = match1.getWinner().getName();
            request.setAttribute("winnerName", winnerName);
        });


        MatchManager matchManager = (MatchManager) request.getSession().getAttribute(matchId.toString());
        String player1Name =  matchManager.getPlayer1Name();
        String player2Name =  matchManager.getPlayer2Name();
        request.setAttribute("player1Name", player1Name);
        request.setAttribute("player2Name", player2Name);
        request.setAttribute("player1MatchScore", matchManager.getPlayerMatchScore(player1Name));
        request.setAttribute("player2MatchScore", matchManager.getPlayerMatchScore(player2Name));


        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
