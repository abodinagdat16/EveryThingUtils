package com.android.prime.arab.ware.everythingutils;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowInsets;
import android.view.WindowMetrics;


/*
this class was created only for getting width and height of
any device
the codes are from StackOverFlow site .
*/

/*
the answer link , all credits are saved : 
https://stackoverflow.com/a/70350121
*/

/*this just for ignoring warnings since this class is using if then else codes to work on all android versions.*/
public class ScreenUtils {


    public static <T extends Activity> int getScreenWidth(T activity) {
        if (Build.VERSION.SDK_INT >= 30) {
            WindowMetrics windowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            Rect bounds = windowMetrics.getBounds();
            Insets insets = windowMetrics.getWindowInsets().getInsetsIgnoringVisibility(
                    WindowInsets.Type.systemBars()
            );

            if (activity.getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_LANDSCAPE
                    && activity.getResources().getConfiguration().smallestScreenWidthDp < 600
            ) { // landscape and phone
                int navigationBarSize = insets.right + insets.left;
                return bounds.width() - navigationBarSize;
            } else { // portrait or tablet
                return bounds.width();
            }
        } else {
            DisplayMetrics outMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
            return outMetrics.widthPixels;
        }
    }

    public static <T extends Activity> int getScreenHeight(T activity) {
        if (Build.VERSION.SDK_INT >= 30) {
            WindowMetrics windowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            Rect bounds = windowMetrics.getBounds();
            Insets insets = windowMetrics.getWindowInsets().getInsetsIgnoringVisibility(
                    WindowInsets.Type.systemBars()
            );

            if (activity.getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_LANDSCAPE
                    && activity.getResources().getConfiguration().smallestScreenWidthDp < 600
            ) { // landscape and phone
                return bounds.height();
            } else { // portrait or tablet
                int navigationBarSize = insets.bottom;
                return bounds.height() - navigationBarSize;
            }
        } else {
            DisplayMetrics outMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
            return outMetrics.heightPixels;
        }
    }


    public static <T extends Activity> int getScreenWidthDp(T activity) {
        return dp(activity, getScreenWidth(activity));
    }

    public static <T extends Activity> int getScreenHeightDp(T activity) {
        return dp(activity, getScreenHeight(activity));
    }


    public static <T extends Activity> int dp(T context, int px) {

        final float density = context.getResources().getDisplayMetrics().density;

        return (int) (px * density);

    }
}
