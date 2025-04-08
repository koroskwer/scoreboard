package scoreboard;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreboardMatchCreationTest {

    private final static String HOME_TEAM_ONE = "Legia Warszawa";
    private final static String AWAY_TEAM_ONE = "Lech Poznań";
    private final static String HOME_TEAM_TWO = "Zagłębie Lubin";
    private final static String AWAY_TEAM_TWO = "Pogoń Grodzisk";


    @Test
    public void testMatchBeingCreatedWithZeroZeroScore() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When
        scoreboard.startMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        List<Match> result = scoreboard.getMatchesSummary();

        // Then
        assertEquals(result.get(0).getHomeTeamScore(), 0);
        assertEquals(result.get(0).getAwayTeamScore(), 0);
    }

    @Test
    public void testMatchBeingCreatedWithTeamNames() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When
        scoreboard.startMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        List<Match> result = scoreboard.getMatchesSummary();

        // Then
        assertEquals(result.get(0).getHomeTeamName(), HOME_TEAM_ONE);
        assertEquals(result.get(0).getAwayTeamName(), AWAY_TEAM_ONE);
    }

    @Test
    public void testMatchNotAcceptingNullFirstTeamName() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When & Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(null, AWAY_TEAM_ONE));
    }

    @Test
    public void testMatchNotAcceptingNullSecondTeamName() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When & Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(HOME_TEAM_ONE, null));
    }

    @Test
    public void testMatchCreationNotAcceptingTwoMatchesWithSameTeams() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When & Then
        scoreboard.startMatch(HOME_TEAM_ONE, HOME_TEAM_TWO);
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(HOME_TEAM_ONE, HOME_TEAM_TWO));
    }

    @Test
    public void testMatchBeingCreatedWithCorrectTimestamp() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration(
                Clock.fixed(Instant.parse("2020-12-10T13:15:30.00Z"), ZoneOffset.UTC));

        // When
        scoreboard.startMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        List<Match> result = scoreboard.getMatchesSummary();

        // Then
        assertEquals(result.get(0).getCreationTime().getYear(), 2020);
        assertEquals(result.get(0).getCreationTime().getMonth(), Month.DECEMBER);
        assertEquals(result.get(0).getCreationTime().getDayOfMonth(), 10);
        assertEquals(result.get(0).getCreationTime().getHour(), 13);
        assertEquals(result.get(0).getCreationTime().getMinute(), 15);
        assertEquals(result.get(0).getCreationTime().getSecond(), 30);
    }

    @Test
    public void testMultipleMatchesBeingCreatedWithIncrementingIds() {
        // Given
        Scoreboard scoreboard = ScoreboardConfiguration.createTestConfiguration();

        // When
        scoreboard.startMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        scoreboard.startMatch(HOME_TEAM_TWO, AWAY_TEAM_TWO);
        scoreboard.startMatch(HOME_TEAM_TWO, AWAY_TEAM_ONE);
        List<Match> result = scoreboard.getMatchesSummary();

        // Then
        assertEquals(result.get(0).getId(), 3);
        assertEquals(result.get(1).getId(), 2);
        assertEquals(result.get(2).getId(), 1);
    }
}
