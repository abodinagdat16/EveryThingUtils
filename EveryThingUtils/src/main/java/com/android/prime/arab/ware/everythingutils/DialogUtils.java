package com.android.prime.arab.ware.everythingutils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import java.util.ArrayList;

public class DialogUtils {
	
	public View view;
	public Context context;
	public AlertDialog ad;
	
	
	public DialogUtils(Context c) {
		context = c;
		ad = new AlertDialog.Builder(c).create();
		
	}
	
	public void setView(int layout) {
		view = View.inflate(context,layout,null);
		ad.setView(view);
	}
	
	public void setView(View v) {
		view = v;
		ad.setView(view);
	}
	
	public void setBackgroundColor(int color) {
		ad.getWindow().setBackgroundDrawable(new ColorDrawable(color));
	}
	
	public void setBackgroundColor(String color) {
		setBackgroundColor(Color.parseColor(color));
	}
	
	public void setBackgroundColor(int red , int green , int blue) {
		setBackgroundColor(Color.rgb(red,green,blue));
	}
	
	public void setBackgroundColor(int alpha , int red , int green , int blue) {
		setBackgroundColor(Color.argb(alpha,red,green,blue));
	}
	
	public void setBlurRadius(int radius) {
		ad.getWindow().setBackgroundBlurRadius(radius);
	}
	
	public void show() {
		ad.show();
	}
	
	public void dismiss() {
		ad.setCancelable(false);
		ad.dismiss();
	}
	
	public void cancel() {
		ad.cancel();
	}
	
	public void hide() {
		ad.hide();
	}
	
	public boolean isShowing() {
		return ad.isShowing();
	}
	
	public void setCancelable(boolean c) {
		ad.setCancelable(c);
	}
	
	public void setCancelableOnTouchOutSide(boolean c) {
		ad.setCanceledOnTouchOutside(c);
	}
	
	public <T> T getView(int id , Class<T> v) {
		return ((T)view.findViewById(id));
	}
	
	
	public View getDialogView() {
		return view;
	}
	
	public Window getWindow() {
		return ad.getWindow();
	}
	
	public void setOnDismiss(AlertDialog.OnDismissListener odl) {
		ad.setOnDismissListener(odl);
	}
	
	public void setOnShow(AlertDialog.OnShowListener osl) {
		ad.setOnShowListener(osl);
	}
	
	public void deleteOnDismissListener() {
		ad.setOnDismissListener(null);
	}
	
	public void deleteOnShowListener() {
		ad.setOnShowListener(null);
	}
	
	
	public ArrayList<View> getViews() {
		return getViews(view,new ArrayList<>());
	}
	
	
	private ArrayList<View> getViews(View v2 , ArrayList<View> v) {
		
		
		
		for(int a = 0; a < ((ViewGroup)v2).getChildCount(); a++) {
			
			View temp = ((ViewGroup)v2).getChildAt(a);
			
			v.add(temp);
			try {
			if(temp instanceof ViewGroup || temp instanceof LinearLayout || temp instanceof ScrollView || temp instanceof ListView || temp instanceof FrameLayout || temp instanceof RelativeLayout || temp instanceof GridView) {
				getViews(temp,v);
			}
			} catch(Throwable e) {
				
			}
			
			
		}
		
		return v;
		
		
		
	}
	
	
	
}