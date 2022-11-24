package notification;

import android.content.Context;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.net.Uri;
import java.io.File;

public class NotificationSound {
	
	public Uri u;
	
	public Context context;
	
	public NotificationSound(Context c) {
		
		context = c;
		
	}
	
	public NotificationSound setSound(String assets) {
		
		if(assets.startsWith("/")) {
			u = Uri.parse("file:///android_assets"+ assets);
			} else {
			u = Uri.parse("file:///android_assets/"+ assets);
		}
		
		return this;
		
	}
	
	public NotificationSound setSound(int id) {
		
		u = getUriToResource(id);
		
		return this;
		
	}
	
	public NotificationSound setSound(File file) {
		
		u = Uri.fromFile(file);
		
		return this;
		
	}
	
	public NotificationSound setSound(Uri u2) {
		
		this.u = u2;
		
		return this;
		
	}
	
	//getUriToResource is taken from a stack over flow answer
	//credit link :
	//https://stackoverflow.com/a/51378760/17808329
	
	public Uri getUriToResource(int id) throws Resources.NotFoundException {
		Resources res = context.getResources();
		
		Uri u = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
		"://" + res.getResourcePackageName(id)
		+ '/' + res.getResourceTypeName(id)
		+ '/' + res.getResourceEntryName(id));
		return u;
	}
	
	public static AudioAttributes aa() {
		
		
		return new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
		.setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
		
	}
	
	public Uri getUri() {
		return u;
	}
	
}