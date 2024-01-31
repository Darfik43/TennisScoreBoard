package com.tennisscoreboard.servlet;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/increaseScore")
public class IncreaseScoreServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры из запроса
        String matchIdString = request.getParameter("matchId");
        String playerIdString = request.getParameter("playerId");

        // Проверяем, что параметры не пустые
        if (matchIdString != null && playerIdString != null) {
            try {
                // Преобразуем строки в UUID и Long
                UUID matchId = UUID.fromString(matchIdString);
                Long playerId = Long.parseLong(playerIdString);

                // Получаем текущий матч из сессии
                Match currentMatch = (Match) request.getSession().getAttribute("currentMatch");

                // Проверяем, что матч существует и его ID совпадает с параметром из запроса
                if (currentMatch != null && currentMatch.getId().equals(matchId)) {
                    // Получаем игрока по ID
                    Player player = currentMatch.getPlayer1();

                    // Проверяем, что игрок существует
                    if (player != null) {
                        // Обрабатываем увеличение счета (ваша бизнес-логика)
                        // Здесь вы можете использовать ваш сервис для увеличения счета

                        // Пример: увеличение счета игрока

                        // Обновляем сессию с обновленным матчем
                        request.getSession().setAttribute("currentMatch", currentMatch);

                        // Перенаправляем на страницу счета
                        response.sendRedirect(request.getContextPath() + "/match.jsp");
                        return;
                    }
                }
            } catch (IllegalArgumentException e) {
                // Обработка ошибки преобразования параметров
                e.printStackTrace();
            }
        }

        // Если что-то пошло не так, перенаправляем на страницу ошибки
        response.sendRedirect(request.getContextPath() + "/error.jsp");
    }
}

