package scoreboard;

import java.util.List;

public class Scoreboard {

    private final MatchService matchService;

    Scoreboard(MatchService matchService) {
        this.matchService = matchService;
    }

    public void startMatch(String homeTeamName, String awayTeamName) {
        this.matchService.startMatch(homeTeamName, awayTeamName);
    }

    public void finishMatch(String homeTeamName, String awayTeamName) {
        this.matchService.finishMatch(homeTeamName, awayTeamName);
    }

    public void updateMatch(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
        this.matchService.updateMatch(homeTeamName, awayTeamName, homeTeamScore, awayTeamScore);
    }

    public List<Match> getMatchesSummary() {
        return this.matchService.getMatchesSummary();
    }
}
