package scoreboard;

import datastructures.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryConcurrentMatchRepository<String, Match> implements Repository<String, Match> {

    private final Map<String, Match> map = new ConcurrentHashMap<>();

    @Override
    public void save(String key, Match content) {
        map.put(key, content);
    }

    @Override
    public Match load(String key, Match content) {
        return null;
    }

    @Override
    public Collection<Match> getAll() {
        return List.of();
    }
}
