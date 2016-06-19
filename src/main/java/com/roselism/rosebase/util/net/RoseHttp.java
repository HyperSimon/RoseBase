package com.roselism.rosebase.util.net;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;


import com.roselism.rosebase.util.convert.InStream2String;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by simon on 2016/4/26.
 * <note>该工具类还在定型阶段，所以不建议使用</note>
 *
 * @version 1.3
 * @deprecated 使用Rosen代替
 */
public class RoseHttp {

    /**
     * 网络请求的方式
     */
    public static final String POST_METHOD = "POST";

    /**
     * 网络连接get方式
     */
    public static final String GET_METHOD = "GET";

    public static final Builder DEFAULT_BUILDER = null; // 默认配置
    private static final String TAG = "RoseHttp";
    private static final boolean DEBUG = true;
    private HttpURLConnection mConnection;
    private Builder mBuilder;

    /**
     * 建造者模式
     * 私有构造器，只能通过builder创建helper对象
     *
     * @param builder 构造器
     * @// FIXME: 16-6-13 当前的设计不足的地方,在创建爱呢RoseHttp的时候就需要确认url,这个设计就很不好，因为可以先进行一系列的配置，然后在真正请求的时候在传递url地址
     * @since 1.1 修复了构造器的bug
     * <p/>
     */
    private RoseHttp(Builder builder) {
        mBuilder = builder;
    }

    public final static RoseHttp newInstance(Builder builder) {
        return new RoseHttp(builder);
    }

    /**
     * @param builder null 将会采用默认配置
     * @since 1.2
     */
    private void openConnection(@Nullable Builder builder) {
        try {
            URL url = new URL(builder.path);

            mConnection = (HttpURLConnection) url.openConnection();

            mConnection.setRequestMethod((builder != null && builder.requestMethod != null) ? builder.requestMethod : POST_METHOD);
            mConnection.setReadTimeout(builder != null && builder.readTimeOut != 0 ? builder.readTimeOut : 5000);
            mConnection.setConnectTimeout(builder != null && builder.connectionTimeOut != 0 ? builder.connectionTimeOut : 5000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据特定的url和builder的配置创建connection
     *
     * @param urlPath
     * @param builder
     * @since 1.3
     */
    private void openConnection(@NonNull String urlPath, @Nullable Builder builder) {
        try {
            URL url = new URL(urlPath);

            mConnection = (HttpURLConnection) url.openConnection();
            mConnection.setRequestMethod(builder != null && builder.requestMethod != null ? builder.requestMethod : POST_METHOD);
            mConnection.setReadTimeout(builder != null && builder.readTimeOut != 0 ? builder.readTimeOut : 5000);
            mConnection.setConnectTimeout(builder != null && builder.connectionTimeOut != 0 ? builder.connectionTimeOut : 5000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送请求
     *
     * @param url
     * @param callBack
     * @since 1.3
     */
    @WorkerThread
    public void post(@NonNull final String url, final DataRequester.ResultCallBack<InputStream> callBack) {

        if (DEBUG)
            Log.d(TAG, "post() called with: " + "url = [" + url + "]");

        new Thread(new Runnable() {
            @Override
            public void run() {
                openConnection(url, DEFAULT_BUILDER); // 打开连接
                try {
                    InputStream inputStream = mConnection.getInputStream();
                    callBack.onResult(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.onResult(null);
                } finally {
                    mConnection.disconnect();
                }
            }
        }).start();
    }

    /**
     * 发送请求
     *
     * @param url
     * @param callBack
     * @since 1.3
     */
    @WorkerThread
    public void get(final String url, final DataRequester.ResultCallBack<InputStream> callBack, final OnErrorListener onErrorListener) {

        if (DEBUG)
            Log.d(TAG, "post() called with: " + "url = [" + url + "]");

        new Thread(new Runnable() {
            @Override
            public void run() {
                openConnection(url, DEFAULT_BUILDER); // 打开连接
                try {
                    InputStream inputStream = mConnection.getInputStream();
                    callBack.onResult(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                    onErrorListener.onError(e);
                } finally {
                    if (mConnection != null)
                        mConnection.disconnect();
                }
            }
        }).start();
    }

    /**
     * 发送请求 获取json格式数据
     *
     * @param url
     * @param callBack
     * @since 1.3
     */
    @WorkerThread
    public void getString(final String url, final DataRequester.ResultCallBack<String> callBack, final OnErrorListener onErrorListener) {

        // TODO: 16-6-15 完成本方法

        if (DEBUG)
            Log.d(TAG, "post() called with: " + "url = [" + url + "]");

        new Thread(new Runnable() {
            @Override
            public void run() {
                openConnection(url, DEFAULT_BUILDER); // 打开连接
                try {
                    InputStream inputStream = mConnection.getInputStream();
                    InStream2String converter = new InStream2String();
                    String json = converter.convert(inputStream);
                    callBack.onResult(json);
                } catch (IOException e) {
                    e.printStackTrace();
                    onErrorListener.onError(e);
                } finally {
                    if (mConnection != null)
                        mConnection.disconnect();
                }
            }
        }).start();
    }

    /**
     * @return 返回<code>HttpURLConnection</code>对象
     * @throws IOException
     * @since 1.0
     * @deprecated 不再推荐使用
     */
    public HttpURLConnection getConnection() throws IOException {
        return mConnection;
    }

    /**
     * 获取输入流
     *
     * @return
     * @throws IOException
     * @deprecated 不再使用 使用 post& get 方法代替
     */
    public InputStream getInpuStream() throws IOException {
        return mConnection.getInputStream();
    }

    /**
     * 获取响应码
     *
     * @return
     * @throws IOException
     */
    private int responseCode() throws IOException {
        if (mConnection == null) throw new IOException();
        return mConnection.getResponseCode();
    }

    /**
     * 是否响应ok（响应码是否为200）
     *
     * @return 如果响应码为200则返回true，反之返回false
     * @throws IOException
     * @deprecated 不再推荐使用
     */
    public boolean isResponseOk() throws IOException {
        if (mConnection == null) {
            openConnection(mBuilder);
        }
        return responseCode() == 200;
    }

    public interface OnErrorListener {
        void onError(Throwable throwable);
    }

    /**
     * 建造者
     *
     * @since 1.0
     * @deprecated 不再推荐使用
     */
    public static class Builder {

        @Deprecated
        public String path;
        public String requestMethod; // 请求方法
        public boolean useCatches; // 使用缓存
        public int connectionTimeOut = 0; // 连接超时
        public int readTimeOut = 0;

        /**
         * 设置网络路径
         *
         * @param path
         * @return
         * @deprecated 不再使用
         */
        public Builder setPath(String path) {
            if (path == null || path.isEmpty())
                throw new IllegalArgumentException();

            this.path = path;
            return this;
        }

        public Builder setRequestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public Builder setUseCatches(boolean useCatches) {
            this.useCatches = useCatches;
            return this;
        }

        public Builder setConnectionTimeOut(int connectionTimeOut) {
            this.connectionTimeOut = connectionTimeOut;
            return this;
        }

        public Builder setReadTimeOut(int readTimeOut) {
            this.readTimeOut = readTimeOut;
            return this;
        }

        public RoseHttp build() {
            return new RoseHttp(this);
        }
    }
}
