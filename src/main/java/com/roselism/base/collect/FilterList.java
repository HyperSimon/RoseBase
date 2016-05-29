package com.roselism.base.collect;

import com.roselism.base.util.function.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * 专门用于筛选的集合
 * 比如查询某个符合条件的元素的下标
 * 过滤掉所有符合条件的元素
 * Created by simon on 16-5-23.
 *
 * @deprecated 已经弃用
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
