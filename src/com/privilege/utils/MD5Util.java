package com.privilege.utils;


import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Util {
    public static String MD5Encoding(String name) {
        //初始化MD5
        MessageDigest md5 = null;
        String encode = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            //创建base64位编码格式
            BASE64Encoder base64Encoder = new BASE64Encoder();
            //加密
            encode = base64Encoder.encode(md5.digest(name.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encode;
    }
}
