package com.tennisscoreboard.services.matchscore;

public enum RegularScore {
    ZERO(0), FIRST(15), SECOND(30), THIRD(40), AD;

    int pts;

    RegularScore() {

    }
    RegularScore(int pts) {
        this.pts = pts;
    }
}
