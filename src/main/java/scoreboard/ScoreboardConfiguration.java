package scoreboard;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;


/**
 * Class used to create different configurations of Scoreboard for purpose of tests
 */
public class ScoreboardConfiguration {
    public static Scoreboard createProdConfiguration(){
        MatchFactory factory = new MatchFactory(Clock.systemDefaultZone(), 500);
        MatchService service = new MatchService(factory);
        return new Scoreboard(service);
    }
    public static Scoreboard createTestConfiguration(){
        MatchFactory factory = new MatchFactory(Clock.fixed(
                Instant.parse("2018-04-29T10:15:30.00Z"), ZoneId.of("Asia/Calcutta")), 1);
        MatchService service = new MatchService(factory);
        return new Scoreboard(service);
    }
}
