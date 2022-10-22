package com.android.prime.arab.ware.everythingutils;


import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.android.prime.arab.ware.everythingutils.listeners.LoadingInterface;


public class AudioUtils {

    public static MediaPlayer mediaPlayer = new MediaPlayer();
    public String album = "null";
    public String album_artist = "null";
    public String artist = "null";
    public String author = "null";

    //public fields
    public String bitrate = "null";
    public String title = "null";
    public String writer = "null";
    public String date = "null";
    public String year = "null";
    public String duration = "null";
    public Bitmap image;
    private final MediaMetadataRetriever media = new MediaMetadataRetriever();
    private final Context context;
    private Activity a;




    /*to load audio from file , like new File("file path") is file , your app must have full files managing permissions*/


    public AudioUtils(Context c, File file) throws IOException {
        context = c;
        media.setDataSource(c, Uri.fromFile(file));
        mediaPlayer = MediaPlayer.create(c.getApplicationContext(), Uri.fromFile(file));
        doSomething();

    }


    /* to load audio from resources , like R.raw.audioName */

    public AudioUtils(Context c, int resource) throws IOException {
        context = c;
        AssetFileDescriptor res = c.getApplicationContext().getResources().openRawResourceFd(resource);
        media.setDataSource(res.getFileDescriptor(), res.getStartOffset(), res.getLength());
        res.close();
        mediaPlayer = MediaPlayer.create(c.getApplicationContext(), resource);
        doSomething();

    }


    /* to load audio from assets , like audio.mp3 or folder/audio.mp3 */

    public AudioUtils(String assets, Context c) throws IOException {
        context = c;
        File tempF = new File(c.getApplicationContext().getCacheDir(), "temp.mp3");

        java.io.InputStream inputs = c.getApplicationContext().getAssets().open(assets);
        FileOutputStream fos;
        fos = new FileOutputStream(tempF);
        final byte[] byteV = new byte[1024];
        int i6;
        while ((i6 = inputs.read(byteV)) != -1) {
            fos.write(byteV, 0, i6);
        }
        inputs.close();
        fos.close();


        media.setDataSource(c, Uri.fromFile(tempF));

        mediaPlayer = MediaPlayer.create(c.getApplicationContext(), Uri.fromFile(tempF));


        doSomething();

    }

    public static MediaPlayer getMediaPlayerFromUrl(final Context ct, final String url, final LoadingInterface li) {

        if (li != null) {
            li.loading();
        }

        Thread t = new Thread(() -> {


            mediaPlayer = MediaPlayer.create(ct, Uri.parse(url));


            ((Activity) ct).runOnUiThread(() -> {
                if (li != null) {
                    li.done();
                }
            });


        });
        t.start();
        try {
            t.join();
        } catch (Exception ignored) {

        }

        return mediaPlayer;


    }

    public void doSomething() {


        try {

            title = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_TITLE);


        } catch (Exception ignored) {


        }
        try {

            album = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_ALBUM);


        } catch (Exception ignored) {


        }
        try {

            album_artist = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST);


        } catch (Exception ignored) {


        }
        try {

            artist = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_ARTIST);


        } catch (Exception ignored) {


        }
        try {

            author = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_AUTHOR);


        } catch (Exception ignored) {


        }
        try {

            writer = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_WRITER);


        } catch (Exception ignored) {


        }
        try {

            bitrate = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_BITRATE);


        } catch (Exception ignored) {


        }
        try {

            date = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_DATE);


        } catch (Exception ignored) {


        }
        try {

            duration = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_DURATION);


        } catch (Exception ignored) {


        }
        try {

            year = media.extractMetadata( MediaMetadataRetriever.METADATA_KEY_YEAR);


        } catch (Exception ignored) {


        }
        try {

            image = BitmapFactory.decodeByteArray(media.getEmbeddedPicture(), 0, media.getEmbeddedPicture().length);


        } catch (Exception ignored) {


        }


    }

    public MediaPlayer getMediaPlayer() {
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
        if (image == null) {
            throw new RuntimeException(new Exception("the audio file does not contain an image or the app is unable to get it ."));
        }
        return image;
    }


}
