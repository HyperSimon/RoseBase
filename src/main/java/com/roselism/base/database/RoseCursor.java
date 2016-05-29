package com.roselism.base.database;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * @author: Hyper Simon Wang(王大锤)
 * @create_time: 2016/05/17 14:36
 * @packageName: com.roselism.mobilesafe24.model
 */
public class RoseCursor extends CursorWrapper {
    private Cursor mCursor;

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public RoseCursor(Cursor cursor) {
        super(cursor);
        mCursor = cursor;
    }

    /**
     * 根据列名获取string
     *
     * @param columnName
     * @return
     */
    public String getString(String columnName) {
        return mCursor.getString(mCursor.getColumnIndex(columnName));
    }

    /**
     * 根据列名获取long值
     *
     * @param columnName
     * @return
     */
    public long getLong(String columnName) {
        return mCursor.getLong(mCursor.getColumnIndex(columnName));
    }


    /**
     * 根据列名获取double值
     *
     * @param columnName
     * @return
     */
    public double getDouble(String columnName) {
        return mCursor.getDouble(mCursor.getColumnIndex(columnName));
    }

    /**
     * 根据列明获取blob
     *
     * @param columnName
     * @return
     */
    public byte[] getBlob(String columnName) {
        return mCursor.getBlob(mCursor.getColumnIndex(columnName));
    }

    /**
     * 根据列名获取int
     *
     * @param columnName 列名
     * @return
     */
    public int getInt(String columnName) {
        return mCursor.getInt(mCursor.getColumnIndex(columnName));
    }
}
