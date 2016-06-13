package com.roselism.base.util.net;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.roselism.base.util.convert.Converter;
import com.roselism.base.util.convert.InStream2String;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by simon on 2016/4/26.
 *
 * @version 1.2
 *          允许静态工厂chungking
 *          添加默认Builder
 *          添加post方法， 简化了网络请求
 *          重构了之前的构造器，抽取出 openConnection 方法
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
    HttpURLConnection mConnection;

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

            mConnection.setRequestMethod(builder.requestMethod != null ? builder.requestMethod : POST_METHOD);
            mConnection.setReadTimeout(builder.readTimeOut != 0 ? builder.readTimeOut : 5000);
            mConnection.setConnectTimeout(builder.connectionTimeOut != 0 ? builder.connectionTimeOut : 5000);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据特定的url和builder的配置创建connection
     *
     * @param urlPath
     * @param builder
     */
    private void openConnection(String urlPath, Builder builder) {
        try {
            URL url = new URL(urlPath);

            mConnection = (HttpURLConnection) url.openConnection();
            mConnection.setRequestMethod(builder.requestMethod != null ? builder.requestMethod : POST_METHOD);
            mConnection.setReadTimeout(builder.readTimeOut != 0 ? builder.readTimeOut : 5000);
            mConnection.setConnectTimeout(builder.connectionTimeOut != 0 ? builder.connectionTimeOut : 5000);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送请求
     *
     * @param url
     * @param callBack
     */
    @WorkerThread
    public void post(final String url, final DataRequester.ResultCallBack<InputStream> callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                openConnection(url, DEFAULT_BUILDER); // 打开连接
                try {
                    InputStream inputStream = mConnection.getInputStream();
                    callBack.onResult(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * @return 返回<code>HttpURLConnection</code>对象
     * @throws IOException
     * @since 1.0
     */
    public HttpURLConnection getConnection() throws IOException {
        return mConnection;
    }

    /**
     * 获取输入流
     *
     * @return
     * @throws IOException
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
    public int responseCode() throws IOException {
        if (mConnection == null) throw new IOException();
        return mConnection.getResponseCode();
    }

    /**
     * 是否响应ok（响应码是否为200）
     *
     * @return 如果响应码为200则返回true，反之返回false
     * @throws IOException
     */
    public boolean isResponseOk() throws IOException {
        if (mConnection == null) {
            openConnection(mBuilder);
        }
        return responseCode() == 200;
    }

    /**
     * 建造者
     *
     * @since 1.0
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
