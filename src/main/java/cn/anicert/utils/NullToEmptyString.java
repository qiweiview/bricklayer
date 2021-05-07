package cn.anicert.utils;

public class NullToEmptyString {
    public static String handle(String s) {
        if (s == null) {
            return "";

        }
        return s;
    }
}
