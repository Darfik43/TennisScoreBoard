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

@WebServlet("/pastMatches")
public class PastMatchesServlet extends HttpServlet {
    private final MatchDao matchDao = MatchDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Match> pastMatches;
        String playerName = request.getParameter("playerName");

        if (playerName != null && !playerName.isEmpty()) {
            pastMatches = matchDao.getMatchesByName(playerName);
        } else {
            pastMatches = matchDao.getPastMatches();
        }

        int page;
        if (request.getParameter("page") == null) {
            page = 1;
        } else {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int pageSize = 2;
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, pastMatches.size());
        int totalPages = (pastMatches.size() + 1) / 2;
        List<Match> currentPageMatches = pastMatches.subList(fromIndex, toIndex);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPageMatches", currentPageMatches);
        request.getRequestDispatcher("pastMatches.jsp").forward(request, response);

    }
}
