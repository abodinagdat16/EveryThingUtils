package com.android.prime.arab.ware.everythingutils;


import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import java.util.ArrayList;
import com.android.prime.arab.ware.everythingutils.*;

public class PermissionUtils {
	private final int API_LEVEL = Build.VERSION.SDK_INT;
	private Activity ct;
	
	public <T extends Activity> PermissionUtils(T c) {
		ct = c;
	}
	
	public void requestPermission(String permission , int yourRequestCode) {
		if(API_LEVEL >= 23) {
			ct.requestPermissions(new String[] {"android.permission."+permission}, yourRequestCode);
		}
	}
	
	public void requestPermissions(ArrayList<String> permissions , int yourRequestCode) {
		if(API_LEVEL >= 23) {
			String[] tmp = new String[permissions.size()];
			for(int a = 0; a < permissions.size(); a++) {
				tmp[a] = "android.permission."+permissions.get(a);
			}
			ct.requestPermissions(tmp,yourRequestCode);
		}
	}
	
	public boolean checkPermission(String permission) {
		if(API_LEVEL >= 23) {
			return ct.checkSelfPermission("android.permission."+permission)==PackageManager.PERMISSION_GRANTED;
		}
		return true;
	}
	
	public void requestAppearOnTopPermission() {
		if(API_LEVEL >= 23) {
			try {
				Uri uri = Uri.parse("package:" + ct.getPackageName());
				Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri);
				ct.startActivity(intent);
			} catch(Exception ex) {
				Intent intent = new Intent();
				intent.setAction(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
				ct.startActivity(intent);
			}
		}
	}
	
	public boolean checkAppearOnTopPermission() {
		if(API_LEVEL >= 23) {
			return android.provider.Settings.canDrawOverlays(ct);
		}
		return true;
	}
	
	public void requestWriteSettings() {
		if(API_LEVEL >= 23) {
			try {
				Uri uri = Uri.parse("package:" + ct.getPackageName());
				Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, uri);
				ct.startActivity(intent);
			} catch(Exception ex) {
				Intent intent = new Intent();
				intent.setAction(Settings.ACTION_MANAGE_WRITE_SETTINGS);
				ct.startActivity(intent);
			}
		}
	}
	
	public boolean checkWriteSettings() {
		if(API_LEVEL >= 23) {
			return Settings.System.canWrite(ct);
		}
		return true;
	}
	
	public void requestManageAllStorage() {
		if(API_LEVEL >= 30) {
			try {
				Uri uri = Uri.parse("package:" + ct.getPackageName());
				Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
				ct.startActivity(intent);
			} catch (Exception ex){
				Intent intent = new Intent();
				intent.setAction(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
				ct.startActivity(intent);
			}
		}
	}
	
	public boolean checkManageAllStorage() {
		if(API_LEVEL >= 30) {
			return android.os.Environment.isExternalStorageManager();
		}
		return true;
	}
	
	public void checkFullStoragePermissions() {
		new ArabWareFileManager(ct).checkStoragePermissions(ct);
	}
	
	public boolean isFullStorageAccess() {
		return new ArabWareFileManager(ct).isFullAccessFiles(ct);
	}
	
	public void requestOptimiseBatteryPermission() {
		if(API_LEVEL >= 23) {
			try {
				Uri uri = Uri.parse("package:" + ct.getPackageName());
				Intent intent = new Intent(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, uri);
				ct.startActivity(intent);
			} catch (Exception ex){
				Intent intent = new Intent();
				intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
				ct.startActivity(intent);
			}
		}
	}
	
	public boolean canIgnoreBatterySaving() {
		if(API_LEVEL >= 23) {
			return ((PowerManager)ct.getSystemService(Context.POWER_SERVICE)).isIgnoringBatteryOptimizations(ct.getPackageName());
		}
		return true;
	}
	
	
	public void requestInstallPermission(int yourRequestCode) {
		if(API_LEVEL >= 23 && API_LEVEL < 29) {
			if (!ct.getPackageManager().canRequestPackageInstalls()) {
				ct.startActivityForResult(new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).setData(Uri.parse(String.format("package:%s", ct.getPackageName()))), yourRequestCode);
			}
			try {
				Uri uri = Uri.parse("package:" + ct.getPackageName());
				Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE, uri);
				ct.startActivity(intent);
			} catch (Exception ex) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
				ct.startActivity(intent);
			}
		}
	}
	
	public boolean canInstallUnknownPackages() {
		if(API_LEVEL >= 26) {
			return ct.getPackageManager().canRequestPackageInstalls();
		}
		return true;
	}
	
	public void requestNotificationAccessPermission() {
		if(API_LEVEL >= 23) {
			try {
				Uri uri = Uri.parse("package:" + ct.getPackageName());
				Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS, uri);
				ct.startActivity(intent);
			} catch (Exception ex){
				Intent intent = new Intent();
				intent.setAction(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
				ct.startActivity(intent);
			}
		}
	}
	
	public boolean checkNotificationAccessPermission() {
		return isNotificationServiceEnabled(ct);
	}
	
	public static String notification_checking_credit = "this isNotificationServiceEnabled function code is by stack over flow , i did not find any else way to put credits to be read so i made a String variable so decompiling the library files can show you the source of that method";
	public static String CREDITS = "ArabWare 95% , StackOverFlow 5%";
	
	
	private boolean isNotificationServiceEnabled(Context c){
		String pkgName = c.getPackageName();
		final String flat = Settings.Secure.getString(c.getContentResolver(), "enabled_notification_listeners");
		if (!TextUtils.isEmpty(flat)) {
			final String[] names = flat.split(":");
			for (int i = 0; i < names.length; i++) {
				final ComponentName cn = ComponentName.unflattenFromString(names[i]);
				if (cn != null) {
					if (TextUtils.equals(pkgName, cn.getPackageName())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void requestPictureInPicturePermission() {
		if(API_LEVEL >= 26) {
			try {
				Uri uri = Uri.parse("package:" + ct.getPackageName());
				Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri);
				ct.startActivity(intent);
			} catch (Exception ex){
				Intent intent = new Intent();
				intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
				ct.startActivity(intent);
			}
		}
	}
	
	public boolean checkPictureInPicturePermission() {
		if(API_LEVEL >= 29) {
			return (((AppOpsManager)ct.getSystemService(Context.APP_OPS_SERVICE)).unsafeCheckOpNoThrow(AppOpsManager.OPSTR_PICTURE_IN_PICTURE,Process.myPid(),ct.getPackageName()) == AppOpsManager.MODE_ALLOWED);
		}
		return (((AppOpsManager)ct.getSystemService(Context.APP_OPS_SERVICE)).checkOpNoThrow(AppOpsManager.OPSTR_PICTURE_IN_PICTURE,Process.myPid(),ct.getPackageName()) == AppOpsManager.MODE_ALLOWED);
	}
}
