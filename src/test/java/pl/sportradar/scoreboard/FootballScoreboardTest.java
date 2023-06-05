package pl.sportradar.scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.sportradar.scoreboard.pojo.FootballMatch;

class FootballScoreboardTest {

    private FootballScoreboard footballScoreboard;

    @BeforeEach
    void setUp() {
        footballScoreboard = new FootballScoreboard();
    }

    @Test
    void startNewGame_ShouldAddMatchToScoreboard() {
        String homeTeam = "Home";
        String awayTeam = "Away";

        footballScoreboard.startNewGame(homeTeam, awayTeam);

        List<FootballMatch> matchesInProgress = footballScoreboard.getSummaryOfGamesInProgress();
        assertEquals(1, matchesInProgress.size());
        assertEquals(homeTeam, matchesInProgress.get(0).getHomeTeam());
        assertEquals(awayTeam, matchesInProgress.get(0).getAwayTeam());
    }

    @ParameterizedTest
    @CsvSource({ "5, 3", "2, 2", "1, 4" })
    void updateScore_ShouldUpdateMatchScore(int homeScore, int awayScore) {
        String homeTeam = "Home";
        String awayTeam = "Away";

        footballScoreboard.startNewGame(homeTeam, awayTeam);
        footballScoreboard.updateScore(homeTeam, awayTeam, homeScore, awayScore);

        List<FootballMatch> matchesInProgress = footballScoreboard.getSummaryOfGamesInProgress();
        assertEquals(homeScore, matchesInProgress.get(0).getHomeScore());
        assertEquals(awayScore, matchesInProgress.get(0).getAwayScore());
    }

    @Test
    void finishGame_ShouldRemoveMatchFromScoreboard() {
        String homeTeam = "Home";
        String awayTeam = "Away";

        footballScoreboard.startNewGame(homeTeam, awayTeam);
        footballScoreboard.finishGame(homeTeam, awayTeam);

        List<FootballMatch> matchesInProgress = footballScoreboard.getSummaryOfGamesInProgress();
        assertEquals(0, matchesInProgress.size());
    }

    @Test
    void getSummaryOfGamesInProgress_ShouldReturnMatchesOrderedByTotalScoreAndStartTime() {
        FootballMatch match1 = new FootballMatch("Uruguay", "Italy");
        match1.setHomeScore(6);
        match1.setAwayScore(6);

        FootballMatch match2 = new FootballMatch("Spain", "Brazil");
        match2.setHomeScore(10);
        match2.setAwayScore(2);

        FootballMatch match3 = new FootballMatch("Mexico", "Canada");
        match3.setHomeScore(0);
        match3.setAwayScore(5);

        FootballMatch match4 = new FootballMatch("Argentina", "Australia");
        match4.setHomeScore(3);
        match4.setAwayScore(1);

        FootballMatch match5 = new FootballMatch("Germany", "France");
        match5.setHomeScore(2);
        match5.setAwayScore(2);

        footballScoreboard.startNewGame("Uruguay", "Italy");
        footballScoreboard.updateScore("Uruguay", "Italy", 6, 6);

        footballScoreboard.startNewGame("Spain", "Brazil");
        footballScoreboard.updateScore("Spain", "Brazil", 10, 2);

        footballScoreboard.startNewGame("Mexico", "Canada");
        footballScoreboard.updateScore("Mexico", "Canada", 0, 5);

        footballScoreboard.startNewGame("Argentina", "Australia");
        footballScoreboard.updateScore("Argentina", "Australia", 3, 1);

        footballScoreboard.startNewGame("Germany", "France");
        footballScoreboard.updateScore("Germany", "France", 2, 2);

        List<FootballMatch> summary = footballScoreboard.getSummaryOfGamesInProgress();

        assertEquals(match1.toString(), summary.get(0).toString());
        assertEquals(match2.toString(), summary.get(1).toString());
        assertEquals(match3.toString(), summary.get(2).toString());
        assertEquals(match4.toString(), summary.get(3).toString());
        assertEquals(match5.toString(), summary.get(4).toString());
    }

}
