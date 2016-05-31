package com.roselism.base.util;

import android.support.annotation.NonNull;

import com.roselism.base.collect.RoseList;
import com.roselism.base.util.function.Predicate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by simon on 16-5-24.
 */
public class RoseListTest {

    /**
     * 测试下标
     *
     * @throws Exception
     */
    @Test
    public void testIndexOf() throws Exception {

        // 初始化集合
        final List<String> list = new ArrayList<>();
        for (int i = 6; i <= 10; i++) {
            list.add(i + "");
        }

        RoseList<String> filter = new RoseList<>(list);

        // 遍历集合 查询是否与下标对应
        for (int i = 0; i < list.size(); i++) {
            final int finalI = i;
            int index = filter.indexOf(new Predicate<String>() {
                @Override
                public boolean test(String s) {
                    return s.equals(list.get(finalI) + "");
                }
            });
            assertEquals(i, index);
        }
    }


    /**
     * 测试原集合大小
     *
     * @throws Exception
     */
    @Test
    public void testFilter() throws Exception {
        testFilter_mataDataSize();
        testFilter_filterCount();
        testFilter_sizeAfterFiler();
        testFilter_subList();
    }

    /**
     * 测试原集合大小
     *
     * @throws Exception
     */
    @Test
    public void testFilter_mataDataSize() throws Exception {
        List<String> list = getStrings();
//        RoseList<String> filterList = new RoseList<>(list);

        assertEquals(10, list.size()); //测试原集合数据是否可改变
    }

    /**
     * 测试包装者的集合的长度
     *
     * @throws Exception
     */
    @Test
    public void testFilter_subList() throws Exception {
        List<String> list = getStrings();
        RoseList<String> filterList = new RoseList<>(list);

        assertEquals(10, filterList.size()); //测试原集合数据是否可改变
    }


    /**
     * 测试过滤掉的数量
     *
     * @throws Exception
     */
    @Test
    public void testFilter_filterCount() throws Exception {
        List<String> list = getStrings();

        RoseList<String> filterList = new RoseList<>(list);
        int count = filterList.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return ((5 + "").equals(s));
            }
        });
        assertEquals(1, count); //测试原集合数据是否可改变
    }


    /**
     * 测试过滤之后的列表的大小
     *
     * @throws Exception
     */
    @Test
    public void testFilter_sizeAfterFiler() throws Exception {
        List<String> list = getStrings();

        RoseList<String> filterList = new RoseList<>(list);
        filterList.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return ((5 + "").equals(s));
            }
        });

        assertEquals(9, filterList.size()); // 测试filter集合数据是否移除
    }

    @NonNull
    private List<String> getStrings() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        return list;
    }
}
