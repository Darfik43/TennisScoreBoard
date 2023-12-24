package com.tennisscoreboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MatchController {
    void doGet(
            HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException;

    void doPost(
            HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException;
}
