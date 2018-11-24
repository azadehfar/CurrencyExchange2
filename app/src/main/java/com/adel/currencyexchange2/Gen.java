package com.adel.currencyexchange2;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.ArrayMap;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.github.thunder413.datetimeutils.DateTimeUnits;
import com.github.thunder413.datetimeutils.DateTimeUtils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import de.mateware.snacky.Snacky;

public class Gen {

    public static String GeneralTextForn = "mysans.ttf";

    static long currency_Cache = 4200;
    static String currency_from_Cache, currency_to_Cache;
    static Date LastupdatedDate;

    public static ArrayList<MoneyDataModel> MoneydataModels = new ArrayList<>();
    public static ArrayList<MoneyDataModel> GolddataModels = new ArrayList<>();


    public static  String ExchangeTiken="987d7de5-b076-4bfa-df64-08d6179c9234";
    public static String ExchangeURL = "http://core.arzws.com/api/";
    //http://api.backino.net/nerkhapi/examples.html
    public static EndPoints endpoints = Generator.createService(EndPoints.class);


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


    public static void closeKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static void showKeyboard(Activity activity) {


        InputMethodManager imgr = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
      //  imgr.showSoftInput(amount, InputMethodManager.SHOW_IMPLICIT);
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
        );

    }

    public static void ShowInfo(View view , Activity activity , String msg ) {

       if (view != null )
         Snacky.builder()
                .setView(view)
                .setText(msg)
                .setDuration(Snacky.LENGTH_SHORT)
                .info()
                .show();

       else if (activity !=null)
           Snacky.builder()
                   .setActivity(activity)
                   .setText(msg)
                   .setDuration(Snacky.LENGTH_SHORT)
                   .info()
                   .show();

}

    public static void ShowError(View view , Activity activity , String msg ) {

        if (view != null )
            Snacky.builder()
                    .setView(view)
                    .setText(msg)
                    .setDuration(Snacky.LENGTH_SHORT)
                    .error()
                    .show();

        else if (activity !=null)
            Snacky.builder()
                    .setActivity(activity)
                    .setText(msg)
                    .setDuration(Snacky.LENGTH_SHORT)
                    .error()
                    .show();

    }

    public static void ShowSuccess(View view , Activity activity , String msg ) {

        if (view != null )
            Snacky.builder()
                    .setView(view)
                    .setText(msg)
                    .setDuration(Snacky.LENGTH_SHORT)
                    .success()
                    .show();

        else if (activity !=null)
            Snacky.builder()
                    .setActivity(activity)
                    .setText(msg)
                    .setDuration(Snacky.LENGTH_SHORT)
                    .success()
                    .show();

    }






    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Activity getRunningActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread")
                    .invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            ArrayMap activities = (ArrayMap) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    return (Activity) activityField.get(activityRecord);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Didn't find the running activity");
    }




    public static String DifferentDatesDuration(Date date)
    {
        Date currentdate = new Date();

        int diffm = DateTimeUtils.getDateDiff(currentdate,date, DateTimeUnits.MINUTES);
        int diffh = DateTimeUtils.getDateDiff(currentdate,date, DateTimeUnits.HOURS);
        int diffd = DateTimeUtils.getDateDiff(currentdate,date, DateTimeUnits.DAYS);
        int diffs = DateTimeUtils.getDateDiff(currentdate,date, DateTimeUnits.SECONDS);



        if (diffd > 0 )
          return  diffd+" روز ";
        else if (diffh > 0 )
            return  diffh+" ساعت ";
        else if (diffm > 0 )
            return  diffm+" دقیقه ";
        else
            return  diffs+" ثانیه ";
    }


 /*   private void getPermission() {
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.READ_SMS},
                    1000);
        }
*/

    }
