package com.roselism.rosebase.database;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * 对于原有的数据库{@link Cursor}的功能的加强
 *
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/17 14:36
 * @packageName: com.roselism.mobilesafe24.model
 */
public class Cursors extends CursorWrapper {
    private Cursor mCursor;

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public Cursors(Cursor cursor) {
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
     * 根据列名获取blob
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
