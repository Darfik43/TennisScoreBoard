package com.tennisscoreboard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "scoreboard", value = "/scoreboard")
public class ScoreboardServlet extends HttpServlet {
    MatchDao matchDao = new MatchDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Match> allMatches = matchDao.getAllMatches();
        PrintWriter pw = resp.getWriter();
        for (int i = 0; i < allMatches.size(); i++) {
            pw.println(i);
        }
        //req.getRequestDispatcher("scoreboard.jsp").forward(req, resp);
    }
}
