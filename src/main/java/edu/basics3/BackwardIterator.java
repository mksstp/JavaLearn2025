package edu.basics3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {

    private final List<T> list;
    private int pointer;

    public BackwardIterator(Collection<T> collection) {
        this.pointer = collection.size() - 1;
        this.list = collection.stream().toList();
    }

    @Override
    public boolean hasNext() {
        return pointer >= 0;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Выход за пределы коллекции!");
        }
        return this.list.get(pointer--);
    }
}
