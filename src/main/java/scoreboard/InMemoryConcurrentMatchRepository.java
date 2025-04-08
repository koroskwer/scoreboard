package scoreboard;

import repository.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Implementation of in memory repository for Matches. Matches are stored in key-value store, backed by ConcurrentHashMap
 *
 * @param <String> Key used to retrieve Matches
 * @param <Match>  Sports Match
 */
class InMemoryConcurrentMatchRepository<String, Match> implements Repository<String, Match> {

    private final Map<String, Match> map;

    InMemoryConcurrentMatchRepository() {
        this.map = new ConcurrentHashMap<>();
    }

    @Override
    public void save(String key, Match content) {
        if (this.map.containsKey(key)) {
            throw new IllegalArgumentException("Match with the key %s is already present in the repository.".formatted(key));
        }
        this.map.put(key, content);
    }

    @Override
    public Match load(String key) {
        Match match = this.map.get(key);
        if (match == null) {
            throw new IllegalArgumentException("Match with the key %s is not present in the repository.".formatted(key));
        }
        return match;
    }

    @Override
    public void update(String key, Match content) {
        if (this.map.containsKey(key)) {
            this.map.put(key, content);
        } else {
            throw new IllegalArgumentException("Match with the key %s is not present in the repository.".formatted(key));
        }
    }

    @Override
    public void remove(String key) {
        if (this.map.containsKey(key)) {
            this.map.remove(key);
        } else {
            // TODO - refactor to log a WARN instead
            throw new IllegalArgumentException("Match with the key %s is not present in the repository.".formatted(key));
        }
    }

    @Override
    public Collection<Match> getAll() {
        return map.values();
    }
}
