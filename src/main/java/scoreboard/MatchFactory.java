package scoreboard;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Factory used to create new matches. Clock must be provided during initialisation of factory, so the matches are
 * created in consistent time zones.
 */
class MatchFactory {

    private final Clock clock;
    private final AtomicLong idCounter;

    MatchFactory(Clock clock, int initialValue) {
        this.clock = clock;
        this.idCounter = new AtomicLong(initialValue);
    }

    Match createMatch(String homeTeam, String awayTeam) {
        return new Match(idCounter.getAndIncrement(), homeTeam, awayTeam, LocalDateTime.now(clock));
    }
}
