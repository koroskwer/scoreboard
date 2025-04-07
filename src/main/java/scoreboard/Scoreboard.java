package scoreboard;

import java.util.List;

/**
 * Facade class used to receive calls regarding matches management and providing match summaries.
 */
public class Scoreboard {

    private final MatchService matchService;

    Scoreboard(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Starts the match. Only one match between two teams with the same name can be active at once.
     *
     * @param homeTeamName Name of the home team.
     * @param awayTeamName Name of the away team.
     */
    public void startMatch(String homeTeamName, String awayTeamName) {
        this.matchService.startMatch(homeTeamName, awayTeamName);
    }


    /**
     * Finishes a match. Finishing a match removes it from repository.
     *
     * @param homeTeamName Name of the home team.
     * @param awayTeamName Name of the away team.
     */
    public void finishMatch(String homeTeamName, String awayTeamName) {
        this.matchService.finishMatch(homeTeamName, awayTeamName);
    }

    /**
     * Updates a match. Only a score of a match can be updated. Scores cannot be updated to have values smaller than 0.
     *
     * @param homeTeamName  Name of the home team.
     * @param awayTeamName  Name of the away team.
     * @param homeTeamScore Score of the home team.
     * @param awayTeamScore Score of the away team.
     */
    public void updateMatch(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
        this.matchService.updateMatch(homeTeamName, awayTeamName, homeTeamScore, awayTeamScore);
    }

    /**
     * Returns summary of matches. Matches will be sorted first basing on sum of both team scores.
     * If these sums are equal, then matches are sorted basing on their identifier. Both are ordered in descending order.
     *
     * @return List of all ongoing matches
     */
    public List<Match> getMatchesSummary() {
        return this.matchService.getMatchesSummary();
    }
}
