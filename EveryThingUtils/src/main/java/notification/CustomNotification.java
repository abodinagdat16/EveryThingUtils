package notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.prime.arab.ware.everythingutils.ImageUtils;
import java.io.File;

public class CustomNotification {

	public Context context;

	public int noti_id;

	public String noti_name;

	public Notification.Builder n;

	public NotificationChannel nc;

	public NotificationManager nm;

	public Intent i;

	public PendingIntent pi;

	public RemoteViews rvs;

	public RemoteViews rvs2;

	public CustomNotification(Context c, String name, String description, int id) {

		context = c;

		noti_id = id;

		noti_name = name;

		nm = (NotificationManager) context.getSystemService(NotificationManager.class);

		if (nm == null) {

			nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		}

		if (Build.VERSION.SDK_INT >= 26) {

			nc = new NotificationChannel(noti_name, noti_name, NotificationManager.IMPORTANCE_DEFAULT);

			nc.setDescription(description);

			nm.createNotificationChannel(nc);

			n = new Notification.Builder(context, noti_name);

		} else {

			n = new Notification.Builder(context);

		}

	}

	public CustomNotification(Context c, String name, String description, int id, int importance) {

		context = c;

		noti_id = id;

		noti_name = name;

		nm = (NotificationManager) context.getSystemService(NotificationManager.class);

		if (nm == null) {

			nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		}

		if (Build.VERSION.SDK_INT >= 26) {

			nc = new NotificationChannel(noti_name, noti_name, importance);

			nc.setDescription(description);

			nm.createNotificationChannel(nc);

			n = new Notification.Builder(context, noti_name);

		} else {

			n = new Notification.Builder(context);

		}

	}

	public CustomNotification(Context c, String name, String description, int id, Uri u) {

		context = c;

		noti_id = id;

		noti_name = name;

		nm = (NotificationManager) context.getSystemService(NotificationManager.class);

		if (nm == null) {

			nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		}

		if (Build.VERSION.SDK_INT >= 26) {

			nc = new NotificationChannel(noti_name, noti_name, NotificationManager.IMPORTANCE_DEFAULT);

			nc.setDescription(description);

			try {
				nc.setSound(u, NotificationSound.aa());
			} catch (Throwable e) {

			}

			nm.createNotificationChannel(nc);

			n = new Notification.Builder(context, noti_name);

		} else {

			n = new Notification.Builder(context);

			try {
				n.setSound(u, NotificationSound.aa());
			} catch (Throwable e) {

			}

		}

	}

	public CustomNotification(Context c, String name, String description, int id, int importance, Uri u) {

		context = c;

		noti_id = id;

		noti_name = name;

		nm = (NotificationManager) context.getSystemService(NotificationManager.class);

		if (nm == null) {

			nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		}

		if (Build.VERSION.SDK_INT >= 26) {

			nc = new NotificationChannel(noti_name, noti_name, importance);

			nc.setDescription(description);

			try {
				nc.setSound(u, NotificationSound.aa());
			} catch (Throwable e) {

			}

			nm.createNotificationChannel(nc);

			n = new Notification.Builder(context, noti_name);

		} else {

			n = new Notification.Builder(context);

			try {
				n.setSound(u, NotificationSound.aa());
			} catch (Throwable e) {

			}

		}

	}

	public void setAlertOnce(boolean one) {

		n.setOnlyAlertOnce(one);

	}

	public void setTitle(String title) {

		n.setContentTitle(title);

	}

	public void setDescription(String description) {

		n.setContentText(description);

	}

	public void setSmallIcon(int resource) {

		n.setSmallIcon(resource);

	}

	public void setSmallIcon(String assets) {

		try {

			n.setSmallIcon(Icon.createWithBitmap(ImageUtils.getBitmapFromAssets(context, assets)));

		} catch (Throwable e) {

			throw new RuntimeException(e);

		}

	}

	public void setSmallIcon(File file) {

		try {

			n.setSmallIcon(Icon.createWithBitmap(ImageUtils.getBitmapFromFile(file)));

		} catch (Throwable e) {

			throw new RuntimeException(e);

		}

	}

	public void setSmallIcon(String resourceFolder, String resourceFileName) {

		try {

			n.setSmallIcon(
					Icon.createWithBitmap(ImageUtils.getBitmapFromResource(context, resourceFolder, resourceFileName)));

		} catch (Throwable e) {

			throw new RuntimeException(e);

		}

	}

	public void setSmallIcon(Bitmap b) {

		n.setSmallIcon(Icon.createWithBitmap(b));

	}

	public void setSmallIcon(Uri u) {

		n.setSmallIcon(Icon.createWithContentUri(u));

	}

	public void setBigIcon(int resource) {

		try {

			n.setLargeIcon(ImageUtils.getBitmapFromResource(context, resource));

		} catch (Throwable e) {

			throw new RuntimeException(e);

		}

	}

	public void setBigIcon(String resourceFolder, String resourceFileName) {

		try {

			n.setLargeIcon(ImageUtils.getBitmapFromResource(context, resourceFolder, resourceFileName));

		} catch (Throwable e) {

			throw new RuntimeException(e);

		}

	}

	public void setBigIcon(String assets) {

		try {

			n.setLargeIcon(ImageUtils.getBitmapFromAssets(context, assets));

		} catch (Throwable e) {

			throw new RuntimeException(e);

		}

	}

	public void setBigIcon(File file) {

		try {

			n.setLargeIcon(ImageUtils.getBitmapFromFile(file));

		} catch (Throwable e) {

			throw new RuntimeException(e);

		}

	}

	public void setBigIcon(Bitmap b) {

		n.setLargeIcon(b);

	}

	public void setBigIcon(Uri u) {

		n.setLargeIcon(Icon.createWithContentUri(u));

	}

	public void setCanBeRemoved(boolean can) {

		if (can) {

			n.setAutoCancel(true);

			n.setOngoing(false);

		} else {

			n.setAutoCancel(false);

			n.setOngoing(true);

		}

	}

	public void openActivityWhenClick(Class<?> a) {

		try {

			if (context.getApplicationContext() != null) {

				i = new Intent(context.getApplicationContext(), a);

			} else {

				i = new Intent(context, a);

			}

		} catch (Throwable e) {

			throw new RuntimeException("unable to get correct context to the notification , full error log is : "
					+ Log.getStackTraceString(e));

		}

		if (Build.VERSION.SDK_INT < 31) {

			pi = PendingIntent.getActivity(context, noti_id, i, 0);

		} else {

			pi = PendingIntent.getActivity(context, noti_id, i, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

		n.setContentIntent(pi);

	}

	public void openBroadcastWhenClick(Class<?> a) {

		try {

			if (context.getApplicationContext() != null) {

				i = new Intent(context.getApplicationContext(), a);

			} else {

				i = new Intent(context, a);

			}

		} catch (Throwable e) {

			throw new RuntimeException("unable to get correct context to the notification , full error log is : "
					+ Log.getStackTraceString(e));

		}

		if (Build.VERSION.SDK_INT < 31) {

			pi = PendingIntent.getBroadcast(context, noti_id, i, 0);

		} else {

			pi = PendingIntent.getBroadcast(context, noti_id, i, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

		n.setContentIntent(pi);

	}

	public void openServiceWhenClick(Class<?> a) {

		try {

			if (context.getApplicationContext() != null) {

				i = new Intent(context.getApplicationContext(), a);

			} else {

				i = new Intent(context, a);

			}

		} catch (Throwable e) {

			throw new RuntimeException("unable to get correct context to the notification , full error log is : "
					+ Log.getStackTraceString(e));

		}

		if (Build.VERSION.SDK_INT < 31) {

			pi = PendingIntent.getService(context, noti_id, i, 0);

		} else {

			pi = PendingIntent.getService(context, noti_id, i, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

		n.setContentIntent(pi);

	}

	public void openUrlWhenClick(String url) {

		try {

			i = new Intent(Intent.ACTION_VIEW);

			i.setData(Uri.parse(url));

		} catch (Throwable e) {

			throw new RuntimeException("unable to get correct url to the notification , full error log is : "
					+ Log.getStackTraceString(e));

		}

		if (Build.VERSION.SDK_INT < 31) {

			pi = PendingIntent.getActivity(context, noti_id, i, 0);

		} else {

			pi = PendingIntent.getActivity(context, noti_id, i, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

		n.setContentIntent(pi);

	}

	public void openIntentWhenClick(Intent intent) {

		if (Build.VERSION.SDK_INT < 31) {

			pi = PendingIntent.getActivity(context, noti_id, intent, 0);

		} else {

			pi = PendingIntent.getActivity(context, noti_id, intent, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

		n.setContentIntent(pi);

	}

	public void show() {
		
		if(Build.VERSION.SDK_INT > 23) {

		n.setCustomContentView(rvs);

		n.setCustomBigContentView(rvs2);
	
		} else {
			
			n.setContent(rvs2);
			
		}
		
		try {
			
			Notification.DecoratedCustomViewStyle dcvs = new Notification.DecoratedCustomViewStyle();
			
			dcvs.setBuilder(getBuilder());
			
			nm.notify(noti_id, dcvs.build());
			
			} catch(Throwable e2) {
			
			nm.notify(noti_id,n.build());
			
		}
		
		
		
		

	}

	public Notification getNotification() {

		if(Build.VERSION.SDK_INT > 23) {
			
			n.setCustomContentView(rvs);
			
			n.setCustomBigContentView(rvs2);
			
			} else {
			
			n.setContent(rvs2);
			
		}
		
		try {

		Notification.DecoratedCustomViewStyle dcvs = new Notification.DecoratedCustomViewStyle();
		
		dcvs.setBuilder(getBuilder());
		
		return dcvs.build();
		
		} catch(Throwable e) {
			
			return n.build();
			
		}

	}

	public void cancel(int id) {

		nm.cancel(id);

	}

	public void addAction(Icon i, String name, PendingIntent pi) {

		n.addAction(new Notification.Action.Builder(i, name, pi).build());

	}

	public Icon getIcon(String assets) {

		try {

			return Icon.createWithBitmap(ImageUtils.getBitmapFromAssets(context, assets));

		} catch (Throwable e) {

			throw new RuntimeException(
					"unable to get the icon to be set in the notification action button from assets");

		}

	}

	public Icon getIcon(File file) {

		try {

			return Icon.createWithBitmap(ImageUtils.getBitmapFromFile(file));

		} catch (Throwable e) {

			throw new RuntimeException("unable to get the icon to be set in the notification action button from file");

		}

	}

	public Icon getIcon(int res) {

		try {

			return Icon.createWithBitmap(ImageUtils.getBitmapFromResource(context, res));

		} catch (Throwable e) {

			throw new RuntimeException(
					"unable to get the icon to be set in the notification action button from resource");

		}

	}

	public PendingIntent getActivityPendingIntent(Class<?> a) {

		try {

			if (context.getApplicationContext() != null) {

				i = new Intent(context.getApplicationContext(), a);

			} else {

				i = new Intent(context, a);

			}

		} catch (Throwable e) {

			throw new RuntimeException("unable to get correct context to the notification , full error log is : "
					+ Log.getStackTraceString(e));

		}

		if (Build.VERSION.SDK_INT < 31) {

			return PendingIntent.getActivity(context, noti_id, i, 0);

		} else {

			return PendingIntent.getActivity(context, noti_id, i, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

	}

	public PendingIntent getServicePendingIntent(Class<?> s) {

		try {

			if (context.getApplicationContext() != null) {

				i = new Intent(context.getApplicationContext(), s);

			} else {

				i = new Intent(context, s);

			}

		} catch (Throwable e) {

			throw new RuntimeException("unable to get correct context to the notification , full error log is : "
					+ Log.getStackTraceString(e));

		}

		if (Build.VERSION.SDK_INT < 31) {

			return PendingIntent.getService(context, noti_id, i, 0);

		} else {

			return PendingIntent.getService(context, noti_id, i, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

	}

	public PendingIntent getBroadcastPendingIntent(Class<?> b) {

		try {

			if (context.getApplicationContext() != null) {

				i = new Intent(context.getApplicationContext(), b);

			} else {

				i = new Intent(context, b);

			}

		} catch (Throwable e) {

			throw new RuntimeException("unable to get correct context to the notification , full error log is : "
					+ Log.getStackTraceString(e));

		}

		if (Build.VERSION.SDK_INT < 31) {

			return PendingIntent.getBroadcast(context, noti_id, i, 0);

		} else {

			return PendingIntent.getBroadcast(context, noti_id, i, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

	}

	public PendingIntent getUrlPendingIntent(String url) {

		try {

			i = new Intent(Intent.ACTION_VIEW);

			i.setData(Uri.parse(url));

		} catch (Throwable e) {

			throw new RuntimeException("unable to get correct url to the notification , full error log is : "
					+ Log.getStackTraceString(e));

		}

		if (Build.VERSION.SDK_INT < 31) {

			return PendingIntent.getActivity(context, noti_id, i, 0);

		} else {

			return PendingIntent.getActivity(context, noti_id, i, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

	}

	public Notification.Builder getBuilder() {
		return n;
	}

	public NotificationManager getManager() {
		return nm;
	}

	public NotificationChannel getChannel() {
		return nc;
	}

	public void setColor(int color) {

		try {

			n.setColor(color);

		} catch (Throwable e) {

			

		}

	}

	public void setColor(String color) {
		setColor(Color.parseColor(color));
	}

	public void setColor(int red, int green, int blue) {
		setColor(Color.rgb(red, green, blue));
	}

	public void setColor(int alpha, int red, int green, int blue) {
		setColor(Color.argb(alpha, red, green, blue));
	}

	public PendingIntent getActivityPendingIntent(Intent i2) {

		if (Build.VERSION.SDK_INT < 31) {

			return PendingIntent.getActivity(context, noti_id, i2, 0);

		} else {

			return PendingIntent.getActivity(context, noti_id, i2, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

	}

	public PendingIntent getServicePendingIntent(Intent i2) {

		if (Build.VERSION.SDK_INT < 31) {

			return PendingIntent.getService(context, noti_id, i2, 0);

		} else {

			return PendingIntent.getService(context, noti_id, i2, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

	}

	public PendingIntent getBroadcastPendingIntent(Intent i2) {

		if (Build.VERSION.SDK_INT < 31) {

			return PendingIntent.getBroadcast(context, noti_id, i2, 0);

		} else {

			return PendingIntent.getBroadcast(context, noti_id, i2, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

	}

	public void openActivityWithIntentWhenClick(Intent intent) {

		if (Build.VERSION.SDK_INT < 31) {

			pi = PendingIntent.getActivity(context, noti_id, intent, 0);

		} else {

			pi = PendingIntent.getActivity(context, noti_id, intent, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

		n.setContentIntent(pi);

	}

	public void openServiceWithIntentWhenClick(Intent intent) {

		if (Build.VERSION.SDK_INT < 31) {

			pi = PendingIntent.getService(context, noti_id, intent, 0);

		} else {

			pi = PendingIntent.getService(context, noti_id, intent, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

		n.setContentIntent(pi);

	}

	public void openBroadcastWithIntentWhenClick(Intent intent) {

		if (Build.VERSION.SDK_INT < 31) {

			pi = PendingIntent.getBroadcast(context, noti_id, intent, 0);

		} else {

			pi = PendingIntent.getBroadcast(context, noti_id, intent, 0 | PendingIntent.FLAG_IMMUTABLE);

		}

		n.setContentIntent(pi);

	}

	public void setSmallCustomView(int layout) {

		rvs = new RemoteViews(context.getPackageName(), layout);

	}

	public void setBigCustomView(int layout) {

		rvs2 = new RemoteViews(context.getPackageName(), layout);

	}

	public void setSmallCustomViewOnClick(int viewId, PendingIntent pi) {

		rvs.setOnClickPendingIntent(viewId, pi);

	}

	public void setSmallCustomViewOnClick(int viewId, Intent inten) {

		rvs.setOnClickFillInIntent(viewId, inten);

	}

	public void setSmallCustomViewText(int viewid, String text) {

		rvs.setTextViewText(viewid, text);

	}

	public void setSmallCustomViewTextColor(int viewId, int color) {

		rvs.setTextColor(viewId, color);

	}

	public void setSmallCustomViewTextColor(int viewId, String color) {

		rvs.setTextColor(viewId, Color.parseColor(color));

	}

	public void setSmallCustomViewProgress(int viewId, int progress, int maxProgress, boolean indeterminate) {

		rvs.setProgressBar(viewId, progress, maxProgress, indeterminate);

	}

	public void setSmallCustomViewVisibility(int viewId, int visibility) {

		rvs.setViewVisibility(viewId, visibility);

	}

	public void setBigCustomViewOnClick(int viewId, PendingIntent pi) {

		rvs2.setOnClickPendingIntent(viewId, pi);

	}

	public void setBigCustomViewOnClick(int viewId, Intent inten) {

		rvs2.setOnClickFillInIntent(viewId, inten);

	}

	public void setBigCustomViewText(int viewid, String text) {

		rvs2.setTextViewText(viewid, text);

	}

	public void setBigCustomViewTextColor(int viewId, int color) {

		rvs2.setTextColor(viewId, color);

	}

	public void setBigCustomViewTextColor(int viewId, String color) {

		rvs2.setTextColor(viewId, Color.parseColor(color));

	}

	public void setBigCustomViewProgress(int viewId, int progress, int maxProgress, boolean indeterminate) {

		rvs2.setProgressBar(viewId, progress, maxProgress, indeterminate);

	}

	public void setBigCustomViewVisibility(int viewId, int visibility) {

		rvs2.setViewVisibility(viewId, visibility);

	}
	
	public RemoteViews getSmallCustomView() {
		
		return rvs;
		
	}
	
	public RemoteViews getBigCustomView() {
		
		return rvs2;
		
	}
	
	

}