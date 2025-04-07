package scoreboard;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchKeyTransformerTest {

    private final static String HOME_TEAM = "Legia Warszawa";
    private final static String AWAY_TEAM = "Lech Poznań";

    private MatchKeyTransformer transformer = new MatchKeyTransformer();
    private MatchFactory matchFactory = new MatchFactory(
            Clock.fixed(Instant.parse("2020-10-10T10:15:30.00Z"), ZoneOffset.UTC), 1);

    @Test
    public void testMatchBeingFormattedCorrectly() {
        // Given
        Match sampleMatch = this.matchFactory.createMatch(HOME_TEAM, AWAY_TEAM);

        // When
        String result = this.transformer.transform(sampleMatch);

        // Then
        assertEquals(result, "Legia Warszawa versus Lech Poznań");

    }
}
