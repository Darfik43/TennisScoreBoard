package com.tennisscoreboard.servlet;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.currentmatch.CurrentMatchServiceImpl;
import com.tennisscoreboard.service.scorecalculation.MatchManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/increaseScore")
public class IncreaseScoreServlet extends HttpServlet {

    private final CurrentMatchServiceImpl currentMatchService = CurrentMatchServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры из запроса
        String matchIdString = request.getParameter("matchId");
        String action = request.getParameter("action");

        // Проверяем, что параметры не пустые
        if (matchIdString != null && action != null) {
            try {
                // Преобразуем строку в UUID
                UUID matchId = UUID.fromString(matchIdString);

                // Получаем текущий матч из сервиса
                MatchManager matchManager = MatchManager.getInstance(matchId);
                matchManager.initNewMatch();

                // Увеличиваем счет в зависимости от действия
                if ("player1".equals(action)) {
                    matchManager.playerWonPoint(matchManager.getPlayer1Name());
                } else if ("player2".equals(action)) {
                    matchManager.playerWonPoint(matchManager.getPlayer2Name());
                }

                // Обновляем сессию с обновленным матчем
                request.getSession().setAttribute("currentMatch", currentMatchService.getCurrentMatch(matchId));

                // Перенаправляем на страницу счета
                response.sendRedirect(request.getContextPath() + "/match.jsp?uuid=" + matchId);
                return;
            } catch (IllegalArgumentException e) {
                // Обработка ошибки преобразования параметров
                e.printStackTrace();
            }
        }

        // Если что-то пошло не так, перенаправляем на страницу ошибки
        response.sendRedirect(request.getContextPath() + "/error.jsp");
    }
}

