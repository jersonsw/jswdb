package io.inouty.jswdb.main.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatesUtil.class);
    private static final String defaultFormat = "yyyy-MM-dd";

    public static Date strToDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            return getFormatter(defaultFormat).parse(str);
        } catch (ParseException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Error trying to parse '{}' to a date", str);
            }
        }
        return null;
    }

    private static SimpleDateFormat getFormatter(String format) {
        return new SimpleDateFormat(format);
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyy-MM-dd");
    }

    public static String dateToStr(Date date, String format) {
        if (date == null) {
            return null;
        }
        return getFormatter(format).format(date);
    }


}
