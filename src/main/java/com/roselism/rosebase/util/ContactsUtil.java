package com.roselism.rosebase.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author: Hyper Simon Wang
 * @create_time: 2016/05/15 19:48
 * @packageName: com.roselism.mobilesafe24.util
 */
public class ContactsUtil {

    public static Cursor checkout(Context context) {
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        ContentResolver resolver = context.getContentResolver();

        return resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null);
    }

    /**
     * 根据uri获取照片
     *
     * @param context
     * @param photoUri
     * @return
     */
    public static InputStream getPhoto(Context context, Uri photoUri) {
        ContentResolver resolver = context.getContentResolver();

        try {
            return resolver.openInputStream(photoUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
