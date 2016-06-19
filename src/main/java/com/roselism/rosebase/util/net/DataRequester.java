package com.roselism.rosebase.util.net;

/**
 * 数据请求者
 * 与RoseHttp搭配使用有神奇效果
 *
 * @param <T> 返回的数据类型
 * @deprecated 使用Rosen代替
 */
public class DataRequester<T> {
    private Stragegy mStragegy;

    public void setStragegy(Stragegy mStragegy) {
        this.mStragegy = mStragegy;
    }

    public void getData(ResultCallBack<T> callBack) {
        mStragegy.request(callBack);
    }

    public interface Stragegy<T> {
        void request(ResultCallBack<T> callBack);
    }

    public interface ResultCallBack<T> {
        void onResult(T t);
    }
}
