package Map;

import data_structures.LinkedList;

public interface Map<K, V> {

    void insert(K key, V value);

    void remove(K key);

    RBTreeMap.Node<K, V> find(K key);

    boolean containsKey(K key);

    boolean isEmpty();

    int getSize();

    void clear();

    LinkedList<K> getKeys();

    LinkedList<V> getValues();

    /**
     * Выводит обход в глубину
     */
    void printMap();
}
