/*THE IMAGEUTILS PROJECT , ALL COPYRIGHT SAVED , ARABWARE 2022*/

/*

credits :

ArabWare : about 75%

StackOverFlow : about 20%

Sketchup and others : about 5%

*/


package com.android.prime.arab.ware.everythingutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Build;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;


/*the class definition*/

public class ImageUtils {
    
    /*
    the field (float[]) is from StackOverFlow :
    credits and answer link :

    https://stackoverflow.com/a/17871384/17808329
    
    */

    public static final String[] credits_dont_delete = {
            "credits", "dont delete me", "it is illegal", "haram", "creators list : ",
            "ArabWare , for coding more than the half of class + Modifications", "StackOverFlow,for some functions , they are mentioned in github page",
            "Sketchware, Sketchub , for some info and ideas about bitmaps", "and to all who helped and suggested me while coding"
    };
    public static final String TAG = "ImageUtils";
    public static final int yearOfThisLibraryClass = 2022;
    public static final String country = "Iraq";
    public static final String religion = "Islam";


    /* here you must set the valid Context , like a working activity, service , fragment , dialog fragment , broadcast receiver, and application , they should be working and not called by a static java calling */
    public static final String province = "Najaf";
    public static final int age = 18;
    public static final boolean isMale = true;
    private static final float[] NEGATIVE = {
            -1.0f, 0, 0, 0, 255, // red
            0, -1.0f, 0, 0, 255, // green
            0, 0, -1.0f, 0, 255, // blue
            0, 0, 0, 1.0f, 0  // alpha
    };
    /*here we save the progress of image editing */
    public ArrayList<Bitmap> bitmaps;

    /*not supported currently*/
    
    /*
    
    public void setFromUrl(String url) {
        
    }
    
    */
    public final Context context;
    /* the image / painting data */
    public Bitmap bitmap;

    public ImageUtils(Context c) {
        context = c;
    }

    static /* synthetic */ int randomThing(Entry entry, Entry entry2) {
        if (entry.getValue() == entry2.getValue()) {
            return Long.compare((Integer) entry.getKey(), (Integer) entry2.getKey());
        }
        return Long.compare((Long) entry.getValue(), (Long) entry2.getValue());
    }

    public static Bitmap getBitmapFromFile(File file) throws Exception {
        if (file.exists()) {
            if (!file.isFile()) {
                throw new RuntimeException(new Exception("you can not get bitmap of a folder , you must choose the image file and not a folder..."));
            }
        } else {
            throw new RuntimeException(new Exception("your path does not exist.make sure you are getting bitmap of a valid file"));
        }

        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public static Bitmap getBitmapFromAssets(Context c, String assets) throws IOException {
        return BitmapFactory.decodeStream(c.getAssets().open(assets));
    }

    public static Bitmap getBitmapFromResource(Context c, int resource) throws Exception {
        return BitmapFactory.decodeResource(c.getResources(), resource);
    }

    @SuppressLint("DiscouragedApi")
    public static Bitmap getBitmapFromResource(Context c, String resourceFolder, String resourceName) throws Exception {
        return BitmapFactory.decodeResource(c.getResources(), c.getResources().getIdentifier(resourceName, resourceFolder, c.getPackageName()));
    }

    public static Bitmap changeWidthAndHeight(Bitmap b, int w, int h) {

        if (b == null) {
            throw new RuntimeException(new Exception("your bitmap of image is null (empty , no data) make sure you loaded a real image correctly"));
        }


        if (w == 0 || w < 0) {
            throw new RuntimeException(new Exception("the width of image can not be less than zero or zero"));
        }

        if (h == 0 || h < 0) {
            throw new RuntimeException(new Exception("the height of image can not be less than zero or zero"));
        }

        try {

            b = b.copy(b.getConfig(), true);

            b.reconfigure(w, h, b.getConfig());

        } catch (Exception e) {

            Bitmap tmp = b.copy(b.getConfig(), true);

            tmp = Bitmap.createScaledBitmap(tmp, w, h, false);

            b = tmp;


        }

        return b;


    }
    
    
    
    /*
    
    use this to set a filter on an image
    
    filter types are , "oldBlackWhite" , "negative"
    
    */

    public static int getColorFromRGB(int red, int green, int blue) {
        return Color.rgb(red, green, blue);
    }

    public static String getStringColorFromRGB(int red, int green, int blue) {
        return "#" + Integer.toHexString(getColorFromRGB(red, green, blue));
    }

    public void setFromFile(File file) throws Exception {

        if (file.exists()) {
            if (!file.isFile()) {
                throw new RuntimeException(new Exception("you can not get bitmap of a folder , you must choose the image file and not a folder..."));
            }
        } else {
            throw new RuntimeException(new Exception("your path does not exist.make sure you are getting bitmap of a valid file"));
        }

        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public void setFromResources(int resource) throws Exception {
        bitmap = BitmapFactory.decodeResource(context.getResources(), resource);
    }
    
    
    
    
    /*
    this function (getMostRepeatedInteger) is from StackOverFlow:
    credits and answer link :
    https://stackoverflow.com/a/49462577/17808329
    */

    @SuppressLint("DiscouragedApi")
    public void setFromResources(String resourceName, String resourceFolder) throws Exception {
        setFromResources(context.getResources().getIdentifier(resourceName, resourceFolder, context.getPackageName()));
    }

    public void setFromAssets(String assets) throws IOException {
        bitmap = BitmapFactory.decodeStream(context.getAssets().open(assets));
    }

    public void rotate(int a) {


        if (bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }

        if (a > 360 || a < 0) {
            throw new RuntimeException(new Exception("you can not set the angle or less than zero or to higher than 360 degrees"));
        }


        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), getRotate(new Matrix(), a), true);


        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }

    }

    private Matrix getRotate(Matrix matrix, int angle) {
        matrix.postRotate(angle);
        return matrix;
    }

    public int getWidthPx() {
        if (bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }

        return bitmap.getWidth();
    }

    public int getHeightPx() {
        if (bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }

        return bitmap.getHeight();
    }

    public void flipHorizontally() {

        if (bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }

        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), flip(true, new Matrix()), true);
        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    public void flipVertically() {

        if (bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }

        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), flip(false, new Matrix()), true);
        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    private Matrix flip(boolean horizontal, Matrix m) {

        if (horizontal) {

            m.postScale(-1, 1, bitmap.getWidth() / 2, bitmap.getHeight() / 2);


        } else {


            m.postScale(1, -1, bitmap.getWidth() / 2, bitmap.getHeight() / 2);

        }

        return m;


    }

    public void setContrast(int contrast) {

        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, 0,
                        0, contrast, 0, 0, 0,
                        0, 0, contrast, 0, 0,
                        0, 0, 0, 1, 0
                });


        Canvas canvas = new Canvas(temp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        bitmap = temp;


        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }


    }

    public void setBrightness(int brightness) {

        //here we are creating a temporary copy of your bitmap

        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        //here we are settings the image colors properties

        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        1, 0, 0, 0, brightness,
                        0, 1, 0, 0, brightness,
                        0, 0, 1, 0, brightness,
                        0, 0, 0, 1, 0
                });


        Canvas canvas = new Canvas(temp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        bitmap = temp;


        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }


    }

    public void setAlpha(int alpha) {

        //you will create a temporary copy of your bitmap

        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        //you will put the copy into a temporary Canvas

        Canvas c = new Canvas(temp);

        //you will create a temporary Paint to set alpha

        Paint p = new Paint();

        p.setAlpha(alpha);

        //you will draw (merge) original bitmap (bitmap) with temporary bitmap (temp)

        c.drawBitmap(bitmap, 0, 0, p);

        //here you will set the old bitmap to the new bitmap

        bitmap = temp;


        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }


    }

    public void setFilter(String type) {


        if (type.equals("negative")) {


            Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

            Paint paint = new Paint();

            paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(NEGATIVE)));

            Canvas c = new Canvas(temp);

            c.drawBitmap(bitmap, 0, 0, paint);

            bitmap = temp;


            if (bitmaps != null) {
                bitmaps.add(bitmap);
            }


        }

        if (type.equals("oldBlackWhite")) {


            Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

            Paint paint = new Paint();

            ColorMatrix cm = new ColorMatrix();

            cm.setSaturation(0);

            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            Canvas c = new Canvas(temp);

            c.drawBitmap(bitmap, 0, 0, paint);

            bitmap = temp;


            if (bitmaps != null) {
                bitmaps.add(bitmap);
            }


        }


    }

    public void blur(int blurValue) {


        if ((getWidthPx() > 8) && (getHeightPx() > 8)) {


            Bitmap temp = bitmap.copy(bitmap.getConfig(), true);


            temp = Bitmap.createScaledBitmap(temp, (temp.getWidth() / blurValue), (temp.getHeight() / blurValue), false);

            bitmap = Bitmap.createScaledBitmap(temp, (temp.getWidth() * blurValue), (temp.getHeight() * blurValue), false);


            if (bitmaps != null) {
                bitmaps.add(bitmap);
            }


        }

    }

    public int getMostUsedColor() {

        return getMostRepeatedInteger();

    }

    public String getMostUsedColorAsString() {

        return "#" + Integer.toHexString(getMostUsedColor());

    }

    public int getMostRepeatedInteger() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return ((Integer) ((Entry) ((Map) getPixels().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))).entrySet().stream().max(new RandomClass()).get()).getKey()).intValue();
        }
        return 0;
    }

    public void setWidthAndHeightPx(int width, int height) {

        if (width == 0 || width < 0) {
            throw new RuntimeException(new Exception("the width of image can not be less than zero or zero"));
        }

        if (height == 0 || height < 0) {
            throw new RuntimeException(new Exception("the height of image can not be less than zero or zero"));
        }

        try {

            bitmap = bitmap.copy(bitmap.getConfig(), true);

            bitmap.reconfigure(width, height, bitmap.getConfig());

        } catch (Exception e) {

            Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

            tmp = Bitmap.createScaledBitmap(tmp, width, height, false);

            bitmap = tmp;


        }

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }


    }

    public void addBitmap(Bitmap bitmap, int x, int w) {

    }

    public ArrayList<Integer> getPixels() {

        ArrayList<Integer> data = new ArrayList<>();

        Bitmap temp = bitmap.copy(bitmap.getConfig(), true);

        int[] pixels = new int[getHeightPx() * getWidthPx()];

        temp.getPixels(pixels, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());

        for (int pixel : pixels) {
            data.add(pixel);
        }


        return data;

    }

    public void changeColor(int color, int color2) {

        Bitmap temp = bitmap.copy(bitmap.getConfig(), true);

        int[] pixels = new int[getHeightPx() * getWidthPx()];

        temp.getPixels(pixels, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());

        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] == color) {

                pixels[i] = color2;
            }
        }

        temp.setPixels(pixels, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());

        bitmap = temp;


        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }

    }

    public void changeColor(String color, String color2) {

        changeColor(Color.parseColor(color), Color.parseColor(color2));

    }

    public int getColorAt(int x, int y) {
        return bitmap.getPixel(x, y);
    }

    public String getColorStringAt(int x, int y) {
        return "#" + Integer.toHexString(getColorAt(x, y));
    }

    public int getColorCount(int color) {

        int count = 0;

        for (int a = 0; a < getPixels().size(); a++) {
            if (getPixels().get(a) == color) {
                count = count + 1;
            }
        }

        return count;


    }

    public int getColorCount(String color) {

        return getColorCount(Color.parseColor(color));

    }

    public void setPixel(int x, int y, int color) {

        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        tmp.setPixel(x, y, color);

        bitmap = tmp;


        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }


    }

    public void crop(int fromX, int toX, int fromY, int toY) {

        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());


        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = hideP(fromX, toX, fromY, toY).copy(hideP(fromX, toX, fromY, toY).getConfig(), true);

        int[] i = new int[getHeightPx() * getWidthPx()];
        int[] i2 = new int[getHeightPx() * getWidthPx()];

        int[] i3 = new int[get(toX - fromX) * get(toY - fromY)];

        ArrayList<Integer> tempdata = new ArrayList<>();


        tmp.getPixels(i, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());
        tmp2.getPixels(i2, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());

        for (int a = 0; a < i.length; a++) {

            if (!(i[a] == i2[a])) {

                tempdata.add(i[a]);

            }


        }

        for (int a = 0; a < tempdata.size(); a++) {
            i3[a] = tempdata.get(a);
        }

        Bitmap temporary = Bitmap.createBitmap(get(toX - fromX), get(toY - fromY), tmp.getConfig());

        temporary.setPixels(i3, 0, get(toX - fromX), 0, 0, get(toX - fromX), get(toY - fromY));

        bitmap = temporary;


        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }


    }

    public void hide(int fromX, int toX, int fromY, int toY) {

        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        Paint paint = new Paint();

        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));

        Canvas c = new Canvas(temp);

        c.drawBitmap(bitmap, 0, 0, null);

        c.drawRect(fromX, fromY, toX, toY, paint);

        bitmap = temp;


        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }


    }

    private Bitmap hideP(int fromX, int toX, int fromY, int toY) {

        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        Paint paint = new Paint();

        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));

        Canvas c = new Canvas(temp);

        c.drawBitmap(bitmap, 0, 0, null);

        c.drawRect(fromX, fromY, toX, toY, paint);

        return temp;


    }

    public void addWaterMarkTopCenter(Bitmap b, int waterMarkWidth, int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = changeWidthAndHeight(b, waterMarkWidth, wareMarkHeight);

        tmp2 = tmp2.copy(tmp2.getConfig(), true);


        Canvas c = new Canvas(tmp);

        int x = ((tmp.getWidth() - tmp2.getWidth()) / 2);
        int y = 0;

        c.drawBitmap(tmp2, x, y, null);

        bitmap = tmp;

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    public void addWaterMarkTopRight(Bitmap b, int waterMarkWidth, int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = changeWidthAndHeight(b, waterMarkWidth, wareMarkHeight);

        tmp2 = tmp2.copy(tmp2.getConfig(), true);


        Canvas c = new Canvas(tmp);

        int x = tmp.getWidth() - tmp2.getWidth();
        int y = 0;

        c.drawBitmap(tmp2, x, y, null);

        bitmap = tmp;

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    public void addWaterMarkTopLeft(Bitmap b, int waterMarkWidth, int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = changeWidthAndHeight(b, waterMarkWidth, wareMarkHeight);

        tmp2 = tmp2.copy(tmp2.getConfig(), true);


        Canvas c = new Canvas(tmp);

        int x = 0;
        int y = 0;

        c.drawBitmap(tmp2, x, y, null);

        bitmap = tmp;

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    public void addWaterMarkBottomCenter(Bitmap b, int waterMarkWidth, int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = changeWidthAndHeight(b, waterMarkWidth, wareMarkHeight);

        tmp2 = tmp2.copy(tmp2.getConfig(), true);


        Canvas c = new Canvas(tmp);

        int x = ((tmp.getWidth() - tmp2.getWidth()) / 2);
        int y = tmp.getHeight() - tmp2.getHeight();

        c.drawBitmap(tmp2, x, y, null);

        bitmap = tmp;

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    public void addWaterMarkBottomRight(Bitmap b, int waterMarkWidth, int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = changeWidthAndHeight(b, waterMarkWidth, wareMarkHeight);

        tmp2 = tmp2.copy(tmp2.getConfig(), true);


        Canvas c = new Canvas(tmp);

        int x = tmp.getWidth() - tmp2.getWidth();
        int y = tmp.getHeight() - tmp2.getHeight();

        c.drawBitmap(tmp2, x, y, null);

        bitmap = tmp;

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    public void addWaterMarkBottomLeft(Bitmap b, int waterMarkWidth, int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = changeWidthAndHeight(b, waterMarkWidth, wareMarkHeight);

        tmp2 = tmp2.copy(tmp2.getConfig(), true);


        Canvas c = new Canvas(tmp);

        int x = 0;
        int y = tmp.getHeight() - tmp2.getHeight();

        c.drawBitmap(tmp2, x, y, null);

        bitmap = tmp;

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    public void addWaterMarkCenter(Bitmap b, int waterMarkWidth, int wareMarkHeight) {

        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = changeWidthAndHeight(b, waterMarkWidth, wareMarkHeight);

        tmp2 = tmp2.copy(tmp2.getConfig(), true);


        Canvas c = new Canvas(tmp);

        int x = ((tmp.getWidth() - tmp2.getWidth()) / 2);
        int y = ((tmp.getHeight() - tmp2.getHeight()) / 2);

        c.drawBitmap(tmp2, x, y, null);

        bitmap = tmp;

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }


    }

    public void addWaterMarkCenterRight(Bitmap b, int waterMarkWidth, int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = changeWidthAndHeight(b, waterMarkWidth, wareMarkHeight);

        tmp2 = tmp2.copy(tmp2.getConfig(), true);


        Canvas c = new Canvas(tmp);

        int x = tmp.getWidth() - tmp2.getWidth();
        int y = ((tmp.getHeight() - tmp2.getHeight()) / 2);

        c.drawBitmap(tmp2, x, y, null);

        bitmap = tmp;

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    public void addWaterMarkCenterLeft(Bitmap b, int waterMarkWidth, int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(), true);

        Bitmap tmp2 = changeWidthAndHeight(b, waterMarkWidth, wareMarkHeight);

        tmp2 = tmp2.copy(tmp2.getConfig(), true);


        Canvas c = new Canvas(tmp);

        int x = 0;
        int y = ((tmp.getHeight() - tmp2.getHeight()) / 2);

        c.drawBitmap(tmp2, x, y, null);

        bitmap = tmp;

        if (bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }

    public Bitmap getResult() {
        return bitmap;
    }

    private int get(int a) {

        if (a < 0) {
            return ((-1) * (a));
        }
        return a;


    }

    public void deleteHistory() {

        bitmaps = new ArrayList<>();

    }

    public int getHistoryEditingsCount() {

        if (bitmaps == null || bitmaps.size() == 0) {

            return 0;

        }

        return bitmaps.size();


    }

    public Bitmap getHistoryImageAt(int position) {

        return bitmaps.get(position);

    }

    public String getBitmap() {
        return getPixels().toString();
    }

    public void setBitmap(String BitMapData) {

        BitMapData = BitMapData.replace("[", "").replace("]", "").replace(" ", "");

        ArrayList<String> Al = new ArrayList<>(java.util.Arrays.asList(BitMapData.split(",")));

        int[] pixels = new int[Al.size()];

        for (int a = 0; a < Al.size(); a++) {
            pixels[a] = Integer.parseInt(Al.get(a));
        }

        Bitmap temp = bitmap.copy(bitmap.getConfig(), true);


        temp.setPixels(pixels, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());

        bitmap = temp;

    }

    public static final /* synthetic */ class RandomClass implements Comparator {
        public int compare(Object obj, Object obj2) {
            return ImageUtils.randomThing((Entry) obj, (Entry) obj2);
        }
    }


}
