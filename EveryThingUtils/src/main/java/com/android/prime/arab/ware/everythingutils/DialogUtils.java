package com.android.prime.arab.ware.everythingutils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import java.util.ArrayList;

public class DialogUtils {
	
	public View view;
	public Context context;
	public AlertDialog ad;
	
	
	public DialogUtils(Context c) {
		context = c;
		ad = new AlertDialog.Builder(c).create();
		
	}
	
	public AlertDialog getAlertDialog() {
		return ad;
	}
	
	public Context getContext() {
		return context;
	}
	
	public void setAlertDialog(AlertDialog AD) {
		ad = AD;
	}
	
	public void setContext(Context c) {
		context = c;
	}
	
	public void setView(int layout) {
		view = View.inflate(context,layout,null);
		ad.setView(view);
	}
	
	public void setView(View v) {
		view = v;
		ad.setView(view);
	}
	
	public void setGravity(int gravity) {
		this.getWindow().setGravity(gravity);
	}
	
	public void setWidthAndHeight(int w , int h) throws Throwable {
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		
		lp.copyFrom(this.getWindow().getAttributes());
		
		lp.width = w;
		
		lp.height = h;
		
		this.getWindow().setAttributes(lp);
		
		try {
		
		ViewGroup.LayoutParams vg = view.getLayoutParams();
		
		
		
		vg.width = w;
		
		vg.height = h;
		
		view.setLayoutParams(vg);
		
		
		} catch(Throwable e) {
			
			throw new Throwable("unable to set width and height , are you sure your view that you use in custom dialog has been loaded , call the method of setting width and height after it has been loaded after showing the dialog!!!!!!!!!!!!!!!!!!!!!");
			
			
		}
	}
	
	public void setCoordinates(int x , int y) {
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		
		lp.copyFrom(this.getWindow().getAttributes());
		
		lp.x = x;
		
		lp.y = y;
		
		this.getWindow().setAttributes(lp);
		
		this.getWindow().getAttributes().dimAmount = 0;
		
		view.setTranslationX(x);
		
		view.setTranslationY(y);
		
	}
	
	public void setAlpha(float alpha) {
		WindowManager.LayoutParams lp = this.getWindow().getAttributes();
		lp.alpha = alpha;
		this.getWindow().setAttributes(lp);
		view.setAlpha(alpha);
		
	}
	
	public void rotate(int agl) {
		try {
		WindowManager.LayoutParams lp = this.getWindow().getAttributes();
		lp.rotationAnimation = agl;
		this.getWindow().setAttributes(lp);
		} catch(Throwable e) {
			
		}
		view.setRotation(agl);
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
	
	public void setDim(int dim) {
		WindowManager.LayoutParams lp = this.getWindow().getAttributes();
		lp.dimAmount = dim;
		this.getWindow().setAttributes(lp);
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
		Toast t = new Toast(context);
		
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