package com.tennisscoreboard.services.scorecalculation;

public enum TennisPoint {
    LOVE(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40),
    ADVANTAGE(41);


    private final int value;

    TennisPoint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

