package com.android.prime.arab.ware.everythingutils;

import android.app.Activity;
import android.view.WindowMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;
import android.graphics.Rect;
import android.graphics.Insets;
import android.view.WindowInsets;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.content.Context;
import android.graphics.Color;
import android.content.ContextWrapper;


/*
this class was created only for getting width and height of
any device
the codes are from StackOverFlow site .
*/

/*
the answer link , all credits are saved : 
https://stackoverflow.com/a/70350121
*/


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
        return dp(activity,getScreenWidth(activity));
    }
    public static <T extends Activity> int getScreenHeightDp(T activity) {
        return dp(activity,getScreenHeight(activity));
    }
    
    
    public static <T extends Activity> int dp( T context , int px ) {
        
    final float density = context.getResources().getDisplayMetrics().density;
    final int valueInDp = (int)(px * density);
    
    return valueInDp;
    
    }
    
    /* any thing after this is by arabware */
    
    private Context context;
    
    public ScreenUtils(Context c) {
        context = c;
    }
    
    
    public static Activity getActivity(Context c) {
        if(c instanceof Activity) {
            return (Activity)c;
        }
        if(c instanceof ContextWrapper) {
            return getActivity(((ContextWrapper)c).getBaseContext());
        }
        throw new RuntimeException(new Exception("you are using Context of A NOT ACTIVITY , you must use an activity in order to use the features of ScreenUtils class , for example if your Activity name is MainActivity ==> MainActivity.this or if it is a fragment ==> FragmentName.this.getActivity()."));
    }
    
    
    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity(context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            try {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } catch(Exception e) {
                
            }
            window.setStatusBarColor(color);
}
    }
    public void setNavigationBarColor(int color) {
        if(Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity(context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(color);
        }
    }
    public void setStatusBarColor(String color) {
        setStatusBarColor(Color.parseColor(color));
    }
    public void setNavigationBarColor(String color) {
        setNavigationBarColor(Color.parseColor(color));
    }
    public void makeStatusBarTransparent() {
        
    }
    public void makeNavigationBarTransparent() {
        
    }
    public void makeStatusAndNavigationBarsTransparent() {
        
    }
    public void hideStatusBar() {
        
    }
    public void hideNavigationBar() {
        
    }
    public void changeStatusBarIconColors(int color) {
        
    }
    public void changeStatusBarIconColors(String color) {
        
    }
    public void changeStatusBarHeightPx(int px) {
        
    }
    public void changeNavigationBarHeightPx(int px) {
        
    }
    public int getStatusBarHeightPx() {
        
        if (context.getResources().getIdentifier("status_bar_height", "dimen", "android") > 0) {
            return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
        }
        return 0;
    }
    public int getNavigationBarHeightPx() {
        if (context.getResources().getIdentifier("navigation_bar_height", "dimen", "android") > 0) {
            return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
        }
        return 0;
    }
    public int getBrightness() {
        return android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS,-1);
    }
    public boolean isPortrait() {
        return true;
    }
    public boolean isLandscape() {
        return true;
    }
    public int getScreenWidthWithoutSystemBars() {
        return getActivity(context).findViewById(android.R.id.content).getWidth();
    }
    public int getScreenHeightWithoutSystemBars() {
        return getActivity(context).findViewById(android.R.id.content).getHeight();
    }
    
    
}
