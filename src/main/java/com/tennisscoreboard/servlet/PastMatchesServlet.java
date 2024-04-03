package com.tennisscoreboard.servlet;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.service.dao.MatchDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/results")
public class PastMatchesServlet extends HttpServlet {
    private final MatchDao matchDao = MatchDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Match> pastMatches = matchDao.getPastMatches();
        request.setAttribute("results", pastMatches);
        request.getRequestDispatcher("pastMatches.jsp").forward(request, response);

    }
}
