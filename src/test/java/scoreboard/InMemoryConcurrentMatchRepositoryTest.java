package scoreboard;

import org.junit.jupiter.api.Test;
import repository.Repository;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryConcurrentMatchRepositoryTest {

    private final static String HOME_TEAM_ONE = "Legia Warszawa";
    private final static String AWAY_TEAM_ONE = "Lech Poznań";
    private final static String HOME_TEAM_TWO = "Zagłębie Lubin";
    private final static String AWAY_TEAM_TWO = "Pogoń Grodzisk";

    private final static String SAMPLE_KEY = "sampleKey";
    private final static String SAMPLE_KEY_TWO = "otherKey";

    private final static MatchFactory MATCH_FACTORY = new MatchFactory(
            Clock.fixed(Instant.parse("2020-10-10T10:15:30.00Z"), ZoneOffset.UTC), 1);


    @Test
    public void testNewMatchIsSaved() {
        // Given
        Repository<String, Match> repository = new InMemoryConcurrentMatchRepository<>();
        Match match = MATCH_FACTORY.createMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);

        // When
        repository.save(SAMPLE_KEY, match);

        // Then
        assertFalse(Objects.isNull(repository.load(SAMPLE_KEY)));

    }

    @Test
    public void testNewMatchIsNotSavedWhenSameMatchExists() {
        // Given
        Repository<String, Match> repository = new InMemoryConcurrentMatchRepository<>();
        Match match = MATCH_FACTORY.createMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);

        // When
        repository.save(SAMPLE_KEY, match);

        // Then
        assertThrows(IllegalArgumentException.class, () -> repository.save(SAMPLE_KEY, match));
    }

    @Test
    public void testMatchIsLoaded() {
        // Given
        Repository<String, Match> repository = new InMemoryConcurrentMatchRepository<>();
        Match match = MATCH_FACTORY.createMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        repository.save(SAMPLE_KEY, match);

        // When
        Match loadedMatch = repository.load(SAMPLE_KEY);

        // Then
        assertEquals(match, loadedMatch);
    }

    @Test
    public void testMatchIsNotLoadedWhenItsNotPresentInRepository() {
        // Given
        Repository<String, Match> repository = new InMemoryConcurrentMatchRepository<>();
        Match match = MATCH_FACTORY.createMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> repository.load(SAMPLE_KEY));
    }

    @Test
    public void testMatchIsUpdated() {
        // Given
        Repository<String, Match> repository = new InMemoryConcurrentMatchRepository<>();
        Match match = MATCH_FACTORY.createMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        repository.save(SAMPLE_KEY, match);
        match.setAwayTeamScore(5);

        // When
        repository.update(SAMPLE_KEY, match);
        Match loadedMatch = repository.load(SAMPLE_KEY);

        // Then
        assertEquals(loadedMatch.getAwayTeamScore(), 5);
    }

    @Test
    public void testMatchIsNotUpdatedWhenMatchIsNotPresentInRepository() {
        // Given
        Repository<String, Match> repository = new InMemoryConcurrentMatchRepository<>();
        Match match = MATCH_FACTORY.createMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);


        // When & Then
        assertThrows(IllegalArgumentException.class, () -> repository.update(SAMPLE_KEY, match));
    }

    @Test
    public void testMatchIsRemovedFromRepository() {
        // Given
        Repository<String, Match> repository = new InMemoryConcurrentMatchRepository<>();
        Match match = MATCH_FACTORY.createMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        repository.save(SAMPLE_KEY, match);

        // When
        repository.remove(SAMPLE_KEY);

        // Then
        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    public void testMatchIsNotRemovedFromRepositoryWhenMatchIsNotPresentInRepository() {
        // Given
        Repository<String, Match> repository = new InMemoryConcurrentMatchRepository<>();
        Match match = MATCH_FACTORY.createMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> repository.remove(SAMPLE_KEY));
    }

    @Test
    public void testGetAllMatchesFromRepository() {
        // Given
        Repository<String, Match> repository = new InMemoryConcurrentMatchRepository<>();
        Match match1 = MATCH_FACTORY.createMatch(HOME_TEAM_ONE, AWAY_TEAM_ONE);
        Match match2 = MATCH_FACTORY.createMatch(HOME_TEAM_TWO, AWAY_TEAM_TWO);
        repository.save(SAMPLE_KEY, match1);
        repository.save(SAMPLE_KEY_TWO, match2);

        // When
        Collection<Match> matchList = repository.getAll();

        // Then
        assertEquals(2, matchList.size());
    }
}
