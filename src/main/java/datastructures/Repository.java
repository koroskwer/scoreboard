package datastructures;

import java.util.Collection;

public interface Repository<K, C> {

    void save(K key, C content);

    C load(K key, C content);

    Collection<C> getAll();
}
