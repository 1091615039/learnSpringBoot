package com.tianmaying.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class StringUtils {
    
    public static String transform(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }
    
    public static String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md5=MessageDigest.getInstance("MD5");
        return Base64.getEncoder().encodeToString(md5.digest(str.getBytes()));
    }
    
    public static String encodeByBase64(String str) throws UnsupportedEncodingException {
        return Base64.getMimeEncoder().encodeToString(str.getBytes());
    }
    
    public static String decodeByBase64(String str) throws UnsupportedEncodingException {
        return new String(Base64.getMimeDecoder().decode(str.getBytes()));
    }
}
