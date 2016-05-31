package com.roselism.base.util.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by simon on 2016/4/26.
 *
 * @version 1.1
 *          修复了构造器函数的bug
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
    private static final String TAG = "RoseHttp";

    HttpURLConnection mConnection;

    /**
     * 建造者模式
     * 私有构造器，只能通过builder创建helper对象
     *
     * @param builder 构造器
     * @since 1.1 修复了构造器的bug
     */
    private RoseHttp(Builder builder) {

        try {
            URL url = new URL(builder.path);
            mConnection = (HttpURLConnection) url.openConnection();

            mConnection.setRequestMethod(builder.requestMethod != null ? builder.requestMethod : POST_METHOD);
            mConnection.setReadTimeout(builder.readTimeOut != 0 ? builder.readTimeOut : 5000);
            mConnection.setConnectTimeout(builder.connectionTimeOut != 0 ? builder.connectionTimeOut : 5000);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
//        Log.i(TAG, "getresponsecode: ");

        if (mConnection == null) throw new IOException();

//        Log.i(TAG, "responseCode: --> " + mConnection.getResponseCode());
        return mConnection.getResponseCode();
    }

    /**
     * 是否响应ok（响应码是否为200）
     *
     * @return 如果响应码为200则返回true，反之返回false
     * @throws IOException
     */
    public boolean isResponseOk() throws IOException {
        return responseCode() == 200;
    }

    /**
     * 建造者
     *
     * @since 1.0
     */
    public static class Builder {
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