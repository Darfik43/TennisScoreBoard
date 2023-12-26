package com.tennisscoreboard.services.matchscore;

import java.util.EnumMap;

public enum RegularScorePoints {
    ZERO(0), FIRST(15), SECOND(30), THIRD(40), AD;

    int pts;

    private static final EnumMap<RegularScorePoints, String> ptsEnum = new EnumMap<>(RegularScorePoints.class);

    static {
        ptsEnum.put(RegularScorePoints.ZERO, "0");
        ptsEnum.put(RegularScorePoints.FIRST, "15");
        ptsEnum.put(RegularScorePoints.SECOND, "30");
        ptsEnum.put(RegularScorePoints.THIRD, "40");
        ptsEnum.put(RegularScorePoints.AD, "AD");
    }

    RegularScorePoints() {

    }
    RegularScorePoints(int pts) {
        this.pts = pts;
    }
}
