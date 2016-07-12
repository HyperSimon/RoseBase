package com.roselism.rosebase.collect;

import com.roselism.rosebase.util.convert.Converter;
import com.roselism.rosebase.util.function.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 集合增强操作的总汇
 * <p/>
 * Created by simon on 16-5-26.
 */
public class RoseList<E> extends AbstractListWrapper<E> {
    public RoseList(List<E> metaList) {
        super(metaList);
    }

    /**
     * 将一组元素按照特定的转换形式转换成另一种类型并存储到列表中
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

    /**
     * 合并另一个集合 collection, 在当前集合的末尾处追加 collection的元素,
     * 只会添加 在没有在当前的集合中没有的元素
     * 添加所有元素请使用 <code>addAll</code>方法
     *
     * @param collection
     */
    private void merge(Collection<E> collection) {
        // TODO: 16-6-9  
    }

    /**
     * 将当前类型的列表根据具体的转换规则映射成为一个别的类型的数组
     *
     * @param converter 具体转换规则
     * @param <R>       元素的目标类型
     * @return 返回映射后的数组
     */
    public <R> List<R> mapping(Converter<E, R> converter) {
        RoseList<R> roseList = new RoseList<>(new ArrayList<R>());
        roseList.addAll(backingList, converter);
        return roseList;
    }

    /**
     * 过滤掉列表中所有符合特定条件的元素
     * 当filter的返回值为true时，该对应元素就会被移除
     *
     * @param filter 过滤器 过滤条件
     * @return 返回移除的元素的数量
     */
    public int filter(Predicate<E> filter) {
        List<E> removeEle = new ArrayList<>(backingList.size());
        int count = 0;
        for (E e : backingList) {
            if (filter.test(e)) {
                removeEle.add(e);
                count++;
            }
        }
        backingList.removeAll(removeEle);
        return count;
    }

    /**
     * 获得符合mattcher的第一个元素的下标
     * 下标将从0开始计算
     *
     * @param mattcher 匹配器
     * @return 返回与mattcher匹配的第一个元素的下标，如果集合中没有匹配项，则返回-1
     */
    public int indexOf(Predicate<E> mattcher) {
        int index = 0;
        for (E e : backingList) {
            if (mattcher.test(e)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
