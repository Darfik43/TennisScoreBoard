package com.tennisscoreboard.servlet;

import com.tennisscoreboard.AppStartData;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private final AppStartData appStartData = new AppStartData();
    @Override
    public void init() {
        appStartData.createStartPlayers();
        appStartData.createStartMatches();
    }
}
