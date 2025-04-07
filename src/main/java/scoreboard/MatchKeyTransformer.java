package scoreboard;

/**
 * Class responsible for transforming Matches into unique map keys
 */
class MatchKeyTransformer {

    // TODO - make separator injectable
    private final String separator = "-";

    /**
     * Transforms match data into a key used in repository
     *
     * @param match Match object containing the data about teams and their score
     * @return key in String format
     */
    String transform(Match match) {
        return String.format("%s %s %s", match.getHomeTeamName(), separator, match.getAwayTeamName());
    }

    /**
     * Transforms two given team names into a key used in repository
     *
     * @param homeTeamName name of home team
     * @param awayTeamName name of away team
     * @return key in String format
     */
    String transform(String homeTeamName, String awayTeamName) {
        return String.format("%s %s %s", homeTeamName, separator, awayTeamName);
    }
}
