package pl.sportradar.scoreboard;

import java.util.List;

import pl.sportradar.scoreboard.pojo.FootballMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.1.0
 * @since 0.0.1
 */
public class FootballScoreboard {

    private List<FootballMatch> matchesInProgress;

    public FootballScoreboard() {
        this.matchesInProgress = new ArrayList<>();
    }

    /**
     * Method name: startNewGame
     *
     * @param homeTeam Team name that will be on the left side of the score board.
     * @param awayTeam Team name that will be on the right side of the score board.
     * @throws IllegalArgumentException
     *
     *                                  Method that start Football game. Also,
     *                                  FootballMatch class validate data, if one or
     *                                  both parameters of this method have null,
     *                                  exception will be thrown.
     */
    public void startNewGame(String homeTeam, String awayTeam) {
        FootballMatch match = new FootballMatch(homeTeam, awayTeam);

        matchesInProgress.add(match);
    }

    /**
     * Method name: updateScore
     *
     * @param homeTeam  Team name on the left side of the score board.
     * @param awayTeam  Team name on the right side of the score board.
     * @param homeScore Desired value for home team.
     * @param awayScore Desired value for away team.
     * @throws IllegalArgumentException
     *
     *                                  Method that updates Football game score
     *                                  data. Also, FootballMatch class validate
     *                                  data, if one or both score parameters of
     *                                  this method is <0, exception will be thrown.
     */
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        FootballMatch match = findMatchByTeams(homeTeam, awayTeam);
        
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }

    /**
     * Method name: givePointToHomeTeam
     *
     * @param homeTeam Team name on the left side of the score board that will
     *                 receive point.
     * 
     *                 Method that give point to home team.
     */
    public void givePointToHomeTeam(String homeTeam, String awayTeam) {
        FootballMatch match = findMatchByTeams(homeTeam, awayTeam);

        match.setHomeScore(match.getHomeScore() + 1);
    }

    /**
     * Method name: givePointToAwayTeam
     *
     * @param homeTeam Team name on the left side of the score board.
     * @param awayTeam Team name on the right side of the score board.
     * 
     *                 Method that give point to away team.
     */
    public void givePointToAwayTeam(String homeTeam, String awayTeam) {
        FootballMatch match = findMatchByTeams(homeTeam, awayTeam);

        match.setHomeScore(match.getAwayScore() + 1);
    }

    /**
     * Method name: finishGame
     *
     * @param homeTeam Team name that will be on the left side of the score board.
     * @param awayTeam Team name that will be on the right side of the score board.
     *
     *                 Method that give point to away team.
     * @return (FootballMatch) Finished match.
     */
    public FootballMatch finishGame(String homeTeam, String awayTeam) {
        FootballMatch match = findMatchByTeams(homeTeam, awayTeam);

        matchesInProgress.remove(match);

        return match;
    }

    /**
     * Method name: getSummaryOfGamesInProgress
     *
     * @return (List<FootballMatch>) All ongoing games.
     */
    public List<FootballMatch> getSummaryOfGamesInProgress() {
        List<FootballMatch> summary = new ArrayList<>(matchesInProgress);

        Collections.sort(summary,
                Comparator.comparingInt((FootballMatch match) -> match.getHomeScore() + match.getAwayScore())
                        .thenComparing(FootballMatch::getStartTime).reversed());

        return summary;
    }

    private FootballMatch findMatchByTeams(String homeTeam, String awayTeam) {
        for (FootballMatch match : matchesInProgress) {
            if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) {
                return match;
            }
        }
        throw new IllegalArgumentException(
                "No match found for the home team: " + homeTeam + " and away team: " + awayTeam);
    }

}
