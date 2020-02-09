package io.inouty.jswdb.main.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumbersUtil {

    public static BigDecimal safeDivide(BigDecimal dividend, BigDecimal divisor){
        if (divisor != null  && divisor.doubleValue() > 0 && dividend != null) {
            return dividend.divide(divisor,4, RoundingMode.HALF_UP);
        }

        return BigDecimal.ZERO;
    }


}
