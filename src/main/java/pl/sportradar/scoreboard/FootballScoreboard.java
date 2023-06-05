package pl.sportradar.scoreboard;

import java.util.List;

import pl.sportradar.scoreboard.pojo.FootballMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FootballScoreboard {
    
    private List<FootballMatch> matchesInProgress;

    public FootballScoreboard() {
        this.matchesInProgress = new ArrayList<>();
    }

    public void startNewGame(String homeTeam, String awayTeam) {
        FootballMatch match = new FootballMatch(homeTeam, awayTeam);
        matchesInProgress.add(match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        for (FootballMatch match : matchesInProgress) {
            if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) {
                match.setHomeScore(homeScore);
                match.setAwayScore(awayScore);
                break;
            }
        }
    }

    public void finishGame(String homeTeam, String awayTeam) {
        matchesInProgress.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
    }

    public List<FootballMatch> getSummaryOfGamesInProgress() {
        List<FootballMatch> summary = new ArrayList<>(matchesInProgress);

        Collections.sort(summary, Comparator
                .comparingInt((FootballMatch match) -> match.getHomeScore() + match.getAwayScore())
                .thenComparing(FootballMatch::getStartTime)
                .reversed());
        
        return summary;
    }
    
}
