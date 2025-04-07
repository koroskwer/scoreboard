package scoreboard;

import repository.Repository;

import java.util.Comparator;
import java.util.List;

class MatchService {
    private final MatchFactory matchFactory;
    private final MatchKeyTransformer matchKeyTransformer;
    private final Repository<String, Match> repository;

    MatchService(MatchFactory matchFactory, MatchKeyTransformer transformer, Repository<String, Match> repository) {
        this.matchFactory = matchFactory;
        this.matchKeyTransformer = transformer;
        this.repository = repository;
    }

    void startMatch(String homeTeamName, String awayTeamName) {
        Match match = this.matchFactory.createMatch(homeTeamName, awayTeamName);
        this.repository.save(this.matchKeyTransformer.transform(match), match);
    }

    void finishMatch(String homeTeamName, String awayTeamName) {
        this.repository.remove(this.matchKeyTransformer.transform(homeTeamName, awayTeamName));
    }

    void updateMatch(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
        String matchKey = this.matchKeyTransformer.transform(homeTeamName, awayTeamName);
        Match match = this.repository.load(matchKey);
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
        this.repository.update(matchKey, match);
    }

    List<Match> getMatchesSummary() {
        return this.repository.getAll().stream().sorted(this.getSummaryComparator()).toList();
    }

    private Comparator<Match> getSummaryComparator() {
        return Comparator.comparing((Match match) -> match.getHomeTeamScore() + match.getAwayTeamScore(),
                Comparator.reverseOrder()).thenComparing(Match::getId, Comparator.reverseOrder());
    }
}
