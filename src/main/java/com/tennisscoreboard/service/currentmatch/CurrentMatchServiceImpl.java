package com.tennisscoreboard.service.currentmatch;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.model.Player;
import com.tennisscoreboard.service.dao.MatchDao;
import com.tennisscoreboard.service.scorecalculation.GameScore;
import com.tennisscoreboard.service.scorecalculation.Score;
import com.tennisscoreboard.service.scorecalculation.SetScore;

import java.util.*;

public class CurrentMatchServiceImpl implements CurrentMatchService {

    private static final Map<UUID, Match> currentMatches = new HashMap<>();
    private final MatchDao matchDao = new MatchDao();


    @Override
    public void startNewMatch(Player player1, Player player2) {
        Match match = new Match(player1, player2);
        UUID matchId = UUID.randomUUID();
        currentMatches.put(matchId, match);
        Score score = new Score(match, new SetScore(match.getPlayer1().getName(), match.getPlayer2().getName(), new GameScore(match.getPlayer1().getName(), match.getPlayer2().getName())));
    }

    @Override
    public void endMatch(UUID matchId) {
        currentMatches.remove(matchId);
    }

    @Override
    public List<Match> getAllCurrentMatches() {
        return new ArrayList<>(currentMatches.values());
    }

    @Override
    public void saveFinishedMatch(Match match) {
        matchDao.create(match);
    }
}

