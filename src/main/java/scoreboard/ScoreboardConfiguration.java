package scoreboard;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;


/**
 * Class used to create different configurations of Scoreboard depending on their purpose.
 */
// TODO - move this configuration to Spring Beans
public class ScoreboardConfiguration {
    public static Scoreboard createProdConfiguration() {
        MatchFactory factory = new MatchFactory(Clock.systemDefaultZone(), 500);
        MatchService service = new MatchService(factory, new MatchKeyTransformer(), new InMemoryConcurrentMatchRepository<String, Match>());
        return new Scoreboard(service);
    }

    public static Scoreboard createTestConfiguration() {
        MatchFactory factory = new MatchFactory(Clock.fixed(
                Instant.parse("2020-10-10T10:15:30.00Z"), ZoneOffset.UTC), 1);
        MatchService service = new MatchService(factory, new MatchKeyTransformer(), new InMemoryConcurrentMatchRepository<String, Match>());
        return new Scoreboard(service);
    }

    public static Scoreboard createTestConfiguration(Clock clock) {
        MatchFactory factory = new MatchFactory(clock, 1);
        MatchService service = new MatchService(factory, new MatchKeyTransformer(), new InMemoryConcurrentMatchRepository<String, Match>());
        return new Scoreboard(service);
    }
}
