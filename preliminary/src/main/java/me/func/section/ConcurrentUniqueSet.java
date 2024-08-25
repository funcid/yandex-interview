package me.func.section;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.*;

public class ConcurrentUniqueSet<T> implements UniqueSet<T> {

    private final Map<T, Integer> counts = new ConcurrentHashMap<>();
    private final Set<T> uniqueElements = new ConcurrentSkipListSet<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(T object) {
        lock.writeLock().lock();
        try {
            if (counts.containsKey(object)) {
                counts.put(object, counts.get(object) + 1);
                uniqueElements.remove(object);
            } else {
                counts.put(object, 1);
                uniqueElements.add(object);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public T getUnique() {
        lock.readLock().lock();
        try {
            if (uniqueElements.isEmpty()) {
                return null;
            }
            return uniqueElements.iterator().next();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void remove(T object) {
        lock.writeLock().lock();
        try {
            if (!counts.containsKey(object)) {
                return;
            }

            int count = counts.get(object) - 1;

            if (count == 0) {
                counts.remove(object);
                uniqueElements.remove(object);
            } else {
                counts.put(object, count);
                if (count == 1) {
                    uniqueElements.add(object);
                } else {
                    uniqueElements.remove(object);
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
}
