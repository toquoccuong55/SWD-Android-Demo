package com.shoesshop.groupassignment.utils;

import java.text.DecimalFormat;

public class CurrencyManager {
    public static String getPrice(double price, String currency) {

        String format = "###,###,###";

        if (price % 1 != 0) {
            format = "###,###,###";
        }

        DecimalFormat dfSing = new DecimalFormat(format);

        String result = dfSing.format(price);

        result = result.replaceAll(",", ".");

        return result + currency;
    }
}
