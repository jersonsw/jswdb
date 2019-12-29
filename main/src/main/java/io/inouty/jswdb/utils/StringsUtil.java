package io.inouty.jswdb.utils;



public class StringsUtil {

    public static final String DOUBLE_QUOTE = "\"";
    public static final String SINGLE_QUOTE = "\'";

    public static String coalesce(Object value, String defaultValue) {
        return (isNullOrEmpty(value) ? defaultValue : value.toString());
    }

    public static boolean isNullOrEmpty(Object value) {
        return (value == null) || value.toString().trim().isEmpty();
    }


}
