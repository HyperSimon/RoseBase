package com.roselism.base.collect;

import com.roselism.base.util.convert.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 可直接转换的List集合
 * Created by simon on 16-5-25.
 *
 * @deprecated 已经弃用
 */
public class ConverterList<E> extends AbstractListWrapper<E> {
    public ConverterList(List<E> backingList) {
        super(backingList);
    }

    /**
     * 将一组元素按照特定的转换形式转换成另一种类型
     * 将T类型数据转换成E类型并存储
     *
     * @param collection 原类型
     * @param converter  转换器
     * @param <T>        原类的类型
     */
    public <T> void addAll(Collection<? extends T> collection, Converter<T, E> converter) {
        List<E> target = new ArrayList<>();
        for (T t : collection) {
            target.add(converter.convert(t));
        }
        addAll(target);
    }
}
