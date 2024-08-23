package me.func.section;

import java.util.*;

public class UnsafeUniqueSet<T> implements UniqueSet<T> {

    private final Map<T, Integer> counts = new HashMap<>();
    private final Set<T> uniqueElements = new HashSet<>();

    public void add(T object) {
        if (counts.containsKey(object)) {
            counts.put(object, counts.get(object) + 1);
            uniqueElements.remove(object);
            return;
        }

        counts.put(object, 1);
        uniqueElements.add(object);
    }

    public T getUnique() {
        if (uniqueElements.isEmpty()) {
            return null;
        }
        return uniqueElements.iterator().next();
    }

    public void remove(T object) {
        if (!counts.containsKey(object)) {
            return;
        }

        int count = counts.get(object) - 1;

        if (count == 0) {
            counts.remove(object);
            uniqueElements.remove(object);
            return;
        }

        counts.put(object, count);

        if (count == 1) {
            uniqueElements.add(object);
        }
    }

}
