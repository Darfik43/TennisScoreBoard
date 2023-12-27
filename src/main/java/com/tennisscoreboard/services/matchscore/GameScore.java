package com.tennisscoreboard.services.matchscore;

import com.tennisscoreboard.Player;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameScore {
    private final Map<String, RegularScorePoints> gameScore = new ConcurrentHashMap<>();
    private static final EnumMap<RegularScorePoints, String> ptsEnum = new EnumMap<>(RegularScorePoints.class);

    static {
        ptsEnum.put(RegularScorePoints.LOVE, "0");
        ptsEnum.put(RegularScorePoints.FIRST, "15");
        ptsEnum.put(RegularScorePoints.SECOND, "30");
        ptsEnum.put(RegularScorePoints.THIRD, "40");
        ptsEnum.put(RegularScorePoints.AD, "AD");
    }
    RegularScorePoints rsp = RegularScorePoints.LOVE;
    public GameScore(Player playerOne, Player playerTwo) {
        gameScore.put(playerOne.getName(), getStartPoints());
    }


    public RegularScorePoints getStartPoints() {
        return RegularScorePoints.LOVE;
    }

    public void upPoints(Player player) {
        gameScore.put(player.getName(), RegularScorePoints.values()[getCurrentPts(player) + 1]);
        //TODO main logic of scoring
    }

    public boolean isTieBreak() {

    }

    public int getCurrentPts(Player player) {
        return gameScore.get(player.getName()).ordinal();
    }
}


