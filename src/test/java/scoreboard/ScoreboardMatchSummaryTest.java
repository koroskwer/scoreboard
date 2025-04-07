package scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreboardMatchSummaryTest {
    private final static String HOME_TEAM_ONE = "Legia Warszawa";
    private final static String AWAY_TEAM_ONE = "Lech Poznań";
    private final static String HOME_TEAM_TWO = "Zagłębie Lubin";
    private final static String AWAY_TEAM_TWO = "Pogoń Grodzisk";


    @Test
    public void testSummaryGettingAllMatches() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When
        scoreboard.startMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        scoreboard.startMatch(HOME_TEAM_TWO, AWAY_TEAM_TWO);
        List<Match> result = scoreboard.getMatchesSummary();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    public void testSummaryGettingMatchesInDescendingOrderOfCreationIfGoalsScoredAreEqual() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When
        scoreboard.startMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        scoreboard.startMatch(HOME_TEAM_TWO, AWAY_TEAM_TWO);
        List<Match> result = scoreboard.getMatchesSummary();

        // Then
        assertTrue(result.get(0).getId() > result.get(1).getId());
    }

    @Test
    public void testSummaryGettingMatchesInDescendingOrderOfGoalsIfGoalsAreNotEqual() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When
        scoreboard.startMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        scoreboard.startMatch(HOME_TEAM_TWO, AWAY_TEAM_TWO);

        scoreboard.updateMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE, 1, 0);
        List<Match> result = scoreboard.getMatchesSummary();

        // Then
        assertTrue(result.get(0).getId() < result.get(1).getId());
        assertEquals(1, result.get(0).getHomeTeamScore());
    }
}
