package csvfilepersistence;

import java.util.List;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public interface DAO<K,E> {
    
    E get(K k);
    void save(E e);
    void delete(K k);
    List<E> getAll();
    int size();
}
