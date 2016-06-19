package com.roselism.rosebase.util.convert;

/**
 * Created by simon on 2016/4/26.
 */
public interface Converter<P, R> {
    R convert(P parameter);
}