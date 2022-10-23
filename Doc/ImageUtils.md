## Full Guide To Image Utils Class 
### This Class Can Get Info And Edit An Image With Many Options
#### the class codes are easy to write and use
##### You Must Currently use this library as java files only because downloading it by implementation code in gradle is not the latest , I am facing some problems on uploading latest library so download the java files and build them in your way , YOUR IDE MUST SUPPORT ANDROID 33 SDK IN ORDER TO NOT GETTING ERRORS
> how to create an ImageUtils? , just pass the Context of activity or fragment or dialog fragment or service or BroadcastReceiver....
``` java

ImageUtils iu = new ImageUtils(YourContext);

```

> note : you can add a listener for progress of loading or editing , also it makes your app fast and without slow running at runtime , add the code between every () of or at last of () params , i wrote some examples , I am currently tired to edit all the old guide and make it for all , the code :
``` java
new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
}
```

> how to set an Image from file ? , YourPath is the file path as string

``` java
try {

iu.setFromFile(new java.io.File(YourPath));

} catch(Exception e) {

}

//or you can load without slow running + with events

iu.setFromFile(new java.io.File(YourPath),new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});

```

> how to set an Image from assets ? , YourAssetsPath is the file path in assets as String like "folder/image.jpg" or "image.jpg"
``` java
try {
iu.setFromAssets(YourAssetsPath);
} catch(Exception e) {

}

//or you can load without slow running + with events

iu.setFromAssets(YourAssetsPath,new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});


```

> how to create an image from resources, like R.raw.image or R.drawable.image

``` java

try {
iu.setFromResources(YourResourcesIDAsInteger);
} catch(Exception e) {
}

//or you can load without running slowly and with events

iu.setFromResources(YourResourceIDAsInteger,new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});


```

> how to set an Image from resources by name and folder name , both are String values

``` java

try {
iu.setFromResources(FileName,FolderName);
} catch(Exception e) {
}

//or you can load without running slowly and with events

iu.setFromResources(FileName,FolderName,new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});

```

> how to get the bitmap (so you can get the result every time)
Here we will put it into ImageView widget,  just as example

``` java

Imageview1.setImageBitmap(iu.getResult());

```

> how to get the pixels count or width (pixels units) or height (pixels count)
``` java

int width = iu.getWidthPx();

int height = iu.getHeightPx();

int pixelsCount = iu.getPixels().size(); //or

int pixelsCount = width*height; //same value as above

```

> how to rotate the image with angle between 0-360

``` java

iu.rotate(90);

//or with progress + no slow running

iu.rotate(90,new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});

```

> how to flip the image horizontally 

``` java

iu.flipHorizontally();

//or without running slowly + events

iu.flipHorizontally(new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});

```

> how to flip the image vertically

``` java

iu.flipVertically();

```

> how to set Contrast of the image (0-100)

``` java
iu.setContrast(50);
```

> how to set Brightness of the image (0-100)

``` java

iu.setBrightness(50);

```

> how to set alpha of the image (0-100) , 0 means full transparent and 100 means normal
``` java
iu.setAlpha(50);
```

> how to set filter of the image , currently we support negative and oldBlackWhite 

``` java
iu.setFilter("oldBlackWhite");
```

> how to set blur effect to the image , 3 is the value , change it!
``` java
iu.blur(3);
```

> how to get the most used color in the image as Integer (number)

``` java
int color = iu.getMostUsedColor();
```

> how to get the most used color in the image as string (like #ffffffff)

``` java
String hexColor = iu.getMostUsedColorAsString();
```
> how to set width and height (pixels units) , width and height are Integer numbers values

``` java
iu.setWidthAndHeightPx(width,height);
```
> how to get all pixels of the image as arraylist of Integers which are the color values
``` java
ArrayList<Integer> al = new ArrayList<>();

al = iu.getPixels();

```

> how to get one pixel color by coordinators X and Y

``` java

int color = iu.getColorAt(x,y):

String colorHex = iu.getColorStringAt(x,y);

```

> how to change a color to another (all the image) , color1 and color2 are integers numbers or String hex colors like #ffffffff or 0xFFFFFFFF

``` java
iu.changeColor(color1,color2);

```

> how to change only one pixel color by coordinators X and Y

``` java
iu.setPixel(x,y,color); /*color is an Integer only here but you can set the value from String by using android.graphics.Color.parseColor(YourColorAsString)
*/
```

> how to get a color count , the count of pixels which are are the same color you chose , it accepts String and Integer colors types

``` java

int countOfAColor = iu.getColorCount(YourColor);
```

> how to crop the image , it uses two X values (from x to x ) and two Y values (from y to y) , coordinators data...

``` java

iu.crop(fromx,toX,fromY,toY);

```

> how to hide part of bitmap (making transparent) , same as above , fromX toX and fromY toY

``` java

iu.hide(fromX,toX,fromY,toY);

```

> how to convert rgb color to Integer color or String color ...

```

int color = ImageUtils.getColorFromRGB(red,green,blue);

String colorHex = ImageUtils.getColorFromRGB(red,green,blue);

```
> how to get the history bitmap,  like the first bitmap before editing or previous etc ... it will help to add undo redo or previous next buttons ... something

``` java
//to get the count of history changes
int count = iu.getHistoryEditingsCount();
//to get the bitmap by position (position starts from 0 so if it is the 4th image (example) it will be 3)
imageview1.setImageBitmap(iu.getHistoryImageAt(3));
//how to delete the history
iu.deleteHistory();

```

> how to convert bitmap to String and vice versa 

``` java

YourString = iu.getBitmap();


//to set the value back again

iu.setBitmap(YourString);

```

> how to add water marks on the image ..

These are the supported modes currently :
1-Center

2-TopCenter

3-TopRight

4-TopLeft

5-BottomCenter

6-BottomRight

7-BottomLeft

8-CenterRight

9-CenterLeft

example :

``` java


iu.addWaterMarkCenterRight();

// or

iu.addWaterMarkBottomRight();

```

> how to get bitmap from file/assets/resources without creating ImageUtils 
``` java
try {
YourBitmap = ImageUtils.getBitmapFromFile(new java.io.File(YourFilePath));
YourBitmap2 = ImageUtils.getBitmapFromAssets(YourContext,YourAssetsPath);
YourBitmap3 = ImageUtils.getBitmapFromResource(YourContext,YourInt); //YourInt : like R.raw.image or R.drawable.logo
YourBotmap4 = ImageUtils.getBitmapFromResource(YourContext , FolderName , FileName);

} catch(Exception e) {
}

```

> how to get resize an bitmap without creating the ImageUtils

``` java
YourBitmap = ImageUtils.changeWidthAndHeight(YourBitmap,width,height);
```


> how to get bitmap of a view

``` java

Bitmap b = ImageUtils.getBitmap(YourViewName);

//or

iu.setBitmap(YourViewName);

//it can also work for ImageView (only its image) but if you want the ImageView itself with its content image , you should do (View)yourImageView and not yourImageView directly

```




