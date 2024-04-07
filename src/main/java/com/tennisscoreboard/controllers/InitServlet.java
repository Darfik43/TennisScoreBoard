package com.tennisscoreboard.controllers;

import com.tennisscoreboard.services.AppStartService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private final AppStartService appStartService = new AppStartService();
    @Override
    public void init() {
        appStartService.createStartPlayers();
        appStartService.createStartMatches();
    }
}
