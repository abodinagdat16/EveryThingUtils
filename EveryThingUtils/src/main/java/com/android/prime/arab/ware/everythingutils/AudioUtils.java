package arabware.file;



import android.content.Context;
import android.net.Uri;
import android.media.MediaMetadataRetriever;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.BitmapFactory;
import android.content.res.AssetFileDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.net.URI;
import java.net.URL;
import arabware.file.listeners.LoadingInterface;
import android.app.Activity;
import android.media.MediaPlayer;
import android.app.Activity;



public class AudioUtils {
    
    private MediaMetadataRetriever media = new MediaMetadataRetriever();
    private AssetFileDescriptor res;
    private File tempF;
    private Context context;
    private Activity a;
    
    //public fields
    
    public String album = "null";
    public String album_artist = "null";
    public String artist = "null";
    public String author = "null";
    public String bitrate = "null";
    public String title = "null";
    public String writer = "null";
    public String date = "null";
    public String year = "null";
    public String duration = "null";
    public Bitmap image;
    
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    
    
    
    
    /*to load audio from file , like new File("file path") is file , your app must have full files managing permissions*/
        
    
    public AudioUtils(Context c , File file) throws IOException {
        context = c;
        media.setDataSource(c,Uri.fromFile(file));
        mediaPlayer = MediaPlayer.create(c.getApplicationContext(),Uri.fromFile(file));
        doSomething();
        
    }
    
    
    /* to load audio from resources , like R.raw.audioName */
    
    public AudioUtils(Context c , int resource) throws IOException {
        context = c;
        res = c.getApplicationContext().getResources().openRawResourceFd(resource);
        media.setDataSource(res.getFileDescriptor(),res.getStartOffset(),res.getLength());
        res.close();
        mediaPlayer = MediaPlayer.create(c.getApplicationContext(),resource);
        doSomething();
        
    }
    
    
    /* to load audio from assets , like audio.mp3 or folder/audio.mp3 */
    
    public AudioUtils(String assets , Context c) throws IOException {
        context = c;
        tempF = new File(c.getApplicationContext().getCacheDir(),"temp.mp3");
        
        java.io.InputStream inputs = c.getApplicationContext().getAssets().open(assets); 
java.io.FileOutputStream fos = null;
fos = new FileOutputStream(tempF);
final byte[] byteV = new byte[1024];
int i6;
while ((i6 = inputs.read(byteV)) != -1) {
fos.write(byteV, 0, i6);
} 
inputs.close();
fos.close();
        
        
        media.setDataSource(c,Uri.fromFile(tempF));
        
        mediaPlayer = MediaPlayer.create(c.getApplicationContext(),Uri.fromFile(tempF));
        
        
        doSomething();
        
    }
    
    
    public void doSomething() {
        
        
        try {
            
            title = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_TITLE);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            album = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_ALBUM);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            album_artist = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            artist = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_ARTIST);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            author = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_AUTHOR);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            writer = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_WRITER);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            bitrate = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_BITRATE);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            date = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DATE);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            duration = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            year = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_YEAR);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        try {
            
            image = BitmapFactory.decodeByteArray(media.getEmbeddedPicture(), 0, media.getEmbeddedPicture().length);
            
            
        } catch(Exception e) {
            
            
            
            
        }
        
        
        
        
    }
    
    
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    
    public static MediaPlayer getMediaPlayerFromUrl(final Context ct , final String url , final LoadingInterface li) {
        
        if(li != null) {
            li.loading();
        }
        
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                
                
                mediaPlayer = MediaPlayer.create(ct,Uri.parse(url));
                
                
                
                ((Activity)ct).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(li != null) {
                            li.done();
                        }
                    }
                });
                
                
                
            }
        });
        t.start();
        try {
        t.join();
        } catch(Exception e) {
         
        }
        
        return mediaPlayer;
        
        
    }
    
    public String getAlbum() {
        return album;
    }
    
    public String getAlbumArtist() {
        return album_artist;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getWriter() {
        return writer;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getYear() {
        return year;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public String getBitrate() {
        return bitrate;
    }
    
    public Bitmap getAudioImage() throws Exception {
        if(image==null) {
            throw new RuntimeException(new Exception("the audio file does not contain an image or the app is unable to get it ."));
        }
        return image;
    }
    
    
}
