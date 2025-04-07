package scoreboard;

import java.time.LocalDateTime;

/**
 * Class used to store data about football matches. Identifiers of the matches are assumed to be immutable.
 */
public class Match {

    private final long id;
    private final String firstTeamName;
    private final String secondTeamName;
    private int firstTeamScore;
    private int secondTeamScore;

    private final LocalDateTime creationTime;

    private MatchStatus status;

    public Match(long id, String firstTeam, String secondTeam, LocalDateTime creationTime){
        this.id = id;
        this.firstTeamName = firstTeam;
        this.secondTeamName = secondTeam;
        this.creationTime = creationTime;
        this.status = MatchStatus.ACTIVE;
        this.firstTeamScore = 0;
        this.secondTeamScore = 0;
    }

    public long getId() {
        return this.id;
    }

    MatchStatus getStatus() {
        return this.status;
    }

    void setStatus(MatchStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public String getSecondTeamName() {
        return this.secondTeamName;
    }

    public String getFirstTeamName() {
        return this.firstTeamName;
    }

    public int getSecondTeamScore() {
        return this.secondTeamScore;
    }

    public int getFirstTeamScore() {
        return this.firstTeamScore;
    }

    public void setSecondTeamScore(int secondTeamScore) {
        this.validateScore(secondTeamScore);
        this.secondTeamScore = secondTeamScore;
    }

    public void setFirstTeamScore(int firstTeamScore) {
        this.validateScore(firstTeamScore);
        this.firstTeamScore = firstTeamScore;
    }

    private void validateScore(int score){
        if(score < 0){
            throw new IllegalArgumentException("Team score must be above 0");
        }
    }
}
