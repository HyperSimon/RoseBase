package com.roselism.base.collect;

import com.roselism.base.util.convert.Converter;
import com.roselism.base.util.function.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 集合增强操作的总汇
 * <p>
 * Created by simon on 16-5-26.
 */
public class RoseList<E> extends AbstractListWrapper<E> {
    public RoseList(List<E> backingList) {
        super(backingList);
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

//        RoseList roseList = null;
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


    /**
     * 可直接转换的List集合
     * Created by simon on 16-5-25.
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


    /**
     * 专门用于筛选的集合
     * 比如查询某个符合条件的元素的下标
     * 过滤掉所有符合条件的元素
     * Created by simon on 16-5-23.
     */
    public class FilterList<E> extends AbstractListWrapper<E> {
        public FilterList(List<E> backingList) {
            super(backingList);
        }

        /**
         * 过滤掉符合符合条件的元素
         * 不改变原来的列表，返回一个新的过滤后的列表
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


}
