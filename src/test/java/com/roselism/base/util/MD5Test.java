package com.roselism.base.util;

import org.junit.Test;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by simon on 16-5-28.
 */
public class MD5Test {

    @Test
    public void getMd5_test() {
        InputStream inputStream = new StringBufferInputStream("asdfasdf");
        assertEquals("6a204bd89f3c8348afd5c77c717a907a", MD5.getMd5(inputStream));
    }
}