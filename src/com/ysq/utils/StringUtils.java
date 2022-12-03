package com.ysq.utils;

public class StringUtils {
    private StringUtils() {
    }

    public static String parseArrayJSON(String[] s) {
        StringBuilder str = new StringBuilder();
        boolean begin = true;
        str.append('[');
        for(String c : s) {
            if(begin) begin = !begin;
            else str.append(',');
            str.append('\"'+c+'\"');
        }
        str.append(']');
        return new String(str);
    }

    public static String ObjectToJOSN(String ...o) {
        StringBuilder str = new StringBuilder();
        boolean begin = true;
        str.append('{');
        for(String c : o) {
            if(begin) begin = !begin;
            else str.append(',');
            str.append(c);
        }
        str.append('}');
        return new String(str);
    }
}
