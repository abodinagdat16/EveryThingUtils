package com.android.prime.arab.ware.everythingutils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class FloatingWindowUtils {
	
	public Context context;
	
	public View view;
	
	public WindowManager wm;
	
	public WindowManager.LayoutParams lp;
	
	
	
	public FloatingWindowUtils(Context c) {
		this.context = c;
		wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
		
		
		lp = random(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
		
	}
	
	private WindowManager.LayoutParams random(int w , int h) {
		
		int LAYOUT_FLAG;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
			} else {
				
			LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
		}
		
		return new WindowManager.LayoutParams(
		w,
		h,
		LAYOUT_FLAG,
		
		WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
		,
		
		PixelFormat.TRANSLUCENT);
		
	}
	
	public void setLayout(View v) {
		view = v;
	}
	
	public void setLayout(int layout) {
		view = View.inflate(context,layout,null);
	}
	
	public void updateLayout(View v) {
		view = v;
		if(wm != null && lp != null) {
			wm.updateViewLayout(view,lp);
		}
	}
	
	public void updateLayout(int layout) {
		updateLayout(View.inflate(context,layout,null));
	}
	
	public void setTouchView(int id) {
		view.findViewById(id).setOnTouchListener(new View.OnTouchListener() {
			
			private int x;
			private int y;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
					x = (int) event.getRawX();
					y = (int) event.getRawY();
					break;
					case MotionEvent.ACTION_MOVE:
					int nowX = (int) event.getRawX();
					int nowY = (int) event.getRawY();
					int movedX = nowX - x;
					int movedY = nowY - y;
					x = nowX;
					y = nowY;
					lp.x = lp.x + movedX;
					lp.y = lp.y + movedY;
					wm.updateViewLayout(view, lp);
					break;
					default:
					break;
				}
				return false;
			}
		});
		lp.gravity = Gravity.TOP | Gravity.LEFT;
		lp.x = 0;
		lp.y = 0;
	}
	
	
	public void show() {
		wm.addView(view,lp);
	}
	
	public void dismiss() {
		wm.removeView(view);
	}
	
	public void start() {
		show();
	}
	
	public void cancel() {
		dismiss();
	}
	
	public void open() {
		show();
	}
	
	public void hide() {
		dismiss();
	}
	
	public void changeWidthAndHeight(int w , int h) {
		
		lp = random(w,h);
		
		updateLayout(view);
		
		
	}
	
	public Context getContext() {
		return context;
	}
	
	public View getView() {
		return view;
	}
	
	public <T> T getView(int id , Class<T> c) {
		return ((T)view.findViewById(id));
	}
	
	
	
}