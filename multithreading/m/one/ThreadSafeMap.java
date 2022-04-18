import java.util.Map;

public class ThreadSafeMap<K, V> {

    private volatile Map<K, V> map;

    public ThreadSafeMap(Map<K, V> map) {
        this.map = map;
    }

    public synchronized void put(K key, V value) {
        System.out.println("put");
        this.map.put(key, value);
    }

    public void putNotSynchronized(K key, V value) {
        this.map.put(key, value);
    }

    public synchronized V get(K key) {
        System.out.println(this);
        return map.get(key);
    }

    public V getNotSynchronized(K key) {
        return map.get(key);
    }

    public synchronized java.util.Set<Map.Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
