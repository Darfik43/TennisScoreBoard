package com.tennisscoreboard.dto;

import lombok.Getter;

@Getter
public record MatchDTO(String player1Name, String player2Name) {
}
