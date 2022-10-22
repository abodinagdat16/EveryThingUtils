/*THE IMAGEUTILS PROJECT , ALL COPYRIGHT SAVED , ARABWARE 2022*/

/*

credits :

ArabWare : about 75%

StackOverFlow : about 20%

Sketchup and others : about 5%

*/


package com.android.prime.arab.ware.everythingutils;


import android.content.Context; /*the most important class in every android device , without this
class no android device can do any thing, just one word , the brain of apps? */
import java.io.File; /*the class which is used to manage files but google are killing it
year by year , so in android 14 or 15 , may be , it will be fully deprecated, currently android/data folder
is only inaccessible on android 11 and up by using that class (you can use DocumenmFile SAF...) */
import java.io.IOException; /*An interface to get errors related to files errors like no access to file */
import android.graphics.Bitmap; /* the class which get the colors of an image file , it can create an image from zero also */
import android.graphics.BitmapFactory; /*the class which creates Bitmap from file , InputStream (assets , etc) and resources */
import android.graphics.Canvas; /* a class that can manage more than a Bitmap,  like drawing Bitmap on a Bitmap */
import android.graphics.ColorFilter;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import java.util.ArrayList; /* a class which is related to List Making , here we are using it to create array list of Strings */
import java.util.Map.Entry;
import java.util.Map;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import android.view.View;
import android.widget.ImageView;
import java.net.HttpURLConnection;


/*the class definition*/
@java.lang.SuppressWarnings("deprecation")
public class ImageUtils {
    
    /*
    the field (float[]) is from StackOverFlow :
    credits and answer link :
    
    https://stackoverflow.com/a/17871384/17808329
    
    */
    
   private static final float[] NEGATIVE = { 
    -1.0f,     0,     0,    0, 255, // red
        0, -1.0f,     0,    0, 255, // green
        0,     0, -1.0f,    0, 255, // blue
        0,     0,     0, 1.0f,   0  // alpha  
  };
  
  
    
    
    private int[] i,i2,i3;
    
    
    /*here we save the progress of image editing */
    public ArrayList<Bitmap> bitmaps;
    
    
    public Context context;
    
    /* the image / painting data */
    public Bitmap bitmap;
    
    
    /* here you must set the valid Context , like a working activity, service , fragment , dialog fragment , broadcast receiver, and application , they should be working and not called by a static java calling */
    
    public ImageUtils(Context c) {
        context = c;
    }
    
    
    public void setFromFile(File file) throws Exception {
        
        if(file.exists()) {
            if(!file.isFile()) {
                throw new RuntimeException(new Exception("you can not get bitmap of a folder , you must choose the image file and not a folder..."));
            }
        } else {
            throw new RuntimeException(new Exception("your path does not exist.make sure you are getting bitmap of a valid file"));
        }
        
        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
    }
    
    public void setFromResources(int resource) throws Exception {
        bitmap = BitmapFactory.decodeResource(context.getResources(),resource);
    }
    
    public void setFromResources(String resourceName , String resourceFolder) throws Exception {
        setFromResources(context.getResources().getIdentifier(resourceName,resourceFolder, context.getPackageName()));
    }
    
    public void setFromAssets(String assets) throws IOException {
        bitmap = BitmapFactory.decodeStream(context.getAssets().open(assets));
    }
    
    /*not supported currently*/
    
    /*
    
    public void setFromUrl(String url) {
        
    }
    
    */
    
    public void rotate(int a) {
        
        
        
        
        
        if(bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }
        
        if(a > 360 || a < 0) {
            throw new RuntimeException(new Exception("you can not set the angle or less than zero or to higher than 360 degrees"));
        }
        
        
        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),getRotate(new Matrix(),a),true);
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
    }
    
    
    private Matrix getRotate(Matrix matrix, int angle) {
        matrix.postRotate(angle);
        return matrix;
    }
    
    
    public int getWidthPx() {
        if(bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }
        
        return (int)bitmap.getWidth();
    }
    
    public int getHeightPx() {
        if(bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }
        
        return (int)bitmap.getHeight();
    }
    
    
    
    public void flipHorizontally() {
        
        if(bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }
        
        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),flip(true,new Matrix()),true);
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    public void flipVertically() {
        
        if(bitmap == null) {
            throw new RuntimeException(new Exception("you must load an image or bitmap first"));
        }
        
        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),flip(false,new Matrix()),true);
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    
    private Matrix flip(boolean horizontal , Matrix m) {
        
        if(horizontal) {
            
            m.postScale(-1,1,bitmap.getWidth()/2,bitmap.getHeight()/2);
            
            
        } else {
            
            
            m.postScale(1,-1,bitmap.getWidth()/2,bitmap.getHeight()/2);
            
        }
        
        return m;
        
        
    }
    
    
    
    public void setContrast(int contrast) {
        
        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        
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
        
        
        
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
    }
    
    
    public void setBrightness(int brightness) {
        
        //here we are creating a temporary copy of your bitmap
        
        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        
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
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
    }
    
    public void setAlpha(int alpha) {
        
        //you will create a temporary copy of your bitmap
        
        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        
        //you will put the copy into a temporary Canvas
        
        Canvas c = new Canvas(temp);
        
        //you will create a temporary Paint to set alpha
        
        Paint p = new Paint();
        
        p.setAlpha(alpha);
        
        //you will draw (merge) original bitmap (bitmap) with temporary bitmap (temp)
        
        c.drawBitmap(bitmap , 0 , 0 , p);
        
        //here you will set the old bitmap to the new bitmap
        
        bitmap = temp;
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
    }
    
    
    
    /*
    
    use this to set a filter on an image
    
    filter types are , "oldBlackWhite" , "negative"
    
    */
    
    public void setFilter(String type) {
        
        
        if(type.equals("negative")||type=="negative") {
        
        
        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        
        Paint paint = new Paint();
        
        paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(NEGATIVE)));
        
        Canvas c = new Canvas(temp);
        
        c.drawBitmap(bitmap,0,0,paint);
        
        bitmap = temp;
        
        
        
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
        }
        
        if(type.equals("oldBlackWhite")||type=="oldBlackWhite") {
        
        
        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        
        Paint paint = new Paint();
        
        ColorMatrix cm = new ColorMatrix();
        
        cm.setSaturation(0);
        
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        
        Canvas c = new Canvas(temp);
        
        c.drawBitmap(bitmap,0,0,paint);
        
        bitmap = temp;
        
        
        
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
        }
        
        
        

        
        
        
        
        
        
    }
    
    
    public void blur(int blurValue) {
        
        
        if((getWidthPx() > 8) && (getHeightPx() > 8)) {
                
                
                Bitmap temp = bitmap.copy(bitmap.getConfig(),true);
                
                
                
                
                temp = Bitmap.createScaledBitmap(temp,((int)temp.getWidth()/blurValue),((int)temp.getHeight()/blurValue),false);
                
                bitmap = Bitmap.createScaledBitmap(temp,((int)temp.getWidth()*blurValue),((int)temp.getHeight()*blurValue),false);
                
                
                
                if(bitmaps != null) {
                    bitmaps.add(bitmap);
                    }

                
            }
        
    }
    
    
    
    
    public int getMostUsedColor() {
        
        return getMostRepeatedInteger();
        
    }
    
    public String getMostUsedColorAsString() {
        
        return "#" + Integer.toHexString(getMostUsedColor()).substring(0);
        
    }
    
    
    
    
    /*
    this function (getMostRepeatedInteger) is from StackOverFlow:
    credits and answer link :
    https://stackoverflow.com/a/49462577/17808329
    */
    
    
    public int getMostRepeatedInteger() {
        return Integer.valueOf(((Integer) ((Entry) ((Map) getPixels().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))).entrySet().stream().max(new RandomClass()).get()).getKey()).intValue()).intValue();
    }

    static /* synthetic */ int randomThing(Entry entry, Entry entry2) {
        if (entry.getValue() == entry2.getValue()) {
            return Long.compare((long) ((Integer) entry.getKey()).intValue(), (long) ((Integer) entry2.getKey()).intValue());
        }
        return Long.compare(((Long) entry.getValue()).longValue(), ((Long) entry2.getValue()).longValue());
    }
    
    public final /* synthetic */ class RandomClass implements Comparator {
    public final int compare(Object obj, Object obj2) {
        return ImageUtils.randomThing((Entry) obj, (Entry) obj2);
    }
}

    
    


                   
    
    
    
    
    
    
    
    public void setWidthAndHeightPx(int width , int height) {
        
        if(width == 0 || width < 0) {
            throw new RuntimeException(new Exception("the width of image can not be less than zero or zero"));
        }
        
        if(height == 0 || height < 0) {
            throw new RuntimeException(new Exception("the height of image can not be less than zero or zero"));
        }
        
        try {
        
        bitmap = bitmap.copy(bitmap.getConfig(),true);
        
        bitmap.reconfigure(width,height,bitmap.getConfig());
        
        } catch(Exception e) {
            
            Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
            
            tmp = Bitmap.createScaledBitmap(tmp,width,height,false);
            
            bitmap = tmp;
            
            
            
            
        }
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
    }
    
    
    
    
    public void addBitmap(Bitmap bitmap , int x , int w) {
        
    }
    
    
    public ArrayList<Integer> getPixels() {
        
        ArrayList<Integer> data = new ArrayList<>();
        
        Bitmap temp = bitmap.copy(bitmap.getConfig(),true);
        
        int[] pixels = new int[getHeightPx() * getWidthPx()];
        
        temp.getPixels(pixels, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());
        
        for(int a = 0;a<pixels.length;a++) {
            data.add(pixels[a]);
        }
        
        
        
        return data;
        
    }
    
    
    
    
    public void changeColor(int color , int color2) {
        
        Bitmap temp = bitmap.copy(bitmap.getConfig(),true);
        
        int[] pixels = new int[getHeightPx() * getWidthPx()];

temp.getPixels(pixels, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());

for(int i = 0; i < pixels.length; i++) {
    if(pixels[i] == color) {
        
        pixels[i] = color2;
    }
}

temp.setPixels(pixels,0,getWidthPx(),0, 0, getWidthPx(),getHeightPx());
        
        bitmap = temp;
        
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
    }
    
    

    public void changeColor(String color , String color2) {
    
    changeColor(Color.parseColor(color),Color.parseColor(color2));
    
    }
    
    
    public int getColorAt(int x , int y) {
        return bitmap.getPixel(x,y);
    }
    
    public String getColorStringAt(int x , int y) {
        return "#" + Integer.toHexString(getColorAt(x,y)).substring(0);
    }
    
    public int getColorCount(int color) {
        
        int count = 0;
        
        for(int a = 0; a < getPixels().size();a++) {
            if(getPixels().get(a) == color) {
                count = count + 1;
            }
        }
        
        return count;
        
        
    }
    
    public int getColorCount(String color) {
        
        return getColorCount(Color.parseColor(color));
        
    }
    
    
    public void setPixel(int x , int y , int color) {
        
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        tmp.setPixel(x,y,color);
        
        bitmap = tmp;
        
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
    }
    
    public void setPixel(int x , int y , String color) {
        setPixel(x,y,Color.parseColor(color));
    }
    
    
    public void save(String folder , String name , int quality) throws Exception {
        
        if(!(new File(folder).exists())) {
            throw new RuntimeException(new Exception("unable to find the folder to save the image"));
        } else {
            if(new File(folder).isDirectory()) {
                if(!folder.endsWith("/")) {
                    folder = folder + "/";
                }
            } else {
                throw new RuntimeException(new Exception("folder String must be a folder path and not a file path"));
            }
        }
        
        try (java.io.FileOutputStream out = new java.io.FileOutputStream(new File(folder+name))) {
            
            if(name.replace("P","p").replace("N","n").replace("G","g").endsWith(".png")) {
            
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, out);
            
            } else {
                
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
            }
            
        } catch (Exception e) {
            throw e;
        }
        
        
    }
    
    
    
    public void crop(int fromX , int toX , int fromY , int toY) {
        
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        
        
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = hideP(fromX,toX,fromY,toY).copy(hideP(fromX,toX,fromY,toY).getConfig(),true);
        
        i = new int[getHeightPx()*getWidthPx()];
        i2 = new int[getHeightPx()*getWidthPx()];
        
        i3 = new int[get(toX-fromX)*get(toY-fromY)];
        
        ArrayList<Integer> tempdata = new ArrayList<>();
        
        
        
        tmp.getPixels(i, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());
        tmp2.getPixels(i2, 0, getWidthPx(), 0, 0, getWidthPx(), getHeightPx());
        
        for(int a = 0; a<i.length;a++) {
            
            if(!(i[a]==i2[a])) {
                
                tempdata.add(i[a]);
                
            }
            
            
            
        }
        
        for(int a = 0; a<tempdata.size();a++) {
            i3[a] = tempdata.get(a);
        }
        
        Bitmap temporary = Bitmap.createBitmap(get(toX-fromX),get(toY-fromY),tmp.getConfig());
        
        temporary.setPixels(i3,0,get(toX-fromX),0, 0, get(toX-fromX), get(toY-fromY));
        
        bitmap = temporary;
        
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
    }
    
    
    public void hide(int fromX , int toX , int fromY , int toY) {
        
        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        
        Paint paint = new Paint();
        
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        
        Canvas c = new Canvas(temp);
        
        c.drawBitmap(bitmap, 0, 0, null);
        
        c.drawRect(fromX, fromY, toX, toY, paint);
        
        bitmap = temp;
        
        
        
        
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
        
    }
    
    
    private Bitmap hideP(int fromX , int toX , int fromY , int toY) {
        
        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        
        Paint paint = new Paint();
        
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        
        Canvas c = new Canvas(temp);
        
        c.drawBitmap(bitmap, 0, 0, null);
        
        c.drawRect(fromX, fromY, toX, toY, paint);
        
        return temp;
        
        
        
    }
    
    
    public void addWaterMarkTopCenter(Bitmap b , int waterMarkWidth , int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = changeWidthAndHeight(b,waterMarkWidth,wareMarkHeight);
        
        tmp2 = tmp2.copy(tmp2.getConfig(),true);
        
        
        Canvas c = new Canvas(tmp);
        
        int x = ((tmp.getWidth()-tmp2.getWidth())/2);
        int y = 0;
        
        c.drawBitmap(tmp2,x,y,null);
        
        bitmap = tmp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    public void addWaterMarkTopRight(Bitmap b , int waterMarkWidth , int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = changeWidthAndHeight(b,waterMarkWidth,wareMarkHeight);
        
        tmp2 = tmp2.copy(tmp2.getConfig(),true);
        
        
        Canvas c = new Canvas(tmp);
        
        int x = tmp.getWidth()-tmp2.getWidth();
        int y = 0;
        
        c.drawBitmap(tmp2,x,y,null);
        
        bitmap = tmp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    public void addWaterMarkTopLeft(Bitmap b , int waterMarkWidth , int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = changeWidthAndHeight(b,waterMarkWidth,wareMarkHeight);
        
        tmp2 = tmp2.copy(tmp2.getConfig(),true);
        
        
        Canvas c = new Canvas(tmp);
        
        int x = 0;
        int y = 0;
        
        c.drawBitmap(tmp2,x,y,null);
        
        bitmap = tmp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    public void addWaterMarkBottomCenter(Bitmap b , int waterMarkWidth , int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = changeWidthAndHeight(b,waterMarkWidth,wareMarkHeight);
        
        tmp2 = tmp2.copy(tmp2.getConfig(),true);
        
        
        Canvas c = new Canvas(tmp);
        
        int x = ((tmp.getWidth()-tmp2.getWidth())/2);
        int y = tmp.getHeight()-tmp2.getHeight();
        
        c.drawBitmap(tmp2,x,y,null);
        
        bitmap = tmp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    public void addWaterMarkBottomRight(Bitmap b , int waterMarkWidth , int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = changeWidthAndHeight(b,waterMarkWidth,wareMarkHeight);
        
        tmp2 = tmp2.copy(tmp2.getConfig(),true);
        
        
        Canvas c = new Canvas(tmp);
        
        int x = tmp.getWidth()-tmp2.getWidth();
        int y = tmp.getHeight()-tmp2.getHeight();
        
        c.drawBitmap(tmp2,x,y,null);
        
        bitmap = tmp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    public void addWaterMarkBottomLeft(Bitmap b , int waterMarkWidth , int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = changeWidthAndHeight(b,waterMarkWidth,wareMarkHeight);
        
        tmp2 = tmp2.copy(tmp2.getConfig(),true);
        
        
        Canvas c = new Canvas(tmp);
        
        int x = 0;
        int y = tmp.getHeight()-tmp2.getHeight();
        
        c.drawBitmap(tmp2,x,y,null);
        
        bitmap = tmp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    public void addWaterMarkCenter(Bitmap b , int waterMarkWidth , int wareMarkHeight) {
        
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = changeWidthAndHeight(b,waterMarkWidth,wareMarkHeight);
        
        tmp2 = tmp2.copy(tmp2.getConfig(),true);
        
        
        Canvas c = new Canvas(tmp);
        
        int x = ((tmp.getWidth()-tmp2.getWidth())/2);
        int y = ((tmp.getHeight()-tmp2.getHeight())/2);
        
        c.drawBitmap(tmp2,x,y,null);
        
        bitmap = tmp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
        
    }
    
    public void addWaterMarkCenterRight(Bitmap b , int waterMarkWidth , int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = changeWidthAndHeight(b,waterMarkWidth,wareMarkHeight);
        
        tmp2 = tmp2.copy(tmp2.getConfig(),true);
        
        
        Canvas c = new Canvas(tmp);
        
        int x = tmp.getWidth()-tmp2.getWidth();
        int y = ((tmp.getHeight()-tmp2.getHeight())/2);
        
        c.drawBitmap(tmp2,x,y,null);
        
        bitmap = tmp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    public void addWaterMarkCenterLeft(Bitmap b , int waterMarkWidth , int wareMarkHeight) {
        Bitmap tmp = bitmap.copy(bitmap.getConfig(),true);
        
        Bitmap tmp2 = changeWidthAndHeight(b,waterMarkWidth,wareMarkHeight);
        
        tmp2 = tmp2.copy(tmp2.getConfig(),true);
        
        
        Canvas c = new Canvas(tmp);
        
        int x = 0;
        int y = ((tmp.getHeight()-tmp2.getHeight())/2);
        
        c.drawBitmap(tmp2,x,y,null);
        
        bitmap = tmp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
    }
    
    public static Bitmap getBitmapFromFile(File file) throws Exception {
        if(file.exists()) {
            if(!file.isFile()) {
                throw new RuntimeException(new Exception("you can not get bitmap of a folder , you must choose the image file and not a folder..."));
            }
        } else {
            throw new RuntimeException(new Exception("your path does not exist.make sure you are getting bitmap of a valid file"));
        }
        
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }
    
    public static Bitmap getBitmapFromAssets(Context c , String assets) throws IOException {
        return BitmapFactory.decodeStream(c.getAssets().open(assets));
    }
    
    public static Bitmap getBitmapFromResource(Context c , int resource) throws Exception {
        return BitmapFactory.decodeResource(c.getResources(),resource);
    }
    
    public static Bitmap getBitmapFromResource(Context c , String resourceFolder , String resourceName) throws Exception {
        return BitmapFactory.decodeResource(c.getResources(),c.getResources().getIdentifier(resourceName,resourceFolder, c.getPackageName()));
    }
    
    public static Bitmap changeWidthAndHeight(Bitmap b , int w , int h) {
        
        if(b==null) {
            throw new RuntimeException(new Exception("your bitmap of image is null (empty , no data) make sure you loaded a real image correctly"));
        }
        
        
        if(w == 0 || w < 0) {
            throw new RuntimeException(new Exception("the width of image can not be less than zero or zero"));
        }
        
        if(h == 0 || h < 0) {
            throw new RuntimeException(new Exception("the height of image can not be less than zero or zero"));
        }
        
        try {
        
        b = b.copy(b.getConfig(),true);
        
        b.reconfigure(w,h,b.getConfig());
        
        } catch(Exception e) {
            
            Bitmap tmp = b.copy(b.getConfig(),true);
            
            tmp = Bitmap.createScaledBitmap(tmp,w,h,false);
            
            b = tmp;
            
            
            
        }
        
        return b;
        
        
    }
    
    
    public Bitmap getResult() {
        return bitmap;
    }
    
    
    private int get(int a) {
        
        if(a<0) {
            return ((-1) * (a));
        }
        return a;
        
        
    }
    
    
    public static int getColorFromRGB(int red , int green , int blue) {
        return Color.rgb(red, green, blue);
    }
    
    public static String getStringColorFromRGB(int red , int green , int blue) {
        return "#" + Integer.toHexString(getColorFromRGB(red,green,blue)).substring(0);
    }
    
    
    public void deleteHistory() {
        
        bitmaps = new ArrayList<>();
        
    }
    
    public int getHistoryEditingsCount() {
        
        if(bitmaps == null || bitmaps.size() == 0) {
            
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
        
        BitMapData = BitMapData.replace("[","").replace("]","").replace(" ","");
        
        ArrayList<String> Al = new ArrayList<String>(java.util.Arrays.asList(BitMapData.split(",")));
        
        int[] pixels = new int[Al.size()];
        
        for(int a = 0; a < Al.size(); a++) {
            pixels[a] = Integer.valueOf(Al.get(a));
        }
        
        Bitmap temp = bitmap.copy(bitmap.getConfig(),true);
        
        
        temp.setPixels(pixels,0,getWidthPx(),0, 0, getWidthPx(),getHeightPx());
        
        bitmap = temp;
        
    }
    
    
    public static final String[] credits_dont_delete = {
        "credits" , "dont delete me" , "it is illegal" , "haram" , "creators list : " ,
        "ArabWare , for coding more than the half of class + Modifications" , "StackOverFlow,for some functions , they are mentioned in github page" ,
        "Sketchware, Sketchub , for some info and ideas about bitmaps" , "and to all who helped and suggested me while coding"
    };
    
    
    public static final String TAG = "ImageUtils";
    
    public static final int yearOfThisLibraryClass = 2022;
    
    public static final String country = "Iraq";
    
    public static final String religion = "Islam";
    
    public static final String province = "Najaf";
    
    public static final int age = 18;
    
    public static final boolean isMale = true;
    
    
    
    public void setBitmap(View view) {
        
        Bitmap temp = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(temp);
        view.draw(c);
        bitmap = temp.copy(temp.getConfig(),true);
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
    }
    
    public static Bitmap getBitmap(View view) {
        Bitmap temp = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(temp);
        view.draw(c);
        return temp;
    }
    
    public void setBitmap(ImageView iv) {
        try {
            
            if(((android.graphics.drawable.BitmapDrawable)iv.getDrawable()).getBitmap() == null) {
                
                bitmap = getBitmap((View)iv);
                
            } else {
                
            
            
            bitmap = ((android.graphics.drawable.BitmapDrawable)iv.getDrawable()).getBitmap();
            
            }
            
            
        } catch(Exception e) {
            
            bitmap = getBitmap((View)iv);
            
        }
    }
    
    public static Bitmap getBitmap(ImageView iv) {
        try {
            
            if(((android.graphics.drawable.BitmapDrawable)iv.getDrawable()).getBitmap() == null) {
                
                return getBitmap((View)iv);
                
            } else {
                
            
            
            return ((android.graphics.drawable.BitmapDrawable)iv.getDrawable()).getBitmap();
            
            }
            
            
        } catch(Exception e) {
            
            return getBitmap((View)iv);
            
        }
    }
    
    public static Bitmap createNewBitmap(int widthPx , int heightPx , int color) {
        
        Bitmap temp = Bitmap.createBitmap(widthPx,heightPx,Bitmap.Config.ARGB_8888);
        
        temp = temp.copy(temp.getConfig(),true);
        
        int[] pixels = new int[widthPx*heightPx];

for(int i = 0; i < (widthPx*heightPx); i++) {
    pixels[i] = color;
}


temp.setPixels(pixels,0,widthPx,0, 0, widthPx,heightPx);
        
        return temp;
        
    }
    
    public void recreateOrSetNewBitmap(int widthPx , int heightPx , int color) {
        Bitmap temp = Bitmap.createBitmap(widthPx,heightPx,Bitmap.Config.ARGB_8888);
        if(bitmap == null) {
        
        temp = temp.copy(temp.getConfig(),true);
        } else {
            temp = bitmap.copy(bitmap.getConfig(),true);
        }
        
        
        
        int[] pixels = new int[widthPx*heightPx];

for(int i = 0; i < (widthPx*heightPx); i++) {
    pixels[i] = color;
}


temp.setPixels(pixels,0,widthPx,0, 0, widthPx,heightPx);
        
        bitmap = temp;
        
        if(bitmaps != null) {
            bitmaps.add(bitmap);
        }
        
    }
    
    public static Bitmap createNewBitmap(int w , int h , String color) {
        return createNewBitmap(w,h,Color.parseColor(color));
    }
    
    public void recreateOrSetNewBitmap(int w , int h , String color) {
        recreateOrSetNewBitmap(w,h,Color.parseColor(color));
    }
    
    /*
    
    public void setFromUrl(String url , LoadingInterface li) {
        
        if(li != null) {
            
            li.loading();
            
        }
        
        try {
            
            new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    try {
                    
                    
                    java.io.InputStream is = new java.net.URL(url).openStream();
                    byte[] bs = new byte[is.available()];
                    is.read(bs);
                    BitmapFactory.Options bo = new BitmapFactory.Options();
                    bo.inMutable = true;
                    bitmap = BitmapFactory.decodeByteArray(bs,0,bs.length,bo);
                    
                    if(is == null) {
                        throw new RuntimeException("your input stream is null");
                    }
                    if(bs == null) {
                        throw new RuntimeException("your byte array is null");
                    }
                    
                    if(bitmap == null) {
                        HttpURLConnection huc = ((HttpURLConnection)new java.net.URL(url).openConnection());
                        huc.setDoInput(true);
                        huc.connect();
                        java.io.InputStream is2 = huc.getInputStream();
                        
                    byte[] bs2 = new byte[is2.available()];
                    is2.read(bs2);
                    BitmapFactory.Options bo2 = new BitmapFactory.Options();
                    bo2.inMutable = true;
                    bitmap = BitmapFactory.decodeByteArray(bs2,0,bs2.length,bo2);
                    }
                    
                    if(li != null) {
            
          new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    
                    
                    
                    li.done();

                }
            });
            
            
            
        }
                    
                    
                    } catch(Exception e) {
                        
                        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    
                    if(li != null) {
                        li.error(e.getMessage().toString());
                    }

                }
            });
                    }
        
        
                }
            }).start();
            
        
        
        
        } catch(Exception e) {
            
            if(li != null) {
                
                li.error(e.getMessage().toString());
                
            }
            
        }
        
        
        
        
    }
    
    
    public interface LoadingInterface {
        public void done();
        public void loading();
        public void error(String error);
    }
    
    
    
    
    
    public static Bitmap getBitmapFromText(String text , int color , int backgroundColor , int size , int width , int height , String font) {
        
    }
    
    public static Bitmap getBitmapFromText(String text , int color , int backgroundColor , int size , int width , int height , int font) {
        
    }
    
    */
    
    
    
}
