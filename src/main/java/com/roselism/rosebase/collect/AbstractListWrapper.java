package com.roselism.rosebase.collect;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * AbstractList的包装者
 *
 * @param <E>
 */
public class AbstractListWrapper<E> extends AbstractList<E> implements Cloneable {
    protected final List<E> backingList;

    public AbstractListWrapper(List<E> backingList) {
        this.backingList = checkNotNull(backingList);
    }

    @Override
    public void add(int index, E element) {
        backingList.add(index, element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return backingList.addAll(index, c);
    }

    @Override
    public E get(int index) {
        return backingList.get(index);
    }

    @Override
    public E remove(int index) {
        return backingList.remove(index);
    }

    @Override
    public E set(int index, E element) {
        return backingList.set(index, element);
    }

    @Override
    public boolean contains(Object o) {
        return backingList.contains(o);
    }

    @Override
    public int size() {
        return backingList.size();
    }
}
