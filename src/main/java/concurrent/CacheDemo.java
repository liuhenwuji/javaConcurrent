package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {
    private Map<String, Object> cache = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

    }

    public Object getData(String key) {
        readWriteLock.readLock().lock();
        Object value;
        try {
            value = cache.get(key);
            if (value == null) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    if (value == null) {
                        value = "aa"; //去查数据库
                    }
                } finally {
                    readWriteLock.writeLock().unlock();
                }
                readWriteLock.readLock().lock();
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

        return value;
    }
}
