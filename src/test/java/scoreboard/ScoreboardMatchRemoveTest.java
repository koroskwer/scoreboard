package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScoreboardMatchRemoveTest {

    private final static String HOME_TEAM_ONE = "Legia Warszawa";
    private final static String AWAY_TEAM_ONE = "Lech Poznań";

    @Test
    public void testFinishMatchRemovesTheMatchFromSummary() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When
        scoreboard.startMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        scoreboard.finishMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        List<Match> result = scoreboard.getMatchesSummary();

        // Then
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testFinishMatchThrowsErrorIfMatchIsNotPresentInRepository() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When & Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.finishMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE));
    }
}
