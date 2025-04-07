package scoreboard;

/**
 * Class responsible for transforming Matches into unique map keys
 */
class MatchKeyTransformer {

    // TODO - make separator injectable
    private final String separator = "versus";

    String transform(Match match) {
        return String.format("%s %s %s", match.getHomeTeamName(), separator, match.getAwayTeamName());
    }

    String transform(String homeTeamName, String awayTeamName) {
        return String.format("%s %s %s", homeTeamName, separator, awayTeamName);
    }
}
