package edu.project2.Generator.KruskalGenerator;

import java.util.HashMap;

public class DSU<T> {
    HashMap<T, T> parent = new HashMap<>();

    T find(T item) {
        if (!parent.containsKey(item) || parent.get(item).equals(item)) {
            return item;
        }
        T answer = find(parent.get(item));
        parent.put(item, answer);
        return answer;
    }

    void unite(T item1, T item2) {
        T item11 = find(item1);
        T item22 = find(item2);
        parent.put(item11, item22);
    }
}
