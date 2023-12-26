package com.tennisscoreboard.services.matchscore;

import java.util.HashMap;
import java.util.Map;

public class inGameScore {
    Map<String, RegularScorePoints> gameScore = new HashMap<>();

    public
    public RegularScorePoints getStartPoints() {
        return RegularScorePoints.ZERO;
    }
}
