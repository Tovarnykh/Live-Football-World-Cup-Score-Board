package pl.sportradar.scoreboard.pojo;

import java.time.LocalDateTime;
import java.util.Objects;

public class FootballMatch {
    
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private LocalDateTime startTime;
    
    public FootballMatch(String homeTeam, String awayTeam){
        
        if (homeTeam == null || homeTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Home team name cannot be null or empty.");
        }
        if (awayTeam == null || awayTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Away team name cannot be null or empty.");
        }
        
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = LocalDateTime.now();
    }
    
    public synchronized String getHomeTeam() {
        return homeTeam;
    }

    public synchronized void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public synchronized void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public synchronized void setHomeScore(int homeScore) {
        if (homeScore < 0) {
            throw new IllegalArgumentException("Home score cannot be negative.");
        }
        
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public synchronized void setAwayScore(int awayScore) {
        if (awayScore < 0) {
            throw new IllegalArgumentException("Away score cannot be negative.");
        }
        
        this.awayScore = awayScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public synchronized void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    @Override
    public String toString() {
        return "FootballMatch{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore +
                '}';
    }
    
    @Override
    public boolean equals(Object anotherMatch) {
        if (this == anotherMatch) return true;
        if (anotherMatch == null || getClass() != anotherMatch.getClass()) return false;
        FootballMatch that = (FootballMatch) anotherMatch;
        return homeScore == that.homeScore &&
                awayScore == that.awayScore &&
                Objects.equals(homeTeam, that.homeTeam) &&
                Objects.equals(awayTeam, that.awayTeam) &&
                Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeScore, awayScore, startTime);
    }

}
