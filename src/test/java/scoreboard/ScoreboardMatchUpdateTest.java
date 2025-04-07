package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreboardMatchUpdateTest {

    private final static String HOME_TEAM_ONE = "Legia Warszawa";
    private final static String AWAY_TEAM_ONE = "Lech Poznań";
    private final static String HOME_TEAM_TWO = "Zagłębie Lubin";
    private final static String AWAY_TEAM_TWO = "Pogoń Grodzisk";

    @Test
    public void testMatchBeingUpdatedWithNewScore() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When
        scoreboard.startMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        scoreboard.updateMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE, 1, 2);
        List<Match> result = scoreboard.getMatchesSummary();

        // Then
        assertEquals(result.get(0).getHomeTeamScore(), 1);
        assertEquals(result.get(0).getAwayTeamScore(), 2);
    }

    @Test
    public void testUpdateThrowingExceptionWhenMatchDoesNotExist() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.updateMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE, 1, 2));
    }
}
