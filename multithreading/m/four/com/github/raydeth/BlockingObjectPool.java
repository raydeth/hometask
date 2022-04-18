package com.github.raydeth;

public class BlockingObjectPool {

    private volatile boolean isBlocked = false;
    private volatile int size = 0;

    private final Object[] pool;

    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        this.pool = new Object[size];
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public Object get() {
        synchronized (this) {
            if (size == 0) {
                this.isBlocked = true;
            }
            if (this.isBlocked) {
                throw new RuntimeException("Blocked");
            }
            Object value = pool[size - 1];
            size--;
            return value;
        }
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void take(Object object) {
        synchronized (this) {
            if (this.isBlocked) {
                throw new RuntimeException("Blocked");
            }
            if (size == pool.length) {
                this.isBlocked = true;
                return;
            }
            pool[size] = object;
            size++;
        }
    }
}