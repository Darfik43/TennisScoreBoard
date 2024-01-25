package com.tennisscoreboard.service.currentmatch;

import com.tennisscoreboard.model.Match;
import com.tennisscoreboard.service.dao.MatchDao;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentMatchServiceImpl implements CurrentMatchService {

    private final Map<Integer, Match> currentMatches = new HashMap<>();
    private final MatchDao matchDao;

    public CurrentMatchServiceImpl(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    @Override
    public void addMatch(Match match) {
        currentMatches.put(match.getId(), match);
    }

    @Override
    public void removeMatch(int matchId) {
        currentMatches.remove(matchId);
    }

    @Override
    public List<Match> getAllCurrentMatches() {
        return new ArrayList<>(currentMatches.values());
    }

    @Override
    public void saveFinishedMatch(Match match) {
        matchDao.createMatch(match);
    }
}

