package com.roselism.rosebase.app;

/**
 * Created by simon on 16-7-5.
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
