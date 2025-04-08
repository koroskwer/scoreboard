package scoreboard;

import java.time.LocalDateTime;

/**
 * Class used to store data about football matches. Identifiers of the matches are assumed to be immutable.
 */
class Match {

    private final long id;
    private final String homeTeamName;
    private final String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;

    private final LocalDateTime creationTime;


    Match(long id, String homeTeam, String awayTeam, LocalDateTime creationTime) {
        this.id = id;
        this.validateTeamName(homeTeam);
        this.homeTeamName = homeTeam;
        this.validateTeamName(awayTeam);
        this.awayTeamName = awayTeam;
        this.creationTime = creationTime;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
    }

    long getId() {
        return this.id;
    }

    LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    String getAwayTeamName() {
        return this.awayTeamName;
    }

    String getHomeTeamName() {
        return this.homeTeamName;
    }

    int getAwayTeamScore() {
        return this.awayTeamScore;
    }

    int getHomeTeamScore() {
        return this.homeTeamScore;
    }

    void setAwayTeamScore(int awayTeamScore) {
        this.validateScore(awayTeamScore);
        this.awayTeamScore = awayTeamScore;
    }

    void setHomeTeamScore(int homeTeamScore) {
        this.validateScore(homeTeamScore);
        this.homeTeamScore = homeTeamScore;
    }

    private void validateScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Team score must be above 0");
        }
    }

    private void validateTeamName(String teamName) {
        if (teamName == null) {
            throw new IllegalArgumentException("Team name must be provided");
        }
    }
}
