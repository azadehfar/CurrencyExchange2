package com.adel.currencyexchange2;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Gen {

    public static String GeneralTextForn = "mysans.ttf";

    static long currency_Cache = 4200;
    static String currency_from_Cache, currency_to_Cache;

    public static String getFormatedAmount(String amount) {
        try {
            //    String pattern = "####,####";
            //    DecimalFormat decimalFormat = new DecimalFormat(pattern);
            //   String number = decimalFormat.format(Double.parseDouble(amount));
            // return number;
            return NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(amount));

        } catch (Exception e) {
            return "";
        }

    }

}
