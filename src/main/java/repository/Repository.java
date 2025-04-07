package repository;

import java.util.Collection;

public interface Repository<K, C> {

    void save(K key, C content);

    C load(K key);

    void update(K key, C content);

    void remove(K key);

    Collection<C> getAll();
}
