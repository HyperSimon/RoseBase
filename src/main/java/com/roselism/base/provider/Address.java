package com.roselism.base.provider;

/**
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/20 12:21
 * @packageName: com.roselism.base.provider
 */
public class Address {
    public String id = "id";

    public static class Data1 extends Address {
        public static String TABLE = "data1";

        /**
         *
         */
        public static String id = "id";


        /**
         *
         */
        public static String t_id = TABLE + ".id";

        /**
         * 外键列
         */
        public static String outkey = "outkey";


        public static String t_outkey = wrapTable(outkey);

        public static String wrapTable(String colName) {
            return TABLE + "." + colName;
        }

    }

    public static class Data2 {
        public static String TABLE = "data2";

        /**
         * id
         */
        public static String id = "id";

        /**
         *
         */
        public static String t_id = wrapTable(id);

        /**
         * 位置
         */
        public static String location = "location";

        /**
         * 位置
         */
        public static String t_location = wrapTable(location);

        /**
         * 所在区域
         */
        public static String area = "area";


        /**
         * 带有表名的列
         */
        public static String t_area = wrapTable(area);


        public static String wrapTable(String colName) {
            return TABLE + "." + colName;
        }
    }
}
