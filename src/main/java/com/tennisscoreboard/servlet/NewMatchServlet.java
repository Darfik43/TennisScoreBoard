package com.tennisscoreboard.servlet;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NewMatchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/createMatch.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1Name = request.getParameter("player1Name");
        String player2Name = request.getParameter("player2Name");


        Match match = new Match(new Player(player1Name), new Player(player2Name));

        response.sendRedirect(request.getContextPath() + "/scoreboard");
    }
}
