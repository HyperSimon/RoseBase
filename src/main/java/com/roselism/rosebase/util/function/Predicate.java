package com.roselism.rosebase.util.function;


/**
 * 断言 断定
 * Created by simon on 16-5-24.
 * 谓语的，述语的
 *
 * @param <T> 参数类型
 */
public interface Predicate<T> {
    boolean test(T t);
}